package com.apcompany;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apcompany.privilege.dao.PermissionDao;
import com.apcompany.privilege.dao.RoleDao;
import com.apcompany.privilege.dao.UserDao;
import com.apcompany.privilege.dao.UserRoleDao;
import com.apcompany.privilege.pojo.Permission;

@RunWith(SpringJUnit4ClassRunner.class)
// 配置了@ContextConfiguration注解并使用该注解的locations属性指明spring和配置文件之后，
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class MyBatisPriviligeFramework {

	// 注入userService
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	// @Test
	// public void insertTest1(){
	// Role role=new Role();
	// role.setRolename("admin");
	// roleDao.insert(role);
	// }

	// @Test
	// public void insertTest2(){
	// Permission permission=new Permission();
	// permission.setPermissionname("add");
	// permission.setRoleId(1);
	// permissionDao.insert(permission);
	// permission.setPermissionname("del");
	// permission.setRoleId(1);
	// permissionDao.insert(permission);
	// permission.setPermissionname("select");
	// permission.setRoleId(2);
	// permissionDao.insert(permission);
	// }

//	@Test
//	public void insertTest3() {
//		User user=new User();
//		user.setName("min");
//		user.setPassword("123456");
//		userDao.insert(user);
//	}
	
//	@Test
//	public void insertTest4() {
//		User user=new User();
//		user.setName("min");
//		user.setPassword("123456");
//		userDao.insert(user);
//	}
	
//	@Test
//	public void insertTest5() {
//		Role role=new Role();
//		role.setId(2);
//		UserRole userRole=new UserRole();
//		userRole.setUserId(2);
//		userRole.setRole(role);
//		userRoleDao.insert(userRole);
//	}
	
	@Test
	public void insertTest6() {
		List<Permission> list=permissionDao.getPermissionByRoleId(1);
		System.out.println(list);
		System.out.println(roleDao.getRoleById(1));
	}
}
