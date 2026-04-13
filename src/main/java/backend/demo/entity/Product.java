package backend.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products") // Tên bảng trong MySQL của bạn
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String category;

    
    // Các bạn nhớ tạo Getter/Setter (Nếu có cài thư viện Lombok thì dùng @Data)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}