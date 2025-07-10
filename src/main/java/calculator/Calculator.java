package calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator<T> {
    protected final List<T> savedData = new ArrayList<>();

    public void save(T result){
        savedData.add(result);
    }

    public List<T> getSavedData() {
        return savedData;
    }


}
