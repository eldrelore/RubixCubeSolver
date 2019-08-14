package solver.types;

import java.util.Objects;

public class BlockFace {
    private FaceColor color;

    public BlockFace() {

    }


    public BlockFace(FaceColor color) {
        this.color = color;
    }


    public FaceColor getColor() {
        return color;
    }

    public void setColor(FaceColor color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockFace blockFace = (BlockFace) o;
        return color == blockFace.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return "BlockFace{" +
                ", color=" + color +
                '}';
    }
}
