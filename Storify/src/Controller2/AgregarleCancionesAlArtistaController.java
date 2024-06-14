package Controller2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Controller.MenuUsuarioController;
import Model.Artista;
import Model.Cancion;
import Model.MetodosAdmin;
import Model.MetodosUsuario;
import Model.Storify;
import Model.Usuario;
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

public class AgregarleCancionesAlArtistaController {

	/*
	 * Atributos y forma de saber de que artista en especifico se esta hablando
	 * tambien para acceder a la clase centrar Storify 
	 */
	private ObservableList<Cancion> canciones = FXCollections.observableArrayList();
	private Artista artista = MetodosAdmin.obtenerArtista();
	Storify acceso = MetodosAdmin.storify;
	//________________________________________________________________________________________________________

	@FXML
	private Button btnAgregarCancion;

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
	//________________________________________________________________________________________________________


	/*
	 * Se obtiene la canción seleccionada en la tabla (tblDatos) mediante getSelectedItem() y se almacena en la variable agregarleCancion.
	 * Se verifica si agregarleCancion es nulo, lo que significa que no se ha seleccionado ninguna canción. En ese caso, se muestra un mensaje de información indicando al usuario que seleccione una canción.
	 * Si agregarleCancion no es nulo, se agrega esa canción al artista específico (artista) mediante artista.getListaCanciones().getAcceso().agregarCancion(agregarleCancion).
	 * Se muestra un mensaje de información indicando que la canción se agregó exitosamente.
	 */
	@FXML
	void agregarCancion(ActionEvent event) {

		Cancion agregarleCancion = this.tblDatos.getSelectionModel().getSelectedItem();

		if(agregarleCancion == null ) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Atención");
			alert.setContentText("Primero selecciona la cancion que quieres agregarle al artista en especifico.");
			alert.showAndWait();

		}else {
			
			if(verificarExistenciaCancion(agregarleCancion) == false) {
				artista.getListaCanciones().getAcceso().agregarCancion(agregarleCancion);

				artista.getListaCanciones().getAcceso().mostrar();
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Atención");
				alert.setContentText("Canción agregada con exito");
				alert.showAndWait();
			}else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Atención");
				alert.setContentText("La canción ya fue agregada");
				alert.showAndWait();
			}
		}
	}
	//________________________________________________________________________________________________________


	private boolean verificarExistenciaCancion(Cancion agregarleCancion) {
		
		ArrayList<Cancion> lista = artista.getListaCanciones().getAcceso().obtenerCanciones();
		
		for (int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getCodigo().equals(agregarleCancion.getCodigo())) {
				return true;
			}
		}
		
		return false;
	}


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
	//________________________________________________________________________________________________________

	
	// Carga la lista de canciones que hay actualmente al table view
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
	//________________________________________________________________________________________________________
}
