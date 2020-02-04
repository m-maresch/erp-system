package htl.insy.commands.create;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class CreateStocktakingCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getStocktaking();

        HttpExtensions.post("/stocktaking", json, r -> System.out.println("Stocktaking created"));
    }
}
