package com.service.app.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.service.app.model.Note;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository //CRUD refers Create, Read, Update, Delete
//@Transactional
//public interface UserRepository extends CrudRepository<User, Integer> {}

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

}
