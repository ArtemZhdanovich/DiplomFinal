package zhdanboro.maincontroller.generatecontroller.utils;

import org.apache.commons.math3.util.Pair;

import java.util.Map;

public class PackInfo {
    public static String[] pack(Map<String, Pair<Double, Boolean>> map) {
        String[] args = new String[map.size()];
        int i = 0;
        for (Map.Entry<String, Pair<Double, Boolean>> entry:map.entrySet()) {
            args[i] = "Тест: " + entry.getKey() +"\n";
            args[i] += "P значение: " + entry.getValue().getFirst() + "\n";
            args[i] += entry.getValue().getSecond()?"Тест пройден":" Тест не пройден";
            args[i] += "\n\n";
            i++;
        }

        return args;
    }
}
