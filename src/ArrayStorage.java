/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size=0;
    void clear() {
        for(int i=0; i<size; i++)
        {
            storage[i]=null;
        }
        size=0;
    }

    void save(Resume r) {
        if(r.uuid!=null) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++)
        {
            if(storage[i].uuid.equals(uuid))
            {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++)
        {
            if(storage[i].uuid.equals(uuid))
            {
                if(i!=size-1) {
                    storage[i] = storage[size - 1];
                    storage[size-1]=null;
                }
                else  storage[i]=null;
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] fullStorage = new Resume[size];
        System.arraycopy(storage, 0, fullStorage, 0, size);
        return fullStorage                                                                                                                  ;
    }

    int size() {
        return size;
    }
}
