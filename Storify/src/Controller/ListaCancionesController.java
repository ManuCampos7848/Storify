package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import Model.Cancion;
import Model.MetodosAdmin;
import Model.MetodosUsuario;
import Model.Storify;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListaCancionesController {

	// La lista ObservableList de canciones que aparecen en el table view
	private ObservableList<Cancion> canciones = FXCollections.observableArrayList();
	//____________________________________________________________________________________________________


	// Atributos
	@FXML
	private Button btnVerInfo;

	@FXML
	private Button btnVolver;

	@FXML
	private Button btnOrdenarNombre;

	@FXML
	private TableColumn<?, ?> colAlbum;

	@FXML
	private TableColumn<?, ?> colAnio;

	@FXML
	private TableColumn<?, ?> colCodigo;

	@FXML
	private TableColumn<?, ?> colDuracion;

	@FXML
	private TableColumn<?, ?> colGenero;

	@FXML
	private TableColumn<?, ?> colNombre;

	@FXML
	private TableView<Cancion> tblDatos;
	//____________________________________________________________________________________________________


	/*
	 * Se obtiene la canción seleccionada en una tabla mediante this.tblDatos.getSelectionModel().getSelectedItem().
	 * Si la canción es null, se muestra un cuadro de diálogo informativo indicando al usuario que debe seleccionar una canción.
	 * En caso contrario, se realiza lo siguiente:
	 * Se establece la canción seleccionada como la canción actual en IUsuario.storify.setCancion(cancion).
	 * Se carga una vista de datos de canción utilizando FXMLLoader.
	 * Se obtiene el controlador de la vista cargada mediante loader.getController().
	 * Se crea una nueva escena y una nueva ventana (Stage) para mostrar la vista de datos de canción.
	 * Se inicializa el controlador mediante controlador.init().
	 * Se configura la ventana modal utilizando stage.initModality(Modality.APPLICATION_MODAL).
	 * Se establece el título y el icono de la ventana.
	 * Se establece la escena en la ventana y se configura la ventana como no redimensionable.
	 * Se muestra la ventana de datos de canción.
	 * Se obtiene la ventana actual (Stage) y se cierra utilizando myStage.close().
	 * Si se produce una excepción durante la carga de la vista de datos de canción, se muestra un cuadro de diálogo de error con el mensaje de la excepción.
	 */
	@FXML
	void verInformacion(ActionEvent event) {

		Cancion cancion = this.tblDatos.getSelectionModel().getSelectedItem();

		if(cancion == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Atención");
			alert.setContentText("Primero selecciona la cancion que quieres verle la información.");
			alert.showAndWait();
		}else {

			MetodosUsuario.storify.setCancion(cancion);

			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DatosCancion.fxml"));
				Parent root = loader.load();

				DatosCancionController controlador = loader.getController();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				controlador.init();
				stage.initModality(Modality.APPLICATION_MODAL); 
				stage.setTitle("Datos de la Canción");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				Stage myStage = (Stage) this.btnVerInfo.getScene().getWindow();
				myStage.close();

			} catch (IOException ex) {

				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}
		}
	}
	//____________________________________________________________________________________________________


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
			stage.setTitle("Menu del Usuario");
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
	//____________________________________________________________________________________________________



	// Acceso al Storify que es la clase central
	Storify acceso = MetodosAdmin.storify;
	//____________________________________________________________________________________________________


	/*
	 * Se configuran las propiedades de las columnas de la tabla utilizando setCellValueFactory y PropertyValueFactory.
	 * Se recorre la lista de canciones y se obtienen los datos de cada canción.
	 * Se crea un nuevo objeto Cancion con los datos obtenidos.
	 * Se agrega la canción a la lista canciones.
	 * Se establece la lista de canciones como el origen de datos de la tabla (tblDatos.setItems(canciones)).
	 * Se refresca la tabla para mostrar los cambios realizados (tblDatos.refresh()).
	 */
	public void init() {

		this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
		this.colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
		this.colAlbum.setCellValueFactory(new PropertyValueFactory("album"));
		this.colAnio.setCellValueFactory(new PropertyValueFactory("anio"));
		this.colDuracion.setCellValueFactory(new PropertyValueFactory("duracion"));
		this.colGenero.setCellValueFactory(new PropertyValueFactory("genero"));


		for (int i = 0; i < acceso.getListaCanciones().size(); i++) {

			String nombre = acceso.getListaCanciones().get(i).getNombre();
			String codigo = acceso.getListaCanciones().get(i).getCodigo();
			String album = acceso.getListaCanciones().get(i).getAlbum();
			String caratula = acceso.getListaCanciones().get(i).getCaratula();
			String anio = acceso.getListaCanciones().get(i).getAnio();
			double duracion = acceso.getListaCanciones().get(i).getDuracion();
			String genero = acceso.getListaCanciones().get(i).getGenero();
			String url = acceso.getListaCanciones().get(i).getUrl();
			File archivo = acceso.getListaCanciones().get(i).getArchivo();

			Cancion cancion = new Cancion(nombre, codigo, album, caratula,  anio, duracion, genero, url,
					archivo);

			this.canciones.add(cancion);
		} 

		this.tblDatos.setItems(canciones);
		this.tblDatos.refresh();
	}
	//____________________________________________________________________________________________________


	/*
	 * Se obtiene la lista de canciones actualmente mostrada en la tabla (tblDatos.getItems()).
	 * Se crea un comparador utilizando Comparator.comparing y especificando que se compare por el nombre de la canción (Cancion::getNombre).
	 * Se ordena la lista de canciones utilizando el comparador creado (canciones.sort(comparador)).
	 * Se actualiza el origen de datos de la tabla con la lista ordenada (tblDatos.setItems(FXCollections.observableArrayList(canciones))).
	 * Se muestra una notificación informativa indicando que las canciones han sido ordenadas por nombre.
	 */
	@FXML
	void ordenarPorNombre(ActionEvent event) {

		ObservableList<Cancion> canciones = tblDatos.getItems();

		// Crear un comparador que compare las canciones por el nombre.
		Comparator<Cancion> comparador = Comparator.comparing(Cancion::getNombre);

		// Ordenar la lista de canciones usando el comparador.
		canciones.sort(comparador);

		// Actualizar el TableView para reflejar el nuevo orden de las canciones.
		tblDatos.setItems(FXCollections.observableArrayList(canciones));

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Notificación");
		alert.setContentText("Canciones Ordenadas Por Nombre");
		alert.showAndWait();
	}
	//____________________________________________________________________________________________________
}