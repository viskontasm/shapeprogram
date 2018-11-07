package com.viskontas.shapesprogram;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.validator.ShapeValidatorImpl;
import com.viskontas.shapesprogram.service.validator.ShapeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;


@SpringBootApplication
//@EnableRedisRepositories
public class ShapesProgramApplication  {

	@Autowired
	ShapeRepository shapeRepository;
	@Autowired
	ShapeValidatorImpl java8Validator;

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
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("sample-data.txt").getFile());
		try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {


			stream.map(line -> line.split(" "))
			.forEach(shapeValues -> {
				try {
					String shapeName = shapeValues[0];
					java8Validator.validate(shapeName);
					double[] points  = Arrays.stream(shapeValues)
						.skip(1)
						.mapToDouble(Double::parseDouble).toArray();
					shapeRepository.save(AvailableShapes.valueOf(
						shapeName.toUpperCase(Locale.ENGLISH)).createShape(shapeName, points));
					List<Shape> map = shapeRepository.findAll();
					System.out.println("test1");
				} catch (ShapeException e) {
					System.out.println(e.getMessage());
				}
			});


			System.out.println("testend" );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
