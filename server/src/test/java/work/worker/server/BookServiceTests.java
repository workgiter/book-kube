package work.worker.server;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import work.worker.server.Repositories.BookRepository;
import work.worker.server.models.Book;

public class BookServiceTests {
    /**book service. */
    BookService bookService;

    /**mock of book repository. */
    @Mock
    BookRepository bookRepository;

    /**mock rest template. */
    @Mock
    RestTemplate mockRestTemplate;

    /**mock book repo before each test. */
    @BeforeEach
    void setUpRepoMock() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookService();
        bookService.setBookRepo(bookRepository);
        mockRestTemplate = new RestTemplate();
        bookService.setRestTemplate(mockRestTemplate);
    }

    @Test
    void getBooksTest() {
        Assertions.assertTrue(
            bookService.getBooks() instanceof List<Book>
        );
    }

    @Test
    void stealBooksFromAPITest() {

        String url = "https://openlibrary.org/isbn/9780140328721.json";

        when(mockRestTemplate.getForObject(url, String.class, 1))
        .thenReturn("asdfasdffsdfsdafsd");

        try {
            bookService.stealBooksFromAPI("9780140328721");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
