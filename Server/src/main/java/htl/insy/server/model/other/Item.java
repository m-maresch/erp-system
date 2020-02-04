package htl.insy.server.model.other;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @NotNull
    public Category category;

    @NotNull
    public String name;
}
