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
    public List<Resume> doGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected boolean exist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
