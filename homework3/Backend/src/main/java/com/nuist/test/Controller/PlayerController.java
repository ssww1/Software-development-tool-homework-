package com.nuist.test.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuist.test.DAO.PlayerDAO;
import com.nuist.test.Entity.PlayerTable;
import com.nuist.test.Entity.WorldTable;
import com.nuist.test.Service.PlayerService;
import com.nuist.test.Service.WorldService;
import com.nuist.test.Entity.ClassTable;
import java.util.Optional;

@RestController
public class PlayerController {
	@Autowired
	private PlayerService playerService;
	@Autowired
	private WorldService worldService;
	@GetMapping("/validate")
	public boolean login(@RequestParam String username, @RequestParam String password) {
		return playerService.loginService(username, password);
	}
	@GetMapping("/worlds")
	public Set<WorldTable> worlds(String pid) {
		return playerService.allWorlds(Integer.parseInt(pid));
	}
	@GetMapping("/players")
	public Set<PlayerTable> players(String wid) {
		return worldService.allPlayers(Integer.parseInt(wid));
	}
	@GetMapping("/class")
	public ResponseEntity<ClassTable> getClassByPlayerCid(@PathVariable int playerCid) {
		Optional<ClassTable> classTable = playerService.getClassByPlayerCid(playerCid);
		return classTable.map(ResponseEntity::ok)
						 .orElseGet(() -> ResponseEntity.notFound().build());
	}
}
