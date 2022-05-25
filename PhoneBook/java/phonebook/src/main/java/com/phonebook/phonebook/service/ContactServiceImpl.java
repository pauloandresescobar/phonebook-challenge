package com.phonebook.phonebook.service;

import com.phonebook.phonebook.model.Contact;
import com.phonebook.phonebook.model.SearchRequest;
import com.phonebook.phonebook.repository.ContactRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {
    ContactRepository contactRepository;

    @Override
    public Contact addContact(Contact contact) throws ExecutionException, InterruptedException {
        return contactRepository.addContact(contact);
    }

    @Override
    public List<Contact> findAll() throws ExecutionException, InterruptedException {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> findByField(SearchRequest searchRequest) throws ExecutionException, InterruptedException {
        return contactRepository.findByField(searchRequest);
    }
}
