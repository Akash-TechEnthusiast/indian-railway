package com.india.railway.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.india.railway.model.College;
import com.india.railway.model.Department;
import com.india.railway.repository.CollegeRepository;
import com.india.railway.repository.DepartmentRepository;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class CollegeService {
    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public College saveCollege(College college) {
    	
    	   Iterator<Department> iterator = college.getDepartment().iterator();
           while (iterator.hasNext()) {
        	   Department element = iterator.next();
        	   departmentRepository.save(element);
           }

    	
        return collegeRepository.save(college);
    }

    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    public College getCollegeById(Long id) {
        return collegeRepository.findById(id).orElse(null);
    }

    public void deleteCollege(Long id) {
    	collegeRepository.deleteById(id);
    }

    public College addDepartmentToCollege(Long collgeId, Long departmentId) {
    	College college = collegeRepository.findById(collgeId).orElse(null);
    	Department department = departmentRepository.findById(departmentId).orElse(null);
        if (college != null && department != null) {
        	college.addDepartment(department);
            return collegeRepository.save(college);
        }
        return null;
    }

    public College removeDepartmentFromCollege(Long collgeId, Long departmentId) {
    	College college = collegeRepository.findById(collgeId).orElse(null);
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (college != null && department != null) {
        	college.removeDepartment(department);
            return collegeRepository.save(college);
        }
        return null;
    }
}
