package htl.insy.commands.get;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import org.json.JSONObject;

import java.io.IOException;

public class GetUserCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("Username: ");
        HttpExtensions.get("/user/", SCANNER.nextLine(), r -> {
            try {
                JSONObject json = new JSONObject(r.body().string());
                System.out.println("Username: "+json.getString("username"));
                System.out.println("Email: "+json.getString("email"));
                System.out.println("FirstName: "+json.getString("firstName"));
                System.out.println("LastName: "+json.getString("lastName"));
                System.out.println("Password: "+json.getString("password"));
                System.out.println("Date of birth: "+json.getString("dateOfBirth").substring(0, 10));
                System.out.println("Location: "+json.getString("location"));
                System.out.println("Can write: "+json.get("canWrite"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
