package com.nuist.test.Service;

import java.util.List;
import java.util.Set;
import com.nuist.test.Entity.ClassTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nuist.test.DAO.PlayerDAO;
import com.nuist.test.DAO.WorldDAO;
import com.nuist.test.Entity.PlayerTable;
import com.nuist.test.Entity.WorldTable;

import java.util.Optional;

@Service
public class PlayerService {
	@Autowired
	private PlayerDAO playerDAO;
	public boolean loginService(String username, String password) {
		if (playerDAO.findByUsernameAndPassword(username, password) != null) {
			return true;
		} else {
			return false;
		}
	}
	public Set<WorldTable> allWorlds(Integer pid) {
		PlayerTable playerTable=playerDAO.findByPid(pid);
		return playerTable.getWorlds();
	}

	public ClassTable getClass(Integer pid) {
		PlayerTable playerTable=playerDAO.findByPid(pid);
		return playerTable.getClassTable();
	}

	private ClassTable getClassTableByCid(int cid) {
		
		return new ClassTable(); // 示例返回
	}
}
