package solver.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Color {
    DEFAULT(""),
    RED("r"),
    BLUE("b"),
    WHITE("w"),
    GREEN("g"),
    YELLOW("y"),
    ORANGE("o"),
    ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    Color(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    private static Map<String, Color> lookup = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            lookup.put(color.getName(), color);
        }
    }

    @JsonCreator
    public static String forValue(String value) {
        return lookup.get(value).getName();
    }

    @JsonValue
    public String toValue() {
        return name;
    }
}
