package Model;

import java.io.IOException;

public class MetodosUsuario {

	public static Storify storify = new Storify(); // Crear una instancia de la clase Storify

	public static void init() {
	    storify.init(); // Iniciar la inicialización de la instancia de Storify
	}

	public static Usuario<?> verificarCuenta(Usuario<?> usuarioLogin) {
	    return storify.verificarCuentaUsuario(usuarioLogin); // Verificar la cuenta del usuario que inicia sesión
	}

	public static boolean verificarCorreoYUsernameUsuario(Usuario<?> usuarioNuevo) {
	    return storify.verificarCorreoYUsernameUsuario(usuarioNuevo); // Verificar si el correo del usuario nuevo ya existe en el sistema
	}

	public static void crearUsuario(Usuario<Object> usuarioNuevo) throws IOException {
	    storify.crearUsuario(usuarioNuevo); // Crear un nuevo usuario en el sistema
	}

	public static Usuario<?> obtenerCliente() {
	    return storify.getUsuario(); // Obtener el usuario cliente actual
	}

	public static Cancion obtenerCancion() {
	    return storify.getCancion(); // Obtener la canción actual
	}

	public static void agregarCancionAListaFavoritas(Cancion cancion, Usuario<?> usuario) {
	    storify.agregarCancionAListaFavoritas(cancion, usuario); // Agregar una canción a la lista de canciones favoritas de un usuario
	}

	public static void agregarCancionALista(Cancion cancion, Usuario<?> usuario) {
	    storify.agregarCancionALista(cancion, usuario); // Agregar una canción a una lista de canciones de un usuario
	}

	public static void eliminarCancionListaCanciones(Cancion cancionSeleccionada, Usuario<?> usuario) {
	    storify.eliminarCancionListaCanciones(cancionSeleccionada, usuario); // Eliminar una canción de una lista de canciones de un usuario
	}

	public static void abrirEnYoutube(Cancion cancion) {
	    storify.abrirEnYoutube(cancion); // Abrir una canción en YouTube
	}

	public static Cancion obtenerCancionAdmin() {
		return storify.getCancionAdmin();
	}

	
	/*
	 * Este metodo va a la clase central Storify y verifica la lista de canciones favoritas
	 * del usuario con el fin de ver si hay la canción seleccionada, existe en la lista de canciones
	 * favoritas
	 */
	public static boolean comprobarExistenciaCancionFavoritos(Cancion cancionSeleccionada) {
		return storify.comprobarExistenciaCancionFavoritos(cancionSeleccionada);
	}

	public static boolean verificarCancionRepetida(Cancion cancion) {
		return storify.verificarCancionRepetida(cancion);
	}

}
