package Controller2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.ListaCancionesController;
import Model.Artista;
import Model.Cancion;
import Model.MetodosAdmin;
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

public class ListaArtistasAdminController implements Initializable{

	// Atributos
	@FXML
	private Button btnAgregarCancionesArtista;

	@FXML
	private Button btnVolver;

	@FXML
	private TableColumn<?, ?> colCodigo;

	@FXML
	private TableColumn<?, ?> colGrupo;

	@FXML
	private TableColumn<?, ?> colNacionalidad;

	@FXML
	private TableColumn<?, ?> colNombre;

	@FXML
	private TableView<Artista> tblDatos;
	//________________________________________________________________________________________________________________________

	// Atributo de la lista de canciones
	ObservableList<Artista> listaArtistas = FXCollections.observableArrayList();
	//________________________________________________________________________________________________________________________


	/*
	 * Se obtiene el artista seleccionado en la tabla (tblDatos) mediante getSelectedItem() y se almacena en la variable artistaSeleccionado.
	 * Se verifica si artistaSeleccionado es nulo, lo que significa que no se ha seleccionado ningún artista. En ese caso, se muestra un mensaje de información indicando al usuario que seleccione un artista.
	 * Si artistaSeleccionado no es nulo, se establece ese artista en la instancia de storify en la clase IAdmin mediante IAdmin.storify.setArtista(artistaSeleccionado).
	 * Se carga la vista "AgregarleCancionArtista.fxml" usando FXMLLoader y se muestra en una nueva ventana.
	 * Se obtiene el controlador de la vista cargada y se inicializa mediante controlador.init().
	 * Se crea una nueva ventana (Stage) para mostrar la vista cargada.
	 * Se configura la ventana y se muestra.
	 * Se cierra la ventana actual mediante Stage myStage = (Stage) this.btnAgregarCancionesArtista.getScene().getWindow(); myStage.close().
	 */
	@FXML
	void agregarCancionesArtista(ActionEvent event) {

		Artista artistaSeleccionado = this.tblDatos.getSelectionModel().getSelectedItem();

		if(artistaSeleccionado == null) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Atención");
			alert.setContentText("Primero seleccione un artista para agregarle una cancion.");
			alert.showAndWait();

		}else {
			MetodosAdmin.storify.setArtista(artistaSeleccionado);

			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/AgregarleCancionArtista.fxml"));
				Parent root = loader.load();

				AgregarleCancionesAlArtistaController controlador = loader.getController();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				controlador.init();
				stage.initModality(Modality.APPLICATION_MODAL); 
				stage.setTitle("Agregar Canciones al Artista");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				Stage myStage = (Stage) this.btnAgregarCancionesArtista.getScene().getWindow();
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
	//________________________________________________________________________________________________________________________



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
	//________________________________________________________________________________________________________________________

	/*
	 * Netodo que permite cargar la lista de artistas
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
		this.colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
		this.colNacionalidad.setCellValueFactory(new PropertyValueFactory("nacionalidad"));
		this.colGrupo.setCellValueFactory(new PropertyValueFactory("artista_O_Grupo"));

		cargarTablaDesdeArbol();

	}
	//________________________________________________________________________________________________________________________

	
	/*
	 * Netodo que permite cargar la lista de artistas
	 */
	private void cargarTablaDesdeArbol() {
		tblDatos.getItems().clear(); // Limpiar tabla
		recorrerArbolParaTabla(acceso.getListaArtistas().getRaiz()); // Recorrer árbol y agregar filas
	}
	//________________________________________________________________________________________________________________________

	
	/*
	 * Metodo que posibilita cargar los artistas al table view
	 */
	private void recorrerArbolParaTabla(Artista nodo) {
		if (nodo != null) {
			recorrerArbolParaTabla(nodo.hijoIzquierdo); // Recorre el subárbol izquierdo
			tblDatos.getItems().add(nodo); // Agrega el artista a la tabla
			recorrerArbolParaTabla(nodo.hijoDerecho); // Recorre el subárbol derecho
		}
	}
	//________________________________________________________________________________________________________________________

	// Forma en la que se accede a la clase principal
	Storify acceso = MetodosAdmin.storify;
	//________________________________________________________________________________________________________________________
}