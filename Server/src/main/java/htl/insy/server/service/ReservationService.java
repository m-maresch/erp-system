package htl.insy.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import htl.insy.server.domain.event.EventManager;
import htl.insy.server.domain.event.IEventRepository;
import htl.insy.server.model.event.EEventType;
import htl.insy.server.model.event.Event;
import htl.insy.server.model.other.Order;
import htl.insy.server.model.other.Reservation;
import htl.insy.server.model.other.WarehouseEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Date;

@RequestMapping(path = "/reservation")
@RestController
public class ReservationService extends ACreateReadService<Reservation> {

    private static Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    public ReservationService(IEventRepository repository, EventManager eventManager, ObjectMapper mapper) {
        super(repository, eventManager, mapper, new EEventType[]{EEventType.Reservation}, Reservation.class, null);
    }

    @Override
    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation create(@Valid @RequestBody Reservation entity, @RequestParam("requester") String requester){
        if(eventManager.getUser(requester).getCanWrite()) {
            try {
                Event event = new Event(new Date(), eventTypes[0], mapper.writeValueAsString(entity), requester);
                repository.save(event);
                event = new Event(new Date(), EEventType.WarehouseEntry, mapper.writeValueAsString(new WarehouseEntry(entity.getAmount(), entity.getItem())), requester);
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
