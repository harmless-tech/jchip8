package tech.harmless.chip8;

import com.google.gson.Gson;

/** Hello world! */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Class<Gson> c = Gson.class;
        var a = c;
        System.out.println(a);

        if (true) {
            System.out.println(a);
        } else {
            System.out.println(a);
        }
    }
}
