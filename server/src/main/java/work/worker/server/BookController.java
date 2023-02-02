package work.worker.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import work.worker.server.models.Book;

@RestController()
@RequestMapping(path = "/books")
@CrossOrigin("*")
public class BookController {

    /**Auto wires the book service class. */
    @Autowired
    BookService bookService;

    /**
     * Get's list of books and their authors from the database.
     * @return list of Book Objects
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    @GetMapping(path = "/list", produces = "application/json")
    List<Book> getBooks()
    throws JsonMappingException, JsonProcessingException {
        try {
            List<Book> books = bookService.getBooks();
            return books;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "could not read data from database");
        }
    }

    /**
     * take in the isbn number of a book and makes a request to
     * the open library books api, then copies parts of the result
     * into a local database.
     * @param isbn isbn number referancing a book
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @return book that was saved
     */
    @GetMapping(path = "/steal/{isbn}", produces = "application/json")
    Book stealBooksFromAPI(@PathVariable final String isbn)
    throws JsonMappingException, JsonProcessingException {
        try {
            return bookService.stealBooksFromAPI(isbn);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "data not saved");
        }
    }
    //https://www.googleapis.com/books/v1/volumes?q=isbn:9780141365442
    //https://openlibrary.org/isbn/9780140328721.json
}
