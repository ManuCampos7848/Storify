package Model;

public class MetodosAdmin {

	public static /*
	 * Forma de acceder a la clase central, es decir, la clase Storify con todos sus metodos
	 */
	Storify storify = new Storify();
	//___________________________________________________________________________________________________________
	
	
	/*
	 * Metodo init el cual quema datos como canciones, usuarios y artistas
	 */
	public static void init() {
		storify.init();
	}
	//___________________________________________________________________________________________________________

	
	/*
	 * Metodo que accede a la clase central Storify con el finde verificar si los datos ingresados por el usuario pertenecen al administrador
	 */
	public static boolean verificarAdministrador(Admin adminLogin) {
		return storify.verificarCuentaAdmin(adminLogin);
	}
	//___________________________________________________________________________________________________________

	
	/*
	 * Metodo que accede a la clase central Storify con el fin de agregar una cancion nueva
	 */
	public static boolean agregarCancion(Cancion cancionNueva) {
		return storify.agregarCancion(cancionNueva);
		 
	}
	//___________________________________________________________________________________________________________
	
	
	/*
	 * Metodo que accede a la clase central Storify con el fin de saber cual es la cancion en especifico que esta queriendo
	 * reproducir el administrador en turno
	 */
	public static Cancion obtenerCancionAdmin() {
		return storify.getCancionAdmin();
	}
	//___________________________________________________________________________________________________________

	
	/*
	 * Metodo que accede a la clase central Storify con el de agregar un artista nuevo
	 */
	public static void agregarArtista(Artista nuevoArtista) {
		storify.agregarArtista(nuevoArtista);
	}
	//___________________________________________________________________________________________________________

	
	/*
	 * Metodo que accede a la clase central Storify con el de saber de que artista en especifico se esta hablando
	 */
	public static Artista obtenerArtista() {
		return storify.getArtista();
	}
	//___________________________________________________________________________________________________________

	
	/*
	 * Metodo que accede a la clase central Storify con el fin de buscar un artista
	 */
	public static Artista buscarArtista(String buscador) {
		return storify.getListaArtistas().buscarArtista(buscador);
	}
	//___________________________________________________________________________________________________________

	
	/*
	 * Metodo que accede a la clase central Storify con el fin de buscar un artista dado un atributo
	 */
	public static Object buscarArtistaPorAtributp(String buscador) {
		return storify.getListaArtistas().buscarArtistaPorAtributo(buscador);
	}
	//___________________________________________________________________________________________________________
	
	/*
	 * Metodo que elimina una cancion de la lista de canciones general
	 */
	public static void eliminarCancionListaGeneral(Cancion cancion) {
		storify.eliminarCancionListaGeneral(cancion);
	}


	public static boolean verificarArtistaExistente(Artista artista) {
		return storify.verificarExistenciaArtista(artista);
	}
}
