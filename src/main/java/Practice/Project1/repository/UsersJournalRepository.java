package Practice.Project1.repository;

import Practice.Project1.entity.UsersJournal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersJournalRepository extends MongoRepository<UsersJournal, ObjectId> {
    UsersJournal findByUserName(String userName);
}
