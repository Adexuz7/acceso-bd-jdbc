package aed.db.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

import aed.db.dao.TeamDAO;
import aed.db.model.Team;

public class TeamController implements TeamDAO {

	private Connection conn;

	public void visualizeAllTeams() {

		try {
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			Statement stmt = conn.createStatement();

			String sql = "SELECT codEquipo, nomEquipo, codLiga, localidad, internacional FROM Equipos";
			ResultSet rs = stmt.executeQuery(sql);

			// Print lines
			printLines();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				Team team = new Team(rs.getInt("codEquipo"), rs.getString("nomEquipo"), rs.getString("codLiga"),
						rs.getString("localidad"), rs.getBoolean("internacional"));

				// Display values
				System.out.println(team);
				// Print lines
				printLines();
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void insertTeam(Team team) {
		try {
			// STEP 4: Execute a query
			System.out.println("Inserting records into the table...");
			Statement stmt = conn.createStatement();

			String sql = "INSERT INTO Equipos (nomEquipo, codLiga, localidad, internacional) " + "VALUES (" + "'"
					+ team.getNomEquipo() + "'" + "," + "'" + team.getCodLiga() + "'" + "," + "'" + team.getLocalidad()
					+ "'" + "," + team.getInternacional() + ")";
			stmt.executeUpdate(sql);

			System.out.println("Inserted records into the table...");

		} catch (SQLException se) {
			se.printStackTrace();
		}

		visualizeAllTeams();
	}

	public void updateTeam(Team team) {
		try {

			Statement stmt = conn.createStatement();
			String sql = "UPDATE Equipos " + "SET nomEquipo = " + "'" + team.getNomEquipo() + "'" + ", codLiga = " + "'"
					+ team.getCodLiga() + "'" + ", localidad = " + "'" + team.getLocalidad() + "'"
					+ ", internacional = " + team.getInternacional() + " WHERE codEquipo = " + team.getCodEquipo();
			stmt.executeUpdate(sql);

		} catch (SQLException se) {
			se.printStackTrace();
		}

		visualizeAllTeams();
	}

	public void deleteTeam(int codEquipo) {
		try {

			Scanner sc = new Scanner(System.in);

			Team team = getTeam(codEquipo);

			System.out.println("¿Seguro que desea eliminar el equipo: " + team.getNomEquipo() + "? (s/n)");
			String answer = sc.next();

			if (answer.equals("s")) {
				Statement stmt = conn.createStatement();
				String sql = "DELETE FROM Equipos " + "WHERE codEquipo = " + codEquipo;
				stmt.executeUpdate(sql);
			}

			sc.close();

		} catch (SQLIntegrityConstraintViolationException ie) {
			// Eliminar contratos antes que el equipo
			try {
				// Eliminar contratos
				Statement stmt = conn.createStatement();
				String sql = "DELETE FROM Contratos " + "WHERE codEquipo = " + codEquipo;
				stmt.executeUpdate(sql);

				// Eliminar equipo
				stmt = conn.createStatement();
				sql = "DELETE FROM Equipos " + "WHERE codEquipo = " + codEquipo;
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}

		visualizeAllTeams();

	}

	public Team getTeam(int codEquipo) {
		Team team = null;

		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT codEquipo, nomEquipo, codLiga, localidad, internacional FROM Equipos";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Team currentTeam = new Team(rs.getInt("codEquipo"), rs.getString("nomEquipo"), rs.getString("codLiga"),
						rs.getString("localidad"), rs.getBoolean("internacional"));

				if (currentTeam.getCodEquipo() == codEquipo)
					team = currentTeam;
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return team;
	}

	public TeamController(Connection connection) {
		this.conn = connection;
	}

	private static void printLines() {
		for (int i = 0; i < 120; i++)
			System.out.print("-");
		System.out.println();
	}

}
