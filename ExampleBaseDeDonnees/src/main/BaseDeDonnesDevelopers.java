package main;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JOptionPane;


public class BaseDeDonnesDevelopers {

	public static void main(String[] args) {
		/*try
		{
		  Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		}
		  catch (Exception e)
		{
		  System.out.println(e);
		  JOptionPane.showMessageDialog(null,"Pilote non valide ou introuvable !","Accès BD impossible",JOptionPane.WARNING_MESSAGE);
		}*/



		/*	Connection conn = null;

		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/avion?" +
		                                   "user=root&password=root");

		    // Do something with the Connection


		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}*/



		String pilote = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/developers";
		String utilisateur="root";
		String motDePasse="root";

		Connection connexion = null;
		try
		{
			Class.forName(pilote).newInstance();
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
			Statement stmt = connexion.createStatement();
			//stmt.executeUpdate("INSERT INTO Skills VALUES(5,'SQL')");
			//stmt.executeUpdate("delete from skills where id=5");


			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/developers?" +
					"user=root&password=root");
			viewTable(connexion); 
			//updateMethode(conn, "DEV", 5);
			//create or replace function bonjour(nom char)
			//return char as
			//begin
			 //  return 'Bonjour ' || nom;
			//end;
			//CallableStatement cs = connexion.prepareCall ("begin ? := bonjour(?); end;");
			//cs.registerOutParameter(1,Types.CHAR);
			//cs.setString(2, "Philippe");
			//cs.executeUpdate();
			//String resultat = cs.getString(1);

			System.out.println("tout va bien se doruler");

		}
		catch(Exception e)
		{
			System.out.println("la connexion n'a pu être établie");
		}


	}




	//Table skills
	public static void viewTable(Connection con) throws SQLException {

		String query = "select * from skills";

		try  {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);




			while (rs.next()) {
				String skillName = rs.getString("skill_name");
				int id = rs.getInt("Id");
				System.out.println("skill name "+ skillName);
				System.out.println("id "+ id);
			}


		} catch (SQLException e) {
			//JDBCTutorialUtilities.printSQLException(e);
			e.getMessage();

		}
	}

	public static void Insert(Connection conn, int id, String value )
	{
		try ( final PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO skills (id, skill_name) VALUES(?, ?)" ) )
		{
			ps.setInt(1, id );
			ps.setString(2, value);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static  void updateMethode ( Connection conn, String skill_name, int id )
			throws SQLException
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE skills SET skill_name = ?, id = ? WHERE id = ?");

			ps.setString(1,skill_name);
			ps.setInt(2,id);
			ps.setInt(3,id);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException se)
		{

			throw se;
		}
	}
	
	
	
	

}











