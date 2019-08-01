package solver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import solver.types.FaceColor;

public class RubixCubeTwoByBlockTest {

    private Cube cube;

    private static final int SIZE = 2;

    @Before
    public void setup() {
        cube = new RubixCube(SIZE).generateCube();
    }

    @Test
    public void testCubeSize() {
        Assert.assertEquals(SIZE, cube.getCube().length);
        Assert.assertEquals(SIZE, cube.getCube()[0].length);
        Assert.assertEquals(SIZE, cube.getCube()[1].length);
        Assert.assertEquals(SIZE, cube.getCube()[0][0].length);
        Assert.assertEquals(SIZE, cube.getCube()[0][1].length);
        Assert.assertEquals(SIZE, cube.getCube()[1][0].length);
        Assert.assertEquals(SIZE, cube.getCube()[1][1].length);
    }

    @Test
    public void testCubeSides() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = cube.getCube()[x][y][z];
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
        cube.rotateHeightLeft(1);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = cube.getCube()[x][y][z];
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

        cube.rotateHeightRight(1);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = cube.getCube()[x][y][z];
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
        cube.rotateWidthForward(1);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = cube.getCube()[x][y][z];
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
        cube.rotateWidthBackward(1);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = cube.getCube()[x][y][z];
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
        cube.rotateDepthClockwise(1);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = cube.getCube()[x][y][z];
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
        cube.rotateDepthCounterClockwise(1);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Block block = cube.getCube()[x][y][z];
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

}
