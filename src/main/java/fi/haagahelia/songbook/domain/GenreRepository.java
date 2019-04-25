package fi.haagahelia.songbook.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre,Long> {
	//Find genre by name
	List<Genre> findByName(String name);
}
