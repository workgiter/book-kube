package work.worker.server;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    /**asdf. */
    @GetMapping(path = "/test", produces = "application/json")
    void testPath() {
        bookService.testPath();
    }

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
     * Get's list of books and their authors added by a
     * given user from the database.
     * @param user
     * @return list of Book Objects
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    @GetMapping(path = "/list/{user}", produces = "application/json")
    Set<Book> getBooksFromUser(@PathVariable final String user)
    throws JsonMappingException, JsonProcessingException {
        try {
            Set<Book> books = bookService.getBooksFromUser(user);
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
     * @param user user submiting request
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @return book that was saved
     */
    @GetMapping(path = "/steal/{isbn}/{user}", produces = "application/json")
    Book stealBooksFromAPI(@PathVariable final String isbn,
    @PathVariable final String user)
    throws JsonMappingException, JsonProcessingException {
        try {
            return bookService.stealBooksFromAPI(isbn, user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "data not saved");
        }
    }

    /**
     * funtion that returns image with the given cover ID.
     * @return image.
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * @param coverID id of cover in database
     */
    @GetMapping(
        path = "/cover/{coverID}",
        produces =  MediaType.IMAGE_JPEG_VALUE
    )
    byte[] imageGet(@PathVariable final String coverID)
    throws JsonMappingException, JsonProcessingException {
        return bookService.imageGet(coverID);
    }
}
