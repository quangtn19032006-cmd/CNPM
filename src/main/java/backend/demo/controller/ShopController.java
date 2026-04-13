package backend.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.demo.entity.Order;
import backend.demo.entity.Product;
import backend.demo.service.ShopService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ShopController {

    @Autowired
    private ShopService shopService;

    // Giả lập Giỏ hàng lưu trên RAM server (US 3)
    private List<Product> mockCart = new ArrayList<>();

    // ==========================================
    // US 1: TÌM KIẾM SẢN PHẨM
    // ==========================================
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> search(@RequestParam(required = false) String q) {
        return ResponseEntity.ok(shopService.searchProducts(q));
    }

    // ==========================================
    // US 2: XEM CHI TIẾT
    // ==========================================
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Product p = shopService.getProductDetail(id);
        if (p == null) return ResponseEntity.status(404).body("Không tìm thấy sản phẩm");
        return ResponseEntity.ok(p);
    }

    // ==========================================
    // US 3: THÊM VÀO GIỎ HÀNG
    // ==========================================
    @PostMapping("/cart/add/{productId}")
    public ResponseEntity<?> addToCart(@PathVariable Long productId) {
        Product p = shopService.getProductDetail(productId);
        if (p != null) {
            mockCart.add(p);
            return ResponseEntity.ok("Đã thêm [" + p.getName() + "] vào giỏ. Giỏ đang có: " + mockCart.size() + " món.");
        }
        return ResponseEntity.status(404).body("Sản phẩm không tồn tại!");
    }

    // ==========================================
    // US 4: THANH TOÁN
    // ==========================================
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestParam String paymentMethod) {
        if (mockCart.isEmpty()) {
            return ResponseEntity.badRequest().body("Giỏ hàng trống!");
        }
        
        // Tính tổng tiền giỏ hàng
        double total = mockCart.stream().mapToDouble(Product::getPrice).sum();
        
        // Tạo đơn hàng
        Order newOrder = shopService.createOrder(total, paymentMethod);
        
        // Xóa giỏ hàng sau khi mua
        mockCart.clear();
        
        return ResponseEntity.ok(newOrder);
    }

    // ==========================================
    // US 5: THEO DÕI ĐƠN HÀNG
    // ==========================================
    @GetMapping("/orders/track/{orderCode}")
    public ResponseEntity<?> trackOrder(@PathVariable String orderCode) {
        Order order = shopService.trackOrder(orderCode);
        if (order == null) return ResponseEntity.status(404).body("Mã đơn hàng không hợp lệ!");
        return ResponseEntity.ok(order);
    }
}