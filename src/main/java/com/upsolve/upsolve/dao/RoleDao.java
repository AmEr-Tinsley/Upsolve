package com.upsolve.upsolve.dao;

import com.upsolve.upsolve.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
