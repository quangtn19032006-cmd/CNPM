package backend.demo.controller;

import backend.demo.entity.Product;
import backend.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/search")
    public List<Product> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        
        // Nếu truyền cả name và category
        if (name != null && category != null) {
            return productRepository.findByNameContainingIgnoreCaseAndCategory(name, category);
        } 
        // Nếu chỉ truyền name
        else if (name != null) {
            return productRepository.findByNameContainingIgnoreCase(name);
        } 
        // Nếu chỉ truyền category
        else if (category != null) {
            return productRepository.findByCategory(category);
        }
        
        // Mặc định trả về tất cả sản phẩm
        return productRepository.findAll();
    }
}
