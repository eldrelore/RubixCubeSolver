package solver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import solver.model.CubeRotation;
import solver.types.*;

import java.io.*;

import java.nio.charset.Charset;

import java.util.*;


public class TwoCube implements Cube {
    public TwoCube() {

    }

    private static final Integer SIZE = 2;
    private static final Integer MAX_HEIGHT = SIZE - 1;
    private static final Integer MAX_WIDTH = SIZE - 1;
    private static final Integer MAX_DEPTH = SIZE - 1;


    @Override
    public Block[][][] initializeCube() {
        Block[][][] cube = new Block[SIZE][SIZE][SIZE];
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
        return cube;
    }


    public Block[][][] initializeCubeFromDescriptor(String descriptor) {
        Block[][][] blockArray = new Block[getSize()][getSize()][getSize()];
        try {
            blockArray = MAPPER.readValue(descriptor, blockArray.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blockArray;
    }


    public Integer getSize() {
        return SIZE;
    }
/* TODO:  implement general (probably abstract inside Cube)
    way to track states, current state,
    and to solve from states (find current state in states, and reverse the steps)*/

    @Override
    public SolutionSteps solve(Map<String, Cube> solutionStates) {
        return null;
    }


    @Override
    public Map<Integer, SolutionSteps> generateStates() {
        /**
         * start with an initialized cube
         *
         * make each possible movement.
         * If the state isn't present in stored states:
         *      store in storedStates
         *      Create a map of storedStates for this number of moves.  Include this in it.
         *
         *
         */
        /* remove all entries from src/test/resources */
        try {
            FileUtils.cleanDirectory(new File("src/test/resources"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Integer, SolutionSteps> storedStates = new HashMap<>();
        int totalAttempts = 0;
        int knownStates = 0;
        Long startTime = System.currentTimeMillis();
        Long movementStartTime = System.currentTimeMillis();
        /* start with initialized cube for first set */
        int numberOfMovements = 0;
        Block[][][] initializeCube = initializeCube();
        String currentFileName = getFileName(numberOfMovements);
        CubeRotation initialCubeRotation = new CubeRotation(0, initializeCube, new ArrayList<String>());
        SolutionSteps currentState = getCurrentState(initialCubeRotation);
        storedStates.put(currentState.getDescriptor().hashCode(), currentState);

        /* write output for this generation */
        appendToFile(currentFileName, currentState);

        numberOfMovements++;
        String priorFileName = getFileName(numberOfMovements - 1);

        Long movementFinishedTime = System.currentTimeMillis();
        boolean priorFileExists = new File(priorFileName).exists();
        /* now read the prior counter for the initial values */
        while (priorFileExists) {
            currentFileName = getFileName(numberOfMovements);

            /* for each previously generated state, make every possible change to it.
            Check to see if they're new states.  If they are, add them.
            If not, don't.*/
            priorFileName = getFileName(numberOfMovements - 1);

            /* read all the strings out of the prior file */
            int currentMovementStates = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(priorFileName))) {
                for (String line; (line = br.readLine()) != null; ) {
                    SolutionSteps priorMovementState = MAPPER.readValue(line, SolutionSteps.class);
                    List<String> priorMovementSteps = priorMovementState.getSteps();
                    Block[][][] priorCubeState = initializeCubeFromDescriptor(priorMovementState.getDescriptor());
                    for (Rotation rotation : Rotation.values()) {
                        for (int i = 0; i < getSize(); i++) {
                            /* create the cube array from the prior know state */
                            Block[][][] rotatedCube = deepClone(priorCubeState);
                            /* apply the rotation to it*/
                            CubeRotation cubeRotation = new CubeRotation(i, rotatedCube, priorMovementSteps, this);
                            /* TODO:  this does not seem to be getting applied */
                            CubeRotation rotationResult = rotation.getFunction().apply(cubeRotation);

                            /* determine if this is a known state*/
                            /* if it's a known state, don't save it */
                            /* if it's not a known state, save it to both maps */
                            String currentDescriptor = getDescriptor(rotationResult.getBlockArray());
                            int currentDescriptorHashCode = currentDescriptor.hashCode();
                            if (null == storedStates.get(currentDescriptorHashCode)) {
                                SolutionSteps updatedState = getCurrentState(rotationResult);
                                storedStates.put(currentDescriptorHashCode, updatedState);

                            /* TODO:  rather than saving to a list and putting in a map, append to a file, and put the file name in the map.
                                don't read the whole file in at once,
                                read each line,
                                generate the new values for it,
                                write all the solutions for the movements applied to it,
                                then proceed to the next line.*/
                                appendToFile(currentFileName, updatedState);
                                currentMovementStates++;
                                knownStates++;
                            }
                            totalAttempts++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            movementFinishedTime = System.currentTimeMillis();
            Long movementDuration = movementFinishedTime - movementStartTime;
            movementStartTime = movementFinishedTime;
            Long totalDuration = movementFinishedTime - startTime;
            System.out.println("Movement: " + numberOfMovements + " states per movement: " + currentMovementStates + " known states: " + knownStates + " duration: " + movementDuration + " total states attempted: " + totalAttempts + " total duration: " + totalDuration);
            numberOfMovements++;
            priorFileExists = new File(currentFileName).exists();
        }
        writeSolutionsToFile(storedStates, "TwoByTwoCubeSoltions_" + System.currentTimeMillis() + ".txt");
        return storedStates;
    }


    private String getFileName(int movement) {
        String testResources = "src/test/resources/";
        String currentFileName = testResources + "solutionStep" + (movement) + ".txt";
        return currentFileName;
    }

    private void appendToFile(String fileName, SolutionSteps currentState) {
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(fileName, true));
            output.append(MAPPER.writeValueAsString(currentState));
            output.append(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            output = null;
        }
    }

    public void writeSolutionsToFile(Map<Integer, SolutionSteps> storedStates, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String solutionString = mapper.writeValueAsString(storedStates);
            File file = new File("src/test/resources/" + filename);
            FileUtils.writeStringToFile(file, solutionString, Charset.forName("utf-8"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Block[][][] deepClone(Block[][][] original) {
        int size = getSize();
        Block[][][] clone = new Block[size][size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    Block originalBlock = original[x][y][z];
                    Block clonedBlock = new Block();
                    FaceColor faceColor = originalBlock.getBack().getColor();
                    FaceDirection faceDirection = originalBlock.getBack().getDirection();

                    clonedBlock.setBack(new BlockFace(faceDirection, faceColor));
                    clone[x][y][z] = clonedBlock;

                }
            }
        }
        return clone;
    }
}
