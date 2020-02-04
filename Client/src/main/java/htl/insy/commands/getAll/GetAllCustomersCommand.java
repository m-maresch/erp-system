package htl.insy.commands.getAll;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import org.json.JSONArray;

import java.io.IOException;

public class GetAllCustomersCommand implements ICommand {

    @Override
    public void execute() {
        HttpExtensions.getAll("/customer", r -> {
            try {
                JSONArray json = new JSONArray(r.body().string());
                for (int i = 0; i < json.length(); i++) {
                    System.out.println(json.getJSONObject(i).getString("name"));
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
