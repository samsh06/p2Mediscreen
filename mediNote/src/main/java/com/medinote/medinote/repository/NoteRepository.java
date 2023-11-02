package com.medinote.medinote.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.medinote.medinote.model.Note;

/**
 * Note Repository
 */
@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

public List<Note> findByPatientId(Long id);


}