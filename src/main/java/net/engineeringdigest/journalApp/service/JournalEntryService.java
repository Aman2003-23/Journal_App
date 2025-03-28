package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//contains business logic
@Component
public class JournalEntryService {
 @Autowired
 private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;



    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        //fetches username--> saves the journal entry in journal_entries in db and in saved local variable
        // then adds this saved journal entry to the user's journal entry list and then calls userservice to save the updated user
        try {
            //agar userrname null hua ya usem kuch garbar hui to journal entries ki bhi entrry roll back ho jayegi(data is not committed)
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);

            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {

            throw new RuntimeException("an error occured while saving journal entry", e);
        }
    }
    public void saveEntry(JournalEntry journalEntry) {
        //fetches username--> saves the journal entry in journal_entries in db and in saved local variable
        // then adds this saved journal entry to the user's journal entry list and then calls userservice to save the updated user

        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public  JournalEntry getJournalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }


    @Transactional
    public  void deleteJournalEntryById(ObjectId id, String username) {
        try {
            User user = userService.findByUserName(username);
            boolean removed = user.getJournalEntries().remove(journalEntryRepository.findById(id).orElse(null));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("an error occured while deleting journal entry", e);
        }
    }

    public   List<JournalEntry>  findByUserName(String username) {
        return userService.findByUserName(username).getJournalEntries();
    }

}
