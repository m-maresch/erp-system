package htl.insy.commands.delete;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class DeleteItemCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getItem();

        HttpExtensions.delete("/item", json, r -> System.out.println("Item deleted"));
    }
}
