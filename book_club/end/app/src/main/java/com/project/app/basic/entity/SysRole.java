package com.project.app.basic.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Getter
@Setter
@TableName("sys_role")
@Schema( description = "角色表")
public class SysRole {

    private String id;

    @Schema(description="角色名称")
    private String name;

    @Schema(description="角色描述")
    private String description;

    @Schema(description="创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


    @TableField(exist = false)
    private List<String> authIdList;


}
