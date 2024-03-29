package com.github.brelok.car;

import com.github.brelok.brandCar.BrandCarRepository;
import com.github.brelok.classCar.ClassCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarService {


    private CarRepository carRepository;
    private BrandCarRepository brandCarRepository;
    private ClassCarRepository classCarRepository;

    @Autowired
    public CarService(CarRepository carRepository, BrandCarRepository brandCarRepository, ClassCarRepository classCarRepository) {
        this.carRepository = carRepository;
        this.brandCarRepository = brandCarRepository;
        this.classCarRepository = classCarRepository;
    }

    public List findAll() {
        List<Car> carList = carRepository.findAll();

        return carList.stream()
                .map(CarDtoDisplay::new)
                .collect(Collectors.toList());
    }

    public void delete(Car car) {
        carRepository.delete(car);
    }

    public Car findOne(Long id) {
        return carRepository.getOne(id);
    }

    public CarDtoSave findOneDto(Long id) {
        return new CarDtoSave(carRepository.getOne(id));
    }

    public Long countAvailableCar() {
        return carRepository.countByStatus(true);
    }

     public Long countRentCar() {
        return carRepository.countByStatus(false);
    }

    public Long countAllCars(){
        return carRepository.findAll().stream().count();
    }

    public double avarageRating(){
        double sumRatings = 0;

        for (Car car : carRepository.findAll()){
            sumRatings += car.getRating();
        }

        return sumRatings / countAllCars();

    }

    public Long AvarageYear (){
        int sumYears = 0;

        for(Car car : carRepository.findAll()){
            sumYears += car.getYearOfProduction();
        }
        return sumYears / countAllCars();
    }

    public double avaragePrice(){
        double avaragePrice = 0;

        for (Car car : carRepository.findAll()){
            avaragePrice += car.getPricePerDay();
        }
        return avaragePrice / countAllCars();
    }


    public void createCar(CarDtoSave carDtoSave) {
        Car car = new Car();

        carRepository.save(setValuesCarFromDtoValues(car, carDtoSave));
    }

    public void editCar(CarDtoSave carDtoSave) {
        Car existing = carRepository.getOne(carDtoSave.getId());

        carRepository.save(setValuesCarFromDtoValues(existing, carDtoSave));
    }

    private Car setValuesCarFromDtoValues(Car car, CarDtoSave carDtoSave) {

        car.setModel(carDtoSave.getModel());
        car.setPricePerDay(carDtoSave.getPricePerDay());
        car.setRating(carDtoSave.getRating());
        car.setStatus(carDtoSave.isStatus());
        car.setYearOfProduction(carDtoSave.getYearOfProduction());
        car.setBrandCar(brandCarRepository.getOne(carDtoSave.getBrandId()));
        car.setClassCar(classCarRepository.getOne(carDtoSave.getClassCarId()));
        return car;
    }

    public Long countAllCarsOfThisBrand(long id){
        return carRepository.findAll().stream()
                .map(CarDtoSave::new)
                .filter(carDtoSave -> carDtoSave.getBrandId() == id).count();
    }

    public Set findAllBrandsCar (){
        return carRepository.findAll().stream()
                .map(CarDtoDisplay::new)
                .map(CarDtoDisplay::getBrandName)
                .collect(Collectors.toSet());
    }

    public List findAllCarByBrandName(String name){
        return carRepository.findAll().stream()
                .map(CarDtoDisplay::new)
                .filter(carDtoDisplay -> carDtoDisplay.getBrandName().equals(name))
                .collect(Collectors.toList());
    }

    public List findAllCarByClassName(String name){
        return carRepository.findAll().stream()
                .map(CarDtoDisplay::new)
                .filter(carDtoDisplay -> carDtoDisplay.getClassCarName().equals(name))
                .collect(Collectors.toList());
    }

}
