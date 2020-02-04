package htl.insy.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import htl.insy.server.domain.event.EventManager;
import htl.insy.server.domain.event.IEventRepository;
import htl.insy.server.model.event.EEventType;
import htl.insy.server.model.other.WarehouseExit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/warehouseexit")
@RestController
public class WarehouseExitService extends ACreateReadService<WarehouseExit> {

    @Autowired
    public WarehouseExitService(IEventRepository repository, EventManager eventManager, ObjectMapper mapper) {
        super(repository, eventManager, mapper, new EEventType[]{EEventType.WarehouseExit}, WarehouseExit.class, null);
    }
}
