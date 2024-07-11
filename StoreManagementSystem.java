import java.util.*;

interface ItemDetails {
    void setItem(String itemName, int itemPrice, String description, int stockSize);
}

interface StoreOperations extends ItemDetails {
    void itemSold(int itemIndex, int quantity);
    void updateItem(int itemIndex, int itemPrice, int stockSize);
    void totalSales();
}

class Item implements ItemDetails {
    String itemName, description;
    int itemPrice, stockSize;

    public void setItem(String itemName, int itemPrice, String description, int stockSize) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.description = description;
        this.stockSize = stockSize;
    }
}

class Store extends Item implements StoreOperations {
    Item[] items;
    int totalSales;

    public Store(int numOfItems) {
        items = new Item[numOfItems];
        for (int i = 0; i < numOfItems; i++) {
            items[i] = new Item();
        }
        totalSales = 0;
    }

    public void itemSold(int itemIndex, int quantity) {
        if (itemIndex >= 0 && itemIndex < items.length) {
            Item item = items[itemIndex];
            if (quantity <= item.stockSize) {
                item.stockSize -= quantity;
                int money = quantity * item.itemPrice;
                totalSales += money;
                System.out.println("Total Items sold worth: " + money);
                System.out.println("Items sold successfully. Current Stock Size: " + item.stockSize);
            } else {
                System.out.println("Insufficient stock.");
            }
        } else {
            System.out.println("Invalid item index.");
        }
    }

    public void updateItem(int itemIndex, int itemPrice, int stockSize) {
        if (itemIndex >= 0 && itemIndex < items.length) {
            Item item = items[itemIndex];
            item.itemPrice = itemPrice;
            item.stockSize = stockSize;
            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Invalid item index.");
        }
    }

    public void totalSales() {
        System.out.println("Total sales: " + totalSales);
    }

    public void displayItems() {
        System.out.println("Items in the store:");
        for (int i = 0; i < items.length; i++) {
            if (items[i].itemName != null) {
                System.out.println(i + 1 + ". " + items[i].itemName + " - Price: " + items[i].itemPrice + ", Stock: " + items[i].stockSize);
            }
        }
    }
}

public class StoreManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int numOfItems = sc.nextInt();
        sc.nextLine();

        Store store = new Store(numOfItems);

        for (int i = 0; i < numOfItems; i++) {
            System.out.println("Enter details for item " + (i + 1) + ":");
            System.out.print("Item Name: ");
            String itemName = sc.nextLine();
            System.out.print("Item Price: ");
            int itemPrice = sc.nextInt();
            sc.nextLine();
            System.out.print("Description: ");
            String description = sc.nextLine();
            System.out.print("Stock Size: ");
            int stockSize = sc.nextInt();
            sc.nextLine();

            store.items[i].setItem(itemName, itemPrice, description, stockSize);
        }

        while (true) {
            System.out.println("1. Item Sold");
            System.out.println("2. Update Item");
            System.out.println("3. Display Total Sales");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 4) {
                break;
            }

            switch (choice) {
                case 1:
                    store.displayItems();
                    System.out.print("Enter item index to sell (1 to " + numOfItems + "): ");
                    int itemIndex = sc.nextInt() - 1;
                    System.out.print("Enter quantity sold: ");
                    int quantitySold = sc.nextInt();
                    sc.nextLine();
                    store.itemSold(itemIndex, quantitySold);
                    break;
                case 2:
                    store.displayItems();
                    System.out.print("Enter item index to update (1 to " + numOfItems + "): ");
                    int updateIndex = sc.nextInt() - 1;
                    System.out.print("Enter new price: ");
                    int newPrice = sc.nextInt();
                    System.out.print("Enter new stock size: ");
                    int newStockSize = sc.nextInt();
                    sc.nextLine();
                    store.updateItem(updateIndex, newPrice, newStockSize);
                    break;
                case 3:
                    store.totalSales();
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }

    }
}
