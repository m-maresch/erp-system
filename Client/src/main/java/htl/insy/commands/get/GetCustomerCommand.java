package htl.insy.commands.get;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import org.json.JSONObject;

import java.io.IOException;

public class GetCustomerCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("Name: ");
        HttpExtensions.get("/customer/", SCANNER.nextLine(), r -> {
            try {
                JSONObject json = new JSONObject(r.body().string());
                System.out.println("Name: "+json.getString("name"));
                System.out.println("Location: "+json.getString("location"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
