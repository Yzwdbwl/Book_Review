package com.project.app.work.service;

import com.project.app.work.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.app.model.dto.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/**
 * <p>
 * 书籍表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface BookService extends IService<Book> {
        Page<Book> page(PageDTO pageDTO);
        Page<Book> pageFront(PageDTO pageDTO);
        List<Book> listAll();
        Book getBookById(String id);
        boolean deleteBookById(String id);
        boolean deleteBookByIds(List<String> ids);
        boolean addBook(Book obj);
        boolean updateBookById(Book obj);
}
