package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Repository.IJournalEntryRepository;
import net.engineeringdigest.journalApp.Services.SJournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

// Controller --> Service --> Repository
@RestController
@RequestMapping("/journals/v2")
public class JournalEntryControllerV2 {
    
    @Autowired
    private SJournalEntryService sJournalEntryService;
    
    @GetMapping
    public List<JournalEntry> getAll(){
        return sJournalEntryService.getAllJournalEntries();
    }
    
    @PostMapping
    public Object createEntry(@RequestBody JournalEntry journalEntry){
        journalEntry.setCreationTime(LocalDateTime.now());
        return sJournalEntryService.create(journalEntry);
    }
    
    @GetMapping("/id/{journal_id}")
    public JournalEntry getById(@PathVariable ObjectId journal_id) {
        return sJournalEntryService.getJournalEntryById(journal_id).orElse(null);
    }

    @DeleteMapping("/id/{journal_id}")
    public boolean deleteById(@PathVariable ObjectId journal_id){
        sJournalEntryService.deleteById(journal_id);
        return true;
    }

    @PutMapping("/id/{journal_id}")
    public JournalEntry updateById(@PathVariable ObjectId journal_id, @RequestBody JournalEntry newJournalEntry){
        JournalEntry oldEntry = sJournalEntryService.getJournalEntryById(journal_id).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newJournalEntry.getTitle() != null && !newJournalEntry.getTitle().isEmpty() ? newJournalEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newJournalEntry.getContent() != null && !newJournalEntry.getContent().isEmpty() ? newJournalEntry.getContent() : oldEntry.getContent());
        }
        sJournalEntryService.create(oldEntry);
        return oldEntry;
    }
    
}
