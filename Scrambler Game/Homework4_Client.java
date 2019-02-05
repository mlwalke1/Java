//Homework 3: the Scrambler 2.0 with GUI
//Course: CIS357
//Due date: 6/13/18
//Name: Mathew Walker
//Instructor: Il-Hyung Cho
//Program description: 
//  This program implements javafx GUI. It begins with asking for the player
//  to enter their name in a text field. If the player clicks the no button the
//  game will end. If the player clicks the Yes button, the game will proceed to
//  a scramble word window. The play will push the "get scrambled word" button and
//  a scrambled word will appear in the the text field. The player types their guess
//  into a text field and presses enter. If the play is wrong, they get two more chances.
//  If the player is right, a window will congratulate the player and ask if they want to 
//  play again. If the player presses yes, they can play another round. If they press no, the
//  results of the game will be shown, and the primary window will show. A new player can play a game
//  or if the player chooses no, an all time record window will pop up. When the player exits the final
//  window the application will exit.

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class Homework4_Client extends Application {
	Scramble theScrambler;
	Player newPlayer;
	int guesses;
	
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(11, 12 ,13, 14));
		pane.setHgap(5);
		pane.setVgap(5);
		
		Button yesButton1 = new Button("Yes");
		Button noButton1 = new Button("No");
		TextField enterPlayerName = new TextField();
		pane.add(yesButton1,0,4);
		pane.add(noButton1,1,4);
		pane.add(new Label("User Name: "),0,0);
		pane.add(enterPlayerName,1,0); 
		pane.add(new Label("Start New Game?"),0,3); 
		yesButton1.setOnAction( e-> {
			newGame(enterPlayerName,primaryStage);
		});
		enterPlayerName.setOnAction( e-> {
			newGame(enterPlayerName,primaryStage);
		});
		noButton1.setOnAction( e-> {
			ArrayList<Player> allTimeRecords = new ArrayList<>();
			allTimeRecords = loadResults();
			primaryStage.hide();
		});
		
		Scene scene = new Scene(pane,300,200);
		primaryStage.setTitle("Homework 3");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * Creates and shows the second window of the program. In this window the player
	 * can press the scramble button to get a scrambled word to appear in the text field.
	 * The player enters their guess in the second text field and presses enter to submit their
	 * guess. This will call the method guessButtonPushed().
	 *
	 * @param primaryStage this method passes the primaryStage to the guessButtonPushed method.
	 */
	private void loadScrambleWindow(Stage primaryStage) {
		String currentWord = theScrambler.getRealWord();
		guesses = 3;
		if(currentWord != null) {
			Stage secondStage = new Stage();
			GridPane pane2 = new GridPane();
			TextField scrambleField = new TextField();
			TextField guessWordField = new TextField();
			Button getScrambleButton = new Button("Get a scrambled word: ");
			
			pane2.setPadding(new Insets(11, 12 ,13, 14));
			pane2.setHgap(5);
			pane2.setVgap(5);
			pane2.add(getScrambleButton,0,0,2,1);
			pane2.add(scrambleField,2,0);
			pane2.add(new Label("Your guess"),1,1);
			pane2.add(guessWordField,2,1);
			pane2.add(new Label("(Enter your guess and hit Return)"),1,2,2,1);
			
			getScrambleButton.setOnAction( e-> {
				scrambleField.setText(theScrambler.getScrambledWord());
				scrambleField.setEditable(false);
			});
			guessWordField.setOnAction( e-> {
				if(guessWordField.getText().isEmpty()) {
					Alert emptyGuessAlert = new Alert(AlertType.WARNING, "Guess is empty. Please enter a guess!");
					emptyGuessAlert.showAndWait();
				}
				else if(scrambleField.getText().isEmpty()) {
					Alert emptyScrambleAlert = new Alert(AlertType.WARNING, "Scramble is empty. Please press scramble button!");
					emptyScrambleAlert.showAndWait();
				}
				else {
				guessButtonPushed(currentWord, guessWordField, secondStage, primaryStage);
				}
			});
			
			Scene scene2 = new Scene(pane2,350,200);
			secondStage.setScene(scene2);
			secondStage.show();
		}
		else {
			Alert noWordsRemainingAlert = new Alert(AlertType.WARNING, "No words remaining. Thanks for playing!");
			noWordsRemainingAlert.showAndWait();
			primaryStage.show();
		}
	}
	
	/**
	 * Handles the event when the user submits their guess for the scrambled word. If the guess
	 * is correct it increments the wins for the players results and alerts the player that they have
	 * won the round. It then calls continueToPlayWindow(). If the guess is wrong it will alert the player
	 * and inform them of how many guesses are remaining. If there are no guesses remaining it will alert the
	 * player and call continueToPlayWindow().
	 *
	 * @param currentWord takes the current word to compare it to the guessed word
	 * @param guessWordField the field containing the guess to get the guess
	 * @param secondStage takes the second stage so that it can hide the window before moving on
	 * @param primaryStage takes the primary stage to pass to the continueToPlayWindow
	 */
	private void guessButtonPushed(String currentWord, TextField guessWordField, Stage secondStage, Stage primaryStage) {
		guesses--;
		newPlayer.guess();
		String guessedWord = guessWordField.getText();
		if(guessedWord.equalsIgnoreCase(currentWord)) {
			newPlayer.guess();
			newPlayer.win();
			Alert alert = new Alert(AlertType.INFORMATION, "GOOD JOB! You got it!");
			alert.showAndWait();
			continueToPlayWindow(primaryStage);
			secondStage.hide();
		}
		else {
			if(guesses != 0) {
				guessWordField.clear();
				Alert alert = new Alert(AlertType.INFORMATION, "Wrong answer! Guesses remaining: " 
					+ guesses);
				alert.showAndWait();
			}
			else {
				newPlayer.lose();
				Alert alert = new Alert(AlertType.INFORMATION, "Wrong Answer! Round Over!");
				alert.showAndWait();
				continueToPlayWindow(primaryStage);
				secondStage.hide();
			}
		}
	}
	
	/**
	 * Simply asks the player if they wish to continue playing. If the answer is yes
	 * it will go back to loadScrambleWindow for another round. If the answer is no 
	 * it will call displayResultsWindow to display the results of the game.
	 *
	 * @param primaryStage to pass the primary stage or to show it if no is selected
	 */
	private void continueToPlayWindow(Stage primaryStage) {
		Stage thirdStage = new Stage();
		GridPane pane3 = new GridPane();
		Button yesButton2 = new Button("Yes");
		Button noButton2 = new Button("No");
		
		pane3.setPadding(new Insets(11, 12 ,13, 14));
		pane3.setHgap(20);
		pane3.setVgap(15);
		pane3.add(new Label("Continue to play?"),0,0,2,1);
		pane3.add(yesButton2,0,1);
		pane3.add(noButton2,1,1);
		
		yesButton2.setOnAction( e-> {
			loadScrambleWindow(primaryStage);
			thirdStage.hide();
		});
		
		noButton2.setOnAction( e-> {
			primaryStage.show();
			thirdStage.hide();
			String type = "current";
			saveResults();
			displayResultsWindow(type);
			
		});
		
		Scene scene3 = new Scene(pane3,200,150);
		thirdStage.setScene(scene3);
		thirdStage.show();
	}
	
	/**
	 * Creates and displays a results window based on the paramater of type. If the type 
	 * is calling for the current game then it will simple display one result of the previous game.
	 * If the type is calling for the all time results it will display the all time results of the game,
	 * that are stored in a text file.
	 *
	 * @param type String containing a word signifying the type of record desired.
	 */
	private void displayResultsWindow(String type) {
		Stage resultStage = new Stage();
		TableView<Player> table = new TableView<Player>();
		
		TableColumn<Player,String> nameColumn = new TableColumn<Player,String>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
		TableColumn<Player,Integer> roundsColumn = new TableColumn<Player,Integer>("Rounds");
		roundsColumn.setCellValueFactory(new PropertyValueFactory<Player,Integer>("rounds"));
		TableColumn<Player,Integer> winsColumn = new TableColumn<Player,Integer>("Wins");
		winsColumn.setCellValueFactory(new PropertyValueFactory<Player,Integer>("wins"));
		TableColumn<Player,Integer> lossesColumn = new TableColumn<Player,Integer>("Losses");
		lossesColumn.setCellValueFactory(new PropertyValueFactory<Player,Integer>("losses"));
		TableColumn<Player,Integer> guessesColumn = new TableColumn<Player,Integer>("Guesses");
		guessesColumn.setCellValueFactory(new PropertyValueFactory<Player,Integer>("guesses"));
		table.getColumns().addAll(nameColumn,roundsColumn,winsColumn,lossesColumn,guessesColumn);
		
		if(type.equals("alltime")) {
			ArrayList<Player> playerList = new ArrayList<>();
			// playerList = loadResults();
			
			for(int i = 0; i < playerList.size(); i++) {
				table.getItems().add(playerList.get(i));
			}
			resultStage.setTitle("All time records");
			Scene scene4 = new Scene(table,400,500);
			resultStage.setScene(scene4);
		}
	  else {
			table.getItems().add(newPlayer);
			resultStage.setTitle("result");
			Scene scene4 = new Scene(table,400,200);
			resultStage.setScene(scene4);
		}
		
		resultStage.show();
	}
	
	/**
	 * Begins a new game for the player. Initializes the results object and the scrambled words object.
	 * Basically is an event handling function for the yes button on the primary stage. If the name is empty
	 * it will alert the user, if not it will initialize members of the class and call loadScrambleWindow().
	 *
	 * @param enterPlayerName the field with the users name so it can retrieve the name
	 * @param primaryStage so it can hide the stage and pass it along to be used later
	 */
	private void newGame(TextField enterPlayerName, Stage primaryStage) {
		String playerName = enterPlayerName.getText(); 
		if(playerName.isEmpty() || playerName == null){
			Alert emptyNameAlert = new Alert(AlertType.WARNING, "Name is empty. Please enter a name!");
			emptyNameAlert.showAndWait();
		}
		else {
			newPlayer = new Player(playerName);
		  theScrambler = new Scramble("scramwords.txt");
			loadScrambleWindow(primaryStage);
			enterPlayerName.clear();
			primaryStage.hide();
		}
	}
	
		/**
	 * 
	 */
	public void saveResults() {
		try {
			//connect to Server
			Socket socket = new Socket("localhost",8000);
			DataInputStream fromServer = new DataInputStream(socket.getInputStream());
			DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
			
			//Send save request
			toServer.writeInt(0);
			toServer.flush();
			
			int reply = fromServer.readInt();
			System.out.println(reply);
			//Send result object
		}
		catch(IOException ex) {
			System.out.println("IOException. ");
		}
	}
	
	/**
	 * 
	 */
	private ArrayList<Player> loadResults() {
		ArrayList<Player> allTimeRecords = new ArrayList<>();
		try {
			//connect to Server
			Socket socket = new Socket("localhost",8000);
			DataInputStream fromServer = new DataInputStream(socket.getInputStream());
			DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
			
			//Send save request
			toServer.writeInt(1);
			toServer.flush();
			
			int reply = fromServer.readInt();
			System.out.println(reply);
			//Send result object
		}
		catch(IOException ex) {
			System.out.println("IOException. ");
		}
		return allTimeRecords;
 }
 
}

