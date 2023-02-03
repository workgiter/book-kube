package work.worker.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import work.worker.server.Repositories.BookRepository;
import work.worker.server.models.Author;
import work.worker.server.models.Book;

@Component
public class BookService {

    /**object used for making http get request to google api. */
    RestTemplate restTemplate; // = new RestTemplate();

    /**
     * sets rest template.
     * @param restTemplateNew
     */
    @Autowired
    public void setRestTemplate(final RestTemplate restTemplateNew) {
        this.restTemplate = restTemplateNew;
    }

    /**reposatory for Book class. */
    BookRepository bookRepo;

    /**
     * sets bookRepo to passed in book repo.
     * This lets you pass in a mock repon during testing
     * @param newBookRepo reposatory for book class
     */
    @Autowired
    public void setBookRepo(final BookRepository newBookRepo) {
        this.bookRepo = newBookRepo;
    }

    BookService() { }

    /**
     * returns list of books and authors from SQL database.
     * @return list of Book
     */
    public List<Book> getBooks() {
        List<Book> books = bookRepo.findAll();
        return books;
    }

    private Set<Author> getAuthorsFromAPI(final JsonNode authorList)
    throws JsonMappingException, JsonProcessingException {
        Set<Author> authors = new HashSet<Author>();
        ObjectMapper mapper = new ObjectMapper();

        for (final JsonNode objNode : authorList) {
            String authorID = objNode.get("key").asText();
            String url = "https://openlibrary.org"
            + authorID
            + ".json";
            String authorJson = restTemplate.getForObject(url, String.class, 1);
            JsonNode authorNode = mapper.readTree(authorJson);
            String authorName = authorNode.get("personal_name").asText();
            Author author = new Author(authorID, authorName);
            authors.add(author);
        }
        return authors;
    }

    /**
     * Read the google api to get infomation for a given ISBN number
     * then saves the infomation we are intrested in into a local DB.
     * @param isbn
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @return book that was saved
     */
    public Book stealBooksFromAPI(final String isbn)
    throws JsonMappingException, JsonProcessingException {
        String url = "https://openlibrary.org/isbn/"
        + isbn.toString()
        + ".json";
        String json = restTemplate.getForObject(url, String.class, 1);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        String bookName = rootNode.get("title")
            .asText();
        int pageCount = rootNode
            .get("number_of_pages")
            .asInt();
        String publisher = rootNode
            .get("publishers")
            .get(0)
            .asText();
        JsonNode authorList = rootNode
        .get("authors");

        Set<Author> authors = getAuthorsFromAPI(authorList);

        //Long id = (long) 1;
        Book book = new Book(isbn,
            bookName,
            pageCount,
            publisher,
            authors
        );
        bookRepo.save(book);
        return book;
    }
}
