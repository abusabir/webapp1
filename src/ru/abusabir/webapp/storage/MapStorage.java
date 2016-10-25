package ru.abusabir.webapp.storage;

import ru.abusabir.webapp.model.Resume;

import java.util.*;

/**
 * Oleg
 * 25.10.2016
 */
public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<String, Resume>();

    @Override
    protected void doSave(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected Resume doLoad(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    public Collection<Resume> getAllSorted() {
        //TODO: sort
        Collection<Resume> list = storage.values();
        return list;
    }

    @Override
    protected boolean checkIsExist(Resume r) {
        return storage.containsValue(r);
    }

    @Override
    protected boolean checkIsExist(String uuid) {
        return storage.containsKey(uuid);
    }
}
