package com.india.railway.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.india.railway.model.Book;
import com.india.railway.service.BookService;
import com.india.railway.utility.Utilitys;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	private Utilitys utilitys;

	@RequestMapping("/addBook")
	@ResponseBody
	public String addBook(@RequestParam("bookId") long bookId, @RequestParam("isbnNumber") String isbnNumber,
			@RequestParam("bookName") String bookName,
			@RequestParam("category") String category) {

		log.info("Info level");
		// log.info("Info level");

		log.error("Error level");
		if (bookService.addBook(bookId, isbnNumber, bookName, category) != null) {
			return "Book got Added Successfully";
		} else {
			return "Something went wrong !";
		}
	}

	// http://localhost:9191/addBook?bookId=1&isbnNumber=12345&bookName=JavaBasics&category=Programming
	// http://localhost:9191/getAllBooks
	// http://localhost:9191/getBook?category=Programming

	@RequestMapping("/getAllBooks")
	@ResponseBody
	public List<Book> getBooks() {
		return bookService.getAllBooks();
	}

	@RequestMapping("/getBook")
	@ResponseBody
	public List<Book> getBook(@RequestParam("category") String category) {
		return bookService.getBookByCategory(category);
	}

	@RequestMapping("/getBookById")
	@ResponseBody
	public Book getBookById(@RequestParam("bookId") long bookId) {
		return bookService.getBookByBookId(bookId);
	}

	@RequestMapping("/deleteBook")
	@ResponseBody
	public String deleteBook(@RequestParam("bookId") int bookId) {
		if (bookService.deleteBook(bookId) == 1) {
			return "Book got Deleted Successfully";
		} else {
			return "Something went wrong !";
		}
	}

	// @PostConstruct
	public void sendMail() {

		System.out.println(utilitys.getDateTimeBasedOnTimeZone(null));
		System.out.println(utilitys.convertToIndianCurrency(345.78));

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("akash922.g@gmail.com");
		message.setSubject("Subject of the email");
		message.setText("<html><body><h1>Hello, World!</h1><p>This is a test HTML email.</p></body></html>");
		message.setFrom("ghussenaiah@gmail.com");

	
	}
}
