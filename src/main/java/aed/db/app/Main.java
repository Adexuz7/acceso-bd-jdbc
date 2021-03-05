package aed.db.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import aed.db.connection.MySQLConnection;
import aed.db.connection.SQLServerConnection;
import aed.db.controller.TeamController;
import aed.db.model.Team;

public class Main {

	private static Connection connection;
	private static TeamController teamController;

	public static void main(String[] args) {

		connection = MySQLConnection.connect();
		// connection = SQLServerConnection.connect();
		teamController = new TeamController(connection);

		Scanner sc = new Scanner(System.in);

		boolean appRunning = true;

		while (appRunning) {

			System.out.println("Selecciona una opción:");
			System.out.println("(1) Visualizar todos los equipos");
			System.out.println("(2) Insertar un equipo");
			System.out.println("(3) Modificar un equipo");
			System.out.println("(4) Eliminar un equipo");
			System.out.println("(5) Salir");
			int op = sc.nextInt();

			switch (op) {

			case 1:
				teamController.visualizeAllTeams();
				break;

			case 2:
				Scanner sc2 = new Scanner(System.in);

				String name, league, location;
				boolean international;

				System.out.println("Nombre:");
				name = sc2.nextLine();

				System.out.println("Código de liga: ");
				league = sc2.nextLine();

				System.out.println("Localidad: ");
				location = sc2.nextLine();

				System.out.println("Internacional: (s/n)");
				if (sc2.nextLine().equals("s")) {
					international = true;
				} else {
					international = false;
				}

				Team newTeam2 = new Team(name, league, location, international);
				teamController.insertTeam(newTeam2);
				break;

			case 3:

				int teamCode3;
				String name3, league3, location3;
				boolean international3;

				Scanner sc3 = new Scanner(System.in);

				System.out.println("Código de equipo: ");
				teamCode3 = sc3.nextInt();
				sc3.nextLine();

				Team newTeam3 = teamController.getTeam(teamCode3);

				System.out.println("Equipo a modificar:");
				System.out.println(newTeam3);

				System.out.println("Nombre:");
				name3 = sc3.nextLine();
				newTeam3.setNomEquipo(name3);

				System.out.println("Código de liga: ");
				league3 = sc3.nextLine();
				newTeam3.setCodLiga(league3);

				System.out.println("Localidad: ");
				location3 = sc3.nextLine();
				newTeam3.setLocalidad(location3);

				System.out.println("Internacional: (s/n)");
				if (sc3.nextLine().equals("s")) {
					international3 = true;
					newTeam3.setInternacional(international3);
				} else {
					international3 = false;
					newTeam3.setInternacional(international3);
				}

				teamController.updateTeam(newTeam3);
				break;

			case 4:

				Scanner sc4 = new Scanner(System.in);

				System.out.println("Introduce código de equipo a eliminar:");
				int codEquipo = sc4.nextInt();

				teamController.deleteTeam(codEquipo);
				sc.close();
				break;

			case 5:
				appRunning = false;
				break;

			}

		}

		sc.close();

		// Cerrar la conexión
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
