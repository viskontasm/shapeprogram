package com.viskontas.shapesprogram;

import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.impl.action.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
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
        //while(true) {
        //    System.out.print("Enter command(type 'help' for more information): ");
        //    acceptCommand();
        //}

        FileServiceImpl fileService = new FileServiceImpl();
        fileService.readFromFile(actionDecisionService);
    }

    private void acceptCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        actionDecisionService.decide(input);
    }

//TODO write tests
}