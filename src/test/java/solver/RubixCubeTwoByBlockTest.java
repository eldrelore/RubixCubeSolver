package solver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import solver.model.CubeRotation;
import solver.types.*;

import java.io.File;
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.RED.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getRight());
                        Assert.assertEquals(Color.RED.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getBack());
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getRight());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.RED.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.RED.getValue(), block.getBack());
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getRight());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.RED.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.RED.getValue(), block.getBack());
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.RED.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getRight());
                        Assert.assertEquals(Color.RED.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getBack());
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBack());
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.WHITE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.WHITE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getBack());
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.WHITE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.WHITE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getBack());
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBack());
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.RED.getValue(), block.getBottom());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.RED.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getBottom());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.RED.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.RED.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getBottom());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.RED.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.RED.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
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
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y && 0 == z) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 1 == y && 0 == z) {
                        /* should now be rotated, and what was 1,1,0*/
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (0 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.RED.getValue(), block.getBottom());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (1 == x && 1 == y && 0 == z) {
                        Assert.assertEquals(Color.BLUE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.RED.getValue(), block.getRight());
                        Assert.assertEquals(Color.WHITE.getValue(), block.getFront());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBack());
                    } else if (1 == x && 0 == y) {
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getTop());
                        Assert.assertEquals(Color.RED.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else if (0 == x) {
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.GREEN.getValue(), block.getLeft());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
                    } else {
                        Assert.assertEquals(Color.ORANGE.getValue(), block.getTop());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getBottom());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getLeft());
                        Assert.assertEquals(Color.BLUE.getValue(), block.getRight());
                        Assert.assertEquals(Color.DEFAULT.getValue(), block.getFront());
                        Assert.assertEquals(Color.YELLOW.getValue(), block.getBack());
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
        Assert.assertEquals("c 1", steps.get(0));
        Assert.assertEquals("l 1", steps.get(1));
    }


    @Test
    public void testInitializeCubeFromDescriptor() {
        Block[][][] initializedBlockArray = cube.initializeCube();
        String initializedCubeDescriptor = cube.getDescriptor(initializedBlockArray);
        Block[][][] blockArrayFromDescriptor = cube.initializeCubeFromDescriptor(initializedCubeDescriptor);
        Assert.assertNotNull(blockArrayFromDescriptor);
        int size = initializedBlockArray.length;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    Block initializedBlock = initializedBlockArray[x][y][z];
                    Block descriptorBlock = blockArrayFromDescriptor[x][y][z];
                    Assert.assertArrayEquals(initializedBlock.getDimensions(), descriptorBlock.getDimensions());
                    Assert.assertArrayEquals(initializedBlock.getFaces(), descriptorBlock.getFaces());
                }
            }

        }


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
        Map<Integer, SolutionSteps> solutions = ((TwoCube) cube).generateIterationStates(1, new HashMap<Integer, Boolean>(), new AtomicInteger(), new AtomicInteger(), new AtomicInteger());
        ;
        Assert.assertNotNull(solutions);
        Assert.assertEquals(6, solutions.size());
        int zeroMovementCounter = 0;
        int expectedZeroMotionCounters = 0;

        int oneMovementCounter = 0;
        int expectedOneMovementCounter = 6;

        for (SolutionSteps solutionSteps : solutions.values()) {
            if (0 == solutionSteps.getSteps().size()) {
                zeroMovementCounter++;
            } else if (1 == solutionSteps.getSteps().size()) {
                oneMovementCounter++;
            }
        }
        Assert.assertEquals(expectedZeroMotionCounters, zeroMovementCounter);
        Assert.assertEquals(expectedOneMovementCounter, oneMovementCounter);
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

        List<String> reversedSteps = ((TwoCube) cube).reverseSteps(steps);
        Assert.assertNotNull(reversedSteps);
        Assert.assertEquals(steps.size(), reversedSteps.size());
        Assert.assertEquals(reversedSteps.get(0), ReveralRotation.CLOCKWISE.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(1), ReveralRotation.COUNTER_CLOCKWISE.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(2), ReveralRotation.LEFT.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(3), ReveralRotation.RIGHT.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(4), ReveralRotation.FORWARD.getCommand() + " 1");
        Assert.assertEquals(reversedSteps.get(5), ReveralRotation.BACKWARD.getCommand() + " 1");

        Assert.assertEquals(reversedSteps.get(6), "test 1");
    }

    @Test
    public void testReadSolutionsFromFile() {
        String solutionFile = "src/test/resources/TestSolution.txt";
        String testDescriptor = "[[[{\\\"width\\\":0,\\\"height\\\":0,\\\"depth\\\":0,\\\"top\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"GREEN\\\"},\\\"left\\\":{\\\"color\\\":\\\"ORANGE\\\"},\\\"right\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"front\\\":{\\\"color\\\":\\\"WHITE\\\"},\\\"back\\\":{\\\"color\\\":\\\"DEFAULT\\\"}},{\\\"width\\\":0,\\\"height\\\":0,\\\"depth\\\":1,\\\"top\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"GREEN\\\"},\\\"left\\\":{\\\"color\\\":\\\"ORANGE\\\"},\\\"right\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"front\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"back\\\":{\\\"color\\\":\\\"YELLOW\\\"}}],[{\\\"width\\\":0,\\\"height\\\":1,\\\"depth\\\":0,\\\"top\\\":{\\\"color\\\":\\\"RED\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"left\\\":{\\\"color\\\":\\\"YELLOW\\\"},\\\"right\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"front\\\":{\\\"color\\\":\\\"BLUE\\\"},\\\"back\\\":{\\\"color\\\":\\\"DEFAULT\\\"}},{\\\"width\\\":0,\\\"height\\\":1,\\\"depth\\\":1,\\\"top\\\":{\\\"color\\\":\\\"WHITE\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"left\\\":{\\\"color\\\":\\\"RED\\\"},\\\"right\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"front\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"back\\\":{\\\"color\\\":\\\"GREEN\\\"}}]],[[{\\\"width\\\":1,\\\"height\\\":0,\\\"depth\\\":0,\\\"top\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"BLUE\\\"},\\\"left\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"right\\\":{\\\"color\\\":\\\"ORANGE\\\"},\\\"front\\\":{\\\"color\\\":\\\"WHITE\\\"},\\\"back\\\":{\\\"color\\\":\\\"DEFAULT\\\"}},{\\\"width\\\":1,\\\"height\\\":0,\\\"depth\\\":1,\\\"top\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"YELLOW\\\"},\\\"left\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"right\\\":{\\\"color\\\":\\\"GREEN\\\"},\\\"front\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"back\\\":{\\\"color\\\":\\\"RED\\\"}}],[{\\\"width\\\":1,\\\"height\\\":1,\\\"depth\\\":0,\\\"top\\\":{\\\"color\\\":\\\"ORANGE\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"left\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"right\\\":{\\\"color\\\":\\\"YELLOW\\\"},\\\"front\\\":{\\\"color\\\":\\\"BLUE\\\"},\\\"back\\\":{\\\"color\\\":\\\"DEFAULT\\\"}},{\\\"width\\\":1,\\\"height\\\":1,\\\"depth\\\":1,\\\"top\\\":{\\\"color\\\":\\\"BLUE\\\"},\\\"bottom\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"left\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"right\\\":{\\\"color\\\":\\\"WHITE\\\"},\\\"front\\\":{\\\"color\\\":\\\"DEFAULT\\\"},\\\"back\\\":{\\\"color\\\":\\\"RED\\\"}}]]]";
        SolutionSteps solution = ((TwoCube) cube).readSolutionsFromFile(solutionFile, testDescriptor);
        Assert.assertNotNull(solution);
    }

    @Test
    public void testReadFileContents() {
        String solutionFile = "src/test/resources/TestSolution.txt";
        String fileContents = ((TwoCube) cube).readFileContents(solutionFile);
        Assert.assertNotNull(fileContents);
        Assert.assertFalse(fileContents.isEmpty());
    }


    @Test
    public void testGenerateAllCubeRotationsFromState() {
        String line = "{\"steps\":[\"b 1\"],\"solutionSteps\":[],\"descriptor\":\"[[[{\\\"faces\\\":[0,4,6,0,3,0],\\\"dimensions\\\":[0,0,0]},{\\\"faces\\\":[0,4,6,0,0,5],\\\"dimensions\\\":[0,0,1]}],[{\\\"faces\\\":[2,0,6,0,3,0],\\\"dimensions\\\":[0,1,0]},{\\\"faces\\\":[2,0,6,0,0,5],\\\"dimensions\\\":[0,1,1]}]],[[{\\\"faces\\\":[0,5,0,1,4,0],\\\"dimensions\\\":[1,0,0]},{\\\"faces\\\":[0,5,0,1,0,2],\\\"dimensions\\\":[1,0,1]}],[{\\\"faces\\\":[3,0,0,1,4,0],\\\"dimensions\\\":[1,1,0]},{\\\"faces\\\":[3,0,0,1,0,2],\\\"dimensions\\\":[1,1,1]}]]]\"}";
        ObjectMapper mapper = new ObjectMapper();
        boolean exceptionEncountered = false;
        try {
            SolutionSteps priorMovementState = mapper.readValue(line, SolutionSteps.class);
            Map<Integer, SolutionSteps> stepMap = new HashMap<>();
            Map<Integer, Boolean> alreadyFoundSteps = new HashMap<>();
            AtomicInteger totalAttempts = new AtomicInteger();
            AtomicInteger currentMovementStates = new AtomicInteger();
            AtomicInteger knownStates = new AtomicInteger();
            ((TwoCube) cube).generateAllCubeRotationsFromState(priorMovementState, "src/test/resources/testSolution1.txt", stepMap, alreadyFoundSteps, totalAttempts, currentMovementStates, knownStates);

        } catch (IOException e) {
            e.printStackTrace();
            exceptionEncountered = true;
        }
        Assert.assertFalse(exceptionEncountered);
    }

    @Test
    public void testCloneBlock() {
        Block bottomLeftFrontBlock = new Block(0, 0, 0,
                new int[]{Color.DEFAULT.getValue(),
                        Color.GREEN.getValue(),
                        Color.ORANGE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.WHITE.getValue(),
                        Color.DEFAULT.getValue()});
        Block clonedBlock = new Block();
        ((TwoCube) cube).cloneBlock(bottomLeftFrontBlock, clonedBlock);
        Assert.assertEquals(bottomLeftFrontBlock.getWidth(), clonedBlock.getWidth());
        Assert.assertEquals(bottomLeftFrontBlock.getHeight(), clonedBlock.getHeight());
        Assert.assertEquals(bottomLeftFrontBlock.getDepth(), clonedBlock.getDepth());
        Assert.assertArrayEquals(bottomLeftFrontBlock.getFaces(), clonedBlock.getFaces());
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
                    Assert.assertNotNull(block.getFront());
                    Assert.assertNotNull(block.getBack());
                    Assert.assertNotNull(block.getTop());
                    Assert.assertNotNull(block.getBottom());
                    Assert.assertNotNull(block.getRight());
                    Assert.assertNotNull(block.getLeft());

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
                new int[]{Color.DEFAULT.getValue(),
                        Color.GREEN.getValue(),
                        Color.ORANGE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.WHITE.getValue(),
                        Color.DEFAULT.getValue()});

        Block bottomRightFrontBlock = new Block(1, 0, 0,
                new int[]{Color.DEFAULT.getValue(),
                        Color.WHITE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.BLUE.getValue(),
                        Color.ORANGE.getValue(),
                        Color.DEFAULT.getValue()});

        Block topLeftFrontBlock = new Block(0, 1, 0,
                new int[]{Color.WHITE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.RED.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.BLUE.getValue(),
                        Color.DEFAULT.getValue()});

        Block topRightFrontBlock = new Block(1, 1, 0,
                new int[]{Color.BLUE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.YELLOW.getValue(),
                        Color.RED.getValue(),
                        Color.DEFAULT.getValue()});


        Block bottomLeftBackBlock = new Block(0, 0, 1,
                new int[]{Color.DEFAULT.getValue(),
                        Color.YELLOW.getValue(),
                        Color.GREEN.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.ORANGE.getValue()});

        Block bottomRightBackBlock = new Block(1, 0, 1,
                new int[]{Color.DEFAULT.getValue(),
                        Color.YELLOW.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.BLUE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.ORANGE.getValue()});

        Block topLeftBackBlock = new Block(0, 1, 1,
                new int[]{Color.YELLOW.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.GREEN.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.RED.getValue()});

        Block topRightBackBlock = new Block(1, 1, 1,
                new int[]{Color.WHITE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.GREEN.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.RED.getValue()});

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
        String solutionFileName = "testSolutions_written.txt";
        ((TwoCube) cube).appendSolutionsToFile(storedStates, solutionFileName);
        File file = new File(TwoCube.RESOURCE_DIRECTORY + "/" + solutionFileName);
        Assert.assertTrue(file.exists());

    }

    @Test
    public void testCorrectCommandNoPreservation() {
        String command = "left";
        String dimension = "1";
        String updatedCommand = ((TwoCube) cube).correctPreserveCommand(command, dimension);
        Assert.assertEquals("left 1", updatedCommand);

        command = "left";
        dimension = "0";
        updatedCommand = ((TwoCube) cube).correctPreserveCommand(command, dimension);
        Assert.assertEquals("left 0", updatedCommand);

        command = "right";
        dimension = "0";
        updatedCommand = ((TwoCube) cube).correctPreserveCommand(command, dimension);
        Assert.assertEquals("right 0", updatedCommand);

        command = "right";
        dimension = "1";
        updatedCommand = ((TwoCube) cube).correctPreserveCommand(command, dimension);
        Assert.assertEquals("right 1", updatedCommand);

        command = "forward";
        dimension = "0";
        updatedCommand = ((TwoCube) cube).correctPreserveCommand(command, dimension);
        Assert.assertEquals("forward 0", updatedCommand);

        command = "forward";
        dimension = "1";
        updatedCommand = ((TwoCube) cube).correctPreserveCommand(command, dimension);
        Assert.assertEquals("forward 1", updatedCommand);

        command = "backward";
        dimension = "1";
        updatedCommand = ((TwoCube) cube).correctPreserveCommand(command, dimension);
        Assert.assertEquals("backward 1", updatedCommand);

        command = "backward";
        dimension = "0";
        updatedCommand = ((TwoCube) cube).correctPreserveCommand(command, dimension);
        Assert.assertEquals("backward 0", updatedCommand);

        command = "clockwise";
        dimension = "1";
        updatedCommand = ((TwoCube) cube).correctPreserveCommand(command, dimension);
        Assert.assertEquals("clockwise 1", updatedCommand);

        command = "clockwise";
        dimension = "0";
        updatedCommand = ((TwoCube) cube).correctPreserveCommand(command, dimension);
        Assert.assertEquals("clockwise 0", updatedCommand);

        command = "counter-clockwise";
        dimension = "0";
        updatedCommand = ((TwoCube) cube).correctPreserveCommand(command, dimension);
        Assert.assertEquals("counter-clockwise 0", updatedCommand);

        command = "counter-clockwise";
        dimension = "1";
        updatedCommand = ((TwoCube) cube).correctPreserveCommand(command, dimension);
        Assert.assertEquals("counter-clockwise 1", updatedCommand);

    }

    @Test
    public void testGenerateDescriptorSimplify() {
        String expectedValue = "[[[{\"faces\":[0,4,6,0,3,0],\"dimensions\":[0,0,0]},{\"faces\":[0,5,4,0,0,6],\"dimensions\":[0,0,1]}],[{\"faces\":[3,0,1,0,2,0],\"dimensions\":[0,1,0]},{\"faces\":[5,0,4,0,0,1],\"dimensions\":[0,1,1]}]],[[{\"faces\":[0,3,0,2,6,0],\"dimensions\":[1,0,0]},{\"faces\":[0,5,0,2,0,6],\"dimensions\":[1,0,1]}],[{\"faces\":[2,0,0,5,1,0],\"dimensions\":[1,1,0]},{\"faces\":[3,0,0,4,0,1],\"dimensions\":[1,1,1]}]]]";
        Block[][][] blocks = getMixedUpBlocks();
        String descriptor = cube.getDescriptor(blocks);
        Assert.assertEquals(expectedValue, descriptor);
    }

    @Test
    public void testReverseSteps() {
        List<String> steps = Arrays.asList("b 1", "r 1", "w 1", "b 1", "l 1", "w 1",
                "w 1", "l 1", "c 1", "r 1");
        List<String> expectedReversedSteps = Arrays.asList("l 1", "w 1", "r 1", "c 1", "c 1", "r 1", "f 1", "c 1", "l 1", "f 1");
        List<String> reversedSteps = ((TwoCube) cube).reverseSteps(steps);
        Assert.assertEquals(expectedReversedSteps, reversedSteps);
    }


    /* These two are more integration / validation, one generates all the states, the other checks a known state & its solution */
//    @Test
    public void testGenerateStates() {
        Map<Integer, SolutionSteps> solutions = cube.generateStates(null);
        String solutionFile = TwoCube.RESOURCE_DIRECTORY + "/" + "TwoByTwoCubeSolutions.txt";
        File file = new File(solutionFile);
        Assert.assertTrue(file.exists());
    }

    //    @Test
    public void testSolve() {
        String solutionFile = TwoCube.RESOURCE_DIRECTORY + "/" + "TwoByTwoCubeSolutions.txt";
        Block[][][] mixedUpBlocks = getMixedUpBlocks();
        String descriptor = cube.getDescriptor(mixedUpBlocks);
        SolutionSteps solution = cube.solve(solutionFile, descriptor);
        Assert.assertNotNull(solution);
        List<String> generationSteps = solution.getSteps();
        Assert.assertNotNull(generationSteps);
        Assert.assertFalse(generationSteps.isEmpty());
        List<String> expectedGenerationsteps = Arrays.asList("f 1", "w 1", "b 1", "r 1",
                "w 1", "f 1", "r 1", "f 1", "w 1", "b 1", "b 1",
                "w 1");
        Assert.assertEquals(expectedGenerationsteps, generationSteps);

        List<String> solutionSteps = solution.getSolutionSteps();
        Assert.assertNotNull(solutionSteps);
        Assert.assertFalse(solutionSteps.isEmpty());

        System.out.println("Generation Steps: " + generationSteps);
        System.out.println("Solution steps: " + solutionSteps);
        List<String> expectedSolutionSteps = Arrays.asList("c 1", "f 1", "f 1", "c 1", "b 1", "l 1", "b 1", "c 1", "l 1", "f 1", "c 1", "b 1");
        Assert.assertEquals(expectedSolutionSteps, solutionSteps);
    }
}
