package javaops.webapp.storage;

import java.util.Arrays;
import javaops.webapp.model.Resume;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {

    private static final int capacity = 10000;
    private Resume[] storage = new Resume[capacity];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Резюме " + r.getUuid() + " уже добавлено в хранилище");
        } else {
            if (size <= capacity - 1) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Хранилище переполнено");
            }
        }
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " не найдено");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " не найдено");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Резюме " + r.getUuid() + " не найдено");
        } else {
            storage[index] = r;
        }
    }

    private int findResumeIndex(String searchUuid) {
       for (int i = 0; i < size; i++) {
            String targetUuid = storage[i].getUuid();
            if (searchUuid.equals(targetUuid)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }
}
