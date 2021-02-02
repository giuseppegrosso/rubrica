package it.plansoft.rubrica.security;/* ggrosso created on 21/01/2021 inside the package - it.plansoft.rubrica.security */

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {

	USER(ApplicationUserPermission.getUserPermission()), ADMIN(ApplicationUserPermission.getAdminPermission()),
	VISUALIZZATORE(ApplicationUserPermission.getVisualizzatorePermission());

	private final Set<ApplicationUserPermission> permissions;

	ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}

	public Set<GrantedAuthority> getGrantedAuthorities(String ruoli) {
		Set<GrantedAuthority> permissions = new HashSet<>();
		if (ruoli != null) {
			String[] roles = ruoli.split("\\|");

			for (int i = 0; i < roles.length; i++) {
				if (roles[i].equalsIgnoreCase(USER.name()) || roles[i].equalsIgnoreCase(ADMIN.name())
						|| roles[i].equalsIgnoreCase(VISUALIZZATORE.name()))
					permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

				else
					permissions.add(new SimpleGrantedAuthority(roles[i]));
			}
		}
		return permissions;
	}

	public Set<GrantedAuthority> getGrantedAutorities() {
		Set<GrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());

		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

		return permissions;
	}

}
