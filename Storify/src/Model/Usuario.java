package Model;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario<T> {

	private Cancion cancion = MetodosUsuario.obtenerCancion();
	private Usuario<?> usuario = MetodosUsuario.obtenerCliente();
	
	/*
	 * Atributos
	 */
	private String username;
	private String email;
	private String contrasenia;
	private ArrayList<Cancion> listaCanciones;
	private ArrayList<Cancion> cancionesFavoritas = new ArrayList<>();
	private ArrayList<Cancion> cancionesEliminadas = new ArrayList<>();
	//__________________________________________________________________________________
	
	/*
	 * Metodos constructor
	 */

	public Usuario(String username, String email, String contrasenia, ArrayList<Cancion> listaCanciones,
			ArrayList<Cancion> cancionesFavoritas, ArrayList<Cancion> cancionesEliminadas) {
		super();
		this.username = username;
		this.email = email;
		this.contrasenia = contrasenia;
		this.listaCanciones = new ArrayList<Cancion>();
		this.cancionesFavoritas = cancionesFavoritas;
		this.cancionesEliminadas = cancionesEliminadas;
	}


	public ArrayList<Cancion> getCancionesFavoritas() {
		return cancionesFavoritas;
	}

	public void setCancionesFavoritas(ArrayList<Cancion> cancionesFavoritas) {
		this.cancionesFavoritas = cancionesFavoritas;
	}

	public Usuario(String username, String email, String contrasenia, ArrayList<Cancion> cancionesFavoritas) {
		super();
		this.username = username;
		this.email = email;
		this.contrasenia = contrasenia;
		this.cancionesFavoritas = cancionesFavoritas;
	}

	
	public Usuario(String username, String contrasenia) {
		super();
		this.username = username;
		this.contrasenia = contrasenia;
	}

	public Usuario() {}
	//__________________________________________________________________________________

	/*
	 * Metodos Get and Set
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	public ArrayList<Cancion> getCancionesEliminadas() {
		return cancionesEliminadas;
	}

	public void setCancionesEliminadas(ArrayList<Cancion> cancionesEliminadas) {
		this.cancionesEliminadas = cancionesEliminadas;
	}


	public void setListaCanciones(ArrayList<Cancion> listaCanciones2) {
		this.listaCanciones = listaCanciones2;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	//__________________________________________________________________________________

	/*
	 * Metodo toString 
	 */
	@Override
	public String toString() {
		return "Usuario: " + username + "\n"
				+ "Email: " + email;
	}
	//__________________________________________________________________________________

	/*
	 * Metodos hasCode and equals
	 */
	@Override
	public int hashCode() {
		return Objects.hash(contrasenia, email, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(contrasenia, other.contrasenia) && Objects.equals(email, other.email)
				&& Objects.equals(username, other.username);
	}
	//__________________________________________________________________________________

	
	/*
	 * Metodo que permite verificar si la contraseña pasada por la interfaz coincide con alguna de los usuarios registrados
	 */
	public boolean verificarContrasenia(String contrasenia2) {

		if(contrasenia2.equals(contrasenia)) {
			return true;
		}
		return false;
	}



}
