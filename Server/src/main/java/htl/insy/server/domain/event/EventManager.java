package htl.insy.server.domain.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import htl.insy.server.model.event.EEventType;
import htl.insy.server.model.event.Event;
import htl.insy.server.model.other.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class EventManager {

    private IEventRepository repository;

    private ObjectMapper mapper;

    @Autowired
    public EventManager(IEventRepository repository, ObjectMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public User getUser(String username){
        return get(u -> u.getUsername().equals(username), User.class, EEventType.UserCreated, EEventType.UserUpdated, EEventType.UserDeleted, null, null);
    }

    public <T> T get(Predicate<T> predicate, Class<T> type, EEventType createEvent, EEventType updateEvent, EEventType deleteEvent, Date after, Date before){
        Optional<T> entity;
        final List<T> matches;

        if (updateEvent != null && deleteEvent != null) {
            final List<T> deleted = getDeleted(type, deleteEvent, after, before);

            matches = getSource(after, before)
                    .filter(e -> e.eventType == createEvent ||
                            e.eventType == updateEvent)
                    .map(e -> {
                        try {
                            return mapper.readValue(e.getData(), type);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    })
                    .filter(e -> !deleted.contains(e))
                    .filter(predicate)
                    .collect(Collectors.toList());

        } else {
            matches = getSource(after, before)
                    .filter(e -> e.eventType == createEvent)
                    .map(e -> {
                        try {
                            return mapper.readValue(e.getData(), type);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    })
                    .filter(predicate)
                    .collect(Collectors.toList());
        }

        entity = getLatest(matches, type, after, before);

        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new RuntimeException(new NoSuchElementException());
        }
    }

    public <T> List<T> getAll(Class<T> type, Function<T, String> identifier, EEventType createEvent, EEventType updateEvent, EEventType deleteEvent, Date after, Date before){
        final List<T> initial;

        if (updateEvent != null && deleteEvent != null) {
            final List<T> deleted = getDeleted(type, deleteEvent, after, before);

            initial = getSource(after, before)
                    .filter(e -> e.eventType == createEvent ||
                            e.eventType == updateEvent)
                    .map(e -> {
                        try {
                            return mapper.readValue(e.getData(), type);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    })
                    .filter(e -> !deleted.contains(e))
                    .collect(Collectors.toList());
        } else {
            initial = getSource(after, before)
                    .filter(e -> e.eventType == createEvent)
                    .map(e -> {
                        try {
                            return mapper.readValue(e.getData(), type);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    })
                    .collect(Collectors.toList());
        }

        return getAllLatest(initial, identifier, type, after, before);
    }

    private Stream<Event> getSource(Date after, Date before) {
        if (after == null && before == null) return repository.streamAll();
        if (after == null) return repository.streamAllByDateBefore(before);
        if (before != null) return repository.streamAllByDateBetween(after, before);
        throw new RuntimeException(new IllegalStateException());
    }

    private <T> List<T> getDeleted(Class<T> type, EEventType deleteEvent, Date after, Date before){
        return getSource(after, before)
                .filter(e -> e.eventType == deleteEvent)
                .map(e -> {
                    try {
                        return mapper.readValue(e.getData(), type);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(Collectors.toList());
    }

    private <T> Optional<T> getLatest(List<T> matches, Class<T> type, Date after, Date before) {
        List<String> matchesAsJson = new ArrayList<>();

        try {
            for (T item : matches) {
                matchesAsJson.add(mapper.writeValueAsString(item));
            }
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        return getSource(after, before)
                .filter(e -> matchesAsJson.contains(e.getData()))
                .sorted((o1, o2) -> {
                    if (o1.getDate().after(o2.getDate())) return -1;
                    if (o1.getDate().before(o2.getDate())) return 1;
                    return 0;
                })
                .map(e -> {
                    try {
                        return mapper.readValue(e.getData(), type);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .findFirst();
    }

    private <T> List<T> getAllLatest(List<T> initial, Function<T, String> identifier, Class<T> type, Date after, Date before) {
        if (identifier != null) {
            List<T> entities = new ArrayList<>();

            HashMap<String, List<T>> map = new HashMap<>();

            for (T item : initial) {

                if (!map.containsKey(identifier.apply(item))) {
                    ArrayList<T> list = new ArrayList<>();
                    list.add(item);

                    map.put(identifier.apply(item), list);
                } else {
                    map.get(identifier.apply(item)).add(item);
                }
            }

            for (Map.Entry<String, List<T>> entry : map.entrySet()) {
                Optional<T> result;

                List<String> matchesAsJson = new ArrayList<>();

                try {
                    for (T item : entry.getValue()) {
                        matchesAsJson.add(mapper.writeValueAsString(item));
                    }
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }

                result = getSource(after, before)
                        .filter(e -> matchesAsJson.contains(e.getData()))
                        .sorted(((o1, o2) -> {
                            if (o1.getDate().after(o2.getDate())) return -1;
                            if (o1.getDate().before(o2.getDate())) return 1;
                            return 0;
                        }))
                        .map(e -> {
                            try {
                                return mapper.readValue(e.getData(), type);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        })
                        .findFirst();

                result.ifPresent(entities::add);
            }

            return entities;
        } else {
            return initial;
        }
    }
}
