package com.project.app.basic.service;

import com.project.app.basic.entity.SysRoleAuthority;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.app.model.dto.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysRoleAuthorityService extends IService<SysRoleAuthority> {
        Page<SysRoleAuthority> page(PageDTO pageDTO);
        List<SysRoleAuthority> listAll();
        SysRoleAuthority getSysRoleAuthorityById(String id);
        boolean deleteSysRoleAuthorityById(String id);
        boolean deleteSysRoleAuthorityByIds(List<String> ids);
        boolean addSysRoleAuthority(SysRoleAuthority obj);
        boolean updateSysRoleAuthorityById(SysRoleAuthority obj);
}
