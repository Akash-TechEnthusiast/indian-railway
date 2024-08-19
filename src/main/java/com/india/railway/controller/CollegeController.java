package com.india.railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.india.railway.model.College;
import com.india.railway.model.Department;
import com.india.railway.service.CollegeService;

import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    @PostMapping
    public College createCollege(@RequestBody College college) {
    	
    	College col=new College();
    	col.setName("JNTU COLLEGE");
    	Department d1=new Department();
    	d1.setTitle("computer science engineering");
    	//d1.setId(4L);
    	Department d2=new Department();
    	d2.setTitle("mechanical  engineering");
    	//d2.setId(5L);
    	Department d3=new Department();
    	d3.setTitle("civil engineering");
    	//d3.setId(6L);
    	col.addDepartment(d1);
    	col.addDepartment(d2);
    	col.addDepartment(d3);
    	col.setActive(null);
    
    
   
    	return collegeService.saveCollege(col);
    }

    @GetMapping("/byName")
    public Page<College> getCollegeByName(@RequestParam(name = "name") String name,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "10") int size) {

                System.out.println("hello test");
    
        return collegeService.getCollegeByName(name,page,size);
    }


    @GetMapping
    public List<College> getAllColleges() {
        return collegeService.getAllColleges();
    }

  
 


    @DeleteMapping("/{id}")
    public void deleteCollege(@PathVariable Long id) {
    	collegeService.deleteCollege(id);
    }


    @PostMapping("/{authorId}/books/{bookId}")
    public College addBookToAuthor(@PathVariable Long collgeId, @PathVariable Long departmentId) {
        return collegeService.addDepartmentToCollege(collgeId, departmentId);
    }

    @DeleteMapping("/{authorId}/books/{bookId}")
    public College removeDepartmentFromCollege(@PathVariable Long collgeId, @PathVariable Long departmentId) {
        return collegeService.removeDepartmentFromCollege(collgeId, departmentId);
    }
}
