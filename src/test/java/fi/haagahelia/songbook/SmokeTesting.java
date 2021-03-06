package fi.haagahelia.songbook;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.songbook.web.SongController;

//Testing that context is creating the controller 

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTesting {

	@Autowired
	private SongController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
