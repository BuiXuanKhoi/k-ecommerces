package com.ecommerce.kgateway.security;

import com.ecommerce.kgateway.authorization.Role;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SecurityContext {

    private Map<String, Set<Role>> securityPath = new HashMap<>();

    private List<String> tempPaths = new ArrayList<>();

    public SecurityContext registerPath(String... paths){
        for (String path : paths){
            tempPaths.add(path);
        }
        return this;
    }

    public SecurityContext withRoles(Role... roles){
        Set<Role> tempRoles = new HashSet<>(roles.length);
        tempRoles.addAll(Arrays.asList(roles));

        if (!tempPaths.isEmpty()){
            for (String path : tempPaths){
                securityPath.put(path, tempRoles);
            }
        }
        return this;
    }

    public SecurityContext permitAll(){
        return withRoles(Role.values());
    }

    public boolean isRequestAccept(String requestPath, Role userRole){
        Set<Role> requestRole = securityPath.get(requestPath);
        return requestRole.contains(userRole);
    }



}

// registerPath("/api/handler").withRoles(Role.ADMIN, Role.CLIENT);
