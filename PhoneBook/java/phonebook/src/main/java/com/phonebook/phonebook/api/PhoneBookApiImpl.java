package com.phonebook.phonebook.api;

import com.phonebook.phonebook.model.Contact;
import com.phonebook.phonebook.model.SearchRequest;
import com.phonebook.phonebook.service.ContactService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@AllArgsConstructor
public class PhoneBookApiImpl implements PhoneBookApi {

    ContactService contactService;

    @Override
    public Contact addContact(Contact contact) throws ExecutionException, InterruptedException {
        return contactService.addContact(contact);
    }

    @Override
    public List<Contact> findAll() throws ExecutionException, InterruptedException {
        return contactService.findAll();
    }

    @Override
    public List<Contact> findByField(SearchRequest searchRequest) throws ExecutionException, InterruptedException {
        return contactService.findByField(searchRequest);
    }
}
