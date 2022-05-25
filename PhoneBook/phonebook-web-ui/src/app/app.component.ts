import {Component} from '@angular/core';
import {Contact} from "./contact";
import {HttpClient, HttpHeaders} from "@angular/common/http";

class SearchItemsRequest {
  field!: string;
  value!: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'phonebook-v1';
  contact = new Contact();
  contacts: Contact[] = [];
  searchContactsRequest = new SearchItemsRequest();


  constructor(private http: HttpClient) {
    this.getContacts();
  }

  createContact() {
    const headers = new HttpHeaders()
      .set('ContentType', 'application/json');
    const resp = this.http.post<any>(`http://localhost:8080/api/v1/contact`, this.contact, {headers}).subscribe(value => {
        alert("contact added successfully");
        this.contact = new Contact();
      }
    );
    this.getContacts();
  }

  getContacts() {
    const headers = new HttpHeaders().set('ContentType', 'application/json');
    const resp = this.http.get<any>(`http://localhost:8080/api/v1/contact`, {headers}).subscribe(value => {
      this.contacts = value
      console.log(value);
    });
  }

  searchByField() {
    const headers = new HttpHeaders()
      .set('ContentType', 'application/json');
    const resp = this.http.post<any>(`http://localhost:8080/api/v1/contact/search`, this.searchContactsRequest, {headers}).subscribe(value => {
      this.contacts = value
      }
    );
  }

  clearSearch() {
    this.getContacts();
    this.contact = new Contact();
  }
}
