package htl.insy.commands.update;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class UpdateItemCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getItem();

        HttpExtensions.put("/item", json, r -> System.out.println("Item updated"));
    }
}
