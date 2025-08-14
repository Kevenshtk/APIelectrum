package APIelectrum.APIelectrum.module;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 50)
    private String category;
    @Column(nullable = false, length = 250)
    private String description;
    @Column(nullable = false, precision = 10)
    private double price;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String tag;

    public Produto() {
    }

    public Produto(int id, String name, String category, String description, String tag, String image, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.tag = tag;
        this.image = image;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
