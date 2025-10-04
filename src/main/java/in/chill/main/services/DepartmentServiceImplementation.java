package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.chill.main.entity.Department;
import in.chill.main.repository.DepartmentRepository;

@Service
public class DepartmentServiceImplementation implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(int id, Department department) {
        Department existingDepartment = departmentRepository.findById(id).orElse(null);
        if (existingDepartment != null) {
            existingDepartment.setName(department.getName());
            return departmentRepository.save(existingDepartment);
        } else {
            throw new RuntimeException("Department not found with id: " + id);
        }
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }
}
