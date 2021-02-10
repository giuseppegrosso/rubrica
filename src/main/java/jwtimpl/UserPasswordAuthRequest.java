package jwtimpl;

/**
 * oggetto per recupero username e password dalla request.
 * 
 * @author Giuseppe Grosso
 *
 */
public class UserPasswordAuthRequest {

	private String username;
	private String password;

	public UserPasswordAuthRequest() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}