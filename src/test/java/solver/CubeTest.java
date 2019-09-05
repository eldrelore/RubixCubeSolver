package solver;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class CubeTest {

    @Test
    public void testCubeHashCode() {
        Block[][][] firstCube = new TwoCube().initializeCube();
        Block[][][] secondCube = new TwoCube().initializeCube();

        Cube cube = new TwoCube();
        Assert.assertEquals(cube.hashCode(firstCube), cube.hashCode(secondCube));
    }


    @Test
    public void testSerDeser() throws IOException, ClassNotFoundException {
        Cube cube = new TwoCube();
        Block[][][] preSerialize = cube.initializeCube();
        Block[][][] postDeserialize = null;

        String descriptor = cube.getDescriptor(preSerialize);

        String serializationFileName = "src/test/resources/test.dat";

        FileOutputStream fos = new FileOutputStream(serializationFileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(preSerialize);

        FileInputStream fis = new FileInputStream(serializationFileName);
        ObjectInputStream iis = new ObjectInputStream(fis);
        postDeserialize = (Block[][][]) iis.readObject();
        String postDeserDescriptor = cube.getDescriptor(postDeserialize);
        Assert.assertEquals(descriptor, postDeserDescriptor);


    }
}
