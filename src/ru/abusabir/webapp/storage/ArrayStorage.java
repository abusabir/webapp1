package ru.abusabir.webapp.storage;

import ru.abusabir.webapp.WebAppException;
import ru.abusabir.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Oleg
 * 05.10.2016
 */
public class ArrayStorage implements IStorage {
    public static final int LIMIT = 100;
    //    protected Logger LOGGER = Logger.getLogger(getClass().getName());
    private static Logger LOGGER = Logger.getLogger(ArrayStorage.class.getName());

    private Resume[] array = new Resume[LIMIT];
    private int size = 0;

    @Override
    public void clear() {
        LOGGER.info("Delete all resumes");
        Arrays.fill(array, null);
        size = 0;
    }


    @Override
    public void save(Resume r) {
        LOGGER.info("Save resume with uuid: " + r.getUuid());
        int idx = getIndex(r.getUuid());
//            try {
//                throw new WebAppException("Resume " + r.getUuid() + "already exist", r);
//            } catch (WebAppException e) {
//                LOGGER.log(Level.SEVERE, e.getMessage(), e);
//            }

        if (idx != -1) throw new WebAppException("Resume " + r.getUuid() + "already exist", r);
        array[size++] = r;


    }

    @Override
    public void update(Resume r) {
        LOGGER.info("Update resume with uuid: " + r.getUuid());
        int i = getIndex(r.getUuid());
        if (i == -1) throw new WebAppException("Resume " + r.getUuid() + "not exist", r);
        array[i] = r;

    }

    @Override
    public Resume load(String uuid) {
        LOGGER.info("Load resume with uuid: " + uuid);
        int i = getIndex(uuid);
        if (i == -1) throw new WebAppException("Resume " + uuid + "not exist");
        return array[i];
    }

    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete resume with uuid: " + uuid);
        int i = getIndex(uuid);
        if (i == -1) throw new WebAppException("Resume " + uuid + "not exist");
        System.arraycopy(array, i + 1, array, i, size - i - 1);
        array[--size] = null;
    }

    @Override
    public Collection<Resume> getAllSorted() {
        Arrays.sort(array, 0, size);
        return Arrays.asList(Arrays.copyOf(array, size));
    }

    @Override
    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < LIMIT; i++) {
            if (array[i] == null) continue;
            if (array[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
