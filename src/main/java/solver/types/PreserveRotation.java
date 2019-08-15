package solver.types;

import solver.constants.CubeConstants;

import java.util.HashMap;
import java.util.Map;

import static solver.constants.CubeConstants.*;

public enum PreserveRotation {
    FORWARD(PRESERVE_FORWARD_COMMAND, PRESERVE_BACKWARD_COMMAND, BACKWARD_COMMAND),
    BACKWARD(PRESERVE_BACKWARD_COMMAND, PRESERVE_FORWARD_COMMAND, FORWARD_COMMAND),
    LEFT(CubeConstants.PRESERVE_LEFT_COMMAND, PRESERVE_RIGHT_COMMAND, RIGHT_COMMAND),
    RIGHT(PRESERVE_RIGHT_COMMAND, PRESERVE_LEFT_COMMAND, LEFT_COMMAND),
    CLOCKWISE(CubeConstants.PRESERVE_CLOCKWISE_COMMAND, PRESERVE_COUNTER_CLOCKWISE_COMMAND, COUNTER_CLOCKWISE_COMMAND),
    COUNTER_CLOCKWISE(CubeConstants.PRESERVE_COUNTER_CLOCKWISE_COMMAND, PRESERVE_CLOCKWISE_COMMAND, CLOCKWISE_COMMAND);


    private static Map<String, PreserveRotation> lookup = new HashMap<>();

    static {
        for (PreserveRotation preserveRotation : values()) {
            lookup.put(preserveRotation.getCommand(), preserveRotation);
        }
    }

    public static final String getInverseByCommand(String command) {
        String inverse = null;
        PreserveRotation rotation = lookup.get(command);
        if (null != rotation) {
            inverse = rotation.getInverse();
        }
        return inverse;
    }

    private String command;
    private String reverse;

    public String getInverse() {
        return inverse;
    }

    private void setInverse(String inverse) {
        this.inverse = inverse;
    }

    private String inverse;

    PreserveRotation(String command, String reverse, String inverse) {
        setCommand(command);
        setReverse(reverse);
        setInverse(inverse);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getReverse() {
        return reverse;
    }

    public void setReverse(String reverse) {
        this.reverse = reverse;
    }
}
