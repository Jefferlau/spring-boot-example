package com.jusfoun.bean;

import com.jusfoun.model.Module;
import com.jusfoun.model.Role;

import java.io.Serializable;
import java.util.List;

public class RoleExtension extends Role implements Serializable {
    private static final long serialVersionUID = -1207480849358597364L;
    private List<Module> modules;

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
