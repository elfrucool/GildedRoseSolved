package gildedrose;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item: items)
            updateItem(item);
    }

    protected void updateItem(Item item) {
        updateItemQuality(item);
        updateItemSellIn(item);
        updateItemWhenOutdated(item);
    }

    protected void updateItemQuality(Item item) {
        if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
            if (item.quality > 0) {
                if (item.name != "Sulfuras, Hand of Ragnaros") {
                    item.quality--;
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality++;
            }

            if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                if (item.sellIn < 11) {
                    if (item.quality < 50) {
                        item.quality++;
                    }
                }

                if (item.sellIn < 6) {
                    if (item.quality < 50) {
                        item.quality++;
                    }
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
                    if (item.quality > 0) {
                        if (item.name != "Sulfuras, Hand of Ragnaros") {
                            item.quality--;
                        }
                    }
                } else {
                    item.quality = 0;
                }
            } else {
                if (item.quality < 50) {
                    item.quality++;
                }
            }
        }
    }
}
