package work.worker.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import work.worker.server.models.Author;

public interface AuthorRepository  extends JpaRepository<Author, Long> {

}
