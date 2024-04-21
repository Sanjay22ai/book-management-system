package com.example.bookmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmanagement.entity.Book;
import com.example.bookmanagement.service.BookService;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@GetMapping("/{id}")
	public Book getBookById(@PathVariable("id") Long id) {
		return bookService.getBookById(id);
	}

	@PostMapping
	public Book createBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}

	@PutMapping("/{id}")
	public Book updateBook(@PathVariable("id") Long id, @RequestBody Book newBook) {

		Book book = bookService.getBookById(id);
		if (book == null) {
			return null;
		}
		book.setTitle(newBook.getTitle());
		book.setAuthor(newBook.getAuthor());
		book.setIsbn(newBook.getIsbn());
		return bookService.saveBook(book);
	}

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
	}

}
