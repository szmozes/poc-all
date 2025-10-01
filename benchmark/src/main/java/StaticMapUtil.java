import java.util.Map;

public class StaticMapUtil {
    public static final Map<String, String> STATIC_MAP =
            Map.of("A", "Apple",
                    "B", "Banana",
                    "C", "Cherry",
                    "D", "Date",
                    "E", "Eggplant");

    public static String toFruit(String letter) {
        return STATIC_MAP.get(letter);
    }

    public static String toFruit2(String letter) {
        Map<String, String> map =
                Map.of("A", "Apple",
                        "B", "Banana",
                        "C", "Cherry",
                        "D", "Date",
                        "E", "Eggplant");
        return map.get(letter);
    }

    public static String toFruit3(String letter) {
        class Local {
            static final Map<String, String> MAP = Map.of("A", "Apple",
                    "B", "Banana",
                    "C", "Cherry",
                    "D", "Date",
                    "E", "Eggplant");
        }
        return Local.MAP.get(letter);
    }

}
