package work.worker.server.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import work.worker.server.models.Book;

public interface BookRepository extends JpaRepository<Book, String> {
    /**finds all books in DB.
     * @return books
     */
    List<Book> findAll();

    /**
     * should find .
     * @param isbn
     * @return books data
     */
    List<Book> findByIsbn(String isbn);

    /**
     * finds book posted by username.
     * @param isbn
     * @param username
     * @return books
     */
    List<Book> findByIsbnAndAddedBy_Username(String isbn, String username);

    //todo: clean up this for fix it so it works.
    // /**
    //  * insert user.
    //  * @param isbn
    //  * @return new user
    //  */
    // @Modifying
    // @Query("insert into books (isbn) select :isbn")
    // int modifyingQueryInsertBooks(@Param("isbn")String isbn);
}
