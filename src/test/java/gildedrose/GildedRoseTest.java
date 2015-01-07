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

    @Test
    @Parameters(method = "transformationParameters")
    public void shouldTransformItem(String label, Item item, Item expected) throws Exception {
        GildedRoseFactory factory = new GildedRoseFactory();
        GildedRose gr = factory.createGildedRose(new Item[]{item});
        gr.updateQuality();
        assertEquals(expected.sellIn, item.sellIn);
        assertEquals(expected.quality, item.quality);
    }

    protected Object transformationParameters() {
        return $(
                $("normal item", new Item("foo", 0, 0), new Item("", -1, 0)),
                $("normal item", new Item("foo", 1, 0), new Item("", 0, 0)),
                $("normal item", new Item("foo", 1, 1), new Item("", 0, 0)),
                $("normal item", new Item("foo", 10, 10), new Item("", 9, 9)),
                $("normal item", new Item("foo", 0, 10), new Item("", -1, 8))
        );
    }
}
