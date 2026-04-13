package backend.demo.service;

import backend.demo.entity.Order;
import backend.demo.entity.Product;
import backend.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShopService {

    @Autowired
    private ProductRepository productRepository;

    // Sửa lỗi đỏ cho US 1
    public List<Product> searchProducts(String query) {
        if (query == null || query.isEmpty()) {
            return productRepository.findAll();
        }
        return productRepository.findByNameContainingIgnoreCase(query);
    }

    // Sửa lỗi đỏ cho US 2 & 3
    public Product getProductDetail(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Sửa lỗi đỏ cho US 4
    public Order createOrder(double total, String paymentMethod) {
        Order order = new Order();
        order.setTotal(total);
        order.setPaymentMethod(paymentMethod);
        // Tạo mã ngẫu nhiên để không bị lỗi null
        order.setOrderCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setStatus("PENDING");
        return order;
    }

    // Sửa lỗi đỏ cho US 5
    public Order trackOrder(String orderCode) {
        // Tạm thời trả về null hoặc logic tìm kiếm đơn hàng của bạn
        return null; 
    }
}