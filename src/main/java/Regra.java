public class Regra {
    private int id;
    private String antecedente;
    private String consequente;

    public Regra() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(String antecedente) {
        this.antecedente = antecedente;
    }

    public String getConsequente() {
        return consequente;
    }

    public void setConsequente(String consequente) {
        this.consequente = consequente;
    }
}
