package Database;

import javax.persistence.*;

@Entity
@Table(name = "fato")
public class FatoDb {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String fato;

    public FatoDb() {
    }

    public FatoDb(String fato) {
        this.fato = fato;
    }

    public String getFato() {
        return fato;
    }
}
