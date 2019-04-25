package fi.haagahelia.songbook;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.songbook.domain.Capo;
import fi.haagahelia.songbook.domain.CapoRepository;
import fi.haagahelia.songbook.domain.Genre;
import fi.haagahelia.songbook.domain.GenreRepository;
import fi.haagahelia.songbook.domain.Song;
import fi.haagahelia.songbook.domain.SongRepository;
import fi.haagahelia.songbook.domain.Type;
import fi.haagahelia.songbook.domain.TypeRepository;
import fi.haagahelia.songbook.domain.User;
import fi.haagahelia.songbook.domain.UserRepository;

//Testing JPA repositories 

@RunWith(SpringRunner.class)
@DataJpaTest
public class SongbookTesting {
	@Autowired
	private CapoRepository crepository;
	@Autowired
	private GenreRepository grepository;
	@Autowired
	private SongRepository srepository;
	@Autowired
	private TypeRepository trepository;
	@Autowired
	private UserRepository urepository;
	
	//Create new capo option 
	@Test
	public void createNewCapoOption(){
		Capo capo = new Capo("13th fret");
		crepository.save(capo);
		assertThat(capo.getCapoid()).isNotNull();
	}
	
	//Delete capo option
	@Test 
	public void deleteCapo(){
		List<Capo> capo= crepository.findByName("1st fret");
		crepository.deleteById((capo.get(0).getCapoid()));
	}
	
	//Search for the option "No capo" and adding to a list 
	@Test
	public void searchCapo(){
		List<Capo> capo=crepository.findByName("No capo");
		assertThat(capo.get(0).getCapoid()).isNotNull();
	}
	
	//Create new genre  
	@Test
	public void createNewGenre(){
		Genre genre = new Genre("Indie");
		grepository.save(genre);
		assertThat(genre.getGenreid()).isNotNull();
	}
	
	//Delete genre 
	@Test 
	public void deleteGenre(){
		List<Genre> genre= grepository.findByName("Pop");
		grepository.deleteById((genre.get(0).getGenreid()));
	}
	
	//Search for the genre "Pop" and adding to a list 
	@Test
	public void searchGenre(){
		List<Genre> genre=grepository.findByName("Rock");
		assertThat(genre.get(0).getGenreid()).isNotNull();
	}
	
	//Create new song  
	@Test
	public void createNewSong(){
		Song song = new Song("Losing my religion","R.E.M.", 1991, "Easy", grepository.findByName("Rock").get(0), crepository.findByName("No capo").get(0), trepository.findByName("Chords").get(0));
		srepository.save(song);
		assertThat(song.getId()).isNotNull();
	}
	
	//Delete song 
	@Test 
	public void deleteSong(){
		Song song= srepository.findByYear(1990);
		srepository.deleteById((song.getId()));
	}
	
	//Find song by year and adding to a list 
	@Test
	public void searchSong(){
		Song song=srepository.findByYear(2015);
		assertThat(song.getDifficulty()).isEqualTo("Easy");
	}
	
	//Create new Type option 
	@Test
	public void createNewTypeOption(){
		Type type = new Type("Chords+Tabs");
		trepository.save(type);
		assertThat(type.getTypeid()).isNotNull();
	}
		
	//Delete type option
	@Test 
	public void deleteType(){
		List<Type> type= trepository.findByName("Tab");
		trepository.deleteById((type.get(0).getTypeid()));
	}
		
	//Search for the option "Chords" and adding to a list 
	@Test
	public void searchType(){
		List<Type> type=trepository.findByName("Chords");
		assertThat(type.get(0).getTypeid()).isNotNull();
	}
	
	//Create new user
	@Test
	public void createUser(){
		User user= new User("minerva", "$2a$04$7KsHcQjGaM99aZ6UaSa3O.rLB8huuz1A776Cq7.GCptZCqcftxKlC","user3@gmail.com", "USER");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	//Delete user
	@Test
	public void deleteUser(){
		User user= urepository.findByUsername("user2");
		urepository.deleteById(user.getId());
	}
	
	//Search for user
	@Test
	public void searchUser(){
		User user = urepository.findByUsername("user");
		assertThat(user.getRole()).isEqualTo("USER");
	}
}
