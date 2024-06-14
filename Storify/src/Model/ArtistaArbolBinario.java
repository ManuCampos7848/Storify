	package Model;

public class ArtistaArbolBinario {

	// Declaración de la raiz
	private Artista raiz; // Raíz del árbol binario
	//__________________________________________________________________________________________________

	// Metodo get and set de la raiz del arbol binario
	public Artista getRaiz() {
		return raiz;
	}
	public void setRaiz(Artista raiz) {
		this.raiz = raiz;
	}
	//__________________________________________________________________________________________________

	/*
	 * Método para insertar un nuevo artista en el árbol binario
	 */
	public void insertarArtista(Artista nuevoArtista) {
		if (raiz == null) {
			raiz = nuevoArtista; // Si el árbol está vacío, el nuevo artista se convierte en la raíz
		} else {
			insertarArtistaRecursivo(raiz, nuevoArtista); // Llamada al método recursivo de inserción
		}
	}
	//__________________________________________________________________________________________________


	/*
	 * Método recursivo para insertar un nuevo artista en el árbol binario
	 */
	private void insertarArtistaRecursivo(Artista nodo, Artista nuevoArtista) {
		// Comparar el nuevo artista con el nodo actual para determinar si se inserta a la izquierda o a la derecha
		if (nuevoArtista.getNombre().compareToIgnoreCase(nodo.getNombre()) < 0) {
			// Insertar a la izquierda si el nuevo artista es menor
			if (nodo.hijoIzquierdo == null) {
				nodo.hijoIzquierdo = nuevoArtista;
			} else {
				insertarArtistaRecursivo(nodo.hijoIzquierdo, nuevoArtista);
			}
		} else {
			// Insertar a la derecha si el nuevo artista es mayor o igual
			if (nodo.hijoDerecho == null) {
				nodo.hijoDerecho = nuevoArtista;
			} else {
				insertarArtistaRecursivo(nodo.hijoDerecho, nuevoArtista);
			}
		}
	}
	//__________________________________________________________________________________________________

	/*
	 * Método para buscar un artista en el árbol binario por nombre
	 */
	public Artista buscarArtista(String nombre) {
		return buscarArtistaRecursivo(raiz, nombre); // Llamada al método recursivo de búsqueda
	}
	//__________________________________________________________________________________________________

	
	/*
	 * Esta función invoca una búsqueda recursiva de un artista por atributo y retorna el resultado de la búsqueda.
	 */
	public Object buscarArtistaPorAtributo(String buscador) {
		return buscarArtistaPorAtributoRecursivo(raiz, buscador);
	}
	//__________________________________________________________________________________________________

	
	/*
	 * Esta función busca recursivamente un artista por atributo en un árbol. Retorna el nodo del 
	 * artista encontrado si se encuentra, o null si el artista no está presente en el árbol.
	 */
	private Object buscarArtistaPorAtributoRecursivo(Artista nodo, String buscador) {

		if (nodo == null) {
			return null; // El artista no se encontró en el árbol
		}

		if (buscador.equalsIgnoreCase(nodo.getNacionalidad())) {
			return nodo; // El artista se encontró en el árbol
		}

		if (buscador.equalsIgnoreCase(nodo.getCodigo())) {
			return nodo; // El artista se encontró en el árbol
		}

		if (buscador.equalsIgnoreCase(String.valueOf(nodo.isArtista_O_Grupo()))) {
			return nodo; // El artista se encontró en el árbol
		}

		// Comparar el buscador con otros atributos del nodo
		int comparacion = buscador.compareToIgnoreCase(nodo.getNacionalidad());
		if (comparacion < 0) {
			// Buscar en el hijo izquierdo si el buscador es menor
			return buscarArtistaPorAtributoRecursivo(nodo.hijoIzquierdo, buscador);
		} else {
			// Buscar en el hijo derecho si el buscador es mayor
			return buscarArtistaPorAtributoRecursivo(nodo.hijoDerecho, buscador);
		}
	}
	//__________________________________________________________________________________________________

	/*
	 * Método recursivo para buscar un artista en el árbol binario por nombre
	 */
	private Artista buscarArtistaRecursivo(Artista nodo, String nombre) {
		if (nodo == null) {
			return null; // El artista no se encontró en el árbol
		}
		// Comparar el nombre del nodo actual con el nombre buscado
		int comparacion = nombre.compareToIgnoreCase(nodo.getNombre());
		if (comparacion == 0) {
			return nodo; // El artista se encontró en el árbol
		} else if (comparacion < 0) {
			// Buscar en el hijo izquierdo si el nombre buscado es menor
			return buscarArtistaRecursivo(nodo.hijoIzquierdo, nombre);
		} else {
			// Buscar en el hijo derecho si el nombre buscado es mayor
			return buscarArtistaRecursivo(nodo.hijoDerecho, nombre);
		}
	}
	//__________________________________________________________________________________________________

	
	/*
	 * Método para eliminar un artista del árbol binario por nombre
	 */
	public void eliminarArtista(String nombre) {
		raiz = eliminarArtistaRecursivo(raiz, nombre); // Llamada al método recursivo de eliminación
	}
	//__________________________________________________________________________________________________

	
	/*
	 * Método recursivo para eliminar un artista del árbol binario por nombre
	 */
	private Artista eliminarArtistaRecursivo(Artista nodo, String nombre) {
		if (nodo == null) {
			return nodo; // El artista no se encontró en el árbol, no se realiza ninguna eliminación
		}

		// Comparar el nombre del nodo actual con el nombre buscado
		int comparacion = nombre.compareToIgnoreCase(nodo.getNombre());

		if (comparacion < 0) {
			// El nombre buscado es menor, buscar en el hijo izquierdo
			nodo.hijoIzquierdo = eliminarArtistaRecursivo(nodo.hijoIzquierdo, nombre);
		} else if (comparacion > 0) {
			// El nombre buscado es mayor, buscar en el hijo derecho
			nodo.hijoDerecho = eliminarArtistaRecursivo(nodo.hijoDerecho, nombre);
		} else {
			// Se encontró el artista a eliminar en el nodo actual
			if (nodo.hijoIzquierdo == null) {
				// Caso 1: El nodo tiene solo hijo derecho o no tiene hijos
				return nodo.hijoDerecho;
			} else if (nodo.hijoDerecho == null) {
				// Caso 2: El nodo tiene solo hijo izquierdo
				return nodo.hijoIzquierdo;
			} else {
				// Caso 3: El nodo tiene ambos hijos
				// Obtener el sucesor inorden (nodo más a la izquierda del subárbol derecho)
				nodo.setNombre(minimoValor(nodo.hijoDerecho));
				// Eliminar el sucesor inorden del subárbol derecho
				nodo.hijoDerecho = eliminarArtistaRecursivo(nodo.hijoDerecho, nodo.getNombre());
			}
		}

		return nodo;
	}
	//__________________________________________________________________________________________________

	/*
	 * Método para obtener el valor mínimo (nombre) en un subárbol a partir de un nodo dado
	 */
	private String minimoValor(Artista nodo) {
		String valorMinimo = nodo.getNombre();
		while (nodo.hijoIzquierdo != null) {
			valorMinimo = nodo.hijoIzquierdo.getNombre();
			nodo = nodo.hijoIzquierdo;
		}
		return valorMinimo;
	}
	//__________________________________________________________________________________________________

	
	/*
	 * Esta función realiza un recorrido inorden de un árbol binario de búsqueda de artistas, imprimiendo el nombre de los artistas en el orden adecuado.
	 */
	public void recorrerInorden(Artista nodo) {
		if (nodo != null) {
			recorrerInorden(nodo.hijoIzquierdo); // Recorre el subárbol izquierdo
			System.out.println(nodo.toString()); // Imprime el nombre del artista en el nodo actual
			recorrerInorden(nodo.hijoDerecho); // Recorre el subárbol derecho
		}
	}
	//__________________________________________________________________________________________________
}
