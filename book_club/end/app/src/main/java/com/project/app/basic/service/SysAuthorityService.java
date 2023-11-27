package com.project.app.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.app.basic.entity.SysAuthority;
import com.project.app.model.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysAuthorityService extends IService<SysAuthority> {
    Page<SysAuthority> page(PageDTO pageDTO);

    List<SysAuthority> listAll();

    SysAuthority getSysAuthorityById(String id);

    boolean deleteSysAuthorityById(String id);

    boolean deleteSysAuthorityByIds(List<String> ids);

    boolean addSysAuthority(SysAuthority obj);

    boolean updateSysAuthorityById(SysAuthority obj);

    List<String> curryAuthority(String roleName);
}
