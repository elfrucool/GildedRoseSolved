package gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GildedRoseTest {
    @Test
    public void canInstantiate() throws Exception {
        Item[] items = { new Item("foo", 1, 2) };
        GildedRoseFactory factory = new GildedRoseFactory();
        GildedRose gr = factory.createGildedRose(items);
        assertNotNull(gr);
        assertArrayEquals(items, gr.items);
    }
}
