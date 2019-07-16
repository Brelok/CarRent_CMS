package com.github.brelok.car;

import com.github.brelok.additionsCar.AdditionsCar;
import com.github.brelok.equipmentsCar.EquipmentsCar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sun.tools.asm.CatchData;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CarDtoSave {

    private Long id;

    @NotNull
    private String model;

    @NotNull
    private int yearOfProduction;

    @NotNull
    private double pricePerDay;

    private double rating;

    @NotNull
    private boolean status;

    @NotNull
    private String classCarName;

    private List<String> additionsCar;
    private List<String> equipmentsCar;
    private Long brandId;


    public CarDtoSave(Car that) {
        this.id = that.getId();
        this.model = that.getModel();
        this.yearOfProduction = that.getYearOfProduction();
        this.pricePerDay = that.getPricePerDay();
        this.rating = that.getRating();
        this.status = that.isStatus();
        this.classCarName = that.getClassCar().toString();
        this.additionsCar = that.getAdditionsCars().stream()
                .map(AdditionsCar::getName).collect(Collectors.toList());
        this.equipmentsCar = that.getEquipmentsCars().stream()
                .map(EquipmentsCar::getName).collect(Collectors.toList());
        this.brandId = that.getBrandCar().getId();

    }

}