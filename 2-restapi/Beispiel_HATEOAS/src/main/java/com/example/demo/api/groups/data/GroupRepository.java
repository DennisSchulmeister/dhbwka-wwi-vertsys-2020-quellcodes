package com.example.demo.api.groups.data;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data Access Object, um die Gruppen auszulesen. Es wereden Standard CRUD-Methoden automatisch bereitgestellt. 
 * Das Interface Kann um weitere Methoden ergaenzt werden.
 * 
*/
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}