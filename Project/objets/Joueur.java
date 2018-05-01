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
	
	public short score; 			// Le score
	public byte position; 			// L'identifiant , il sert a savoir quel joueur c'est, (1 / 2 / 3 / 4)
	public static boolean prise;	// Si le joueurs a pris.
	public static byte miseActuel;	// Sa valeur de mise.
	
	public byte cartejouee;
	public boolean jouer;
	
	public ArrayList<Carte> Hand = new ArrayList<Carte>();
	
	//CONSTRUCTEUR
	/** 
	 * @param Pseudo
	 * 					Le pseudo du joueur
	 * <p>
	 * @param Score
	 * 					Son score
	 * <p>
	 * @param Position
	 * 					Son identifiant
	 */
	public Joueur(String Pseudo,short Score,byte Position){  //(Pseudo, score, position) / ex : (Aurélien, 0 , 1) = Pseudo: Aurélien,Score: 0,Joueur n°01
		this.pseudo = Pseudo;
		this.score = Score;
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
	public int getScore(){  //Affiche le score
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
	 */
	public void display() {
		int i;
		System.out.println(getPseudo() + " a dans sa main :");
		
		for(i=0; i < Hand.size(); i++) {	
			System.out.println("[" + Hand.get(i) + "]  [" + i +"]");	//Affiche en colonne dans la console.
		}
		
		System.out.println( "----END---- \n");
	}
	
	/**
	 * Trie les cartes du joueur selon le compareur de la classe Carte.
	 * @see Carte
	 */
	public void trier() {
		Collections.sort(this.Hand);
	}
	
	/**
	 * Propose les mises au joueurs.
	 */
	public int mise(){
		
		String[] Mise = {"Petite", "Garde", "Garde sans", "Garde contre"}; //Les mises possibles.
		
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
	 * Joue une carte (Pour le joueur)
	 */
	public void jouerJOUEUR() {
		
		this.jouer = false;
		
		if(Jeu.carteJouees.isEmpty() == true && this.jouer == false) { //Si c'est le premier a jouer.
			
			System.out.println("Quel carte voulez vous jouer ? \n Utilise le nombre correspondant a sa position dans ta main \n EN ATTENTE D'INTERFACE GRAPHIQUE !");
			this.cartejouee = Jeu.sc1.nextByte();			//Entrée clavier utilisateur
			System.out.println("Vous avez jouer [" + this.Hand.get(cartejouee) + "] !");
			Jeu.carteJouees.add(this.Hand.get(cartejouee));	//On ajoute la carte sur le terrain de jeu.
			this.Hand.remove(cartejouee);					//On retire la carte de la main du joueur.
			this.jouer = true;
			
		} else { // Sinon on verifie la couleur jouée.
			
			byte tentative = 0;
			
			if(tentative < 10) {
				
				while(Jeu.carteJouees.get(0).getCouleur() != this.Hand.get(cartejouee).getCouleur() && this.jouer == false) {
					
					tentative++;
					
					System.out.println("Quel carte voulez vous jouer ? \n Utilise le nombre correspondant a sa position dans ta main.");
					this.cartejouee = Jeu.sc1.nextByte();
					
					if(this.Hand.get(cartejouee).getCouleur() == Carte.ATOUT && jouer == false) { //Pour le cas ou la carte jouée est un atout. (=Couper).
						
						System.out.println("Vous avez jouer [" + this.Hand.get(cartejouee) + "] !");
						Jeu.carteJouees.add(this.Hand.get(cartejouee));			//On ajoute la carte sur le terrain de jeu.
						this.Hand.remove(cartejouee);							//On retire la carte de la main du joueur.
						jouer = true;
						
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