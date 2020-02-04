package htl.insy.server.model.other;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @NotNull
    public String name;
}
