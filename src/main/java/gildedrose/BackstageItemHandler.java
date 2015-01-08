package gildedrose;

public class BackstageItemHandler extends AgedBrieItemHandler {
    @Override
    protected void updateItemQuality(Item item) {
        super.updateItemQuality(item);
        if (item.sellIn < 11)
            incrementQuality(item);
        if (item.sellIn < 6)
            incrementQuality(item);
    }

    @Override
    protected void updateItemWhenOutdated(Item item) {
        item.quality = 0;
    }
}
