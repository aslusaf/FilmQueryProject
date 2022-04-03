package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	private String user = "student";
	private String pass = "student";

	@Override
	public Film findFilmById(int filmId) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt;
//			sqltxt = "SELECT * FROM film WHERE film.id = ?";
			sqltxt = "SELECT f.*, l.name FROM film f JOIN language l ON f.language_id = l.id WHERE f.id = ?";
			stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Integer releaseYear = rs.getInt("release_year");
				int languageId = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				Integer length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				String language = rs.getString("name");

				rs.close();
				stmt.close();

				return new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures, language);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException sqle) {
				System.out.println(sqle);
			}
		}

		return null;
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver not found.");
			throw new RuntimeException("Unable to MySQL load driver class");
		}
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Film> filmList = new ArrayList<>();

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt;
//			sqltxt = "SELECT * FROM film WHERE film.id = ?";
			sqltxt = "SELECT f.*, l.name FROM film f JOIN language l ON f.language_id = l.id WHERE f.title LIKE ? OR f.description LIKE ? ORDER BY f.id";
//			sqltxt = "SELECT f.*, l.name FROM film f JOIN language l ON f.language_id = l.id WHERE f.title LIKE "%bea%" OR f.description LIKE "%bea%" ORDER BY f.id";
			stmt = conn.prepareStatement(sqltxt);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Integer releaseYear = rs.getInt("release_year");
				int languageId = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				Integer length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				String language = rs.getString("name");

				filmList.add(new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate,
						length, replacementCost, rating, specialFeatures, language));

			}

			rs.close();
			stmt.close();

			return filmList;

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException sqle) {
				System.out.println(sqle);
			}
		}

		return null;
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver not found.");
			throw new RuntimeException("Unable to MySQL load driver class");
		}
	}

	@Override
	public Actor findActorById(int actorId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt;
			sqltxt = "SELECT * FROM actor WHERE actor.id = ?";
			stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, actorId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				return new Actor(id, firstName, lastName);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException sqle) {
				System.out.println(sqle);
			}
		}

		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Actor> actor = new ArrayList<>();

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt;
			sqltxt = "SELECT a.id, a.first_name, a.last_name FROM actor a JOIN film_actor fa ON fa.actor_id = a.id JOIN film f ON f.id = fa.film_id WHERE f.id = ? ORDER BY a.id";
			stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				actor.add(new Actor(id, firstName, lastName));

			}

			rs.close();
			stmt.close();

			return actor;

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException sqle) {
				System.out.println(sqle);
			}
		}

		return null;
	}

}
