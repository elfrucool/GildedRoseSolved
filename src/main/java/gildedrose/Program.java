package gildedrose;

import static java.lang.String.format;

public class Program {
    public static void main(String... args) {
        System.out.println("OMGHAI!");
        System.out.println();
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        };
        GildedRose app = new GildedRose(items);
        printItems(items, "Initial Conditions");
        for (int i = 0; i < 20; i++) {
            app.updateQuality();
            printItems(items, format("After %d day", i + 1));
        }
    }

    private static void printItems(Item[] items, String title) {
        System.out.println(format("--------------------------[ %s ]-------------------------------", title));
        for (Item item : items) {
            System.out.println(format("%-50.50s | %5d | %5d", item.name, item.sellIn, item.quality));
        }
    }
}
