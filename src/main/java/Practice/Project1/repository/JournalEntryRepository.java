package Practice.Project1.repository;

import Practice.Project1.entity.JournalEntries;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntries, ObjectId> {
}
