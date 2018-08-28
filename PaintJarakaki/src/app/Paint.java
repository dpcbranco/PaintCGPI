package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Paint extends Application{
	
	Parent quadro;
	Scene cena;

	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		quadro = FXMLLoader.load(getClass().getResource("pj_canvas.fxml"));
		cena = new Scene(quadro);
		
		primaryStage.setTitle("Paint");
		primaryStage.setScene(cena);
		primaryStage.show();
	}

}
