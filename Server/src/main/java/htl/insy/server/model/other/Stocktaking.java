package htl.insy.server.model.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stocktaking {

    @NotNull
    @Positive
    Integer amount;

    @NotNull
    Item item;

    @NotNull
    User user;
}
