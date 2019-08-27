package solver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import solver.model.CubeRotation;
import solver.types.Color;
import solver.types.Rotation;

import java.util.List;
import java.util.Map;

/**
 * uses bottom front left as point of reference; therefore can't move it.
 * Reduces number of moves possible to rotations of right, top, and back (and any mids).
 */
public interface Cube {
    Block[][][] initializeCube();

    Integer getSize();

    SolutionSteps solve(String solutionFileName, String descriptor);

    Map<Integer, SolutionSteps> generateStates(Integer max);

    default void switchFaceForward(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        Color frontFaceHolder = block.getFront();
        block.setFront(block.getTop());
        block.setTop(block.getBack());
        block.setBack(block.getBottom());
        block.setBottom(frontFaceHolder);
    }


    default void switchFaceBackwards(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        Color frontFaceHolder = block.getFront();
        block.setFront(block.getBottom());
        block.setBottom(block.getBack());
        block.setBack(block.getTop());
        block.setTop(frontFaceHolder);
    }


    default void rotateWidthForward(int width, Block[][][] cube, List<String> steps) {
        if (width > 0) {
            steps.add(Rotation.FORWARD.getDisplayString(width));
            int length = getMaxDimension();
            for (int y = 0; y <= length / 2; y++) {
                for (int z = 0; z < length - y; z++) {
                    Block holder = cube[width][y][z];
                    switchFaceForward(holder);

                    cube[width][y][z] = cube[width][length - z][y];
                    switchFaceForward(cube[width][y][z]);

                    cube[width][length - z][y] = cube[width][length - y][length - z];
                    switchFaceForward(cube[width][length - z][y]);

                    cube[width][length - y][length - z] = cube[width][z][length - y];
                    switchFaceForward(cube[width][length - y][length - z]);

                    cube[width][z][length - y] = holder;
                }
            }
        } else {
            int size = getSize();
            for (int i = 1; i < size; i++) {
                rotateWidthBackward(i, cube, steps);
            }
        }
    }


    default void setBlockArrayWidthHeightDepth(Block[][][] cube) {
        int size = getSize();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    Block block = cube[x][y][z];
                    block.setWidth(x);
                    block.setHeight(y);
                    block.setDepth(z);
                }
            }
        }
    }

    default void setBlockWidthHeightDepth(Block block, int width, int height, int depth) {
        block.setWidth(width);
        block.setHeight(height);
        block.setDepth(depth);
    }


    default void rotateWidthBackward(int width, Block[][][] cube, List<String> steps) {
        if (width > 0) {
            steps.add(Rotation.BACKWARD.getDisplayString(width));

            int length = getMaxDimension();
            for (int y = 0; y <= length / 2; y++) {
                for (int z = 0; z < length - y; z++) {
                    Block holder = cube[width][y][z];
                    switchFaceBackwards(holder);

                    cube[width][y][z] = cube[width][z][length - y];
                    switchFaceBackwards(cube[width][y][z]);

                    cube[width][z][length - y] = cube[width][length - y][length - z];
                    switchFaceBackwards(cube[width][z][length - y]);

                    cube[width][length - y][length - z] = cube[width][length - z][y];
                    switchFaceBackwards(cube[width][length - y][length - z]);

                    cube[width][length - z][y] = holder;
                }
            }
        } else {
            int size = getSize();
            for (int i = 1; i < size; i++) {
                rotateWidthForward(i, cube, steps);
            }
        }
    }


    /* TODO No middles, all corners.  This should be extractable to a more generic way of rotating.
       Haven't bothered (yet) to do the math or search for it.
       I figure I'll get it working, and then see if it needs optimization.
       */

    default Integer getMaxDimension() {
        return getSize() - 1;
    }

    default void switchFaceLeft(Block block) {
        /* change facing: front becomes left, right becomes front,  back becomes right, left becomes back */
        Color leftBlockFaceHolder = block.getLeft();
        block.setLeft(block.getFront());
        block.setFront(block.getRight());
        block.setRight(block.getBack());
        block.setBack(leftBlockFaceHolder);
    }

    default void switchFaceRight(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        Color frontFaceHolder = block.getFront();
        block.setFront(block.getLeft());
        block.setLeft(block.getBack());
        block.setBack(block.getRight());
        block.setRight(frontFaceHolder);
    }


    default void rotateHeightLeft(int height, Block[][][] cube, List<String> steps) {
        if (height > 0) {
            steps.add(Rotation.LEFT.getDisplayString(height));
            int length = getMaxDimension();
            for (int x = 0; x <= length / 2; x++) {
                for (int z = 0; z < length - x; z++) {
                    Block holder = cube[x][height][z];
                    switchFaceLeft(holder);

                    cube[x][height][z] = cube[length - z][height][x];
                    switchFaceLeft(cube[x][height][z]);

                    cube[length - z][height][x] = cube[length - x][height][length - z];
                    switchFaceLeft(cube[length - z][height][x]);

                    cube[length - x][height][length - z] = cube[z][height][length - x];
                    switchFaceLeft(cube[length - x][height][length - z]);

                    cube[z][height][length - x] = holder;
                }
            }
        } else {
            int size = getSize();
            for (int i = 1; i < size; i++) {
                rotateHeightRight(i, cube, steps);
            }
        }
    }

    default void rotateHeightRight(int height, Block[][][] cube, List<String> steps) {
        if (height > 0) {
            steps.add(Rotation.RIGHT.getDisplayString(height));
            int length = getMaxDimension();
            for (int x = 0; x <= length / 2; x++) {
                for (int z = 0; z < length - x; z++) {
                    Block holder = cube[x][height][z];
                    switchFaceRight(holder);

                    cube[x][height][z] = cube[z][height][length - x];
                    switchFaceRight(cube[x][height][z]);

                    cube[z][height][length - x] = cube[length - x][height][length - z];
                    switchFaceRight(cube[z][height][length - x]);

                    cube[length - x][height][length - z] = cube[length - z][height][x];
                    switchFaceRight(cube[length - x][height][length - z]);

                    cube[length - z][height][x] = holder;
                }
            }
        } else {
            int size = getSize();
            for (int i = 1; i < size; i++) {
                rotateHeightLeft(i, cube, steps);
            }
        }
    }


    default void switchFaceClockwise(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        Color topFaceHolder = block.getTop();
        block.setTop(block.getLeft());
        block.setLeft(block.getBottom());
        block.setBottom(block.getRight());
        block.setRight(topFaceHolder);
    }


    default void switchFaceCounterClockwise(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        Color topFaceHolder = block.getTop();
        block.setTop(block.getRight());
        block.setRight(block.getBottom());
        block.setBottom(block.getLeft());
        block.setLeft(topFaceHolder);
    }

    default void rotateDepthClockwise(int depth, Block[][][] cube, List<String> steps) {
        if (depth > 0) {
            steps.add(Rotation.CLOCKWISE.getDisplayString(depth));
            int length = getMaxDimension();
            for (int x = 0; x <= length / 2; x++) {
                for (int y = 0; y < length - x; y++) {
                    Block holder = cube[x][y][depth];
                    switchFaceClockwise(holder);

                    cube[x][y][depth] = cube[length - y][x][depth];
                    switchFaceClockwise(cube[x][y][depth]);

                    cube[length - y][x][depth] = cube[length - x][length - y][depth];
                    switchFaceClockwise(cube[length - y][x][depth]);

                    cube[length - x][length - y][depth] = cube[y][length - x][depth];
                    switchFaceClockwise(cube[length - x][length - y][depth]);

                    cube[y][length - x][depth] = holder;
                }
            }
        } else {
            int size = getSize();
            for (int i = 1; i < size; i++) {
                rotateDepthCounterClockwise(i, cube, steps);
            }
        }
    }


    default void rotateDepthCounterClockwise(int depth, Block[][][] cube, List<String> steps) {
        if (depth > 0) {
            steps.add(Rotation.COUNTER_CLOCKWISE.getDisplayString(depth));
            int length = getMaxDimension();
            for (int x = 0; x <= length / 2; x++) {
                for (int y = 0; y < length - x; y++) {
                    Block holder = cube[x][y][depth];
                    switchFaceCounterClockwise(holder);

                    cube[x][y][depth] = cube[y][length - x][depth];
                    switchFaceCounterClockwise(cube[x][y][depth]);

                    cube[y][length - x][depth] = cube[length - x][length - y][depth];
                    switchFaceCounterClockwise(cube[y][length - x][depth]);

                    cube[length - x][length - y][depth] = cube[length - y][x][depth];
                    switchFaceCounterClockwise(cube[length - x][length - y][depth]);

                    cube[length - y][x][depth] = holder;
                }
            }
        } else {
            int size = getSize();
            for (int i = 1; i < size; i++) {
                rotateDepthClockwise(i, cube, steps);
            }
        }
    }

    ObjectMapper MAPPER = new ObjectMapper();

    default String getDescriptor(Block[][][] blocks) {
        /* create a descriptor for the cube (for x, y, z; non default faces, and sides) */
        StringBuilder sb = new StringBuilder();
        int size = getSize();

        String cubeDescriptor = null;
        try {
            cubeDescriptor = MAPPER.writeValueAsString(blocks);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return cubeDescriptor;
    }

    default SolutionSteps getCurrentState(CubeRotation cubeRotation) {
        SolutionSteps solutionSteps = new SolutionSteps();
        solutionSteps.setDescriptor(getDescriptor(cubeRotation.getBlockArray()));
        solutionSteps.setSteps(cubeRotation.getSteps());
        return solutionSteps;
    }
}
