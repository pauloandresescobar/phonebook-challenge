package com.phonebook.phonebook.api;

import com.phonebook.phonebook.model.Contact;
import com.phonebook.phonebook.model.SearchRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "api/v1/contact")
public interface PhoneBookApi {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    Contact addContact(@RequestBody Contact contact) throws ExecutionException, InterruptedException;

    @GetMapping
    List<Contact> findAll() throws ExecutionException, InterruptedException;

    @PostMapping("/search")
    List<Contact> findByField(@RequestBody SearchRequest searchRequest) throws ExecutionException, InterruptedException;
}
