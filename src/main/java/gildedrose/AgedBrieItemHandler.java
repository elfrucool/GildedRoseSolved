package gildedrose;

public class AgedBrieItemHandler extends ItemHandler {
    @Override
    protected void updateItemQuality(Item item) {
        incrementQuality(item);
    }

    @Override
    protected void updateItemWhenOutdated(Item item) {
        incrementQuality(item);
    }
}
