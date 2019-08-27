package solver;

import solver.types.Color;

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
                new Color[]{Color.BLUE, Color.GREEN,
                        Color.ORANGE, Color.RED,
                        Color.YELLOW, Color.WHITE});
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
