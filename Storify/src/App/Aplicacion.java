package App;

import java.io.IOException;

import Model.MetodosAdmin;
import Model.MetodosUsuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Aplicacion extends Application{

	/*
	 * Metodo que permite cargar la interfaz que da inicio a la aplicacion
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			//Cargo la vista, la direccion de la interfaz del menu principal
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/View/MenuPrincipal.fxml"));

			// Cargo la ventana
			Pane ventana = (Pane) loader.load();

			// Cargo el scene
			Scene scene = new Scene(ventana);

			// Seteo la scene y la muestro
			primaryStage.setScene(scene);
			primaryStage.show();
			/*
			 * primaryStage.setResizable(false) hace que no se pueda engrandecer la pantalla
			 */
			primaryStage.setResizable(false);
			primaryStage.setTitle("Menu Principal");

			/*
			 * Carga la imagen del logo spotify
			 */
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	//__________________________________________________________________________________________________


	
	/*
	 * Metodo Main junto a iniciar el metodo init que quema datos de canciones, artistas y usuarios
	 */
	public static void main(String[] args) {	
		MetodosAdmin.init();
		MetodosUsuario.init();
		launch(args);

	}
	//____________________________
}
