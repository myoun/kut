package app.myoun.kut.service

import app.myoun.kut.dao.ProductRepository
import app.myoun.kut.dao.SellerRepository
import app.myoun.kut.dao.entity.Product
import app.myoun.kut.dao.entity.Seller
import app.myoun.kut.dto.ProductDto
import app.myoun.kut.dto.UserDto
import app.myoun.kut.utils.encryptSHA256
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
    fun createSeller(sellerDto : UserDto): Seller? {
        if (getSeller(sellerDto.id) != null) return null

        val seller = Seller().apply {
            id = sellerDto.id
            name = sellerDto.name
            password = sellerDto.password.encryptSHA256()
        }

        return sellerRepository.save(seller)
    }

    /**
     * @param id Seller Id
     * @return product
     */
    fun addProduct(seller: Seller, productDto: ProductDto): Product {
        val product = Product().apply {
            name = productDto.name
            price = productDto.price
            createdBy = seller.id
        }

        return productRepository.save(product)
    }

    fun getProducts(seller: Seller): List<Product> {
        return productRepository.findAllByCreatedBy(Pageable.ofSize(20), seller.id).toList()
    }


}