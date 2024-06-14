package Model;

import java.util.ArrayList;

public class ListaDobleCancionesArtista<T> {

	public class Nodo {

		// Atributos
		private Cancion valor;
		private Nodo siguiente;
		private Nodo anterior;
		//____________________________________________________________________________________________________________


		// Metodo constructor
		public Nodo(Cancion cancion) {
			this.valor = cancion;
			this.siguiente = null;
			this.anterior = null;
		}
		//____________________________________________________________________________________________________________

		
		// Getters y setters
		public Cancion getValor() {
			return valor;
		}

		public void setValor(Cancion valor) {
			this.valor = valor;
		}

		public Nodo getSiguiente() {
			return siguiente;
		}

		public void setSiguiente(Nodo siguiente) {
			this.siguiente = siguiente;
		}

		public Nodo getAnterior() {
			return anterior;
		}

		public void setAnterior(Nodo anterior) {
			this.anterior = anterior;
		}
		//____________________________________________________________________________________________________________
	}
	//____________________________________________________________________________________________________________
	//____________________________________________________________________________________________________________
	//____________________________________________________________________________________________________________



	/*
	 * Forma en la que se logra acceder a los metodos que contiene la lista doblemente enlazada
	 */
	ListaDobleEnlazada acceso = new ListaDobleEnlazada();
	//____________________________________________________________________________________________________________


	/*
	 * Metodos Get and Set
	 */
	public ListaDobleEnlazada getAcceso() {
		return acceso;
	}
	public void setAcceso(ListaDobleEnlazada acceso) {
		this.acceso = acceso;
	}
	//____________________________________________________________________________________________________________


	//____________________________________________________________________________________________________________
	//____________________________________________________________________________________________________________
	//____________________________________________________________________________________________________________
	public class ListaDobleEnlazada {

		/*
		 * Atributos
		 */
		private Nodo cabeza;
		private Nodo cola;
		//____________________________________________________________________________________________________________


		/*
		 * Metodos Get and Set
		 */
		public Nodo getCabeza() {
			return cabeza;
		}

		public void setCabeza(Nodo cabeza) {
			this.cabeza = cabeza;
		}

		public Nodo getCola() {
			return cola;
		}

		public void setCola(Nodo cola) {
			this.cola = cola;
		}
		//____________________________________________________________________________________________________________

		
		/*
		 * Metodo constructor
		 */
		public ListaDobleEnlazada() {
			this.cabeza = null;
			this.cola = null;
		}
		//____________________________________________________________________________________________________________

		// Métodos de la lista doblemente enlazada

		// Agregar un nodo al final de la lista
		/*
		 * Este código agrega una canción a una lista enlazada doblemente, colocando el nuevo nodo al final de la lista. 
		 * Si la lista está vacía, tanto la cabeza como la cola se establecen en el nuevo nodo. Si la lista ya contiene elementos, 
		 * se actualiza la cola para que apunte al nuevo nodo y se ajustan los enlaces de los nodos para mantener la coherencia de la lista.
		 */
		public void agregarCancion(Cancion cancion) {
			Nodo nuevoNodo = new Nodo(cancion);
			if (cabeza == null) {
				cabeza = nuevoNodo;
				cola = nuevoNodo;
			} else {
				cola.setSiguiente(nuevoNodo);
				nuevoNodo.setAnterior(cola);
				cola = nuevoNodo;
			}
		}
		//____________________________________________________________________________________________________________

		
		// Eliminar un nodo con un valor específico
		/*
		 *  Este código elimina una canción de una lista enlazada doblemente. Si la canción a eliminar se encuentra en 
		 *  la cabeza de la lista, se actualizan los enlaces para eliminar el primer nodo. Si la canción se encuentra 
		 *  en otro lugar de la lista, se recorre la lista para encontrar el nodo que contiene la canción y se ajustan 
		 *  los enlaces para eliminar ese nodo.
		 */
		public void eliminar(Cancion cancion) {
			if (cabeza == null) {
				return;
			}

			if (cabeza.getValor() == cancion) {
				cabeza = cabeza.getSiguiente();
				if (cabeza != null) {
					cabeza.setAnterior(null);
				} else {
					cola = null;
				}
				return;
			}

			Nodo nodoActual = cabeza;
			while (nodoActual.getSiguiente() != null && nodoActual.getSiguiente().getValor() != cancion) {
				nodoActual = nodoActual.getSiguiente();
			}

			if (nodoActual.getSiguiente() != null) {
				Nodo nodoSiguiente = nodoActual.getSiguiente().getSiguiente();
				nodoActual.setSiguiente(nodoSiguiente);
				if (nodoSiguiente != null) {
					nodoSiguiente.setAnterior(nodoActual);
				} else {
					cola = nodoActual;
				}
			}
		}
		//____________________________________________________________________________________________________________

		// Mostrar el contenido de la lista
		/*
		 * Este código muestra los valores almacenados en cada nodo de una lista enlazada doblemente. Imprime los valores 
		 * uno por uno en la misma línea, separados por un espacio, y luego agrega un salto de línea.
		 */
		public void mostrar() {
			Nodo nodoActual = cabeza;
			while (nodoActual != null) {
				System.out.print(nodoActual.getValor() + " ");
				nodoActual = nodoActual.getSiguiente();
			}
			System.out.println();
		}
		//____________________________________________________________________________________________________________

		
		/*
		 * Este código recorre una lista enlazada doblemente y construye un ArrayList que contiene todas las canciones 
		 * almacenadas en los nodos de la lista. Luego, devuelve este ArrayList como resultado.
		 */
		public ArrayList<Cancion> obtenerCanciones() {
			ArrayList<Cancion> canciones = new ArrayList<>();
			Nodo nodoActual = acceso.cabeza;
			while (nodoActual != null) {
				canciones.add(nodoActual.getValor());
				nodoActual = nodoActual.getSiguiente();
			}
			return canciones;
		}
		//____________________________________________________________________________________________________________

	}
	//____________________________________________________________________________________________________________
}
