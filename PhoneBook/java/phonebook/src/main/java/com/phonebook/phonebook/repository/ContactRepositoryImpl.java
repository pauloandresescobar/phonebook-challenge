package com.phonebook.phonebook.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.phonebook.phonebook.model.Contact;
import com.phonebook.phonebook.model.SearchRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Slf4j
@Repository
@AllArgsConstructor
public class ContactRepositoryImpl implements ContactRepository{

    private final Firestore firestore;

    @Override
    public Contact addContact(Contact contact) throws ExecutionException, InterruptedException {
        final CollectionReference reference = getCollectionName();
        final ApiFuture<DocumentReference> apiFuture = reference.add(contact);
        Contact contactResult = apiFuture.get().get().get().toObject(Contact.class);
        log.info("added new contact {}", contactResult);
        return contactResult;
    }

    @Override
    public List<Contact> findAll() throws ExecutionException, InterruptedException {
        final CollectionReference reference = getCollectionName();
        QuerySnapshot query = reference.get().get();
        return query.toObjects(Contact.class);
    }

    @Override
    public List<Contact> findByField(SearchRequest searchRequest) throws ExecutionException, InterruptedException {
            CollectionReference userCollection = getCollectionName();
            Query query = userCollection.whereEqualTo(searchRequest.getField(), searchRequest.getValue());
            return query.get().get().toObjects(Contact.class);
    }

    private CollectionReference getCollectionName(){
        return firestore.collection(Contact.class.getSimpleName());
    }
}
