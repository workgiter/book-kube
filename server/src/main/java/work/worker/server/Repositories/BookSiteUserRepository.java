package work.worker.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import work.worker.server.models.BookSiteUser;

public interface BookSiteUserRepository
extends JpaRepository<BookSiteUser, String> {

}
