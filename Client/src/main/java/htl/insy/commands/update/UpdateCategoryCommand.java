package htl.insy.commands.update;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class UpdateCategoryCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getCategory();

        HttpExtensions.put("/category", json, r -> System.out.println("Category updated"));
    }
}
