package fi.haagahelia.songbook.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
	//Find user by username
	UserInfo findByUsername(final String username);
}
