package htl.insy;

import org.json.JSONObject;

import java.util.Scanner;

public class JSONObjectFactory {

    final static Scanner SCANNER = new Scanner(System.in);

    public static JSONObject getUser() {
        JSONObject json = new JSONObject();

        System.out.println("Username: ");
        json.put("username", SCANNER.nextLine());
        System.out.println("Email: ");
        json.put("email", SCANNER.nextLine());
        System.out.println("FirstName: ");
        json.put("firstName", SCANNER.nextLine());
        System.out.println("LastName: ");
        json.put("lastName", SCANNER.nextLine());
        System.out.println("Password: ");
        json.put("password", SCANNER.nextLine());
        System.out.println("Date of birth: (YYYY-MM-DD)");
        json.put("dateOfBirth", SCANNER.nextLine());
        System.out.println("Location: ");
        json.put("location", SCANNER.nextLine());
        System.out.println("Can write: (true/false)");
        json.put("canWrite", SCANNER.nextLine());

        return json;
    }

    public static JSONObject getCustomer() {
        JSONObject json = new JSONObject();

        System.out.println("Name: ");
        json.put("name", SCANNER.nextLine());
        System.out.println("Location: ");
        json.put("location", SCANNER.nextLine());

        return json;
    }

    public static JSONObject getSupplier() {
        JSONObject json = new JSONObject();

        System.out.println("Name: ");
        json.put("name", SCANNER.nextLine());
        System.out.println("Location: ");
        json.put("location", SCANNER.nextLine());

        return json;
    }

    public static JSONObject getCategory() {
        JSONObject json = new JSONObject();

        System.out.println("Name: ");
        json.put("name", SCANNER.nextLine());

        return json;
    }

    public static JSONObject getItem() {
        JSONObject json = new JSONObject();

        System.out.println("Category: ");
        json.put("category", getCategory());
        System.out.println("Name: ");
        json.put("name", SCANNER.nextLine());

        return json;
    }

    public static JSONObject getOrder() {
        JSONObject json = new JSONObject();

        System.out.println("Amount: ");
        json.put("amount", SCANNER.nextLine());
        System.out.println("Item: ");
        json.put("item", getItem());
        System.out.println("Customer: ");
        json.put("customer", getCustomer());
        System.out.println("User: ");
        json.put("user", getUser());

        return json;
    }

    public static JSONObject getReservation() {
        JSONObject json = new JSONObject();

        System.out.println("Amount: ");
        json.put("amount", SCANNER.nextLine());
        System.out.println("Item: ");
        json.put("item", getItem());
        System.out.println("Supplier: ");
        json.put("supplier", getSupplier());
        System.out.println("User: ");
        json.put("user", getUser());

        return json;
    }

    public static JSONObject getStocktaking() {
        JSONObject json = new JSONObject();

        System.out.println("Amount: ");
        json.put("amount", SCANNER.nextLine());
        System.out.println("Item: ");
        json.put("item", getItem());
        System.out.println("User: ");
        json.put("user", getUser());

        return json;
    }

    public static JSONObject getManualEntry() {
        JSONObject json = new JSONObject();

        System.out.println("Amount: ");
        json.put("amount", SCANNER.nextLine());
        System.out.println("Item: ");
        json.put("item", getItem());
        System.out.println("User: ");
        json.put("user", getUser());
        System.out.println("Description: ");
        json.put("description", SCANNER.nextLine());

        return json;
    }

    public static JSONObject getWarehouseEntry() {
        JSONObject json = new JSONObject();

        System.out.println("Amount: ");
        json.put("amount", SCANNER.nextLine());
        System.out.println("Item: ");
        json.put("item", getItem());

        return json;
    }

    public static JSONObject getWarehouseExit() {
        JSONObject json = new JSONObject();

        System.out.println("Amount: ");
        json.put("amount", SCANNER.nextLine());
        System.out.println("Item: ");
        json.put("item", getItem());

        return json;
    }
}
