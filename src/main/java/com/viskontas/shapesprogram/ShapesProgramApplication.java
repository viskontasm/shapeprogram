package com.viskontas.shapesprogram;

import com.viskontas.shapesprogram.service.ActionDecisionService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
@CommonsLog
public class ShapesProgramApplication  {

    @Autowired
    ActionDecisionService actionDecisionService;

    public static void main(String... args) {
        SpringApplication.run(ShapesProgramApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws IOException {
        /*while(true) {
            System.out.println("Enter model(type 'help' for more information)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }
            if (input.equals("help")) {
                System.out.println("help!");0
            } else {
                System.out.println("Result: " + firstInputValidation(input));
            }
        }*/
        readFromFile();
    }

    private void readFromFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sample-data.txt").getFile());
        try (Stream<String> lineStream = Files.lines(Paths.get(file.getPath()))) {
            actionDecisionService.decide(lineStream);
            System.out.println("THE END" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
