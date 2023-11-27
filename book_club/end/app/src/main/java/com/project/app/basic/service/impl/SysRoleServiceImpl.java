package com.project.app.basic.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.app.basic.entity.SysRole;
import com.project.app.basic.entity.SysRoleAuthority;
import com.project.app.basic.mapper.SysRoleMapper;
import com.project.app.basic.service.SysRoleAuthorityService;
import com.project.app.basic.service.SysRoleService;
import com.project.app.model.dto.PageDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleAuthorityService sysRoleAuthorityService;

    @Override
    public Page<SysRole> page(PageDTO pageDTO) {
        return lambdaQuery()
                .like(StringUtils.hasText(pageDTO.getSearch()), SysRole::getName, pageDTO.getSearch())
                .or()
                .like(StringUtils.hasText(pageDTO.getSearch()), SysRole::getDescription, pageDTO.getSearch())
                .orderByDesc(SysRole::getCreateTime).page(pageDTO.getMybatisPage());
    }


    @Override
    public List<SysRole> listAll() {
        return list();
    }

    @Override
    public SysRole getSysRoleById(String id) {
        return getById(id);
    }

    @Override
    public boolean deleteSysRoleById(String id) {
        return removeById(id);
    }

    @Override
    public boolean deleteSysRoleByIds(List<String> ids) {
        return removeBatchByIds(ids);
    }

    @Override
    public boolean addSysRole(SysRole obj) {
        List<String> authIdList = obj.getAuthIdList();
        List<SysRoleAuthority> sysRoleAuthorities = new ArrayList<>();
        for (String s : authIdList) {
            SysRoleAuthority sysRoleAuthority = new SysRoleAuthority();
            sysRoleAuthority.setRoleId(obj.getId());
            sysRoleAuthority.setAuthorityId(s);
            sysRoleAuthorities.add(sysRoleAuthority);
        }
        sysRoleAuthorityService.saveBatch(sysRoleAuthorities);
        return save(obj);
    }

    @Override
    public boolean updateSysRoleById(SysRole obj) {
        List<String> authIdList = obj.getAuthIdList();
        sysRoleAuthorityService.lambdaUpdate()
                .eq(SysRoleAuthority::getRoleId, obj.getId())
                .remove();
        List<SysRoleAuthority> sysRoleAuthorities = new ArrayList<>();
        for (String s : authIdList) {
            SysRoleAuthority sysRoleAuthority = new SysRoleAuthority();
            sysRoleAuthority.setRoleId(obj.getId());
            sysRoleAuthority.setAuthorityId(s);
            sysRoleAuthorities.add(sysRoleAuthority);
        }
        sysRoleAuthorityService.saveBatch(sysRoleAuthorities);
        return updateById(obj);
    }
}
