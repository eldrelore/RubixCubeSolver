package solver.types;

public enum BlockType {
    DEFAULT(0),
    CENTER(1),
    EDGE(2),
    CORNER(3),

    ;

    public int getVisibleFaces() {
        return visibleFaces;
    }

    private void setVisibleFaces(int visibleFaces) {
        this.visibleFaces = visibleFaces;
    }

    private int visibleFaces;

    BlockType(int visibleFaces) {
        setVisibleFaces(visibleFaces);
    }

}
