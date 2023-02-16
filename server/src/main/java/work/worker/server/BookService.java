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
import work.worker.server.Repositories.BookSiteUserRepository;
import work.worker.server.Repositories.CoverImgRepository;
import work.worker.server.Repositories.SiteUserRepository;
import work.worker.server.models.Author;
import work.worker.server.models.Book;
import work.worker.server.models.BookSiteUser;
import work.worker.server.models.CoverImg;
import work.worker.server.models.SiteUser;

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
    /**site user repo. */
    SiteUserRepository siteUserRepo;
    /**book site user repo. */
    BookSiteUserRepository bookSiteUserRepo;

    /**
     * sets CoverImgRepo to passed in CoverImgRepo.
     * This lets you pass in a mock during testing
     * @param newCoverImgRepo reposatory for CoverImg class
     * @param newSiteUserRepo reposatory for siteUser class
     * @param newBookSiteUserRepo
     */
    @Autowired
    public void setRepo(final CoverImgRepository newCoverImgRepo,
    final SiteUserRepository newSiteUserRepo,
    final BookSiteUserRepository newBookSiteUserRepo) {
        this.coverImgRepo = newCoverImgRepo;
        this.siteUserRepo = newSiteUserRepo;
        this.bookSiteUserRepo = newBookSiteUserRepo;
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

    /**
     * return list of books added by user.
     * @param user
     * @return books
     */
    public Set<Book> getBooksFromUser(final String user) {
        SiteUser userRequested = siteUserRepo.findById(user).orElse(null);
        Set<Book> userBooks = userRequested.getBook();
        return userBooks;
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
     * if code called with ony one param.
     * @param isbn
     * @return null book
     */
    public Book stealBooksFromAPI(final String isbn)
    throws JsonMappingException, JsonProcessingException {
        return stealBooksFromAPI(isbn, "null");
    }

    /**
     * Read the google api to get infomation for a given ISBN number
     * then saves the infomation we are intrested in into a local DB.
     * @param isbn
     * @param user
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @return book that was saved
     */
    public Book stealBooksFromAPI(final String isbn, final String user)
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

        Boolean bookExists = bookRepo.existsById(isbn);
        if (bookExists) {
            Book book = bookRepo.getReferenceById(isbn);
            book.setBookName(bookName);
            book.setPageCount(pageCount);
            book.setPublisher(publisher);
            book.setCoverImg(image);
            book.setWrittenBy(authors);

            bookRepo.save(book);

            SiteUser newUser = new SiteUser(user, null);
            siteUserRepo.save(newUser);

            BookSiteUser newBookUserJoin = new BookSiteUser(isbn, user);
            bookSiteUserRepo.save(newBookUserJoin);

            return null;
        } else {
            Set<SiteUser> users;
            users = new HashSet<SiteUser>();
            users.add(new SiteUser(user, null));

            Book book = new Book(isbn,
                bookName,
                pageCount,
                publisher,
                image,
                authors,
                users
            );
            return bookRepo.save(book);
        }

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


    /**asdf. */
    public void testPath() {
        System.out.println("HHHHHHHHHHHHHHHHHHH"
        + "HHHHHHHHHHHHHHHHHHHHHHHHHHH   this got here   "
        + "HHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
    }

}
