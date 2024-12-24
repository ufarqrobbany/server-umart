package com.umart.backend.config;

import com.umart.backend.model.admin.AdminRole;
import com.umart.backend.repository.admin.AdminRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AdminRoleRepository adminRoleRepository;

    public DataInitializer(AdminRoleRepository adminRoleRepository) {
        this.adminRoleRepository = adminRoleRepository;
    }

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
    }

}
