package it.plansoft.rubrica.model;/* ggrosso created on 24/01/2021 inside the package - it.plansoft.rubrica.model */

import javax.persistence.*;
import java.util.Date;

/**
 * classe di gestione utenti
 */
@Entity
public class User extends IDModel<Long> {
    private String cognome;
    private String nome;
    private String email;
    private String azienda;


    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public User() {
        super();
    }

    public User(String cognome, String nome, String email, String azienda) {
        this.cognome = cognome;
        this.nome = nome;
        this.email = email;
        this.azienda = azienda;
    }

    public User(String cognome, String nome, String email, String azienda, Account account) {
        this.cognome = cognome;
        this.nome = nome;
        this.email = email;
        this.azienda = azienda;
        this.account = account;
    }

    public User(Long aLong, Date tsInsert, String userInsert, Date tsUpdate, String userUpdate, String cognome, String nome, String email, String azienda) {
        super(aLong, tsInsert, userInsert, tsUpdate, userUpdate);
        this.cognome = cognome;
        this.nome = nome;
        this.email = email;
        this.azienda = azienda;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAzienda() {
        return azienda;
    }

    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", azienda='" + azienda + '\'' +
                '}';
    }
}
