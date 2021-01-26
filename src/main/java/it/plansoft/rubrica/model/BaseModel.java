package it.plansoft.rubrica.model;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * classe di base per i modelli di dati:id + tracciamento inserimento / modifica
 */
@MappedSuperclass
public class BaseModel<ID> extends IDModel<ID> {

    protected Date tsInsert;
    protected String userInsert;
    protected Date tsUpdate;
    protected String userUpdate;

    public BaseModel() {

    }


    public BaseModel(ID id, Date tsInsert, String userInsert, Date tsUpdate, String userUpdate) {
        this.id = id;
        this.tsInsert = tsInsert;
        this.userInsert = userInsert;
        this.tsUpdate = tsUpdate;
        this.userUpdate = userUpdate;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Date getTsUpdate() {
        return tsUpdate;
    }

    public void setTsUpdate(Date tsUpdate) {
        this.tsUpdate = tsUpdate;
    }

    public Date getTsInsert() {
        return tsInsert;
    }

    public void setTsInsert(Date tsInsert) {
        this.tsInsert = tsInsert;
    }

    public String getUserInsert() {
        return userInsert;
    }

    public void setUserInsert(String userInsert) {
        this.userInsert = userInsert;
    }
}