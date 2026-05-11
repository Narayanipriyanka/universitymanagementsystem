package com.example.hostelservice.controller;

import com.example.hostelservice.dto.MessDTO;
import com.example.hostelservice.entity.MealType;
import com.example.hostelservice.entity.MessType;
import com.example.hostelservice.service.HostelService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hostel/mess")
public class MessController {
    @Autowired
    private HostelService hostelService;
  @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
  @Operation(summary = "add mess to hostel",description = "Only for admin ,he can add a mess")
  public String addMess(@RequestBody MessDTO dto){
      return hostelService.addMess(dto);
  }
    @PostMapping("/meal")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "add mess to hostel",description = "Only for admin ,he can add a mess")
    public String addMealPlan(@RequestParam Long messId,@RequestParam MealType type){
        return hostelService.addMealPLan(messId,type);
    }
@PutMapping("/selectMess")
@PreAuthorize("hasRole('STUDENT')")
@Operation(summary = "add mess to hostel",description = "Only for admin ,he can add a mess")
public String selectMess(@RequestParam UUID studentId,@RequestBody MessDTO dto){
    return hostelService.selectAMess(studentId,dto);
}
    @GetMapping
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "get all mess details in hostel",description = "only student cna access this ,gets info about mess")
    public List<MessDTO> getMess(){
        return hostelService.getAllMessDetails();
    }
    @PutMapping("/selectMeal")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "select meal plan hostel",description = "only student cna access this ,selects a meal plan")
    public String selectMealPlan(UUID studentId,Long messId, MealType type){
        return hostelService.selectMealPlan(studentId,messId,type);
    }
    @PutMapping("/dropMeal")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "drop a meal plan hostel",description = "only student cna access this ,drops a meal plan")
    public String dropMealPlan(UUID studentId,Long mealId){
        return hostelService.dropFromMealPlan(studentId,mealId);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get no of students in veg or non veg plans",description = "only admin cna access this ,gets info about nof students eatuing veg ro non veg in mess")
    public String getNonVegOrVegSTudents(MessType type){
        return hostelService.noOFVegOrNonvegStudents(type);
    }

}
