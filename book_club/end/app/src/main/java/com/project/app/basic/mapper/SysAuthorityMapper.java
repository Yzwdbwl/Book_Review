package com.project.app.basic.mapper;

import com.project.app.basic.entity.SysAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysAuthorityMapper extends BaseMapper<SysAuthority> {

    @Select("select  a.name authroity_name\n" +
            "from sys_role_authority ra\n" +
            "         left join sys_authority a  on ra.authority_id = a.id\n" +
            "         left join sys_role r on r.id = ra.role_id\n" +
            "where r.name=#{userRole}\n")
    List<String> authorityByUserRole(@Param("userRole") String userRole);
}
