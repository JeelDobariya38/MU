package com.example.complaint;

import java.awt.Desktop;
import java.net.URI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ComplaintManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                ComplaintManagementApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowser() {
        String url = "http://localhost:8080";

        System.out.println("========================================");
        System.out.println("Complaint Management System is running!");
        System.out.println("Open: " + url);
        System.out.println("========================================");

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            }
        } catch (Exception e) {
            System.out.println(
                    "Please open your browser manually: " + url);
        }
    }
}