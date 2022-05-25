package com.phonebook.phonebook.service;

import com.phonebook.phonebook.model.Contact;
import com.phonebook.phonebook.model.SearchRequest;
import com.phonebook.phonebook.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {


    ContactService contactService;
    ContactRepository contactRepository;

    @BeforeEach
    void setup() {

        contactRepository = mock(ContactRepository.class);
        contactService = new ContactServiceImpl(contactRepository);
    }

    @Test
    void addContact(@Mock Contact contact) throws ExecutionException, InterruptedException {
        when(contactRepository.addContact(contact)).thenReturn(contact);
        contactService.addContact(contact);
        verify(contactRepository,atLeast(1)).addContact(contact);
    }

    @Test
    void findAll(@Mock List<Contact> contacts) throws ExecutionException, InterruptedException {
        when(contactRepository.findAll()).thenReturn(contacts);
        contactService.findAll();
        verify(contactRepository,atLeast(1)).findAll();
    }

    @Test
    void findByField(@Mock SearchRequest searchRequest, @Mock List<Contact> contacts) throws ExecutionException, InterruptedException {
        when(contactRepository.findByField(searchRequest)).thenReturn(contacts);
        contactService.findByField(searchRequest);
        verify(contactRepository,atLeast(1)).findByField(searchRequest);
    }
}