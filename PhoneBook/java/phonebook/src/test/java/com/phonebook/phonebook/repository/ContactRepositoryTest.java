package com.phonebook.phonebook.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.phonebook.phonebook.model.Contact;
import com.phonebook.phonebook.model.SearchRequest;
import com.phonebook.phonebook.service.ContactService;
import com.phonebook.phonebook.service.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactRepositoryTest {

    Firestore firestore;
    ContactRepository contactRepository;

    @BeforeEach
    void setup() {
        firestore = mock(Firestore.class);
        contactRepository = new ContactRepositoryImpl(firestore);
    }

    @Test
    void addContact(@Mock Contact contact,
                    @Mock CollectionReference collectionReference,
                    @Mock ApiFuture<DocumentReference> apiFuture,
                    @Mock DocumentReference documentReference,
                    @Mock DocumentSnapshot documentSnapshot,
                    @Mock ApiFuture<DocumentSnapshot> documentSnapshotApiFuture) throws ExecutionException, InterruptedException {

        when(firestore.collection(anyString())).thenReturn(collectionReference);
        when(collectionReference.add(contact)).thenReturn(apiFuture);
        when(apiFuture.get()).thenReturn(documentReference);
        when(documentReference.get()).thenReturn(documentSnapshotApiFuture);
        when(documentSnapshotApiFuture.get()).thenReturn(documentSnapshot);
        when(documentSnapshot.toObject(Contact.class)).thenReturn(contact);
        contactRepository.addContact(contact);
        verify(firestore,atLeast(1)).collection(anyString());
    }

    @Test
    void findAll(@Mock List<Contact> contacts,
                 @Mock CollectionReference collectionReference,
                 @Mock ApiFuture<QuerySnapshot> querySnapshotApiFuture,
                 @Mock QuerySnapshot querySnapshot) throws ExecutionException, InterruptedException {

        when(firestore.collection(anyString())).thenReturn(collectionReference);
        when(collectionReference.get()).thenReturn(querySnapshotApiFuture);
        when(querySnapshotApiFuture.get()).thenReturn(querySnapshot);
        when(querySnapshot.toObjects(Contact.class)).thenReturn(contacts);
        contactRepository.findAll();
        verify(querySnapshot,atLeast(1)).toObjects(Contact.class);
    }

    @Test
    void findByField(@Mock List<Contact> contacts,
                     @Mock SearchRequest searchRequest,
                     @Mock CollectionReference collectionReference,
                     @Mock Query query,
                     @Mock ApiFuture<QuerySnapshot> querySnapshotApiFuture,
                     @Mock QuerySnapshot querySnapshot) throws ExecutionException, InterruptedException {

        when(searchRequest.getField()).thenReturn("field");
        when(searchRequest.getValue()).thenReturn("value");
        when(firestore.collection(anyString())).thenReturn(collectionReference);
        when(collectionReference.whereEqualTo(anyString(),anyString())).thenReturn(query);
        when(query.get()).thenReturn(querySnapshotApiFuture);
        when(querySnapshotApiFuture.get()).thenReturn(querySnapshot);
        when(querySnapshot.toObjects(Contact.class)).thenReturn(contacts);
        contactRepository.findByField(searchRequest);
        verify(querySnapshot,atLeast(1)).toObjects(Contact.class);
    }

}