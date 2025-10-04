package in.chill.main.config;

import in.chill.main.entity.Admin;
import in.chill.main.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private AdminService adminService;
    
    @Override
    public void run(String... args) throws Exception {
        // Check if any admins exist
        if (adminService.getAllAdmins().isEmpty()) {
            System.out.println("No admin users found. Creating default admin users...");
            
            // Create default admin users
            try {
                adminService.createAdmin(new Admin("Sarah Johnson", "sarah.johnson@admin.org", "admin123"));
                adminService.createAdmin(new Admin("Mike Williams", "mike.williams@admin.org", "admin123"));
                adminService.createAdmin(new Admin("Emma Davis", "emma.davis@admin.org", "admin123"));
                adminService.createAdmin(new Admin("John Smith", "john.smith@admin.org", "admin123"));
                adminService.createAdmin(new Admin("Lisa Brown", "lisa.brown@admin.org", "admin123"));
                adminService.createAdmin(new Admin("David Wilson", "david.wilson@admin.org", "admin123"));
                adminService.createAdmin(new Admin("Jennifer Garcia", "jennifer.garcia@admin.org", "admin123"));
                adminService.createAdmin(new Admin("Robert Martinez", "robert.martinez@admin.org", "admin123"));
                adminService.createAdmin(new Admin("Maria Rodriguez", "maria.rodriguez@admin.org", "admin123"));
                adminService.createAdmin(new Admin("James Anderson", "james.anderson@admin.org", "admin123"));
                
                System.out.println("Successfully created 10 default admin users.");
                System.out.println("Login credentials: Use any email ending with @admin.org and password 'admin123'");
                
            } catch (Exception e) {
                System.err.println("Error creating admin users: " + e.getMessage());
            }
        } else {
            System.out.println("Admin users already exist. Skipping initialization.");
        }
    }
}