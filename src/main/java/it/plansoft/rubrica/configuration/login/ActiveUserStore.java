package it.plansoft.rubrica.configuration.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;

/**
 * classe di base per tracciare gli utenti che hanno effettuato il login.
 * 
 * @author Giuseppe Grosso
 *
 */
public class ActiveUserStore {

	public List<String> users;

	public ActiveUserStore() {
		users = new ArrayList<String>();
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

}
