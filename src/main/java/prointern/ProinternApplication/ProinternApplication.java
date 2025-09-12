package prointern.ProinternApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "prointern.ProinternApplication")
public class ProinternApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProinternApplication.class, args);
        System.out.println("🚀 ProIntern Backend is running...");
    }
}