package htl.insy.commands.create;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class CreateCustomerCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getCustomer();

        HttpExtensions.post("/customer", json, r -> System.out.println("Customer created"));
    }
}
