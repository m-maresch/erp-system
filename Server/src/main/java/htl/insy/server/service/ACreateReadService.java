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
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class ACreateReadService<T> {

    protected IEventRepository repository;

    protected EventManager eventManager;

    protected ObjectMapper mapper;

    protected EEventType[] eventTypes;

    protected Class<T> type;

    protected Function<T, String> identifier;

    private static Logger logger = LoggerFactory.getLogger(ACreateReadService.class);

    public ACreateReadService(IEventRepository repository, EventManager eventManager, ObjectMapper mapper, EEventType[] eventTypes, Class<T> type, Function<T, String> identifier){
        this.repository = repository;
        this.eventManager = eventManager;
        this.mapper = mapper;
        this.eventTypes = eventTypes;
        this.type = type;
        this.identifier = identifier;
    }

    @Transactional
    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<T> getAll(@RequestParam(value = "after", required = false) String after, @RequestParam(value = "before", required = false) String before){
        Date beforeAsDate = null;
        Date afterAsDate = null;

        try {
            if (before != null && after == null) {
                beforeAsDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").parse(before);
            }
            if (before != null && after != null) {
                beforeAsDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").parse(before);
                afterAsDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").parse(after);
            }
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        try {
            return eventManager.getAll(type, identifier, eventTypes[0], eventTypes[1], eventTypes[2], afterAsDate, beforeAsDate);
        } catch (IndexOutOfBoundsException ex) {
            return eventManager.getAll(type, identifier, eventTypes[0], null, null, afterAsDate, beforeAsDate);
        }
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public T create(@Valid @RequestBody T entity, @RequestParam("requester") String requester){
        if(eventManager.getUser(requester).getCanWrite()) {
            try {
                Event event = new Event(new Date(), eventTypes[0], mapper.writeValueAsString(entity), requester);
                repository.save(event);
                logger.info("Create Event");
                return entity;
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new RuntimeException(new ValidationException());
        }
    }
}
