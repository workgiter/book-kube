package work.worker.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import work.worker.server.models.Book;

@RestController()
@RequestMapping(path = "/books")
@CrossOrigin("*")
public class BookController {

    @Autowired
    BookService bookService;// = new BookService();
    
    @GetMapping(path="/list", produces = "application/json")
    List<Book> getBooks () throws JsonMappingException, JsonProcessingException {
        List<Book> book = bookService.getBooks();
        return book;
    }

    @GetMapping(path="/steal/{isbn}", produces = "application/json")
    void stealBooksFromAPI(@PathVariable long isbn) throws JsonMappingException, JsonProcessingException{
        bookService.stealBooksFromAPI(isbn);
    }
    //https://www.googleapis.com/books/v1/volumes?q=isbn:9780141365442
}
