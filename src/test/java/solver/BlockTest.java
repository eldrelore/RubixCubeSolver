package solver;

import org.junit.Assert;
import org.junit.Test;
import solver.types.Color;

public class BlockTest {
    @Test
    public void testBlockHash() {
        Block block = getTestBlock();

        Block secondBlock = getTestBlock();
        Assert.assertEquals(block.hashCode(), secondBlock.hashCode());
    }


    private Block getTestBlock() {
        Block block = new Block();
        block.setTop(Color.WHITE.getValue());
        block.setBottom(Color.GREEN.getValue());
        block.setLeft(Color.BLUE.getValue());
        block.setRight(Color.YELLOW.getValue());
        block.setFront(Color.ORANGE.getValue());
        block.setBack(Color.RED.getValue());
        block.setWidth(0);
        block.setHeight(0);
        block.setDepth(0);
        return block;
    }

}
