package model;

public class Cor {

    String cor;

    public Cor(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "\u001B[" + this.cor + "m ";
    }
}
