/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    private int isExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1; // THE RESUME IS NOT PRESENT
    }

    void save(Resume r) {
        if (r.uuid != null) {
            if (isExist(r.uuid) == -1) {
                storage[size] = r;
                size++;
            } else System.out.println("\nResume is present");
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int exist = isExist(uuid);
        if (exist != -1) {
            if (exist != size - 1) {
                storage[exist] = storage[size - 1];
                storage[size - 1] = null;
            } else storage[exist] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    int size() {
        return size;
    }
}
