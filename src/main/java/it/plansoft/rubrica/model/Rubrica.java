package it.plansoft.rubrica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * entity di rubrica
 * 
 * @author Giuseppe Grosso
 *
 */
@Entity
public class Rubrica extends BaseModel {

	private String cognome;
	private String nome;
	private String indirizzo;

	public Rubrica() {
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return super.getId();
	}

	public Rubrica(String cognome, String nome, String indirizzo) {
		this.cognome = cognome;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.setTsInsert(new Date());
		this.setUserInsert("root");
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

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public String toString() {
		return this.cognome + " " + this.nome + " " + this.indirizzo + " " + this.getTsInsert() + " "
				+ this.getUserInsert();
	}
}
