import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Homework4_Server extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		TextArea ta = new TextArea();
		Scene scene = new Scene(new ScrollPane(ta), 450, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread( () -> {
			try {
				// Create a server socket
				ServerSocket serverSocket = new ServerSocket(8000);
				Platform.runLater(() ->
					ta.appendText("Scramble Game server started at: " 
          + new Date() + '\n'));
					
				while(true) {	
					// Listen for a connection request
					Socket socket = serverSocket.accept();
					
					// Get initial signal from client
						// load or save function
					new Thread(new HandleClient(socket)).start();
				}
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}).start();
	}
	
	class HandleClient implements Runnable {
		private Socket socket;
		
		public HandleClient(Socket socket) {
			this.socket = socket;
		}
		
		public void run() {
			try {
				DataInputStream inputFromClient = new DataInputStream(
					socket.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(
					socket.getOutputStream());
				int request = inputFromClient.readInt();
				if(request == 0) {
					outputToClient.writeInt(0);
				}
				else {
					outputToClient.writeInt(1);
				}
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
