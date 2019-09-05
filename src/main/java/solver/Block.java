package solver;

import com.fasterxml.jackson.annotation.JsonIgnore;
import solver.types.BlockFace;
import solver.types.Color;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import static solver.types.BlockFace.*;
import static solver.types.Dimensions.*;

public class Block implements Serializable {

    private int[] faces = new int[6];
    private int[] dimensions = new int[3];

    public int[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(int[] dimensions) {
        this.dimensions = dimensions;
    }

    public int[] getFaces() {
        return faces;
    }

    public void setFaces(int[] faces) {
        this.faces = faces;
    }


    public Block(int currentWidth, int currentHeight, int currentDepth,
                 int[] currentFaces) {
        setWidth(currentWidth);
        setHeight(currentHeight);
        setDepth(currentDepth);
        setWidth(currentWidth);
        setHeight(currentHeight);
        setDepth(currentDepth);
        setFaces(currentFaces);
    }

    public Block() {
    }

    @JsonIgnore
    public int getWidth() {
        return dimensions[WIDTH.getValue()];
    }

    public void setWidth(int width) {
        dimensions[WIDTH.getValue()] = width;
    }

    @JsonIgnore
    public int getHeight() {
        return dimensions[HEIGHT.getValue()];
    }

    public void setHeight(int height) {
        dimensions[HEIGHT.getValue()] = height;
    }

    @JsonIgnore
    public int getDepth() {
        return dimensions[DEPTH.getValue()];
    }

    public void setDepth(int depth) {
        dimensions[DEPTH.getValue()] = depth;
    }

    @JsonIgnore
    public int getTop() {
        return faces[TOP.getValue()];
    }

    public void setTop(int top) {
        faces[TOP.getValue()] = top;
    }

    @JsonIgnore
    public int getBottom() {
        return faces[BOTTOM.getValue()];
    }

    public void setBottom(int bottom) {
        faces[BOTTOM.getValue()] = bottom;
    }

    @JsonIgnore
    public int getLeft() {
        return faces[LEFT.getValue()];
    }

    public void setLeft(int left) {
        faces[LEFT.getValue()] = left;
    }

    @JsonIgnore
    public int getRight() {
        return faces[RIGHT.getValue()];
    }

    public void setRight(int right) {
        faces[RIGHT.getValue()] = right;
    }

    @JsonIgnore
    public int getFront() {
        return faces[FRONT.getValue()];
    }

    public void setFront(int front) {
        faces[FRONT.getValue()] = front;
    }

    @JsonIgnore
    public int getBack() {
        return faces[BACK.getValue()];
    }

    public void setBack(int back) {
        faces[BACK.getValue()] = back;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return dimensions == block.dimensions &&
                Objects.equals(faces, block.faces);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(faces) * 13 + Arrays.hashCode(dimensions) * 31;
    }

    @Override
    public String toString() {
        return "Block{" +
                "dimensions=" + dimensions +
                ", faces=" + faces +
                '}';
    }
}

