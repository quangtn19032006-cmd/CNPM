package backend.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.demo.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Tìm kiếm theo tên có chứa từ khóa (tương đương LIKE %...%)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Tìm kiếm theo danh mục chính xác
    List<Product> findByCategory(String category);

    // Kết hợp tìm cả tên và danh mục
    List<Product> findByNameContainingIgnoreCaseAndCategory(String name, String category);
}