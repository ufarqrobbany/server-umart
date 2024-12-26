package com.umart.backend.config;

import com.umart.backend.model.admin.Admin;
import com.umart.backend.model.admin.AdminRole;
import com.umart.backend.repository.admin.AdminRepository;
import com.umart.backend.repository.admin.AdminRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AdminRoleRepository adminRoleRepository;
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (adminRoleRepository.count() == 0) {
            List<AdminRole> roles = List.of(
                    new AdminRole(null, "SUPER_ADMIN", "Memiliki akses penuh untuk mengelola seluruh sistem."),
                    new AdminRole(null, "INSTITUTION_ADMIN", "Mengelola institusi yang terdaftar."),
                    new AdminRole(null, "ORGANIZATION_ADMIN", "Mengelola organisasi yang terdaftar."),
                    new AdminRole(null, "USER_ADMIN", "Mengelola user yang terdaftar."),
                    new AdminRole(null, "SELLER_ADMIN", "Mengelola seller yang terdaftar."),
                    new AdminRole(null, "COURIER_ADMIN", "Mengelola courier yang terdaftar."),
                    new AdminRole(null, "CONTENT_ADMIN", "Mengelola konten aplikasi, termasuk promosi dan pemberitahuan."),
                    new AdminRole(null, "SUPPORT_ADMIN", "Menangani masalah teknis dan keluhan pengguna."),
                    new AdminRole(null, "FINANCE_ADMIN", "Mengelola laporan keuangan, transaksi pembayaran, dan anggaran terkait keuangan di platform."),
                    new AdminRole(null, "REPORTING_ADMIN", "Fokus pada pengelolaan laporan dan analitik data.")
            );

            for (AdminRole role : roles) {
                if (!adminRoleRepository.existsByName(role.getName())) {
                    adminRoleRepository.save(role);
                }
            }
            System.out.println("Initialized default admin roles.");
        }

        if(adminRepository.count() == 0) {
            Optional<AdminRole> roleSuperAdmin = adminRoleRepository.findByName("SUPER_ADMIN");

            if(roleSuperAdmin.isPresent()) {
                String encryptedPassword = passwordEncoder.encode("2024Dec23@umart");
                Admin superAdmin = new Admin(null, "Umar Faruq Robbany", null, "ufarq", "umar.faruq.tif423@polban.ac.id", encryptedPassword, LocalDateTime.now(), Admin.Status.ACTIVE, roleSuperAdmin.get());

                if(!adminRepository.existsByUsername(superAdmin.getUsername())) {
                    adminRepository.save(superAdmin);
                    System.out.println("Initialized default super admin.");
                }
            }
        }
    }

}
