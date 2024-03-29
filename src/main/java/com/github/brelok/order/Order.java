package com.github.brelok.order;

import com.github.brelok.additionCar.AdditionCar;
import com.github.brelok.car.Car;
import com.github.brelok.BaseEntity;
import com.github.brelok.converter.LocalDateAttributeConverter;
import com.github.brelok.orderAdditionCar.OrderAdditionCar;
import com.github.brelok.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalPrice;

    @CreatedDate
    @Convert(converter = LocalDateAttributeConverter.class)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startRent;

    @CreatedDate
    @Convert(converter = LocalDateAttributeConverter.class)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endRent;

    @ManyToOne
    private Car car;

    @ManyToOne
    private User user;

//    @ManyToMany
//    @JoinTable(name = "order_additionCar",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "additionalCar_id"))
//    private Set<AdditionCar> additionsCars = new HashSet<>();

    @OneToMany(mappedBy = "order")
    private Set<OrderAdditionCar> orderAdditionCars;


}
