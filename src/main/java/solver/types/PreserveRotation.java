package solver.types;

import solver.constants.CubeConstants;

import static solver.constants.CubeConstants.*;

public enum PreserveRotation {
    FORWARD(PRESERVE_FORWARD_COMMAND, PRESERVE_BACKWARD_COMMAND),
    BACKWARD(PRESERVE_BACKWARD_COMMAND, PRESERVE_FORWARD_COMMAND),
    LEFT(CubeConstants.PRESERVE_LEFT_COMMAND, PRESERVE_RIGHT_COMMAND),
    RIGHT(PRESERVE_RIGHT_COMMAND, PRESERVE_LEFT_COMMAND),
    CLOCKWISE(CubeConstants.PRESERVE_CLOCKWISE_COMMAND, PRESERVE_COUNTER_CLOCKWISE_COMMAND),
    COUNTER_CLOCKWISE(CubeConstants.PRESERVE_COUNTER_CLOCKWISE_COMMAND, PRESERVE_CLOCKWISE_COMMAND);


    private String command;
    private String reverse;

    PreserveRotation(String command, String reverse) {
        setCommand(command);
        setReverse(reverse);
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
