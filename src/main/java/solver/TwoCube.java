package solver;

import solver.types.BlockFace;
import solver.types.BlockType;
import solver.types.FaceColor;
import solver.types.FaceDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TwoCube implements Cube {

    private static Block[][][] cube;

    public TwoCube() {
        buildCube();
    }

    private static final Integer SIZE = 2;
    private static final Integer MAX_HEIGHT = SIZE - 1;
    private static final Integer MAX_WIDTH = SIZE - 1;
    private static final Integer MAX_DEPTH = SIZE - 1;
    private static final Integer MIN_HEIGHT = 0;
    private static final Integer MIN_WIDTH = 0;
    private static final Integer MIN_DEPTH = 0;

    @Override
    public Cube buildCube() {
        cube = new Block[SIZE][SIZE][SIZE];
        List<Block> blocks = new ArrayList<>();
        Block bottomLeftFrontBlock = new Block(0, 0, 0,
                BlockType.CORNER,
                new BlockFace(FaceDirection.TOP, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.BOTTOM, FaceColor.GREEN),
                new BlockFace(FaceDirection.LEFT, FaceColor.ORANGE),
                new BlockFace(FaceDirection.RIGHT, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.FRONT, FaceColor.WHITE),
                new BlockFace(FaceDirection.BACK, FaceColor.DEFAULT));

        Block bottomRightFrontBlock = new Block(1, 0, 0,
                BlockType.CORNER,
                new BlockFace(FaceDirection.TOP, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.BOTTOM, FaceColor.GREEN),
                new BlockFace(FaceDirection.LEFT, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.RIGHT, FaceColor.RED),
                new BlockFace(FaceDirection.FRONT, FaceColor.WHITE),
                new BlockFace(FaceDirection.BACK, FaceColor.DEFAULT));

        Block topLeftFrontBlock = new Block(0, 1, 0,
                BlockType.CORNER,
                new BlockFace(FaceDirection.TOP, FaceColor.BLUE),
                new BlockFace(FaceDirection.BOTTOM, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.LEFT, FaceColor.ORANGE),
                new BlockFace(FaceDirection.RIGHT, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.FRONT, FaceColor.WHITE),
                new BlockFace(FaceDirection.BACK, FaceColor.DEFAULT));

        Block topRightFrontBlock = new Block(1, 1, 0,
                BlockType.CORNER,
                new BlockFace(FaceDirection.TOP, FaceColor.BLUE),
                new BlockFace(FaceDirection.BOTTOM, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.LEFT, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.RIGHT, FaceColor.RED),
                new BlockFace(FaceDirection.FRONT, FaceColor.WHITE),
                new BlockFace(FaceDirection.BACK, FaceColor.DEFAULT));


        Block bottomLeftBackBlock = new Block(0, 0, 1,
                BlockType.CORNER,
                new BlockFace(FaceDirection.TOP, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.BOTTOM, FaceColor.GREEN),
                new BlockFace(FaceDirection.LEFT, FaceColor.ORANGE),
                new BlockFace(FaceDirection.RIGHT, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.FRONT, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.BACK, FaceColor.YELLOW));

        Block bottomRightBackBlock = new Block(1, 0, 1,
                BlockType.CORNER,
                new BlockFace(FaceDirection.TOP, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.BOTTOM, FaceColor.GREEN),
                new BlockFace(FaceDirection.LEFT, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.RIGHT, FaceColor.RED),
                new BlockFace(FaceDirection.FRONT, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.BACK, FaceColor.YELLOW));

        Block topLeftBackBlock = new Block(0, 1, 1,
                BlockType.CORNER,
                new BlockFace(FaceDirection.TOP, FaceColor.BLUE),
                new BlockFace(FaceDirection.BOTTOM, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.LEFT, FaceColor.ORANGE),
                new BlockFace(FaceDirection.RIGHT, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.FRONT, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.BACK, FaceColor.YELLOW));

        Block topRightBackBlock = new Block(1, 1, 1,
                BlockType.CORNER,
                new BlockFace(FaceDirection.TOP, FaceColor.BLUE),
                new BlockFace(FaceDirection.BOTTOM, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.LEFT, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.RIGHT, FaceColor.RED),
                new BlockFace(FaceDirection.FRONT, FaceColor.DEFAULT),
                new BlockFace(FaceDirection.BACK, FaceColor.YELLOW));

        blocks.add(bottomLeftFrontBlock);
        blocks.add(bottomRightFrontBlock);
        blocks.add(topLeftFrontBlock);
        blocks.add(topRightFrontBlock);

        blocks.add(bottomLeftBackBlock);
        blocks.add(bottomRightBackBlock);
        blocks.add(topLeftBackBlock);
        blocks.add(topRightBackBlock);


        for (Block block : blocks) {
            cube[block.getWidth()][block.getHeight()][block.getDepth()] = block;
        }
        return this;
    }
/* TODO:  implement general (probably abstract inside Cube)
    way to track states, current state,
    and to solve from states (find current state in states, and reverse the steps)*/

    @Override
    public SolutionSteps solve(Map<String, Cube> solutionStates) {
        return null;
    }

    @Override
    public Map<String, Cube> generateStates(int maxSteps) {
        return null;
    }

    @Override
    public SolutionSteps getCurrentState() {
        return null;
    }

    @Override
    public Block[][][] getCube() {
        if (null == cube) {
            throw new RuntimeException("Cube hasn't been set up yet");
        }
        return cube;
    }


    /* No middles, all corners.  This should be extractable to a more generic way of rotating.
    Haven't bothered (yet) to do the math or search for it.
    I figure I'll get it working, and then see if it needs optimization.
    */
    @Override
    public void rotateHeightLeft(int height) {
        if (height > 0) {
            /* Start from top left front*/
            Block[][][] localCube = getCube();
            /* create holder for  left top front and spin holder in place*/
            Block tempBlock = localCube[MIN_WIDTH][height][MIN_DEPTH];
            switchFaceLeft(tempBlock);


            /* left top front receives right top front ; rotate left top front block*/
            localCube[MIN_WIDTH][height][MIN_DEPTH] = localCube[MAX_WIDTH][height][MIN_DEPTH];
            switchFaceLeft(localCube[MIN_WIDTH][height][MIN_DEPTH]);

            /* top right front receives right top back, rotate right top front block */
            localCube[MAX_WIDTH][height][MIN_DEPTH] = localCube[MAX_WIDTH][height][MAX_DEPTH];
            switchFaceLeft(localCube[MAX_WIDTH][height][MIN_DEPTH]);

            /* right top back receives left top back; rotate right top back block */
            localCube[MAX_WIDTH][height][MAX_DEPTH] = localCube[MIN_WIDTH][height][MAX_DEPTH];
            switchFaceLeft(localCube[MAX_WIDTH][height][MAX_DEPTH]);

            /* right top back receives holder; already rotated*/
            localCube[MIN_WIDTH][height][MAX_DEPTH] = tempBlock;
        }
    }


    public void switchFaceLeft(Block block) {
        /* change facing: front becomes left, right becomes front,  back becomes right, left becomes back */
        BlockFace leftBlockFaceHolder = block.getLeft();
        block.setLeft(block.getFront());
        block.setFront(block.getRight());
        block.setRight(block.getBack());
        block.setBack(leftBlockFaceHolder);
    }

    public void switchFaceRight(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        BlockFace frontFaceHolder = block.getFront();
        block.setFront(block.getLeft());
        block.setLeft(block.getBack());
        block.setBack(block.getRight());
        block.setRight(frontFaceHolder);
    }

    @Override
    public void rotateHeightRight(int height) {
        if (height > 0) {
            /* Start from right top front*/
            Block[][][] localCube = getCube();
            /* create holder for right top front and spin holder in place*/
            Block tempBlock = localCube[MAX_WIDTH][height][MIN_DEPTH];
            switchFaceRight(tempBlock);

            /* right top front receives left top front ; rotate right top front block*/
            localCube[MAX_WIDTH][height][MIN_DEPTH] = localCube[MIN_WIDTH][height][MIN_DEPTH];
            switchFaceRight(localCube[MAX_WIDTH][height][MIN_DEPTH]);


            /* left top front receives left top back, rotate left top front block */
            localCube[MIN_WIDTH][height][MIN_DEPTH] = localCube[0][height][MAX_DEPTH];
            switchFaceRight(localCube[MIN_WIDTH][height][MIN_DEPTH]);

            /* left top back receives right top front; rotate left top back block */
            localCube[MIN_WIDTH][height][MAX_DEPTH] = localCube[1][height][MAX_DEPTH];
            switchFaceRight(localCube[MIN_WIDTH][height][MAX_DEPTH]);

            /* right top back receives holder; already rotated*/
            localCube[MAX_WIDTH][height][MAX_DEPTH] = tempBlock;
        }
    }


    public void switchFaceForward(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        BlockFace frontFaceHolder = block.getFront();
        block.setFront(block.getTop());
        block.setTop(block.getBack());
        block.setBack(block.getBottom());
        block.setBottom(frontFaceHolder);
    }

    @Override
    public void rotateWidthForward(int width) {
        if (width > 0) {
            Block[][][] localCube = getCube();
            /* create holder for right top front and spin holder in place*/
            Block tempBlock = localCube[width][MAX_HEIGHT][MIN_DEPTH];
            switchFaceForward(tempBlock);

            /* right top front receives right top back ; rotate right top front block*/
            localCube[width][MAX_HEIGHT][MIN_DEPTH] = localCube[width][MAX_HEIGHT][MAX_DEPTH];
            switchFaceForward(localCube[width][MAX_HEIGHT][MIN_DEPTH]);


            /* right top back receives right bottom back, rotate right top back block */
            localCube[width][MAX_HEIGHT][MAX_DEPTH] = localCube[width][MIN_HEIGHT][MAX_DEPTH];
            switchFaceForward(localCube[width][MAX_HEIGHT][MAX_DEPTH]);

            /* right bottom back receives bottom right front; rotate right bottom  back block */
            localCube[width][MIN_HEIGHT][MAX_DEPTH] = localCube[width][MIN_HEIGHT][MIN_DEPTH];
            switchFaceForward(localCube[width][MIN_HEIGHT][MAX_DEPTH]);

            /* right bottom front receives holder; already rotated*/
            localCube[width][MIN_HEIGHT][MIN_DEPTH] = tempBlock;
        }
    }

    public void switchFaceBackwards(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        BlockFace frontFaceHolder = block.getFront();
        block.setFront(block.getBottom());
        block.setBottom(block.getBack());
        block.setBack(block.getTop());
        block.setTop(frontFaceHolder);
    }

    @Override
    public void rotateWidthBackward(int width) {
        if (width > 0) {
            Block[][][] localCube = getCube();
            /* create holder for right top front and spin holder in place*/
            Block tempBlock = localCube[width][MAX_HEIGHT][MIN_DEPTH];
            switchFaceBackwards(tempBlock);

            /* right top front receives right bottom front ; rotate right top front block*/
            localCube[width][MAX_HEIGHT][MIN_DEPTH] = localCube[width][MIN_HEIGHT][MIN_DEPTH];
            switchFaceBackwards(localCube[width][MAX_HEIGHT][MIN_DEPTH]);


            /* right bottom front receives right bottom back, rotate right bottom front block */
            localCube[width][MIN_HEIGHT][MIN_DEPTH] = localCube[width][MIN_HEIGHT][MAX_DEPTH];
            switchFaceBackwards(localCube[width][MIN_HEIGHT][MIN_DEPTH]);

            /* right bottom back receives right top back; rotate right bottom back block */
            localCube[width][MIN_HEIGHT][MAX_DEPTH] = localCube[width][MAX_HEIGHT][MAX_DEPTH];
            switchFaceBackwards(localCube[width][MIN_HEIGHT][MAX_DEPTH]);

            /* right top back receives holder; already rotated*/
            localCube[width][MAX_HEIGHT][MAX_DEPTH] = tempBlock;
        }
    }


    public void switchFaceClockwise(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        BlockFace topFaceHolder = block.getTop();
        block.setTop(block.getLeft());
        block.setLeft(block.getBottom());
        block.setBottom(block.getRight());
        block.setRight(topFaceHolder);
    }


    public void switchFaceCounterClockwise(Block block) {
        /* change facing: left becomes front,  back becomes left, right becomes back, front becomes right  */
        BlockFace topFaceHolder = block.getTop();
        block.setTop(block.getRight());
        block.setRight(block.getBottom());
        block.setBottom(block.getLeft());
        block.setLeft(topFaceHolder);
    }


    @Override
    public void rotateDepthClockwise(int depth) {
        if (depth > 0) {
            Block[][][] localCube = getCube();
            /* create holder for right top back and spin holder in place*/
            Block tempBlock = localCube[MAX_WIDTH][MAX_HEIGHT][depth];
            switchFaceClockwise(tempBlock);

            /* right top back receives left top back ; rotate right top back block*/
            localCube[MAX_WIDTH][MAX_HEIGHT][depth] = localCube[MIN_WIDTH][MAX_HEIGHT][depth];
            switchFaceClockwise(localCube[MAX_WIDTH][MAX_HEIGHT][depth]);


            /* left top back receives left bottom back, rotate left top back block */
            localCube[MIN_WIDTH][MAX_HEIGHT][depth] = localCube[MIN_WIDTH][MIN_HEIGHT][depth];
            switchFaceClockwise(localCube[MIN_WIDTH][MAX_HEIGHT][depth]);

            /* left bottom back receives right bottom back; rotate left bottom back block */
            localCube[MIN_WIDTH][MIN_HEIGHT][depth] = localCube[MAX_WIDTH][MIN_HEIGHT][depth];
            switchFaceClockwise(localCube[MIN_WIDTH][MIN_HEIGHT][depth]);

            /* right bottom back receives holder; already rotated*/
            localCube[MAX_WIDTH][MIN_HEIGHT][depth] = tempBlock;
        }
    }

    @Override
    public void rotateDepthCounterClockwise(int depth) {
        if (depth > 0) {
            Block[][][] localCube = getCube();
            /* create holder for  right top back and spin holder in place*/
            Block tempBlock = localCube[MAX_WIDTH][MAX_HEIGHT][depth];
            switchFaceCounterClockwise(tempBlock);

            /* right top front receives left top front ; rotate right top front block*/
            localCube[MAX_WIDTH][MAX_HEIGHT][depth] = localCube[MAX_WIDTH][MIN_HEIGHT][depth];
            switchFaceCounterClockwise(localCube[MAX_WIDTH][MAX_HEIGHT][depth]);


            /* top left front receives right top back, rotate left top front block */
            localCube[MAX_WIDTH][MIN_HEIGHT][depth] = localCube[MIN_WIDTH][MIN_HEIGHT][depth];
            switchFaceCounterClockwise(localCube[MAX_WIDTH][MIN_HEIGHT][depth]);

            /* right top back receives right top front; rotate right top back block */
            localCube[MIN_WIDTH][MIN_HEIGHT][depth] = localCube[MIN_WIDTH][MAX_HEIGHT][depth];
            switchFaceCounterClockwise(localCube[MIN_WIDTH][MIN_HEIGHT][depth]);

            /* right top back receives holder; already rotated*/
            localCube[MIN_WIDTH][MAX_HEIGHT][depth] = tempBlock;
        }
    }
}
