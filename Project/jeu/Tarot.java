package jeu;

import javax.swing.JOptionPane;

import affichage.Debug;
import objets.Joueur;
import objets.Paquet;

/**
 * <b>Class withe the main()</b>
 * 
 * @author Aurélien
 *
 */
public class Tarot {

	static byte nbJoueur;					// Number of players.
	static byte nbDistribution = 0; 			// Distribution number made (Debug).
	
	public static void main(String[] args) {
		
		Debug.initConsole(); 				// Display the console, with a keyboard input.
		choixNombreDeJoueur(); 				// Choice of player's number.
		
		Jeu Tarot = new Jeu(nbJoueur); 			// Create the game according to it.
		Tarot.creationJoueurs(); 			// Create the players.
		
		while (Joueur.prise != true) {			// Loop while the player doens't "take".
			nbDistribution++;
			reset(); 		 	 	// Put everything back to zero.
			Tarot.distribution();			// Distribute the cards.
			Tarot.affichage();			// Display the pseudos and score.
			Tarot.mise();				// Offers the player to bet.
		}

		System.out.println("Vous avez fait : " + nbDistribution + " distribtutions avant de prendre ! \n"); //Display how many time the loop had work.
		
		Tarot.compositionChien();			// Composing the "dog" by player.
		
		while(Jeu.tourDeJeu < Jeu.tourDeJeuMAX) {
			
			Jeu.tourDeJeu++;
			System.out.println("++++++++++++++++ Tour n_" + Jeu.tourDeJeu + " ++++++++++++++++");
			
			Tarot.jouer();
		}
	}
	
	/**
	 * Choose the number of players.
	 */
	private static void choixNombreDeJoueur() {
		String[] nb = {"3 joueurs", "4 joueurs"};
	    nbJoueur = (byte) JOptionPane.showOptionDialog(null,  
	    	      "A combien voulez vous jouer ?",            //Windows message
	    	      "2D > 3D",				  //Windows title
	    	      JOptionPane.YES_NO_CANCEL_OPTION,		  //give a byte
	    	      JOptionPane.QUESTION_MESSAGE,		  //précise qu'on attends une réponse de l'utilisateur
	    	      null,
	    	      nb,					  //Reference list
	    	      nb[0]);
	    
	    if (nbJoueur == 0) { //Transform the answer got into more significant numbers.
	    	
	    	nbJoueur =3; //3  players.
	    } else {
	    	
	    	nbJoueur = 4; //4 players.
	    }
	}
	
	/**
	 * Reset the game.
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
