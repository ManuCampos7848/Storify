package Controller2;

import java.io.File;
import java.io.IOException;

import Model.Cancion;
import Model.MetodosAdmin;
import Model.MetodosUsuario;
import Model.Storify;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class ListaCancionesAdminController {

	// Metodo que permite crear la lista de canciones para agregarlas al table view
	private ObservableList<Cancion> canciones = FXCollections.observableArrayList();
	//___________________________________________________________________________________________________________________

	// Atributos del JavaFX
	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnVerInfo;

	@FXML
	private Button btnVolver;

	@FXML
	private Button btnRefrescar;

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
	//___________________________________________________________________________________________________________________

	/*
	 * Metodo que elimina una cancion de la lista general de canciones
	 */
	@FXML
	void eliminarCancion(ActionEvent event) {
		
		Cancion cancion = this.tblDatos.getSelectionModel().getSelectedItem();
		
		if(cancion == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificación");
			alert.setContentText("Primero seleccione una canción.");
			alert.showAndWait();
		}else{
			MetodosAdmin.eliminarCancionListaGeneral(cancion);
			this.canciones.remove(cancion);
			this.tblDatos.refresh();
			
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificación");
			alert.setContentText("Canción eliminada con exito.");
			alert.showAndWait();
		}

	}
	//___________________________________________________________________________________________________________________


	/*
	 * Obtiene el elemento seleccionado (selectedItem) de una tabla (tblDatos) en la interfaz de usuario, que se supone contiene datos de canciones. 
	 * El elemento seleccionado se guarda en un objeto Cancion local llamado cancion.
	 * Establece el objeto cancion en un objeto IAdmin.storify, lo que parece ser una instancia de una clase o interfaz llamada IAdmin con una propiedad o método llamado storify.
	 * Carga una nueva interfaz de usuario JavaFX desde un archivo FXML llamado DatosCancionAdmin.fxml utilizando un cargador de FXML (FXMLLoader) y lo guarda en un objeto Parent llamado root.
	 * Obtiene el controlador (controlador) asociado a la interfaz de usuario cargada utilizando el método getController() del cargador de FXML.
	 * Crea una nueva escena (Scene) a partir del root.
	 * Crea una nueva ventana (Stage) y la muestra en pantalla con el método show(). La ventana tiene un título, un ícono de aplicación, 
	 * está configurada para ser modal (bloqueando la interacción con otras ventanas hasta que se cierre), y no es redimensionable.
	 * Cierra la ventana actual (myStage) en la que se encuentra el botón (btnVerInfo) que desencadenó el evento de acción.
	 */
	@FXML
	void verInformacion(ActionEvent event) {

		Cancion cancion = this.tblDatos.getSelectionModel().getSelectedItem();
		MetodosAdmin.storify.setCancionAdmin(cancion);
		MetodosUsuario.storify.setCancionAdmin(cancion);

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/DatosCancionAdmin.fxml"));
			Parent root = loader.load();

			DatosCancionAdmin controlador = loader.getController();
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

			// 			MenuUsuarioController controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			// 			controlador.init();
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
	//___________________________________________________________________________________________________________________

	Storify acceso = MetodosAdmin.storify;
	//___________________________________________________________________________________________________________________

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
	//___________________________________________________________________________________________________________________

	/*
	 * Refresca la tabla de canciones
	 */
	@FXML
	void refrescarLista(ActionEvent event) {
		this.tblDatos.refresh();
	}
	//___________________________________________________________________________________________________________________

}
