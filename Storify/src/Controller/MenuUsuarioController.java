package Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import Controller2.ListaArtistasPorAtributoController;
import Controller2.ListaCancionesPorAtributoController;
import Controller2.VerListaCancionesArtistaController;
import Model.Artista;
import Model.Cancion;
import Model.MetodosAdmin;
import Model.MetodosUsuario;
import Model.ListaCircularCancionesUsuario;
import Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuUsuarioController {

	// Atributo que permite saber de que cliente se esta hablando
	private Usuario<?> usuario = MetodosUsuario.obtenerCliente();
	//_____________________________________________________________________________________________________________________


	// Atributos
	@FXML
	private Button AriaMath;

	@FXML
	private Button btnListaCanciones;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button AriaMath1;

	@FXML
	private Button btnVolver;

	@FXML
	private Button btn16Lines;

	@FXML
	private Button btnBeatles;

	@FXML
	private Button btnBogPoppa;

	@FXML
	private Button btnDiome;

	@FXML
	private Button btnGhostB;

	@FXML
	private Button btnInserciones;

	@FXML
	private Button btnPrinceGod;

	@FXML
	private Button btnListaCFavoritas;

	@FXML
	private Button btnNicolaiFella;

	@FXML
	private Button btnSerenata;

	@FXML
	private Button btnStupidLoveHistory;

	@FXML
	private Button btnSurfCurse;

	@FXML
	private Button btnVerListaCanciones;

	@FXML
	private Button btnListaArtistas;

	@FXML
	private TextField txtBuscar;

	@FXML
	private Button dePeAPa;

	@FXML
	private Label labelNombre;
	//_____________________________________________________________________________________________________________________


	/*
	 * Este controlador de eventos carga y muestra la vista ListaArtistas.fxml 
	 * en una nueva ventana cuando se produce un clic en el botón correspondiente.
	 */
	@FXML
	void listaArtistas(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/ListaArtistas.fxml"));
			Parent root = loader.load();

			//    		ListaCancionesController controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			//    		controlador.init();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Lista de los Artistas");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos carga y muestra la vista ListaCanciones.fxml en una nueva ventana cuando se produce un 
	 * clic en el botón correspondiente. Además, inicializa el controlador de la vista llamando al método init antes de mostrar la ventana.
	 */
	@FXML
	void listaCanciones(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ListaCanciones.fxml"));
			Parent root = loader.load();

			ListaCancionesController controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			controlador.init();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Lista de Canciones");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos carga y muestra la vista ListaCancionesFavoritasUsuario.fxml en una nueva ventana cuando se produce un 
	 * clic en el botón correspondiente. Antes de mostrar la ventana, se verifica si el usuario tiene canciones favoritas y se muestra 
	 * un mensaje de información si la lista de canciones favoritas es nula. Además, inicializa el controlador de la vista llamando al método 
	 * init antes de mostrar la ventana.
	 */
	@FXML
	void ListaCancionesFavoritas(ActionEvent event) {


		MetodosUsuario.storify.setUsuario(usuario);
		MetodosAdmin.storify.setUsuario(usuario);


		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ListaCancionesFavoritasUsuario.fxml"));
			Parent root = loader.load();

			ListaCancionesUsuarioFavoritasController controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			controlador.init();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Lista de Canciones Favoritas");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos crea una instancia de la canción "Aria Math - C418" y la asigna al objeto Cancion en la clase 
	 * IUsuario.storify. Luego, carga y muestra la vista DatosCancion.fxml en una nueva ventana cuando se produce un clic en el 
	 * botón correspondiente. Antes de mostrar la ventana, inicializa el controlador de la vista llamando al método init.
	 */
	@FXML
	void ariaMath(ActionEvent event) {

		String nombre = "Aria Math - C418";
		String codigo = "790155";
		String album = "Soundtrack Minecraft";

		ImageIcon caratula = new ImageIcon("src/Resources/C4-18.jpg");
		String caratulaToString = caratula.toString();

		String anio = "2011";
		double duracion = 3.57;
		String genero = "Relajante";
		String url = "https://www.youtube.com/watch?v=atgjKEgSqSU&pp=ygUJYXJpYSBtYXRo";
		File archivo = new File("src/Resources/Musica/C418  Aria Math Minecraft.mp3");


		Cancion ariaMath = new Cancion(nombre, codigo, album, caratulaToString, anio, duracion, genero , url, archivo);

		MetodosUsuario.storify.setCancion(ariaMath);

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
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * 
	 */
	@FXML
	void bigPoppa(ActionEvent event) {

		String nombre = "Big Poppa - The Notorius BIG";
		String codigo = "13569";
		String album = "Ready to Die";

		ImageIcon caratula = new ImageIcon("src/Resources/Big Poppa.jpg");
		String caratulaToString = caratula.toString();

		String anio = "1994";
		double duracion = 4.21;
		String genero = "Hip-Hop";
		String url = "https://www.youtube.com/watch?v=QceVTChhlJM&pp=ygUJYmlnIHBvcHBh";

		File archivo = new File("src/Resources/Musica/Big Poppa - The Notorious Big.mp3");

		Cancion ariaMath = new Cancion(nombre, codigo, album, caratulaToString, anio, duracion, genero , url, archivo);

		MetodosUsuario.storify.setCancion(ariaMath);

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
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos crea una instancia de la canción "Big Poppa - The Notorious BIG" y la asigna al objeto 
	 * Cancion en la clase IUsuario.storify. Luego, carga y muestra la vista DatosCancion.fxml en una nueva ventana cuando 
	 * se produce un clic en el botón correspondiente. Antes de mostrar la ventana, inicializa el controlador de la vista llamando al método init.
	 */
	@FXML
	void dePAPa(ActionEvent event) {

		String nombre = "De Pe a Pa - Granuja";
		String codigo = "0129";
		String album = "Circulo Vicioso";

		ImageIcon caratula = new ImageIcon("src/Resources/Granuja.jpg");
		String caratulaToString = caratula.toString();

		String anio = "2017";
		double duracion = 4.24;
		String genero = "Hip-Hop";
		String url = "https://www.youtube.com/watch?v=auAIsRn0mPM&pp=ygUKZGUgcGUgYSBwYQ%3D%3D";

		File archivo = new File("src/Resources/Musica/De Pe a Pa - Granuja.mp3");

		Cancion ariaMath = new Cancion(nombre, codigo, album, caratulaToString, anio, duracion, genero , url, archivo);

		MetodosUsuario.storify.setCancion(ariaMath);

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
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos crea una instancia de la canción "Freaks - Surf Curse" y la asigna al objeto Cancion en 
	 * la clase IUsuario.storify. Luego, carga y muestra la vista DatosCancion.fxml en una nueva ventana cuando se produce 
	 * un clic en el botón correspondiente. Antes de mostrar la ventana, inicializa el controlador de la vista llamando al método init.
	 */
	@FXML
	void freaks(ActionEvent event) {

		String nombre = "Freaks - Surf Curse";
		String codigo = "34567";
		String album = "Demos";

		ImageIcon caratula = new ImageIcon("src/Resources/Freaks.jpg");
		String caratulaToString = caratula.toString();

		String anio = "2013";
		double duracion = 2.28;
		String genero = "Indie Pop";
		String url = "https://www.youtube.com/watch?v=NfMegACVJQw&pp=ygUGZnJlYWtz";

		File archivo = new File("src/Resources/Musica/Freaks - Surf Curse.mp3");

		Cancion ariaMath = new Cancion(nombre, codigo, album, caratulaToString, anio, duracion, genero , url, archivo);

		MetodosUsuario.storify.setCancion(ariaMath);

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
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos crea una instancia de la canción "Ghost Boy - Lil Peep" y la asigna al objeto Cancion en 
	 * la clase IUsuario.storify. Luego, carga y muestra la vista DatosCancion.fxml en una nueva ventana cuando se produce 
	 * un clic en el botón correspondiente. Antes de mostrar la ventana, inicializa el controlador de la vista llamando al método init.
	 */
	@FXML
	void ghostBoy(ActionEvent event) {

		String nombre = "Ghost Boy - Lil peep";
		String codigo = "2334";
		String album = "La luna";
		ImageIcon caratula = new ImageIcon("src/Resources/Ghost-Boy.jpg");
		String caratulaToString = caratula.toString();
		String anio = "2017";
		double duracion = 4.54;
		String genero = "Trap";
		String url = "https://www.youtube.com/watch?v=NiWFVHbB_Eo&pp=ygUSZ2hvc3QgYm95IGxpbCBwZWVw";

		File archivo = new File("src/Resources/Musica/Lil Peep - Ghost Boy.mp3");

		Cancion ghostBoy = new Cancion(nombre, codigo, album, caratulaToString, anio, duracion, genero , url, archivo);

		MetodosUsuario.storify.setUsuario(usuario);
		MetodosUsuario.storify.setCancion(ghostBoy);

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
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos crea una instancia de la canción "Help - The Beatles" y la asigna al objeto Cancion en la 
	 * clase IUsuario.storify. Luego, carga y muestra la vista DatosCancion.fxml en una nueva ventana cuando se produce un clic 
	 * en el botón correspondiente. Antes de mostrar la ventana, inicializa el controlador de la vista llamando al método init.
	 */
	@FXML
	void helpBeatles(ActionEvent event) {

		String nombre = "Help - The Beatles";
		String codigo = "123124325";
		String album = "Help!";

		ImageIcon caratula = new ImageIcon("src/Resources/Past.png");
		String caratulaToString = caratula.toString();

		String anio = "1965";
		double duracion = 2.21;
		String genero = "Pop Rock";
		String url = "https://www.youtube.com/watch?v=2Q_ZzBGPdqE&pp=ygUQaGVscCB0aGUgYmVhdGxlcw%3D%3D";

		File archivo = new File("src/Resources/Musica/Help - The Beatles.mp3");

		Cancion ariaMath = new Cancion(nombre, codigo, album, caratulaToString, anio, duracion, genero , url, archivo);

		MetodosUsuario.storify.setCancion(ariaMath);

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
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos crea una instancia de la canción "Me gustas - Nicolai Fella" y la asigna al objeto Cancion 
	 * en la clase IAdmin.storify. Luego, carga y muestra la vista DatosCancion.fxml en una nueva ventana cuando se produce un 
	 * clic en el botón correspondiente. Antes de mostrar la ventana, inicializa el controlador de la vista llamando al método init.
	 */
	@FXML
	void meGustas(ActionEvent event) {

		String nombre = "Me gustas - Nicolai Fella";
		String codigo = "434";
		String album = "Querido Frankie";

		ImageIcon caratula = new ImageIcon("src/Resources/Querido Frankie.jpg");
		String caratulaToString = caratula.toString();

		String anio = "2011";
		double duracion = 4.10;
		String genero = "Rap";
		String url = "https://www.youtube.com/watch?v=PWbn9MuDnwo&pp=ygUJbWUgZ3VzdGFz";

		File archivo = new File("src/Resources/Musica/Me Gustas - Nicolai Fella.mp3");

		Cancion ariaMath = new Cancion(nombre, codigo, album, caratulaToString, anio, duracion, genero , url, archivo);

		MetodosAdmin.storify.setCancion(ariaMath);

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
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos crea una instancia de la canción "Serenata de Amor - Jaime R. Echavarria" y la asigna al 
	 * objeto Cancion en la clase IUsuario.storify. Luego, carga y muestra la vista DatosCancion.fxml en una nueva ventana 
	 * cuando se produce un clic en el botón correspondiente. Antes de mostrar la ventana, inicializa el controlador de la 
	 * vista llamando al método init.
	 */
	@FXML
	void serenataDeAmor(ActionEvent event) {

		String nombre = "Serenata de Amor - Jaime R. Echavarria";
		String codigo = "34345";
		String album = "Vida y Obra";

		ImageIcon caratula = new ImageIcon("src/Resources/Serenata de amor.jpg");
		String caratulaToString = caratula.toString();

		String anio = "1977";
		double duracion = 4.20;
		String genero = "Bolero";
		String url = "https://www.youtube.com/watch?v=jPtq91gB8rU&pp=ygUQc2VyZW5hdGEgZGUgYW1vcg%3D%3D";

		File archivo = new File("src/Resources/Musica/Serenata de Amor - Jaime R Echavarria.mp3");

		Cancion ariaMath = new Cancion(nombre, codigo, album, caratulaToString, anio, duracion, genero , url, archivo);

		MetodosUsuario.storify.setCancion(ariaMath);

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
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos crea una instancia de la canción "Sin medir distancia - diomedes d" y la asigna al objeto 
	 * Cancion en la clase IUsuario.storify. Luego, carga y muestra la vista DatosCancion.fxml en una nueva ventana cuando 
	 * se produce un clic en el botón correspondiente. Antes de mostrar la ventana, inicializa el controlador de la vista llamando al método init.
	 */
	@FXML
	void sinMedirDistancias(ActionEvent event) {

		String nombre = "Sin medir distancia - diomedes d";
		String codigo = "3242";
		String album = "orgullosa";

		ImageIcon caratula = new ImageIcon("src/Resources/Sin medir distancia.jpg");
		String caratulaToString = caratula.toString();

		String anio = "1986";
		double duracion = 4.55;
		String genero = "vallenato";
		String url = "https://www.youtube.com/watch?v=lAITJiV8xbQ&pp=ygUTc2luIG1lZGlyIGRpc3RhbmNpYQ%3D%3D";

		File archivo = new File("src/Resources/Musica/Sin Medir Distancias - Diomedes Diaz.mp3");

		Cancion ariaMath = new Cancion(nombre, codigo, album, caratulaToString, anio, duracion, genero , url, archivo);

		MetodosUsuario.storify.setCancion(ariaMath);

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
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos crea una instancia de la canción "16 Lines - lil peep" y la asigna al objeto Cancion 
	 * en la clase IAdmin.storify. Luego, carga y muestra la vista DatosCancion.fxml en una nueva ventana cuando se produce 
	 * un clic en el botón correspondiente. Antes de mostrar la ventana, inicializa el controlador de la vista llamando al método init.
	 */
	@FXML
	void sixteenLines(ActionEvent event) {

		String nombre = "16 Lines - lil peep";
		String codigo = "45555";
		String album = "Come Over When You're Sober, Pt. 2";

		ImageIcon caratula = new ImageIcon("src/Resources/16 Lines.jpg");
		String caratulaToString = caratula.toString();

		String anio = "2017";
		double duracion = 4;
		String genero = "trap";
		String url = "https://www.youtube.com/watch?v=DxNt7xV5aII&pp=ygURMTYgbGluZXMgbGlsIHBlZXA%3D";

		File archivo = new File("src/Resources/Musica/16 Lines - Lil Peep.mp3");

		Cancion ariaMath = new Cancion(nombre, codigo, album, caratulaToString, anio, duracion, genero , url, archivo, false);

		MetodosAdmin.storify.setCancion(ariaMath);

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
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos crea una instancia de la canción "Stupid Love History - Canserbero" y la asigna al objeto 
	 * Cancion en la clase IUsuario.storify. Luego, carga y muestra la vista DatosCancion.fxml en una nueva ventana cuando se 
	 * produce un clic en el botón correspondiente. Antes de mostrar la ventana, inicializa el controlador de la vista llamando al método init.
	 */
	@FXML
	void stupidLoveHistory(ActionEvent event) {

		String nombre = "Stupid Love History - Canserbero";
		String codigo = "3444565";
		String album = "Apa y Can";

		ImageIcon caratula = new ImageIcon("src/Resources/Stupid Love History.jpg");
		String caratulaToString = caratula.toString();

		String anio = "2013";
		double duracion = 4.43;
		String genero = "Hip-Hop";
		String url = "https://www.youtube.com/watch?v=wmef2TEPGgo&pp=ygURc3R1cGlkIGxvdmUgc3Rvcnk%3D";

		File archivo = new File("src/Resources/Musica/Stupid Love History - Canserbero.mp3");

		Cancion ariaMath = new Cancion(nombre, codigo, album, caratulaToString, anio, duracion, genero , url, archivo);

		MetodosUsuario.storify.setCancion(ariaMath);

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
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos crea una instancia de la canción "Ultima Carta - Prince Royce" y la asigna al objeto Cancion 
	 * en la clase IUsuario.storify. Luego, carga y muestra la vista DatosCancion.fxml en una nueva ventana cuando se produce 
	 * un clic en el botón correspondiente. Antes de mostrar la ventana, inicializa el controlador de la vista llamando al método init.
	 */
	@FXML
	void ultimaCarta(ActionEvent event) {

		String nombre = "Ultima Carta - Prince Royce";
		String codigo = "34524653";
		String album = "Prince Royce";

		ImageIcon caratula = new ImageIcon("src/Resources/Prince.jpg");
		String caratulaToString = caratula.toString();

		String anio = "2010";
		double duracion = 4;
		String genero = "Bachata";
		String url = "https://www.youtube.com/watch?v=lEJOgeDL2rE&pp=ygUMdWx0aW1hIGNhcnRh";

		File archivo = new File("src/Resources/Musica/Ultima Carta - Prince Royce.mp3");

		Cancion ariaMath = new Cancion(nombre, codigo, album, caratulaToString, anio, duracion, genero , url, archivo);

		MetodosUsuario.storify.setCancion(ariaMath);

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
			Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	 * Este controlador de eventos verifica si el usuario tiene canciones eliminadas y muestra la lista de canciones 
	 * eliminadas en una nueva ventana si hay canciones eliminadas. Si no hay canciones eliminadas, muestra un mensaje 
	 * de información al usuario.
	 */
	@FXML
	void verInserciones(ActionEvent event) {


		if(usuario.getCancionesEliminadas() == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Atención");
			alert.setContentText("Primero elimine una canción de su lista de canciones.");
			alert.showAndWait();
		}else {
			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CancionesUsuarioEliminadas.fxml"));
				Parent root = loader.load();

				CancionesUsuarioEliminadas controlador = loader.getController();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				controlador.init();
				stage.initModality(Modality.APPLICATION_MODAL); 
				stage.setTitle("Lista de Canciones Eliminadas");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	//_____________________________________________________________________________________________________________________


	/*
	 * Este controlador de eventos verifica si el usuario tiene una lista de canciones y muestra la lista de canciones en 
	 * una nueva ventana si la lista contiene al menos una canción. Si la lista está vacía o no existe, muestra un mensaje 
	 * de información al usuario.
	 */
	@FXML
	void verListaCanciones(ActionEvent event) {


		if(usuario.getListaCanciones().isEmpty()) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Atención");
			alert.setContentText("Primero agrega una cancion a tu lista de canciones.");
			alert.showAndWait();

		}else {

			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ListaCancionesUsuario.fxml"));
				Parent root = loader.load();

				ListaCancionesUsuario controlador = loader.getController();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				controlador.init();
				stage.initModality(Modality.APPLICATION_MODAL); 
				stage.setTitle("Lista de Canciones del Usuario");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
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
	//_____________________________________________________________________________________________________________________


	/*
	 * El método init establece el texto del nombre de usuario en la vista y aplica estilos al campo de texto de búsqueda.
	 */
	public void init() {
		labelNombre.setText(usuario.getUsername().toUpperCase());

		txtBuscar.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  2px 2px 2px 2px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  50px; -fx-border-radius:  50px");
	}
	//_____________________________________________________________________________________________________________________


	/*
	 * El método volverEvent carga la vista del menú principal y muestra la ventana correspondiente, cerrando la ventana actual.
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
	//_____________________________________________________________________________________________________________________


	/*
	 * El proceso comienza al ejecutar la función buscar cuando se activa un evento de acción, como hacer clic en un botón.
	 * Se obtiene el texto de búsqueda del campo de texto.
	 * Se realizan comprobaciones para determinar el tipo de búsqueda: canción, artista o atributo.Si es una búsqueda de canción:
	 * Se recorre la lista de canciones y se compara el nombre o código de cada canción con el texto de búsqueda.
	 * Si se encuentra una coincidencia, se realiza lo siguiente:
	 * Se guarda la canción encontrada en una variable.
	 * Se muestra un mensaje de éxito.Se carga una nueva vista (FXML) para mostrar los detalles de la canción.Se cierra la ventana actual.
	 * Si es una búsqueda de artista:
	 * Se realiza una búsqueda del artista en función del texto ingresado.
	 * Si se encuentra el artista, se realiza lo siguiente:
	 * Se guarda el artista encontrado en una variable.
	 * Se muestra un mensaje.
	 * Se carga una nueva vista para mostrar la lista de canciones del artista.
	 * Se cierra la ventana actual.
	 * Si es una búsqueda por atributo:
	 * Se realiza una búsqueda de canciones y artistas que coincidan con el atributo ingresado.
	 * Si se encuentran canciones, se realiza lo siguiente:
	 * Se guarda la lista de canciones encontradas en una variable.
	 * Se carga una nueva vista para mostrar la lista de canciones por atributo.
	 * Se cierra la ventana actual.
	 * Si se encuentra un artista, se realiza lo siguiente:
	 * Se guarda el artista encontrado en una variable.
	 * Se carga una nueva vista para mostrar la lista de artistas por atributo.
	 * Se cierra la ventana actual.
	 * Si no se encuentran coincidencias en ninguna de las búsquedas anteriores, se muestra un mensaje informativo.
	 * En caso de que ocurra algún error durante el proceso de carga de vistas, se muestra un mensaje de error correspondiente.
	 */
	@FXML
	void buscar(ActionEvent event) {

		String buscador = this.txtBuscar.getText();

		if(comprobacionCancion(buscador)) {

			for (int i = 0; i < MetodosAdmin.storify.getListaCanciones().size(); i++) {

				System.out.println(MetodosAdmin.storify.getListaCanciones().get(i));

				if(MetodosAdmin.storify.getListaCanciones().get(i).getNombre().equals(buscador) ||
						MetodosAdmin.storify.getListaCanciones().get(i).getCodigo().equals(buscador)){

					Cancion cancionEncontrada = MetodosAdmin.storify.getListaCanciones().get(i);

					MetodosUsuario.storify.setCancion(cancionEncontrada);

					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Atención");
					alert.setContentText("Canción encontrada con exito");
					alert.showAndWait();

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
						Stage myStage = (Stage) this.btnBuscar.getScene().getWindow();
						myStage.close();

					} catch (IOException ex) {

						alert = new Alert(Alert.AlertType.ERROR);
						alert.setHeaderText(null);
						alert.setTitle("Error");
						alert.setContentText(ex.getMessage());
						alert.showAndWait();
					}

				}
			}
		}else {

			if(busquedadArtista(buscador) != null) {

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Atención");
				alert.setContentText("bien");

				alert.showAndWait();

				Artista artistaEncontrado = busquedadArtista(buscador);

				MetodosAdmin.storify.setArtista(artistaEncontrado);

				try {

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/VerListaCancionesArtista.fxml"));
					Parent root = loader.load();

					VerListaCancionesArtistaController controlador = loader.getController();
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					controlador.init();
					stage.initModality(Modality.APPLICATION_MODAL); 
					stage.setTitle("Lista de Canciones del Artista");
					stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
					Stage myStage = (Stage) this.btnBuscar.getScene().getWindow();
					myStage.close();

				} catch (IOException ex) {

					alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Error");
					alert.setContentText(ex.getMessage());
					alert.showAndWait();
				}	
			}else {

				if (MetodosAdmin.storify.buscarPorAtributoCancion(buscador) != null) {
					List<Cancion> cancionesEncontradas = MetodosAdmin.storify.buscarPorAtributoCancion(buscador);
					if (!cancionesEncontradas.isEmpty()) {
						MetodosAdmin.storify.getListaCancionesPorAtributo().addAll(cancionesEncontradas);

						try {
							System.out.println(cancionesEncontradas);

							FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/ListaCancionesPorAtributo.fxml"));
							Parent root = loader.load();

							ListaCancionesPorAtributoController controlador = loader.getController();
							Scene scene = new Scene(root);
							Stage stage = new Stage();
							controlador.init();
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.setTitle("Lista de Canciones Dado un Atributo");
							stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
							stage.setScene(scene);
							stage.setResizable(false);
							stage.show();
							Stage myStage = (Stage) this.btnGhostB.getScene().getWindow();
							myStage.close();

						} catch (IOException ex) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setHeaderText(null);
							alert.setTitle("Error");
							alert.setContentText(ex.getMessage());
							alert.showAndWait();
						}
					}else if(busquedadArtistaPorAtributo(buscador) != null) {

						System.out.println("melo");
						Artista artistaEncontradoPorAtributo = (Artista) busquedadArtistaPorAtributo(buscador);

						MetodosAdmin.storify.setArtista(artistaEncontradoPorAtributo);

						try {

							FXMLLoader loader = new FXMLLoader(getClass().getResource("/View2/ListaArtistasPorAtributo.fxml"));
							Parent root = loader.load();

							Scene scene = new Scene(root);
							Stage stage = new Stage();

							stage.initModality(Modality.APPLICATION_MODAL); 
							stage.setTitle("Lista de Artistas Dado un Atriubuto de Ellos");
							stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/LogoSpo.png")));
							stage.setScene(scene);
							stage.setResizable(false);
							stage.show();
							Stage myStage = (Stage) this.btnBuscar.getScene().getWindow();
							myStage.close();

						} catch (IOException ex) {

							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setHeaderText(null);
							alert.setTitle("Error");
							alert.setContentText(ex.getMessage());
							alert.showAndWait();
						}	
					}else{
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setHeaderText(null);
						alert.setTitle("Atención");
						alert.setContentText("No se encontraron coincidencias");
						alert.showAndWait();
					}
				}
			}
		}
	}

	//_____________________________________________________________________________________________________________________


	/*
	 * Esta función verifica si una canción se encuentra en la lista de canciones, comparando su nombre y 
	 * código con un texto de búsqueda. Retorna true si se encuentra la canción y false si no se encuentra.
	 */
	private boolean comprobacionCancion(String buscador) {

		for (int i = 0; i < MetodosAdmin.storify.getListaCanciones().size(); i++) {

			System.out.println(MetodosAdmin.storify.getListaCanciones().get(i));

			if(MetodosAdmin.storify.getListaCanciones().get(i).getNombre().equals(buscador) ||
					MetodosAdmin.storify.getListaCanciones().get(i).getCodigo().equals(buscador)){

				return true;
			}
		}
		return false;
	}
	//_____________________________________________________________________________________________________________________


	/*
	 * Esta función privada busca un artista en base a un texto de búsqueda y retorna el artista encontrado utilizando 
	 * el método buscarArtista de la clase IAdmin.
	 */
	private Artista busquedadArtista(String buscador) {
		return MetodosAdmin.buscarArtista(buscador);
	}
	//_____________________________________________________________________________________________________________________


	/*
	 * Esta función privada busca un artista por atributo en base a un texto de búsqueda y retorna el artista 
	 * encontrado utilizando el método buscarArtistaPorAtributo de la clase IAdmin. El tipo de dato de retorno es Object.
	 */
	private Object busquedadArtistaPorAtributo(String buscador) {
		return MetodosAdmin.buscarArtistaPorAtributp(buscador);
	}
	//_____________________________________________________________________________________________________________________


	/*
	 * Este código es un método informacionBuscar que se ejecuta cuando se detecta un evento de ratón. Sin embargo, 
	 * el contenido del método está comentado, por lo que no tiene un efecto en la ejecución del programa. Es probable 
	 * que haya sido comentado para desactivar temporalmente la funcionalidad de mostrar una ventana emergente de información 
	 * utilizando la clase Alert.
	 */
	@FXML
	void informacionBuscar(MouseEvent event) {
		//		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		//		alert.setHeaderText(null);
		//		alert.setTitle("Información");
		//		alert.setContentText("Recuerde que para buscar una cancion, se puede hacer por nombre completo o por su codigo");
		//		alert.showAndWait();
	}
	//_____________________________________________________________________________________________________________________

}
