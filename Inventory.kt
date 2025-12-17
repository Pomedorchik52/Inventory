package pisipopki

class Inventory(val maxWeight: Int = 50) {
    private val items: MutableList<Item> = mutableListOf()

    fun addItem(item: Item): Boolean {
        val currentWeight = getTotalWeight()
        if (currentWeight + item.weight > maxWeight) {
            println("Нельзя добавить '${item.name}': превышен максимальный вес ($maxWeight)")
            return false
        }
        items.add(item)
        println("Предмет '${item.name}' добавлен в инвентарь")
        return true
    }

    fun removeItem(item: Item): Boolean {
        val removed = items.remove(item)
        if (removed) {
            println("Предмет '${item.name}' успешно удалён из инвентаря")
        } else {
            println("Такого предмета нет в инвентаре")
        }
        return removed
    }

    fun printInventory() {
        println("=== Мой инвентарь (вес: ${getTotalWeight()}/$maxWeight) ===")
        if (items.isEmpty()) {
            println("Инвентарь пуст")
            return
        }
        for (item in items) {
            println("- ${item.name} (ID: ${item.id}, урон: ${item.damage}, вес: ${item.weight})")
        }
    }

    fun getTotalWeight(): Int {
        return items.sumOf { it.weight }
    }

    fun findItemByName(name: String): Item? {
        return items.find { it.name.equals(name, ignoreCase = true) }
    }

    fun findItemById(id: Int): Item? {
        return items.find { it.id == id }
    }

    fun printItemDetails(item: Item?) {
        if (item == null) {
            println("Предмет не найден!")
            return
        }
        println("=== Детали предмета ===")
        println("Имя: ${item.name}")
        println("ID: ${item.id}")
        println("Описание: ${item.description}")
        println("Урон: ${item.damage}")
        println("Вес: ${item.weight}")
        println("Цена: ${item.price}")
    }

    // Новый метод: выбросить предмет в сундук
    fun dropItem(item: Item, chest: Chest): Boolean {
        if (removeItem(item)) {
            chest.addItem(item)
            println("Предмет '${item.name}' выброшен в сундук!")
            return true
        }
        return false
    }
}

// Простой "мировой" сундук
class Chest {
    private val items: MutableList<Item> = mutableListOf()

    fun addItem(item: Item) {
        items.add(item)
        // Без сообщения, чтобы не спамить
    }

    fun printChest() {
        println("=== Сундук в мире ===")
        if (items.isEmpty()) {
            println("Сундук пуст")
            return
        }
        for (item in items) {
            println("- ${item.name} (ID: ${item.id})")
        }
    }
}