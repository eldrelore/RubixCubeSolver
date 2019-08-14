package solver;

import solver.types.FaceColor;
import solver.types.BlockFace;
import solver.types.BlockType;

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
                new BlockFace(FaceColor.BLUE), new BlockFace(FaceColor.GREEN),
                new BlockFace(FaceColor.ORANGE), new BlockFace(FaceColor.RED),
                new BlockFace(FaceColor.YELLOW), new BlockFace(FaceColor.WHITE));
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
    public SolutionSteps solve(String solutionFileName, String descriptor) {
        /* one by one cube is always solved.  */
        SolutionSteps solutionSteps = new SolutionSteps();
        solutionSteps.getSteps().add("");

        return solutionSteps;
    }

    @Override
    public Map<Integer, SolutionSteps> generateStates(Integer max) {
        Map<Integer, SolutionSteps> stateMap = new HashMap<>();
        stateMap.put("".hashCode(), new SolutionSteps());
        return stateMap;
    }


}
