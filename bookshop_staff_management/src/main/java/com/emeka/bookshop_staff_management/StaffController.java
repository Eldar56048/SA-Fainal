package com.emeka.bookshop_staff_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/staff/{id}")
    public Staff getStaffName(@PathVariable int id) {
        Staff staff = staffRepository.getAllById(id);
        return staff;
    }

    @PostMapping("/staff")
    public String createStaff(@RequestBody Staff staff) {
        staffRepository.save(staff);
        return "Staff created successfully";
    }

    @DeleteMapping("/staff/{id}")
    public String deleteStaff(@PathVariable int id){
        staffRepository.deleteById(id);
        return "Staff "+id+" Successfully deleted";
    }

    @PutMapping("/staff/{id}")
    public String updateStaff(@PathVariable int id,@RequestBody Staff newStaff){
        Staff staff = staffRepository.getAllById(id);
        staff.setFirstName(newStaff.getFirstName());
        staff.setSurname(newStaff.getSurname());
        staffRepository.save(staff);
        return "Staff "+id+" Successfully updated";
    }

}
