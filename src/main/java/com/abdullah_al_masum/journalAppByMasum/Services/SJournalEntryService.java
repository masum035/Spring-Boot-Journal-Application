package com.abdullah_al_masum.journalAppByMasum.Services;

import com.abdullah_al_masum.journalAppByMasum.Repository.IJournalEntryRepository;
import com.abdullah_al_masum.journalAppByMasum.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SJournalEntryService {
    
    @Autowired
    private IJournalEntryRepository iJournalEntryRepository;
    
    public JournalEntry create(JournalEntry journalEntry) {
        return iJournalEntryRepository.save(journalEntry);
    }
    
    public List<JournalEntry> getAllJournalEntries(){
        return iJournalEntryRepository.findAll();
    }
    
    public Optional<JournalEntry> getJournalEntryById(ObjectId id){
        return iJournalEntryRepository.findById(id);
    }
    
    public void deleteById(ObjectId id){
        iJournalEntryRepository.deleteById(id);
    }
    
}
