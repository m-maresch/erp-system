package htl.insy.commands.delete;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class DeleteUserCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getUser();

        HttpExtensions.delete("/user", json, r -> System.out.println("User deleted"));
    }
}
