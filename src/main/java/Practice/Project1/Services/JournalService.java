package Practice.Project1.Services;

import Practice.Project1.entity.JournalEntries;
import Practice.Project1.entity.UsersJournal;
import Practice.Project1.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    private final JournalEntryRepository journalEntryRepository;

    private final UsersJournalService usersJournalService;

    JournalService(JournalEntryRepository journalEntryRepository,UsersJournalService usersJournalService){
        this.journalEntryRepository=journalEntryRepository;
        this.usersJournalService=usersJournalService;
    }

    public JournalEntries createEntry(JournalEntries entry) {
        entry.setDate(LocalDateTime.now());
        return journalEntryRepository.save(entry);
    }

    @Transactional
    public JournalEntries saveEntry(JournalEntries entry,String userName) {
        try{
            UsersJournal user=usersJournalService.getUserByUserName(userName);
            entry.setDate(LocalDateTime.now());
            JournalEntries savedEntry = journalEntryRepository.save(entry);
            user.getJournalEntries().add(savedEntry);
            user.setUserName(null);
            usersJournalService.saveUser(user);
            return savedEntry;
        } catch (Exception e) {
            throw new RuntimeException("An Exception has occurred while saving data : " + e);
        }

    }


    public List<JournalEntries> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntries> getEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public Optional<JournalEntries> updateEntry(ObjectId id, JournalEntries updatedEntry) {
        return journalEntryRepository.findById(id).map(existing -> {
            existing.setTitle(updatedEntry.getTitle());
            existing.setContent(updatedEntry.getContent());

            return journalEntryRepository.save(existing);
        });
    }

    public void deleteEntry(ObjectId id, String userName) {
        UsersJournal user=usersJournalService.getUserByUserName(userName);
        user.getJournalEntries().removeIf((entry)->entry.getId().equals(id));
        usersJournalService.saveUser(user);
        journalEntryRepository.deleteById(id);
    }
}
