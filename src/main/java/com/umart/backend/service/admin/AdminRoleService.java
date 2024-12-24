package com.umart.backend.service.admin;

import com.umart.backend.model.admin.AdminRole;
import com.umart.backend.repository.admin.AdminRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AdminRoleService {

    @Autowired
    private AdminRoleRepository adminRoleRepository;
}
