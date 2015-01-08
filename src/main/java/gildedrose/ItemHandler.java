package gildedrose;

public class ItemHandler {
    public void updateItem(Item item) {
        updateItemQuality(item);
        updateItemSellIn(item);
        updateItemWhenOutdated(item);
    }

    protected void updateItemQuality(Item item) {
        if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
            decrementQuality(item);
        } else {
            incrementQuality(item);

            if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                if (item.sellIn < 11) {
                    incrementQuality(item);
                }

                if (item.sellIn < 6) {
                    incrementQuality(item);
                }
            }
        }
    }

    protected void updateItemSellIn(Item item) {
        if (item.name != "Sulfuras, Hand of Ragnaros") {
            item.sellIn--;
        }
    }

    protected void updateItemWhenOutdated(Item item) {
        if (item.sellIn < 0) {
            if (item.name != "Aged Brie") {
                if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
                    decrementQuality(item);
                } else {
                    item.quality = 0;
                }
            } else {
                incrementQuality(item);
            }
        }
    }

    protected void incrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    protected void decrementQuality(Item item) {
        if (item.quality > 0) {
            if (item.name != "Sulfuras, Hand of Ragnaros") {
                item.quality--;
            }
        }
    }
}