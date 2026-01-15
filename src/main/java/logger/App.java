package logger;

import logger.pojo.Log;
import logger.service.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication(scanBasePackages = "logger")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("Logging System Service Started...");
        System.out.println("Logger Instance: " + Logger.getInstance());
    }

}
