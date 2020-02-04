package htl.insy.commands.create;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class CreateManualEntryCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getManualEntry();

        HttpExtensions.post("/manualentry", json, r -> System.out.println("Manual Entry created"));
    }
}
