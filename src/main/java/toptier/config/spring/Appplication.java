package toptier.config.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import toptier.auth.AuthContext;
import toptier.config.firebase.FBInit;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = "toptier")
public class Appplication {

    public static void main(String[] args) {
        SpringApplication.run(Appplication.class, args);
        FBInit.init();
        AuthContext.init();
    }
}
