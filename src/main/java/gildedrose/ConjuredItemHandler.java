package gildedrose;

public class ConjuredItemHandler extends ItemHandler {
    @Override
    protected void decrementQuality(Item item) {
        super.decrementQuality(item, 2);
    }
}
