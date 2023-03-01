package com.juzi.springmvc.controller;

import com.juzi.springmvc.core.annotation.Autowired;
import com.juzi.springmvc.core.annotation.Controller;
import com.juzi.springmvc.core.annotation.RequestMapping;
import com.juzi.springmvc.pojo.entity.Book;
import com.juzi.springmvc.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 测试控制器
 *
 * @author codejuzi
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 测试方法
     * @param request request
     * @param response response
     */
    @RequestMapping(value = "/book/list")
    public void listBooks(HttpServletRequest request, HttpServletResponse response)  {
        response.setContentType("text/html; charset=utf-8");

        try {
            List<Book> bookList = bookService.listBooks();
            StringBuilder content = new StringBuilder("<h1>Book List</h1>");
            content.append("<table width='500px' style='border-collapse:collapse border='1px'>");
            for (Book book : bookList) {
                content.append("<tr><td>")
                        .append(book.getBookId())
                        .append("</td><td>")
                        .append(book.getBookName())
                        .append("</td>");
            }
            content.append("</table>");
            PrintWriter writer = response.getWriter();
            writer.write(content.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
