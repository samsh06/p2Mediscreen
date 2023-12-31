package com.medinote.medinote.service;

import java.util.List;

import com.medinote.medinote.Exceptions.NoteNotFoundException;
import com.medinote.medinote.model.Note;

public interface INoteService {
    List<Note> getAllNotes();

    Note findNoteById(String id) throws NoteNotFoundException;

    Note saveNote(Note note);

    Note updateNote(Note note) throws NoteNotFoundException;

    Note deleteNote(String id) throws NoteNotFoundException;

    List<Note> findNoteByPatientId(Long id) throws NoteNotFoundException;
}