package com.jusfoun.service.impl;

import com.jusfoun.dao.mapper.RoleMapper;
import com.jusfoun.bean.RoleExtension;
import com.jusfoun.model.Role;
import com.jusfoun.model.RoleExample;
import com.jusfoun.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 Service
 * Created by liutiyang on 2017/5/24.
 */
@Service
public class RoleService extends BaseService<RoleMapper, Role, RoleExample> {

    public List<RoleExtension> selectRoleExtensionByUserId(String id) {
        return mapper.selectRoleExtensionByUserId(id);
    }
}
