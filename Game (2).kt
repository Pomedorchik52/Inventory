package pisipopki  // Лучше в тот же пакет, чтобы не импортировать

fun main() {
    val player = Player("Oleg")
    val chest = Chest()  // Мировой сундук

    // Некоторые предметы для теста
    val sword = Item(1, "Просто меч", "Меч не самый надёжный", 30, 10, 100)
    val potion = Item(2, "Зелье здоровья", "Хилит на 15 хп", 0, 1, 50)
    val shield = Item(3, "Щит", "Хорошая защита", 15, 15, 150)

    player.inventory.addItem(sword)
    player.inventory.addItem(potion)
    player.inventory.addItem(shield)

    player.inventory.printInventory()

    // === Поиск по имени ===
    println("\n=== Поиск предмета по имени ===")
    print("Введите имя предмета для поиска: ")
    val searchName = readln()
    val foundByName = player.inventory.findItemByName(searchName)

    player.inventory.printItemDetails(foundByName)

    if (foundByName != null) {
        print("Выбросить '${foundByName.name}' в сундук? (да/нет): ")
        val answer = readln().lowercase()
        if (answer == "да") {
            player.inventory.dropItem(foundByName, chest)
        }
    }

    // === Поиск по ID ===
    println("\n=== Поиск предмета по ID ===")
    print("Введите ID предмета для поиска: ")
    val searchId = readln().toIntOrNull() ?: 0
    val foundById = player.inventory.findItemById(searchId)

    player.inventory.printItemDetails(foundById)

    if (foundById != null) {
        print("Выбросить '${foundById.name}' в сундук? (да/нет): ")
        val answer = readln().lowercase()
        if (answer == "да") {
            player.inventory.dropItem(foundById, chest)
        }
    }

    // Вывод инвентаря и сундука в конце
    println("\nПосле операций:")
    player.inventory.printInventory()
    chest.printChest()

    // Добавим пару предметов в сундук для примера (как будто уже лежали)
    chest.addItem(Item(4, "Старый кинжал", "Ржавый, но острый", 20, 5, 30))
    chest.addItem(Item(5, "Кольцо силы", "Увеличивает урон", 10, 1, 200))
    chest.printChest()
}

class Player(val name: String) {
    val inventory = Inventory()
}