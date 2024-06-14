package Controller2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import Controller.ReproducirCancionAdmin;
import Model.Cancion;
import Model.MetodosAdmin;
import Model.MetodosUsuario;
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

public class DatosCancionAdmin {

	// Atributo de tipo cancion para saber de que canción es de la que se esta hablando
	private Cancion cancion = MetodosAdmin.obtenerCancionAdmin();
	//___________________________________________________________________________________________________________________

	// Atributos del fxml
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
	//___________________________________________________________________________________________________________________



	/*
	 * Configurar las etiquetas (labelNombre, labelCodigo, labelAlmbum, labelAnio, labelDuracion, labelGenero) con los valores de los atributos 
	 * de la canción. Parece que se espera que la canción sea un objeto con propiedades como 
	 * nombre, codigo, album, anio, duracion y genero, y que estos valores se muestren en las etiquetas en letras mayúsculas.
	 * 
	 * Cargar una imagen de carátula de la canción utilizando cancion.getCaratula(), que parece ser una ruta de archivo o URL que apunta a la 
	 * imagen de carátula. La imagen se carga en un objeto Image utilizando FileInputStream para leer el archivo, y luego 
	 * se establece en un ImageView llamado imagen mediante imagen.setImage(imagenInternaImage).
	 * 
	 * En caso de que ocurra una excepción de tipo FileNotFoundException al intentar cargar la imagen de carátula, se muestra un mensaje de error 
	 * en la consola con el contenido de la excepción utilizando System.out.println("Hay un error" + e).
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
			System.out.println("Hay un error" + e);
		}
	}
	//___________________________________________________________________________________________________________________


	/*
	 * Carga un archivo de vista (FXML) llamado "MenuAdmin.fxml" utilizando un objeto FXMLLoader. La ruta del archivo se
	 *  obtiene utilizando el método getClass().getResource(), 
	 * que busca el archivo en el classpath del proyecto.Crea un nuevo objeto Parent a partir del archivo de vista cargado en el paso anterior.
	 * 
	 * Crea una nueva escena (Scene) a partir del objeto Parent creado en el paso anterior.
	 * 
	 * Crea una nueva ventana (Stage) y establece la escena creada en el paso anterior como su escena.
	 * 
	 * Configura la ventana para que sea modal, lo que significa que bloqueará la interacción con otras ventanas hasta que se cierre.
	 * 
	 * Establece el título de la ventana como "Menu del Administrador".
	 * 
	 * Asigna un icono a la ventana utilizando un archivo de imagen llamado "LogoSpo.png" que se carga desde los recursos 
	 * del proyecto.
	 * 
	 * Muestra la ventana usando el método show().
	 * 
	 * Obtiene la referencia a la ventana actual (Stage) mediante la llamada al método this.btnVolver.getScene().getWindow(),
	 *  donde btnVolver es una referencia a un botón u otro componente de la GUI desde el cual se disparó el evento. Luego, 
	 *  cierra la ventana actual usando el método close().
	 *  
	 *  Si ocurre una excepción de tipo IOException al cargar el archivo de vista o al realizar alguna de las acciones
	 *  anteriores, se muestra un cuadro de diálogo de tipo Alert con un mensaje de error que contiene el mensaje de la 
	 *  excepción.
	 */
	@FXML
	void volverEvent(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/MenuAdmin.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Menu del Administrador");
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
	//___________________________________________________________________________________________________________________

	
	@FXML
	void registrarEvent(ActionEvent event) {
		
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ReproducirCancionAdmin.fxml"));
			Parent root = loader.load();

			ReproducirCancionAdmin controlador = loader.getController();
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

	/*
	 * Invoca el método abrirEnYoutube() en algún objeto de tipo IUsuario, pasándole como argumento el objeto cancion.
	 */
	@FXML
	void abrirYoutube(ActionEvent event) {
		MetodosUsuario.abrirEnYoutube(cancion);
	}
	//___________________________________________________________________________________________________________________
}
