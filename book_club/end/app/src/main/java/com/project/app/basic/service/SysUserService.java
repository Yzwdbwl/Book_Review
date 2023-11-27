package com.project.app.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.app.basic.entity.SysUser;
import com.project.app.model.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysUserService extends IService<SysUser> {
    Page<SysUser> page(PageDTO pageDTO);

    List<SysUser> listAll();
    List<SysUser> listAllByRole(String role);

    SysUser getSysUserById(String id);

    boolean deleteSysUserById(String id);

    boolean deleteSysUserByIds(List<String> ids);

    boolean addSysUser(SysUser obj);

    boolean updateSysUserById(SysUser obj);
    boolean updateSysUserByName(SysUser obj);
}
