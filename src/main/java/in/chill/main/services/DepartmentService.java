package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import in.chill.main.entity.Department;

public interface DepartmentService {
    
    public List<Department> getAllDepartments();
    
    public Optional<Department> getDepartmentById(int id);
    
    public Department createDepartment(Department department);
    
    public Department updateDepartment(int id, Department department);
    
    public void deleteDepartment(int id);
}
