package gildedrose;

import java.util.Map;

public class GildedRose {
    private ItemHandler defaultItemHandler;
    Item[] items;
    private Map<String, ItemHandler> specialItemHandlers;

    public GildedRose(Item[] items, ItemHandler defaultItemHandler, Map<String, ItemHandler> specialItemHandlers) {
        this.items = items;
        this.defaultItemHandler = defaultItemHandler;
        this.specialItemHandlers = specialItemHandlers;
    }

    public void updateQuality() {
        for (Item item: items)
            updateItem(item);
    }

    protected void updateItem(Item item) {
        ItemHandler handler = getItemHandler(item);
        handler.updateItem(item);
    }

    protected ItemHandler getItemHandler(Item item) {
        ItemHandler handler = defaultItemHandler;
        if (specialItemHandlers.containsKey(item.name))
            handler = specialItemHandlers.get(item.name);
        return handler;
    }
}
