package com.abdullah_al_masum.journalAppByMasum.Controller;

import com.abdullah_al_masum.journalAppByMasum.Entity.User;
import com.abdullah_al_masum.journalAppByMasum.Services.SJournalEntryService;
import com.abdullah_al_masum.journalAppByMasum.Entity.JournalEntry;
import com.abdullah_al_masum.journalAppByMasum.Services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Controller --> Service --> Repository
@RestController
@RequestMapping("/journals")
public class JournalEntryController {

    @Autowired
    private SJournalEntryService sJournalEntryService;
    @Autowired
    private  UserService userService;
    
    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User byUserName = userService.findByUserName(userName);
        List<JournalEntry> allJournalEntries = byUserName.getJournalEntries();
        if(allJournalEntries != null && !allJournalEntries.isEmpty()) {
            return new ResponseEntity<>(allJournalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{username}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry journalEntry, @PathVariable String username) {
        try {
            journalEntry.setCreationTime(LocalDateTime.now());
            sJournalEntryService.create(journalEntry, username);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{journal_id}")
    public ResponseEntity<?> getById(@PathVariable ObjectId journal_id) {
        Optional<JournalEntry> journalEntryById = sJournalEntryService.getJournalEntryById(journal_id);
        if (journalEntryById.isPresent()) {
            return new ResponseEntity<>(journalEntryById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{username}/{journal_id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId journal_id, @PathVariable String username) {
        sJournalEntryService.deleteById(journal_id, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{username}/{journal_id}")
    public ResponseEntity<?> updateById(@PathVariable String username,
                                        @PathVariable ObjectId journal_id,
                                        @RequestBody JournalEntry newJournalEntry) {
        JournalEntry oldEntry = sJournalEntryService.getJournalEntryById(journal_id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setTitle(newJournalEntry.getTitle() != null && !newJournalEntry.getTitle().isEmpty() ? newJournalEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newJournalEntry.getContent() != null && !newJournalEntry.getContent().isEmpty() ? newJournalEntry.getContent() : oldEntry.getContent());
            sJournalEntryService.create(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
