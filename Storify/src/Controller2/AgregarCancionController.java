package Controller2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import Model.Cancion;
import Model.MetodosAdmin;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class AgregarCancionController implements Initializable{

	// Atributos del javaFx como otras variables
	@FXML
	private Button btnAdjuntarCaratula;

	@FXML
	private Button btnAgregarCancion;

	@FXML
	private Button btnArchivo;

	@FXML
	private Button btnVolver;

	@FXML
	private SplitPane caratula;

	@FXML
	private ImageView imagen;

	@FXML
	private TextField txtAlbum;

	@FXML
	private TextField txtAnio;

	@FXML
	private TextField txtDuracion;

	@FXML
	private TextField txtGenero;


	private File archivoImagen; // Archivo de la carátula de la canción

	private File archivoDeLaCancion; // Archivo de la canción en formato MP3

	@FXML
	private TextField txtNombreCancion;

	@FXML
	private TextField txtURL;
	//_____________________________________________________________________________________________________________________


	/*
	 * Crea una nueva instancia de Stage con valor null. Muestra un cuadro de diálogo para que el usuario seleccione un archivo de imagen. 
	 * Si se selecciona un archivo de imagen, muestra la ruta absoluta del archivo en la consola, carga la imagen en un elemento de la interfaz de usuario, guarda el archivo seleccionado en una variable y muestra una ventana de alerta. 
	 * Si no se selecciona un archivo de imagen, muestra un mensaje en la consola. 
	 * En caso de ocurrir una excepción al leer el archivo, imprime la traza de la excepción en la consola sin manejar el error.
	 */
	@FXML
	void adjuntarCaratula(ActionEvent event) {

		Stage primaryStage = null;

		Platform.runLater(() -> {
			File seleccionarArchivoImagen = seleccionarArchivoImagen(primaryStage); // Mostrar diálogo para seleccionar archivo de imagen
			if (seleccionarArchivoImagen != null) {
				System.out.println("Archivo png o jpg seleccionado: " + seleccionarArchivoImagen.getAbsolutePath());
				FileInputStream input;
				try {

					input = new FileInputStream(seleccionarArchivoImagen);
					Image image = new Image(input);
					imagen.setImage(image);

					archivoImagen = seleccionarArchivoImagen; // Guardar el archivo de la carátula seleccionada

					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Notificación");
					alert.setContentText("Archivo de la Imagen seleccionado con exito.");
					alert.showAndWait();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}


			} else {
				System.out.println("Ningún archivo de tipo imagen seleccionado.");
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Notificación");
				alert.setContentText("Ningún archivo de tipo imagen seleccionado.");
				alert.showAndWait();
			}
		});
	}
	//_____________________________________________________________________________________________________________________

	/*
	 * Crea una nueva instancia de Stage con valor null. 
	 * Muestra un cuadro de diálogo para que el usuario seleccione un archivo de canción en formato MP3. 
	 * Si se selecciona un archivo de canción, muestra la ruta absoluta del archivo en la consola, guarda el archivo seleccionado en una variable y muestra una ventana de alerta. 
	 * Si no se selecciona un archivo de canción, muestra un mensaje en la consola. 
	 * En caso de ocurrir una excepción al leer el archivo, no maneja el error y simplemente imprime la traza de la excepción en la consola.
	 */
	private File seleccionarArchivoImagen(Stage primaryStage) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar Imagen");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Archivos Imagen", "*.png", "*.jpg"));
		File selectedFile = fileChooser.showOpenDialog(primaryStage);

		if (selectedFile != null) {
			return selectedFile;
		} else {
			return null;
		}
	}
	//_____________________________________________________________________________________________________________________


	/*
	 * El método archivoCancion(ActionEvent event) es un manejador de eventos para un evento de acción, 
	 * presumiblemente asociado con un botón o algún otro componente de la GUI. 
	 * Cuando se ejecuta este evento, se muestra un diálogo para seleccionar un archivo de canción en formato MP3 mediante la llamada al método seleccionarArchivoMP3(Stage primaryStage).
	 *  Si se selecciona un archivo MP3 válido, se imprime la ruta absoluta 
	 * del archivo en la consola, se guarda el archivo seleccionado en una variable llamada archivoDeLaCancion, y se muestra un cuadro de diálogo de tipo Alert con un mensaje de éxito.
	 * 
	 * El método seleccionarArchivoMP3(Stage primaryStage) crea y muestra un diálogo de selección de archivo utilizando la clase FileChooser de JavaFX. 
	 * El diálogo permite al usuario seleccionar un archivo con extensión ".mp3". Si se selecciona un archivo válido, se retorna el archivo seleccionado como un objeto File. 
	 * Si no se selecciona ningún archivo, se retorna null.
	 */
	@FXML
	void archivoCancion(ActionEvent event) {

		Stage primaryStage = null;

		Platform.runLater(() -> {
			File archivoMP3 = seleccionarArchivoMP3(primaryStage); // Mostrar diálogo para seleccionar archivo de canción MP3
			if (archivoMP3 != null) {
				System.out.println("Archivo MP3 seleccionado: " + archivoMP3.getAbsolutePath());
				archivoDeLaCancion = archivoMP3; // Guardar el archivo de la canción seleccionada

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Notificación");
				alert.setContentText("Archivo de la canción agregado con exito.");
				alert.showAndWait();
			} else {

				System.out.println("Ningún archivo MP3 seleccionado.");
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Notificación");
				alert.setContentText("Ningún archivo MP3 seleccionado.");
				alert.showAndWait();
			}
		});
	}

	public File seleccionarArchivoMP3(Stage primaryStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar Canción");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Archivos MP3", "*.mp3"));
		File selectedFile = fileChooser.showOpenDialog(primaryStage);

		if (selectedFile != null) {
			return selectedFile;
		} else {
			return null;
		}
	}
	//_____________________________________________________________________________________________________________________


	/*
	 * Genera un número aleatorio de 4 cifras utilizando la clase Random para ser utilizado como código de la nueva canción.
	 * Realiza una serie de comprobaciones en los campos de texto y archivos adjuntos para asegurarse de que todos los campos estén llenos y los archivos seleccionados existan antes de continuar.
	 * Verifica que el campo de texto "txtDuracion" no contenga letras, utilizando una expresión regular para comprobar si la duración de la canción es un valor numérico.
	 * Si todas las comprobaciones son exitosas, obtiene los valores de los campos de texto y archivos adjuntos, crea una nueva instancia de la clase Cancion con los valores proporcionados, y 
	 * la agrega a través de un objeto de una interfaz IAdmin (presumiblemente relacionada con la administración de canciones).
	 * Muestra un cuadro de diálogo de tipo Alert con un mensaje de éxito si la canción se agrega correctamente.
	 */
	@FXML
	void agregarCancion(ActionEvent event) throws IOException {

		Random random = new Random();

		// Generar un número aleatorio de 4 cifras
		int randomNumber = random.nextInt(9000) + 1000;

		String codigo = Integer.toString(randomNumber);


		if(this.txtNombreCancion.getText().equals("") || this.txtGenero.getText().equals("") || this.txtAlbum.getText().equals("") ||
				this.txtAnio.getText().equals("") || this.txtURL.getText().equals("") || this.txtDuracion.getText().equals("") &&   
				archivoImagen == null || archivoDeLaCancion == null) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificación");
			alert.setContentText("Primero llene todos los campos y elija los archivos correspondientes.");
			alert.showAndWait();

		}else {

			if(this.txtDuracion.getText().matches("[a-zA-Z]+") || !this.txtDuracion.getText().matches("\\d+(\\.\\d+)?")) {
			    Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setHeaderText(null);
			    alert.setTitle("Notificación");
			    alert.setContentText("La duración de la canción debe ser un valor numérico double, ejemplo: 2.42");
			    alert.showAndWait();
			}else {

				String nombreCancion = this.txtNombreCancion.getText();
				String genero = this.txtGenero.getText();
				String album = this.txtAlbum.getText();
				String anio = this.txtAnio.getText();
				String url = this.txtURL.getText();
				double duracion = Double.parseDouble(this.txtDuracion.getText());

				Cancion cancionNueva = new Cancion(nombreCancion, codigo, album, archivoImagen.toString(), anio, duracion,
						genero, url, archivoDeLaCancion, false);

				if(verificarExistenciaCancion(cancionNueva)) {
					
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Notificación");
					alert.setContentText("La canción ya existe.");
					alert.showAndWait();
				}else {

					if(MetodosAdmin.agregarCancion(cancionNueva)) {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setHeaderText(null);
						alert.setTitle("Notificación");
						alert.setContentText("Canción agregada con exito.");
						alert.showAndWait();

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
							Stage myStage = (Stage) this.btnVolver.getScene().getWindow();
							myStage.close();

						} catch (IOException ex) {

							alert = new Alert(Alert.AlertType.ERROR);
							alert.setHeaderText(null);
							alert.setTitle("Error");
							alert.setContentText(ex.getMessage());
							alert.showAndWait();
						}
					}else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setHeaderText(null);
						alert.setTitle("Error");
						alert.setContentText("Esa canción ya existe");
						alert.showAndWait();
					}
				}





			}
		}
	}
	private boolean verificarExistenciaCancion(Cancion cancionNueva) {
		return MetodosAdmin.storify.verificarExistenciaCancion(cancionNueva);
	}
	//_____________________________________________________________________________________________________________________


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
	 * Establece el título de la ventana como "Menu del Cliente".
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
	//_____________________________________________________________________________________________________________________


	/*
	 * Establece estilos de texto, color de fondo, ancho de borde y radio de esquina para el componente txtNombreCancion
	 * 
	 * Establece estilos de texto, color de fondo, ancho de borde y radio de esquina para el componente txtGenero
	 * 
	 * Establece estilos de texto, color de fondo, ancho de borde y radio de esquina para el componente txtAnio
	 * 
	 * Establece estilos de texto, color de fondo, ancho de borde y radio de esquina para el componente txtDuracion
	 * 
	 * Establece estilos de texto, color de fondo, ancho de borde y radio de esquina para el componente txtURL
	 * 
	 * Establece estilos de texto, color de fondo, ancho de borde y radio de esquina para el componente txtAlbum
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		txtNombreCancion.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

		txtGenero.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

		txtAnio.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

		txtDuracion.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

		txtURL.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

		txtAlbum.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

	}
	//_____________________________________________________________________________________________________________________

}
