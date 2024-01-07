package hotelgui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import java.awt.*;

@SpringBootApplication
@ComponentScan
public class Main {

    @Autowired
    private EmailService emailService;

    private LoginGUI loginGui;  // Declare loginGui as an instance variable

    public static void main(String[] args) {
        // Start the Spring Boot application context
        SpringApplication.run(Main.class);

        // Execute Swing GUI setup in the EventQueue
        EventQueue.invokeLater(() -> {
            try {
                Main main = new Main();  // Create an instance of Main
                main.loginGui = new LoginGUI();  // Assign the LoginGUI instance to the instance variable
                // Register a callback for login event
                main.loginGui.addLoginListener(email -> {
                    HotelGUI appFrame = new HotelGUI("http://localhost:8080/hotel_rooms");
                    appFrame.setVisible(true);
                });
                main.loginGui.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @EventListener(org.springframework.boot.context.event.ApplicationReadyEvent.class)
    public void onApplicationReady() {
        // Logic that requires EmailService
        System.out.println("Application is ready, sending email...");

        // Example: Sending an email
        if (loginGui != null) {
            emailService.sendEmail(
                    loginGui.getEmail(),
                    "Hello Booboo",
                    "This is a test email from me. Dw about it.\n From me"
            );
        } else {
            System.out.println("LoginGUI instance is null. Cannot send email.");
        }

        System.out.println("Email sent!");
    }
}

