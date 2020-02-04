package htl.insy.commands.delete;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class DeleteCategoryCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getCategory();

        HttpExtensions.delete("/category", json, r -> System.out.println("Category deleted"));
    }
}
