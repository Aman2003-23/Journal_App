package net.engineeringdigest.journalApp.entity;

import java.util.*;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


import java.util.Collection;
import java.util.Collections;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    private ObjectId id;
    //keeps username unique
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;
    private String email;
    private boolean SentimentAnalysis ;
    //only id has been embedded from the journal_Entries collection
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> roles ;
}
