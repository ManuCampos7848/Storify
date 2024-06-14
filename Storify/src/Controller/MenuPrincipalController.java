package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuPrincipalController {

	// Atributos del FXML
	@FXML
	private Button btnInicioSesion;

	@FXML
	private Hyperlink hypAdmin;

	@FXML
	private Button btnRegistro;
	//_____________________________________________________________________________________________________________________________________


	/*
	 * Carga una nueva interfaz de usuario JavaFX desde un archivo FXML llamado Login.fxml utilizando un cargador de FXML (FXMLLoader) y lo guarda en un objeto Parent llamado root.
	 * Crea una nueva escena (Scene) a partir del root.
	 * Crea una nueva ventana (Stage) y la muestra en pantalla con el método show(). La ventana tiene un título, un ícono de aplicación, está configurada para ser modal 
	 * (bloqueando la interacción con otras ventanas hasta que se cierre), y no es redimensionable.
	 * Cierra la ventana actual (myStage) en la que se encuentra el botón (btnInicioSesion) que desencadenó el evento de acción.
	 */
	@FXML
	void iniciarSesion(ActionEvent event) {

		try {
			/*
			 * Ruta de la interfaz del login
			 */
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Login");
			/*
			 * Carga el logo del spotify que esta en la carpeta Resources y ya imagen LogoSpo.png
			 */
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			
			/*
			 * Cuando se presione el boton btnInicioSesion, lo que hace estas dos lineas de codigo es cerrar la interfaz que estaba
			 * anteriormente
			 */
			Stage myStage = (Stage) this.btnInicioSesion.getScene().getWindow();
			myStage.close();

		} catch (IOException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
	}
	//_____________________________________________________________________________________________________________________________________


	/*
	 * Carga una nueva interfaz de usuario JavaFX desde un archivo FXML llamado RegistroUsuarios.fxml utilizando un cargador de FXML (FXMLLoader) y lo guarda en un objeto Parent llamado root.
	 * Crea una nueva escena (Scene) a partir del root.
	 * Crea una nueva ventana (Stage) y la muestra en pantalla con el método show(). La ventana tiene un título, un ícono de aplicación, está configurada para ser modal (bloqueando la interacción con otras ventanas hasta que se cierre), y no es redimensionable.
	 * Cierra la ventana actual (myStage) en la que se encuentra el botón (btnRegistro) que desencadenó el evento de acción.
	 */
	@FXML
	void registrar(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RegistroUsuarios.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Registro Usuarios");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			Stage myStage = (Stage) this.btnRegistro.getScene().getWindow();
			myStage.close();

		} catch (IOException ex) {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
	}
	//_____________________________________________________________________________________________________________________________________


	/*
	 * Carga una nueva interfaz de usuario JavaFX desde un archivo FXML llamado LoginAdministrador.fxml utilizando un cargador de FXML (FXMLLoader) y lo guarda en un objeto Parent llamado root.
	 * Crea una nueva escena (Scene) a partir del root.
	 * Crea una nueva ventana (Stage) y la muestra en pantalla con el método show(). La ventana tiene un título, un ícono de aplicación, está configurada para ser modal (
	 * bloqueando la interacción con otras ventanas hasta que se cierre), y no es redimensionable.
	 * Cierra la ventana actual (myStage) en la que se encuentra el botón (btnRegistro) que desencadenó el evento de acción.
	 */
	@FXML
	void logearComoAdmin(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginAdministrador.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Login Administrador");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			Stage myStage = (Stage) this.btnRegistro.getScene().getWindow();
			myStage.close();

		} catch (IOException ex) {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
	}
	//_____________________________________________________________________________________________________________________________________

}
