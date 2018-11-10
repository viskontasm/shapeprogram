package com.viskontas.shapesprogram;

import com.viskontas.shapesprogram.service.ActionDecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    ActionDecisionService actionDecisionService;

    @Autowired
    public ApplicationStartup(ActionDecisionService actionDecisionService) {
        this.actionDecisionService = actionDecisionService;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//TODO wirte tests
}