package it.plansoft.rubrica.configuration.login;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * classe di monitor accessi.
 * 
 * @author Giuseppe Grosso
 *
 */
@Configuration
@Component
public class LoggedUser implements HttpSessionBindingListener {

	private String username;
	@Autowired
	private ActiveUserStore activeUserStore;

	public LoggedUser(String username, ActiveUserStore activeUserStore) {
		this.username = username;
		this.activeUserStore = activeUserStore;
	}

	public LoggedUser() {
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		List<String> users = activeUserStore.getUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		if (!users.contains(user.getUsername())) {
			users.add(user.getUsername());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		List<String> users = activeUserStore.getUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		if (users.contains(user.getUsername())) {
			users.remove(user.getUsername());
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ActiveUserStore getActiveUserStore() {
		return activeUserStore;
	}

	public void setActiveUserStore(ActiveUserStore activeUserStore) {
		this.activeUserStore = activeUserStore;
	}

}