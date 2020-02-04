package htl.insy;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Scanner;

public interface ICommand {

    Scanner SCANNER = new Scanner(System.in);

    void execute();
}
