package com.github.brelok.order;

import com.github.brelok.additionCar.AdditionCar;
import com.github.brelok.orderAdditionCar.OrderAdditionCar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class OrderDtoDisplay {

    private Long id;
    private String startRent;
    private String endRent;
    private String carName;
    private String userName;
    private List<Integer> orderAdditionCarsQuantity;
    private List<String> orderAdditionCarsName;
    private Double totalPrice;

    public OrderDtoDisplay (Order that){
        this.id = that.getId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.startRent = that.getStartRent().format(formatter);
        this.endRent = that.getEndRent().format(formatter);
        this.carName = that.getCar().toString();
        this.userName = that.getUser().getFirstName().toString();
        this.orderAdditionCarsQuantity = that.getOrderAdditionCars().stream()
                .map(OrderAdditionCar::getAdditionQuantity)
                .collect(Collectors.toList());
        this.orderAdditionCarsName = that.getOrderAdditionCars().stream()
                .map(OrderAdditionCar::getAdditionCar)
                .map(AdditionCar::getName)
                .collect(Collectors.toList());
//        this.additionsList = that.getAdditionsCars().stream()
//                .map(AdditionCar::toString)
//                .collect(Collectors.toList());
        this.totalPrice = that.getTotalPrice();
    }
}
