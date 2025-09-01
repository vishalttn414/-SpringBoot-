package Practice.Project1.controller;


import Practice.Project1.Services.JournalService;
import Practice.Project1.Services.UsersJournalService;
import Practice.Project1.entity.JournalEntries;
import Practice.Project1.entity.UsersJournal;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private final JournalService journalService;

    private final UsersJournalService usersJournalService;

    JournalEntryController(JournalService journalService,UsersJournalService usersJournalService){
        this.journalService=journalService;
        this.usersJournalService=usersJournalService;
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntries> createEntry(@RequestBody JournalEntries entry,@PathVariable String userName) {
        return ResponseEntity.ok(journalService.saveEntry(entry,userName));
    }

    @GetMapping("/{userName}")
    public ResponseEntity<List<JournalEntries>> getAllJournalEntriesOfUser(@PathVariable String userName) {
        UsersJournal user=usersJournalService.getUserByUserName(userName);
        List<JournalEntries> allJournalEntries=user.getJournalEntries();
        if(!allJournalEntries.isEmpty()){
            return new ResponseEntity<>(allJournalEntries,OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntries> getEntryById(@PathVariable ObjectId id) {
        return journalService.getEntryById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/{userName}")
    public ResponseEntity<JournalEntries> updateEntry(@PathVariable ObjectId id,
                                                      @RequestBody JournalEntries updatedEntry,
                                                          @PathVariable String userName) {
        return journalService.updateEntry(id, updatedEntry)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/{userName}")
    public ResponseEntity<Void> deleteEntry(@PathVariable ObjectId id,@PathVariable String userName) {
        journalService.deleteEntry(id,userName);
        return ResponseEntity.noContent().build();
    }
}

