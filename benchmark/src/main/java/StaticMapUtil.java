import java.util.Map;

public class StaticMapUtil {
    public static final Map<String, String> STATIC_MAP = getMap();

    public static String toFruit(String letter) {
        return STATIC_MAP.get(letter);
    }

    public static String toFruit2(String letter) {
        Map<String, String> map = getMap();
        return map.get(letter);
    }

    public static String toFruit3(String letter) {
        class Local {
            static final Map<String, String> MAP = getMap();
        }
        return Local.MAP.get(letter);
    }

    private static Map<String, String> getMap() {
        return Map.of("A", "Apple",
                "B", "Banana",
                "C", "Cherry",
                "D", "Date",
                "E", "Eggplant");
    }

}
