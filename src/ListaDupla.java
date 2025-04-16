public class ListaDupla<T> {

    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public void inserir(T dado) {
        No<T> novo = new No<>(dado);
        if (inicio == null) {
            inicio = novo;
        } else {
            novo.setAnterior(fim);
            fim.setProximo(novo);
        }
        fim = novo;
        tamanho++;
    }

    public No<T> pesquisar(T dado) {
        No<T> atual = fim;
        while (atual != null) {
            if (atual.getDado().equals(dado)) {
                return atual;
            }
            atual = atual.getAnterior();
        }
        return null;
    }

    public void percorrer(Iterador<T> iterador) {
        No<T> atual = inicio;
        while (atual != null) {
            iterador.visitar(atual.getDado());
            atual = atual.getProximo();
        }
    }

    public interface Iterador<T> {
        void visitar(T elemento);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        No<T> atual = inicio;
        while (atual != null) {
            sb.append(atual.getDado().toString()).append("\n");
            atual = atual.getProximo();
        }
        return sb.toString();
    }
}