import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.HPos;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.*;

public class HW1_Walker extends Application {
	@Override
	public void start(Stage primaryStage) {
		buildSearch(primaryStage);
		primaryStage.show();
	}
	
	public void buildSearch(Stage primaryStage) {
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(40,60,60,60));
		pane.setHgap(10);
		pane.setVgap(25);
		
		Label lblSearch = new Label("Search for Courses");
		lblSearch.setFont(Font.font("CALIBRI",
			FontWeight.BOLD,20));
		TextField txtInstructor  = new TextField();
		TextField txtCourse  = new TextField();
		TextField txtRoomNumber  = new TextField();
		Button btnSearch = new Button("Search");
		
		btnSearch.setOnAction( e-> {
			sendRequest(txtInstructor,txtCourse,txtRoomNumber);
		});
		
		pane.add(lblSearch,0,0,2,1);
		pane.add(new Label("Instructor Name"),0,1);
		pane.add(txtInstructor,1,1);
		pane.add(new Label("Course Name"),0,2);
		pane.add(txtCourse,1,2);
		pane.add(new Label("Room Number"),0,3);
		pane.add(txtRoomNumber,1,3);
		btnSearch.setMinWidth(100);
		pane.setHalignment(btnSearch,HPos.RIGHT);
		pane.add(btnSearch,1,4);
		
		Scene scene = new Scene(pane,375,350);
		primaryStage.setTitle("Course Lookup");
		primaryStage.setScene(scene);
	}
	
	public void sendRequest(TextField txtInstructor, TextField txtCourse, TextField txtRoomNumber) {
		String prefix = "CS";
		String term = "FA2018";
		String instructor = txtInstructor.getText();
		String course = txtCourse.getText();
		String roomNumber = txtRoomNumber.getText();
		
		try {
			String url = "https://api.svsu.edu/courses?prefix=" + prefix + "&instructor=" + instructor;
			//+ "&courseNumber=" + course + "&term=" + term + 
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			int responseCode = con.getResponseCode();
			System.out.println(responseCode);
			
			BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			} in .close();
			System.out.println(response.toString());
		}
		catch(IOException ex) {
			
		}		
	}
}