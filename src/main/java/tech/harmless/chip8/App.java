package tech.harmless.chip8;

import com.google.gson.Gson;

/** Hello world! */
public class App {

    private byte[] memory = new byte[4096]; // Test

    public static void main(String[] args) {
        System.out.println("Hello World!");

        var a = Gson.class;
        System.out.println(a);
        System.out.println(a);
    }
}
