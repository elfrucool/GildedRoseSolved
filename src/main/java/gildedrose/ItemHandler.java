package gildedrose;

public class ItemHandler {
    public void updateItem(Item item) {
        updateItemQuality(item);
        updateItemSellIn(item);
        if (item.sellIn < 0)
            updateItemWhenOutdated(item);
    }

    protected void updateItemQuality(Item item) {
        decrementQuality(item);
    }

    protected void updateItemSellIn(Item item) {
        item.sellIn--;
    }

    protected void updateItemWhenOutdated(Item item) {
        decrementQuality(item);
    }

    protected void decrementQuality(Item item) {
        decrementQuality(item, 1);
    }

    protected void decrementQuality(Item item, int amount) {
        item.quality -= amount;
        if (item.quality < 0)
            item.quality = 0;
    }
}