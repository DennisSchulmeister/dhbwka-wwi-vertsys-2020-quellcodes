package com.example.demo.api.users.data;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * Copyright © 2020 Martin Kutscher
 * 
 * E-Mail: martin.kutscher@exxeta.com
 * 
 * Dieser Quellcode ist lizenziert unter einer Creative Commons Namensnennung
 * 4.0 International Lizenz.
 * 
 * Data Access Object, um die Benutzer auszulesen. Es wereden Standard
 * CRUD-Methoden automatisch bereitgestellt. Das Interface Kann um weitere
 * Methoden ergaenzt werden.
 * 
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}