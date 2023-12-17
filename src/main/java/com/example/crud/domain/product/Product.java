package com.example.crud.domain.product;

import com.example.crud.dtos.RequestProductDto;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Entity(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Integer prince_in_cents;

    private Boolean active;

    public Product(RequestProductDto requestProduct) {
        this.name = requestProduct.name();
        this.prince_in_cents = requestProduct.prince_in_cents();
        this.active = true;
    }
}
