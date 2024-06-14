package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import Model.Admin;
import Model.MetodosAdmin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginAdministradorController implements Initializable{

	// Atributos del FXML
	@FXML
	private Button btnLogear;

	@FXML
	private Button btnVolver;

	@FXML
	private PasswordField txtContrasenia;

	@FXML
	private TextField txtUsuario;
	//____________________________________________________________________

	
	/*
	 * Este es un método que se ejecuta al hacer clic en un botón llamado "logearEvent". Cuando se ejecuta, 
	 * se obtienen los valores de los campos "txtUsuario" y "txtContrasenia" y se crea un objeto "Admin" con esos valores. 
	 * Luego, se llama a la función "verificarCuentaAdmin" para verificar si el objeto "Admin" coincide con uno existente en el sistema. Si coincide, 
	 * se muestra una alerta de información que dice "Bienvenido Admin" y se carga una nueva ventana ("MenuAdmin.fxml") utilizando el objeto "FXMLLoader" 
	 * y se muestra la ventana. Si no coincide, se muestra una alerta de información que dice "No se encontro al administrador".
	 */
	@FXML
	void logearEvent(ActionEvent event) {

		String usuario = this.txtUsuario.getText();
		String contrasenia = this.txtContrasenia.getText();

		Admin adminLogin = new Admin(usuario, contrasenia);

		if(verificarCuentaAdmin(adminLogin)){

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Bienvenido Admin");
			alert.showAndWait();

			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/MenuAdmin.fxml"));
				Parent root = loader.load();

				//    			MenuAdminController controlador = loader.getController();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				//    			controlador.init();
				stage.initModality(Modality.APPLICATION_MODAL); 
				stage.setTitle("Menu del Administrador");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				Stage myStage = (Stage) this.btnLogear.getScene().getWindow();
				myStage.close();

			} catch (IOException ex) {

				alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}

		}else {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("No se encontro al administrador.");
			alert.showAndWait();
		}

	}
	//____________________________________________________________________

	
	/*
	 * La función verificarCuentaAdmin recibe un objeto de tipo Admin y verifica si las 
	 * credenciales son válidas utilizando la interfaz IAdmin y su método verificarAdministrador. Si el resultado es verdadero, devuelve
	 * verdadero, de lo contrario, devuelve falso.
	 */
	private boolean verificarCuentaAdmin(Admin adminLogin) {

		if(MetodosAdmin.verificarAdministrador(adminLogin)) {
			return true;
		}
		return false;
	}
	//____________________________________________________________________

	
	/*
	 * Este código define el evento de clic en el botón "volver" de una ventana de la aplicación. 
	 * Al hacer clic en este botón, se carga la ventana del menú principal de la aplicación y se cierra la ventana actual. 
	 * La ventana del menú principal se carga a través de la carga de un archivo FXML, se configura la escena y se muestra en una nueva ventana. 
	 * Si ocurre un error durante este proceso, se muestra una alerta de error al usuario.
	 */
	@FXML
	void volverEvent(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MenuPrincipal.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Menu Principal");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			Stage myStage = (Stage) this.btnVolver.getScene().getWindow();
			myStage.close();

		} catch (IOException ex) {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}

	}
	//____________________________________________________________________

	
	/*
	 * Este código es el método "initialize" de un controlador de interfaz de usuario en JavaFX. 
	 * Se ejecuta automáticamente cuando se carga la vista asociada a este controlador. En este método, 
	 * se establecen algunos estilos CSS en los campos de texto "txtUsuario" y "txtContrasenia". 
	 * Estos estilos incluyen colores de texto y de fondo, así como bordes y esquinas redondeadas. En general, se 
	 * están definiendo estilos para hacer que los campos de texto tengan un aspecto consistente y atractivo en la aplicación.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtUsuario.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

		txtContrasenia.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");
	}
	//____________________________________________________________________

}