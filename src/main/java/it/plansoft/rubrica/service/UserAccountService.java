package it.plansoft.rubrica.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.plansoft.rubrica.model.UserAccount;
import it.plansoft.rubrica.repository.UserAccountRepository;

/**
 * classe di gestione della tabella userAccount
 */
@Service
public class UserAccountService extends BaseCrudService<UserAccountRepository, UserAccount, Long>
		implements IUserAccountService {

	@Autowired
	public UserAccountService(UserAccountRepository userAccountRepository) {
		super(userAccountRepository);
	}

	@Override
	public List<UserAccount> IsUserFromFactory(String azienda) {
		List<UserAccount> ua = ((UserAccountRepository) repo).findAll();
		List<UserAccount> uaout = null;
		// foreach
		for (UserAccount userAccount : ua) {
			if (userAccount.getAzienda().equalsIgnoreCase(azienda)) {
				if (uaout == null ) uaout = new ArrayList<>();
				
				uaout.add(userAccount);
			}
		}
		return uaout;
	}

	@Override
	public String getRoles(String sso_id) {
		// stream
		// foreach

		List<UserAccount> ua = ((UserAccountRepository) repo).findAll();
		String roles = "";
// for classico
//		for (int i = 0; i < ua.size(); i++) {
//			if (ua.get(i).getSso_id().equalsIgnoreCase(sso_id)) {
//				roles = ua.get(i).getRuoli();
//				break;
//			}
//		}
		// foreach
		for (UserAccount userAccount : ua) {
			if (userAccount.getSso_id().equalsIgnoreCase(sso_id)) {
				roles = userAccount.getRuoli();
				break;
			}
		}

		// lambda
//		if (ua != null) {
//			ua.stream().findFirst(x -> {
//				if (x.getSso_id().equalsIgnoreCase(sso_id)) {
//					roles = x.getRuoli();
//					break;
//				}
//			});
//		}

		return roles;
	}

}