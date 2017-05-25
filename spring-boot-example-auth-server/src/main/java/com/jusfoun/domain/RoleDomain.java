package com.jusfoun.domain;

import com.jusfoun.bean.RoleExtension;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * Created by liutiyang on 2017/5/24.
 */
public class RoleDomain extends RoleExtension implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = -5166971395270023298L;

    @Override
    public String getAuthority() {
        return getPermissionName();
    }
}
