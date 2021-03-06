package solver.types;

import java.util.HashMap;
import java.util.Map;

import static solver.constants.CubeConstants.*;

public enum ReveralRotation {
    FORWARD(Rotation.FORWARD.getName(), Rotation.FORWARD.getReverse()),
    BACKWARD(Rotation.BACKWARD.getName(), Rotation.BACKWARD.getReverse()),
    LEFT(Rotation.LEFT.getName(), Rotation.LEFT.getReverse()),
    RIGHT(Rotation.RIGHT.getName(), Rotation.RIGHT.getReverse()),
    CLOCKWISE(Rotation.CLOCKWISE.getName(), Rotation.CLOCKWISE.getReverse()),
    COUNTER_CLOCKWISE(Rotation.COUNTER_CLOCKWISE.getName(), Rotation.COUNTER_CLOCKWISE.getReverse()),
    ;

    private static Map<String, ReveralRotation> lookup = new HashMap<>();

    static {
        for (ReveralRotation rotation : ReveralRotation.values()) {
            lookup.put(rotation.getCommand(), rotation);
        }
    }

    public static final String getReversalByCommand(String command) {
        ReveralRotation reveralRotation = lookup.get(command);
        String reveral = command;
        if (null != reveralRotation) {
            reveral = reveralRotation.getReverse();
        }
        return reveral;
    }

    public String getCommand() {
        return command;
    }

    private void setCommand(String command) {
        this.command = command;
    }

    public String getReverse() {
        return reverse;
    }

    private void setReverse(String reverse) {
        this.reverse = reverse;
    }

    private String command;
    private String reverse;

    ReveralRotation(String command, String reverse) {
        setCommand(command);
        setReverse(reverse);
    }

}
