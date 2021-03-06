package solver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.FileUtils;
import org.nustaq.serialization.FSTConfiguration;
import solver.model.CubeRotation;
import solver.types.*;

import java.io.*;

import java.nio.charset.Charset;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class TwoCube implements Cube {
    public TwoCube() {

    }

    private static final Integer SIZE = 2;

    @Override
    public Block[][][] initializeCube() {
        Block[][][] cube = new Block[SIZE][SIZE][SIZE];
        List<Block> blocks = new ArrayList<>();
        Block bottomLeftFrontBlock = new Block(0, 0, 0,
                new int[]{Color.DEFAULT.getValue(),
                        Color.GREEN.getValue(),
                        Color.ORANGE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.WHITE.getValue(),
                        Color.DEFAULT.getValue()});

        Block bottomRightFrontBlock = new Block(1, 0, 0,
                new int[]{Color.DEFAULT.getValue(),
                        Color.GREEN.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.RED.getValue(),
                        Color.WHITE.getValue(),
                        Color.DEFAULT.getValue()});

        Block topLeftFrontBlock = new Block(0, 1, 0,
                new int[]{Color.BLUE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.ORANGE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.WHITE.getValue(),
                        Color.DEFAULT.getValue()});

        Block topRightFrontBlock = new Block(1, 1, 0,
                new int[]{Color.BLUE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.RED.getValue(),
                        Color.WHITE.getValue(),
                        Color.DEFAULT.getValue()});


        Block bottomLeftBackBlock = new Block(0, 0, 1,
                new int[]{Color.DEFAULT.getValue(),
                        Color.GREEN.getValue(),
                        Color.ORANGE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.YELLOW.getValue()});

        Block bottomRightBackBlock = new Block(1, 0, 1,
                new int[]{Color.DEFAULT.getValue(),
                        Color.GREEN.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.RED.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.YELLOW.getValue()});

        Block topLeftBackBlock = new Block(0, 1, 1,
                new int[]{Color.BLUE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.ORANGE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.YELLOW.getValue()});

        Block topRightBackBlock = new Block(1, 1, 1,
                new int[]{Color.BLUE.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.RED.getValue(),
                        Color.DEFAULT.getValue(),
                        Color.YELLOW.getValue()});

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

    private void cleanupOrCreateDirectory(String directory) {
        try {
            File workingDirectory = new File(directory);
            if (workingDirectory.exists()) {
                FileUtils.cleanDirectory(workingDirectory);
            } else {
                workingDirectory.mkdirs();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        cleanupOrCreateDirectory(WORKING_DIRECTORY);
        /* remove all entries from src/test/resources */
        String solutionFileName = "TwoByTwoCubeSolutions.txt";
        File file = new File(RESOURCE_DIRECTORY + "/" + solutionFileName);
        if (file.exists()) {
            file.delete();
        }
        /* TODO, move this down to the loop.  Have a separate map for checking whether each solution has been found. */
        /* clear out the solutions file first.  Then append to it inside loop.*/
        Map<Integer, Boolean> alreadyFoundStates = new HashMap<>();
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
        Map<Integer, SolutionSteps> initialState = new HashMap<>();
        Integer initialHashCode = currentState.getDescriptor().hashCode();
        initialState.put(currentState.getDescriptor().hashCode(), currentState);
        alreadyFoundStates.put(initialHashCode, true);
        appendSolutionsToFile(initialState, solutionFileName);

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
                AtomicInteger currentMovementStates = new AtomicInteger();
                Map<Integer, SolutionSteps> storedStates = generateIterationStates(numberOfMovements, alreadyFoundStates, totalAttempts, currentMovementStates, knownStates);
                movementFinishedTime = System.currentTimeMillis();
                Long movementDuration = movementFinishedTime - movementStartTime;
                movementStartTime = movementFinishedTime;
                Double totalDuration = ((movementFinishedTime * 1.0) - startTime) / 1000;
                String duationSeconds = String.format("%.4f", totalDuration);
                System.out.println("Movement: " + numberOfMovements + " states per movement: " + currentMovementStates + " known states: " + knownStates + " duration: " + movementDuration + " total states attempted: " + totalAttempts + " total duration: " + duationSeconds + " seconds.");
                numberOfMovements++;

                priorFileExists = new File(currentFileName).exists();
                appendSolutionsToFile(storedStates, solutionFileName);
            }
        }
        alreadyFoundStates.clear();
        return null;
    }

    public Map<Integer, SolutionSteps> generateIterationStates(Integer numberOfMovements, Map<Integer, Boolean> alreadyFoundStates, AtomicInteger totalAttempts, AtomicInteger currentMovementStates, AtomicInteger knownStates) {
        Map<Integer, SolutionSteps> storedStates = new HashMap<>();
        String currentFileName = getFileName(numberOfMovements);
            /* for each previously generated state, make every possible change to it.
            Check to see if they're new states.  If they are, add them.
            If not, don't.*/
        String priorFileName = getFileName(numberOfMovements - 1);
        /* read all the strings out of the prior file */

        try (BufferedReader br = new BufferedReader(new FileReader(priorFileName))) {
            for (String line; (line = br.readLine()) != null; ) {
                SolutionSteps priorMovementState = MAPPER.readValue(line, SolutionSteps.class);
                generateAllCubeRotationsFromState(priorMovementState, currentFileName, storedStates, alreadyFoundStates, totalAttempts, currentMovementStates, knownStates);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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


    public void generateAllCubeRotationsFromState(SolutionSteps priorMovementState, String currentFileName, Map<Integer, SolutionSteps> storedStates, Map<Integer, Boolean> foundStates, AtomicInteger totalAttempts, AtomicInteger currentMovementStates, AtomicInteger knownStates) {
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
                if (null == foundStates.get(currentDescriptorHashCode)) {
                    SolutionSteps updatedState = getCurrentState(rotationResult);
                    foundStates.put(currentDescriptorHashCode, true);
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
        String currentFileName = WORKING_DIRECTORY + "/solutionStep" + (movement) + ".txt";
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
                if (null != output) {
                    output.flush();
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            output = null;
        }
    }

    public void appendSolutionsToFile(Map<Integer, SolutionSteps> storedStates, String filename) {
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(RESOURCE_DIRECTORY + "/" + filename, true));
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
                    output.flush();
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
        clonedBlock.setTop(originalBlock.getTop());
        clonedBlock.setBottom(originalBlock.getBottom());
        clonedBlock.setLeft(originalBlock.getLeft());
        clonedBlock.setRight(originalBlock.getRight());
        clonedBlock.setFront(originalBlock.getFront());
        clonedBlock.setBack(originalBlock.getBack());
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


//    static final FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
//
//    static {
//        conf.registerClass(Color.class, Block.class, BlockFace.class);
//    }
//
//    @Override
//    public String getDescriptor(Block[][][] blocks) {
//        String descriptor = new String(conf.asByteArray(blocks));
//        return descriptor;
//    }
}
