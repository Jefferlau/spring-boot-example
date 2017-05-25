package com.jusfoun.security;

import com.jusfoun.domain.ModuleDomain;
import com.jusfoun.bean.RoleExtension;
import com.jusfoun.domain.RoleDomain;
import com.jusfoun.model.Module;
import com.jusfoun.model.Role;
import com.jusfoun.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecurityUser extends User implements UserDetails {
    private static final long serialVersionUID = 1L;

    private List<RoleDomain> roles;

    private Set<ModuleDomain> modules;

    public List<RoleDomain> getRoles() {
        return roles;
    }

    public Set<ModuleDomain> getModules() {
        return modules;
    }

    public void setRoles(List<RoleExtension> roles) {
        this.roles = getRoleDomains(roles);
        this.modules = getModuleDomains();
    }

    public SecurityUser(User user) {
        if (user != null) {
            BeanUtils.copyProperties(user, this);
        }
    }

    @Transient
    private List<RoleDomain> getRoleDomains(List<RoleExtension> roles) {
        List<RoleDomain> roleDomains = new ArrayList<>();
        RoleDomain roleDomain;
        for (RoleExtension roleExtension : roles) {
            roleDomain = new RoleDomain();
            BeanUtils.copyProperties(roleExtension, roleDomain);
            roleDomains.add(roleDomain);
        }
        return roleDomains;
    }

    @Transient
    private Set<ModuleDomain> getModuleDomains() {
        Set<ModuleDomain> moduleDomains = new HashSet<>();
        for (RoleExtension role : roles) {
            // moduleDomains.addAll(role.getModules());
            addModuleDomainsFromModules(moduleDomains, role.getModules());
        }
        return moduleDomains;
    }

    private void addModuleDomainsFromModules(Set<ModuleDomain> moduleDomains, List<Module> modules) {
        ModuleDomain moduleDomain;
        for (Module module : modules) {
            moduleDomain = new ModuleDomain();
            BeanUtils.copyProperties(module, moduleDomain);
            moduleDomains.add(moduleDomain);
        }
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<RoleDomain> userRoles = this.getRoles();
        Set<ModuleDomain> roleModule = this.getModules();

        authorities.addAll(userRoles);
        authorities.addAll(roleModule);

        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        //return super.getEmail();
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return  true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getStatus() != 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getStatus() != 2;
    }
}
