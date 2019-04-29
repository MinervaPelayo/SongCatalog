package fi.haagahelia.songbook.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.haagahelia.songbook.domain.UserInfo;
import fi.haagahelia.songbook.domain.UserInfoRepository;

//Implement UserDetailService to authenticate and authorize user

@Service
public class UserDetailService implements UserDetailsService {
	@Autowired
	private UserInfoRepository repository;

	
    public UserDetailService() {
        super();
    }
	
	public UserDetailService(UserInfoRepository userInfoRepository) {
		this.repository = userInfoRepository;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final UserInfo curruser = repository.findByUsername(username);
        if (curruser == null) {
            throw new UsernameNotFoundException(username);
        }
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}
}
