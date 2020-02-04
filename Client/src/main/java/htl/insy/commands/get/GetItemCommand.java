package htl.insy.commands.get;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import org.json.JSONObject;

import java.io.IOException;

public class GetItemCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("Name: ");
        HttpExtensions.get("/item/", SCANNER.nextLine(), r -> {
            try {
                JSONObject json = new JSONObject(r.body().string());
                System.out.println("Name: "+json.getString("name"));
                System.out.println("Category: "+ new JSONObject(json.getString("category")).getString("name"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
