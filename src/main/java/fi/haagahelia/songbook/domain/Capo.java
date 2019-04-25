package fi.haagahelia.songbook.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Capo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long capoid;
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "capo")
	private List<Song> songs;

	public Capo() {
	}

	public Capo(String name) {
		super();
		this.name = name;
	}

	public Long getCapoid() {
		return capoid;
	}

	public void setCapoid(Long capoid) {
		this.capoid = capoid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Capo [capoid=" + capoid + ", name=" + name + "]";
	}

}
