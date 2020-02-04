package htl.insy.commands.delete;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class DeleteSupplierCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getSupplier();

        HttpExtensions.delete("/supplier", json, r -> System.out.println("Supplier deleted"));
    }
}
