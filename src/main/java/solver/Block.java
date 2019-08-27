package solver;

import com.fasterxml.jackson.annotation.JsonIgnore;
import solver.types.BlockFace;
import solver.types.Color;

import java.util.Objects;

import static solver.types.BlockFace.*;
import static solver.types.Dimensions.*;

public class Block {

    private Color[] faces = new Color[6];
    private int[] dimensions = new int[3];

    public int[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(int[] dimensions) {
        this.dimensions = dimensions;
    }


    public Color[] getFaces() {
        return faces;
    }

    public void setFaces(Color[] faces) {
        this.faces = faces;
    }


    public Block(int currentWidth, int currentHeight, int currentDepth,
                 Color[] currentFaces) {
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
    public Color getTop() {
        return faces[TOP.getValue()];
    }

    public void setTop(Color top) {
        faces[TOP.getValue()] = top;
    }

    @JsonIgnore
    public Color getBottom() {
        return faces[BOTTOM.getValue()];
    }

    public void setBottom(Color bottom) {
        faces[BOTTOM.getValue()] = bottom;
    }

    @JsonIgnore
    public Color getLeft() {
        return faces[LEFT.getValue()];
    }

    public void setLeft(Color left) {
        faces[LEFT.getValue()] = left;
    }

    @JsonIgnore
    public Color getRight() {
        return faces[RIGHT.getValue()];
    }

    public void setRight(Color right) {
        faces[RIGHT.getValue()] = right;
    }

    @JsonIgnore
    public Color getFront() {
        return faces[FRONT.getValue()];
    }

    public void setFront(Color front) {
        faces[FRONT.getValue()] = front;
    }

    @JsonIgnore
    public Color getBack() {
        return faces[BACK.getValue()];
    }

    public void setBack(Color back) {
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
        return Objects.hash(dimensions, faces);
    }

    @Override
    public String toString() {
        return "Block{" +
                "dimensions=" + dimensions +
                ", faces=" + faces +
                '}';
    }
}

