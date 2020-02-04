package htl.insy.commands.create;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class CreateItemCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getItem();

        HttpExtensions.post("/item", json, r -> System.out.println("Item created"));
    }
}
