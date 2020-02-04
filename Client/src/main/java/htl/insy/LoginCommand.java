package htl.insy;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class LoginCommand implements ICommand {

    public static String userName;

    @Override
    public void execute() {
        JSONObject json = new JSONObject();

        System.out.println("Username: ");
        json.put("username", SCANNER.nextLine());
        System.out.println("Password: ");
        json.put("password", SCANNER.nextLine());

        HttpExtensions.post("/user/login", json, r -> {
            try {
                LoginCommand.userName = new JSONObject(r.body().string()).getString("username");
                System.out.println("Logged in");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }});
    }
}
