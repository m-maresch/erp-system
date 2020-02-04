package htl.insy.server.model.event;

import htl.insy.server.model.AEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EVENTS")
public class Event extends AEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OCCURRENCE_DATE", nullable = false)
    public Date date;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "TYPE", nullable = false)
    public EEventType eventType;

    @NotNull
    @Column(name = "DATA", nullable = false)
    public String data;

    @NotNull
    @Column(name = "REQUESTER", nullable = false)
    public String requester;
}
