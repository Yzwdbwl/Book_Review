package com.project.app.work.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.app.model.dto.PageDTO;
import com.project.app.work.entity.Book;
import com.project.app.work.mapper.BookMapper;
import com.project.app.work.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 书籍表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Override
    public Page<Book> page(PageDTO pageDTO) {
        return lambdaQuery()
                .like(StringUtils.hasText(pageDTO.getSearch()), Book::getName, pageDTO.getSearch())
                .orderByDesc(Book::getCreateTime).page(pageDTO.getMybatisPage());
    }

    @Override
    public Page<Book> pageFront(PageDTO pageDTO) {
        return lambdaQuery()
                .like(StringUtils.hasText(pageDTO.getSearch()), Book::getName, pageDTO.getSearch())
                .or()
                .like(StringUtils.hasText(pageDTO.getSearch()), Book::getAuthor, pageDTO.getSearch())
                .orderByDesc(Book::getCreateTime).page(pageDTO.getMybatisPage());
    }

    @Override
    public List<Book> listAll() {
        return list();
    }

    @Override
    public Book getBookById(String id) {
        return getById(id);
    }

    @Override
    public boolean deleteBookById(String id) {
        return removeById(id);
    }

    @Override
    public boolean deleteBookByIds(List<String> ids) {
        return removeBatchByIds(ids);
    }

    @Override
    public boolean addBook(Book obj) {
        return save(obj);
    }

    @Override
    public boolean updateBookById(Book obj) {
        return updateById(obj);
    }
}
