package objets;

import java.util.ArrayList;

/**
 * <b> Les équipes ! </b>
 * <p>
 * @author Aurélien
 *
 */
public class Equipe {
	
	public ArrayList<Joueur> joueurs = new ArrayList<Joueur>(); //Les joueurs de l'équipe.
	public ArrayList<Carte> plis = new ArrayList<Carte>();      //Les plis gagnés de l'équipe.
	public double score = 0.0;
	byte type; //Soit 1 ou 0
	String nom;
	
	/**
	 * 
	 * @param Type
	 * 			0 = Defenseur, 
	 * 			1 = Preneur
	 */
	public Equipe(byte Type) {
		this.type = Type;
	}
	
	public String nom() {
		switch (type) {
		
		case 0: return "Defenseur";
		case 1: return "Preneur";
		default: return "Euh... Erreur ?!";
		}
	}
	
	public String getNom() {
		return nom;
	}
	
	public ArrayList<Joueur> getEquipe() {
		return joueurs;
	}
	
	public ArrayList<Carte> getPlis() {
		return plis;
	}
}
