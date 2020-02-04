package htl.insy.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import htl.insy.server.domain.event.EventManager;
import htl.insy.server.domain.event.IEventRepository;
import htl.insy.server.model.event.EEventType;
import htl.insy.server.model.event.Event;
import htl.insy.server.model.other.Category;
import htl.insy.server.model.other.Supplier;
import htl.insy.server.model.other.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

@RequestMapping(path = "/category")
@RestController
public class CategoryService extends AService<Category> {

    @Autowired
    public CategoryService(IEventRepository repository, EventManager eventManager, ObjectMapper mapper) {
        super(repository, eventManager, mapper, new EEventType[]
                {EEventType.CategoryCreated, EEventType.CategoryUpdated, EEventType.CategoryDeleted},
                Category.class, c -> c.getName());
    }

    @Transactional
    @GetMapping(path = "/{entityName}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Category get(@PathVariable("entityName") String name){
        try {
            return eventManager.get(c -> c.getName() == name, type, eventTypes[0], eventTypes[1], eventTypes[2], null, null);
        } catch (IndexOutOfBoundsException ex) {
            return eventManager.get(c -> c.getName() == name, type, eventTypes[0], null, null, null, null);
        }
    }
}
