package work.worker.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import work.worker.server.models.CoverImg;

public interface CoverImgRepository extends JpaRepository<CoverImg, String> {

}
