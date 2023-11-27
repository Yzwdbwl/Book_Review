package com.project.app.basic.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.app.basic.entity.SysAuthority;
import com.project.app.basic.mapper.SysAuthorityMapper;
import com.project.app.basic.service.SysAuthorityService;
import com.project.app.model.dto.PageDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class SysAuthorityServiceImpl extends ServiceImpl<SysAuthorityMapper, SysAuthority> implements SysAuthorityService {
    
    @Resource
    private SysAuthorityMapper sysAuthorityMapper;

    @Override
    public Page<SysAuthority> page(PageDTO pageDTO) {
        return lambdaQuery()
                .like(StringUtils.hasText(pageDTO.getSearch()), SysAuthority::getName, pageDTO.getSearch())
                .or()
                .like(StringUtils.hasText(pageDTO.getSearch()), SysAuthority::getDescription, pageDTO.getSearch())
                .orderByDesc(SysAuthority::getCreateTime).page(pageDTO.getMybatisPage());
    }

    @Override
    public List<SysAuthority> listAll() {
        return list();
    }

    @Override
    public SysAuthority getSysAuthorityById(String id) {
        return getById(id);
    }

    @Override
    public boolean deleteSysAuthorityById(String id) {
        return removeById(id);
    }

    @Override
    public boolean deleteSysAuthorityByIds(List<String> ids) {
        return removeBatchByIds(ids);
    }

    @Override
    public boolean addSysAuthority(SysAuthority obj) {
        return save(obj);
    }

    @Override
    public boolean updateSysAuthorityById(SysAuthority obj) {
        return updateById(obj);
    }

    @Override
    public List<String> curryAuthority(String roleName) {
        return sysAuthorityMapper.authorityByUserRole(roleName);
    }
}
