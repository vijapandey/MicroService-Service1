/**
 * 
 */
package com.service.app.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.app.model.Note;
import com.service.app.service.NotesService;

/**
 * @author vijay pandey
 *
 */
//@RepositoryRestResource
@RestController
@RequestMapping(path = "/api/note")
public class NoteDBController {
	
    private static final Logger logger = LoggerFactory.getLogger(NoteDBController.class);
	
	@Autowired
	NotesService notesService;

	@PostMapping(path = "/add", produces = "application/json", consumes = "application/json", headers = "Accept=application/json") 
	@PreAuthorize("hasRole('USER')")
	public @ResponseBody String addNewNote(@RequestBody Note note) {
		logger.info("Going to save content" + note.getCreatedAt());
		Date date = new Date();
		note.setCreatedAt(date);
		note.setUpdatedAt(date);
		notesService.save(note);
		return "Saved";
	}

    @GetMapping(path="/", produces = "application/json", consumes = "application/json", headers = "Accept=application/json")
	@PreAuthorize("hasRole('USER')")
	public @ResponseBody Iterable<Note> getAllUsers() {
		logger.debug("Going to fetch note ..............................");
		// This returns a JSON or XML with the users
		return notesService.getAllUsers();
	}
}
