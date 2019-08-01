package solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SolutionSteps {
    private List<String> steps = new ArrayList<>();
    private int currentHash;
    private Cube cube;

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public int getCurrentHash() {
        return currentHash;
    }

    public void setCurrentHash(int currentHash) {
        this.currentHash = currentHash;
    }

    public Cube getCube() {
        return cube;
    }

    public void setCube(Cube cube) {
        this.cube = cube;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolutionSteps that = (SolutionSteps) o;
        return currentHash == that.currentHash &&
                Objects.equals(steps, that.steps) &&
                Objects.equals(cube, that.cube);
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps, currentHash, cube);
    }

    @Override
    public String toString() {
        return "SolutionSteps{" +
                "steps=" + steps +
                ", currentHash=" + currentHash +
                ", cube=" + cube +
                '}';
    }
}
