package multithreading_practicals;

import java.util.ArrayList;
import java.util.List;

public class Dependency {

    public List<String> doSomething(int n) {
        List<String> list = new ArrayList<>();
        for(int k = 0; k<n; k++) {
            list.add(generateString((k)));
        }

        return list;
    }

    public String generateString(int i) {
        return ("SomeString" + i);
    }
}
