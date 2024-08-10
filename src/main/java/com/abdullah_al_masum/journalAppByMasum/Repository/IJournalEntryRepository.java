package com.abdullah_al_masum.journalAppByMasum.Repository;

import com.abdullah_al_masum.journalAppByMasum.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IJournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
