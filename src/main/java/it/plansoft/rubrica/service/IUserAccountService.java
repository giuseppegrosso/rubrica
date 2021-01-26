package it.plansoft.rubrica.service;

import java.util.List;

import it.plansoft.rubrica.model.UserAccount;

public interface IUserAccountService {

	/**
	 * ottengo data un'azienda gli utenti che ne fanno parte
	 * 
	 * @param azienda
	 * @return
	 */
	public List<UserAccount> IsUserFromFactory(String azienda);

	/**
	 * dato un utente (sso_id) ottengo i suoi ruoli
	 * 
	 * @param sso_id
	 * @return
	 */
	public String getRoles(String sso_id);

}
