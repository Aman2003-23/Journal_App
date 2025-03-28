package net.engineeringdigest.journalApp.repository;
import  net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
//expects id ka datatype inside the generics
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
