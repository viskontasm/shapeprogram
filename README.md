build ant run:

    java -jar shapesprogram-0.0.1-SNAPSHOT.jar

**What is done:**<br />

**1-5** tasks are fulfilled.

**Extras:**

6)Think about implementing it in a way which would perform well even for a very large number shapes (e.g., tens of millions, but assuming it can still be held in the program memory).

   All shapes are saved by their type to their object (Donut, Triangle...) and these objects do have coordinates list. This helps saving memory, when not new object is created for every new shape.

    public class Shape {
        private long id;
        private String shapeName;
        private List<double[]> shapeData;
        ...
    }

7)Allow input from a file as well as the console. It should be possible, for example, to read a file with shape definitions and then to continue with an interactive session. Please provide a sample input file for testing.

    It is working by entering command ‘file’. Then program reads file ‘sample-data.txt located in resources folder.


8)Feel free to add additional shapes (e.g. square, rectangle, ellipsis) and operations (e.g. to delete a given shape). An advanced option could be to find all the shapes that overlap one that’s named by the user. 

    No additional shapes added, but delete operation is working.


9)Build file (ANT, Maven, Gradle, …) project.
 
	Project is built using gradle.


10)When calculating all figures that contains a specific point (x, y), use threading for parallelism.
 
	Parallel stream is used.

11)Dependency injection.
 
	Is used

12)Use any database to store the figures.
 
	In memory DB2 used.








