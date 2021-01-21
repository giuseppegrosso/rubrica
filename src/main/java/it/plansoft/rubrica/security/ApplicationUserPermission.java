package it.plansoft.rubrica.security;/* ggrosso created on 21/01/2021 inside the package - it.plansoft.rubrica.security */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ApplicationUserPermission {
//    USER_WRITE("USER:WRITE"),
//    USER_READ("USER:READ"),
//    ADMIN_WRITE("ADMIN:WRITE"),
//    ADMIN_READ("ADMIN:READ");
	
	READ("READ"),
	WRITE("WRITE");

    private final String permission;

    ApplicationUserPermission(String permission){
        this.permission = permission;
    }

    public String getPermission()
    {
        return permission;
    }

//    public static Set<ApplicationUserPermission> getUserPermission()
//    {
//        return new HashSet<ApplicationUserPermission>(Arrays.asList(USER_READ, USER_WRITE));
//    }
//
//    public static Set<ApplicationUserPermission> getAdminPermission()
//    {
//        return new HashSet<ApplicationUserPermission>(Arrays.asList(ADMIN_WRITE, ADMIN_READ, USER_READ, USER_WRITE));
//    }
//    public static Set<ApplicationUserPermission> getVisualizzatorePermission()
//    {
//        return new HashSet<ApplicationUserPermission>(Arrays.asList( USER_READ));
//    }
    
    public static Set<ApplicationUserPermission> getUserPermission()
    {
        return new HashSet<ApplicationUserPermission>(Arrays.asList(READ));
    }

    public static Set<ApplicationUserPermission> getAdminPermission()
    {
        return new HashSet<ApplicationUserPermission>(Arrays.asList(WRITE, READ));
    }
    public static Set<ApplicationUserPermission> getVisualizzatorePermission()
    {
        return new HashSet<ApplicationUserPermission>(Arrays.asList( READ));
    }
}
