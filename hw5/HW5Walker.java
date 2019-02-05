import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button; 

public class HW5Walker extends Application { 
  @Override public void start(Stage primaryStage) { 
    // Create a scene and place a button in the scene 
    Scene scene = new Scene(new Button("OK"), 200, 250);
    primaryStage.setTitle("MyJavaFX"); // Set the stage title 
    primaryStage.setScene(scene); // Place the scene in the stage 
    primaryStage.show(); // Display the stage

    Stage stage = new Stage(); // Create a new stage 
    stage.setTitle("Second Stage"); // Set the stage title 
    stage.setScene(new Scene(new Button("New Stage"), 100, 100));       
     stage.show(); // Display the stage }
 
  }
}