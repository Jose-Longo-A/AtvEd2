public class Cidade {
    private String nome;
    private ListaDupla<Ligacao> ligacoes;

    public Cidade(String nome) {
        this.nome = nome;
        this.ligacoes = new ListaDupla<>();
    }

    public String getNome() {
        return nome;
    }

    public ListaDupla<Ligacao> getLigacoes() {
        return ligacoes;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Cidade outra) {
            return this.nome.equalsIgnoreCase(outra.getNome());
        }
        return false;
    }

    public String toString() {
        return nome;
    }
}