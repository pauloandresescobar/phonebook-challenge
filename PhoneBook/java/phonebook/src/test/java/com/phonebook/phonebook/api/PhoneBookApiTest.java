package com.phonebook.phonebook.api;

import com.phonebook.phonebook.model.Contact;
import com.phonebook.phonebook.model.SearchRequest;
import com.phonebook.phonebook.repository.ContactRepository;
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
import static org.mockito.Mockito.atLeast;

@ExtendWith(MockitoExtension.class)
class PhoneBookApiTest {


    PhoneBookApi api;
    ContactService contactService;

    @BeforeEach
    void setup() {

        contactService = mock(ContactService.class);
        api = new PhoneBookApiImpl(contactService);
    }

    @Test
    void addContact(@Mock Contact contact) throws ExecutionException, InterruptedException {
        when(contactService.addContact(contact)).thenReturn(contact);
        api.addContact(contact);
        verify(contactService,atLeast(1)).addContact(contact);
    }

    @Test
    void findAll(@Mock List<Contact> contacts) throws ExecutionException, InterruptedException {
        when(contactService.findAll()).thenReturn(contacts);
        api.findAll();
        verify(contactService,atLeast(1)).findAll();
    }

    @Test
    void findByField(@Mock SearchRequest searchRequest, @Mock List<Contact> contacts) throws ExecutionException, InterruptedException {
        when(contactService.findByField(searchRequest)).thenReturn(contacts);
        api.findByField(searchRequest);
        verify(contactService,atLeast(1)).findByField(searchRequest);
    }
}