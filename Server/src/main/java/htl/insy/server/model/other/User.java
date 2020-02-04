package htl.insy.server.model.other;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotNull
    public String username;

    @NotNull
    public String email;

    @NotNull
    public String firstName;

    @NotNull
    public String lastName;

    @NotNull
    public String password;

    @NotNull
    public Date dateOfBirth;

    @NotNull
    public String location;

    @NotNull
    public Boolean canWrite;
}
