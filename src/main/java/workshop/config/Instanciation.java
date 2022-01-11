package workshop.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import workshop.domain.User;
import workshop.repository.UserRepository;

@Configuration
public class Instanciation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		User maria = new User(null, "Maria Souza", "maria@teste.com");
		User alex = new User(null, "Alex Ferreira", "alex@teste.com");
		User roberto = new User(null, "Roberto Nogueira", "roberto@teste.com");

		userRepository.deleteAll();
		userRepository.saveAll(Arrays.asList(maria, alex, roberto));
	}

}
