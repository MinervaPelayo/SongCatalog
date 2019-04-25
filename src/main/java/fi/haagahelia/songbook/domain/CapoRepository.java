package fi.haagahelia.songbook.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CapoRepository extends CrudRepository<Capo, Long> {
	//Find Capo by name 
	List<Capo> findByName(String name);

}
