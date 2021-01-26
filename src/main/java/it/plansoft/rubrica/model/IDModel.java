package it.plansoft.rubrica.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * classe di base per i modelli di dati.
 */
@MappedSuperclass
public class IDModel<ID> {
    @Id
    @GeneratedValue
    protected ID id;


    public IDModel() {
    }


    public IDModel(ID id, Date tsInsert, String userInsert, Date tsUpdate, String userUpdate) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }


    public void setId(ID id) {
        this.id = id;
    }
}