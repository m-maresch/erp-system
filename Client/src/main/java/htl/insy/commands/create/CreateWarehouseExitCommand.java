package htl.insy.commands.create;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class CreateWarehouseExitCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getWarehouseExit();

        HttpExtensions.post("/warehouseexit", json, r -> System.out.println("Warehouse Exit created"));
    }
}
