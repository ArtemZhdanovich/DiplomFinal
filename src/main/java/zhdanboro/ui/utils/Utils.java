package zhdanboro.ui.utils;

import java.util.Map;

public class Utils {

    public static String[] packGenerateInfo(Map<String, String> map) {
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
            map.put("Start", Integer.toString(1));

        map.put("Deviation", map.get("Deviation").replace(",", "."));

        return toGenerateArgs(map);
    }

    private static String[] toGenerateArgs(Map<String, String> map) {
        String[] args = new String[8];
        args[0] = map.get("Polynomial");
        args[1] = map.get("Deviation");
        args[2] = map.get("Length");
        args[3] = map.get("Count");
        args[4] = map.get("Start");
        args[5] = map.get("Save");
        args[6] = map.get("Analyze");
        args[7] = map.get("Single");

        return args;
    }

    public static String[] packAnalyzeInfo(Map<String, String> map) {
        if (map.get("SequenceFile").length() == 0) {
            String[] args = new String[1];
            args[0] = "Не выбрано файла последовательности";
            return args;
        } else if (!map.get("SequenceFile").contains(".txt")) {
            String[] args = new String[1];
            args[0] = "Выберите файл txt";
            return args;
        }

        String[] args = new String[4];
        args[0] = map.get("SequenceFile");
        args[1] = map.get("SequenceType");
        args[2] = map.get("BaseType");
        args[3] = map.get("Criteria");

        return args;
    }
}
