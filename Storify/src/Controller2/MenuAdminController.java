package Controller2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

import Controller.DatosCancionController;
import Controller.ListaCancionesController;

import Model.Artista;
import Model.Cancion;
import Model.MetodosAdmin;
import Model.MetodosUsuario;
import Model.ListaDobleCancionesArtista;
import Model.Storify;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuAdminController {

	// Atributos
	@FXML
	private Button AriaMath;

	@FXML
	private Button btnListaArtistas;

	@FXML
	private Button btnCancionesOArtisas;

	@FXML
	private Button btnCancionesAgregarTXT;

	@FXML
	private Button btn16Lines;

	@FXML
	private Button btnAgregarCancion;

	@FXML
	private Button btnListaCanciones;

	@FXML
	private Button btnVolver;
	//_____________________________________________________________________________________________________________________________________


	
	/*
	 * Este código carga un nuevo archivo de diseño y muestra una nueva ventana que muestra la lista de artistas para el administrador, 
	 * cerrando la ventana actual. También maneja excepciones en caso de que ocurran problemas durante el proceso de carga del archivo de diseño.
	 */
	@FXML
	void listaArtistas(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/ListaArtistasAdmin.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Lista de los Artistas");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			Stage myStage = (Stage) this.btnListaArtistas.getScene().getWindow();
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
	 * Este código carga un nuevo archivo de diseño y muestra una nueva ventana que muestra la lista de canciones para el administrador, 
	 * cerrando la ventana actual. También maneja excepciones en caso de que ocurran problemas durante el proceso de carga del archivo de diseño.
	 */
	@FXML
	void listaCanciones(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/ListaCancionesAdmin.fxml"));
			Parent root = loader.load();

			ListaCancionesAdminController controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			controlador.init();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Ver la Lista de Canciones");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			Stage myStage = (Stage) this.btnListaCanciones.getScene().getWindow();
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
	 * Este código carga un nuevo archivo de diseño y muestra una nueva ventana para agregar una canción, cerrando la ventana actual. 
	 * También maneja excepciones en caso de que ocurran problemas durante el proceso de carga del archivo de diseño.
	 */
	@FXML
	void agregarCancion(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/AgregarCancion.fxml"));
			Parent root = loader.load();

			//			MenuUsuarioController controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			//			controlador.init();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Agregar Canción");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			Stage myStage = (Stage) this.btnAgregarCancion.getScene().getWindow();
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
	 * Este código carga un nuevo archivo de diseño y muestra una nueva ventana para agregar un artista, cerrando la ventana actual. 
	 * También maneja excepciones en caso de que ocurran problemas durante el proceso de carga del archivo de diseño.
	 */
	@FXML
	void agregarArtista(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/AgregarArtista.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Agregar Artista");
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
	//_____________________________________________________________________________________________________________________________________


	/*
	 * Este código carga un nuevo archivo de diseño y muestra una nueva ventana del menú principal, cerrando la ventana actual. 
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
	//_____________________________________________________________________________________________________________________________________


	/*
	 * Muestra una notificación informativa al usuario, permite seleccionar un archivo de texto que contiene información de artistas, 
	 * lee el contenido del archivo, crea objetos de artistas y los agrega a través de IAdmin. También muestra alertas para informar 
	 * al usuario sobre el progreso y maneja excepciones en caso de que ocurran problemas de lectura del archivo.
	 */
	@FXML
	void agregarCancionesOArtistas(ActionEvent event) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Atención");
		alert.setContentText("Antes de agregar un artista nuevo tenga en cuenta el orden, el cual es: \n"
				+ "\n#Artistas\r\n"
				+ "NombreArtista;Codigo;Nacionalidad;Artista_O_Grupo(true/false)");
		alert.showAndWait();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar archivo de artistas");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Archivos de texto", "*.txt")
				);
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {

			try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(";");
					String codigo = parts[0];
					String nombre = parts[1];
					String nacionalidad = parts[2];
					boolean artistaOGupo = Boolean.parseBoolean(parts[3]);
					Artista artista = new Artista(nombre, codigo, nacionalidad, artistaOGupo,  new ListaDobleCancionesArtista<Cancion>());
					// Aquí puedes hacer lo que quieras con el objeto Artista, como agregarlo a una lista o mostrarlo en la interfaz de usuario.

					System.out.println(artista);

					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Atención");
					alert.setContentText("Se agrego un artista desde archivo txt con exito.");
					alert.showAndWait();

					MetodosAdmin.agregarArtista(artista);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//_____________________________________________________________________________________________________________________________________

	
	/*
	 * Muestra una notificación informativa al usuario, permite seleccionar un archivo de texto que contiene información de canciones, lee 
	 * el contenido del archivo, crea objetos de canciones y los agrega a través de IAdmin. También muestra alertas para informar al usuario sobre 
	 * el progreso y maneja excepciones en caso de que ocurran problemas de lectura del archivo.
	 */
	@FXML
	void agregarCancionesTXT(ActionEvent event) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Atención");
		alert.setContentText("Antes de agregar una canción nueva tenga en cuenta el orden, el cual es: \n"
				+ "\n#Canciones\r\n"
				+ "NombreCancion;Codigo;NombreAlbum;Caratula;Año;Duración;Género;URLCanciónYoutube;ArchivoCancion;false");
		alert.showAndWait();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar archivo de canciones");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Archivos de texto", "*.txt")
				);
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {

			try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(";");
					String nombre = parts[0];
					String codigo = parts[1];
					String album = parts[2];
					//        	        String caratula = parts[3];
					String caratula = parts[3].replace("\"", "");

					File imagen = new File(caratula);
					String anio = parts[4];
					double duracion = Double.parseDouble(parts[5]);
					String genero = parts[6];
					String url = parts[7];
					File archivo = new File(parts[8]);

					Cancion cancion = new Cancion(nombre, codigo, album, imagen.toString(), anio, duracion,
							genero, url, archivo);

					System.out.println(cancion);

					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Atención");
					alert.setContentText("Se agrego una canción desde archivo txt con exito.");
					alert.showAndWait();

					MetodosAdmin.agregarCancion(cancion);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//_____________________________________________________________________________________________________________________________________

	
	/*
	 * Acceso a la clase Storify, es decir la clase principal
	 */
	Storify acceso = MetodosAdmin.storify;
	//_____________________________________________________________________________________________________________________________________

	
}
