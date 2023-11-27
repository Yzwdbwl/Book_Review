package com.project.app.work.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 书籍表
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Getter
@Setter
@Schema( description = "书籍表")
public class Book {

    @Schema(description="ID")
    private String id;

    @Schema(description="书籍名称")
    private String name;

    @Schema(description="封面")
    private String img;

    @Schema(description="作者")
    private String author;

    @Schema(description="出版社")
    private String publishers;

    @Schema(description="流派")
    private String tributary;

    @Schema(description="副标题")
    private String subheading;

    @Schema(description="译者")
    private String translator;

    @Schema(description="原作名")
    private String originalTitle;

    @Schema(description="出版年")
    private String year;

    @Schema(description="页数")
    private Integer pagination;

    @Schema(description="内容简介")
    private String description;

    @Schema(description="isbn")
    private String isbn;

    @Schema(description="创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description="发布人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;


}
