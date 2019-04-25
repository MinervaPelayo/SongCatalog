package fi.haagahelia.songbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.songbook.domain.User;
import fi.haagahelia.songbook.domain.UserRepository;
import fi.haagahelia.songbook.domain.Capo;
import fi.haagahelia.songbook.domain.CapoRepository;
import fi.haagahelia.songbook.domain.Genre;
import fi.haagahelia.songbook.domain.GenreRepository;
import fi.haagahelia.songbook.domain.Song;
import fi.haagahelia.songbook.domain.SongRepository;
import fi.haagahelia.songbook.domain.Type;
import fi.haagahelia.songbook.domain.TypeRepository;

@SpringBootApplication

public class SongbookApplication {
	private static final Logger log = LoggerFactory.getLogger(SongbookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SongbookApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(SongRepository repository, GenreRepository grepository, CapoRepository crepository, TypeRepository trepository, UserRepository urepository) {
		return (args) -> {
			//Creating and saving demo genres
			grepository.save(new Genre("Pop"));
			grepository.save(new Genre("Rock"));
			grepository.save(new Genre("Folk"));
			grepository.save(new Genre("Country"));
			grepository.save(new Genre("Blues"));
			
			//Creating and saving capo options
			crepository.save(new Capo("No capo"));
			crepository.save(new Capo("1st fret"));
			crepository.save(new Capo("2nd fret"));
			crepository.save(new Capo("3rd fret"));
			crepository.save(new Capo("4th fret"));
			crepository.save(new Capo("5th fret"));
			crepository.save(new Capo("6th fret"));
			crepository.save(new Capo("7th fret"));
			crepository.save(new Capo("8th fret"));
			crepository.save(new Capo("9th fret"));
			crepository.save(new Capo("10th fret"));
			crepository.save(new Capo("11th fret"));
			crepository.save(new Capo("12th fret"));
			
			//Creating and saving type
			trepository.save(new Type("Chords"));
			trepository.save(new Type("Tab"));
			
			//Creating and saving song information 
			repository.save(new Song("Let her go","Passenger", 2012, "Easy", grepository.findByName("Pop").get(0), crepository.findByName("7th fret").get(0), trepository.findByName("Chords").get(0)));
			repository.save(new Song("Stairway to heaven","Led Zeppelin", 1971, "Hard", grepository.findByName("Rock").get(0), crepository.findByName("No capo").get(0), trepository.findByName("Tab").get(0)));
			repository.save(new Song("Thinking out loud","Ed Sheeran", 2014, "Easy", grepository.findByName("Pop").get(0), crepository.findByName("2nd fret").get(0), trepository.findByName("Chords").get(0)));
			repository.save(new Song("Can't help falling in love","Elvis Presley", 1969, "Intermediate", grepository.findByName("Pop").get(0), crepository.findByName("2nd fret").get(0), trepository.findByName("Chords").get(0)));
			repository.save(new Song("Love yourself","Justin Bieber", 2015, "Easy", grepository.findByName("Pop").get(0), crepository.findByName("4th fret").get(0), trepository.findByName("Chords").get(0)));
			repository.save(new Song("Sweet child o' mine", "Guns N' Roses",1987, "Intermediate", grepository.findByName("Rock").get(0), crepository.findByName("No capo").get(0), trepository.findByName("Tab").get(0)));
			repository.save(new Song("Hey there Delilah","Plain White T's", 2012, "Easy", grepository.findByName("Folk").get(0), crepository.findByName("No capo").get(0), trepository.findByName("Tab").get(0)));
			repository.save(new Song("Wonderwall","Oasis", 1995, "Intermediate", grepository.findByName("Pop").get(0), crepository.findByName("No capo").get(0), trepository.findByName("Chords").get(0)));
			repository.save(new Song("More than words","Extreme", 1990, "Intermediate", grepository.findByName("Rock").get(0), crepository.findByName("No capo").get(0), trepository.findByName("Chords").get(0)));
			repository.save(new Song("Wake me up","Green Day", 2014, "Easy", grepository.findByName("Rock").get(0), crepository.findByName("No capo").get(0), trepository.findByName("Tab").get(0)));
			
			//Create and save demo users
			User user1 = new User("user", "$2a$10$0YrHuKVOJILuFAj060eLRe.ITw87ZufRGcHERlk5CUPaziVdjrHJ2","user1@gmail.com", "USER");
			User user2 = new User("user2", "$2a$10$0YrHuKVOJILuFAj060eLRe.ITw87ZufRGcHERlk5CUPaziVdjrHJ2","user2@gmail.com", "USER");
			urepository.save(user1);
			urepository.save(user2);
			
			//Fetching all songs
			for(Song song : repository.findAll()) {
				log.info(song.toString());
			}
		};
	}
}
