package fi.haagahelia.songbook.domain;

import org.springframework.data.repository.CrudRepository;


public interface SongRepository extends CrudRepository<Song,Long>{
	Song findByYear(int year);
}
