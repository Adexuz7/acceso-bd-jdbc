package aed.db.dao;

import aed.db.model.Team;

public interface TeamDAO {
	
	public void visualizeAllTeams();
	
	public void insertTeam(Team team);
	
	public void updateTeam(Team team);
	
	public void deleteTeam(int codEquipo);
	
}
