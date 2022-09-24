package app.myoun.kut.service

import app.myoun.kut.dao.ProductRepository
import app.myoun.kut.dao.SellerRepository
import app.myoun.kut.dao.entity.Product
import app.myoun.kut.dao.entity.Seller
import app.myoun.kut.dto.ProductDto
import app.myoun.kut.dto.AccountDto
import app.myoun.kut.dto.ValidateDto
import app.myoun.kut.utils.encryptSHA256
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

    /**
     * @return product
     */
    fun addProduct(seller: Seller, productDto: ProductDto): Product {
        val product = Product().apply {
            name = productDto.name
            price = productDto.price
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

    fun getProducts(pageable: Pageable): Page<Product> {
        return productRepository.findAll(pageable)
    }


}