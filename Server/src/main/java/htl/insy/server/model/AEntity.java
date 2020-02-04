package htl.insy.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class AEntity extends AVersionEntity{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Transient
    private Long uuid;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AEntity aEntity = (AEntity) o;

        if (id == null){
            return getUUId().equals(aEntity.getUUId());
        }

        return Objects.equals(id, aEntity.id);
    }

    private Long getUUId(){
        return (uuid == null)? uuid = UUID.randomUUID().getLeastSignificantBits(): uuid;
    }

    @Override
    public final int hashCode() { return (id == null)? getUUId().hashCode() : id.hashCode();
    }
}
