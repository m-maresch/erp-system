package htl.insy.server.domain.event;

import htl.insy.server.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;

import java.util.Date;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

public interface IEventRepository  extends JpaRepository<Event, Long>{

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "50"))
    @Query(value = "select e from Event e")
    Stream<Event> streamAll();

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "50"))
    Stream<Event> streamAllByDateBefore(Date date);

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "50"))
    Stream<Event> streamAllByDateBetween(Date date1, Date date2);
}
