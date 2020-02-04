package htl.insy.commands.getAll;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class GetAllOrdersCommand implements ICommand {

    @Override
    public void execute() {
        HttpExtensions.getAll("/order", r -> {
            try {
                JSONArray json = new JSONArray(r.body().string());
                for (int i = 0; i < json.length(); i++) {
                    JSONObject item = new JSONObject(json.getJSONObject(i).getString("item"));
                    JSONObject customer = new JSONObject(json.getJSONObject(i).getString("customer"));
                    JSONObject user = new JSONObject(json.getJSONObject(i).getString("user"));

                    System.out.println(json.getJSONObject(i).getString("amount") + " " +
                            item.getString("name") + " " +
                            customer.getString("name") + " " +
                            user.getString("username"));
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
