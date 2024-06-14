package Model;

import java.util.Objects;

public class Artista {

	/*
	 * Atributos
	 */
	private String nombre;
	private String codigo;
	private String nacionalidad;
	private boolean artista_O_Grupo;
	private ListaDobleCancionesArtista<Cancion> listaCanciones;
	
	// Atributos para el árbol binario
    public Artista hijoIzquierdo;
    public Artista hijoDerecho;
	//___________________________________________________________________
	
	/*
	 * Metodos Constructor
	 */
    
    
	public Artista(){}
	
	
	public Artista(String nombre, String codigo, String nacionalidad, boolean artista_O_Grupo) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.nacionalidad = nacionalidad;
		this.artista_O_Grupo = artista_O_Grupo;
	}


	public Artista(String nombre, String codigo, String nacionalidad, boolean artista_O_Grupo,
			ListaDobleCancionesArtista<Cancion> listaCanciones) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.nacionalidad = nacionalidad;
		this.artista_O_Grupo = artista_O_Grupo;
		this.listaCanciones = new ListaDobleCancionesArtista<Cancion>();
	}

	public Artista(String nombre, String codigo, String nacionalidad, boolean artista_O_Grupo,
			ListaDobleCancionesArtista<Cancion> listaCanciones, Artista hijoIzquierdo, Artista hijoDerecho) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.nacionalidad = nacionalidad;
		this.artista_O_Grupo = artista_O_Grupo;
		this.listaCanciones = listaCanciones;
		this.hijoIzquierdo = hijoIzquierdo;
		this.hijoDerecho = hijoDerecho;
	}

	//___________________________________________________________________
	
	
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
	public Artista getHijoIzquierdo() {
		return hijoIzquierdo;
	}

	public void setHijoIzquierdo(Artista hijoIzquierdo) {
		this.hijoIzquierdo = hijoIzquierdo;
	}

	public Artista getHijoDerecho() {
		return hijoDerecho;
	}

	public void setHijoDerecho(Artista hijoDerecho) {
		this.hijoDerecho = hijoDerecho;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public boolean isArtista_O_Grupo() {
		return artista_O_Grupo;
	}
	public void setArtista_O_Grupo(boolean artista_O_Grupo) {
		this.artista_O_Grupo = artista_O_Grupo;
	}
	public ListaDobleCancionesArtista<Cancion> getListaCanciones() {
		return listaCanciones;
	}
	public void setListaCanciones(ListaDobleCancionesArtista<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}
	//___________________________________________________________________
	
	/*
	 * Metodo toString
	 */
	@Override
	public String toString() {
		return "Autor [nombre=" + nombre + ", codigo=" + codigo + ", nacionalidad=" + nacionalidad
				+ ", artista_O_Grupo=" + artista_O_Grupo + ", listaCanciones=" + listaCanciones + "]";
	}
	//___________________________________________________________________
	
	
	/*
	 * Metodo hasCode and equals
	 */
	@Override
	public int hashCode() {
		return Objects.hash(artista_O_Grupo, codigo, listaCanciones, nacionalidad, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		return artista_O_Grupo == other.artista_O_Grupo && Objects.equals(codigo, other.codigo)
				&& Objects.equals(listaCanciones, other.listaCanciones)
				&& Objects.equals(nacionalidad, other.nacionalidad) && Objects.equals(nombre, other.nombre);
	}
	//___________________________________________________________________

	public int compareTo(String nombre2) {
		return 0;
	}
	
	
	// Clase que representa el nodo del árbol
	
}
