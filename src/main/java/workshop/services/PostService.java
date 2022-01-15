package workshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workshop.domain.Post;
import workshop.repository.PostRepository;
import workshop.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		
		Optional<Post> post = postRepository.findById(id); 
		return post.orElseThrow(() -> new ObjectNotFoundException());
	}
}
