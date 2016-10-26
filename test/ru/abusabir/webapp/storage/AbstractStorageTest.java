package ru.abusabir.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.abusabir.webapp.WebAppException;
import ru.abusabir.webapp.model.ContactType;
import ru.abusabir.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract public class AbstractStorageTest {

    private Resume R1, R2, R3;

    protected IStorage storage;


    @Before
    public void before() {
        R1 = new Resume("Name1", "loc1");
        R1.addContact(ContactType.MAIL, "mail@ya.ru");
        R1.addContact(ContactType.PHONE, "111222333");

        R2 = new Resume("Name2", "loc2");
        R2.addContact(ContactType.SKYPE, "mail123");
        R2.addContact(ContactType.PHONE, "333444555");

        R3 = new Resume("Name3", null);

        storage.clear();
        storage.save(R3);
        storage.save(R1);
        storage.save(R2);
    }


    @Test
    public void testClear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void testSave() throws Exception {
        Resume r = new Resume("Name", "loc");
        r.addContact(ContactType.HOME_PHONE, "345345");
        storage.save(r);
        Assert.assertNotNull(storage.load(r.getUuid()));
    }

    @Test
    public void testUpdate() throws Exception {
        R2.setFullName("Updated N2");
        storage.update(R2);
        Assert.assertEquals(R2, storage.load(R2.getUuid()));
    }

    @Test
    public void testLoad() throws Exception {
        Assert.assertEquals(storage.load(R1.getUuid()), R1);
        Assert.assertEquals(storage.load(R2.getUuid()), R2);
        Assert.assertEquals(storage.load(R3.getUuid()), R3);
    }

    @Test(expected = WebAppException.class)
    public void testNotFound() throws Exception{
        storage.load("sdfgsdfg");
    }

    @Test(expected = WebAppException.class)
    public void testSavePresented() {
        storage.save(R1);
    }

    @Test(expected = WebAppException.class)
    public void testUpdateMissed() {
        Resume resume = new Resume("dummy", "Russia");
        storage.update(resume);
    }

    @Test(expected = WebAppException.class)
    public void testDeleteMissed() {
        storage.delete("dummy");
    }

    @Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2, storage.size());

    }

    @Test
    public void testGetAllSorted() throws Exception {
        List<Resume> list = Arrays.asList(R1, R2, R3);
        Collections.sort(list, (Resume o1, Resume o2) -> {
                int cmp = o1.getFullName().compareTo(o2.getFullName());
                if(cmp != 0) return cmp;
                return o1.getUuid().compareTo(o2.getUuid());
        });

        Assert.assertEquals(list, storage.getAllSorted());
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, storage.size());
    }
}