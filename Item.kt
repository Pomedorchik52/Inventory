package pisipopki

data class Item(
    val id: Int,
    val name: String,
    val description: String,
    val damage: Int,
    val weight: Int,
    val price: Int = 0
)