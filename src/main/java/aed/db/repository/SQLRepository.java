package aed.db.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SQLRepository {

	private Connection connection;
	private String pattern = "\\d{8}[A-Z]";

	public void spObtenerContratoFutbolista(String dni) {
		CallableStatement cstmt;
		ResultSet rs;

		if (!dni.matches(pattern)) {
			System.out.println("DNI no válido");
			return;
		}

		try {
			// Call the stored procedure
			String query = "{call contrato_futbolista(?)}";
			cstmt = connection.prepareCall(query);
			cstmt.setString(1, dni);
			// Execute query
			rs = cstmt.executeQuery();
			// Cycle through results
			while (rs.next()) {
				System.out.print("Contrato: " + rs.getInt("codContrato"));
				System.out.print(" - Equipo: " + rs.getString("nomEquipo"));
				System.out.print(" - Liga:" + rs.getString("nomLiga"));
				System.out.print(" - Fecha de inicio: " + rs.getString("fechaInicio"));
				System.out.print(" - Fecha de fin: " + rs.getString("fechaFin"));
				System.out.print(" - Precio anual: " + rs.getInt("precioAnual"));
				System.out.print(" - Precio rescisión: " + rs.getInt("precioRecision"));
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fnMesesEnEquipos(String dni) {

		if (!dni.matches(pattern)) {
			System.out.println("DNI no válido");
			return;
		}
		// Preparing a CallableStatement to call a function
		CallableStatement cstmt;
		try {
			cstmt = connection.prepareCall("{? = call fn_meses_en_equipos(?)}");

			// Registering the out parameter of the function (return type)
			cstmt.registerOutParameter(1, Types.INTEGER);
			// Setting the input parameters of the function
			cstmt.setString(2, dni);
			// Executing the statement
			cstmt.execute();
			System.out.println("Meses en equipos: " + cstmt.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public SQLRepository(Connection connection) {
		this.connection = connection;
	}

}
