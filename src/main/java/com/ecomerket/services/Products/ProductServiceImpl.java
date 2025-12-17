package com.ecomerket.services.Products;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecomerket.models.products.Product;
import com.ecomerket.repositories.products.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            Product productDb = productOptional.orElseThrow();

            productDb.setName(product.getName());
            productDb.setDescripcion(product.getDescripcion());
            productDb.setPrecio(product.getPrecio());
            productDb.setStock(product.getStock());
            productDb.setStockCritico(product.getStockCritico());
            productDb.setCategoria(product.getCategoria());
            productDb.setImagen(product.getImagen());

            return Optional.of(repository.save(productDb));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        productOptional.ifPresent(product -> {
            repository.delete(product);
        });
        return productOptional;
    }
}