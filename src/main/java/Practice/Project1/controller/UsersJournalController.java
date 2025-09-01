package Practice.Project1.controller;

import Practice.Project1.Services.EmailService;
import Practice.Project1.Services.JournalService;
import Practice.Project1.Services.RedisService;
import Practice.Project1.Services.UsersJournalService;
import Practice.Project1.entity.JournalEntries;
import Practice.Project1.entity.UsersJournal;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

@RestController
@RequestMapping("/users/journal")
public class UsersJournalController {

    private final UsersJournalService usersJournalService;
    private final EmailService emailService;
    private final RedisService redisService;

    UsersJournalController(UsersJournalService usersJournalService,
                           EmailService emailService,RedisService redisService){
        this.usersJournalService=usersJournalService;
        this.emailService=emailService;
        this.redisService=redisService;
    }

    @PostMapping
    public ResponseEntity<UsersJournal> createEntry(@RequestBody UsersJournal usersJournal) {
        return ResponseEntity.ok(usersJournalService.createUser(usersJournal));
    }

    @GetMapping
    public ResponseEntity<List<UsersJournal>> getAllEntries() {
        return ResponseEntity.ok(usersJournalService.getAllUsers());
    }

    @PutMapping("/{userName}")
    public ResponseEntity<UsersJournal> getUserByUserName(@RequestBody UsersJournal usersJournal,@PathVariable String userName) {
        UsersJournal usersJournalInDb=usersJournalService.getUserByUserName(userName);
        if(usersJournalInDb!=null){
            usersJournalInDb.setUserName(usersJournal.getUserName());
            usersJournalInDb.setPassword(usersJournal.getPassword());
            return ResponseEntity.ok(usersJournalService.saveUser(usersJournalInDb));
        }
         return ResponseEntity.notFound().build();
    }

    @GetMapping("/custom")
    public ResponseEntity<List<UsersJournal>> getValidEmailAndSA(){
        return new ResponseEntity<>(usersJournalService.getCustomQuery(),OK);
    }

    @PostMapping("email/{to}/{body}/{subject}")
    public String emailSender(@PathVariable String to,
                              @PathVariable String body,
                              @PathVariable String subject){
        emailService.sendEmail(to,body,subject);
        return "Email Sent SuccessFully";
    }

    @GetMapping("/{username}")
    public UsersJournal getUserByUerName(@PathVariable String username){
         UsersJournal cachedUser=redisService.get(username,UsersJournal.class);
         if(cachedUser==null){
             UsersJournal userToBeCached=usersJournalService.getUserByUserName(username);
             redisService.set(username,userToBeCached, 300L);
             return userToBeCached;
         }
         return cachedUser;
    }


}
