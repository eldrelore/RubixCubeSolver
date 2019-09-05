package solver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import solver.types.Color;

public class RubixCubeOneByBlockTest {

    private Cube cube;

    private static final int SIZE = 1;

    @Before
    public void setup() {
        cube = new RubixCube(SIZE).generateCube();
    }

    @Test
    public void testCubeSize() {
        Block[][][] blocks = cube.initializeCube();
        Assert.assertEquals(SIZE, blocks.length);
        Assert.assertEquals(SIZE, blocks[0].length);
        Assert.assertEquals(SIZE, blocks[0][0].length);
        Assert.assertEquals(SIZE, blocks[0][0].length);
    }

    @Test
    public void testCubeFaces() {
        /*  setup and test a rubixCube of size one,
            make sure it has all the right faces (and type)*/
        Block[][][] blocks = cube.initializeCube();
        Block block = blocks[0][0][0];
        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
        Assert.assertEquals(Color.RED.getValue(), block.getRight());
        Assert.assertEquals(Color.YELLOW.getValue(), block.getFront());
        Assert.assertEquals(Color.WHITE.getValue(), block.getBack());
    }

    @Test
    public void testSolution() {
        SolutionSteps solutionSteps = cube.solve("", "");
        Assert.assertEquals(1, solutionSteps.getSteps().size());
        Assert.assertEquals("", solutionSteps.getSteps().get(0));
    }
}
