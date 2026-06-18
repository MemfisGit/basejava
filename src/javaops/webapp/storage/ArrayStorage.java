package javaops.webapp.storage;

import javaops.webapp.model.Resume;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {
    private final int capacity = 10000;
    private Resume[] storage = new Resume[capacity];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        int index = getIndexById(r.getUuid());
        if (index >= 0) {
            System.out.println("Резюме " + r.getUuid() + " уже добавлено в массив");
        } else {
            if (size <= capacity - 1) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Массив ArayStorage переполнен");
            }
        }
    }

    public Resume get(String uuid) {
        int index = getIndexById(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " не найдено");
            return null;
        } else {
            Resume r = storage[index];
            return r;
        }
    }

    public void delete(String uuid) {
        int index = getIndexById(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " не найдено");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
        return;
    }

    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }

        return resumes;
    }

    public void update(Resume r) {
        int index = getIndexById(r.getUuid());
        if (index < 0) {
            System.out.println("Резюме " + r.getUuid() + " не найдено");
        } else {
            storage[index] = r;
        }
    }

    private int getIndexById(String searchId) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            String targetId = storage[i].getUuid();
            if (searchId.equals(targetId)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public int size() {
        return size;
    }
}
