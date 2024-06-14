package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.MetodosUsuario;
import Model.Usuario;
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

public class RegistroUsuarioController implements Initializable{

	// Atributos
	@FXML
	private Button btnRegistrar;

	@FXML
	private Button btnVolver;

	@FXML
	private PasswordField txtContrasenia;

	@FXML
	private TextField txtCorreo;

	@FXML
	private TextField txtUsuario;
	//_________________________________________________________________________________________________________________________________

	
	/*
	 * Este código registra a un nuevo usuario, realiza la serialización de un objeto y muestra una nueva ventana correspondiente al menú 
	 * del usuario registrado, cerrando la ventana actual. También maneja excepciones en caso de que ocurran problemas durante el proceso de 
	 * registro y carga del archivo de diseño.
	 */
	@FXML
	void registrarEvent(ActionEvent event) throws IOException {

		String username = this.txtUsuario.getText();
		String email = this.txtCorreo.getText();
		String contrasenia = this.txtContrasenia.getText();


		Usuario<Object> usuarioNuevo = new Usuario<Object>(username, email, contrasenia, null, new ArrayList<>(), null);

		if(!(MetodosUsuario.verificarCorreoYUsernameUsuario(usuarioNuevo))) {

			MetodosUsuario.crearUsuario(usuarioNuevo);
			MetodosUsuario.storify.setUsuario(usuarioNuevo);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Bienvenido!");
			alert.showAndWait();

			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MenuUsuario.fxml"));
				Parent root = loader.load();

				MenuUsuarioController controlador = loader.getController();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				controlador.init();
				stage.initModality(Modality.APPLICATION_MODAL); 
				stage.setTitle("Menu del Cliente");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				Stage myStage = (Stage) this.btnRegistrar.getScene().getWindow();
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
			alert.setContentText("Esta cuenta ya existe!");
			alert.showAndWait();
		}
	}
	//_________________________________________________________________________________________________________________________________

	
	/*
	 * Este código carga un nuevo archivo de diseño y muestra una nueva ventana correspondiente al menú principal, cerrando la ventana actual. 
	 * También maneja excepciones en caso de que ocurran problemas durante el proceso de carga del archivo de diseño.
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
	//_________________________________________________________________________________________________________________________________

	
	/*
	 * Este método se ejecuta durante la inicialización del controlador y se encarga de aplicar estilos CSS 
	 * a los campos de texto en la interfaz gráfica.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		txtUsuario.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

		txtContrasenia.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

		txtCorreo.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

	}
	//_________________________________________________________________________________________________________________________________
}