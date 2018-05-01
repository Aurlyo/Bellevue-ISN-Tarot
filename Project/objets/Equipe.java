package objets;

import java.util.ArrayList;

/**
 * <b> The team ! </b>
 * <p>
 * @author Aurélien
 *
 */
public class Equipe {
	
	public ArrayList<Joueur> joueurs = new ArrayList<Joueur>(); //Team's players.
	public ArrayList<Carte> plis = new ArrayList<Carte>();     // Round win by the team.
	byte type; //Let 1 ou 0
	String nom;
	
	/**
	 * 
	 * @param Type
	 * 			0 = Defender, 
	 * 			1 = Taker
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
