package com.example.carmanagementsystemspring.Controller;

import com.example.carmanagementsystemspring.DBConnection.Cars;
import com.example.carmanagementsystemspring.DBConnection.CarsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {
    //using the CarsRepo interface
    private CarsRepo carsRepo;
    @Autowired
    public Controller(CarsRepo carsRepo) {
        this.carsRepo = carsRepo;
    }

    //Show all cars in database
    @Transactional(rollbackFor = Throwable.class)
    @GetMapping()
    public ModelAndView HomePage(){
        ModelAndView modelAndView = new ModelAndView("Home");
        ArrayList<Cars> arr = (ArrayList<Cars>) carsRepo.findAll();
        modelAndView.addObject("data",arr);
        return modelAndView;
    }

    //Add new car to database
    @GetMapping("/AddNewCar")
    public String GoToAddCar(){
        return "AddCar";
    }
    @Transactional(rollbackFor = Throwable.class)
    @PostMapping("/AddCar")
    public String AddCar(Cars cars) {
        try {
            carsRepo.save(cars);
        }catch (Exception e){
            return "redirect:/AddNewCar";
        }
        return "redirect:/";
    }

    //Update car
    @Transactional(rollbackFor = Throwable.class)
    @GetMapping("/Update")
    public ModelAndView GoToUpdateCar(Cars cars){
        ModelAndView modelAndView = new ModelAndView("UpdateCar");
        ArrayList<Cars> arr = (ArrayList<Cars>) carsRepo.findByCarid(cars.getCarid());
        modelAndView.addObject("data",arr);
        return modelAndView;
    }
    @Transactional
    @PostMapping("/CarUpdate")
    public String Update(Cars cars){
        try {
            carsRepo.UpdateCars(cars.getCarname(), cars.getCarmry(), cars.getPrice(), cars.getCarid());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

    //Delete car
    @GetMapping("/DeleteCar")
    public String DeleteCar(Cars cars){
        carsRepo.delete(cars);
        return "redirect:/";
    }

    //Search Car
    @GetMapping("/SearchCar")
    public String GoToSearch(){
        return "SearchCar";
    }
    @Transactional(rollbackFor = Throwable.class)
    @GetMapping("searchcar")
    public ModelAndView SearchCar(Cars cars){
        ModelAndView modelAndView = null;
        try {
            modelAndView = new ModelAndView("SearchCar");
            ArrayList<Cars> arr = (ArrayList<Cars>) carsRepo.findByCarid(cars.getCarid());
            modelAndView.addObject("data",arr);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return modelAndView;
    }
}
