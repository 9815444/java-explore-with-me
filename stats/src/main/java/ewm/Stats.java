package ewm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Stats {

    public static void main(String[] args) throws Exception {
        new SpringApplication(Stats.class).run(args);
    }
}
