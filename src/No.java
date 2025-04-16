public class No<T> {
    private T dado;
    private No<T> anterior;
    private No<T> proximo;

    public No(T dado) {
        this.dado = dado;
    }

    public T getDado() {
        return dado;
    }

    public No<T> getAnterior() {
        return anterior;
    }

    public No<T> getProximo() {
        return proximo;
    }

    public void setAnterior(No<T> anterior) {
        this.anterior = anterior;
    }

    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }
}