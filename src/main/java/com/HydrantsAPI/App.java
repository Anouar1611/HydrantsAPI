package com.bookiply.interview.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    //This class intended to hide the Warning "Illegal reflective access" while launching the App
    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }

    public static void main(String[] args) {
        disableWarning();
        SpringApplication.run(App.class, args);
    }
}
