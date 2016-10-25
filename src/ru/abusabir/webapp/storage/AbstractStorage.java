package ru.abusabir.webapp.storage;

import ru.abusabir.webapp.WebAppException;
import ru.abusabir.webapp.model.Resume;

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

    public void save(Resume r) {
        logger.info("Save resume with uuid: " + r.getUuid());
        if (checkIsExist(r)) throw new WebAppException("Resume " + r.getUuid() + "already exist", r);
        doSave(r);
    }

    public void update(Resume r) {
        logger.info("Update resume with uuid: " + r.getUuid());
        if (!checkIsExist(r)) throw new WebAppException("Resume " + r.getUuid() + "not exist", r);
        doUpdate(r);
    }

    public Resume load(String uuid) {
        logger.info("Load resume with uuid: " + uuid);
        if (!checkIsExist(uuid)) throw new WebAppException("Resume " + uuid + "not exist");
        return doLoad(uuid);
    }

    public void delete(String uuid) {
        logger.info("Delete resume with uuid: " + uuid);
        if (!checkIsExist(uuid)) throw new WebAppException("Resume " + uuid + "not exist");
        doDelete(uuid);
    }

    public int size() {
        return size;
    }

    protected abstract void doSave(Resume r);

    protected abstract void doUpdate(Resume r);

    protected abstract void doDelete(String uuid);

    protected abstract Resume doLoad(String uuid);

    protected abstract void doClear();

    protected abstract boolean checkIsExist(Resume r);

    protected abstract boolean checkIsExist(String uuid);
}
