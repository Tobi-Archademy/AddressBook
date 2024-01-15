package org.example.addressbook.api.controller

import org.example.addressbook.api.entity.Contact
import org.example.addressbook.repo.ContactRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/contacts")
class ContactController(private val repository: ContactRepository) {

    @GetMapping("/")
    fun getAllContacts(): List<Contact> = repository.findAll() // fetches all contacts

    @PostMapping
    fun createContact(@RequestBody contact: Contact): Contact = repository.save(contact) // create a new contact and adds it to the list

    @GetMapping("/{id}")
    fun getContactById(@PathVariable id: Long): Optional<Contact> = repository.findById(id) // fetch a contact by its id

    @DeleteMapping("/{id}")
    fun deleteContact(@PathVariable id: Long) = repository.deleteById(id) // delete a contact by its id
}