package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objets.Carte;
import objets.Equipe;
import objets.Joueur;
import objets.Paquet;

/**
 * <b>Contient toutes les fonctions relative au jeu</b>
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
    public static Scanner sc1 = new Scanner(System.in);					//Entrée clavier
	static Paquet jeuDeCartes = new Paquet(); 					//Création du jeu de cartes
	static List<Joueur> players = new ArrayList<Joueur>();				//Liste de Joueurs
	Equipe preneur = new Equipe((byte) 1);						//Création de l'équipe "preneur"
	Equipe defenseur = new Equipe((byte) 0);					//Création de l'équipe 'Defenseur'.
	public static List<Carte> carteJouees = new ArrayList<Carte>(); 		//Liste pour les cartes actuellement jouées
	
	static double score = 0.0;
	static Joueur Joueur01 = new Joueur("Futa", score, (byte) 1), 		//Création des joueurs
				  Joueur02 = new Joueur("Wilhelm", score, (byte) 2),
				  Joueur03 = new Joueur("Aurelien", score, (byte) 3),
				  Joueur04 = new Joueur("Loli", score, (byte) 4),
				  Chien = new Joueur("Chien", score, (byte) 5);
	
	static byte nbJoueur;			//Le nombre de joueurs
	public static int tourDeJeuMAX; 	//Le nombre de tours de jeu maximum ( = plis maximum)
	public static int tourDeJeu = 0;	//Tours de jeu
	static double scoreNecessaire = 51;	//Le score necessaire pour gagner
	byte bouts = 0;				//Le nombre de bout du preneur
	
	public Jeu(byte nbJoueurs) {
		nbJoueur = nbJoueurs;
	}
	
	//METHODES

	/**
	 * Nommes les joueurs (selon le nombre), et les ajoutes a la liste "players".
	 * 
	 * @param pNbJoueurs
	 * 		Le nombre de joueurs. (3/4)
	 */
	public void creationJoueurs() {
		
		players.add(Chien);
		
		if (nbJoueur == 3) {	// Pour 3 joueurs.
			
			tourDeJeuMAX = 24;	//Nombre de plis maximum (= nombre de tour de jeu a faire)
			
			Joueur01.nommer();			// Nomme le joueur
			players.add(Joueur01);			// Ajout a la liste de joueur
			preneur.joueurs.add(Joueur01);		// Ajout a l'équipe " Preneur " (étant donner que dans cette version,
			c'est le joueur qui prends forcément)
			
			Joueur02.pseudoAleatoire();		// Choisit un pseudo parmi une liste prédeterminé
			players.add(Joueur02);			// Ajout a la liste de joueur
			defenseur.joueurs.add(Joueur02);	// Ajout a l'équipê " défenseur "
			Joueur03.pseudoAleatoire();
			players.add(Joueur03);
			defenseur.joueurs.add(Joueur03);
			
		} else if(nbJoueur == 4){	// Pour 4 joueurs.
			
			tourDeJeuMAX = 18;      //Nombre de plis maximum (= nombre de tour de jeu a faire)
			
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
			// Renvois une erreur :
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
			
			for(i = 0; i < 8; i++) { // Distribution 3 par 3 comme au tarot. (sauf la constitution du chien,
				                 //manque de temps et complexification inutile pour première version).
				
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
			
			for(i = 0; i < 6; i++) { // Distribution 3 par 3 comme au tarot. (sauf la constitution du chien,
						 //manque de temps et complexification inutile pour première version).
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
			Joueur01.display(true);
			
			System.out.println("Joueur 02:" + "\n" + "Pseudo : " + Joueur02.pseudo + "\n" + "Score : " + Joueur02.getScore() + "\n");
			//Joueur02.display();
			System.out.println("Joueur 03:" + "\n" + "Pseudo : " + Joueur03.pseudo + "\n" + "Score : " + Joueur03.getScore() + "\n");
			//Joueur03.display();
				
		} else {
				
			System.out.println("Joueur 01:" + "\n" + "Pseudo : " + Joueur01.pseudo + "\n" + "Score : " + Joueur01.getScore() + "\n");
			Joueur01.display(true);
			
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

		if(Joueur01.miseActuel == 0 || Joueur01.miseActuel == 1) {
			
			for (byte i = 0; i < 6; i++) {
				
			Joueur01.Hand.add(Chien.Hand.get(0));
			Chien.Hand.remove(0);
			
			
			}
			
			compositionChien();
		}
	}

	/**
	 * Le joueur compose son chien.
	 */
	public void compositionChien() {
			
			for(byte i = 6; i != 0; i--) { //Pour composer le chien de 6 cartes.
				
				Joueur01.trier();
				Joueur01.display(true);	//Affiche la main du joueur.
				
				System.out.println("Il vous reste : " + i + " carte(s) a mettre dans le chien \n Veuillez composez votre chien, \n Pour cela il suffit d'entrer la position de la carte :");
				
				byte carteSelectionee = sc1.nextByte(); //Récupère l'entrée clavier.
				Chien.Hand.add(Joueur01.Hand.get(carteSelectionee)); //Ajoute la carte dans le chien.
				
				System.out.println("\n Vous avez mis [" + Joueur01.Hand.get(carteSelectionee) + "] dans le chien.");
				Joueur01.Hand.remove(carteSelectionee); //Supprime la carte du joueur.
			}
			
	}
	
	/**
	 * Fait jouer les joueurs.
	 * (Simplifier) = Work in progress !
	 * 
	 */
	public void jouer() {
		
		if(nbJoueur == 3) {	//Pour 3 joueurs
			
			//Le joueur commenceras toujours les parties /!\
			if(Joueur01.victoire == true) {
				Joueur01.jouerJOUEUR();
				
				Joueur02.jouerIA();
				
				Joueur03.jouerIA();
				
				victoire();
			} else if(Joueur02.victoire == true) {
				
				Joueur02.jouerIA();
				
				Joueur03.jouerIA();
				
				Joueur01.jouerJOUEUR();
				
				victoire();
				
			} else if(Joueur03.victoire == true) {
				
				Joueur03.jouerIA();
				
				Joueur01.jouerJOUEUR();
				
				Joueur02.jouerIA();
				
				victoire();
				
			}
		} else {	// Pour 4 joueurs
			
			Joueur01.jouerJOUEUR();
			
			Joueur02.jouerIA();
			
			Joueur03.jouerIA();
			
			Joueur04.jouerIA();
			
			victoire();
			
		}
	}
	
	/**
	 * Détermine qui gagne la manche(/plis)
	 */
	public void victoire() {
		
		if(nbJoueur == 3) {
			
			byte c1 = (byte) carteJouees.get(0).getRang();
			byte c2 = (byte) carteJouees.get(1).getRang();
			byte c3 = (byte) carteJouees.get(2).getRang();
			
			if( c1 > c2 && c1 > c3) { // Joueur01 gagne la manche
				
				for (byte i = 0; i < carteJouees.size() ; i++) {
					
					preneur.plis.add(carteJouees.get(0));
					carteJouees.remove(0);
				}
				
				System.out.println(" Vous avez gagné(e) ce plis !"); 
				Joueur01.victoire = false;
				carteJouees.clear();
				
			} else {			// Joueur01 perd la manche
						
				for (byte i = 0; i < carteJouees.size() ; i++) {
					
					defenseur.plis.add(carteJouees.get(0));
					carteJouees.remove(0);
				}
				
				System.out.println(" Vous avez perdu(e) ce plis !"); 
				carteJouees.clear();
			}
		} else {
			
			byte c1 = (byte) carteJouees.get(0).getRang();
			byte c2 = (byte) carteJouees.get(1).getRang();
			byte c3 = (byte) carteJouees.get(2).getRang();
			byte c4 = (byte) carteJouees.get(3).getRang();
			
			if( c1 > c2 && c1 > c3 && c1> c4) { // Joueur01 gagne la manche
				
				for (byte i = 0; i < carteJouees.size() ; i++) {
					
					preneur.plis.add(carteJouees.get(0));
					carteJouees.remove(0);
				}
				
				System.out.println(" Vous avez gagné(e) ce plis !"); 
				carteJouees.clear();
				
			} else {			// Joueur01 perd la manche
						
				for (byte i = 0; i < carteJouees.size() ; i++) {
					
					defenseur.plis.add(carteJouees.get(0));
					carteJouees.remove(0);
				}
				
				System.out.println(" Vous avez perdu(e) ce plis !"); 
				carteJouees.clear();
			}
			
		}
	}
	
	/**
	 * Compte les points des joueurs. 
	 */
	public void scoring() {
		
		for (byte i = 0; i < preneur.plis.size(); i++) {
			
			byte carte = (byte) preneur.plis.get(i).getRang();
			
			
			
			//Compte le nombre de bouts du preneur
			switch (carte) {
			
				case 15 : bouts = (byte) (bouts + 1); // Petit
				case 35 : bouts = (byte) (bouts + 1); // 21 d'atout
				case 36 : bouts = (byte) (bouts + 1); // Excuse
			
			}
			
			//Décompte des points du preneur :
			if (carte < 10 || (carte >= 16 && carte <= 34)) { //Si le rang est compris entre [0;10]U[16;34] .
				
				preneur.score = preneur.score + 0.5 ;		  // On augmente de 0.5 le score.
				
			} else {
			
				switch(carte) {
				
					//Tête
					case 11 : preneur.score = preneur.score + 1.5 ; //Valet
					case 12 : preneur.score = preneur.score + 2.5 ; //Cavalier
					case 13 : preneur.score = preneur.score + 3.5 ; //Dame
					case 14 : preneur.score = preneur.score + 4.5 ; //Roi
					//ATOUTS
					case 15 : preneur.score = preneur.score + 4.5 ; //Petit
					case 35 : preneur.score = preneur.score + 4.5 ; // 21
					case 36 : preneur.score = preneur.score + 4.5 ; // Excuse
						
				}
			}
		}
		
		//Score necessaire pour réussir :
		switch(bouts) {
		
			case 1 : scoreNecessaire = 51;
			case 2 : scoreNecessaire = 41;
			case 3 : scoreNecessaire = 36;
		
		}
		
		if (preneur.score >= scoreNecessaire) {	//Victoire
			
			byte mise = Joueur01.miseActuel;
			
			switch(mise) {
			
			case 1: Joueur01.score = Joueur01.score + preneur.score * 2;// Garde(x2)
					Joueur02.score = Joueur02.score - preneur.score;
					Joueur03.score = Joueur03.score - preneur.score;
					if (nbJoueur == 4) {
						Joueur04.score = Joueur04.score - preneur.score;
					}

			case 2: Joueur01.score = Joueur01.score + preneur.score * 4;// Garde sans(x4)
					Joueur02.score = Joueur02.score - preneur.score;
					Joueur03.score = Joueur03.score - preneur.score;
					if (nbJoueur == 4) {
						Joueur04.score = Joueur04.score - preneur.score;
					}
					
			case 3: Joueur01.score = Joueur01.score + preneur.score * 3;// Garde contre(x6)
					Joueur02.score = Joueur02.score - preneur.score;
					Joueur03.score = Joueur03.score - preneur.score;
					if (nbJoueur == 4) {
						Joueur04.score = Joueur04.score - preneur.score;
					}
					
			}
			
			preneur.score = 0;// Reset du score des preneurs
			System.out.println(" \n Vous avez gagné !   :D ");
		} else { //Défaite
			
			byte mise = Joueur01.miseActuel;
			
			switch(mise) {
			
			case 1: Joueur01.score = Joueur01.score - preneur.score * 2;// Garde(x2)
					Joueur02.score = Joueur02.score + preneur.score;
					Joueur03.score = Joueur03.score + preneur.score;
					if (nbJoueur == 4) {
						Joueur04.score = Joueur04.score + preneur.score;
					}

			case 2: Joueur01.score = Joueur01.score - preneur.score * 4;// Garde sans(x4)
					Joueur02.score = Joueur02.score + preneur.score;
					Joueur03.score = Joueur03.score + preneur.score;
					if (nbJoueur == 4) {
						Joueur04.score = Joueur04.score + preneur.score;
					}
					
			case 3: Joueur01.score = Joueur01.score - preneur.score * 3;// Garde contre(x6)
					Joueur02.score = Joueur02.score + preneur.score;
					Joueur03.score = Joueur03.score + preneur.score;
					if (nbJoueur == 4) {
						Joueur04.score = Joueur04.score + preneur.score;
					}
			}
			
			preneur.score = 0; // Reset du score des preneurs
			System.out.println(" \n Vous avez perdu !  :( ");
		}
		
		
		
	}
	
} //END
