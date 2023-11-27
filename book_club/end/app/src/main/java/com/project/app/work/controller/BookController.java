package com.project.app.work.controller;

import com.project.app.model.dto.PageDTO;
import com.project.app.util.R;
import com.project.app.work.entity.Book;
import com.project.app.work.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 书籍表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService iBookService;


    @PostMapping("page")
    @Operation(summary = "书籍表分页")
    @PreAuthorize("hasRole('ADMIN')")
    public R page(@RequestBody PageDTO pageDTO) {
        return R.success(iBookService.page(pageDTO));
    }

    @PostMapping("pageFront")
    @Operation(summary = "书籍表分页前台")
    public R pageFront(@RequestBody PageDTO pageDTO) {
        return R.success(iBookService.pageFront(pageDTO));
    }

    @GetMapping("listAll")
    @Operation(summary = "书籍表列表")
    public R listAll() {
        return R.success(iBookService.listAll());
    }

    @GetMapping("getById/{id}")
    @Operation(summary = "书籍表详情")
    public R getBookById(@PathVariable("id") String id) {
        return R.success(iBookService.getBookById(id));
    }

    @DeleteMapping("deleteById/{id}")
    @Operation(summary = "书籍表删除")
    @PreAuthorize("hasRole('ADMIN')")
    public R deleteBookById(@PathVariable("id") String id) {
        return R.success(iBookService.deleteBookById(id), "删除成功");
    }

    @DeleteMapping("deleteByIds")
    @Operation(summary = "书籍表批量删除")
    @PreAuthorize("hasRole('ADMIN')")
    public R deleteBookById(@RequestBody List<String> ids) {
        return R.success(iBookService.deleteBookByIds(ids), "删除成功");
    }

    @PostMapping("add")
    @Operation(summary = "书籍表新增")
    @PreAuthorize("hasRole('ADMIN')")
    public R addBook(@RequestBody Book obj) {
        return R.success(iBookService.addBook(obj), "新增成功");
    }


    @PutMapping("updateById")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "书籍表更新")
    public R updateBookById(@RequestBody Book obj) {
        return R.success(iBookService.updateBookById(obj), "更新成功");
    }

}
