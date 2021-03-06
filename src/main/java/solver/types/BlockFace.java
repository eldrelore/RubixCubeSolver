package solver.types;
public enum BlockFace {
    TOP(0),
    BOTTOM(1),
    LEFT(2),
    RIGHT(3),
    FRONT(4),
    BACK(5);

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;

    BlockFace(int value) {
        setValue(value);
    }
}
