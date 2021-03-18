package uz.pdp.springbootsecuritytestdemo.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootsecuritytestdemo.entity.Role;
import uz.pdp.springbootsecuritytestdemo.entity.enums.RoleName;

import java.util.Collection;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByRoleNameIn(Collection<RoleName> roleName);
}
