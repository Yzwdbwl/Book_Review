package com.project.app.work.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.app.enums.RoleEnum;
import com.project.app.model.dto.PageDTO;
import com.project.app.util.SecurityUtil;
import com.project.app.work.entity.Comment;
import com.project.app.work.mapper.CommentMapper;
import com.project.app.work.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public Page<Comment> page(PageDTO pageDTO) {
        String role = SecurityUtil.getCurrentUser().getRole();
        if (!role.equals(RoleEnum.ADMIN.name())){
            return lambdaQuery()
                    .eq(Comment::getCreateBy,SecurityUtil.getCurrentUser().getUsername())
                    .like(StringUtils.hasText(pageDTO.getSearch()), Comment::getDescription, pageDTO.getSearch())
                    .orderByDesc(Comment::getCreateTime).page(pageDTO.getMybatisPage());
        }
        return lambdaQuery()
                .like(StringUtils.hasText(pageDTO.getSearch()), Comment::getDescription, pageDTO.getSearch())
                .orderByDesc(Comment::getCreateTime).page(pageDTO.getMybatisPage());
    }

    @Override
    public List<Comment> listAll() {
        return list();
    }

    @Override
    public Map<String, Object> listAllByBookId(String bookId) {
        Map<String, Object> map = new HashMap<>();
        List<Comment> list = lambdaQuery().eq(Comment::getBookId, bookId)
                .orderByDesc(Comment::getCreateTime)
                .list();
        map.put("data", list);

        double asDouble = list.stream().filter(s -> s.getScore() != null)
                .mapToDouble(Comment::getScore)
                .average().orElse(0);

        map.put("score", asDouble);
        map.put("times", list.stream().filter(s -> s.getScore() != null).count());
        return map;
    }

    @Override
    public Comment getCommentById(String id) {
        return getById(id);
    }

    @Override
    public boolean deleteCommentById(String id) {
        return removeById(id);
    }

    @Override
    public boolean deleteCommentByIds(List<String> ids) {
        return removeBatchByIds(ids);
    }

    @Override
    public boolean addComment(Comment obj) {

        return save(obj);
    }

    @Override
    public boolean updateCommentById(Comment obj) {
        return updateById(obj);
    }
}
