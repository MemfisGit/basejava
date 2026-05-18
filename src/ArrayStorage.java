/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final int length = 10000;
    Resume[] storage = new Resume[length];
    int size = 0;

    void clear() {
        for(int i = 0; i < size; ++i) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        Resume old = get(r.uuid);
        if (old == null) {
            if (size < length) {
                storage[size] = r;
            }
            ++size;
        }
    }

    Resume get(String uuid) {
        for(int i = 0; i < size; ++i) {
            Resume r = storage[i];
            if (r.uuid.equals(uuid)) {
                return r;
            }
        }

        return null;
    }

    void delete(String uuid) {
        for(int i = 0; i < size; ++i) {
            Resume r = storage[i];
            if (r.uuid.equals(uuid)) {
                for(int j = i; j < size - 1; ++j) {
                    storage[j] = storage[j + 1];
                }
                storage[size - 1] = null;
                --size;
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] arr = new Resume[size];
        for(int i = 0; i < size; ++i) {
            arr[i] = storage[i];
        }

        return arr;
    }

    int size() {
        return size;
    }

}
