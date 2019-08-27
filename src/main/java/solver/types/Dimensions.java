package solver.types;

public enum Dimensions {

    WIDTH(0),
    HEIGHT(1),
    DEPTH(2);

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;

    Dimensions(int value) {
        setValue(value);
    }
}
