package fi.haagahelia.songbook.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String artist;
	private int year;
	private String difficulty;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "genreid")
	private Genre genre;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "capoid")
	private Capo capo;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "typeid")
	private Type type;

	public Song() {

	}

	public Song(String title, String artist, int year, String difficulty, Genre genre, Capo capo, Type type) {
		super();
		this.title = title;
		this.artist = artist;
		this.year = year;
		this.difficulty = difficulty;
		this.genre = genre;
		this.capo = capo;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Capo getCapo() {
		return capo;
	}

	public void setCapo(Capo capo) {
		this.capo = capo;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", title=" + title + ", artist=" + artist + ", year=" + year + ", difficulty="
				+ difficulty + ", genre=" + genre + ", capo=" + capo + ", type=" + type + "]";
	}

}
