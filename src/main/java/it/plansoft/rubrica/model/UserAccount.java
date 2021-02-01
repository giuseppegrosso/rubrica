package it.plansoft.rubrica.model;

import java.util.Collection;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.plansoft.rubrica.security.ApplicationUserRole;

/**
 * classe di esempio che mappa utente e password con lista ruoli
 * 
 * @author Giuseppe Grosso
 *
 */
@Entity
public class UserAccount extends IDModel<Long> implements UserDetails {

	private static final long serialVersionUID = 3617721411274218217L;
	private String cognome;
	private String name;
	private String email;
	private String azienda;

	private String sso;
	private String password;

	private String ruoli; // lista dei ruoli separati da | o ,

	public UserAccount() {
	}

	public UserAccount(String cognome, String name, String email, String azienda, String sso_id, String password,
			String ruoli) {
		super();
		this.cognome = cognome;
		this.name = name;
		this.email = email;
		this.azienda = azienda;
		this.sso = sso_id;
		this.password = password;
		this.ruoli = ruoli;
		// inserisco un utente con le proprietà già valorizzate per poter accede
		this.enabled = true;
		this.locked = false;
		this.expired = false;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSso() {
		return sso;
	}

	public void setSso(String sso_id) {
		this.sso = sso_id;
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
		return "UserAccount [cognome=" + cognome + ", name=" + name + ", email=" + email + ", azienda=" + azienda
				+ ", sso_id=" + sso + ", password=" + password + ", ruoli=" + ruoli.split("|") + "]";
	}

	private Boolean locked = false;

	private Boolean enabled = true;

	private Boolean expired = false;

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return ApplicationUserRole.USER.getGrantedAutorities();
//		final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
//		return Collections.singletonList(simpleGrantedAuthority);
	}

	@Override
	public String getUsername() {
		return sso;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !expired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
