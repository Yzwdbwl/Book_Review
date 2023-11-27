package com.project.app.basic.service.impl;

import com.project.app.basic.entity.SysRoleAuthority;
import com.project.app.basic.mapper.SysRoleAuthorityMapper;
import com.project.app.basic.service.SysRoleAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.project.app.model.dto.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import java.util.List;
/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class SysRoleAuthorityServiceImpl extends ServiceImpl<SysRoleAuthorityMapper, SysRoleAuthority> implements SysRoleAuthorityService {
        @Override
        public Page<SysRoleAuthority> page(PageDTO pageDTO) {
        return lambdaQuery()
        .like(StringUtils.hasText(pageDTO.getSearch()),SysRoleAuthority::getRoleId,pageDTO.getSearch())
        .or()
        .like(StringUtils.hasText(pageDTO.getSearch()),SysRoleAuthority::getAuthorityId,pageDTO.getSearch())
        .orderByDesc(SysRoleAuthority::getCreateTime).page(pageDTO.getMybatisPage());
        }
        @Override
        public List<SysRoleAuthority> listAll() {
            return list();
        }
        @Override
        public SysRoleAuthority getSysRoleAuthorityById(String id) {
            return getById(id);
        }
        @Override
        public boolean deleteSysRoleAuthorityById(String id) {
            return removeById(id);
        }

        @Override
        public boolean deleteSysRoleAuthorityByIds(List<String> ids) {
        return removeBatchByIds(ids);
        }

        @Override
        public boolean addSysRoleAuthority(SysRoleAuthority obj) {
            return save(obj);
        }
        @Override
        public boolean updateSysRoleAuthorityById(SysRoleAuthority obj) {
            return updateById(obj);
        }
}
