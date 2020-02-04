package htl.insy.commands.create;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class CreateSupplierCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getSupplier();

        HttpExtensions.post("/supplier", json, r -> System.out.println("Supplier created"));
    }
}
