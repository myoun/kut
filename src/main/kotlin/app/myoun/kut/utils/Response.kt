package app.myoun.kut.utils

import app.myoun.kut.dao.entity.Product
import app.myoun.kut.dto.AccountInfo
import app.myoun.kut.dto.ProductResponse

data class ValidationResponse(val isValid: Boolean)
data class ErrorMessage(val messages: Collection<String>)

fun Product.toProductResponse(): ProductResponse = ProductResponse(id!!, name, price, thumbnail_url, AccountInfo(seller!!.id, seller!!.name), description, content  )