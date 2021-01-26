package it.plansoft.rubrica.security;/* ggrosso created on 21/01/2021 inside the package - it.plansoft.rubrica.security */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ApplicationUserPermission {

	READ("READ"), WRITE("WRITE");

	private final String permission;

	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

	public static Set<ApplicationUserPermission> getUserPermission() {
		return new HashSet<ApplicationUserPermission>(Arrays.asList(READ));
	}

	public static Set<ApplicationUserPermission> getAdminPermission() {
		return new HashSet<ApplicationUserPermission>(Arrays.asList(WRITE, READ));
	}

	public static Set<ApplicationUserPermission> getVisualizzatorePermission() {
		return new HashSet<ApplicationUserPermission>(Arrays.asList(READ));
	}
}
