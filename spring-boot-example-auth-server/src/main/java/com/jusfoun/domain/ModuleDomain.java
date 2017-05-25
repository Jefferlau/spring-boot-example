package com.jusfoun.domain;

import com.jusfoun.model.Module;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * Created by liutiyang on 2017/5/24.
 */
public class ModuleDomain extends Module implements Serializable, GrantedAuthority {
    private static final long serialVersionUID = -6558237585114800888L;

    @Override
    public String getAuthority() {
        return getModuleUrl();
    }
}
