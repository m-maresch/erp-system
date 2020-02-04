package htl.insy.commands.update;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class UpdateCustomerCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getCustomer();

        HttpExtensions.put("/customer", json, r -> System.out.println("Customer updated"));
    }
}
