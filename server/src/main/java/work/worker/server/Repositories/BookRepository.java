package work.worker.server.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import work.worker.server.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    /**finds all books in DB.
     * @return books
     */
    List<Book> findAll();
}
