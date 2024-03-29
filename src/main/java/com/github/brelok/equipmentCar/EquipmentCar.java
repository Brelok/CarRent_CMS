package com.github.brelok.equipmentCar;

import com.github.brelok.BaseEntity;
import com.github.brelok.car.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EquipmentCar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String description;

    @ManyToMany(mappedBy = "equipmentsCars")
    private Set<Car> cars = new HashSet<>();

    @Override
    public String toString() {
        return "EquipmentCar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cars=" + cars +
                '}';
    }
}
