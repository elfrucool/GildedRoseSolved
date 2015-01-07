package gildedrose;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnitParamsRunner.class)
public class GildedRoseTest {
    private String capturedOutput = "";

    @Test
    public void canInstantiate() throws Exception {
        Item[] items = { new Item("foo", 1, 2) };
        GildedRoseFactory factory = new GildedRoseFactory();
        GildedRose gr = factory.createGildedRose(items);
        assertNotNull(gr);
        assertArrayEquals(items, gr.items);
    }

    @Test
    @Parameters(method = "iterationParameters")
    public void canIterate(String label, Item[] items, String expected) throws Exception {
        GildedRose gr = new GildedRoseSpy(items);
        gr.updateQuality();
        assertEquals(expected, capturedOutput);
    }

    protected Object iterationParameters() {
        return $(
                $("no items", new Item[0], ""),
                $("items", new Item[] { new Item("foo", 0, 0), new Item("bar", 0, 0) }, "foobar")
        );
    }

    private class GildedRoseSpy extends GildedRose {
        public GildedRoseSpy(Item[] items) {
            super(items);
        }

        @Override
        protected void updateItem(Item item) {
            capturedOutput += item.name;
        }
    }
}
