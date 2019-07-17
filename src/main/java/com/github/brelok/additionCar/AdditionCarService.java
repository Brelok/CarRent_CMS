package com.github.brelok.additionCar;

import com.github.brelok.car.Car;
import com.github.brelok.car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdditionCarService {

    private AdditionCarRepository additionCarRepository;
    private CarRepository carRepository;

    @Autowired
    public AdditionCarService(AdditionCarRepository additionsCarRepository, CarRepository carRepository) {
        this.additionCarRepository = additionsCarRepository;
        this.carRepository = carRepository;
    }

    public List findAll() {
        List<AdditionCar> list = additionCarRepository.findAll();

        return list.stream()
                .map(AdditionCarDtoDisplay::new)
                .collect(Collectors.toList());
    }

    public void createAddition(AdditionCarDtoSaveForm additionCarDtoSaveForm) {
        AdditionCar additionCar = new AdditionCar();
        additionCarRepository.save(setValueAdditionCarFromAdditionCarDTOSaveForm(additionCar,additionCarDtoSaveForm));
    }

    public Set<Car> changeAdditionsCarDTOSaveToAdditionsCar(Long[] additions) {
        List<Car> list = new ArrayList<>();

        for (Long along : additions) {
            list.add(carRepository.findOne(along));
        }

        return new HashSet<>(list);
    }

    public void delete(Long id) {
        additionCarRepository.delete(id);
    }

    public void editAddition(AdditionCarDtoSaveForm additionCarDtoSaveForm){
        AdditionCar existing = additionCarRepository.findOne(additionCarDtoSaveForm.getId());

        additionCarRepository.save(setValueAdditionCarFromAdditionCarDTOSaveForm(existing, additionCarDtoSaveForm));
    }

    public AdditionCar setValueAdditionCarFromAdditionCarDTOSaveForm(AdditionCar additionCar,
                                                                     AdditionCarDtoSaveForm additionCarDtoSaveForm) {
        additionCar.setName(additionCarDtoSaveForm.getName());
        additionCar.setDescription(additionCarDtoSaveForm.getDescription());
        additionCar.setQuantity(additionCarDtoSaveForm.getQuantity());

        return additionCar;
    }

    public AdditionCarDtoSaveForm findOneDtoSaveForm (Long id){
        return new AdditionCarDtoSaveForm(additionCarRepository.findOne(id));
    }

}