package Database;

import javax.persistence.*;

@Entity
@Table(name = "regra")
public class RegraDb {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String regra;

    public RegraDb() {
    }

    public RegraDb(String regra) {
        this.regra = regra;
    }

    public String getRegra() {
        return regra;
    }
}
