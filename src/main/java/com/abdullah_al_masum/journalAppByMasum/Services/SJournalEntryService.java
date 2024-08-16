package com.abdullah_al_masum.journalAppByMasum.Services;

import com.abdullah_al_masum.journalAppByMasum.Entity.User;
import com.abdullah_al_masum.journalAppByMasum.Repository.IJournalEntryRepository;
import com.abdullah_al_masum.journalAppByMasum.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class SJournalEntryService {
    
    @Autowired
    private IJournalEntryRepository iJournalEntryRepository;
    @Autowired
    private UserService userService;
    
    public void create(JournalEntry journalEntry, String byUserName) {
        User user = userService.findByUserName(byUserName);
        journalEntry.setCreationTime(LocalDateTime.now());
        JournalEntry saved = iJournalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.create(user);
    }

    public void create(JournalEntry journalEntry) {
        iJournalEntryRepository.save(journalEntry);
    }
    
    public List<JournalEntry> getAllJournalEntries(){
        return iJournalEntryRepository.findAll();
    }
    
    public Optional<JournalEntry> getJournalEntryById(ObjectId id){
        return iJournalEntryRepository.findById(id);
    }
    
    public void deleteById(ObjectId id, String username){
        User user = userService.findByUserName(username);
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.create(user);
        iJournalEntryRepository.deleteById(id);
    }

    
}
