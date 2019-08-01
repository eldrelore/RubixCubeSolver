package solver;

import solver.types.BlockFace;
import solver.types.BlockType;
import solver.types.FaceDirection;

import java.util.Objects;

public class Block {
    private int width;
    private int height;
    private int depth;
    private BlockType type;
    private BlockFace top = new BlockFace(FaceDirection.TOP);
    private BlockFace bottom = new BlockFace(FaceDirection.BOTTOM);
    private BlockFace left = new BlockFace(FaceDirection.LEFT);
    private BlockFace right = new BlockFace(FaceDirection.RIGHT);
    private BlockFace front = new BlockFace(FaceDirection.FRONT);
    private BlockFace back = new BlockFace(FaceDirection.BACK);

    public Block(int currentWidth, int currentHeight, int currentDepth,
                 BlockType blockType,
                 BlockFace top, BlockFace bottom,
                 BlockFace left, BlockFace right,
                 BlockFace front, BlockFace back) {
        this.width = currentWidth;
        this.height = currentHeight;
        this.depth = currentDepth;
        this.type = blockType;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.front = front;
        this.back = back;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public BlockFace getTop() {
        return top;
    }

    public void setTop(BlockFace top) {
        this.top = top;
    }

    public BlockFace getBottom() {
        return bottom;
    }

    public void setBottom(BlockFace bottom) {
        this.bottom = bottom;
    }

    public BlockFace getLeft() {
        return left;
    }

    public void setLeft(BlockFace left) {
        this.left = left;
    }

    public BlockFace getRight() {
        return right;
    }

    public void setRight(BlockFace right) {
        this.right = right;
    }

    public BlockFace getFront() {
        return front;
    }

    public void setFront(BlockFace front) {
        this.front = front;
    }

    public BlockFace getBack() {
        return back;
    }

    public void setBack(BlockFace back) {
        this.back = back;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return width == block.width &&
                height == block.height &&
                depth == block.depth &&
                type == block.type &&
                Objects.equals(top, block.top) &&
                Objects.equals(bottom, block.bottom) &&
                Objects.equals(left, block.left) &&
                Objects.equals(right, block.right) &&
                Objects.equals(front, block.front) &&
                Objects.equals(back, block.back);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, depth, type, top, bottom, left, right, front, back);
    }

    @Override
    public String toString() {
        return "Block{" +
                "width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", type=" + type +
                ", top=" + top +
                ", bottom=" + bottom +
                ", left=" + left +
                ", right=" + right +
                ", front=" + front +
                ", back=" + back +
                '}';
    }
}

