package Controller2;

import java.io.File;
import java.io.IOException;

import Controller.DatosCancionController;
import Controller.MenuUsuarioController;
import Controller.ReproducirCancionListaController;
import Model.Artista;
import Model.Cancion;
import Model.MetodosAdmin;
import Model.MetodosUsuario;
import Model.Usuario;
import Model.ListaCircularCancionesUsuario.NodoCancion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class VerListaCancionesArtistaController {

	// Atributos que permiten crear la tabla de canciones y saber que usuario es del que se habla
	private ObservableList<Cancion> canciones = FXCollections.observableArrayList();
	private Usuario<?> usuario = MetodosUsuario.obtenerCliente();
	private Artista artista = MetodosAdmin.obtenerArtista();

	//____________________________________________________________________________________________________________________________


	/*
	 * Atributos del FXML
	 */
	@FXML
	private Button btnAgregarFavoritos;

	@FXML
	private Button btnReproducirLista;

	@FXML
	private Button btnRehacerInsercion;

	@FXML
	private Button btnEliminar;

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

		Cancion cancionSeleccionada = this.tblDatos.getSelectionModel().getSelectedItem();

		if(cancionSeleccionada == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Atención");
			alert.setContentText("Primero selecciona una canción.");
			alert.showAndWait();
		}else {
			Cancion cancion = this.tblDatos.getSelectionModel().getSelectedItem();
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

		//	    	IUsuario.storify.setUsuario(usuario);

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
	 * Obtiene la canción seleccionada en la tabla tblDatos utilizando el método getSelectedItem() del objeto tblDatos (presumiblemente es un objeto de una clase TableView o similar) y lo asigna a la variable cancionSeleccionada.
	 * 
	 * Verifica si la canción seleccionada es nula, lo cual significa que no se ha seleccionado ninguna canción en la tabla. Si es nula, muestra un cuadro de diálogo de tipo información con un mensaje de atención indicando que se debe seleccionar una canción antes de agregarla a favoritos.
	 * 
	 * Si la canción seleccionada no es nula, llama al método agregarCancionAListaFavoritas() del objeto IUsuario (presumiblemente una interfaz o clase que representa al usuario y tiene un método para agregar canciones a la lista de favoritos) pasando la canción seleccionada y el objeto usuario como argumentos. Esto implica que la canción seleccionada se agregará a la lista de canciones favoritas del usuario.
	 * 
	 * Muestra un cuadro de diálogo de tipo información con un mensaje d
	 */
	@FXML
	void agregarFavoritos(ActionEvent event) {

		Cancion cancionSeleccionada = this.tblDatos.getSelectionModel().getSelectedItem();

		if(cancionSeleccionada == null) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Atención");
			alert.setContentText("Primero selecciona la cancion que quieres agregar a favoritos.");
			alert.showAndWait();

		}else {

			MetodosUsuario.agregarCancionAListaFavoritas(cancionSeleccionada, usuario);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Atención");
			alert.setContentText("Cancion agregada con exito.");
			alert.showAndWait();
		}
	}
	//____________________________________________________________________________________________________________________________


	/*
	 * Carga un archivo FXML de la vista ReproducirCancionLista.fxml utilizando la clase FXMLLoader y lo asigna a la variable loader. La vista probablemente contiene una interfaz de reproducción de canciones en una lista.
	 * 
	 * Obtiene el nodo raíz de la vista cargada utilizando el método load() de la clase FXMLLoader y lo asigna a la variable root.
	 * 
	 * Obtiene el controlador de la vista cargada utilizando el método getController() de la clase FXMLLoader y lo asigna a la variable controlador. 
	 * El controlador es una instancia de ReproducirCancionListaController, que es responsable de manejar la lógica de la vista ReproducirCancionLista.fxml.
	 * 
	 * Crea una nueva escena Scene con el nodo raíz de la vista cargada y lo asigna a la variable scene.
	 * 
	 * Crea una nueva ventana Stage y le asigna la escena creada en el paso anterior. Configura el título, icono, y otras propiedades de la ventana.
	 * 
	 * Llama al método init() del controlador ReproducirCancionListaController para inicializar la vista.
	 * 
	 * Configura la modalidad de la ventana Stage como APPLICATION_MODAL, lo que significa que la ventana es modal y bloquea la interacción con otras ventanas de la aplicación hasta que se cierre.
	 * 
	 * Muestra la ventana Stage con el método show().
	 * 
	 * Obtiene la referencia a la ventana actual myStage utilizando el método getScene().getWindow() del botón que disparó el evento, y la cierra utilizando el método close().
	 * 
	 * En caso de que ocurra una excepción de tipo IOException al cargar la vista FXML, muestra un cuadro de diálogo de tipo error con un mensaje indicando el error.
	 */
	@FXML
	void reproducirLista(ActionEvent event) {

		if(artista.getListaCanciones().getAcceso().getCabeza() == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Atención");
			alert.setContentText("Este artista no tiene canciónes aun.");
			alert.showAndWait();
		}else {
			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/ReproducirCancionesArtista.fxml"));
				Parent root = loader.load();

				ReproducirListaCancionesArtistaController controlador = loader.getController();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				controlador.init();
				stage.initModality(Modality.APPLICATION_MODAL); 
				stage.setTitle("Reproducir Canciones de la Lista");
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
	public void init() {

		//			labelNombre.setText(usuario.getUsername().toUpperCase());

		this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
		this.colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
		this.colAlbum.setCellValueFactory(new PropertyValueFactory("album"));
		this.colAnio.setCellValueFactory(new PropertyValueFactory("anio"));
		this.colDuracion.setCellValueFactory(new PropertyValueFactory("duracion"));
		this.colGenero.setCellValueFactory(new PropertyValueFactory("genero"));

		for (int i = 0; i < artista.getListaCanciones().getAcceso().obtenerCanciones().size(); i++) {

			String nombre = artista.getListaCanciones().getAcceso().obtenerCanciones().get(i).getNombre();
			String codigo = artista.getListaCanciones().getAcceso().obtenerCanciones().get(i).getCodigo();
			String album = artista.getListaCanciones().getAcceso().obtenerCanciones().get(i).getAlbum();
			String caratula = artista.getListaCanciones().getAcceso().obtenerCanciones().get(i).getCaratula();
			String anio = artista.getListaCanciones().getAcceso().obtenerCanciones().get(i).getAnio();
			double duracion = artista.getListaCanciones().getAcceso().obtenerCanciones().get(i).getDuracion();
			String genero = artista.getListaCanciones().getAcceso().obtenerCanciones().get(i).getGenero();
			String url = artista.getListaCanciones().getAcceso().obtenerCanciones().get(i).getUrl();
			File archivo = artista.getListaCanciones().getAcceso().obtenerCanciones().get(i).getArchivo();

			Cancion cancion = new Cancion(nombre, codigo, album, caratula,  anio, duracion, genero, url,
					archivo);

			this.canciones.add(cancion);





		}

		this.tblDatos.setItems(canciones);
		this.tblDatos.refresh();


		//	    	NodoCancion nodoActual = usuario.getListaCanciones().getLista().getPrimerNodo();
		//
		//	    	do {
		//	    		System.out.println(nodoActual.getCancion());
		//
		//	    		String nombre = nodoActual.getCancion().getNombre();
		//	    		String codigo = nodoActual.getCancion().getCodigo();
		//	    		String album = nodoActual.getCancion().getAlbum();
		//	    		String caratula = nodoActual.getCancion().getCaratula();
		//	    		String anio = nodoActual.getCancion().getAnio();
		//	    		double duracion = nodoActual.getCancion().getDuracion();
		//	    		String genero = nodoActual.getCancion().getGenero();
		//	    		String url = nodoActual.getCancion().getUrl();
		//	    		File archivo = nodoActual.getCancion().getArchivo();
		//
		//	    		Cancion cancion = new Cancion(nombre, codigo, album, caratula,  anio, duracion, genero, url,
		//	    				archivo);
		//
		//	    		this.canciones.add(cancion);
		//
		//	    		nodoActual = nodoActual.getSiguiente();
		//	    	} while (nodoActual != usuario.getListaCanciones().getLista().getPrimerNodo());
		//
		//			this.tblDatos.setItems(canciones);
		//			this.tblDatos.refresh();
	}
	//____________________________________________________________________________________________________________________________
}
