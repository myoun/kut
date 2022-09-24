package app.myoun.kut.dto


data class ProductResponse(
    val id: Long,
    val name: String,
    val price: Int,
    val thumbnail_url: String?,
    val seller: AccountInfo
)

data class AccountInfo(val id: String, val name: String)