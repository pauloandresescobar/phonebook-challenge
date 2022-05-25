package com.phonebook.phonebook.api;

import com.phonebook.phonebook.model.Contact;
import com.phonebook.phonebook.model.SearchRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RequestMapping(path = "api/v1/user")
public interface PhoneBookApi {

  // POST http://localhost:9091/user

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  Contact addContact(@RequestBody Contact contact) throws ExecutionException, InterruptedException;

  // GET http://localhost:9091/user

  @GetMapping
  List<Contact> findAll() throws ExecutionException, InterruptedException;

  @PostMapping("/search")
  List<Contact> findByField(@RequestBody SearchRequest searchRequest) throws ExecutionException, InterruptedException;
}
