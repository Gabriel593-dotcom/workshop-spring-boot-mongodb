package workshop.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import workshop.domain.Post;
import workshop.domain.User;
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
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem.", "Vou viajar para São Paulo. Abraços!",
				maria);
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", maria);

		userRepository.saveAll(Arrays.asList(maria, alex, roberto));
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
