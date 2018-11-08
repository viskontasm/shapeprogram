package com.viskontas.shapesprogram;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.service.ShapeService;
import com.viskontas.shapesprogram.service.validator.ShapeValidatorImpl;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


@SpringBootApplication
@CommonsLog
public class ShapesProgramApplication  {

	@Autowired
	ShapeValidatorImpl shapeValidator;
	@Autowired
	ShapeService shapeService;
	@Autowired
	AvailableShapes availableShapes;

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
			decideAction(lineStream);
            System.out.println("testend" );
        } catch (IOException e) {
			e.printStackTrace();
		}

    }

	private void decideAction(Stream<String> lineStream) {
		lineStream.map(line -> line.split(" "))
			.forEach(line -> {
				String shapeName = line[0];
				if (shapeValidator.isValidShapeName(shapeName)) {
					saveShape(line);

				} else if (shapeValidator.isValidLookUpCoordinates(2, line)) {
					lookUpAllShapes();
				} else {
					System.out.println("There is no such shape or not correct look up coordinates: " + line);
				}
			});

		//else {
			//throw new ShapeException("not valid input");
		//}
	}

	private void lookUpAllShapes() {
	}

	private void saveShape(String... shapeValues) {
		double[] shapeData = Arrays.stream(shapeValues)
				.skip(1)
				.mapToDouble(Double::parseDouble).toArray();
		Shape savedShape = shapeService.createOrUpdateShape(availableShapes.getShapeWithData(shapeValues[0], shapeData));
		System.out.println(savedShape.getShapeInformation());
	}
}
