package htl.insy.commands.create;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

import javax.naming.spi.ObjectFactory;
import java.io.IOException;

public class CreateUserCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getUser();

        HttpExtensions.post("/user", json, r -> System.out.println("User created"));
    }
}
