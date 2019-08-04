package solver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import solver.types.FaceColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsNot.not;

public class RubixCubeTwoByBlockTest {

    private Cube cube;

    private static final int SIZE = 2;

    @Before
    public void setup() {
        cube = new RubixCube(SIZE).generateCube();
    }

    @Test
    public void testCubeSize() {
        Block[][][] blocks = cube.initializeCube();
        Assert.assertEquals(SIZE, blocks.length);
        Assert.assertEquals(SIZE, blocks[0].length);
        Assert.assertEquals(SIZE, blocks[1].length);
        Assert.assertEquals(SIZE, blocks[0][0].length);
        Assert.assertEquals(SIZE, blocks[0][1].length);
        Assert.assertEquals(SIZE, blocks[1][0].length);
        Assert.assertEquals(SIZE, blocks[1][1].length);
    }

    @Test
    public void testCubeSides() {
        Block[][][] blocks = cube.initializeCube();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = blocks[x][y][z];
                    if (0 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    }
                }
            }
        }
    }

    @Test
    public void testRotateTopLeft() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateHeightLeft(1, blocks, steps);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = blocks[x][y][z];
                    if (0 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getBack().getColor());
                    }
                }
            }
        }
    }


    @Test
    public void testRotateTopRight() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateHeightRight(1, blocks, steps);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = blocks[x][y][z];
                    if (0 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getBack().getColor());
                    }
                }
            }
        }
    }


    @Test
    public void testRotateRightForward() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateWidthForward(1, blocks, steps);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = blocks[x][y][z];
                    if (0 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.BLUE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.YELLOW, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.BLUE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.YELLOW, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBack().getColor());
                    }
                }
            }
        }
    }

    @Test
    public void testRotateRightBackward() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateWidthBackward(1, blocks, steps);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = blocks[x][y][z];
                    if (0 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.WHITE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.BLUE, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.WHITE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.BLUE, block.getBack().getColor());
                    }
                }
            }
        }
    }

    @Test
    public void testRotateBackClockwise() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateDepthClockwise(1, blocks, steps);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = blocks[x][y][z];
                    if (0 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.BLUE, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.ORANGE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.ORANGE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.BLUE, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    }
                }
            }
        }
    }

    @Test
    public void testRotateBackCounterClockwise() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateDepthCounterClockwise(1, blocks, steps);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = blocks[x][y][z];
                    if (0 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (0 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.BLUE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(FaceColor.BLUE, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.RED, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.WHITE, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBack().getColor());
                    } else if (1 == x && 0 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.DEFAULT, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.ORANGE, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (0 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.RED, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.BLUE, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    } else if (1 == x && 1 == y && 1 == z) {
                        Assert.assertEquals(FaceColor.RED, block.getTop().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getBottom().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getLeft().getColor());
                        Assert.assertEquals(FaceColor.GREEN, block.getRight().getColor());
                        Assert.assertEquals(FaceColor.DEFAULT, block.getFront().getColor());
                        Assert.assertEquals(FaceColor.YELLOW, block.getBack().getColor());
                    }
                }
            }
        }
    }


    @Test
    public void testGetCurrentState() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateDepthClockwise(1, blocks, steps);
        cube.rotateHeightLeft(1, blocks, steps);
        Assert.assertNotNull(steps);
        Assert.assertEquals("clockwise 1", steps.get(0));
        Assert.assertEquals("left 1", steps.get(1));
    }


    @Test
    public void testInitializeCubeFromDescriptor() {
        Block[][][] initializedBlockArray = cube.initializeCube();
        String initializedCubeDescriptor = cube.getDescriptor(initializedBlockArray);
        Block[][][] blockArrayFromDescriptor = ((TwoCube) cube).initializeCubeFromDescriptor(initializedCubeDescriptor);
        Assert.assertNotNull(blockArrayFromDescriptor);
        Assert.assertArrayEquals(initializedBlockArray, blockArrayFromDescriptor);
    }

    @Test
    public void testClone() {
        List<String> steps = new ArrayList<>();
        Block[][][] clonedArray = ((TwoCube) cube).deepClone(cube.initializeCube());
        cube.rotateWidthForward(1, clonedArray, steps);
        Assert.assertThat(cube.initializeCube(), not(equals(clonedArray)));
    }




    @Test
    public void testGenerateStates() {
        Map<Integer, SolutionSteps> solutions = cube.generateStates();
        Assert.assertNotNull(solutions);
        for (Integer descriptorHash : solutions.keySet()) {
            SolutionSteps steps = solutions.get(descriptorHash);
            Assert.assertNotNull(steps);
            /* TODO:  the last one should be empty*/
            Assert.assertFalse(steps.getSteps().isEmpty());
        }
    }

}
