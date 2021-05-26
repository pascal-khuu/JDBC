package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDeDonnesAvion {
	public static void main(String[] args) {
		String pilote = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/avion";
		String utilisateur="root";
		String motDePasse="root";
		
		Connection connexion = null;
		try
		{
			// Charger le pilote
			Class.forName(pilote).newInstance();
			// Préparer la connexion
		connexion = DriverManager.getConnection(url,utilisateur,motDePasse);
		// indique que la connexion est réussie
		System.out.println("la connexion est réussie");
		// Préparer la requête SQL
		String sql = "SELECT * from avion";
		// Exécuter la requête
		Statement instruction = null; // déclaration
		 instruction = connexion.createStatement(); // initialisation
		 // Récupérer les résultats
		 ResultSet resultat = null;
		 resultat = instruction.executeQuery(sql); // exécution et récupération du résultat
		
		while (resultat.next()) // tant qu'il y a des enregistrements
			{
				int av_id = resultat.getInt("AV_ID");
				String av_const = resultat.getString("AV_CONST");
				String av_modele = resultat.getString("AV_MODELE");
				int av_capacite = resultat.getInt("AV_CAPACITE");
				String av_site = resultat.getString("AV_SITE");
				System.out.println("caractéristiques : " + av_id + " " + av_const + " "+ av_modele+ " "+av_capacite+" "+ av_site);
				
			}
		
		//instruction.executeUpdate("Insert into avion values(110,'AIRBUS','A320',250,'Paris')");
		// préparation de la requête
		PreparedStatement stm = connexion.prepareStatement("UPDATE avion SET AV_CAPACITE=? WHERE AV_ID=?");

		// affectation de valeurs aux paramètres de la requête
		stm.setInt(1,300);
		stm.setInt(2,10);

		// exécution de la requête
		stm.executeUpdate();

		 
		}
		catch(Exception e)
		{
			System.out.println("la connexion n'est pas réussie");
		}
	}
}
