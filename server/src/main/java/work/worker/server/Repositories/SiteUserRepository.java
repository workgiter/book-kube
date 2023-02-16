package work.worker.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import work.worker.server.models.SiteUser;

public interface SiteUserRepository  extends JpaRepository<SiteUser, String> {

}
