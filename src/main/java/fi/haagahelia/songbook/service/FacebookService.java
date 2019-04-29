package fi.haagahelia.songbook.service;

import org.springframework.social.facebook.api.User;

public interface FacebookService {

	String facebooklogin();

	String getFacebookAccessToken(String code);

	User getFacebookUserProfile(String accessToken);

}
