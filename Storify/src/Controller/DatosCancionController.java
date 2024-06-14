package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;

import Model.Cancion;
import Model.MetodosUsuario;
import Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DatosCancionController {

	// Atributos para saber de que canción y que usuario se esta hablando
	private Cancion cancion = MetodosUsuario.obtenerCancion();
	private Usuario<?> usuario = MetodosUsuario.obtenerCliente();
	//_______________________________________________________________________________________________________________________


	// Atributos
	@FXML
	private Button btnRegistrar;

	@FXML
	private SplitPane caratula;

	@FXML
	private Button btnAgregarLista;

	@FXML
	private Label labelAlmbum;

	@FXML
	private Label labelAnio;

	@FXML
	private Label labelCodigo;

	@FXML
	private Button btnVolver;

	@FXML
	private Label labelDuracion;

	@FXML
	private Label labelGenero;

	@FXML
	private Button btnAbrirYoutube;

	@FXML
	private Button btnRehacerInsercion;

	@FXML
	private ImageView imagen;

	@FXML
	private Label labelNombre;
	//_______________________________________________________________________________________________________________________


	/*
	 * Se carga el archivo FXML de la vista "ReproducirCancion.fxml" utilizando FXMLLoader.
	 * Se obtiene el controlador de la vista cargada utilizando loader.getController().
	 * Se crea una nueva escena con el nodo raíz de la vista cargada.
	 * Se crea una nueva ventana (Stage).
	 * Se inicializa el controlador llamando al método init() del controlador obtenido en el paso 2.
	 * Se configura la ventana como una ventana modal utilizando stage.initModality(Modality.APPLICATION_MODAL).
	 * Se establece el título de la ventana.
	 * Se agrega un icono a la ventana.
	 * Se establece la escena en la ventana.
	 * Se desactiva la capacidad de redimensionar la ventana.
	 * Se muestra la ventana.
	 * Se obtiene la referencia a la ventana actual y se cierra (myStage.close()).
	 */
	@FXML
	void registrarEvent(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ReproducirCancion.fxml"));
			Parent root = loader.load();

			ReproducirCancion controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			controlador.init();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Reproducir Canción");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			Stage myStage = (Stage) this.btnRegistrar.getScene().getWindow();
			myStage.close();

		} catch (IOException ex) {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
	}
	//_______________________________________________________________________________________________________________________


	/*
	 * Se carga el archivo FXML de la vista "ReproducirCancion.fxml" utilizando FXMLLoader.
	 * Se obtiene el controlador de la vista cargada utilizando loader.getController().
	 * Se crea una nueva escena con el nodo raíz de la vista cargada.
	 * Se crea una nueva ventana (Stage).
	 * Se inicializa el controlador llamando al método init() del controlador obtenido en el paso 2.
	 * Se configura la ventana como una ventana modal utilizando stage.initModality(Modality.APPLICATION_MODAL).
	 * Se establece el título de la ventana.
	 * Se agrega un icono a la ventana.
	 * Se establece la escena en la ventana.
	 * Se desactiva la capacidad de redimensionar la ventana.
	 * Se muestra la ventana.
	 * Se obtiene la referencia a la ventana actual y se cierra (myStage.close()).
	 */
	public void init() {

		labelNombre.setText(cancion.getNombre().toUpperCase());
		labelCodigo.setText(cancion.getCodigo().toUpperCase());
		labelAlmbum.setText(cancion.getAlbum().toUpperCase());
		labelAnio.setText(cancion.getAnio().toUpperCase());
		labelDuracion.setText(String.valueOf(cancion.getDuracion()).toUpperCase());
		labelGenero.setText(cancion.getGenero().toUpperCase());

		FileInputStream input;
		try {
			input = new FileInputStream(cancion.getCaratula());

			Image imagenInternaImage = new Image(input);

			imagen.setImage(imagenInternaImage);
		} catch (FileNotFoundException e) {
			System.out.println("Hay un error");
		}
	}
	//_______________________________________________________________________________________________________________________


	/*
	 * Se carga una vista de menú de usuario utilizando FXMLLoader.
	 * Se obtiene el controlador de la vista cargada mediante loader.getController().
	 * Se crea una nueva escena y una nueva ventana (Stage) para mostrar la vista del menú de usuario.
	 * Se inicializa el controlador mediante controlador.init().
	 * Se configura la ventana modal utilizando stage.initModality(Modality.APPLICATION_MODAL).
	 * Se establece el título y el icono de la ventana.
	 * Se establece la escena en la ventana y se configura la ventana como no redimensionable.
	 * Se muestra la ventana del menú de usuario.
	 * Se obtiene la ventana actual (Stage) y se cierra utilizando myStage.close().
	 * Si se produce una excepción durante la carga de la vista del menú de usuario, se muestra un cuadro 
	 * de diálogo de error con el mensaje de la excepción.
	 */
	@FXML
	void volverEvent(ActionEvent event) {

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

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
	}
	//_______________________________________________________________________________________________________________________


	/*
	 * Se carga el archivo FXML de la vista "ReproducirCancion.fxml" utilizando FXMLLoader.
	 * Se obtiene el controlador de la vista cargada utilizando loader.getController().
	 * Se crea una nueva escena con el nodo raíz de la vista cargada.
	 * Se crea una nueva ventana (Stage).
	 * Se inicializa el controlador llamando al método init() del controlador obtenido en el paso 2.
	 * Se configura la ventana como una ventana modal utilizando stage.initModality(Modality.APPLICATION_MODAL).
	 * Se establece el título de la ventana.
	 * Se agrega un icono a la ventana.
	 * Se establece la escena en la ventana.
	 * Se desactiva la capacidad de redimensionar la ventana.
	 * Se muestra la ventana.
	 * Se obtiene la referencia a la ventana actual y se cierra (myStage.close()).
	 */
	@FXML
	void agregarLista(ActionEvent event) {

		if(usuario == null) {
			System.out.println("usuario es null");
		}else if(cancion == null) {
			System.out.println("cancion es nul");
		}
		else {
			
			if(verificarCancionRepetida(cancion)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Atención");
				alert.setContentText("La cancion seleccionada ya existe en tu lista de canciones.");
				alert.showAndWait();
			}else {
				MetodosUsuario.agregarCancionALista(cancion, usuario);
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Atención");
				alert.setContentText("Se agrego una canción con exito.");
				alert.showAndWait();
			}
		}

	}
	private boolean verificarCancionRepetida(Cancion cancion2) {
		return MetodosUsuario.verificarCancionRepetida(cancion);
	}
	//_______________________________________________________________________________________________________________________


	/*
	 * Se llama al método abrirEnYoutube de la clase IUsuario y se le pasa la canción actual como parámetro.
	 */
	@FXML
	void abrirYoutube(ActionEvent event) {
		MetodosUsuario.abrirEnYoutube(cancion);
	}
	//_______________________________________________________________________________________________________________________
}