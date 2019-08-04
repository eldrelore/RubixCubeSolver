package solver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import solver.model.CubeRotation;
import solver.types.BlockFace;
import solver.types.Rotation;

import java.util.List;
import java.util.Map;

/**
 * uses bottom front left as point of reference; therefore can't move it.
 * Reduces number of moves possible to rotations of right, top, and back (and any mids).
 */
public interface Cube {
    Integer MIN_HEIGHT = 0;
    Integer MIN_WIDTH = 0;
    Integer MIN_DEPTH = 0;


    Block[][][] initializeCube();

    Integer getSize();

    SolutionSteps solve(Map<String, Cube> solutionStates);

    Map<Integer, SolutionSteps> generateStates();

    default void switchFaceForward(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        BlockFace frontFaceHolder = block.getFront();
        block.setFront(block.getTop());
        block.setTop(block.getBack());
        block.setBack(block.getBottom());
        block.setBottom(frontFaceHolder);
    }


    default void switchFaceBackwards(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        BlockFace frontFaceHolder = block.getFront();
        block.setFront(block.getBottom());
        block.setBottom(block.getBack());
        block.setBack(block.getTop());
        block.setTop(frontFaceHolder);
    }

    default void rotateWidthForward(int width, Block[][][] cube, List<String> steps) {
        rotateWidthForward(width, true, cube, steps);
    }

    default void rotateWidthForward(int width, boolean recordSteps, Block[][][] cube, List<String> steps) {
        if (width > 0) {
            if (recordSteps) {
                steps.add(Rotation.FORWARD.getDisplayString(width));
            }
            int maxDimension = getMaxDimension();
            /* create holder for right top front and spin holder in place*/
            Block tempBlock = cube[width][maxDimension][MIN_DEPTH];
            switchFaceForward(tempBlock);

            /* right top front receives right top back ; rotate right top front block*/
            cube[width][maxDimension][MIN_DEPTH] = cube[width][maxDimension][maxDimension];
            switchFaceForward(cube[width][maxDimension][MIN_DEPTH]);


            /* right top back receives right bottom back, rotate right top back block */
            cube[width][maxDimension][maxDimension] = cube[width][MIN_HEIGHT][maxDimension];
            switchFaceForward(cube[width][maxDimension][maxDimension]);

            /* right bottom back receives bottom right front; rotate right bottom  back block */
            cube[width][MIN_HEIGHT][maxDimension] = cube[width][MIN_HEIGHT][MIN_DEPTH];
            switchFaceForward(cube[width][MIN_HEIGHT][maxDimension]);

            /* right bottom front receives holder; already rotated*/
            cube[width][MIN_HEIGHT][MIN_DEPTH] = tempBlock;
        } else {
            if (recordSteps) {
                steps.add("fpf 0");
            }
            for (int i = 1; i < getSize(); i++) {
                rotateWidthBackward(i, false, cube, steps);
            }
        }
    }


    default void rotateWidthBackward(int width, Block[][][] cube, List<String> steps) {
        rotateWidthBackward(width, true, cube, steps);
    }

    default void rotateWidthBackward(int width, boolean recordSteps, Block[][][] cube, List<String> steps) {
        if (width > 0) {
            if (recordSteps) {
                steps.add(Rotation.BACKWARD.getDisplayString(width));
            }
            int maxDimension = getMaxDimension();
            /* create holder for right top front and spin holder in place*/
            Block tempBlock = cube[width][maxDimension][MIN_DEPTH];
            switchFaceBackwards(tempBlock);

            /* right top front receives right bottom front ; rotate right top front block*/
            cube[width][maxDimension][MIN_DEPTH] = cube[width][MIN_HEIGHT][MIN_DEPTH];
            switchFaceBackwards(cube[width][maxDimension][MIN_DEPTH]);


            /* right bottom front receives right bottom back, rotate right bottom front block */
            cube[width][MIN_HEIGHT][MIN_DEPTH] = cube[width][MIN_HEIGHT][maxDimension];
            switchFaceBackwards(cube[width][MIN_HEIGHT][MIN_DEPTH]);

            /* right bottom back receives right top back; rotate right bottom back block */
            cube[width][MIN_HEIGHT][maxDimension] = cube[width][maxDimension][maxDimension];
            switchFaceBackwards(cube[width][MIN_HEIGHT][maxDimension]);

            /* right top back receives holder; already rotated*/
            cube[width][maxDimension][maxDimension] = tempBlock;
        } else {
            if (recordSteps) {
                steps.add("bpf 0");
            }
            for (int i = 1; i < getSize(); i++) {
                rotateWidthForward(i, false, cube, steps);
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
        BlockFace leftBlockFaceHolder = block.getLeft();
        block.setLeft(block.getFront());
        block.setFront(block.getRight());
        block.setRight(block.getBack());
        block.setBack(leftBlockFaceHolder);
    }

    default void switchFaceRight(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        BlockFace frontFaceHolder = block.getFront();
        block.setFront(block.getLeft());
        block.setLeft(block.getBack());
        block.setBack(block.getRight());
        block.setRight(frontFaceHolder);
    }

    default void rotateHeightLeft(int height, Block[][][] blocks, List<String> steps) {
        rotateHeightLeft(height, true, blocks, steps);
    }

    default void rotateHeightLeft(int height, boolean recordSteps, Block[][][] cube, List<String> steps) {
        if (height > 0) {
            if (recordSteps) {
                steps.add(Rotation.LEFT.getDisplayString(height));
            }

            /* create holder for  left top front and spin holder in place*/
            Block tempBlock = cube[MIN_WIDTH][height][MIN_DEPTH];
            switchFaceLeft(tempBlock);
            int maxDimension = getMaxDimension();

            /* left top front receives right top front ; rotate left top front block*/
            cube[MIN_WIDTH][height][MIN_DEPTH] = cube[getMaxDimension()][height][MIN_DEPTH];
            switchFaceLeft(cube[MIN_WIDTH][height][MIN_DEPTH]);

            /* top right front receives right top back, rotate right top front block */
            cube[maxDimension][height][MIN_DEPTH] = cube[maxDimension][height][maxDimension];
            switchFaceLeft(cube[maxDimension][height][MIN_DEPTH]);

            /* right top back receives left top back; rotate right top back block */
            cube[maxDimension][height][maxDimension] = cube[MIN_WIDTH][height][maxDimension];
            switchFaceLeft(cube[maxDimension][height][maxDimension]);

            /* right top back receives holder; already rotated*/
            cube[MIN_WIDTH][height][maxDimension] = tempBlock;
        } else {
            if (recordSteps) {
                steps.add("lpf 0");
            }
            for (int i = 1; i < getSize(); i++) {
                rotateHeightRight(i, false, cube, steps);
            }
        }
    }

    default void rotateHeightRight(int height, Block[][][] cube, List<String> steps) {
        rotateHeightRight(height, true, cube, steps);
    }

    default void rotateHeightRight(int height, boolean recordSteps, Block[][][] cube, List<String> steps) {
        if (height > 0) {
            if (recordSteps) {
                steps.add(Rotation.RIGHT.getDisplayString(height));
            }
            int maxDimension = getMaxDimension();
            /* create holder for right top front and spin holder in place*/
            Block tempBlock = cube[maxDimension][height][MIN_DEPTH];
            switchFaceRight(tempBlock);

            /* right top front receives left top front ; rotate right top front block*/
            cube[maxDimension][height][MIN_DEPTH] = cube[MIN_WIDTH][height][MIN_DEPTH];
            switchFaceRight(cube[maxDimension][height][MIN_DEPTH]);


            /* left top front receives left top back, rotate left top front block */
            cube[MIN_WIDTH][height][MIN_DEPTH] = cube[0][height][maxDimension];
            switchFaceRight(cube[MIN_WIDTH][height][MIN_DEPTH]);

            /* left top back receives right top front; rotate left top back block */
            cube[MIN_WIDTH][height][maxDimension] = cube[1][height][maxDimension];
            switchFaceRight(cube[MIN_WIDTH][height][maxDimension]);

            /* right top back receives holder; already rotated*/
            cube[maxDimension][height][maxDimension] = tempBlock;
        } else {
            if (recordSteps) {
                steps.add("rpf 0");
            }
            for (int i = 1; i < getSize(); i++) {
                rotateHeightLeft(i, false, cube, steps);
            }
        }
    }


    default void switchFaceClockwise(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        BlockFace topFaceHolder = block.getTop();
        block.setTop(block.getLeft());
        block.setLeft(block.getBottom());
        block.setBottom(block.getRight());
        block.setRight(topFaceHolder);
    }


    default void switchFaceCounterClockwise(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        BlockFace topFaceHolder = block.getTop();
        block.setTop(block.getRight());
        block.setRight(block.getBottom());
        block.setBottom(block.getLeft());
        block.setLeft(topFaceHolder);
    }


    default void rotateDepthClockwise(int depth, Block[][][] cube, List<String> steps) {
        rotateDepthClockWise(depth, true, cube, steps);
    }

    default void rotateDepthClockWise(int depth, boolean recordSteps, Block[][][] cube, List<String> steps) {
        if (depth > 0) {
            if (recordSteps) {
                steps.add(Rotation.CLOCKWISE.getDisplayString(depth));
            }
            int maxDimension = getMaxDimension();
            /* create holder for right top back and spin holder in place*/
            Block tempBlock = cube[maxDimension][maxDimension][depth];
            switchFaceClockwise(tempBlock);

            /* right top back receives left top back ; rotate right top back block*/
            cube[maxDimension][maxDimension][depth] = cube[MIN_WIDTH][maxDimension][depth];
            switchFaceClockwise(cube[maxDimension][maxDimension][depth]);


            /* left top back receives left bottom back, rotate left top back block */
            cube[MIN_WIDTH][maxDimension][depth] = cube[MIN_WIDTH][MIN_HEIGHT][depth];
            switchFaceClockwise(cube[MIN_WIDTH][maxDimension][depth]);

            /* left bottom back receives right bottom back; rotate left bottom back block */
            cube[MIN_WIDTH][MIN_HEIGHT][depth] = cube[maxDimension][MIN_HEIGHT][depth];
            switchFaceClockwise(cube[MIN_WIDTH][MIN_HEIGHT][depth]);

            /* right bottom back receives holder; already rotated*/
            cube[maxDimension][MIN_HEIGHT][depth] = tempBlock;
        } else {
            if (recordSteps) {
                steps.add("cpf 0");
            }
            for (int i = 1; i < getSize(); i++) {
                rotateDepthCounterClockwise(i, false, cube, steps);
            }
        }
    }


    default void rotateDepthCounterClockwise(int depth, Block[][][] cube, List<String> steps) {
        rotateDepthCounterClockwise(depth, true, cube, steps);
    }

    default void rotateDepthCounterClockwise(int depth, boolean recordSteps, Block[][][] cube, List<String> steps) {
        if (depth > 0) {
            steps.add(Rotation.COUNTER_CLOCKWISE.getDisplayString(depth));
            int maxDimension = getMaxDimension();
            /* create holder for  right top back and spin holder in place*/
            Block tempBlock = cube[maxDimension][maxDimension][depth];
            switchFaceCounterClockwise(tempBlock);

            /* right top front receives left top front ; rotate right top front block*/
            cube[maxDimension][maxDimension][depth] = cube[maxDimension][MIN_HEIGHT][depth];
            switchFaceCounterClockwise(cube[maxDimension][maxDimension][depth]);


            /* top left front receives right top back, rotate left top front block */
            cube[maxDimension][MIN_HEIGHT][depth] = cube[MIN_WIDTH][MIN_HEIGHT][depth];
            switchFaceCounterClockwise(cube[maxDimension][MIN_HEIGHT][depth]);

            /* right top back receives right top front; rotate right top back block */
            cube[MIN_WIDTH][MIN_HEIGHT][depth] = cube[MIN_WIDTH][maxDimension][depth];
            switchFaceCounterClockwise(cube[MIN_WIDTH][MIN_HEIGHT][depth]);

            /* right top back receives holder; already rotated*/
            cube[MIN_WIDTH][maxDimension][depth] = tempBlock;
        } else {
            if (recordSteps) {
                steps.add("ccwpf 0");
            }
            for (int i = 1; i < getSize(); i++) {
                rotateDepthClockWise(i, false, cube, steps);
            }
        }
    }

    default void setBlock(Block block) {
        Block[][][] blocks = initializeCube();
        blocks[block.getWidth()][block.getHeight()][block.getDepth()] = block;
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
