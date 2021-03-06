package workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workshop.domain.User;
import workshop.dto.UserDTO;
import workshop.repository.UserRepository;
import workshop.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException());
	}

	public User insert(User obj) {
		return userRepository.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}

	public User update(User user) {
		User updatedUser = findById(user.getId());
		updateData(updatedUser, user);

		return userRepository.save(updatedUser);
	}

	private void updateData(User updatedUser, User user) {
		updatedUser.setName(user.getName());
		updatedUser.setEmail(user.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}

}
