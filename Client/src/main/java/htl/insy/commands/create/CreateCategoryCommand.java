package htl.insy.commands.create;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class CreateCategoryCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getCategory();

        HttpExtensions.post("/category", json, r -> System.out.println("Category created"));
    }
}
