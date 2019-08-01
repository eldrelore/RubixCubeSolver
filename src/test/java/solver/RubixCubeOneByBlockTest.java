package solver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import solver.types.BlockType;
import solver.types.FaceColor;

public class RubixCubeOneByBlockTest {

    private Cube cube;

    private static final int SIZE = 1;

    @Before
    public void setup() {
        cube = new RubixCube(SIZE).generateCube();
    }

    @Test
    public void testCubeSize() {
        Assert.assertEquals(SIZE, cube.getCube().length);
        Assert.assertEquals(SIZE, cube.getCube()[0].length);
        Assert.assertEquals(SIZE, cube.getCube()[0][0].length);
        Assert.assertEquals(SIZE, cube.getCube()[0][0].length);
    }

    @Test
    public void testCubeFaces() {
        /* TODO:  setup and test a rubixCube of size one,
            make sure it has all the right faces (and type)*/
        Block block = cube.getCube()[0][0][0];
        Assert.assertEquals(BlockType.DEFAULT, block.getType());
        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
        Assert.assertEquals(FaceColor.YELLOW, block.getFront().getColor());
        Assert.assertEquals(FaceColor.WHITE, block.getBack().getColor());
    }

    @Test
    public void testSolution() {
        SolutionSteps solutionSteps = cube.solve(null);
        Assert.assertEquals(1, solutionSteps.getSteps().size());
        Assert.assertEquals("", solutionSteps.getSteps().get(0));
        Assert.assertEquals(cube, solutionSteps.getCube());
    }
}