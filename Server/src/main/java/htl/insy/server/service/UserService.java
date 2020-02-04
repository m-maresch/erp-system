package htl.insy.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import htl.insy.server.domain.event.EventManager;
import htl.insy.server.domain.event.IEventRepository;
import htl.insy.server.model.event.EEventType;
import htl.insy.server.model.event.Event;
import htl.insy.server.model.other.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Date;

@RequestMapping(path = "/user")
@RestController
public class UserService extends AService<User> {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(IEventRepository repository, EventManager eventManager, ObjectMapper mapper) {
        super(repository, eventManager, mapper, new EEventType[]
                {EEventType.UserCreated, EEventType.UserUpdated, EEventType.UserDeleted},
                User.class, u -> u.getUsername());
    }

    @Override
    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User entity, @RequestParam(value = "requester", required = false, defaultValue = "server") String requester) {
        try {
            Event event = new Event(new Date(), eventTypes[0], mapper.writeValueAsString(entity), requester);
            repository.save(event);
            logger.info("Create Event");
            return entity;
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Transactional
    @GetMapping(path = "/{entityName}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public User get(@PathVariable("entityName") String name){
        return eventManager.getUser(name);
    }

    @Transactional
    @PostMapping(path = "/login", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public User login(@Valid @RequestBody Login entity){
        User user = eventManager.getUser(entity.getUsername());

        if (user.getPassword().equals(entity.getPassword())) {
            return user;
        } else {
            throw new RuntimeException(new ValidationException());
        }
    }
}
