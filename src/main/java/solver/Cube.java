package solver;

import java.util.Map;

/**
 * uses bottom front left as point of reference; therefore can't move it.
 * Reduces number of moves possible to rotations of right, top, and back (and any mids).
 */
public interface Cube {

    Cube buildCube();

    SolutionSteps solve(Map<String, Cube> solutionStates);

    Map<String, Cube> generateStates(int maxSteps);

    SolutionSteps getCurrentState();

    Block[][][] getCube();


    void rotateWidthForward(int width);
    void rotateWidthBackward(int width);
    void rotateHeightLeft(int height);
    void rotateHeightRight(int height);
    void rotateDepthClockwise(int depth);
    void rotateDepthCounterClockwise(int depth);

    default void setBlock(Block block) {
        Block[][][] blocks = getCube();
        blocks[block.getWidth()][block.getHeight()][block.getDepth()] = block;
    }
}
