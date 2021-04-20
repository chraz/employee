package com.employee.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//webbapp http-anrop 127:0:0:1:8080/Employee
@RestController
public class EmployeeController {

    @Autowired
	private EmployeeRepository employeeRepository;

    //Skapa en controllermetod (endpoint för '/')



    @GetMapping(path="/")
    @CrossOrigin()
    String empty()
    {
        return "jepp";
    }

    @GetMapping(path="/employee")
    @CrossOrigin()
    List<Employee> getAll(){
        var l = new ArrayList<Employee>();
        for(Employee p : employeeRepository.findAll() ){
            l.add(p);
        }
        return l;       
    }


    @GetMapping(path ="/employee/{id}")
    @CrossOrigin()
    Employee getSingleemployee(@PathVariable Integer id){
        return employeeRepository.findById(id).get();
    }

    /**
     * Update
     * @param id
     * @param updatedEmployee
     * @return
     */
    @PutMapping(path ="/employee/{id}", consumes="application/json", produces = "application/json")
    @CrossOrigin()
    Employee update(@PathVariable Integer id, @RequestBody Employee updatedEmployee){
        Employee dbemployee = employeeRepository.findById(id).get();
        dbemployee.setName(updatedEmployee.getName().toString());
        dbemployee.setCar(updatedEmployee.getCar());
        dbemployee.setCompamny(updatedEmployee.getCompany());
        employeeRepository.save(dbemployee);

        return dbemployee;
    }

    /**
     * ADD
     * @param p
     * @return
     */
    @PostMapping(path="/employee", consumes="application/json", produces = "application/json")
    @CrossOrigin()
    ResponseEntity<Object> add(@RequestBody Employee p){
        employeeRepository.save(p);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(p.getId())
        .toUri();  
        return ResponseEntity.created(location).build();  
    }

    /**
     * Delete
     * @return 
     */
    @DeleteMapping(path ="/employee/{id}")
    @CrossOrigin()
    String delete(@PathVariable Integer id){
        employeeRepository.deleteById(id);
        return "DELETED updatedEmployee id: "+id;

    }
  
}
