package work.worker.server;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import work.worker.server.Repositories.BookRepository;
import work.worker.server.models.Book;

public class BookServiceTests {
    /**book service. */
    BookService bookService;

    /**mock of book repository. */
    @Mock
    BookRepository bookRepository;

    /**mock book repo before each test. */
    @BeforeEach
    void setUpRepoMock() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookService();
        bookService.setBookRepo(bookRepository);
    }

    @Test
    void getBooksTest() {
        Assertions.assertTrue(
            bookService.getBooks() instanceof List<Book>
        );
    }

//     @Test
//     void stealBooksFromAPITest() {

//     }
}
