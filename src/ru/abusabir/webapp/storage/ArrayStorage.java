package ru.abusabir.webapp.storage;

import ru.abusabir.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Oleg
 * 05.10.2016
 */
public class ArrayStorage extends AbstractStorage {
    public static final int LIMIT = 100;
    //    protected Logger LOGGER = Logger.getLogger(getClass().getName());

    private Resume[] array = new Resume[LIMIT];


    @Override
    public void doClear() {
        Arrays.fill(array, null);
    }


    @Override
    public void doSave(Resume r) {
        array[size++] = r;
    }

    @Override
    public void doUpdate(Resume r) {
        //TODO: убрать дублирование
        int i = getIndex(r.getUuid());
        array[i] = r;
    }

    @Override
    public Resume doLoad(String uuid) {
        //TODO: убрать дублирование
        int i = getIndex(uuid);
        return array[i];
    }

    @Override
    public void doDelete(String uuid) {
        int i = getIndex(uuid);
        System.arraycopy(array, i + 1, array, i, size - i - 1);
        array[--size] = null;
    }

    @Override
    public List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOf(array, size));
    }


    protected int getIndex(String uuid) {
        for (int i = 0; i < LIMIT; i++) {
            if (array[i] == null) continue;
            if (array[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public boolean exist(String uuid) {
        return (getIndex(uuid) != -1);
    }
}
