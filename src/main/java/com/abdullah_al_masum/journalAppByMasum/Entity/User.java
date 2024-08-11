package com.abdullah_al_masum.journalAppByMasum.Entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String userName;
    @NonNull
    private String userPassword;
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
