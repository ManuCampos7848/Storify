package Model;

import java.util.ArrayList;

public class ListaCircularCancionesUsuario<T> {


	/*
	 * Atributio
	 */
	private NodoCancion primerNodo;
	//_____________________________________________________________________________________________________________________

	
	/*
	 * Este código elimina un nodo de una lista enlazada circular de canciones. Busca el nodo que contiene la canción seleccionada, 
	 * actualiza los enlaces de los nodos para eliminarlo y lo elimina de la lista. Si la lista está vacía o la canción no se 
	 * encuentra, no se realiza ninguna acción.
	 */
	public void eliminarCancionListaCanciones(Cancion cancionSeleccionada) {
		if (primerNodo == null) {
			// La lista está vacía, no hay nada que eliminar
			return;
		}
		else if (primerNodo.getSiguiente() == null) {
			// La lista solo tiene un elemento, se hace null el primer nodo
			primerNodo = null;
			return;
		}

		NodoCancion nodoActual = primerNodo;
		NodoCancion nodoAnterior = null;

		// Recorre la lista circular para encontrar el nodo de la canción seleccionada
		while (nodoActual != null) {
			if (nodoActual.getCancion().equals(cancionSeleccionada)) {
				// Se encontró la canción, se elimina el nodo
				if (nodoAnterior == null) {
					// Si el nodo anterior es null, significa que es el primer nodo de la lista
					// Se actualiza el primer nodo para que sea el siguiente nodo
					primerNodo = nodoActual.getSiguiente();
				} else {
					// Si el nodo anterior no es null, se actualiza su referencia al siguiente nodo
					nodoAnterior.setSiguiente(nodoActual.getSiguiente());
				}
				return; // Se encontró y eliminó la canción, se sale del bucle
			}

			// Se mueve al siguiente nodo
			nodoAnterior = nodoActual;
			nodoActual = nodoActual.getSiguiente();

			// Si se volvió al primer nodo, significa que se recorrió toda la lista y no se encontró la canción
			if (nodoActual == primerNodo) {
				break;
			}
		}
	}
	//_____________________________________________________________________________________________________________________


	//__________________________________Clase Nodo_________________________________________________________________________
	//_____________________________________________________________________________________________________________________
	public class NodoCancion {

		// Atributos
		private Cancion cancion;
		private NodoCancion siguiente;
		//__________________________________________________

		// Metodo Constructor
		public NodoCancion(Cancion cancion) {
			this.cancion = cancion;
			this.siguiente = null;
		}
		//__________________________________________________

		// Getters y setters para las variables de instancia
		public Cancion getCancion() {
			return cancion;
		}

		public void setCancion(Cancion cancion) {
			this.cancion = cancion;
		}

		public NodoCancion getSiguiente() {
			return siguiente;
		}

		public void setSiguiente(NodoCancion siguiente) {
			this.siguiente = siguiente;
		}
		//__________________________________________________
	}
	//_____________________________________________________________________________________________________________________
	//_____________________________________________________________________________________________________________________



	//_____________________________________________________Clase ListaCiruclarCancion____________________________________________________
	//_____________________________________________________________________________________________________________________
	public class ListaCircularCancion {

		/*
		 * Atributo
		 */
		private NodoCancion primerNodo;
		//_____________________________________________________________________________________________________________________


		/*
		 * Metodos Get and Set
		 */
		public NodoCancion getPrimerNodo() {
			return primerNodo;
		}

		public void setPrimerNodo(NodoCancion primerNodo) {
			this.primerNodo = primerNodo;
		}
		//_____________________________________________________________________________________________________________________
		
		
		/*
		 * Metodo constructor
		 */
		public ListaCircularCancion() {
			this.primerNodo = null;
		}
		//_____________________________________________________________________________________________________________________

		
		/*
		 * Metodo que comprueba si la lista circular esta vacia
		 */
		public boolean estaVacia() {
			return primerNodo == null;
		}
		//_____________________________________________________________________________________________________________________

		
		/*
		 * Este código agrega un nuevo nodo al final de una lista enlazada circular de canciones. Si la lista está vacía, 
		 * el nuevo nodo se convierte en el primer nodo y su enlace se establece para que apunte a sí mismo. Si la lista no 
		 * está vacía, se encuentra el último nodo de la lista, se actualizan los enlaces para agregar el nuevo nodo al final 
		 * y se cierra el ciclo para mantener la lista enlazada circular.
		 */
		public void agregarCancionAlFinal(Cancion cancion) {
			NodoCancion nuevoNodo = new NodoCancion(cancion);

			if (estaVacia()) {
				primerNodo = nuevoNodo;
				nuevoNodo.setSiguiente(primerNodo);
			} else {
				NodoCancion ultimoNodo = obtenerUltimoNodo();
				ultimoNodo.setSiguiente(nuevoNodo);
				nuevoNodo.setSiguiente(primerNodo);
			}
		}
		//_____________________________________________________________________________________________________________________

		
		/*
		 * Este código agrega un nuevo nodo al final de una lista enlazada circular de canciones, pero solo si la canción no 
		 * existe previamente en la lista. Si la lista está vacía, el nuevo nodo se convierte en el primer nodo y su enlace 
		 * se establece para que apunte a sí mismo. Si la lista no está vacía, se recorre la lista para verificar la existencia 
		 * de la canción. Si la canción ya existe, no se realiza ninguna acción adicional. Si la canción no existe, se agrega el 
		 * nuevo nodo al final de la lista y se cierra el ciclo para mantener la lista enlazada circular.
		 */
		public void agregarCancionSinReemplazar(Cancion cancion) {
			NodoCancion nuevoNodo = new NodoCancion(cancion);

			if (estaVacia()) {
				primerNodo = nuevoNodo;
				nuevoNodo.setSiguiente(primerNodo);
			} else {
				NodoCancion nodoActual = primerNodo;

				// Recorre la lista para verificar si la canción ya existe
				do {
					if (nodoActual.getCancion().equals(cancion)) {
						return; // La canción ya existe, no se agrega nada
					}
					nodoActual = nodoActual.getSiguiente();
				} while (nodoActual != primerNodo);

				// La canción no existe en la lista, se agrega al final
				NodoCancion ultimoNodo = obtenerUltimoNodo();
				ultimoNodo.setSiguiente(nuevoNodo);
				nuevoNodo.setSiguiente(primerNodo);
			}

		}
		//_____________________________________________________________________________________________________________________


		/*
		 * Este código agrega un nuevo nodo al final de una lista enlazada circular de canciones, estableciendo el estado de 
		 * rehacer de la canción como verdadero antes de agregarla. Si la canción ya existe en la lista, no se realiza ninguna acción adicional.
		 */
		public void agregarCancionYaEliminada(Cancion cancion) {
			NodoCancion nuevoNodo = new NodoCancion(cancion);
			nuevoNodo.getCancion().setEstadoRehacer(true);

			System.out.println(nuevoNodo.getCancion().getEstadoRehacer());

			if (estaVacia()) {
				primerNodo = nuevoNodo;
				nuevoNodo.setSiguiente(primerNodo);
			} else {
				NodoCancion nodoActual = primerNodo;

				// Recorre la lista para verificar si la canción ya existe
				do {
					if (nodoActual.getCancion().equals(cancion)) {
						return; // La canción ya existe, no se agrega nada
					}
					nodoActual = nodoActual.getSiguiente();
				} while (nodoActual != primerNodo);

				// La canción no existe en la lista, se agrega al final
				NodoCancion ultimoNodo = obtenerUltimoNodo();
				ultimoNodo.setSiguiente(nuevoNodo);
				nuevoNodo.setSiguiente(primerNodo);

			}
		}
		//_____________________________________________________________________________________________________________________

		
		/*
		 * Este método recorre la lista enlazada circular de canciones hasta llegar al último nodo y lo retorna. Es u
		 * tilizado en otros métodos para realizar operaciones en el último nodo, como agregar un nuevo nodo al final de la lista.
		 */
		private NodoCancion obtenerUltimoNodo() {
			NodoCancion nodoActual = primerNodo;

			while (nodoActual.getSiguiente() != primerNodo) {
				nodoActual = nodoActual.getSiguiente();
			}

			return nodoActual;
		}
		//_____________________________________________________________________________________________________________________

		
		/*
		 * Este método recorre la lista enlazada circular de canciones y agrega todas las canciones a un ArrayList, que luego se 
		 * devuelve. Esto proporciona una forma de obtener todas las canciones almacenadas en la lista para su posterior 
		 * procesamiento o visualización.
		 */
		public ArrayList<Cancion> obtenerTodasLasCanciones() {
			ArrayList<Cancion> canciones = new ArrayList<>();
			NodoCancion nodoActual = lista.getPrimerNodo();

			// Recorre la lista circular para obtener todas las canciones
			do {
				canciones.add(nodoActual.getCancion());
				nodoActual = nodoActual.getSiguiente();
			} while (nodoActual != lista.getPrimerNodo());

			return canciones;
		}
		//_____________________________________________________________________________________________________________________

		
		/*
		 *  Recorre una lista enlazada circular de canciones y elimina el nodo que contiene la canción seleccionada. 
		 *  Si la lista está vacía o solo tiene un elemento, se realizan las operaciones correspondientes para vaciar 
		 *  o eliminar la lista por completo.
		 */
		public void eliminarCancionListaCanciones(Cancion cancionSeleccionada) {
			if (primerNodo == null) {
				// La lista está vacía, no hay nada que eliminar
				return;
			}

			if(primerNodo.siguiente == null || primerNodo.siguiente.equals(null)) {
				primerNodo = new NodoCancion(null);
				return;
			}

			else if (primerNodo.getSiguiente() == null) {
				// La lista solo tiene un elemento, se hace null el primer nodo
				primerNodo = null;
				return;
			}

			NodoCancion nodoActual = primerNodo;
			NodoCancion nodoAnterior = null;

			// Recorre la lista circular para encontrar el nodo de la canción seleccionada
			while (nodoActual != null) {
				if (nodoActual.getCancion().equals(cancionSeleccionada)) {
					// Se encontró la canción, se elimina el nodo
					if (nodoAnterior == null) {
						// Si el nodo anterior es null, significa que es el primer nodo de la lista
						// Se actualiza el primer nodo para que sea el siguiente nodo
						primerNodo = nodoActual.getSiguiente();
					} else {
						// Si el nodo anterior no es null, se actualiza su referencia al siguiente nodo
						nodoAnterior.setSiguiente(nodoActual.getSiguiente());
					}
					return; // Se encontró y eliminó la canción, se sale del bucle
				}

				// Se mueve al siguiente nodo
				nodoAnterior = nodoActual;
				nodoActual = nodoActual.getSiguiente();

				// Si se volvió al primer nodo, significa que se recorrió toda la lista y no se encontró la canción
				if (nodoActual == primerNodo) {
					break;
				}
			}
		}
		//_____________________________________________________________________________________________________________________

		
		/*
		 * Metodo que permite limpiar la lista de canciones del usuario
		 */
		public void limpiarLista() {
			primerNodo = null;
		}
		//_____________________________________________________________________________________________________________________
	}
	//_____________________________________________________________________________________________________________________
	//_____________________________________________________________________________________________________________________


	/*
	 * Forma de acceder a la lista circular
	 */
	ListaCircularCancion lista = new ListaCircularCancion();
	//_____________________________________________________________________________________________________________________

	
	/*
	 * Metodos Get and Set
	 */
	public ListaCircularCancion getLista() {
		return lista;
	}
	
	public void setLista(ListaCircularCancion lista) {
		this.lista = lista;
	}
	//_____________________________________________________________________________________________________________________

	
	/*
	 * Metodo agregar cancion que conecta directamente con la lista circular
	 */
	public void agregar(Cancion cancion2) {
		lista.agregarCancionSinReemplazar(cancion2);
	}
	//_____________________________________________________________________________________________________________________
}
