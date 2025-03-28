package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//expects id ka datatype inside the generics
public interface UserRepository extends MongoRepository<User, ObjectId> {
    //findBy method username-->field ka naam
User findByUsername(String username);


    void deleteByUsername(String name);
}
