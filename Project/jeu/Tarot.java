package jeu;

import javax.swing.JOptionPane;

import affichage.Debug;
import objets.Paquet;

/**
 * <b>Classe contenant le main()</b>
 * 
 * @author Aurélien
 *
 */
public class Tarot {

	static byte nbJoueur;				// Le nombre de Joueurs.
	static byte nbDistribution = 0; 		// Le nombre de distribution effectué (Debug).
	static boolean rejouer = true;
	
	public static void main(String[] args) {
		
		Debug.initConsole(); 			// Affiche une fenêtre console, avec entrée clavier.
		
		while (rejouer == true) {
			nbDistribution = 0;
			Jeu.Joueur01.prise = false;
			choixNombreDeJoueur(); 			// Choix du nombre de joueurs.
			
			Jeu Tarot = new Jeu(nbJoueur); 		// Création du jeu selon ce nombre de joueurs.
			Tarot.creationJoueurs(); 		// Création des joueurs.
			
			while (Jeu.Joueur01.prise != true) {// Boucle tant que le joueur ne prends pas.
				nbDistribution++;
				reset(); 		 	 	// Remet tout a zero.
				Tarot.distribution();			// Distribue les cartes.
				Tarot.affichage();			// Affiche les pseudos et score des joueurs.
				Tarot.mise();				// Propose au joueur de miser.
			}

			System.out.println("Vous avez fait : " + nbDistribution + " distribtutions avant de prendre ! \n"); 
			//indique le nombre de fois que la boucle a été exécuté.
			
			while(Jeu.tourDeJeu < Jeu.tourDeJeuMAX) {
				
				Jeu.tourDeJeu++;
				System.out.println("++++++++++++++++ Tour n_" + Jeu.tourDeJeu + " ++++++++++++++++");
				
				Tarot.jouer();
				
			}
			
			Tarot.scoring();
			Tarot.affichage();
			
			// REJOUER ?
			String[] nb = {"Oui", "Non"};
			int rejouer1 = JOptionPane.showOptionDialog(null, 		//Permet de choisir le nombre de joueurs.
		    	      "Voulez vous rejouer ?",            			//Message de la fenêtre
		    	      " Encore une ?",						//Titre de la fenêtre
		    	      JOptionPane.YES_NO_CANCEL_OPTION,			  	//permet de donner un int
		    	      JOptionPane.QUESTION_MESSAGE,				//précise qu'on attends une réponse de l'utilisateur
		    	      null,
		    	      nb,							//Liste de référence
		    	      nb[0]);
			if (rejouer1 == 0) { //Transforme la réponse en obtenu, en chiffre plus significatifs.
		    	
				rejouer = true;
		    } else {
		    	
		    	rejouer = false;
		    }	
		}
	}
	
	/**
	 * Permet de choisir le nombre de joueurs.
	 */
	private static void choixNombreDeJoueur() {
		String[] nb = {"3 joueurs", "4 joueurs"};
	    nbJoueur = (byte) JOptionPane.showOptionDialog(null,  //Permet de choisir le nombre de joueurs.
	    	      "A combien voulez vous jouer ?",            //Message de la fenêtre
	    	      "2D > 3D",				  //Titre de la fenêtre
	    	      JOptionPane.YES_NO_CANCEL_OPTION,		  //permet de donner un int
	    	      JOptionPane.QUESTION_MESSAGE,		  //précise qu'on attends une réponse de l'utilisateur
	    	      null,
	    	      nb,					  //Liste de référence
	    	      nb[0]);
	    
	    if (nbJoueur == 0) { //Transforme la réponse en obtenu, en chiffre plus significatifs.
	    	
	    	nbJoueur =3; //3 joueurs
	    } else {
	    	
	    	nbJoueur = 4; //4 joueurs
	    }
	}
	
	/**
	 * Réinitialise le jeu.
	 */
	private static void reset() {
		
		Paquet.paquet.clear();
			Jeu.Joueur01.Hand.clear();
			Jeu.Joueur02.Hand.clear();
			Jeu.Joueur03.Hand.clear();
			Jeu.Joueur04.Hand.clear();			
			Jeu.Chien.Hand.clear();
	}
}
