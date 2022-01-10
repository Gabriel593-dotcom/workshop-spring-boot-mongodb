package workshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import workshop.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
