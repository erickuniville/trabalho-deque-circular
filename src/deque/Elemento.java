package deque;

class Elemento<T> {
	Elemento<T> proximo;
	Elemento<T> anterior;
	T valor;
	
	Elemento(T valor, Elemento<T> anterior, Elemento<T> proximo) {
		this.valor = valor;
		this.anterior = anterior;
		this.proximo = proximo;
	}
	
	Elemento(T valor) {
		this.valor = valor;
	}
}
