package com.project.app.work.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.app.model.dto.PageDTO;
import com.project.app.work.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface CommentService extends IService<Comment> {
    Page<Comment> page(PageDTO pageDTO);

    List<Comment> listAll();

    Map<String, Object> listAllByBookId(String bookId);

    Comment getCommentById(String id);

    boolean deleteCommentById(String id);

    boolean deleteCommentByIds(List<String> ids);

    boolean addComment(Comment obj);

    boolean updateCommentById(Comment obj);
}
