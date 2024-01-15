package org.example.addressbook

import org.assertj.core.api.Assertions.assertThat
import org.example.addressbook.api.entity.Contact
import org.example.addressbook.repo.ContactRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class ContactRepositoryTest @Autowired constructor(
    val repository: ContactRepository,
) {

    @Test
    fun `should save and retrieve a contact`() {
        val contact = Contact(name = "John Doe", phoneNumber = "1234567890", address = "Anytown, USA")
        val savedContact = repository.save(contact)

        val foundContact = repository.findById(savedContact.id!!).get()
        assertThat(foundContact).isEqualTo(savedContact)
    }

    @Test
    fun `should find contact by ID`() {
        val contact = repository.save(Contact(name = "John Doe", phoneNumber = "1234567890", address = "Anytown, USA"))

        val result = repository.findById(contact.id!!).get()
        assertThat(result).isEqualTo(contact)
    }

    @Test
    fun `should find all contacts`() {
        val contacts = listOf(
            Contact(name = "Bob Johnson", phoneNumber = "08033675428", address = "Ebinpejo lane, idumota, lagos."),
            Contact(name = "Chidozie Nnamdi", phoneNumber = "09055567342", address = "Aba main-market"),
            Contact(name = "Alabi Ikudaisi", phoneNumber = "09087654321", address = "Anthony village, okoko")
        )
        repository.saveAll(contacts)

        val foundContacts = repository.findAll()
        assertThat(foundContacts).containsExactlyInAnyOrderElementsOf(contacts)
    }

    @Test
    fun `should delete contact by ID`() {
        val contact = repository.save(Contact(name = "Alabi Ikudaisi", phoneNumber = "09087654321", address = "Anthony village, okoko"))

        repository.deleteById(contact.id!!)
        assertThat(repository.findById(contact.id!!)).isEmpty
    }
}