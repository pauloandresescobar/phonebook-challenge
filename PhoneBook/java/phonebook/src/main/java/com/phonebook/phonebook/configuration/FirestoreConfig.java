package com.phonebook.phonebook.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FirestoreConfig {

  public static final String FIRESTORE_PROJECT_ID = "FIRESTORE_PROJECT_ID";

  @Bean
  public Firestore firestore() {
    try {

      final GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
      final FirebaseOptions options = FirebaseOptions.builder()
              .setCredentials(credentials)
              .setProjectId(System.getenv(FIRESTORE_PROJECT_ID))
              .build();
      FirebaseApp.initializeApp(options);
      return FirestoreClient.getFirestore();
    } catch (IOException e) {
      throw new BeanCreationException("Firestore Bean couldn't be created");
    }
  }
}
