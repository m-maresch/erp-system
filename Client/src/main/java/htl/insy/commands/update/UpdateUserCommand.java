package htl.insy.commands.update;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

import javax.naming.spi.ObjectFactory;

public class UpdateUserCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getUser();

        HttpExtensions.put("/user", json, r -> System.out.println("User updated"));
    }
}
