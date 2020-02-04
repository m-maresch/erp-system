package htl.insy.commands.delete;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class DeleteCustomerCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getCustomer();

        HttpExtensions.delete("/customer", json, r -> System.out.println("Customer deleted"));
    }
}
