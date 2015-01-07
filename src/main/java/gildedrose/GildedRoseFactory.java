package gildedrose;

public class GildedRoseFactory {
    public GildedRose createGildedRose(Item[] items) {
        return new GildedRose(items);
    }
}
