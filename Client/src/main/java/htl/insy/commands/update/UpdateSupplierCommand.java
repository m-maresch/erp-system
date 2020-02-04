package htl.insy.commands.update;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class UpdateSupplierCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getSupplier();

        HttpExtensions.put("/supplier", json, r -> System.out.println("Supplier updated"));
    }
}
