package htl.insy.commands.create;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class CreateWarehouseEntryCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getWarehouseEntry();

        HttpExtensions.post("/warehouseentry", json, r -> System.out.println("Warehouse Entry created"));
    }
}
