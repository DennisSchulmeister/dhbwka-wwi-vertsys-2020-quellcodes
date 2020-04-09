package com.example.demo.api.users.data;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data Access Object, um die Benutzer auszulesen. Es wereden Standard CRUD-Methoden automatisch bereitgestellt. 
 * Das Interface Kann um weitere Methoden ergaenzt werden.
 * 
*/
public interface UserRepository extends JpaRepository<UserEntity, Long>{
}