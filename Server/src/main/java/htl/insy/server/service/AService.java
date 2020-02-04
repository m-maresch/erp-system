package htl.insy.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import htl.insy.server.domain.event.EventManager;
import htl.insy.server.domain.event.IEventRepository;
import htl.insy.server.model.event.EEventType;
import htl.insy.server.model.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Date;
import java.util.function.Function;

public abstract class AService<T> extends ACreateReadService<T> {

    private static Logger logger = LoggerFactory.getLogger(AService.class);

    public AService(IEventRepository repository, EventManager eventManager, ObjectMapper mapper, EEventType[] eventTypes, Class<T> type, Function<T, String> identifier){
        super(repository, eventManager, mapper, eventTypes, type, identifier);
    }

    @Transactional
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody T entity, @RequestParam("requester") String requester){
        updateOrDelete(entity, requester, eventTypes[1], "Update Event");
    }

    @Transactional
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Valid @RequestBody T entity, @RequestParam("requester") String requester){
        updateOrDelete(entity, requester, eventTypes[2], "Delete Event");
    }

    private void updateOrDelete(T entity, String requester, EEventType eventType, String logMessage){
        if (eventManager.getUser(requester).getCanWrite()) {
            try {
                Event event = new Event(new Date(), eventType, mapper.writeValueAsString(entity), requester);
                repository.save(event);
                logger.info(logMessage);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        } else  {
            throw new RuntimeException(new ValidationException());
        }
    }
}
