package ru.abusabir.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.abusabir.webapp.WebAppException;
import ru.abusabir.webapp.model.Contact;
import ru.abusabir.webapp.model.ContactType;
import ru.abusabir.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorageTest {

    private Resume R1, R2, R3;

    private ArrayStorage storage = new ArrayStorage();


    @Before
    public void before() {
        R1 = new Resume("Name1", "loc1");
        R1.addContact(new Contact(ContactType.MAIL, "mail@ya.ru"));
        R1.addContact(new Contact(ContactType.PHONE, "111222333"));

        R2 = new Resume("Name2", "loc2");
        R2.addContact(new Contact(ContactType.SKYPE, "mail123"));
        R2.addContact(new Contact(ContactType.PHONE, "333444555"));

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
        r.addContact(new Contact(ContactType.HOME_PHONE, "345345"));
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

    @Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2, storage.size());

    }

    @Test
    public void testGetAllSorted() throws Exception {
        Resume[] src = new Resume[]{R1, R2, R3};
        Arrays.sort(src);
        Assert.assertArrayEquals(src, storage.getAllSorted().toArray());
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, storage.size());
    }
}