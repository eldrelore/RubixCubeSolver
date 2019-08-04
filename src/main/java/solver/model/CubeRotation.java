package solver.model;

import solver.Block;
import solver.Cube;

import java.util.List;

public class CubeRotation {

    private int scalar;
    private Block[][][] blockArray;
    private List<String> steps;

    public CubeRotation(int scalar, Block[][][] blockArray, List<String> steps, Cube cube) {
        this.scalar = scalar;
        this.blockArray = blockArray;
        this.steps = steps;
        this.cube = cube;
    }

    public Cube getCube() {
        return cube;
    }

    public void setCube(Cube cube) {
        this.cube = cube;
    }

    private Cube cube;


    public CubeRotation(int scalar, Block[][][] blockArray, List<String> steps) {
        this.scalar = scalar;
        this.blockArray = blockArray;
        this.steps = steps;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }


    public CubeRotation(int scalar, Block[][][] blockArray) {
        this.scalar = scalar;
        this.blockArray = blockArray;
    }

    public int getScalar() {
        return scalar;
    }

    public void setScalar(int scalar) {
        this.scalar = scalar;
    }

    public Block[][][] getBlockArray() {
        return blockArray;
    }

    public void setBlockArray(Block[][][] blockArray) {
        this.blockArray = blockArray;
    }
}
