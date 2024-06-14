package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Cancion;
import Model.MetodosUsuario;
import Model.Usuario;
import Model.ListaCircularCancionesUsuario.NodoCancion;
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

public class CancionesUsuarioEliminadas implements Initializable{

	// Atributo para saber que usuario es el que se esta hablando
	private Usuario<?> usuario = MetodosUsuario.obtenerCliente();
	//_____________________________________________________________________________________________________________

	@FXML
	private Button btnDeshacerInsercion;

	@FXML
	private Button btnVerInfo;

	@FXML
	private Button btnVolver;

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

	private ObservableList<Cancion> canciones = FXCollections.observableArrayList();
	//_____________________________________________________________________________________________________________


	/* Metodo que vuelve a agregar la canción que el usuario haya eliminado otra vez a su lista de canciones
	 * 
	 */
	@FXML
	void deshacerInsercion(ActionEvent event) {

		Cancion cancionSeleccionada = this.tblDatos.getSelectionModel().getSelectedItem();

		if(cancionSeleccionada == null) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Atención");
			alert.setContentText("Primero selecciona una canción.");
			alert.showAndWait();

		}else {

			if(usuario.getCancionesEliminadas().isEmpty()) {
				usuario.getCancionesEliminadas().clear();
			}else {
				usuario.getCancionesEliminadas().remove(cancionSeleccionada);
				usuario.getListaCanciones().add(cancionSeleccionada);

				this.canciones.remove(cancionSeleccionada);
				this.tblDatos.refresh();
			}
		}
	}
	//_____________________________________________________________________________________________________________


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
	//_____________________________________________________________________________________________________________


	/*
	 * Metodo Initializable que carga las canciones que el usuario haya eliminado ya
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
		this.colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
		this.colAlbum.setCellValueFactory(new PropertyValueFactory("album"));
		this.colAnio.setCellValueFactory(new PropertyValueFactory("anio"));
		this.colDuracion.setCellValueFactory(new PropertyValueFactory("duracion"));
		this.colGenero.setCellValueFactory(new PropertyValueFactory("genero"));


		for (int i = 0; i < usuario.getCancionesEliminadas().size(); i++) {

			String nombre = usuario.getCancionesEliminadas().get(i).getNombre();
			String codigo = usuario.getCancionesEliminadas().get(i).getCodigo();
			String album = usuario.getCancionesEliminadas().get(i).getAlbum();
			String caratula = usuario.getCancionesEliminadas().get(i).getCaratula();
			String anio = usuario.getCancionesEliminadas().get(i).getAnio();
			double duracion =usuario.getCancionesEliminadas().get(i).getDuracion();
			String genero = usuario.getCancionesEliminadas().get(i).getGenero();
			String url = usuario.getCancionesEliminadas().get(i).getUrl();
			File archivo = usuario.getCancionesEliminadas().get(i).getArchivo();

			Cancion cancion = new Cancion(nombre, codigo, album, caratula,  anio, duracion, genero, url,
					archivo);

			if(!this.canciones.contains(cancion)) {
				this.canciones.add(cancion);
				this.tblDatos.setItems(canciones);

			} 


		}

	}
	//_____________________________________________________________________________________________________________

	// Metodo init
	public void init() {}
	//_____________________________________________________________________________________________________________
}