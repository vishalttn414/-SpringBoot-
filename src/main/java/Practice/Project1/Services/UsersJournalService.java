package Practice.Project1.Services;

import Practice.Project1.entity.UsersJournal;
import Practice.Project1.repository.UsersJournalRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UsersJournalService {

    private final UsersJournalRepository usersJournalRepository;
    private final MongoTemplate mongoTemplate;

    UsersJournalService(UsersJournalRepository usersJournalRepository,MongoTemplate mongoTemplate){
        this.usersJournalRepository=usersJournalRepository;
        this.mongoTemplate=mongoTemplate;
    }

    public UsersJournal createUser(UsersJournal usersJournal){
        return usersJournalRepository.save(usersJournal);
    }
    public UsersJournal saveUser(UsersJournal usersJournal){
        return usersJournalRepository.save(usersJournal);
    }

    public List<UsersJournal> getAllUsers(){
        return usersJournalRepository.findAll();
    }

    public UsersJournal getUserByUserName(String userName){
        log.error("user NOt found in cached DB call was made");
        return usersJournalRepository.findByUserName(userName);
    }

    public List<UsersJournal> getCustomQuery(){
        Query query=new Query();
//        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,10}$"));
//        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
//        return mongoTemplate.find(query, UsersJournal.class);

        query.addCriteria(new Criteria().orOperator(
                Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,10}$"),
                Criteria.where("sentimentAnalysis").is(true)
        ));

        return mongoTemplate.find(query, UsersJournal.class);
    }

}
