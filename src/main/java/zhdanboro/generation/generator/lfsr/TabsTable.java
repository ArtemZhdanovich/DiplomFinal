package zhdanboro.generation.generator.lfsr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TabsTable {
    private final static Map<Integer, String> table = new HashMap<>();
    static {
        table.put(3,"2");
        table.put(4,"3");
        table.put(5,"3");
        table.put(6,"5");
        table.put(7,"6");
        table.put(8,"6,5,4");
        table.put(9,"5");
        table.put(10,"7");
        table.put(11,"9");
        table.put(12,"6,4,1");
        table.put(13,"4,3,1");
        table.put(14,"5,3,1");
        table.put(15,"14");
        table.put(16,"15,13,4");
    }

    public static int[] getArray(int key) {
        return Arrays.stream((table.get(key).split(","))).mapToInt(Integer::parseInt).toArray();
    }
}
