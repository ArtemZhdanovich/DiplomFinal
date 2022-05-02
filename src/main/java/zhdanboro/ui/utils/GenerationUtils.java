package zhdanboro.ui.utils;

import java.util.Map;

public class GenerationUtils {

    public static String[] packInfo(Map<String, String> map) {
        if (map.get("Polynomial").length() == 0)
            map.put("Polynomial", "10000");

        String genLen = Integer.toString((int) (Math.pow(2, map.get("Polynomial").length())-1));
        if (map.get("Deviation").length() == 0)
            map.put("Deviation", Double.toString(0.0000));
        if (map.get("Length").length() == 0)
            map.put("Length", genLen);
        if (map.get("Count").length() == 0)
            map.put("Count", genLen);
        if (map.get("Start").length() == 0)
            map.put("Start", Integer.toString(0));

        map.put("Deviation", map.get("Deviation").replace(",", "."));

        return toArgs(map);
    }

    private static String[] toArgs(Map<String, String> map) {
        String[] args = new String[7];
        args[0] = map.get("Polynomial");
        args[1] = map.get("Deviation");
        args[2] = map.get("Length");
        args[3] = map.get("Count");
        args[4] = map.get("Start");
        args[5] = map.get("Save");
        args[6] = map.get("Analyze");

        return args;
    }
}
