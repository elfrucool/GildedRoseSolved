package gildedrose;

import java.util.HashMap;
import java.util.Map;

public class GildedRoseFactory {
    public GildedRose createGildedRose(Item[] items) {
        Map<String, ItemHandler> specialItemHandlers = new HashMap<String, ItemHandler>() {{
            put("Sulfuras, Hand of Ragnaros", new SulfurasItemHandler());
            put("Aged Brie", new AgedBrieItemHandler());
        }};
        return new GildedRose(items, new ItemHandler(), specialItemHandlers);
    }
}
