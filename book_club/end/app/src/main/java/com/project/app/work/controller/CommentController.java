package com.project.app.work.controller;

import com.project.app.model.dto.PageDTO;
import com.project.app.util.R;
import com.project.app.work.entity.Comment;
import com.project.app.work.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService iCommentService;


    @PostMapping("page")
    @Operation(summary = "评论表分页")
    public R page(@RequestBody PageDTO pageDTO) {
        return R.success(iCommentService.page(pageDTO));
    }

    @GetMapping("listAll")
    @Operation(summary = "评论表列表")
    public R listAll() {
        return R.success(iCommentService.listAll());
    }

    @GetMapping("listAllByBookId/{bookId}")
    @Operation(summary = "评论表列表")
    public R listAllByBookId(@PathVariable("bookId") String bookId) {
        return R.success(iCommentService.listAllByBookId(bookId));
    }


    @GetMapping("getById/{id}")
    @Operation(summary = "评论表详情")
    public R getCommentById(@PathVariable("id") String id) {
        return R.success(iCommentService.getCommentById(id));
    }

    @DeleteMapping("deleteById/{id}")
    @Operation(summary = "评论表删除")
    public R deleteCommentById(@PathVariable("id") String id) {
        return R.success(iCommentService.deleteCommentById(id), "删除成功");
    }

    @DeleteMapping("deleteByIds")
    @Operation(summary = "评论表批量删除")
    public R deleteCommentById(@RequestBody List<String> ids) {
        return R.success(iCommentService.deleteCommentByIds(ids), "删除成功");
    }

    @PostMapping("add")
    @Operation(summary = "评论表新增")
    public R addComment(@RequestBody Comment obj) {
        return R.success(iCommentService.addComment(obj), "发表成功");
    }


    @PutMapping("updateById")
    @Operation(summary = "评论表更新")
    public R updateCommentById(@RequestBody Comment obj) {
        return R.success(iCommentService.updateCommentById(obj), "更新成功");
    }

}
