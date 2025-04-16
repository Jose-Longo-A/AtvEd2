public class Ligacao {
    private String destino;
    private double distancia;
    private double fatorTrafego;
    private int pedagios;

    public Ligacao(String destino, double distancia, double fatorTrafego, int pedagios) {
        this.destino = destino;
        this.distancia = distancia;
        this.fatorTrafego = fatorTrafego;
        this.pedagios = pedagios;
    }

    public String getDestino() {
        return destino;
    }

    public double getTempoEstimado() {
        return (distancia * fatorTrafego) + (pedagios * 2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Ligacao outra) {
            return this.destino.equalsIgnoreCase(outra.destino);
        }
        return false;
    }

    public String toString() {
        return destino + " | Distância: " + distancia + " km | Tráfego: " + fatorTrafego +
               " | Pedágios: " + pedagios + " | Tempo: " + String.format("%.2f", getTempoEstimado()) + " min";
    }
}