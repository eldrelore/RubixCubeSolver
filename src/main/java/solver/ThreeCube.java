package solver;

import java.util.Map;

public class ThreeCube implements Cube {

    public ThreeCube() {
        buildCube();
    }

    @Override
    public Cube buildCube() {
        return null;
    }

    @Override
    public SolutionSteps solve(Map<String, Cube> solutionStates) {
        return null;
    }

    @Override
    public Map<String, Cube> generateStates(int maxSteps) {
        return null;
    }

    @Override
    public SolutionSteps getCurrentState() {
        return null;
    }

    @Override
    public Block[][][] getCube() {
        return new Block[0][][];
    }

    @Override
    public void rotateWidthForward(int width) {

    }

    @Override
    public void rotateWidthBackward(int width) {

    }

    @Override
    public void rotateHeightLeft(int height) {

    }

    @Override
    public void rotateHeightRight(int height) {

    }

    @Override
    public void rotateDepthClockwise(int depth) {

    }

    @Override
    public void rotateDepthCounterClockwise(int depth) {

    }

}
