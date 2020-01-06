/**
 * 
 */
package com.service.app.controller;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.service.app.MessageProperties;
import com.service.app.model.Employee;
import com.service.app.model.EmployeeCompany;
import com.service.app.model.EmployeeSkills;
import com.service.app.service.Service1;

/**
 * https://www.baeldung.com/spring-requestmapping
 * @author vijpande
 *
 *Time Out can be handle by 2 ways 
 *1) Put Retryable Logic in the Service
 * https://dzone.com/articles/spring-retry-way-to-handle-failures
 * 
 * 2) Make Best way to limit time execution in a @RestController using @EnableAsync in Any AsyncConfig java file
 * https://stackoverflow.com/questions/52098798/best-way-to-limit-time-execution-in-a-restcontroller/52098799
 *
 *
 */
 
@RestController
@RequestMapping(path = "/api/employees")
public class EmployeeController {
  
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
    private Service1 service;
    
    @Autowired
    MessageProperties messageProperties;  
    
	@Value("${message.application.name}")
	private  String applicationName ;
	
	
    @GetMapping(path="/", produces = "application/json", consumes = "application/json", headers = "Accept=application/json")
  //  @RolesAllowed("ADMIN")
  //  @PreAuthorize("hasAuthority('ADMIN')")
	@PreAuthorize("hasRole('ADMIN')")
    public List<Employee> getEmployees(@RequestHeader MultiValueMap<String, String> headers) throws Exception {
    	headers.forEach((key, value) -> {
    		logger.info(String.format("Header %s = %s", key, value.stream().collect(Collectors.joining("|"))));
    	});
    	logger.info("Get Employee method is calling ....applicationName :" + applicationName);
    	logger.info("Property getting using Message Properties class :" + messageProperties.getGoodbye());
        return service.getAllEmployees();
    }
    
    //method = { RequestMethod.PUT, RequestMethod.POST }
    @GetMapping(value = {"/{id}/{message}", "/{id}" }, /**path="/{id}/{message}" **/ 
    		produces = { "application/xml", "text/xml", "application/json"})
    public Employee getEmployeesById(@PathVariable(value="id") Integer empId, 
    		@PathVariable(value="message", required=false) String message) {
    	logger.info("Get Employee by id  :" + empId + " Message :" + message);
        return service.getEmployeesById(empId);
    }
    
    @DeleteMapping(path="/", produces = {"text/xml", "application/json"})
    public Boolean deleteEmployeesById(@RequestParam(name="param1", required=true) Integer id, 
    		@RequestParam(name="param2", required=true) String message) {
    	logger.info("Delete Employee by id  :" + id + " Message :" + message);
        return service.deleteEmployeesById(id);
    }
    
    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) throws Exception {
        Integer id = service.getAllEmployees().size() + 1;
        employee.setId(id);
   
        service.addEmployee(employee);
         
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(employee.getId())
                                    .toUri();
         
        return ResponseEntity.created(location).build();
    }
    
    @GetMapping(path="/company", produces = "application/json", consumes = "application/json")
    @RolesAllowed("ROLE_ADMIN")
    public List<EmployeeCompany> getEmployeeCompany() {
    	logger.debug("Get Employee company method is calling :" );
        return service.getEmployeeCompany();
    }
    
    @GetMapping(value = {"/company/{id}"}, /**path="/{id}/{message}" **/ 
    		produces = { "application/xml", "text/xml", "application/json"})
    public EmployeeCompany getEmployeeCompanyById(@PathVariable(value="id") Integer empId) {
    	logger.info("Get Employee by company empIid  :" + empId );
        return service.getEmployeeCompanyById(empId);
    }
    
    @GetMapping(path="/skills", produces = "application/json", consumes = "application/json", headers = "Accept=application/json")
  //  @RolesAllowed("ROLE_USER")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EmployeeSkills> getEmployeeSkills() {
    	logger.debug("Get Employee Skills method is calling :");
        return service.getEmployeeSkills();
    }
    
    
    //To test duplicate url , need to change produces types 
    @GetMapping(value = "foos/duplicate", produces = MediaType.APPLICATION_JSON_VALUE)
	public String duplicate() {	    return "Duplicate";	}
	 
	@GetMapping(value = "foos/duplicate", produces = MediaType.APPLICATION_XML_VALUE)
	public int duplicateEx() {	    return 0;	}
	
	@GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Principal> get(final Principal principal) {
        return ResponseEntity.ok(principal);
    }

}