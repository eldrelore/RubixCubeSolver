package solver.types;

import java.util.Objects;

public class BlockFace {
    private FaceDirection direction;
    private FaceColor color;

    public BlockFace() {

    }

    public BlockFace(FaceDirection direction) {
        this.direction = direction;
    }

    public BlockFace(FaceDirection direction, FaceColor color) {
        this.direction = direction;
        this.color = color;
    }

    public FaceDirection getDirection() {
        return direction;
    }

    public void setDirection(FaceDirection direction) {
        this.direction = direction;
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
        return direction == blockFace.direction &&
                color == blockFace.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, color);
    }

    @Override
    public String toString() {
        return "BlockFace{" +
                "direction=" + direction +
                ", color=" + color +
                '}';
    }
}
