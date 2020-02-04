package htl.insy.server.model;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

@MappedSuperclass
public abstract class AVersionEntity implements Serializable {

    @Getter
    @Version
    private Long version;
}
