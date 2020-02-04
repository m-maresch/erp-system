package htl.insy.server.model.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @NotNull
    public String name;

    @NotNull
    public String location;
}
