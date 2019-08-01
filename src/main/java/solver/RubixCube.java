package solver;


public class RubixCube {
    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    private int size;


    public RubixCube(int size) {
        setSize(size);
    }


    public Cube generateCube() {
        Cube cube = null;
        switch (size) {
            case 1:
                cube = new OneCube();
                break;
            case 2:
                cube = new TwoCube();
                break;
            case 3:
                cube = new ThreeCube();
                break;
            default:
                throw new RuntimeException("size of " + size + " not implemented yet.");
        }
        return cube;
    }
}
