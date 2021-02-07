package it.plansoft.rubrica.model;

import javax.persistence.Entity;
import java.util.Date;

/**
 * entity di rubrica
 *
 * @author Giuseppe Grosso
 */
@Entity
public class Rubrica extends BaseModel<Long> {

    private String cognome;
    private String nome;
    private String indirizzo;

    public Rubrica() {
        super();
    }

    public static Rubrica build()
    {
        Rubrica r = new Rubrica();
        r.setTsInsert(new Date());
        r.setUserInsert("root");
        return r;
    }


    public Rubrica(String cognome, String nome, String indirizzo) {
        this();
        this.cognome = cognome;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.setTsInsert(new Date());
        this.setUserInsert("root");
    }

    public String getCognome() {
        return cognome;
    }

    public Rubrica setCognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Rubrica setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Rubrica setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
        return this;
    }

    @Override
    public String toString() {
        return this.cognome + " " + this.nome + " " + this.indirizzo + " " + this.getTsInsert() + " "
                + this.getUserInsert();
    }
}
