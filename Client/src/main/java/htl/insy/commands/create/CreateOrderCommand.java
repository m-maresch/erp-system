package htl.insy.commands.create;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class CreateOrderCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getOrder();

        HttpExtensions.post("/order", json, r -> System.out.println("Order created"));
    }
}
