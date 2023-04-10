package model;

public class OlhoDoPassaro extends Cor {
    public OlhoDoPassaro(String cor) {
        super(cor);
    }

    @Override
    public String toString() {
        return "\u001B[" + this.cor + "m*";
    }
}
