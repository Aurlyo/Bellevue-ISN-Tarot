package objets;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import jeu.Jeu;

/**
 * <b> Les joueurs ! </b>
 * <p>
 * Chaque joueurs possède :
 * <ul>
 * <li> Un pseudo (modifiable).
 * <li> Un score (modifiable).
 * <li> Un identifiant (NON modifiable).
 * <li> Une liste contenant ses cartes (= Sa main).
 * <li> Sa mise (et si oui ou non il mise).
 * <li> La carte jouée.
 * <li> Si oui ou non il a déjâ joué.
 * </ul>
 * 
 * @see Carte
 * 
 * @author Aurélien
 * @version 0.2
 */
public class Joueur {
	
	//ATTRIBUTS
	public String pseudo = "Aurelien , Wilhelm, Futa, Loli, Yoshi, Mario, Jack, Dio, Jotaro, Joseph, Polnareff, C3PO, R2D2, Assia, Alhynae, Flavien, Iridium, Saturne V, Ciel, Sebastian, L, Kira, Misaka, Aoba"; // Le pseudo, avec une liste de pseudo pré-défini.
	public String Pseudos[] = pseudo.split(",");
	
	public double score; 			// Le score
	public byte position; 			// L'identifiant , il sert a savoir quel joueur c'est, (1 / 2 / 3 / 4)
	public boolean prise;			// Si le joueurs a pris.
	public byte miseActuel = 2;		// Sa valeur de mise.
	public boolean victoire = true;
	
	public byte cartejouee;			// La carte jouée par le joueur
	public boolean jouer;			// Si le joueur a joué
	
	public ArrayList<Carte> Hand = new ArrayList<Carte>();	 // La main du joueur
	public ArrayList<Carte> CartesPossible = new ArrayList<Carte>();	// Les cartes qu'il peut jouer
	
	//CONSTRUCTEUR
	
	/** 
	 * @param Pseudo
	 * 					Le pseudo du joueur
	 * <p>
	 * @param score2
	 * 					Son score
	 * <p>
	 * @param Position
	 * 					Son identifiant
	 */
	public Joueur(String Pseudo,double score2,byte Position){  //(Pseudo, score, position) / ex : (Aurélien, 0 , 1) = Pseudo: Aurélien,Score: 0,Joueur n°01
		this.pseudo = Pseudo;
		this.score = score2;
		this.position = Position;
	}

	//METHODES
	
	/**
	 * @return Le pseudo du joueur. (String)
	 */
	public String getPseudo(){  //Affiche le pseudo
		return pseudo;
	}

	/**
	 * Change le pseudo du joueur.
	 * 
	 * @param PseudoEntree
	 * 					Le pseudo entrée par l'utilisateur.
	 */
	public void changePseudo(String PseudoEntree){  //Change le pseudo
		this.pseudo = PseudoEntree;
	}
	
	/**
	 * Nomme aléatoirement le joueur.
	 */
	public void pseudoAleatoire() {
		byte random = (byte) (Math.random() * ( this.Pseudos.length - 0 ));
		
		this.pseudo = Pseudos[random];
	}
	
	/**
	 * Permet de nommer le joueur.
	 * <p>
	 * On attribue au joueur un pseudo qu'il choisit
	 * </P>
	 */
	public void nommer() {
		
		new JOptionPane();
	    String entPseudo = JOptionPane.showInputDialog(null,/* "Joueur"+ nb + ":*/ "Veuillez indiquer votre pseudo :", "Et pourquoi pas Futa ?", JOptionPane.QUESTION_MESSAGE);

	    JOptionPane.showMessageDialog(null, "Votre pseudo est donc : " + entPseudo, "Magnifique pseudo d(^^*)", JOptionPane.INFORMATION_MESSAGE);
	    
	    this.changePseudo(entPseudo);
	  }

	/**
	 * @return Le score du joueur. (short)
	 */
	public double getScore(){  //Affiche le score
		return score;
	}
	
	/**
	 * Change le score du joueur.
	 * 
	 * @param ScoreEntree
	 * 					Le score du joueur.
	 */
	public void changeScore(short ScoreEntree){ 
		score = ScoreEntree;
	}
	
	/**
	 * Indique le nombre de carte du joueur.
	 */
	public void getHand() {
		System.out.print(pseudo + " a :" + Hand.size() + " cartes ! \n");
	}
	
	/**
	 * Affiche les cartes du joueurs.
	 * 
	 * @param hand
	 * 				Est-ce la main du joueur qu'on veut afficher ?
	 */
	public void display(boolean hand) {
		
		if(hand == true) {
			int i;
			System.out.println(getPseudo() + " a dans sa main :");
			
			for(i=0; i < Hand.size(); i++) {	
				System.out.println("[" + this.Hand.get(i) + "]  [" + i +"]");	//Affiche en colonne dans la console.
			}
			
			System.out.println( "----END---- \n");
		} else {
			
			int i;
			System.out.println(getPseudo() + " Vous pouvez jouer :");
			
			for(i=0; i < this.CartesPossible.size(); i++) {	
				System.out.println("[" + this.CartesPossible.get(i) + "]  [" + i +"]");	//Affiche en colonne dans la console.
			}
			
			System.out.println( "----END---- \n");
			
		}
	}
	
	/**
	 * Trie les cartes du joueur selon le compareur de la classe Carte.
	 * @see Carte
	 */
	public void trier() {
		Collections.sort(this.Hand);
	}
	
	/**
	 * Propose les mises au joueurs.s
	 */
	public void mise(){
		
		String[] Mise = {"Petite", "Garde", "Garde sans", "Garde contre"}; //Les mises possibles.
		
		byte mise = (byte) JOptionPane.showOptionDialog(null,
	    	      "Veuillez indiquer votre mise",
	    	      "Alors ? Bon jeu ou pas ? ;)",
	    	      JOptionPane.YES_NO_CANCEL_OPTION,
	    	      JOptionPane.QUESTION_MESSAGE,
	    	      null,
	    	      Mise,
	    	      Mise[0]);
		
		this.miseActuel = mise;
		
	}
	
	/**
	 * Propose au joueur de miser.
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
	 * Selectionne les cartes possible a jouer.
	 */
	void selectionCartes() {
		
		int couleurJouee = Jeu.carteJouees.get(0).getCouleur();
		
		if(couleurJouee == 0) { // PIQUE
			
			for(int i = 0; i < this.Hand.size(); i++) {
				
				int carte = this.Hand.get(i).getCouleur();
				
				if (carte == 0) {
					
					this.CartesPossible.add(this.Hand.get(i));
					this.Hand.remove(i);
					
				}
			}
			
			if(this.CartesPossible.isEmpty()) {
				
				for (int j = 0; j < this.Hand.size(); j++) {
					
					int carte1 = this.Hand.get(j).getCouleur();
					
					if (carte1 == 4) {
						
						this.CartesPossible.add(this.Hand.get(j));
						this.Hand.remove(j);
						
					}
				}
				
			}
			
		} else if(couleurJouee == 1) { // TREFLE
			
			for(int i = 0; i < this.Hand.size(); i++) {
				
				int carte = this.Hand.get(i).getCouleur();
				
				if (carte == 1) {
					
					this.CartesPossible.add(this.Hand.get(i));
					this.Hand.remove(i);
					
				}
			}
			
			if(this.CartesPossible.isEmpty()) {
				
				for (int j = 0; j < this.Hand.size(); j++) {
					
					int carte1 = this.Hand.get(j).getCouleur();
					
					if (carte1 == 4) {
						
						this.CartesPossible.add(this.Hand.get(j));
						this.Hand.remove(j);
						
					}
			
				}
				
			}
			
		} else if(couleurJouee == 2) { // COEUR
			
			for(int i = 0; i < this.Hand.size(); i++) {
				
				int carte = this.Hand.get(i).getCouleur();
				
				if (carte == 2) {
					
					this.CartesPossible.add(this.Hand.get(i));
					this.Hand.remove(i);
					
				}
			}
			
			if(this.CartesPossible.isEmpty()) {
				
				for (int j = 0; j < this.Hand.size(); j++) {
					
					int carte1 = this.Hand.get(j).getCouleur();
					
					if (carte1 == 4) {
						
						this.CartesPossible.add(this.Hand.get(j));
						this.Hand.remove(j);
						
					}
			
				}
				
			}
			
		} else if(couleurJouee == 3) { // Carreau
			
			for(int i = 0; i < this.Hand.size(); i++) {
				
				int carte = this.Hand.get(i).getCouleur();
				
				if (carte == 3) {
					
					this.CartesPossible.add(this.Hand.get(i));
					this.Hand.remove(i);
					
				}
			}
			
			if(this.CartesPossible.isEmpty()) {
				
				for (int j = 0; j < this.Hand.size(); j++) {
					
					int carte1 = this.Hand.get(j).getCouleur();
					
					if (carte1 == 4) {
						
						this.CartesPossible.add(this.Hand.get(j));
						this.Hand.remove(j);
						
					}
			
				}
				
			}
			
		} else if(couleurJouee == 4) { //ATOUT
				
			for(int i = 0; i < this.Hand.size(); i++) {
					
				int carte = this.Hand.get(i).getCouleur();
				int carte2 = this.Hand.get(i).getRang();
					
				if (carte == 4 && carte2 > Jeu.carteJouees.get(0).getRang()) {
						
					this.CartesPossible.add(this.Hand.get(i));
					this.Hand.remove(i);
						
				}
			}
				
		}
	}
	
	
	/**
	 * Joue une carte (POUR LES IA)
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
			
			selectionCartes();
			
			if(this.CartesPossible.isEmpty()) {
				
				this.cartejouee = (byte) (Math.random() * ( (Jeu.tourDeJeuMAX - Jeu.tourDeJeu) - 0 ));
				System.out.println(this.pseudo + " a jouer : [" + this.Hand.get(cartejouee) + "] !");

				Jeu.carteJouees.add(this.Hand.get(cartejouee));
				this.Hand.remove(cartejouee);
			} else {
		
				this.cartejouee = (byte) (Math.random() * ( (Jeu.tourDeJeuMAX - (this.Hand.size() + Jeu.tourDeJeu) - 0 )));
				System.out.println(this.pseudo + " a jouer : [" + this.CartesPossible.get(cartejouee) + "] !");

				Jeu.carteJouees.add(this.CartesPossible.get(cartejouee));
				this.CartesPossible.remove(cartejouee);
				//Remet les cartes dans la main du joueur
				this.Hand.addAll(CartesPossible);
				CartesPossible.clear();
			}
			
			trier();
			this.jouer = true;
				
			}
			
		}
	
	/**
	 * Joue une carte (Pour le joueur)
	 */
	public void jouerJOUEUR() {
		
		this.jouer = false;
		
		if(Jeu.carteJouees.isEmpty() == true && this.jouer == false) { //Si c'est le premier a jouer.
			
			this.display(true);
			System.out.println("Quel carte voulez vous jouer ? \n Utilise le nombre correspondant a sa position dans ta main.");
			this.cartejouee = Jeu.sc1.nextByte();			//Entrée clavier utilisateur
			System.out.println("Vous avez jouer [" + this.Hand.get(cartejouee) + "] !");
			Jeu.carteJouees.add(this.Hand.get(cartejouee));	//On ajoute la carte sur le terrain de jeu.
			this.Hand.remove(cartejouee);					//On retire la carte de la main du joueur.
			this.jouer = true;
			
		} else { // Sinon on verifie la couleur jouée.
					
			selectionCartes();
			
			if(this.CartesPossible.isEmpty()) {
				
				System.out.println("Quel carte voulez vous jouer ? \n Utilise le nombre correspondant a sa position dans ta main.");
				this.cartejouee = Jeu.sc1.nextByte();			//Entrée clavier utilisateur
				System.out.println("Vous avez jouer [" + this.Hand.get(cartejouee) + "] !");
				Jeu.carteJouees.add(this.Hand.get(cartejouee));	//On ajoute la carte sur le terrain de jeu.
				this.Hand.remove(cartejouee);					//On retire la carte de la main du joueur.
				
			} else {
		
				System.out.println("Quel carte voulez vous jouer ? \n Utilise le nombre correspondant a sa position dans ta main.");
				this.cartejouee = Jeu.sc1.nextByte();			//Entrée clavier utilisateur

				Jeu.carteJouees.add(this.CartesPossible.get(cartejouee));
				this.CartesPossible.remove(cartejouee);
				//Remet les cartes dans la main du joueur
				this.Hand.addAll(CartesPossible);
				CartesPossible.clear();
			}
			
			trier();
			this.jouer = true;
			
			
			}
			
		}
} //FIN CLASSE JOUEUR d(^^*)