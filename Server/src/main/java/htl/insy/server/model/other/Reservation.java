package htl.insy.server.model.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

// I buy from someone
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @NotNull
    @Positive
    Integer amount;

    @NotNull
    Item item;

    @NotNull
    Supplier supplier;

    @NotNull
    User user;
}
