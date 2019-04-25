package fi.haagahelia.songbook.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import fi.haagahelia.songbook.domain.FileModel;
import fi.haagahelia.songbook.domain.FileModelRepository;
import fi.haagahelia.songbook.domain.CapoRepository;
import fi.haagahelia.songbook.domain.GenreRepository;
import fi.haagahelia.songbook.domain.Song;
import fi.haagahelia.songbook.domain.SongRepository;
import fi.haagahelia.songbook.domain.TypeRepository;

@Controller
public class SongController {
	// Read upload.path value and save it to uploadFolder variable
	@Value("${upload.path}")
	private String uploadFolder;
	// Save repositories to different variables
	@Autowired
	private SongRepository repository;
	@Autowired
	private GenreRepository grepository;
	@Autowired
	private CapoRepository crepository;
	@Autowired
	private TypeRepository trepository;
	@Autowired
	private FileModelRepository frepository;

	// Show login page for authentication
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// Show all songs in Thymeleaf template
	@RequestMapping(value = "/songlist")
	public String songList(Model model) {
		model.addAttribute("songs", repository.findAll());
		return "songlist";
	}

	// Show template to add a new song
	@RequestMapping(value = "/add")
	public String addSong(Model model) {
		model.addAttribute("song", new Song());
		model.addAttribute("genres", grepository.findAll());
		model.addAttribute("capos", crepository.findAll());
		model.addAttribute("types", trepository.findAll());
		return "addsong";
	}

	// Save the song and redirect to songlist template to show all songs
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Song song) {
		repository.save(song);
		return "redirect:songlist";
	}

	// Delete song from database
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteSong(@PathVariable("id") Long songId, Model model) {
		repository.deleteById(songId);
		return "redirect:../songlist";
	}

	// Show template to edit song information
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editSong(@PathVariable("id") Long songId, Model model) {
		model.addAttribute("song", repository.findById(songId));
		model.addAttribute("genres", grepository.findAll());
		model.addAttribute("capos", crepository.findAll());
		model.addAttribute("types", trepository.findAll());
		return "editsong";
	}

	// RESTful service to get all songs
	@RequestMapping(value = "/song", method = RequestMethod.GET)
	public @ResponseBody List<Song> songListRest() {
		return (List<Song>) repository.findAll();
	}

	// RESTful service to get song by id
	@RequestMapping(value = "/song/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Song> findSongRest(@PathVariable("id") Long songId) {
		return repository.findById(songId);
	}

	// Show upload files page
	@RequestMapping(value = "/uploadfile")
	public String uploadFile() {
		return "uploadfile";
	}

	// Save uploaded file
	@PostMapping("/upload")
	public String fileUpload(@RequestParam("file") MultipartFile file, Model model) {
		if (file.isEmpty()) {
			model.addAttribute("msg", "Upload failed");
			return "uploadstatus";
		}
		try {
			FileModel fileModel = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
			frepository.save(fileModel);

			return "redirect:/files";
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "uploadstatus";
	}

	// Fetching all song files
	@GetMapping("/files")
	public String fileList(Model model) {
		model.addAttribute("files", frepository.findAll());
		return "filelist";
	}

	// File download
	@GetMapping("/file/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		Optional<FileModel> fileOptional = frepository.findById(id);

		if (fileOptional.isPresent()) {
			FileModel file = fileOptional.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
					.body(file.getFile());
		}

		return ResponseEntity.status(404).body(null);
	}

}
