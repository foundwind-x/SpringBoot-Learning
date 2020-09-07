package com.fz.boot.sharding.controller;

import com.fz.boot.sharding.entity.Book;
import com.fz.boot.sharding.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Macky
 * @Title class BookController
 * @Description:
 * @date 2019/7/12 20:53
 */
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/book")
    public List<Book> getItems(){
        return bookService.getBookList();
    }

    @PostMapping(value = "/book")
    public Boolean saveItem(Book book){
        return bookService.save(book);
    }
}
