package org.example.addressbook.repo

import org.example.addressbook.api.entity.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<Contact, Long>