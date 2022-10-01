package app.myoun.kut.service

import app.myoun.kut.dao.ProductRepository
import app.myoun.kut.dao.SellerRepository
import app.myoun.kut.dao.entity.Product
import app.myoun.kut.dao.entity.Seller
import app.myoun.kut.dto.*
import app.myoun.kut.utils.encryptSHA256
import app.myoun.kut.utils.toProductResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SellerService(val sellerRepository: SellerRepository, val productRepository: ProductRepository) {

    /**
     * @param id seller id
     * @return seller or null
     */
    fun getSeller(id: String): Seller? {
        return sellerRepository.findByIdOrNull(id)
    }

    /**
     * @return if return is null, it means seller is already exists.
     */
    fun createSeller(accountDto : AccountDto): Seller? {
        if (getSeller(accountDto.id) != null) return null

        val seller = Seller().apply {
            id = accountDto.id
            name = accountDto.name
            password = accountDto.password.encryptSHA256()
        }

        return sellerRepository.save(seller)
    }

    fun getProductByProductId(productId: Long): Product? {
        return productRepository.findByIdOrNull(productId)
    }

    /**
     * @return product
     */
    fun addProduct(seller: Seller, productDto: ProductDto): Product {
        val product = Product().apply {
            name = productDto.name
            price = productDto.price
            description = productDto.description
            content = productDto.content
            thumbnail_url = productDto.thumbnail_url
        }

        val realProduct = productRepository.save(product)

        seller.addProduct(product)
        sellerRepository.save(seller)

        return realProduct
    }

    fun validateSeller(sellerValidateDto: ValidateDto):Boolean? {
        val user = getSeller(sellerValidateDto.id) ?: return null
        return user.password == sellerValidateDto.password.encryptSHA256()
    }

    fun getProducts(pageable: Pageable): Page<ProductResponse> {
        return productRepository.findAll(pageable).map { it.toProductResponse() }
    }
}