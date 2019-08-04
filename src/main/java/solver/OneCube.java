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
        initializeCube();
    }

    @Override
    public Block[][][] initializeCube() {
        Block block = new Block(0, 0, 0,
                BlockType.DEFAULT,
                new BlockFace(FaceDirection.TOP, FaceColor.BLUE), new BlockFace(FaceDirection.BOTTOM, FaceColor.GREEN),
                new BlockFace(FaceDirection.LEFT, FaceColor.ORANGE), new BlockFace(FaceDirection.RIGHT, FaceColor.RED),
                new BlockFace(FaceDirection.FRONT, FaceColor.YELLOW), new BlockFace(FaceDirection.BACK, FaceColor.WHITE));
        cube = new Block[1][1][1];
        cube[0][0][0] = block;
        return cube;
    }

    @Override
    public Integer getSize() {
        return null;
    }


    public Block[][][] getCube() {
        if (null == cube) {
            throw new RuntimeException("Cube hasn't been set up yet");
        }
        return cube;
    }


    @Override
    public SolutionSteps solve(Map<String, Cube> solutionStates) {
        /* one by one cube is always solved.  */
        SolutionSteps solutionSteps = new SolutionSteps();
        solutionSteps.getSteps().add("");

        return solutionSteps;
    }

    @Override
    public Map<Integer, SolutionSteps> generateStates() {
        Map<Integer, SolutionSteps> stateMap = new HashMap<>();
        stateMap.put("".hashCode(), new SolutionSteps());
        return stateMap;
    }


}
