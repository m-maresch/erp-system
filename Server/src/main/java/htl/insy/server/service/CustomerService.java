package htl.insy.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import htl.insy.server.domain.event.EventManager;
import htl.insy.server.domain.event.IEventRepository;
import htl.insy.server.model.event.EEventType;
import htl.insy.server.model.other.Category;
import htl.insy.server.model.other.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/customer")
@RestController
public class CustomerService extends AService<Customer>{

    @Autowired
    public CustomerService(IEventRepository repository, EventManager eventManager, ObjectMapper mapper) {
        super(repository, eventManager, mapper, new EEventType[]
                {EEventType.CustomerCreated, EEventType.CustomerUpdated, EEventType.CustomerDeleted},
                Customer.class, c -> c.getName());
    }

    @Transactional
    @GetMapping(path = "/{entityName}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Customer get(@PathVariable("entityName") String name){
        try {
            return eventManager.get(c -> c.getName() == name, type, eventTypes[0], eventTypes[1], eventTypes[2], null, null);
        } catch (IndexOutOfBoundsException ex) {
            return eventManager.get(c -> c.getName() == name, type, eventTypes[0], null, null, null, null);
        }
    }
}
