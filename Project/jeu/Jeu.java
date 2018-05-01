package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objets.Carte;
import objets.Joueur;
import objets.Paquet;

/**
 * <b>Contient toutes les fonctions relative au jeu</b>
 * <p>
 * On y trouve les fonctions :
 * <ul>
 * <li>D'initialisation.</li>
 * <ul>
 * <li>Création du paquet de cartes.
 * <li>Ainsi que des joueurs.
 * <li>De leurs mises.
 * </ul>
 * <li>De jeu.</li>
 * <ul>
 * <li> ???
 * </ul>
 * </ul>
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
    public static Scanner sc1 = new Scanner(System.in);					//Entrée clavier
	static Paquet jeuDeCartes = new Paquet(); 							//Création du jeu de cartes
	static List<Joueur> players = new ArrayList<Joueur>();				//Liste de Joueurs
	public static List<Carte> carteJouees = new ArrayList<Carte>(); 	//Liste pour les cartes actuellement jouées
	
	static short score = 0;
	static Joueur Joueur01 = new Joueur("Futa", score, (byte) 1), 		//Création des joueurs
				  Joueur02 = new Joueur("Wilhelm", score, (byte) 2),
				  Joueur03 = new Joueur("Aurelien", score, (byte) 3),
				  Joueur04 = new Joueur("Loli", score, (byte) 4),
				  Chien = new Joueur("Chien", score, (byte) 5);
	
	static byte nbJoueur;				//Le nombre de joueurs
	static int tourDeJeuMAX; 			//Le nombre de tours de jeu maximum ( = plis maximum)
	public static int tourDeJeu = 0;	//Tours de jeu
	
	public Jeu(byte nbJoueurs) {
		nbJoueur = nbJoueurs;
	}
	
	//METHODES

	/**
	 * Nommes les joueurs (selon le nombre), et les ajoutes a la liste "players".
	 * 
	 * @param pNbJoueurs
	 * 					Le nombre de joueurs. (3/4)
	 */
	public void creationJoueurs() {
		
		players.add(Chien);
		
		if (nbJoueur == 3) {
			
			tourDeJeuMAX = 24;
			
			Joueur01.nommer();
			players.add(Joueur01);
			Joueur02.pseudoAleatoire();
			players.add(Joueur02);
			Joueur03.pseudoAleatoire();
			players.add(Joueur03);
			
		} else if(nbJoueur == 4){
			
			tourDeJeuMAX = 18;
			
			Joueur01.nommer();
			players.add(Joueur01);
			Joueur02.pseudoAleatoire();
			players.add(Joueur02);
			Joueur03.pseudoAleatoire();
			players.add(Joueur03);
			Joueur04.pseudoAleatoire();
			players.add(Joueur04);
		} else {
			throw new IllegalArgumentException("Erreur: nombre de joueurs impossible !");
		}
	}
			
	/**
	 * Distribue les cartes. (toujours selon le nombre de joueurs.)
	 */
	public void distribution() {
		
		jeuDeCartes.initialisation();
		jeuDeCartes.melanger();
		
		int i;
		int j;
		
		for(i = 0; i < 6; i++) {
			
			Chien.Hand.add(Paquet.paquet.get(0)); 
			// Ajoute la carte a la liste du joueurs (ici le chien mais le principe est le même).
			Paquet.paquet.remove(0);
			// Supprime la carte du paquet.
		}
		
		if( nbJoueur == 3) {
			
			for(i = 0; i < 8; i++) { // Distribution 3 par 3 comme au tarot. (sauf la constitution du chien, manque de temps et complexification inutile pour première version).
				
				for(j = 0; j < 3; j++) {
					
					Joueur01.Hand.add(Paquet.paquet.get(0));
					Paquet.paquet.remove(0);
					
					Joueur02.Hand.add(Paquet.paquet.get(0));
					Paquet.paquet.remove(0);
					
					Joueur03.Hand.add(Paquet.paquet.get(0));
					Paquet.paquet.remove(0);
					
				}
				
			}
		} else {
			
			for(i = 0; i < 6; i++) { // Distribution 3 par 3 comme au tarot. (sauf la constitution du chien, manque de temps et complexification inutile pour première version).
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
		
		Joueur01.trier();
	}

	/**
	 * Affiche les joueurs :
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
	 * Fait miser le joueur et ajoute le chien a sa main.
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
	 * Le joueur compose son chien.
	 */
	public void compositionChien() {
		
		if(Joueur.miseActuel <= 1) {
			for(byte i = 6; i != 0; i--) { //Pour composer le chien de 6 cartes.
				
				Joueur01.trier();
				Joueur01.display();	//Affiche la main du joueur.
				
				System.out.println("Il vous reste : " + i + " carte(s) a mettre dans le chien \n Veuillez composez votre chien, \n Pour cela il suffit d'entrer la position de la carte :");
				
				byte carteSelectionee = sc1.nextByte(); //Récupère l'entrée clavier.
				Chien.Hand.add(Joueur01.Hand.get(carteSelectionee)); //Ajoute la carte dans le chien.
				
				System.out.println("\n Vous avez mis [" + Joueur01.Hand.get(carteSelectionee) + "] dans le chien.");
				Joueur01.Hand.remove(carteSelectionee); //Supprime la carte du joueur.
			}
		}
	}

	public void jouer() {
		
		if(nbJoueur == 3) {
			
		} else {
			
		}
	}
} //END