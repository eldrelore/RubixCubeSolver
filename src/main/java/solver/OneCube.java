package solver;

import solver.types.FaceColor;
import solver.types.BlockFace;
import solver.types.BlockType;
import solver.types.FaceDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OneCube implements Cube {

    private static Block[][][] cube;

    public OneCube() {
        buildCube();
    }

    @Override
    public Cube buildCube() {
        Block block = new Block(0, 0, 0,
                BlockType.DEFAULT,
                new BlockFace(FaceDirection.TOP, FaceColor.BLUE), new BlockFace(FaceDirection.BOTTOM, FaceColor.GREEN),
                new BlockFace(FaceDirection.LEFT, FaceColor.ORANGE), new BlockFace(FaceDirection.RIGHT, FaceColor.RED),
                new BlockFace(FaceDirection.FRONT, FaceColor.YELLOW), new BlockFace(FaceDirection.BACK, FaceColor.WHITE));
        cube = new Block[1][1][1];
        cube[0][0][0] = block;
        return this;
    }


    public Block[][][] getCube() {
        if (null == cube) {
            throw new RuntimeException("Cube hasn't been set up yet");
        }
        return cube;
    }

    @Override
    public void rotateWidthForward(int middleCount) {

    }

    @Override
    public void rotateWidthBackward(int middleCount) {

    }

    @Override
    public void rotateHeightLeft(int middleCount) {

    }

    @Override
    public void rotateHeightRight(int middleCount) {

    }

    @Override
    public void rotateDepthClockwise(int middleCount) {

    }

    @Override
    public void rotateDepthCounterClockwise(int middleCount) {

    }


    @Override
    public SolutionSteps solve(Map<String, Cube> solutionStates) {
        /* one by one cube is always solved.  */
        SolutionSteps solutionSteps = new SolutionSteps();
        solutionSteps.getSteps().add("");
        solutionSteps.setCube(this);
        return solutionSteps;
    }

    @Override
    public Map<String, Cube> generateStates(int maxSteps) {
        Map<String, Cube> stateMap = new HashMap<>();
        stateMap.put("", this);
        return stateMap;
    }

    @Override
    public SolutionSteps getCurrentState() {
        SolutionSteps solutionSteps = new SolutionSteps();
        solutionSteps.setCube(this);
        solutionSteps.setCurrentHash(this.hashCode());
        solutionSteps.setSteps(new ArrayList<>());
        return solutionSteps;
    }
}
