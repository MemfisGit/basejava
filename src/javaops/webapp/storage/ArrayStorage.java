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
        Resume old = get(r.getUuid());
        if (old == null) {
            if (size < capacity) {
                storage[size] = r;
                size++;
            }
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            Resume r = storage[i];
            if (r.getUuid().equals(uuid)) {
                return r;
            }
        }

        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            Resume r = storage[i];
            if (r.getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }

        return resumes;
    }

    public void update(Resume r) {
        int index = getIndexById(r.getUuid());
        if (index > -1) {
            storage[index] = r;
        } else {
            System.out.println("Резюме " + r.getUuid() + " " +  " не найдено");
        }
    }


    private int getIndexById(String searchId) {
        int result = -1;
        for(int i = 0; i < size; i++) {
            String targetId = storage[i].getUuid();
            if (searchId.equals(targetId)) {
                result = i;
            }
        }

        return result;
    }

    public int size() {
        return size;
    }

}
