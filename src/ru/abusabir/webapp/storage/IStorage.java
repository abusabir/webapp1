package ru.abusabir.webapp.storage;

import ru.abusabir.webapp.model.Resume;

import java.util.Collection;

/**
 * Oleg
 * 05.10.2016
 */
public interface IStorage {
    void clear();

    void save(Resume r);

    void update(Resume r);

    Resume load(String uuid);

    void delete(String uuid);

    Collection<Resume> getAllSorted();

    int size();
}
