package htl.insy;

import okhttp3.*;
import okio.Buffer;
import org.json.JSONObject;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Consumer;

public class HttpExtensions {

    private final static Scanner SCANNER = new Scanner(System.in);

    private final static OkHttpClient HTTP_CLIENT = new OkHttpClient();

    private final static String URL = "http://localhost:8082/wawi";

    private final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void get(String endpoint, String entityName, Consumer<Response> onSuccess) {
        tryAndExecuteOnSuccess(new Request.Builder()
                .url(URL + endpoint + "/" + entityName + "?requester=" + LoginCommand.userName)
                .build(), onSuccess);
    }

    public static void getAll(String endpoint, Consumer<Response> onSuccess) {
        System.out.println("After: (YYYY-MM-DD HH-MM-SS)");
        String after = SCANNER.nextLine();
        System.out.println("Before: (YYYY-MM-DD HH-MM-SS)");
        String before = SCANNER.nextLine();

        if (after.isEmpty()) {
            after = null;
        }
        if (before.isEmpty()) {
            before = null;
        }

        String fullUrl = URL + endpoint;

        if (before != null && after == null) {
            fullUrl += "?before=" + before;
        }

        if (after != null && before != null) {
            fullUrl += "?after=" + after + "&before=" + before;
        }

        tryAndExecuteOnSuccess(new Request.Builder()
                .url(fullUrl)
                .build(), onSuccess);
    }

    public static void post(String endpoint, JSONObject body, Consumer<Response> onSuccess) {
        String fullUrl = URL + endpoint + "?requester=" + LoginCommand.userName;

        if (LoginCommand.userName == null) {
            fullUrl = URL + endpoint;
        }

        tryAndExecuteOnSuccess(new Request.Builder()
                .url(fullUrl)
                .post(RequestBody.create(JSON, body.toString()))
                .build(), onSuccess);
    }

    public static void put(String endpoint, JSONObject body, Consumer<Response> onSuccess) {
        tryAndExecuteOnSuccess(new Request.Builder()
                .url(URL + endpoint + "?requester=" + LoginCommand.userName)
                .put(RequestBody.create(JSON, body.toString()))
                .build(), onSuccess);
    }

    public static void delete(String endpoint, JSONObject body, Consumer<Response> onSuccess) {
        tryAndExecuteOnSuccess(new Request.Builder()
                .url(URL + endpoint + "?requester=" + LoginCommand.userName)
                .delete(RequestBody.create(JSON, body.toString()))
                .build(), onSuccess);
    }

    private static void tryAndExecuteOnSuccess(Request request, Consumer<Response> onSuccess) {
        try {
            Response response = HTTP_CLIENT.newCall(request).execute();

            if (response.isSuccessful()) {
                onSuccess.accept(response);
            } else {
                final Buffer buffer = new Buffer();
                request.body().writeTo(buffer);

                System.out.println("Request URL: "+request.url());
                System.out.println("Request Body: "+buffer.readUtf8());
                System.out.println("Response Code: "+response.code());
                System.out.println("Response Body: "+response.body().string());
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
