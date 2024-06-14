package Model;

import java.io.File;
import java.util.Objects;

public class Cancion {

	/*
	 * Atributos
	 */
	private String nombre;         // Nombre de la canción
	private String codigo;         // Código de la canción
	private String album;          // Álbum de la canción
	private String caratula;       // Carátula de la canción
	private String anio;           // Año de lanzamiento de la canción
	private double duracion;       // Duración de la canción
	private String genero;         // Género de la canción
	private String url;            // URL de la canción
	private File archivo;         // Archivo de la canción
	private boolean estadoRehacer = false;  // Estado de rehacer de la canción
	//_________________________________________________________________
	
	/*
	 * Metodos Constructor
	 */

	public Cancion() {
		super();
	}
	public Cancion(String nombre, String codigo, String album, String caratula, String anio, double duracion,
			String genero, String url, File archvioCancion) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.album = album;
		this.caratula = caratula;
		this.anio = anio;
		this.duracion = duracion;
		this.genero = genero;
		this.url = url;
		this.archivo = archvioCancion;
	}
	
	//_________________________________________________________________

	
	


	public Cancion(Cancion cancion) {
		// TODO Auto-generated constructor stub
	}
	public Cancion(String nombre, String codigo, String album, String caratula, String anio, double duracion,
			String genero, String url, File archivo, boolean estadoRehacer) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.album = album;
		this.caratula = caratula;
		this.anio = anio;
		this.duracion = duracion;
		this.genero = genero;
		this.url = url;
		this.archivo = archivo;
		this.estadoRehacer = false;
	}
	/*
	 * Metodos Get and Set
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean getEstadoRehacer() {
		return estadoRehacer;
	}
	
	public void setEstadoRehacer(boolean estadoRehacer) {
		this.estadoRehacer = estadoRehacer;
	}
	
	public String getAlbum() {
		return album;
	}

	public File getArchivo() {
		return archivo;
	}
	
	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	//_________________________________________________________________

	/*
	 * Metodo toString
	 */
	@Override
	public String toString() {
		return "Cancion [nombre=" + nombre + ", codigo=" + codigo + ", album=" + album + ", caratula=" + caratula
				+ ", anio=" + anio + ", duracion=" + duracion + ", genero=" + genero + ", url=" + url + "]";
	}
	//_________________________________________________________________
	
	/*
	 * Metodos hasCode and equals
	 */
	@Override
	public int hashCode() {
		return Objects.hash(album, anio, archivo, caratula, codigo, duracion, estadoRehacer, genero, nombre, url);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancion other = (Cancion) obj;
		return Objects.equals(album, other.album) && Objects.equals(anio, other.anio)
				&& Objects.equals(archivo, other.archivo) && Objects.equals(caratula, other.caratula)
				&& Objects.equals(codigo, other.codigo)
				&& Double.doubleToLongBits(duracion) == Double.doubleToLongBits(other.duracion)
				&& estadoRehacer == other.estadoRehacer && Objects.equals(genero, other.genero)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(url, other.url);
	}
	//_________________________________________________________________
}

