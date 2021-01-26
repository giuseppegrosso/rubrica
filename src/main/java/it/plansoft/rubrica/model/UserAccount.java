package it.plansoft.rubrica.model;

import javax.persistence.Entity;

/**
 * classe di esempio che mappa utente e password con lista ruoli
 * 
 * @author Giuseppe Grosso
 *
 */
@Entity
public class UserAccount extends IDModel<Long> {

	private String cognome;
	private String nome;
	private String email;
	private String azienda;

	private String sso_id;
	private String password;

	private String ruoli; // lista dei ruoli separati da | o ,

	public UserAccount() {
	}

	public UserAccount(String cognome, String nome, String email, String azienda, String sso_id, String password,
			String ruoli) {
		super();
		this.cognome = cognome;
		this.nome = nome;
		this.email = email;
		this.azienda = azienda;
		this.sso_id = sso_id;
		this.password = password;
		this.ruoli = ruoli;
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

	public String getRuoli() {
		return ruoli;
	}

	public void setRuoli(String ruoli) {
		this.ruoli = ruoli;
	}

	@Override
	public String toString() {
		return "UserAccount [cognome=" + cognome + ", nome=" + nome + ", email=" + email + ", azienda=" + azienda
				+ ", sso_id=" + sso_id + ", password=" + password + ", ruoli=" + ruoli + "]";
	}

}
