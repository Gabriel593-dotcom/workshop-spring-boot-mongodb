package workshop.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import workshop.domain.Post;
import workshop.domain.User;
import workshop.dto.AuthorDTO;
import workshop.dto.CommentDTO;
import workshop.repository.PostRepository;
import workshop.repository.UserRepository;

@Configuration
public class Instanciation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		User maria = new User(null, "Maria Souza", "maria@teste.com");
		User alex = new User(null, "Alex Ferreira", "alex@teste.com");
		User roberto = new User(null, "Roberto Nogueira", "roberto@teste.com");

		userRepository.deleteAll();
		postRepository.deleteAll();
		
		userRepository.saveAll(Arrays.asList(maria, alex, roberto));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem.", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(roberto));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
			
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
		
	}

}
