package Controller2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Artista;
import Model.MetodosAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AgregarArtistaController implements Initializable{

	// Atributos
	@FXML
	private Button btnAgregarArtista;

	@FXML
	private Button btnVolver;

	@FXML
	private ComboBox<String> comboBox;

	@FXML
	private TextField txtCodigo;

	@FXML
	private TextField txtNacionalidad;

	@FXML
	private TextField txtNombreCancion;
	//_______________________________________________________________________________________________
	
	
	/*
	 * Se verifica si todos los campos necesarios (txtNombreCancion, txtCodigo, txtNacionalidad) están completos. Si alguno de los campos está vacío, se muestra un mensaje de error.
	 * Si todos los campos están completos, se obtienen los valores de los campos (nombre, codigo, nacionalidad) y se verifica si el artista es un grupo o un solista mediante el método verificarGrupoOSolista().
	 * Se crea un nuevo objeto Artista con los valores obtenidos y el resultado de la verificación anterior.
	 * Se llama al método agregarArtista de la clase IAdmin y se le pasa el nuevo artista como parámetro para agregarlo.
	 * Se muestra un mensaje de confirmación de que el artista ha sido agregado exitosamente.
	 * Se carga la vista del menú del administrador (MenuAdmin.fxml).
	 * Se crea y muestra una nueva ventana (Stage) con la escena del menú del administrador.
	 * Se cierra la ventana actual.
	 */
	@FXML
	void agregarArtista(ActionEvent event) {

		if(this.txtNombreCancion.getText().equals("") || this.txtCodigo.getText().equals("") ||
				this.txtNacionalidad.getText().equals("") || this.comboBox.getValue() == null) {


			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Complete todos los campos y elija una de las opciones sobre grupo o solista");
			alert.showAndWait();

		}else {

			String nombre = this.txtNombreCancion.getText();
			String codigo = this.txtCodigo.getText();
			String nacionalidad = this.txtNacionalidad.getText();

			boolean verificarArtistaOGrupo = verificarGrupoOSolista();

			Artista nuevoArtista = new Artista(nombre, codigo, nacionalidad, verificarArtistaOGrupo, null);

			if(!(MetodosAdmin.verificarArtistaExistente(nuevoArtista))) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("Este artista ya existe");
				alert.showAndWait();
			}else {
				MetodosAdmin.agregarArtista(nuevoArtista);


				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Confirmación");
				alert.setContentText("Artista agregado con exito");
				alert.showAndWait();

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

					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Error");
					alert.setContentText(ex.getMessage());
					alert.showAndWait();
				}
			}
			
		}
	}
	//_______________________________________________________________________________________________


	/*
	 * Metodo que verifica si el artista es solista o tiene grupo
	 */
	private boolean verificarGrupoOSolista() {

		if(this.comboBox.getValue().equals("Si")) {
			return true;
		}
		return false;
	}
	//_______________________________________________________________________________________________


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
	//_______________________________________________________________________________________________


	/*
	 * Se agrega "Si" y "No" como opciones al ComboBox (comboBox).
	 * Se aplican estilos de apariencia a los campos de texto txtNombreCancion, txtCodigo y txtNacionalidad. Estos estilos incluyen el color del texto, el color de fondo, el ancho del borde y el color del borde.
	 * Esta inicialización se realiza al cargar la vista correspondiente y se aplica a los elementos mencionados.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		comboBox.getItems().addAll("Si", "No");

		txtNombreCancion.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

		txtCodigo.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

		txtNacionalidad.setStyle("-fx-text-fill:  #00d856; -fx-background-color:  #282828; -fx-border-width:  0px 0px 2px 0px;"
				+ "-fx-border-color:  #00d856; -fx-background-radius:  -2px");

	}
	//_______________________________________________________________________________________________


}
