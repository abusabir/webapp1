package ru.abusabir.webapp.storage;

import ru.abusabir.webapp.WebAppException;
import ru.abusabir.webapp.model.Resume;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Oleg
 * 06.10.2016
 */
abstract public class AbstractStorage implements IStorage {
    protected int size = 0;
    protected Logger logger = Logger.getLogger(getClass().getName());

    public void clear() {
        logger.info("Delete all resumes");
        doClear();
        size = 0;
    }

    protected abstract void doClear();

    public void save(Resume r) {
        logger.info("Save resume with uuid: " + r.getUuid());
        if (exist(r.getUuid())) throw new WebAppException("Resume " + r.getUuid() + "already exist", r);
        doSave(r);
    }

    protected abstract void doSave(Resume r);

    public void update(Resume r) {
        logger.info("Update resume with uuid: " + r.getUuid());
        if (!exist(r.getUuid())) throw new WebAppException("Resume " + r.getUuid() + "not exist", r);
        doUpdate(r);
    }

    protected abstract void doUpdate(Resume r);

    public Resume load(String uuid) {
        logger.info("Load resume with uuid: " + uuid);
        if (!exist(uuid)) throw new WebAppException("Resume " + uuid + "not exist");
        return doLoad(uuid);
    }

    protected abstract Resume doLoad(String uuid);

    public void delete(String uuid) {
        logger.info("Delete resume with uuid: " + uuid);
        if (!exist(uuid)) throw new WebAppException("Resume " + uuid + "not exist");
        doDelete(uuid);
    }

    protected abstract void doDelete(String uuid);

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("getAllSorted");
        List<Resume> list = doGetAll();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> doGetAll();

    public int size() {
        return size;
    }

    protected abstract boolean exist(String uuid);
}
