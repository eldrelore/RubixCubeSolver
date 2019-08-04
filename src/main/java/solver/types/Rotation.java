package solver.types;

import solver.model.CubeRotation;

import java.util.Random;
import java.util.function.Function;

public enum Rotation {

    FORWARD("forward", new Function<CubeRotation, CubeRotation>() {
        @Override
        public CubeRotation apply(CubeRotation cubeRotation) {
            cubeRotation.getCube().rotateWidthForward(cubeRotation.getScalar(), cubeRotation.getBlockArray(), cubeRotation.getSteps());
            return cubeRotation;
        }
    }),
    BACKWARD("backward", new Function<CubeRotation, CubeRotation>() {
        @Override
        public CubeRotation apply(CubeRotation cubeRotation) {
            cubeRotation.getCube().rotateWidthBackward(cubeRotation.getScalar(), cubeRotation.getBlockArray(), cubeRotation.getSteps());
            return cubeRotation;
        }
    }),
    LEFT("left", new Function<CubeRotation, CubeRotation>() {
        @Override
        public CubeRotation apply(CubeRotation cubeRotation) {
            cubeRotation.getCube().rotateHeightLeft(cubeRotation.getScalar(), cubeRotation.getBlockArray(), cubeRotation.getSteps());
            return cubeRotation;
        }
    }),
    RIGHT("right", new Function<CubeRotation, CubeRotation>() {
        @Override
        public CubeRotation apply(CubeRotation cubeRotation) {
            cubeRotation.getCube().rotateHeightRight(cubeRotation.getScalar(), cubeRotation.getBlockArray(), cubeRotation.getSteps());
            return cubeRotation;
        }
    }),
    CLOCKWISE("clockwise", new Function<CubeRotation, CubeRotation>() {
        @Override
        public CubeRotation apply(CubeRotation cubeRotation) {
            cubeRotation.getCube().rotateDepthClockwise(cubeRotation.getScalar(), cubeRotation.getBlockArray(), cubeRotation.getSteps());
            return cubeRotation;
        }
    }),
    COUNTER_CLOCKWISE("counter-clockwise", new Function<CubeRotation, CubeRotation>() {
        @Override
        public CubeRotation apply(CubeRotation cubeRotation) {
            cubeRotation.getCube().rotateDepthCounterClockwise(cubeRotation.getScalar(), cubeRotation.getBlockArray(), cubeRotation.getSteps());
            return cubeRotation;
        }
    }),
    ;

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

    Rotation(String name, Function<CubeRotation, CubeRotation> function) {
        setName(name);
        setFunction(function);
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
