package com.phonebook.phonebook.service;

import com.phonebook.phonebook.model.Contact;
import com.phonebook.phonebook.model.SearchRequest;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ContactService {

    Contact addContact(Contact contact) throws ExecutionException, InterruptedException;

    List<Contact> findAll() throws ExecutionException, InterruptedException;

    List<Contact> findByField(SearchRequest searchRequest) throws ExecutionException, InterruptedException;
}
