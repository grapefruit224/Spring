package com.example.spring_daily;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/addBook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        book.setId(nextId++);
        books.add(book);
        return "redirect:/books";
    }

    @GetMapping("/editBook/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = findBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "editBook"; // 템플릿 파일의 이름
        } else {
            System.err.println("Book not found with id: " + id);
            return "redirect:/books";
        }
    }

    @PostMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Long id, @ModelAttribute Book updatedBook) {
        Book book = findBookById(id);
        if (book != null) {
            updateBook(book, updatedBook);
        } else {
            System.err.println("Book not found with id: " + id);
        }
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        Book book = findBookById(id);
        if (book != null) {
            books.remove(book);
        } else {
            System.err.println("Book not found with id: " + id);
        }
        return "redirect:/books";
    }

    private Book findBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private void updateBook(Book book, Book updatedBook) {
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublicationYear(updatedBook.getPublicationYear());
    }


}