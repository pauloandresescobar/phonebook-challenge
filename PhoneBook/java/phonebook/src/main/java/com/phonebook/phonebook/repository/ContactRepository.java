package com.phonebook.phonebook.repository;

import com.phonebook.phonebook.model.Contact;
import com.phonebook.phonebook.model.SearchRequest;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ContactRepository {

    Contact addContact(Contact contact) throws ExecutionException, InterruptedException;

    List<Contact> findAll() throws ExecutionException, InterruptedException;

    List<Contact> findByField(SearchRequest searchRequest) throws ExecutionException, InterruptedException;

}
