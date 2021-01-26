package it.plansoft.rubrica.model;/* ggrosso created on 24/01/2021 inside the package - it.plansoft.rubrica.model */

import javax.persistence.*;
import java.util.Date;

/**
 * classe di account
 */
@Entity
public class Account extends IDModel<Long> {
    private String sso_id;
    private String password;


    @OneToOne(mappedBy = "account")
    private User user;

    public Account() {
        super();
    }

    public Account(String sso_id, String password) {
        this.sso_id = sso_id;
        this.password = password;
    }

    public Account(Long aLong, Date tsInsert, String userInsert, Date tsUpdate, String userUpdate, String sso_id, String password) {
        super(aLong, tsInsert, userInsert, tsUpdate, userUpdate);
        this.sso_id = sso_id;
        this.password = password;
    }

    public String getSso_id() {
        return sso_id;
    }

    public void setSso_id(String sso_id) {
        this.sso_id = sso_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "sso_id='" + sso_id + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
