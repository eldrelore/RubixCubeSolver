package solver.types;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum Color {
    DEFAULT(0),
    RED(1),
    BLUE(2),
    WHITE(3),
    GREEN(4),
    YELLOW(5),
    ORANGE(6),
    ;

    private int value;


    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }


    Color(int value) {
        setValue(value);
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

    private static Map<Integer, Color> lookup = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            lookup.put(color.getValue(), color);
        }
    }

    @JsonCreator
    public static Color forIntValue(Integer value) {
        return lookup.get(value);
    }
}
