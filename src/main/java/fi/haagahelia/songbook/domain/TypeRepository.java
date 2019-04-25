package fi.haagahelia.songbook.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Long> {
	//Find song type by name
	List<Type> findByName(String name);

}
