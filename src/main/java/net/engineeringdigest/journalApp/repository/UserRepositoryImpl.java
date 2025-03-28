package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {


    @Autowired
    public MongoTemplate mongoTemplate;



    public List<User> getUserForSA() {
        //query and criteria run hand in hand with the help of each  other
        Query query = new Query();
       // query.addCriteria(Criteria.where("username").is("aman"));
         query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));

      //  query.addCriteria(Criteria.where("email").exists(true));

       // query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("SentimentAnalysis").is(true));


      List<User> users= mongoTemplate.find(query, User.class);
      return  users;
    }
}
