package objets;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import jeu.Jeu;

/**
 * <b> Players ! </b>
 * <p>
 * Each player had :
 * <ul>
 * <li> a pseudo (alterable).
 * <li> a score (alterable).
 * <li> a identifiant (NOT alterable).
 * <li> a list with all his cards (= his Hand).
 * <li> his bet (and if he bet).
 * <li> played card.
 * <li> if he has already played.
 * </ul>
 * 
 * @see Carte
 * 
 * @author Aurélien
 * @version 0.2
 */
public class Joueur {
	
	//ATTRIBUTS
	public String pseudo = "Aurelien , Wilhelm, Futa, Loli, Yoshi, Mario, Jack, Dio, Jotaro, Joseph, Polnareff, C3PO, R2D2, Assia, Alhynae, Flavien, Iridium, Saturne V, Ciel, Sebastian, L, Kira, Misaka, Aoba"; // pseudo, with a preset list.
	public String Pseudos[] = pseudo.split(",");
	
	public short score; 			// score
	public byte position; 			// Id , it serves to know which player it's, (1 / 2 / 3 / 4)
	public static boolean prise;	// If he take..
	public static byte miseActuel;	// His bet.
	
	public byte cartejouee;		// Played card
	public boolean jouer;		// Played ?
	
	public ArrayList<Carte> Hand = new ArrayList<Carte>();
	
	//CONSTRUCTEUR
	/** 
	 * @param Pseudo
	 * 					his pseudo
	 * <p>
	 * @param Score
	 * 					his score
	 * <p>
	 * @param Position
	 * 					his id
	 */
	public Joueur(String Pseudo,short Score,byte Position){  //(Pseudo, score, position) / ex : (Aurélien, 0 , 1) = Pseudo: Aurélien,Score: 0,Joueur n°01
		this.pseudo = Pseudo;
		this.score = Score;
		this.position = Position;
	}

	//METHODES
	
	/**
	 * @return Player's pseudo. (String)
	 */
	public String getPseudo(){
		return pseudo;
	}

	/**
	 * Change player's pseudo.
	 * 
	 * @param PseudoEntree
	 * 					input pseudo.
	 */
	public void changePseudo(String PseudoEntree){
		this.pseudo = PseudoEntree;
	}
	
	/**
	 * Random name for player.
	 */
	public void pseudoAleatoire() {
		byte random = (byte) (Math.random() * ( this.Pseudos.length - 0 ));
		
		this.pseudo = Pseudos[random];
	}
	
	/**
	 * Allow to name the player.
	 * <p>
	 * The player is given a nickname that he chooses
	 * </P>
	 */
	public void nommer() {
		
		new JOptionPane();
	    String entPseudo = JOptionPane.showInputDialog(null,/* "Joueur"+ nb + ":*/ "Veuillez indiquer votre pseudo :", "Et pourquoi pas Futa ?", JOptionPane.QUESTION_MESSAGE);

	    JOptionPane.showMessageDialog(null, "Votre pseudo est donc : " + entPseudo, "Magnifique pseudo d(^^*)", JOptionPane.INFORMATION_MESSAGE);
	    
	    this.changePseudo(entPseudo);
	  }

	/**
	 * @return player's score. (short)
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * Change player's score.
	 * 
	 * @param ScoreEntree
	 * 					input score.
	 */
	public void changeScore(short ScoreEntree){ 
		score = ScoreEntree;
	}
	
	/**
	 * Indicates the player's card number.
	 */
	public void getHand() {
		System.out.print(pseudo + " a :" + Hand.size() + " cartes ! \n");
	}
	
	/**
	 * Display the player's cards.
	 */
	public void display() {
		int i;
		System.out.println(getPseudo() + " a dans sa main :");
		
		for(i=0; i < Hand.size(); i++) {	
			System.out.println("[" + Hand.get(i) + "]  [" + i +"]");	//Column poster in the console.
		}
		
		System.out.println( "----END---- \n");
	}
	
	/**
	 * Sort the player's card.
	 * @see Carte
	 */
	public void trier() {
		Collections.sort(this.Hand);
	}
	
	/**
	 * Offord the bet to the player.
	 */
	public int mise(){
		
		String[] Mise = {"Petite", "Garde", "Garde sans", "Garde contre"}; //Possible bet.
		
		byte mise = (byte) JOptionPane.showOptionDialog(null,
	    	      "Veuillez indiquer votre mise",
	    	      "Alors ? Bon jeu ou pas ? ;)",
	    	      JOptionPane.YES_NO_CANCEL_OPTION,
	    	      JOptionPane.QUESTION_MESSAGE,
	    	      null,
	    	      Mise,
	    	      Mise[0]);
		
		miseActuel = mise;
		
		return miseActuel;
	}
	
	/**
	 * Afford to take the "dog".
	 * @return 
	 */
	public boolean prise() {
		
		int priseChien = JOptionPane.showConfirmDialog(null,
				"Voulez-vous prendre ? \n",
				"Wouf !",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		
		if(priseChien == JOptionPane.OK_OPTION){
	          prise = true;
	          mise();
			} else {
		  	  prise = false;
		  	}
		
		return prise;
	}
	
	
	
	/**
	 * Play a card (FOR AI)
	 */
	public void jouerIA() {
		
		this.jouer = false;
		
		if(Jeu.carteJouees.isEmpty() == true) {
			
			this.cartejouee = (byte) (Math.random() * ( (24 - Jeu.tourDeJeu) - 0 ));
			System.out.println(this.pseudo + " a jouer : [" + this.Hand.get(cartejouee) + "] !");
			Jeu.carteJouees.add(this.Hand.get(cartejouee));
			this.Hand.remove(cartejouee);
			this.jouer = true;
			
		} else {
			
			while(Jeu.carteJouees.get(0).getCouleur() != this.Hand.get(cartejouee).getCouleur() && this.jouer == false) {
							
				this.cartejouee = (byte) (Math.random() * ( (24 - Jeu.tourDeJeu) - 0 ));
				
				if(this.Hand.get(cartejouee).getCouleur() == Carte.ATOUT && this.jouer == false) break;
			}
			
			System.out.println(this.pseudo + " a jouer : [" + this.Hand.get(cartejouee) + "] !");
			Jeu.carteJouees.add(this.Hand.get(cartejouee));
			this.Hand.remove(cartejouee);
			
			
		}
	}
	
	/**
	 * Play a card (player)
	 */
	public void jouerJOUEUR() {
		
		this.jouer = false;
		
		if(Jeu.carteJouees.isEmpty() == true && this.jouer == false) { //If it's the first to play.
			
			System.out.println("Quel carte voulez vous jouer ? \n Utilise le nombre correspondant a sa position dans ta main \n EN ATTENTE D'INTERFACE GRAPHIQUE !");
			this.cartejouee = Jeu.sc1.nextByte();			//Keyboard input
			System.out.println("Vous avez jouer [" + this.Hand.get(cartejouee) + "] !");
			Jeu.carteJouees.add(this.Hand.get(cartejouee));	// Adding the card on the playField
			this.Hand.remove(cartejouee);			// Remove it from his hand.
			this.jouer = true;
			
		} else { // Check the colors played.
			
			byte tentative = 0;
			
			if(tentative < 10) {
				
				while(Jeu.carteJouees.get(0).getCouleur() != this.Hand.get(cartejouee).getCouleur() && this.jouer == false) {
					
					tentative++;
					
					System.out.println("Quel carte voulez vous jouer ? \n Utilise le nombre correspondant a sa position dans ta main.");
					this.cartejouee = Jeu.sc1.nextByte();
					
					if(this.Hand.get(cartejouee).getCouleur() == Carte.ATOUT && jouer == false) { //Pour le cas ou la carte jouée est un atout. (=Couper).
						
						System.out.println("Vous avez jouer [" + this.Hand.get(cartejouee) + "] !");
						Jeu.carteJouees.add(this.Hand.get(cartejouee));	 //Adding the card on the playField
						this.Hand.remove(cartejouee);			 //Remove it from his hand.
						jouer = true;					 // Played = true
						
					} else {
						
						System.out.println("Vous ne pouver pas jouer cette carte !");
						
					}
				}
			} else {
				
				System.out.println("Il semblerait que vous etes arriver a un point de blocage !!!");
				
			}
			
			System.out.println("Vous avez jouer [" + this.Hand.get(cartejouee) + "] !");
			Jeu.carteJouees.add(this.Hand.get(cartejouee));	//On ajoute la carte sur le terrain de jeu.
			this.Hand.remove(cartejouee);
			jouer = true;
			
		}
	}
} //FIN CLASSE JOUEUR d(^^*)
