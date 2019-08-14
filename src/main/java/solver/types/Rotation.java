package solver.types;

import solver.constants.CubeConstants;
import solver.model.CubeRotation;

import java.util.Random;
import java.util.function.Function;

import static solver.constants.CubeConstants.*;

public enum Rotation {

    FORWARD(FORWARD_COMMAND, BACKWARD_COMMAND, new Function<CubeRotation, CubeRotation>() {
        @Override
        public CubeRotation apply(CubeRotation cubeRotation) {
            cubeRotation.getCube().rotateWidthForward(cubeRotation.getScalar(), cubeRotation.getBlockArray(), cubeRotation.getSteps());
            return cubeRotation;
        }
    }),
    BACKWARD(BACKWARD_COMMAND, FORWARD_COMMAND, new Function<CubeRotation, CubeRotation>() {
        @Override
        public CubeRotation apply(CubeRotation cubeRotation) {
            cubeRotation.getCube().rotateWidthBackward(cubeRotation.getScalar(), cubeRotation.getBlockArray(), cubeRotation.getSteps());
            return cubeRotation;
        }
    }),
    LEFT(LEFT_COMMAND, RIGHT_COMMAND, new Function<CubeRotation, CubeRotation>() {
        @Override
        public CubeRotation apply(CubeRotation cubeRotation) {
            cubeRotation.getCube().rotateHeightLeft(cubeRotation.getScalar(), cubeRotation.getBlockArray(), cubeRotation.getSteps());
            return cubeRotation;
        }
    }),
    RIGHT(RIGHT_COMMAND, LEFT_COMMAND, new Function<CubeRotation, CubeRotation>() {
        @Override
        public CubeRotation apply(CubeRotation cubeRotation) {
            cubeRotation.getCube().rotateHeightRight(cubeRotation.getScalar(), cubeRotation.getBlockArray(), cubeRotation.getSteps());
            return cubeRotation;
        }
    }),
    CLOCKWISE(CLOCKWISE_COMMAND, COUNTER_CLOCKWISE_COMMAND, new Function<CubeRotation, CubeRotation>() {
        @Override
        public CubeRotation apply(CubeRotation cubeRotation) {
            cubeRotation.getCube().rotateDepthClockwise(cubeRotation.getScalar(), cubeRotation.getBlockArray(), cubeRotation.getSteps());
            return cubeRotation;
        }
    }),
    COUNTER_CLOCKWISE(COUNTER_CLOCKWISE_COMMAND, CLOCKWISE_COMMAND, new Function<CubeRotation, CubeRotation>() {
        @Override
        public CubeRotation apply(CubeRotation cubeRotation) {
            cubeRotation.getCube().rotateDepthCounterClockwise(cubeRotation.getScalar(), cubeRotation.getBlockArray(), cubeRotation.getSteps());
            return cubeRotation;
        }
    }),
    ;

    public String getReverse() {
        return reverse;
    }

    private void setReverse(String reverse) {
        this.reverse = reverse;
    }

    private String reverse;

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String name;

    public Function<CubeRotation, CubeRotation> getFunction() {
        return function;
    }

    private void setFunction(Function<CubeRotation, CubeRotation> function) {
        this.function = function;
    }

    private Function<CubeRotation, CubeRotation> function;

    Rotation(String name, String reverse, Function<CubeRotation, CubeRotation> function) {
        setName(name);
        setFunction(function);
        setReverse(reverse);
    }

    public String getDisplayString(int scalar) {
        return getName() + " " + scalar;
    }

    private static Rotation[] rotations = null;
    private static Random random = null;
    private static int rotationBound = 0;

    static {
        rotations = Rotation.values();
        random = new Random();
        rotationBound = rotations.length;
    }
}
