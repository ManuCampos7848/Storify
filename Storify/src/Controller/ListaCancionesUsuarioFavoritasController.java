package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Cancion;
import Model.MetodosUsuario;
import Model.Usuario;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListaCancionesUsuarioFavoritasController implements Initializable{

	// Atributos que permiten crear la tabla de canciones y saber que usuario es del que se habla
	private ObservableList<Cancion> canciones = FXCollections.observableArrayList();
	private Usuario<?> usuario = MetodosUsuario.obtenerCliente();
	//____________________________________________________________________________________________________________________________


	/*
	 * Atributos del FXML
	 */
	@FXML
	private Button btnVerInfo;

	@FXML
	private Button btnVolver;

	@FXML
	private TableColumn<?, ?> colAlbum;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnReproducirLista;

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
	private Label labelNombre;

	@FXML
	private TableView<Cancion> tblDatos;
	//____________________________________________________________________________________________________________________________


	/*
	 * Carga una nueva escena desde un archivo FXML utilizando FXMLLoader para la vista "DatosCancion.fxml".
	 * 
	 * Obtiene una referencia al controlador de la vista cargada mediante loader.getController() y lo almacena en una variable controlador.
	 * 
	 * Crea una nueva escena con la vista cargada.
	 * 
	 * Crea una nueva ventana (Stage) y configura sus propiedades, como título, icono, y si es redimensionable o no.
	 * 
	 * Muestra la nueva ventana con la escena cargada.
	 * 
	 * Cierra la ventana actual (myStage) utilizando myStage.close() para cerrar la ventana anterior.
	 * 
	 * En caso de que ocurra una excepción durante la carga de la vista, muestra un cuadro de diálogo de error con un mensaje de error.
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
	//____________________________________________________________________________________________________________________________


	/*
	 * Carga una nueva escena desde un archivo FXML utilizando FXMLLoader para la vista "MenuUsuario.fxml".
	 * 
	 * Obtiene una referencia al controlador de la vista cargada mediante loader.getController() y lo almacena en una variable controlador.
	 * 
	 * Crea una nueva escena con la vista cargada.
	 * 
	 * Crea una nueva ventana (Stage) y configura sus propiedades, como título, icono, y si es redimensionable o no.
	 * 
	 * Muestra la nueva ventana con la escena cargada.
	 * 
	 * Cierra la ventana actual (myStage) utilizando myStage.close() para cerrar la ventana anterior.
	 * 
	 * En caso de que ocurra una excepción durante la carga de la vista, muestra un cuadro de diálogo de error con un mensaje de error.
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
	//____________________________________________________________________________________________________________________________


	/*
	 * Configura las celdas de una tabla (presumiblemente tblDatos) con los valores de las propiedades de la clase Cancion (nombre, código, álbum, año, duración, género) utilizando setCellValueFactory() de PropertyValueFactory.
	 * 
	 * Recorre las canciones favoritas del usuario (presumiblemente almacenadas en un objeto usuario).
	 * 
	 * Por cada canción favorita del usuario, obtiene los valores de sus propiedades (nombre, código, álbum, carátula, año, duración, género, URL y archivo) y crea un nuevo objeto Cancion con esos valores.
	 * 
	 * Comprueba si la canción ya existe en la lista canciones (presumiblemente una lista observable que se utiliza para la tabla). Si no existe, la agrega a la lista y actualiza la tabla con tblDatos.setItems(canciones).
	 * 
	 * Este proceso se repite para todas las canciones favoritas del usuario.
	 * 
	 * Es importante tener en cuenta que no se realiza ninguna actualización visual de la tabla (tblDatos) en cada iteración del bucle. En su lugar, se actualiza la tabla una vez después de agregar todas las canciones favoritas del usuario.
	 * Esto puede mejorar la eficiencia y el rendimiento en comparación con actualizar la tabla en cada iteración del bucle.
	 * 
	 * Este método se ejecutará automáticamente al cargar la vista correspondiente, lo que permite que los datos de las canciones favoritas del usuario se muestren en la tabla al iniciar la interfaz de usuario.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
		this.colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
		this.colAlbum.setCellValueFactory(new PropertyValueFactory("album"));
		this.colAnio.setCellValueFactory(new PropertyValueFactory("anio"));
		this.colDuracion.setCellValueFactory(new PropertyValueFactory("duracion"));
		this.colGenero.setCellValueFactory(new PropertyValueFactory("genero"));

		for (int i = 0; i < usuario.getCancionesFavoritas().size(); i++) {

			String nombre = usuario.getCancionesFavoritas().get(i).getNombre();
			String codigo = usuario.getCancionesFavoritas().get(i).getCodigo();
			String album = usuario.getCancionesFavoritas().get(i).getAlbum();
			String caratula = usuario.getCancionesFavoritas().get(i).getCaratula();
			String anio = usuario.getCancionesFavoritas().get(i).getAnio();
			double duracion = usuario.getCancionesFavoritas().get(i).getDuracion();
			String genero = usuario.getCancionesFavoritas().get(i).getGenero();
			String url = usuario.getCancionesFavoritas().get(i).getUrl();
			File archivo = usuario.getCancionesFavoritas().get(i).getArchivo();

			Cancion cancion = new Cancion(nombre, codigo, album, caratula,  anio, duracion, genero, url,
					archivo);

			if(!this.canciones.contains(cancion)) {
				this.canciones.add(cancion);
				this.tblDatos.setItems(canciones);
			} 
		}
	}
	//____________________________________________________________________________________________________________________________


	/*
	 * Obtiene la canción seleccionada en la tabla (tblDatos) mediante getSelectedItem() de SelectionModel.
	 * 
	 * Verifica si se ha seleccionado una canción o no. Si no se ha seleccionado ninguna canción, muestra un cuadro de diálogo de información con un mensaje indicando que se debe seleccionar una canción primero.
	 * 
	 * Si se ha seleccionado una canción, la elimina de la lista de canciones favoritas del usuario (presumiblemente almacenadas en un objeto usuario) utilizando remove() de ObservableList.
	 * 
	 * Luego, elimina la canción de la lista de canciones (canciones) que se muestra en la tabla utilizando remove() de ObservableList, y refresca la tabla con tblDatos.refresh() para que la eliminación sea visible en la interfaz de usuario.
	 */
	@FXML
	void eliminarCancion(ActionEvent event) {
		Cancion cancionSeleccionada = this.tblDatos.getSelectionModel().getSelectedItem();


		if(cancionSeleccionada == null) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Atención");
			alert.setContentText("Primero selecciona la cancion que quieres eliminar.");
			alert.showAndWait();

		}else {

			usuario.getCancionesFavoritas().remove(cancionSeleccionada);

			this.canciones.remove(cancionSeleccionada);
			this.tblDatos.refresh();


		}
	}
	//____________________________________________________________________________________________________________________________


	/*
	 * Carga la interfaz de usuario ReproducirCancionListaFavoritos.fxml utilizando FXMLLoader y la carga en un objeto Parent llamado root.
	 * 
	 * Obtiene el controlador asociado a la interfaz de usuario cargada utilizando loader.getController() y lo guarda en un objeto controlador de tipo ReproducirCancionListaFavoritasController. 
	 * Presumiblemente, ReproducirCancionListaFavoritasController es la clase que controla la lógica de la interfaz de usuario para reproducir una lista de canciones favoritas.
	 * 
	 * Crea una nueva escena (Scene) con root como nodo raíz de la interfaz de usuario.
	 * 
	 * Crea una nueva ventana (Stage) con la escena creada y la configura con varios atributos, como el título de la ventana, el ícono, la modalidad de la ventana (
	 * en este caso, se establece como Modality.APPLICATION_MODAL para que la ventana sea modal y bloquee otras ventanas), la no redimensionabilidad de la ventana, y muestra la ventana con stage.show().
	 * 
	 * Cierra la ventana actual en la que se encuentra el botón que activó el evento, utilizando this.btnReproducirLista.getScene().getWindow().close().
	 * 
	 * Maneja una excepción (IOException) si ocurre algún error al cargar la interfaz de usuario, mostrando un cuadro de diálogo de error con el mensaje de error obtenido.
	 */
	@FXML
	void reproducirLista(ActionEvent event) {

		Cancion cancion = this.tblDatos.getSelectionModel().getSelectedItem();
		
		if(cancion == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Atención");
			alert.setContentText("Primero debe agregar canciones.");
			alert.showAndWait();
		}else {


			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ReproducirCancionListaFavoritos.fxml"));
				Parent root = loader.load();

				ReproducirCancionListaFavoritasController controlador = loader.getController();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				controlador.init();
				stage.initModality(Modality.APPLICATION_MODAL); 
				stage.setTitle("Reproducir Lista de Canciones Favoritas");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				Stage myStage = (Stage) this.btnReproducirLista.getScene().getWindow();
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
	//____________________________________________________________________________________________________________________________


	/*
	 * Obtiene el nombre de usuario del objeto usuario (presumiblemente es un objeto de una clase Usuario o similar) utilizando el método getUsername().
	 * 
	 * Convierte el nombre de usuario a letras mayúsculas utilizando el método toUpperCase().
	 * 
	 * Establece el texto del labelNombre (presumiblemente es un objeto de una clase Label o similar) con el nombre de usuario en letras mayúsculas utilizando el método setText().
	 */
	public void init() {
		labelNombre.setText(usuario.getUsername().toUpperCase());
	}
	//____________________________________________________________________________________________________________________________

}
