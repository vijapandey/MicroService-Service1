package com.service.app.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.service.app.dao.NoteRepository;
import com.service.app.model.Note;

/**
 * @Transactional can be used on Method level or Class level
 * @author vijpande
 *
 */
@Repository
//@Transactional(timeout = 1)
public class NotesService {

	/**
	 * This is Synchronous call , we set timeout is 1 sec so it will automatically
	 * timeout.throw TransactionException
	 */
	//@Transactional(timeout = 1)
	public void save(Note note) {
		/*
		 * try { // Thread.sleep(1); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		noteRepository.save(note);
	}

	public Iterable<Note> getAllUsers() {
		// This returns a JSON or XML with the users
		return noteRepository.findAll();
	}

	// An EntityManager will be automatically injected from entityManagerFactory
	// setup on DatabaseSource class.
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	NoteRepository noteRepository;

	public Optional<Note> find(Long id) {
		return noteRepository.findById(id);
	}
}
