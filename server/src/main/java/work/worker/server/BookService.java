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

    RestTemplate restTemplate = new RestTemplate();

    BookRepository bookRepo;

    @Autowired
    public void setBookRepo(final BookRepository newBookRepo) {
        this.bookRepo = newBookRepo;
    }

    BookService() { }

    // @Autowired
    // public void setRestTemplate(RestTemplateBuilder restTemplateBuilder) {
    //     this.restTemplate = restTemplateBuilder.build();
    // }
    
    public List<Book> getBooks() {
        List<Book> books = bookRepo.findAll();
        //List<Book> books = null;
        return books;
    }

    public void stealBooksFromAPI(Long isbn) throws JsonMappingException, JsonProcessingException {
        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:"+isbn.toString();
        String json = restTemplate.getForObject(url, String.class, 1);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        rootNode = rootNode.get("items").get(0);
        String bookName = rootNode.get("volumeInfo").get("title").toString();
        String description = rootNode
            .get("volumeInfo")
            .get("description")
            .asText();
        int pageCount = rootNode
            .get("volumeInfo")
            .get("pageCount")
            .asInt();
        String publisher = rootNode
            .get("volumeInfo")
            .get("publisher")
            .asText();
        String ISBN = isbn.toString();
        JsonNode authorName = rootNode
        .get("volumeInfo")
        .get("authors");

        Set<Author> authors = new HashSet<Author>();

        for (final JsonNode objNode : authorName) {
            Author author = new Author(objNode.asText());
            authors.add(author);
        }
        Book book = new Book((long)100, 
            bookName,  
            description, 
            pageCount, 
            publisher, 
            ISBN,
            authors
        );
        bookRepo.save(book);
    }
}
