package solver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import solver.model.CubeRotation;
import solver.types.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
    public void testRotateBottomLeftPreserveFront() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateHeightLeft(0, blocks, steps);
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
    public void testRotateBottomRightPreserveFront() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateHeightRight(0, blocks, steps);
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
    public void testRotateLeftForwardPreserveFront() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateWidthForward(0, blocks, steps);
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
    public void testRotateLeftBackwardPreserveFacing() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateWidthBackward(0, blocks, steps);
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
    public void testRotateFrontClockwisePreserveFace() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateDepthClockwise(0, blocks, steps);
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
    public void testRotateFrontCounterClockwisePreserveFace() {
        Block[][][] blocks = cube.initializeCube();
        List<String> steps = new ArrayList<>();
        cube.rotateDepthCounterClockwise(0, blocks, steps);
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
    public void testGenerateOneMovement() {
        Map<Integer, SolutionSteps> solutions = cube.generateStates(1);
        Assert.assertNotNull(solutions);
        int solutionSize = solutions.size();
        Assert.assertEquals(7, solutions.size());
        int zeroMovementCounter = 0;
        int expectedZeroMotionCounters = 1;

        int oneMovementCounter = 0;
        int expectedOneMovementCountes = 6;

        for (SolutionSteps solutionSteps : solutions.values()) {
            if (0 == solutionSteps.getSteps().size()) {
                zeroMovementCounter++;
            } else if (1 == solutionSteps.getSteps().size()) {
                oneMovementCounter++;
            }
        }
        Assert.assertEquals(expectedZeroMotionCounters, zeroMovementCounter);
        Assert.assertEquals(expectedOneMovementCountes, oneMovementCounter);
    }


    @Test
    public void testGenerateStates() {
        Map<Integer, SolutionSteps> solutions = cube.generateStates(null);
        Assert.assertNotNull(solutions);
        int solutionSize = solutions.size();

        int trueCounter = 0;
        for (Integer descriptorHash : solutions.keySet()) {
            SolutionSteps steps = solutions.get(descriptorHash);
            Assert.assertNotNull(steps);
            /* TODO:  the last one should be empty*/
            if (steps.getSteps().isEmpty()) {
                trueCounter++;
            }
        }
        Assert.assertEquals(1, trueCounter);
    }


    @Test
    public void testReverseSolutionSteps() {
        List<String> steps = new ArrayList<>();
        steps.add("test 1");
        steps.add(ReveralRotation.FORWARD.getCommand() + " 1");
        steps.add(ReveralRotation.BACKWARD.getCommand() + " 1");
        steps.add(ReveralRotation.LEFT.getCommand() + " 1");
        steps.add(ReveralRotation.RIGHT.getCommand() + " 1");
        steps.add(ReveralRotation.CLOCKWISE.getCommand() + " 1");
        steps.add(ReveralRotation.COUNTER_CLOCKWISE.getCommand() + " 1");
        steps.add(ReveralRotation.FORWARD_PRESERVE.getCommand() + " 1");
        steps.add(ReveralRotation.BACKWARD_PRESRVE.getCommand() + " 1");
        steps.add(ReveralRotation.LEFT_PRESERVE.getCommand() + " 1");
        steps.add(ReveralRotation.RIGHT_PRESERVE.getCommand() + " 1");
        steps.add(ReveralRotation.CLOCKWISE_PRESERVE.getCommand() + " 1");
        steps.add(ReveralRotation.COUNTER_CLOCKWISE_PRESERVE.getCommand() + " 1");

        List<String> reversedSteps = ((TwoCube) cube).reverseSteps(steps);
        Assert.assertNotNull(reversedSteps);
        Assert.assertEquals(steps.size(), reversedSteps.size());
        Assert.assertEquals(reversedSteps.get(0), ReveralRotation.CLOCKWISE_PRESERVE.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(1), ReveralRotation.COUNTER_CLOCKWISE_PRESERVE.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(2), ReveralRotation.LEFT_PRESERVE.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(3), ReveralRotation.RIGHT_PRESERVE.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(4), ReveralRotation.FORWARD_PRESERVE.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(5), ReveralRotation.BACKWARD_PRESRVE.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(6), ReveralRotation.CLOCKWISE.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(7), ReveralRotation.COUNTER_CLOCKWISE.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(8), ReveralRotation.LEFT.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(9), ReveralRotation.RIGHT.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(10), ReveralRotation.FORWARD.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(11), ReveralRotation.BACKWARD.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(12), "test 1");
    }

    @Test
    public void testSolve() {
        String solutionFile = "TwoByTwoCubeSolutions.txt";
        Block[][][] mixedUpBlocks = getMixedUpBlocks();
        String descriptor = cube.getDescriptor(mixedUpBlocks);
        SolutionSteps solution = cube.solve(solutionFile, descriptor);
        Assert.assertNotNull(solution);
        List<String> generationSteps = solution.getSteps();
        Assert.assertNotNull(generationSteps);
        Assert.assertFalse(generationSteps.isEmpty());

        List<String> solutionSteps = solution.getSolutionSteps();
        Assert.assertNotNull(solutionSteps);
        Assert.assertFalse(solutionSteps.isEmpty());

        System.out.println("Generation Steps: " + generationSteps);
        System.out.println("Solution steps: " + solutionSteps);
        List<String> expectedSolutionSteps = Arrays.asList("rpf 0", "backward 1", "ccwpf 0", "rpf 0", "backward 1", "ccwpf 0", "backward 1", "right 1", "backward 1", "counter-clockwise 1");
        Assert.assertEquals(expectedSolutionSteps, solutionSteps);
    }


    @Test
    public void testReadSolutionsFromFile() {
        String solutionFile = "src/test/TestSolution.txt";
        String testDescriptor = "[[[{\\\"width\\\":0,\\\"height\\\":0,\\\"depth\\\":0,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"GREEN\\\"},\\\"left\\\":{\\\"color\\\":\\\"ORANGE\\\"},\\\"right\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"front\\\":{\\\"color\\\":\\\"WHITE\\\"},\\\"back\\\":{\\\"color\\\":\\\"DEFAULT\\\"}},{\\\"width\\\":0,\\\"height\\\":0,\\\"depth\\\":1,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"GREEN\\\"},\\\"left\\\":{\\\"color\\\":\\\"ORANGE\\\"},\\\"right\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"front\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"back\\\":{\\\"color\\\":\\\"YELLOW\\\"}}],[{\\\"width\\\":0,\\\"height\\\":1,\\\"depth\\\":0,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"RED\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"left\\\":{\\\"color\\\":\\\"YELLOW\\\"},\\\"right\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"front\\\":{\\\"color\\\":\\\"BLUE\\\"},\\\"back\\\":{\\\"color\\\":\\\"DEFAULT\\\"}},{\\\"width\\\":0,\\\"height\\\":1,\\\"depth\\\":1,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"WHITE\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"left\\\":{\\\"color\\\":\\\"RED\\\"},\\\"right\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"front\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"back\\\":{\\\"color\\\":\\\"GREEN\\\"}}]],[[{\\\"width\\\":1,\\\"height\\\":0,\\\"depth\\\":0,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"BLUE\\\"},\\\"left\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"right\\\":{\\\"color\\\":\\\"ORANGE\\\"},\\\"front\\\":{\\\"color\\\":\\\"WHITE\\\"},\\\"back\\\":{\\\"color\\\":\\\"DEFAULT\\\"}},{\\\"width\\\":1,\\\"height\\\":0,\\\"depth\\\":1,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"YELLOW\\\"},\\\"left\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"right\\\":{\\\"color\\\":\\\"GREEN\\\"},\\\"front\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"back\\\":{\\\"color\\\":\\\"RED\\\"}}],[{\\\"width\\\":1,\\\"height\\\":1,\\\"depth\\\":0,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"ORANGE\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"left\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"right\\\":{\\\"color\\\":\\\"YELLOW\\\"},\\\"front\\\":{\\\"color\\\":\\\"BLUE\\\"},\\\"back\\\":{\\\"color\\\":\\\"DEFAULT\\\"}},{\\\"width\\\":1,\\\"height\\\":1,\\\"depth\\\":1,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"BLUE\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"left\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"right\\\":{\\\"color\\\":\\\"WHITE\\\"},\\\"front\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"back\\\":{\\\"color\\\":\\\"RED\\\"}}]]]";
        SolutionSteps solution = ((TwoCube) cube).readSolutionsFromFile(solutionFile, testDescriptor);
        Assert.assertNotNull(solution);
    }

    @Test
    public void testReadFileContents() {
        String solutionFile = "src/main/resources/TwoByTwoCubeSolutions.txt";
        String fileContents = ((TwoCube) cube).readFileContents(solutionFile);
        Assert.assertNotNull(fileContents);
        Assert.assertFalse(fileContents.isEmpty());
    }


    @Test
    public void testGenerateAllCubeRotationsFromState() {
        String line = "{\"steps\":[],\"descriptor\":\"[[[{\\\"width\\\":0,\\\"height\\\":0,\\\"depth\\\":0,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"GREEN\\\"},\\\"left\\\":{\\\"color\\\":\\\"ORANGE\\\"},\\\"right\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"front\\\":{\\\"color\\\":\\\"WHITE\\\"},\\\"back\\\":{\\\"color\\\":\\\"DEFAULT\\\"}},{\\\"width\\\":0,\\\"height\\\":0,\\\"depth\\\":1,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"GREEN\\\"},\\\"left\\\":{\\\"color\\\":\\\"ORANGE\\\"},\\\"right\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"front\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"back\\\":{\\\"color\\\":\\\"YELLOW\\\"}}],[{\\\"width\\\":0,\\\"height\\\":1,\\\"depth\\\":0,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"BLUE\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"left\\\":{\\\"color\\\":\\\"ORANGE\\\"},\\\"right\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"front\\\":{\\\"color\\\":\\\"WHITE\\\"},\\\"back\\\":{\\\"color\\\":\\\"DEFAULT\\\"}},{\\\"width\\\":0,\\\"height\\\":1,\\\"depth\\\":1,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"BLUE\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"left\\\":{\\\"color\\\":\\\"ORANGE\\\"},\\\"right\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"front\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"back\\\":{\\\"color\\\":\\\"YELLOW\\\"}}]],[[{\\\"width\\\":1,\\\"height\\\":0,\\\"depth\\\":0,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"GREEN\\\"},\\\"left\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"right\\\":{\\\"color\\\":\\\"RED\\\"},\\\"front\\\":{\\\"color\\\":\\\"WHITE\\\"},\\\"back\\\":{\\\"color\\\":\\\"DEFAULT\\\"}},{\\\"width\\\":1,\\\"height\\\":0,\\\"depth\\\":1,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"GREEN\\\"},\\\"left\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"right\\\":{\\\"color\\\":\\\"RED\\\"},\\\"front\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"back\\\":{\\\"color\\\":\\\"YELLOW\\\"}}],[{\\\"width\\\":1,\\\"height\\\":1,\\\"depth\\\":0,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"BLUE\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"left\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"right\\\":{\\\"color\\\":\\\"RED\\\"},\\\"front\\\":{\\\"color\\\":\\\"WHITE\\\"},\\\"back\\\":{\\\"color\\\":\\\"DEFAULT\\\"}},{\\\"width\\\":1,\\\"height\\\":1,\\\"depth\\\":1,\\\"type\\\":\\\"CORNER\\\",\\\"top\\\":{\\\"color\\\":\\\"BLUE\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"left\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"right\\\":{\\\"color\\\":\\\"RED\\\"},\\\"front\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"back\\\":{\\\"color\\\":\\\"YELLOW\\\"}}]]]\"}\n";
        ObjectMapper mapper = new ObjectMapper();
        boolean exceptionEncountered = false;
        try {
            SolutionSteps priorMovementState = mapper.readValue(line, SolutionSteps.class);
            Map<Integer, SolutionSteps> stepMap = new HashMap<>();
            AtomicInteger totalAttempts = new AtomicInteger();
            AtomicInteger currentMovementStates = new AtomicInteger();
            AtomicInteger knownStates = new AtomicInteger();
            ((TwoCube) cube).generateAllCubeRotationsFromState(priorMovementState, "src/test/resources/testSolution1.txt", stepMap, totalAttempts, currentMovementStates, knownStates);

        } catch (IOException e) {
            e.printStackTrace();
            exceptionEncountered = true;
        }
        Assert.assertFalse(exceptionEncountered);
    }


    @Test
    public void testCloneBlockFace() {
        BlockFace originalBlockFace = new BlockFace();
        FaceColor testColor = FaceColor.BLUE;
        originalBlockFace.setColor(testColor);
        BlockFace clonedBlockFace = new BlockFace();
        ((TwoCube) cube).cloneBlockFace(originalBlockFace, clonedBlockFace);
        Assert.assertEquals(testColor, clonedBlockFace.getColor());
    }

    @Test
    public void testCloneBlock() {
        Block bottomLeftFrontBlock = new Block(0, 0, 0,
                BlockType.CORNER,
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.GREEN),
                new BlockFace(FaceColor.ORANGE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.WHITE),
                new BlockFace(FaceColor.DEFAULT));
        Block clonedBlock = new Block();
        ((TwoCube) cube).cloneBlock(bottomLeftFrontBlock, clonedBlock);
        Assert.assertEquals(bottomLeftFrontBlock, clonedBlock);
    }

    @Test
    public void testCloneCubeApplyRotation() {
        Block[][][] priorCubeState = cube.initializeCube();
        List<String> priorSteps = new ArrayList<>();
        int i = 0;
        Rotation rotation = Rotation.BACKWARD;
        CubeRotation cubeRotation = ((TwoCube) cube).cloneCubeApplyRotation(priorCubeState, priorSteps, i, rotation);
        Assert.assertNotNull(cubeRotation);
        Block[][][] blocks = cubeRotation.getBlockArray();
        int size = cube.getSize();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    Block block = blocks[x][y][z];
                    Assert.assertNotNull(block.getFront().getColor());
                    Assert.assertNotNull(block.getBack().getColor());
                    Assert.assertNotNull(block.getTop().getColor());
                    Assert.assertNotNull(block.getBottom().getColor());
                    Assert.assertNotNull(block.getRight().getColor());
                    Assert.assertNotNull(block.getLeft().getColor());

                    Assert.assertEquals(x, block.getWidth());
                    Assert.assertEquals(y, block.getHeight());
                    Assert.assertEquals(z, block.getDepth());
                }
            }
        }

    }


    private Block[][][] getMixedUpBlocks() {
        Block[][][] blockArray = new Block[SIZE][SIZE][SIZE];
        List<Block> blocks = new ArrayList<>();
        /* This is the one you always have to make sure matches*/
        Block bottomLeftFrontBlock = new Block(0, 0, 0,
                BlockType.CORNER,
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.GREEN),
                new BlockFace(FaceColor.ORANGE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.WHITE),
                new BlockFace(FaceColor.DEFAULT));

        Block bottomRightFrontBlock = new Block(1, 0, 0,
                BlockType.CORNER,
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.ORANGE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.BLUE),
                new BlockFace(FaceColor.YELLOW),
                new BlockFace(FaceColor.DEFAULT));
        
        Block topLeftFrontBlock = new Block(0, 1, 0,
                BlockType.CORNER,
                new BlockFace(FaceColor.RED),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.BLUE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.WHITE),
                new BlockFace(FaceColor.DEFAULT));

        Block topRightFrontBlock = new Block(1, 1, 0,
                BlockType.CORNER,
                new BlockFace(FaceColor.RED),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.GREEN),
                new BlockFace(FaceColor.WHITE),
                new BlockFace(FaceColor.DEFAULT));


        Block bottomLeftBackBlock = new Block(0, 0, 1,
                BlockType.CORNER,
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.GREEN),
                new BlockFace(FaceColor.YELLOW),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.RED));

        Block bottomRightBackBlock = new Block(1, 0, 1,
                BlockType.CORNER,
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.GREEN),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.YELLOW),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.ORANGE));

        Block topLeftBackBlock = new Block(0, 1, 1,
                BlockType.CORNER,
                new BlockFace(FaceColor.ORANGE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.BLUE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.WHITE));

        Block topRightBackBlock = new Block(1, 1, 1,
                BlockType.CORNER,
                new BlockFace(FaceColor.BLUE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.RED),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.YELLOW));

        blocks.add(bottomLeftFrontBlock);
        blocks.add(bottomRightFrontBlock);
        blocks.add(topLeftFrontBlock);
        blocks.add(topRightFrontBlock);

        blocks.add(bottomLeftBackBlock);
        blocks.add(bottomRightBackBlock);
        blocks.add(topLeftBackBlock);
        blocks.add(topRightBackBlock);
        for (Block block : blocks) {
            blockArray[block.getWidth()][block.getHeight()][block.getDepth()] = block;
        }
        return blockArray;
    }


    @Test
    public void testWriteSolutionsToFile() {
        Map<Integer, SolutionSteps> storedStates = new HashMap<>();
        List<SolutionSteps> solutionSteps = ((TwoCube) cube).readSolutionSteps("src/test/resources/solutionStep3.txt");
        for (SolutionSteps step : solutionSteps) {
            storedStates.put(step.getDescriptor().hashCode(), step);
        }
        ((TwoCube) cube).writeSolutionsToFile(storedStates, "testSolutions.txt");
    }
}
