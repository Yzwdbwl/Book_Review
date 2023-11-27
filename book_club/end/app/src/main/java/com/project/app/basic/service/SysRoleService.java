package com.project.app.basic.service;

import com.project.app.basic.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.app.model.dto.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysRoleService extends IService<SysRole> {
        Page<SysRole> page(PageDTO pageDTO);
        List<SysRole> listAll();
        SysRole getSysRoleById(String id);
        boolean deleteSysRoleById(String id);
        boolean deleteSysRoleByIds(List<String> ids);
        boolean addSysRole(SysRole obj);
        boolean updateSysRoleById(SysRole obj);
}
