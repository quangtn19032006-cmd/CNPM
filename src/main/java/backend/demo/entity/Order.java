package backend.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderCode;
    private Double total;
    private String paymentMethod;
    private String status;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderCode() {
        return orderCode;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public String getStatus() {
        return status;
    }
    // BẮT BUỘC phải có Getter/Setter cho các trường trên
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public void setOrderCode(String orderCode) { this.orderCode = orderCode; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setStatus(String status) { this.status = status; }
}