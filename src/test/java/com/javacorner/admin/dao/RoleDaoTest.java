package com.javacorner.admin.dao;

import com.javacorner.admin.AbstractTest;
import com.javacorner.admin.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql","file:src/test/resources/db/javacorner-admin-db.sql"})
class RoleDaoTest extends AbstractTest {

    @Autowired
    private RoleDao roleDao;

    @Test
    void testFindByName() {
        String roleName = "Admin";
        Role role = roleDao.findByName(roleName);
        assertEquals(roleName, role.getName());
    }

    @Test
    void testFindNonExistingRole() {
        String roleName = "NewRole";
        Role role = roleDao.findByName(roleName);
        assertNull(role);
    }
}