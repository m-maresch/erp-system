package htl.insy.server.model.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

// Someone buys from me
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @NotNull
    @Positive
    Integer amount;

    @NotNull
    Item item;

    @NotNull
    Customer customer;

    @NotNull
    User user;


}
