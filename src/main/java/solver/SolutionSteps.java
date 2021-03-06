package solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SolutionSteps {
    private List<String> steps = new ArrayList<>();
    private List<String> solutionSteps = new ArrayList<>();
    private String descriptor;


    public List<String> getSolutionSteps() {
        return solutionSteps;
    }

    public void setSolutionSteps(List<String> solutionSteps) {
        this.solutionSteps = solutionSteps;
    }


    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }


    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolutionSteps that = (SolutionSteps) o;
        return Objects.equals(steps, that.steps) &&
                Objects.equals(solutionSteps, that.solutionSteps) &&
                Objects.equals(descriptor, that.descriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps, solutionSteps, descriptor);
    }

    @Override
    public String toString() {
        return "SolutionSteps{" +
                "steps=" + steps +
                ", solutionSteps=" + solutionSteps +
                ", descriptor='" + descriptor + '\'' +
                '}';
    }
}
