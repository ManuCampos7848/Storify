package Model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;

import Model.ListaCircularCancionesUsuario.ListaCircularCancion;
import Model.ListaCircularCancionesUsuario.NodoCancion;


public class Storify {

	/*
	 * Atributos que tiene la clase padre
	 */
	private Usuario<?> usuario;
	private Cancion cancion;
	private Cancion cancionAdmin;
	private Artista artista;

	private HashMap<String, Usuario> listaUsuarios = new HashMap<>();
	private ArrayList<Cancion> listaCanciones = new ArrayList<>();
	private ArrayList<Admin> admin = new ArrayList<Admin>();
	private static ArtistaArbolBinario listaArtistas = new ArtistaArbolBinario();

	private ArrayList<Cancion> listaCancionesPorAtributo = new ArrayList<>();
	private ArrayList<Artista> listaArtistasPorAtributo = new ArrayList<>();

	//_________________________________________________________________________________________________

	
	/*
	 * Metodos Get and Set
	 */
	public ArrayList<Cancion> getListaCancionesPorAtributo() {
		return listaCancionesPorAtributo;
	}

	public ArrayList<Artista> getListaArtistasPorAtributo() {
		return listaArtistasPorAtributo;
	}

	public void setListaArtistasPorAtributo(ArrayList<Artista> listaArtistasPorAtributo) {
		this.listaArtistasPorAtributo = listaArtistasPorAtributo;
	}

	public void setListaCancionesPorAtributo(ArrayList<Cancion> listaCancionesPorAtributo) {
		this.listaCancionesPorAtributo = listaCancionesPorAtributo;
	}

	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	public Cancion getCancion() {
		return cancion;
	}

	public Cancion getCancionAdmin() {
		return cancionAdmin;
	}

	public void setCancionAdmin(Cancion cancionAdmin) {
		this.cancionAdmin = cancionAdmin;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public void setCancion(Cancion cancion) {
		this.cancion = cancion;
	}

	public ArtistaArbolBinario getListaArtistas() {
		return listaArtistas;
	}

	public void setListaArtistas(ArtistaArbolBinario listaArtistas) {
		this.listaArtistas = listaArtistas;
	}

	public Usuario<?> getUsuario() {
		return usuario;
	}

	public ArrayList<Admin> getAdmin() {
		return admin;
	}

	public void setUsuario(Usuario<?> usuario) {
		this.usuario = usuario;
	}

	public void setAdmin(ArrayList<Admin> admin) {
		this.admin = admin;
	}

	public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}

	public HashMap<String, Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(HashMap<String, Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	//_________________________________________________________________________________________________
	

	/*
	 * Declara el método "verificarCuentaUsuario" que toma un objeto "Usuario" llamado "usuarioLogin" como parámetro y devuelve un objeto "Usuario" o null.
	 * La variable "usuarioARetornar" se inicializa con el valor del usuario correspondiente al nombre de usuario proporcionado en el objeto "usuarioLogin" desde la lista de usuarios. Parece que "listaUsuarios" es una colección o mapa que contiene usuarios indexados por su nombre de usuario.
	 * Se verifica si "usuarioARetornar" no es nulo y si la contraseña del objeto "usuarioARetornar" coincide con la contraseña proporcionada en el objeto "usuarioLogin". Para hacer esto, probablemente se esté llamando a un método "verificarContrasenia" en el objeto "usuarioARetornar".
	 * Si la verificación anterior es exitosa, es decir, el usuario existe en la lista de usuarios y la contraseña coincide, se devuelve el objeto "usuarioARetornar".
	 * Si la verificación falla en cualquiera de los casos anteriores, se devuelve null.
	 */
	public Usuario verificarCuentaUsuario(Usuario usuarioLogin) {


		Usuario usuarioARetornar = listaUsuarios.get(usuarioLogin.getUsername());

		if(usuarioARetornar != null && usuarioARetornar.verificarContrasenia(usuarioLogin.getContrasenia())) {
			return usuarioARetornar;
		}

		return null;
	}
	//_________________________________________________________________________________________________


	/*
	 * La función "verificarCuentaAdmin" recibe un objeto "Admin" llamado "adminLogin" como parámetro y devuelve un valor booleano (verdadero o falso).
	 * El código realiza un recorrido a través de una colección de objetos "Admin" llamada "admin" utilizando un bucle for-each. En cada iteración del bucle,
	 * se compara el nombre de usuario y la contraseña del objeto "adminLoger" actual con el nombre de usuario y la contraseña del objeto "adminLogin" pasado como argumento.
	 * Si los nombres de usuario y las contraseñas son iguales, la función devuelve verdadero, lo que implica que la cuenta de administrador ha sido verificada exitosamente.
	 * Si no se encuentra ninguna coincidencia después de recorrer todos los objetos "admin", la función devuelve falso, lo que significa que la cuenta de administrador no ha sido verificada.
	 */
	public boolean verificarCuentaAdmin(Admin adminLogin) {

		for (Admin adminLoger : admin) {
			if(adminLoger.getUsername().equals(adminLogin.getUsername()) &&
					adminLoger.getContrasenia().equals(adminLogin.getContrasenia())){

				return true;

			}
		}
		return false;
	}
	//_________________________________________________________________________________________________


	/*
	 * Se crea un objeto "Admin" llamado "ad" y se le asigna un nombre de usuario y una contraseña.
	 * El objeto "ad" se agrega a una colección llamada "admin".
	 * Se crean varios objetos "Artista" y se insertan en una lista llamada "listaArtistas".
	 * Se recorre la lista de artistas utilizando el método "recorrerInorden" y pasando la raíz de la lista como argumento.
	 * Se crea un objeto "Usuario" y se agrega a un mapa llamado "listaUsuarios" utilizando el nombre de usuario como clave.
	 * Se crean varios objetos "Cancion" con diferentes propiedades (nombre, ID, género, URL, archivo, etc.) y se agregan a una lista llamada "listaCanciones". También se actualizan las variables que cuentan la cantidad de canciones por género.
	 * Se imprime "bre" en la consola.
	 * Se recorre la lista de canciones y se imprime el nombre de cada canción en la consola.
	 */
	public void init() {

		Admin ad = new Admin();
		ad.setUsername("admin");
		ad.setContrasenia("admin");
		admin.add(ad);

		Artista autorPrueba = new Artista("Lil Peep", "0129", "USA", false, null);
		listaArtistas.insertarArtista(autorPrueba);


		autorPrueba = new Artista("The Weekend", "2847", "USA", false, null);
		listaArtistas.insertarArtista(autorPrueba);

		autorPrueba = new Artista("Ed Sheeran", "3959", "USA", false, null);
		listaArtistas.insertarArtista(autorPrueba);


		listaArtistas.recorrerInorden(listaArtistas.getRaiz());

		Usuario<Object> usuarioNuevo = new Usuario<Object>("darly", "correo@gmail.com", "123", null, new ArrayList<>(), null);


		listaUsuarios.put(usuarioNuevo.getUsername(), usuarioNuevo);

		Cancion cancionNueva = new Cancion("Ghost Boy - Lil Peep", "2334", "La luna", 
				new ImageIcon("src/Resources/Ghost-Boy.jpg").toString(), "2017", 4.54, "Trap",
				"https://www.youtube.com/watch?v=NiWFVHbB_Eo&pp=ygUSZ2hvc3QgYm95IGxpbCBwZWVw",new File("src/Resources/Musica/Lil Peep - Ghost Boy.mp3"));
		listaCanciones.add(cancionNueva);
	

		// ____________________________________________________________________


		String url = "https://www.youtube.com/watch?v=lAITJiV8xbQ&pp=ygUTc2luIG1lZGlyIGRpc3RhbmNpYQ%3D%3D";
		File archivo = new File("src/Resources/Musica/Sin Medir Distancias - Diomedes Diaz.mp3");

		Cancion sinMedirDistancias = new Cancion("Sin Medir Distancia - Diomedes Diaz", "3242", "Orgullosa", 
				new ImageIcon("src/Resources/Sin medir distancia.jpg").toString(), "1986",
				4.55, "Vallenato" , url, archivo);
		listaCanciones.add(sinMedirDistancias);

		//_______________________________________


		String url16Lines = "https://www.youtube.com/watch?v=DxNt7xV5aII&pp=ygURMTYgbGluZXMgbGlsIHBlZXA%3D";
		File archivo16Lines = new File("src/Resources/Musica/16 Lines - Lil Peep.mp3");

		Cancion sixteenLines = new Cancion("16 Lines - Lil Peep", "45555", "Come Over When You're Sober, Pt. 2", 
				new ImageIcon("src/Resources/16 Lines.jpg").toString(), "2017",
				4, "Trap" , url16Lines, archivo16Lines);
		listaCanciones.add(sixteenLines);

		//______________________________________________________


		String urlStupidLove = "https://www.youtube.com/watch?v=wmef2TEPGgo&pp=ygURc3R1cGlkIGxvdmUgc3Rvcnk%3D";
		File archivoStupidLove= new File("src/Resources/Musica/Stupid Love History - Canserbero.mp3");

		Cancion stupidLoveHistory = new Cancion("Stupid Love History - Canserbero", "3444565", "Apa y Can", 
				new ImageIcon("src/Resources/Stupid Love History.jpg").toString(), "2013",
				4.43, "Hip-Hop" , urlStupidLove, archivoStupidLove);
		listaCanciones.add(stupidLoveHistory);

		//_______________________________________________________________

		String urlUltimaCarta = "https://www.youtube.com/watch?v=lEJOgeDL2rE&pp=ygUMdWx0aW1hIGNhcnRh";
		File archivoUltimaCarta= new File("src/Resources/Musica/Ultima Carta - Prince Royce.mp3");

		Cancion ultimaCarta = new Cancion("Ultima Carta - Prince Royce", "34524653", "Prince Royce", 
				new ImageIcon("src/Resources/Prince.jpg").toString(), "2010",
				4, "Bachata" , urlUltimaCarta, archivoUltimaCarta);
		listaCanciones.add(ultimaCarta);

		//______________________________________________________________

		String urlSerenataDeAmor = "https://www.youtube.com/watch?v=jPtq91gB8rU&pp=ygUQc2VyZW5hdGEgZGUgYW1vcg%3D%3D";
		File archivoSerenataDeAmor = new File("src/Resources/Musica/Serenata de Amor - Jaime R Echavarria.mp3");

		Cancion serenataDeAmor = new Cancion("Serenata de amor - Jaime R. Echavarria", "34345", "Vida y Obra", 
				new ImageIcon("src/Resources/Serenata de amor.jpg").toString(), "1977",
				4.20, "Bolero" , urlSerenataDeAmor, archivoSerenataDeAmor);
		listaCanciones.add(serenataDeAmor);

		//_______________________________________________________________

		String urlMeGustas = "https://www.youtube.com/watch?v=PWbn9MuDnwo&pp=ygUJbWUgZ3VzdGFz";
		File archivoMeGustas = new File("src/Resources/Musica/Me Gustas - Nicolai Fella.mp3");

		Cancion meGustas = new Cancion("Me Gustas - Nicolai Fella", "434", "Querido Frankie", 
				new ImageIcon("src/Resources/Querido Frankie.jpg").toString(), "2011",
				4.10, "Rap" , urlMeGustas, archivoMeGustas);
		listaCanciones.add(meGustas);

		//________________________________________________________________

		String urlHelp = "https://www.youtube.com/watch?v=2Q_ZzBGPdqE&pp=ygUQaGVscCB0aGUgYmVhdGxlcw%3D%3D";
		File archivoHelp = new File("src/Resources/Musica/Help - The Beatles.mp3");

		Cancion help = new Cancion("Help - The Beatles", "123124325", "Help!", 
				new ImageIcon("src/Resources/Past.png").toString(), "1965",
				2.21, "Pop Rock" , urlHelp, archivoHelp);
		listaCanciones.add(help);

		//________________________________________________________________

		String urlFreaks = "https://www.youtube.com/watch?v=NfMegACVJQw&pp=ygUGZnJlYWtz";
		File archivoFreaks = new File("src/Resources/Musica/Freaks - Surf Curse.mp3");

		Cancion freaks = new Cancion("Freaks - Surf Curse", "34567", "Demos", 
				new ImageIcon("src/Resources/Freaks.jpg").toString(), "2013",
				2.28, "Indie Pop" , urlFreaks, archivoFreaks);
		listaCanciones.add(freaks);

		//________________________________________________________________

		String urlDePeAPa = "https://www.youtube.com/watch?v=auAIsRn0mPM&pp=ygUKZGUgcGUgYSBwYQ%3D%3D";
		File archivoDePeAPa = new File("src/Resources/Musica/De Pe a Pa - Granuja.mp3");

		Cancion dePeAPa = new Cancion("De Pe a Pa - Granuja", "0129", "Circulo Vicioso", 
				new ImageIcon("src/Resources/Granuja.jpg").toString(), "2017",
				4.24, "Hip-hop" , urlDePeAPa, archivoDePeAPa);
		listaCanciones.add(dePeAPa);

		//_________________________________________________________________

		String urlBigPoppa = "https://www.youtube.com/watch?v=QceVTChhlJM&pp=ygUJYmlnIHBvcHBh";
		File archivoBigPoppa = new File("src/Resources/Musica/Big Poppa - The Notorious Big.mp3");

		Cancion bigPoppa = new Cancion("Big Poppa - The Notorius BIG", "13569", "Ready to Die", 
				new ImageIcon("src/Resources/Big Poppa.jpg").toString(), "1994",
				4.21, "Hip-hop" , urlBigPoppa, archivoBigPoppa);
		listaCanciones.add(bigPoppa);

		//_________________________________________________________________

		String urlAriaMath = "https://www.youtube.com/watch?v=atgjKEgSqSU&pp=ygUJYXJpYSBtYXRo";
		File archivoAriaMath  = new File("src/Resources/Musica/C418  Aria Math Minecraft.mp3");

		Cancion ariaMath = new Cancion("Aria Math - C418", "790155", "soundtrack minecraft", 
				new ImageIcon("src/Resources/C4-18.jpg").toString(), "2011",
				3.57, "Relajante" , urlAriaMath, archivoAriaMath);
		listaCanciones.add(ariaMath);

		System.out.println("bre");

		for (int i = 0; i < listaCanciones.size(); i++) {
			System.out.println( "\n" + listaCanciones.get(i).getNombre());
		}
	}
	//_________________________________________________________________________________________________


	public boolean verificarCorreoYUsernameUsuario(Usuario usuarioNuevo) {


		for(Map.Entry<String, Usuario> entry : listaUsuarios.entrySet()) {

			Usuario correoDelUsuario = entry.getValue();

			if(usuarioNuevo.getEmail().equals(correoDelUsuario.getEmail())) {
				return true;
			}

		}

		return false;
	}
	//_________________________________________________________________________________________________


	/*
	 * El método recibe un objeto de tipo "Usuario<Object>" llamado "usuarioNuevo" como parámetro.
	 * El objeto "usuarioNuevo" se agrega al mapa "listaUsuarios" utilizando el nombre de usuario como clave y el objeto completo como valor.
	 * Se imprime en la consola el mensaje "Usuario nuevo agregado".
	 */
	public void crearUsuario(Usuario<Object> usuarioNuevo) throws IOException {

		listaUsuarios.put(usuarioNuevo.getUsername(), usuarioNuevo);

		System.out.println("Usuario nuevo agregado");
	}
	//_________________________________________________________________________________________________


	/*
	 * El método recibe dos parámetros: un objeto de tipo "Cancion" llamado "cancion2" y un objeto de tipo "Usuario<?>", que se indica como un usuario genérico y se denomina "usuario2".
	 * Se verifica si el objeto "usuario2" es nulo. Si es así, se imprime en la consola el mensaje "El objeto usuario2 es nulo" y se retorna de inmediato, finalizando la ejecución del método.
	 * Si el objeto "usuario2" no es nulo, se accede a la lista de canciones del usuario utilizando el método "getListaCanciones()" y se invoca el método "agregarCancionSinReemplazar()" de esa lista, pasando como argumento la canción "cancion2". Este método se encarga de agregar la canción a la lista de canciones del usuario, evitando reemplazar canciones con el mismo nombre.
	 * Se imprime en la consola el mensaje "listo".
	 */
	public void agregarCancionALista(Cancion cancion2, Usuario<?> usuario2) {

		if (usuario2 == null) {
			System.out.println("El objeto usuario2 es nulo");
			return;
		}
		
		

		usuario2.getListaCanciones().add(cancion2);

		System.out.println("listo");
	}
	//_________________________________________________________________________________________________


	/*
	 * El método recibe dos parámetros: un objeto de tipo "Cancion" llamado "cancion2" y un objeto de tipo "Usuario<?>", que se indica como un usuario genérico y se denomina "usuario2".
	 * Se verifica si el objeto "usuario2" es nulo. Si es así, se imprime en la consola el mensaje "El objeto usuario2 es nulo" y se retorna de inmediato, finalizando la ejecución del método.
	 * Si el objeto "usuario2" no es nulo, se verifica si la lista de canciones favoritas del usuario, obtenida mediante el método "getCancionesFavoritas()", es nula. Si es nula, se crea una nueva instancia de "ArrayList<Cancion>" y se asigna como la lista de canciones favoritas del usuario.
	 * Después, se agrega la canción "cancion2" a la lista de canciones favoritas del usuario utilizando el método "add()".
	 * Por último, se imprime en la consola el mensaje "listo".
	 */
	public void agregarCancionAListaFavoritas(Cancion cancion2, Usuario<?> usuario2) {

		if (usuario2 == null) {
			System.out.println("El objeto usuario2 es nulo");
			return;
		}

		if (usuario2.getCancionesFavoritas() == null) {
			usuario2.setCancionesFavoritas(new ArrayList<Cancion>());
		}

		usuario2.getCancionesFavoritas().add(cancion2);
		System.out.println("listo");

	}
	//_________________________________________________________________________________________________


	/*
	 * cancionSeleccionada" y un objeto de tipo "Usuario<?>", que se indica como un usuario genérico y se denomina "usuario2".
	 * Se verifica si la lista de canciones eliminadas del usuario, obtenida mediante el método "getCancionesEliminadas()", es nula. Si es nula, se crea una nueva instancia de "ArrayList<Cancion>" y se asigna como la lista de canciones eliminadas del usuario.
	 * Después, se agrega la canción "cancionSeleccionada" a la lista de canciones eliminadas del usuario utilizando el método "add()".
	 */
	public void eliminarCancionListaCanciones(Cancion cancionSeleccionada, Usuario<?> usuario2) {	

		if (usuario.getCancionesEliminadas() == null) {

			usuario.setCancionesEliminadas(new ArrayList<Cancion>());
		}
		
		usuario.getCancionesEliminadas().add(cancionSeleccionada);


	}
	//_________________________________________________________________________________________________


	/*
	 * El método recibe un parámetro de tipo "Cancion" llamado "cancion2".
	 * Se utiliza un bloque try-catch para manejar posibles excepciones. En este caso, se capturan las excepciones de tipo "URISyntaxException" e "IOException".
	 * Dentro del bloque try, se crea una instancia de la clase "Desktop" utilizando el método estático "getDesktop()" de la clase "Desktop".
	 * A continuación, se crea una instancia de la clase "URI" a partir de la URL del video de YouTube de la canción proporcionada. Se utiliza el atributo "url" de la canción, asumiendo que contiene la URL válida del video de YouTube.
	 * Finalmente, se llama al método "browse()" de la instancia de "Desktop", pasando la URI como argumento. Esto abre la URL en el navegador web predeterminado del sistema operativo.
	 */
	public void abrirEnYoutube(Cancion cancion2) {

		try {
			// Crear una instancia de la clase Desktop
			Desktop desktop = Desktop.getDesktop();

			// Crear una URI a partir de la URL del video de YouTube
			URI uri = new URI(cancion.getUrl());

			// Abrir la URI en el navegador predeterminado
			desktop.browse(uri);

		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}

	}
	//_________________________________________________________________________________________________


	/*
	 * El método recibe un parámetro de tipo "Artista" llamado "nuevoArtista".
	 * Utilizando el objeto "listaArtistas", que parece ser una lista o estructura de datos que contiene artistas, se llama al método "insertarArtista(nuevoArtista)" para agregar el nuevo artista a la lista.
	 * Luego, se llama al método "recorrerInorden(listaArtistas.getRaiz())" para recorrer la lista de artistas en orden. Este método 
	 * probablemente implementa un recorrido inorden en la estructura de datos de la lista de artistas, lo cual implica visitar los nodos en el siguiente orden: primero el subárbol izquierdo, luego el nodo actual y finalmente el subárbol derecho.
	 * Es posible que el método imprima en la consola información sobre cada artista mientras realiza el recorrido inorden.
	 */
	public void agregarArtista(Artista nuevoArtista) {
		listaArtistas.insertarArtista(nuevoArtista);
		listaArtistas.recorrerInorden(listaArtistas.getRaiz());
	}
	//_________________________________________________________________________________________________


	/*
	 * El método recibe un parámetro de tipo "Cancion" llamado "cancionNueva".
	 * Utilizando un bucle "for", itera a través de la lista de canciones llamada "listaCanciones" para comparar cada canción con la canción nueva.
	 * Dentro del bucle, se compara el archivo de cada canción en la lista con el archivo de la canción nueva utilizando el método "getArchivo()". 
	 * Si los archivos son iguales, significa que la canción ya existe en la lista y se retorna "true" para indicar su existencia.
	 * Si el bucle termina sin encontrar una coincidencia de archivos, se retorna "false" para indicar que la canción no existe en la lista.
	 */
	public boolean verificarExistenciaCancion(Cancion cancionNueva) {

		for (int i = 0; i < listaCanciones.size(); i++) {
			if(listaCanciones.get(i).getArchivo().equals(cancionNueva.getArchivo())) {
				return true;
			}
		}
		return false;
	}
	//_________________________________________________________________________________________________


	/*
	 * El método recibe un parámetro de tipo String llamado "buscador", que representa el criterio de búsqueda.
	 * Se crea una nueva ArrayList llamada "cancionesAretornar" para almacenar las canciones que coinciden con el criterio de búsqueda.
	 * Utilizando un bucle "for", itera a través de la lista de canciones llamada "listaCanciones" para verificar cada canción.
	 * Dentro del bucle, se comparan diferentes atributos de cada canción con el criterio de búsqueda utilizando los métodos correspondientes. 
	 * Los atributos que se comparan son el álbum, el género, la duración y el año de la canción.
	 * Si alguno de los atributos coincide con el criterio de búsqueda, se agrega la canción a la lista "cancionesAretornar" utilizando el método "add()".
	 * Una vez finalizado el bucle, se devuelve la lista "cancionesAretornar" que contiene las canciones que coinciden con el criterio de búsqueda.
	 */
	public ArrayList<Cancion> buscarPorAtributoCancion(String buscador) {

		ArrayList<Cancion> cancionesAretornar = new ArrayList<>();

		for (int i = 0; i < listaCanciones.size(); i++) {

			if(listaCanciones.get(i).getAlbum().equals(buscador) || listaCanciones.get(i).getGenero().equals(buscador) ||
					String.valueOf(listaCanciones.get(i).getDuracion()).equals(buscador) || listaCanciones.get(i).getAnio().equals(buscador)) {

				cancionesAretornar.add(listaCanciones.get(i));

			}
		}
		return cancionesAretornar;
	}
	//_________________________________________________________________________________________________
	
	/*
	 * Metodo que verifica que no haya canciones duplicadas en la lista de canciones
	 * favoritas del usuario
	 */
	public boolean comprobarExistenciaCancionFavoritos(Cancion cancionSeleccionada) {
		for (int i = 0; i < usuario.getCancionesFavoritas().size(); i++) {
			if(usuario.getCancionesFavoritas().get(i).equals(cancionSeleccionada))
				return true;
		}
		return false;
	}

	public boolean verificarCancionRepetida(Cancion cancion2) {
		for (Cancion c : usuario.getListaCanciones()) {
			if(c.getCodigo().equals(cancion2.getCodigo())) {
				return true;
			}
		}
		return false;
	}

	public boolean agregarCancion(Cancion cancionNueva) {
	    for (int i = 0; i < listaCanciones.size(); i++) {
	        if (listaCanciones.get(i).getUrl().equals(cancionNueva.getUrl())) {
	            return false;
	        }
	    }
	    listaCanciones.add(cancionNueva);
	    return true;
	}

	public void eliminarCancionListaGeneral(Cancion cancion) {
	    Iterator<Cancion> iterator = listaCanciones.iterator();
	    while (iterator.hasNext()) {
	        Cancion c = iterator.next();
	        if (c.getCodigo().equals(cancion.getCodigo())) {
	            iterator.remove();
	            break; // Asumiendo que los códigos son únicos, puedes salir del bucle después de eliminar
	        }
	    }
	}

	public static boolean verificarExistenciaArtista(Artista artista) {
	    Artista artistaBuscado = (Artista) listaArtistas.buscarArtistaPorAtributo(artista.getCodigo());
	    return artistaBuscado != null;
	}


}