package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginUsuarioController implements Initializable, Serializable{

	// Atributos del FXML
	@FXML
	private Button btnLogear;

	@FXML
	private Button btnVolver;

	@FXML
	private TextField txtContrasenia;

	@FXML
	private TextField txtUsuario;
	//___________________________________________________________________________

	
	/*
	 * Este código corresponde a un controlador de eventos de la interfaz gráfica de usuario que se encarga de realizar el inicio de sesión de un usuario en la aplicación.
	 * 
	 * Cuando el usuario presiona el botón de "Logear", el código obtiene los valores ingresados en los campos de usuario y contraseña de la interfaz gráfica.
	 * A continuación, se crea un objeto Usuario con esos valores y se llama a la función "verificarCuenta" para comprobar si existe una cuenta de usuario con esos datos.}
	 * 
	 * Si la cuenta de usuario existe, se muestra un mensaje de confirmación y se llama a la vista del menú del usuario correspondiente, se cierra la ventana actual.
	 * Si la cuenta de usuario no existe, se muestra un mensaje de error indicando que la cuenta no fue encontrada.
	 */
	@FXML
	void logearEvent(ActionEvent event) {

		String usuario = this.txtUsuario.getText();
		String contrasenia = this.txtContrasenia.getText();

		Usuario<?> usuarioLogin = new Usuario<Object>(usuario, contrasenia);

		Usuario<?> usuarioPosibleLogeado = verificarCuenta(usuarioLogin);

		if(usuarioPosibleLogeado == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("No se encontro el usuario, asegurese de escrbir bien la contraseña y el username");
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Bienvenido!");
			alert.showAndWait();

			MetodosUsuario.storify.setUsuario(usuarioPosibleLogeado);

			
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
				Stage myStage = (Stage) this.btnLogear.getScene().getWindow();
				myStage.close();

			} catch (IOException ex) {

				alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}
		}

	}
	//___________________________________________________________________________


	/*
	 * El método "verificarCuenta" recibe un objeto "Usuario" que contiene la información de la cuenta que se está intentando 
	 * verificar. Este método busca en la base de datos si existe una cuenta con esa información y devuelve el objeto "Usuario" correspondiente si se
	 * encuentra alguna coincidencia. Si no se encuentra ninguna coincidencia, el método devuelve null.
	 */
	private Usuario<?> verificarCuenta(Usuario<?> usuarioLogin) {

		Usuario<?> usuarioLog = null;

		if(MetodosUsuario.verificarCuenta(usuarioLogin) != null){

			usuarioLog = MetodosUsuario.verificarCuenta(usuarioLogin);

			return usuarioLog;
		}

		return usuarioLog;
	}
	//___________________________________________________________________________

	
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
	//___________________________________________________________________________

	
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
	//___________________________________________________________________________
}
