package backend.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.demo.entity.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> { 
    // Đổi String thành Long ở trên vì id của Order là kiểu Long

    // Thêm hàm này để phục vụ US 5 (Tìm theo mã đơn hàng chứ không phải ID)
    Optional<Order> findByOrderCode(String orderCode);
}
