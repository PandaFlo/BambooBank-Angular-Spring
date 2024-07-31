package BambooBank_Angular_Spring.BambooBank_Spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BambooBank_Angular_Spring.BambooBank_Spring.exceptions.EmployeeNotFoundException;
import BambooBank_Angular_Spring.BambooBank_Spring.pojos.Employee;
import BambooBank_Angular_Spring.BambooBank_Spring.repositories.EmployeeRepository;


@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) throws EmployeeNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(long id, Employee employee) throws EmployeeNotFoundException {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        employee.setEmployeeId(id);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(long id) throws EmployeeNotFoundException {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }



     
}
