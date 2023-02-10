package work.worker.server;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
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
import work.worker.server.Repositories.CoverImgRepository;
import work.worker.server.models.Author;
import work.worker.server.models.Book;
import work.worker.server.models.CoverImg;

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

    /**cover img repo. */
    CoverImgRepository coverImgRepo;

    /**
     * sets CoverImgRepo to passed in CoverImgRepo.
     * This lets you pass in a mock during testing
     * @param newCoverImgRepo reposatory for CoverImg class
     */
    @Autowired
    public void setCoverImgRepo(final CoverImgRepository newCoverImgRepo) {
        this.coverImgRepo = newCoverImgRepo;
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
        String imageID = rootNode
            .get("covers")
            .get(0)
            .asText();
        JsonNode authorList = rootNode
        .get("authors");

        Set<Author> authors = getAuthorsFromAPI(authorList);

        CoverImg image = saveCover(imageID);

        Book book = new Book(isbn,
            bookName,
            pageCount,
            publisher,
            image,
            authors
        );
        bookRepo.save(book);
        return book;
    }

    private CoverImg saveCover(final String imageID) {
        byte[] image = restTemplate.getForObject(
            "https://covers.openlibrary.org/b/ID/" + imageID + "-S.jpg",
            byte[].class,
            1
        );
        CoverImg coverImg = new CoverImg(imageID, image);
        return coverImg;
    }

    /**
     * gets img from database.
     * @param imageID
     * @return the image
     */
    public byte[] imageGet(final String imageID) {
        CoverImg img = coverImgRepo.findById(imageID).get();
        return img.getCoverPicture();
    }
}
