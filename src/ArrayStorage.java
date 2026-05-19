/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final int capacity = 10000;
    Resume[] storage = new Resume[capacity];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        Resume old = get(r.uuid);
        if (old == null) {
            if (size < capacity) {
                storage[size] = r;
            }
            size++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            Resume r = storage[i];
            if (r.uuid.equals(uuid)) {
                return r;
            }
        }

        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            Resume r = storage[i];
            if (r.uuid.equals(uuid)) {
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
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; ++i) {
            resumes[i] = storage[i];
        }

        return resumes;
    }

    int size() {
        return size;
    }

}
