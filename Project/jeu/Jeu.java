package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objets.Carte;
import objets.Equipe;
import objets.Joueur;
import objets.Paquet;

/**
 * <b>Had all the functions relative to the game</b>
 * <p>
 * 
 * @see Joueur
 * @see Carte
 * @see Paquet
 * 
 * @author Aurélien
 * @version 1.0
 */
public class Jeu {
				
	//ATTRIBUTS
    public static Scanner sc1 = new Scanner(System.in);					//Keyboard inputs.
	static Paquet jeuDeCartes = new Paquet(); 					//Creat a Cards Deck.
	static List<Joueur> players = new ArrayList<Joueur>();				//Player's list.
	Equipe preneur = new Equipe((byte) 1);						//Create team "Taker".
	Equipe defenseur = new Equipe((byte) 0);					//Create team "Defender".
	public static List<Carte> carteJouees = new ArrayList<Carte>(); 		//List for actual playedCards.
	
	static short score = 0;
	static Joueur Joueur01 = new Joueur("Futa", score, (byte) 1), 			//Create the players
				  Joueur02 = new Joueur("Wilhelm", score, (byte) 2),
				  Joueur03 = new Joueur("Aurelien", score, (byte) 3),
				  Joueur04 = new Joueur("Loli", score, (byte) 4),
				  Chien = new Joueur("Chien", score, (byte) 5);
	
	static byte nbJoueur;				// Players number
	static int tourDeJeuMAX; 			// Maximum rounds
	public static int tourDeJeu = 0;		// ActualRound
	
	public Jeu(byte nbJoueurs) {
		nbJoueur = nbJoueurs;
	}
	
	//METHODES

	/**
	 * Name the players (according to the numbers of it), and add it to the list "players".
	 * 
	 * @param pNbJoueurs
	 * 					Numbers of players. (3/4)
	 */
	public void creationJoueurs() {
		
		players.add(Chien);
		
		if (nbJoueur == 3) {	// for 3 players.
			
			tourDeJeuMAX = 24;
			
			Joueur01.nommer();
			players.add(Joueur01);
			preneur.joueurs.add(Joueur01);
			
			Joueur02.pseudoAleatoire();
			players.add(Joueur02);
			defenseur.joueurs.add(Joueur02);
			Joueur03.pseudoAleatoire();
			players.add(Joueur03);
			defenseur.joueurs.add(Joueur03);
			
		} else if(nbJoueur == 4){	// for 4 players.
			
			tourDeJeuMAX = 18;
			
			Joueur01.nommer();
			players.add(Joueur01);
			preneur.joueurs.add(Joueur01);
			
			Joueur02.pseudoAleatoire();
			players.add(Joueur02);
			defenseur.joueurs.add(Joueur02);
			Joueur03.pseudoAleatoire();
			players.add(Joueur03);
			defenseur.joueurs.add(Joueur03);
			Joueur04.pseudoAleatoire();
			players.add(Joueur04);
			defenseur.joueurs.add(Joueur04);
		} else {
			throw new IllegalArgumentException("Erreur: nombre de joueurs impossible !");
		}
	}
			
	/**
	 * Distributes the cards. (according to number of players)
	 */
	public void distribution() {
		
		jeuDeCartes.initialisation();	//Add the 78 cards to the deck
		jeuDeCartes.melanger();		//Shuffle
		
		int i;
		int j;
		
		for(i = 0; i < 6; i++) {	//For the 6 cards from "Dog"
			
			Chien.Hand.add(Paquet.paquet.get(0)); 
			// Add the card to player's Hand ("dog" here but it's the same).
			Paquet.paquet.remove(0);
			// Remove it from the deck.
		}
		
		if( nbJoueur == 3) {	//For 3 players
			
			for(i = 0; i < 8; i++) { // 3 by 3 distribution. (exept for the "dog" , lack of time and unnecessary complexity for first release).
				
				for(j = 0; j < 3; j++) {
					
					Joueur01.Hand.add(Paquet.paquet.get(0));
					Paquet.paquet.remove(0);
					
					Joueur02.Hand.add(Paquet.paquet.get(0));
					Paquet.paquet.remove(0);
					
					Joueur03.Hand.add(Paquet.paquet.get(0));
					Paquet.paquet.remove(0);
					
				}
				
			}
		} else {			 // For 4 players
			
			for(i = 0; i < 6; i++) { // 3 by 3 distribution. (exept for the "dog" , lack of time and unnecessary complexity for first release).
				for(j = 0; j < 3; j++) {
					Joueur01.Hand.add(Paquet.paquet.get(0));
					Paquet.paquet.remove(0);
					Joueur02.Hand.add(Paquet.paquet.get(0));
					Paquet.paquet.remove(0);
					Joueur03.Hand.add(Paquet.paquet.get(0));
					Paquet.paquet.remove(0);
					Joueur04.Hand.add(Paquet.paquet.get(0));
					Paquet.paquet.remove(0);
					
				}
			}
		}
		
		Joueur01.trier();	//Sort the player Hand.
	}

	/**
	 * Display the players :
	 * <ul>
	 * <li> Joueur0X: Pseudo : <i>pseudo</i> Score: <i>score</i>
	 * </ul>
	 */
	public void affichage() {
			
		if (nbJoueur == 3) {
				
			System.out.println("Joueur 01:" + "\n" + "Pseudo : " + Joueur01.pseudo + "\n" + "Score : " + Joueur01.getScore() + "\n");
			Joueur01.display();
			
			System.out.println("Joueur 02:" + "\n" + "Pseudo : " + Joueur02.pseudo + "\n" + "Score : " + Joueur02.getScore() + "\n");
			//Joueur02.display();
			System.out.println("Joueur 03:" + "\n" + "Pseudo : " + Joueur03.pseudo + "\n" + "Score : " + Joueur03.getScore() + "\n");
			//Joueur03.display();
				
		} else {
				
			System.out.println("Joueur 01:" + "\n" + "Pseudo : " + Joueur01.pseudo + "\n" + "Score : " + Joueur01.getScore() + "\n");
			Joueur01.display();
			
			System.out.println("Joueur 02:" + "\n" + "Pseudo : " + Joueur02.pseudo + "\n" + "Score : " + Joueur02.getScore() + "\n");
			//Joueur02.display();
			System.out.println("Joueur 03:" + "\n" + "Pseudo : " + Joueur03.pseudo + "\n" + "Score : " + Joueur03.getScore() + "\n");
			//Joueur03.display();
			System.out.println("Joueur 04:" + "\n" + "Pseudo : " + Joueur04.pseudo + "\n" + "Score : " + Joueur04.getScore() + "\n");
			//Joueur04.display();
				
		}
	}
	
	/**
	 * Bet the player and add the "dog" to his hand
	 */
	public void mise() {
		
		Joueur01.prise();
		
		if(Joueur.prise == true) {
			
			for (byte i = 0; i < 6; i++) {
				
			Joueur01.Hand.add(Chien.Hand.get(0));
			Chien.Hand.remove(0);
			
			}
		}
	}

	/**
	 * The player make his "dog".
	 */
	public void compositionChien() {
		
		if(Joueur.miseActuel <= 1) {
			for(byte i = 6; i != 0; i--) { //To compose the "dog" of 6 cards.
				
				Joueur01.trier();	//Sort the player Hand.
				Joueur01.display();	//Display his hand.
				
				System.out.println("Il vous reste : " + i + " carte(s) a mettre dans le chien \n Veuillez composez votre chien, \n Pour cela il suffit d'entrer la position de la carte :");
				
				byte carteSelectionee = sc1.nextByte(); //Recovered the keyboard input.
				Chien.Hand.add(Joueur01.Hand.get(carteSelectionee)); //Add the card in the "dog".
				
				System.out.println("\n Vous avez mis [" + Joueur01.Hand.get(carteSelectionee) + "] dans le chien.");
				Joueur01.Hand.remove(carteSelectionee); //Remove it from his hand.
			}
		}
	}

	public void jouer() {
		
		if(nbJoueur == 3) {
			
			//The player begin ALL the games ! /!\
			// WIP !
				
				Joueur01.jouerJOUEUR();
				
				Joueur02.jouerIA();
				
				Joueur03.jouerIA();
			
				carteJouees.clear();
		} else {
			
		}
	}
} //END
