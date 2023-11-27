package com.project.app.work.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Getter
@Setter
@Schema(description = "评论表")
public class Comment {

    @Schema(description = "ID")
    private String id;

    @Schema(description = "书籍ID")
    private String bookId;

    @Schema(description = "评分")
    private Double score;

    @Schema(description = "评论内容")
    private String description;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "发布人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;


}
