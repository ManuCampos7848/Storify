package Controller2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import Controller.MenuUsuarioController;
import Model.Artista;
import Model.Cancion;
import Model.MetodosAdmin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ReproducirListaCancionesArtistaController implements Initializable{

	// Atributo que permite saber de que usuario se habla
	private Artista artista = MetodosAdmin.obtenerArtista();
	//____________________________________________________________________________________________________________________________


	// Atributos para reproducir la canción
	@FXML
	private Button btnNext;

	@FXML
	private Button btnPlay;

	@FXML
	private Slider volumeSlider;

	@FXML
	private ProgressBar songProgressBar;
	
	@FXML
	private Label songLabel;

	@FXML
	private Button btnVolver;

	@FXML
	private Button btnPause;

	@FXML
	private Button btnReset;

	private Media media;
	private MediaPlayer mediaPlayer;

	private Timer timer;
	private TimerTask task;

	private ArrayList<File> songs;

	private int songNumber;

	private boolean running;
	//____________________________________________________________________________________________________________________________

	// Lista que añade las canciones favoritas del usuario 
	ArrayList<Cancion> todasLasCanciones = artista.getListaCanciones().getAcceso().obtenerCanciones();
	//____________________________________________________________________________________________________________________________


	/*
	 * Se establece el valor inicial del control deslizante (volumeSlider) en 50.
	 * 
	 * Se establece el estilo del control deslizante mediante la propiedad style con el valor de fondo interior de control (-fx-control-inner-background)
	 * en #00d856 y color de fondo (-fx-background-color) en #000.
	 * 
	 * Se deshabilita el botón btnPlay y se habilita el botón btnPause, pero lo oculta.
	 * 
	 * Se crea una lista de archivos (songs) y se agrega un archivo (directory) obtenido de un objeto cancion.
	 * 
	 * Se crea un objeto Media con la URL del primer archivo de la lista (songs) y se guarda en media.
	 * 
	 * Se crea un objeto MediaPlayer con media y se guarda en mediaPlayer.
	 * 
	 * Se agrega un ChangeListener al valor de la propiedad value del control deslizante volumeSlider, que actualiza el volumen del reproductor de medios 
	 * (mediaPlayer) en función del valor del control deslizante.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		volumeSlider.setValue(50);

		volumeSlider.setStyle("-fx-control-inner-background:  #00d856; -fx-background-color:  #000;");

		btnPlay.setDisable(false);
		btnPause.setDisable(true);
		btnPause.setVisible(false);

		songs = new ArrayList<File>();

		for (Cancion cancion : todasLasCanciones) {
			songs.add(cancion.getArchivo());
		}

		media = new Media(songs.get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);


		songLabel.setText(songs.get(songNumber).getName());

		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

				mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);			
			}
		});
		
		songProgressBar.setStyle("-fx-accent: #00FF00;");
	}
	//____________________________________________________________________________________________________________________________


	/*
	 * Se llama al método beginTimer(), que probablemente esté relacionado con el inicio de un temporizador para realizar alguna acción específica durante la reproducción de la música. Sin ver el código completo, no se puede determinar con certeza qué hace exactamente este método.
	 * 
	 * Se establece el volumen del reproductor de medios (mediaPlayer) utilizando el valor actual del control deslizante (volumeSlider.getValue() * 0.01), que se convierte a un valor de volumen válido entre 0 y 1.
	 * 
	 * Se llama al método play() en el objeto mediaPlayer para iniciar la reproducción del archivo de música.
	 * 
	 * Se deshabilita el botón btnPlay y se oculta.
	 * 
	 * Se habilita el botón btnPause y se muestra.
	 */
	public void playMedia() {

		beginTimer();
		mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
		mediaPlayer.play();

		btnPlay.setDisable(true);
		btnPlay.setVisible(false);

		btnPause.setDisable(false);
		btnPause.setVisible(true);
	}
	//____________________________________________________________________________________________________________________________


	/*
	 * Se llama al método cancelTimer(), que probablemente esté relacionado con la cancelación de un temporizador previamente iniciado durante la reproducción de la música. Sin ver el código completo, no se puede determinar con certeza qué hace exactamente este método.
	 * 
	 * Se llama al método pause() en el objeto mediaPlayer para pausar la reproducción del archivo de música
	 * 
	 * Se habilita el botón btnPlay y se muestra.
	 * 
	 * Se deshabilita el botón btnPause y se oculta.
	 */
	public void pauseMedia() {

		cancelTimer();
		mediaPlayer.pause();

		btnPlay.setDisable(false);
		btnPlay.setVisible(true);

		btnPause.setDisable(true);
		btnPause.setVisible(false);
	}
	//____________________________________________________________________________________________________________________________


	/*
	 * Se llama al método seek() en el objeto mediaPlayer con un argumento de Duration.seconds(0), lo que establece la posición de reproducción del archivo de música en cero segundos, reiniciando así la reproducción desde el principio.
	 */
	public void resetMedia() {
		songProgressBar.setProgress(0);
		mediaPlayer.seek(Duration.seconds(0));
	}
	//____________________________________________________________________________________________________________________________


	/*
	 * Se verifica si songNumber (que representa el índice de la canción actual en la lista de canciones) es mayor que 0, lo que significa que hay una canción anterior en la lista.
	 * 
	 * Si hay una canción anterior en la lista, se decrementa songNumber en 1.
	 * 
	 * Se detiene la reproducción del mediaPlayer actual utilizando el método stop().
	 * 
	 * Se crea un nuevo objeto Media con la ruta del archivo de música de la canción anterior en la lista, utilizando songs.get(songNumber).toURI().toString().
	 * 
	 * Se crea un nuevo objeto MediaPlayer con el nuevo objeto Media creado.
	 * 
	 * Se actualiza la etiqueta de la canción actual en la interfaz de usuario utilizando songLabel.setText(songs.get(songNumber).getName())
	 * 
	 * Se llama al método playMedia() para comenzar la reproducción de la nueva canción.
	 */
	public void previousMedia() {

		if(songNumber > 0) {

			songNumber--;

			mediaPlayer.stop();

			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);

			songLabel.setText(songs.get(songNumber).getName());

			playMedia();
		}
		else {

			songNumber = songs.size() - 1;

			mediaPlayer.stop();

			if(running) {

				cancelTimer();
			}

			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);

			songLabel.setText(songs.get(songNumber).getName());

			playMedia();
		}
	}
	//____________________________________________________________________________________________________________________________


	/*
	 * Se verifica si songNumber (que representa el índice de la canción actual en la lista de canciones) es mayor que 0, lo que significa que hay una canción anterior en la lista.
	 * 
	 * Si hay una canción anterior en la lista, se decrementa songNumber en 1.
	 * 
	 * Se detiene la reproducción del mediaPlayer actual utilizando el método stop().
	 * 
	 * Se crea un nuevo objeto Media con la ruta del archivo de música de la canción anterior en la lista, utilizando songs.get(songNumber).toURI().toString().
	 * 
	 * Se crea un nuevo objeto MediaPlayer con el nuevo objeto Media creado.
	 * 
	 * Se actualiza la etiqueta de la canción actual en la interfaz de usuario utilizando songLabel.setText(songs.get(songNumber).getName()).
	 * 
	 * Se llama al método playMedia() para comenzar la reproducción de la nueva canción.
	 */
	public void nextMedia() {

		if(songNumber < songs.size() - 1) {

			songNumber++;

			mediaPlayer.stop();

			if(running) {

				cancelTimer();
			}

			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);

			songLabel.setText(songs.get(songNumber).getName());

			playMedia();
		}
		else {

			songNumber = 0;

			mediaPlayer.stop();

			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);

			songLabel.setText(songs.get(songNumber).getName());

			playMedia();
		}
	}
	//____________________________________________________________________________________________________________________________


	/*
	 * El método beginTimer() crea un temporizador (Timer) en Java y define una tarea (TimerTask) que se ejecutará periódicamente a intervalos fijos utilizando scheduleAtFixedRate().
	 *  Esta tarea se encarga de verificar si la reproducción del mediaPlayer ha alcanzado el final de la canción actual comparando el tiempo de reproducción actual (current) con la duración total
	 *   de la canción (end).
	 * 
	 * Si la reproducción ha alcanzado el final de la canción, es decir, si la división de current/end es igual a 1 (lo que significa que se ha reproducido el 100% de la canción), entonces se llama al 
	 * método cancelTimer() para detener el temporizador.
	 */
	public void beginTimer() {

		timer = new Timer();

		task = new TimerTask() {

			public void run() {

				double current = mediaPlayer.getCurrentTime().toSeconds();
				double end = media.getDuration().toSeconds();
				songProgressBar.setProgress(current/end);
				
				if(current/end == 1) {

					cancelTimer();
				}
			}
		};

		timer.scheduleAtFixedRate(task, 0, 1000);
	}
	//____________________________________________________________________________________________________________________________


	/*
	 * El método cancelTimer() se encarga de cancelar el temporizador (Timer) y detener la tarea (TimerTask) asociada a él. 
	 * Para ello, llama al método cancel() del objeto timer. Además, establece la variable booleana running como false para indicar que el 
	 * temporizador ha sido detenido.
	 */
	public void cancelTimer() {

		running = false;
		timer.cancel();
	}
	//____________________________________________________________________________________________________________________________


	/*
	 * Llama al método pauseMedia() para pausar la reproducción de medios.
	 * 
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
	 * Cierra la ventana actual (myStage) utilizando myStage.close() para volver al menú anterior.
	 * 
	 * En caso de que ocurra una excepción durante la carga de la vista, muestra un cuadro de diálogo de error con un mensaje de error.
	 */
	@FXML
	void volverEvent(ActionEvent event) {

		pauseMedia();

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MenuUsuario.fxml"));
			Parent root = loader.load();

			MenuUsuarioController controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			controlador.init();
			stage.initModality(Modality.APPLICATION_MODAL); 
			stage.setTitle("Menu del Cliente");
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

	public void init() {

	}
	//____________________________________________________________________________________________________________________________
}