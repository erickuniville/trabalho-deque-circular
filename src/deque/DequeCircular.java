package deque;

import java.util.function.Consumer;

class DequeCircular<T> {
	Elemento<T> cabeca;
	Elemento<T> cauda;
	
	void addLast(T valor) {
		Elemento<T> novo = new Elemento<>(valor, cauda, cabeca);
		
		if(isEmpty()) {
			cabeca = novo;
			cauda = novo;
			return;
		}
		
		cauda.proximo = novo;
		cauda = novo;
		cabeca.anterior = cauda;
	}

	void addFirst(T valor) {
		Elemento<T> novo = new Elemento<>(valor, cauda, cabeca);
		
		if(isEmpty()) {
			cabeca = novo;
			cauda = novo;
			return;
		}
		
		cabeca.anterior = novo;
		cabeca = novo;
		cauda.proximo = cabeca;
	}
	
	T removeFirst() {
		if(isEmpty()) {
			throw new RuntimeException("Não é possível remover elemento de um Deque vazio.");
		}
		
		T removido = cabeca.valor;
		
		if(apenasUmElemento()) {
			cabeca = null;
			cauda = null;
			return removido;
		}
		
		cabeca = cabeca.proximo;
		cabeca.anterior = cauda;
		cauda.proximo = cabeca;
		return removido;
	}
	
	T removeLast() {
		if(isEmpty()) {
			throw new RuntimeException("Não é possível remover elemento de um Deque vazio.");
		}
		
		T removido = cauda.valor;
		
		if(apenasUmElemento()) {
			cabeca = null;
			cauda = null;
			return removido;
		}
		
		cauda = cauda.anterior;
		cauda.proximo = cabeca;
		cabeca.anterior = cauda;
		return removido;
	}
	
	boolean isEmpty() {
		return cabeca == null;
	}
	
	boolean apenasUmElemento() {
		return cabeca == cauda;
	}
	
	void clear() {
		Elemento<T> auxiliar = cabeca;
		
		while (auxiliar != null) {
			Elemento<T> proximo = auxiliar.proximo;
			auxiliar.valor = null;
			auxiliar.proximo = null;
			auxiliar.anterior = null;
			auxiliar = proximo;
		}
		
		cabeca = null;
		cauda = null;
	}
	
	void list(Consumer<T> function) { 
		if(isEmpty()) {
			System.out.println();
			return;
		}
		
		Elemento<T> atual = cabeca;
		
		do {
			function.accept(atual.valor);
			atual = atual.proximo;
		} while(atual != null && atual != cabeca);
	}
}
