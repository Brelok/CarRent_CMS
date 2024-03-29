package com.github.brelok.additionCar;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdditionCarDtoSaveForm {

    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Double pricePerDay;

    public AdditionCarDtoSaveForm(AdditionCar that){
        this.id = that.getId();
        this.name = that.getName();
        this.description = that.getDescription();
        this.quantity = that.getTotalQuantity();
        this.pricePerDay = that.getPricePerDay();
    }
}
