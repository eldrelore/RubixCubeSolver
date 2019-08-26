package solver;

import solver.types.BlockFace;

import java.util.Objects;

public class Block {
    private int width;
    private int height;
    private int depth;

    private BlockFace top = new BlockFace();
    private BlockFace bottom = new BlockFace();
    private BlockFace left = new BlockFace();
    private BlockFace right = new BlockFace();
    private BlockFace front = new BlockFace();
    private BlockFace back = new BlockFace();

    public Block(int currentWidth, int currentHeight, int currentDepth,

                 BlockFace top, BlockFace bottom,
                 BlockFace left, BlockFace right,
                 BlockFace front, BlockFace back) {
        this.width = currentWidth;
        this.height = currentHeight;
        this.depth = currentDepth;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.front = front;
        this.back = back;
    }

    public Block() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return width == block.width &&
                height == block.height &&
                depth == block.depth &&
                Objects.equals(top, block.top) &&
                Objects.equals(bottom, block.bottom) &&
                Objects.equals(left, block.left) &&
                Objects.equals(right, block.right) &&
                Objects.equals(front, block.front) &&
                Objects.equals(back, block.back);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, depth,  top, bottom, left, right, front, back);
    }

    @Override
    public String toString() {
        return "Block{" +
                "width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", top=" + top +
                ", bottom=" + bottom +
                ", left=" + left +
                ", right=" + right +
                ", front=" + front +
                ", back=" + back +
                '}';
    }
}

