package ru.practicum.explorewithme;

//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import ru.practicum.explorewithme.configuration.LocalDateConverter;
//import ru.practicum.explorewithme.configuration.LocalDateTimeConverter;

@SpringBootApplication
public class Stats /*implements CommandLineRunner*/ {

//    @Override
//    public void run(String... arg0) throws Exception {
//        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
//            throw new ExitException();
//        }
//    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Stats.class).run(args);
    }
//
//    @Configuration
//    static class CustomDateConfig extends WebMvcConfigurerAdapter {
//        @Override
//        public void addFormatters(FormatterRegistry registry) {
//            registry.addConverter(new LocalDateConverter("yyyy-MM-dd"));
//            registry.addConverter(new LocalDateTimeConverter("yyyy-MM-dd'T'HH:mm:ss.SSS"));
//        }
//    }
//
//    class ExitException extends RuntimeException implements ExitCodeGenerator {
//        private static final long serialVersionUID = 1L;
//
//        @Override
//        public int getExitCode() {
//            return 10;
//        }
//
//    }
}
