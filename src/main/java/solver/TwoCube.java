package solver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import solver.model.CubeRotation;
import solver.types.*;

import java.io.*;

import java.nio.charset.Charset;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


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
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.GREEN),
                new BlockFace(FaceColor.ORANGE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.WHITE),
                new BlockFace(FaceColor.DEFAULT));

        Block bottomRightFrontBlock = new Block(1, 0, 0,
                BlockType.CORNER,
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.GREEN),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.RED),
                new BlockFace(FaceColor.WHITE),
                new BlockFace(FaceColor.DEFAULT));

        Block topLeftFrontBlock = new Block(0, 1, 0,
                BlockType.CORNER,
                new BlockFace(FaceColor.BLUE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.ORANGE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.WHITE),
                new BlockFace(FaceColor.DEFAULT));

        Block topRightFrontBlock = new Block(1, 1, 0,
                BlockType.CORNER,
                new BlockFace(FaceColor.BLUE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.RED),
                new BlockFace(FaceColor.WHITE),
                new BlockFace(FaceColor.DEFAULT));


        Block bottomLeftBackBlock = new Block(0, 0, 1,
                BlockType.CORNER,
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.GREEN),
                new BlockFace(FaceColor.ORANGE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.YELLOW));

        Block bottomRightBackBlock = new Block(1, 0, 1,
                BlockType.CORNER,
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.GREEN),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.RED),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.YELLOW));

        Block topLeftBackBlock = new Block(0, 1, 1,
                BlockType.CORNER,
                new BlockFace(FaceColor.BLUE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.ORANGE),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.DEFAULT),
                new BlockFace(FaceColor.YELLOW));

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

    @Override
    public SolutionSteps solve(String solutionFileName, String descriptor) {
        int hashCode = descriptor.hashCode();
        /* TODO:  write a quick find hash in file, and retrieve that entry, rather than reading the entire solutions file in */
        SolutionSteps solutionSteps = readSolutionsFromFile(solutionFileName, descriptor);
        if (null != solutionSteps) {
            /* reverse the steps, both order and commands */
            List<String> reversedSteps = reverseSteps(solutionSteps.getSteps());
            List<String> generationSteps = solutionSteps.getSteps();
            List<String> correctedGenerationSteps = correctGenerationSteps(generationSteps);
            solutionSteps.setSteps(correctedGenerationSteps);
            solutionSteps.setSolutionSteps(reversedSteps);
        }

        return solutionSteps;
    }

    public List<String> correctGenerationSteps(List<String> generationSteps) {
        List<String> correctedGenerationSteps = new ArrayList<>();
        for (String generationStep : generationSteps) {
            String[] splitStep = generationStep.split(" ");
            String command = splitStep[0];
            String dimension = splitStep[1];
            String updatedCommand = correctPreserveCommand(command, dimension);
            correctedGenerationSteps.add(updatedCommand);
        }
        return correctedGenerationSteps;
    }

    public List<String> reverseSteps(List<String> stepsToReverse) {
        List<String> reversedSteps = new ArrayList<>();
        for (String step : stepsToReverse) {
            String[] splitStep = step.split(" ");
            String command = splitStep[0];
            String dimension = splitStep[1];
            command = ReveralRotation.getReversalByCommand(command);
            if ("0".equals(dimension)) {
                String updatedCommand = correctPreserveCommand(command, dimension);
                reversedSteps.add(updatedCommand);
            } else {
                reversedSteps.add(command + " " + dimension);
            }

        }
        Collections.reverse(reversedSteps);
        return reversedSteps;
    }

    public String correctPreserveCommand(String command, String dimension) {
        String result = command + " " + dimension;
        return result;
    }


    @Override
    public Map<Integer, SolutionSteps> generateStates(Integer max) {
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
            FileUtils.cleanDirectory(new File("src/test/resources/working"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Integer, SolutionSteps> storedStates = new HashMap<>();
        AtomicInteger totalAttempts = new AtomicInteger();
        AtomicInteger knownStates = new AtomicInteger();
        Long startTime = System.currentTimeMillis();
        Long movementStartTime = System.currentTimeMillis();
        /* start with initialized cube for first set */
        Integer numberOfMovements = 0;
        Block[][][] initializeCube = initializeCube();
        String currentFileName = getFileName(numberOfMovements);
        CubeRotation initialCubeRotation = new CubeRotation(0, initializeCube, new ArrayList<String>());
        SolutionSteps currentState = getCurrentState(initialCubeRotation);
        storedStates.put(currentState.getDescriptor().hashCode(), currentState);

        /* write output for this generation */
        appendToFile(currentFileName, currentState);

        numberOfMovements++;
        if (null == max || numberOfMovements <= max) {
            String priorFileName = getFileName(numberOfMovements - 1);

            Long movementFinishedTime = System.currentTimeMillis();
            boolean priorFileExists = new File(priorFileName).exists();
            /* now read the prior counter for the initial values */
            while (priorFileExists && ((null == max) || numberOfMovements <= max)) {


                currentFileName = getFileName(numberOfMovements);

            /* for each previously generated state, make every possible change to it.
            Check to see if they're new states.  If they are, add them.
            If not, don't.*/
                priorFileName = getFileName(numberOfMovements - 1);

                /* read all the strings out of the prior file */
                AtomicInteger currentMovementStates = new AtomicInteger();
                try (BufferedReader br = new BufferedReader(new FileReader(priorFileName))) {
                    for (String line; (line = br.readLine()) != null; ) {
                        SolutionSteps priorMovementState = MAPPER.readValue(line, SolutionSteps.class);
                        generateAllCubeRotationsFromState(priorMovementState, currentFileName, storedStates, totalAttempts, currentMovementStates, knownStates);
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
            writeSolutionsToFile(storedStates, "TwoByTwoCubeSolutions_" + System.currentTimeMillis() + ".txt");
        }
        return storedStates;
    }

    public List<SolutionSteps> readSolutionSteps(String fileName) {
        List<SolutionSteps> solutionSteps = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (String line; (line = br.readLine()) != null; ) {
                SolutionSteps priorMovementState = MAPPER.readValue(line, SolutionSteps.class);
                solutionSteps.add(priorMovementState);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return solutionSteps;
    }


    public void generateAllCubeRotationsFromState(SolutionSteps priorMovementState, String currentFileName, Map<Integer, SolutionSteps> storedStates, AtomicInteger totalAttempts, AtomicInteger currentMovementStates, AtomicInteger knownStates) {
        List<String> priorMovementSteps = priorMovementState.getSteps();
        Block[][][] priorCubeState = initializeCubeFromDescriptor(priorMovementState.getDescriptor());
        for (Rotation rotation : Rotation.values()) {
            for (int i = 0; i < getSize(); i++) {
                List<String> priorSteps = new ArrayList<>();
                priorSteps.addAll(priorMovementSteps);
                /* create the cube array from the prior know state */
                /* TODO:  still problems in how the steps are working, and how the block clone is working */
                CubeRotation rotationResult = cloneCubeApplyRotation(priorCubeState, priorSteps, i, rotation);

                /* determine if this is a known state*/
                /* if it's a known state, don't save it */
                /* if it's not a known state, save it to both maps */
                String currentDescriptor = getDescriptor(rotationResult.getBlockArray());
                int currentDescriptorHashCode = currentDescriptor.hashCode();
                if (null == storedStates.get(currentDescriptorHashCode)) {
                    SolutionSteps updatedState = getCurrentState(rotationResult);
                    storedStates.put(currentDescriptorHashCode, updatedState);
                    appendToFile(currentFileName, updatedState);
                    currentMovementStates.incrementAndGet();
                    knownStates.incrementAndGet();
                }
                totalAttempts.incrementAndGet();
            }
        }
    }


    public CubeRotation cloneCubeApplyRotation(Block[][][] priorCubeState, List<String> priorSteps, int i, Rotation rotation) {
        Block[][][] rotatedCube = deepClone(priorCubeState);
        /* apply the rotation to it*/
        CubeRotation cubeRotation = new CubeRotation(i, rotatedCube, priorSteps, this);

        CubeRotation rotationResult = rotation.getFunction().apply(cubeRotation);
        setBlockArrayWidthHeightDepth(rotationResult.getBlockArray());
        return rotationResult;
    }


    private String getFileName(int movement) {
        String testResources = "src/test/resources/working/";
        File directory = new File(testResources);
        if (!directory.exists()) {
            directory.mkdirs();
        }
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
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter("src/test/resources/working/" + filename, true));
            for (Map.Entry<Integer, SolutionSteps> step : storedStates.entrySet()) {

                if (null != step) {
                    String stepValue = MAPPER.writeValueAsString(step);
                    if (null != stepValue) {
                        output.append(stepValue);
                        output.append(System.lineSeparator());
                    }
                }

            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != output) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            output = null;
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
                    cloneBlock(originalBlock, clonedBlock);
                    clonedBlock.setWidth(originalBlock.getWidth());
                    clonedBlock.setHeight(originalBlock.getHeight());
                    clonedBlock.setDepth(originalBlock.getDepth());
                    clone[x][y][z] = clonedBlock;
                }
            }
        }
        return clone;
    }

    public void cloneBlock(Block originalBlock, Block clonedBlock) {
        cloneBlockFace(originalBlock.getTop(), clonedBlock.getTop());
        cloneBlockFace(originalBlock.getBottom(), clonedBlock.getBottom());
        cloneBlockFace(originalBlock.getBack(), clonedBlock.getBack());
        cloneBlockFace(originalBlock.getFront(), clonedBlock.getFront());
        cloneBlockFace(originalBlock.getLeft(), clonedBlock.getLeft());
        cloneBlockFace(originalBlock.getRight(), clonedBlock.getRight());
        clonedBlock.setType(originalBlock.getType());
    }

    public void cloneBlockFace(BlockFace originalBlockFace, BlockFace clonedBlockFace) {
        FaceColor color = originalBlockFace.getColor();
        clonedBlockFace.setColor(color);

    }

    public String readFileContents(String filename) {
        String content = "";
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(filename));
            content = new String(encoded, Charset.forName("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public SolutionSteps readSolutionsFromFile(String filename, String descriptor) {
        AtomicReference<SolutionSteps> steps = new AtomicReference<>(new SolutionSteps());
        TypeReference<Map.Entry<Integer, SolutionSteps>> reference = new TypeReference<Map.Entry<Integer, SolutionSteps>>() {
        };
        File file = new File(filename);
        int descriptorHash = descriptor.hashCode();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Map.Entry<Integer, SolutionSteps> entry = MAPPER.readValue(line, reference);
                if (entry.getKey().equals(descriptorHash)) {
                    steps.set(entry.getValue());
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return steps.get();
    }
}
