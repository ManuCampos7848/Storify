package Model;

public class Admin {

	private String username; // Variable de instancia para almacenar el nombre de usuario del administrador
	private String contrasenia; // Variable de instancia para almacenar la contraseña del administrador
	
	public Admin() {} // Constructor vacío de la clase Admin
	
	public Admin(String username, String contrasenia) { // Constructor con parámetros para inicializar el nombre de usuario y la contraseña del administrador
		super();
		this.username = username; // Asignar el valor del nombre de usuario proporcionado al atributo username
		this.contrasenia = contrasenia; // Asignar el valor de la contraseña proporcionada al atributo contrasenia
	}

	public String getUsername() { // Método para obtener el nombre de usuario del administrador
		return username;
	}

	public void setUsername(String username) { // Método para establecer el nombre de usuario del administrador
		this.username = username; // Asignar el valor del nombre de usuario proporcionado al atributo username
	}

	public String getContrasenia() { // Método para obtener la contraseña del administrador
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) { // Método para establecer la contraseña del administrador
		this.contrasenia = contrasenia; // Asignar el valor de la contraseña proporcionada al atributo contrasenia
	}
	
}
