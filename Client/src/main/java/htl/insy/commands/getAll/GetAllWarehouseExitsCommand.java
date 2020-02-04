package htl.insy.commands.getAll;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class GetAllWarehouseExitsCommand implements ICommand {

    @Override
    public void execute() {
        HttpExtensions.getAll("/warehouseexit", r -> {
            try {
                JSONArray json = new JSONArray(r.body().string());
                for (int i = 0; i < json.length(); i++) {
                    JSONObject item = new JSONObject(json.getJSONObject(i).getString("item"));

                    System.out.println(json.getJSONObject(i).getString("amount") + " " +
                            item.getString("name"));
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
