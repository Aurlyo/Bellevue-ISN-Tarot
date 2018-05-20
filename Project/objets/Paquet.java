package objets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <b>Le paquet de cartes !</b>
 * <p>
 * <i>C'est ici que les 78 cartes sont créer pour être intégrer
 * dans le paquet de jeu.</i>
 * </p>
 * 
 * @see Carte
 * 
 * @author Aurélien
 * @version 0.1
 * 
 */
public class Paquet {

	//ATTRIBUTS
	
	/**
	 * Liste vide pour les cartes.
	 */
	public static List<Carte> paquet = new ArrayList<Carte>(); //Création d'une liste vide pour "acceuillir" les cartes.

	//METHODES
	
	/**
	 * Création des cartes, via une boucle.
	 * <p>
	 * <i>Les cartes classique et les atouts sont séparer lors de la créations.
	 * Mais elles sont attribuer à la même liste, "paquet".</i>
	 * 
	 * @return Une instance du paquet de cartes
	 */
	public void initialisation() { //Création des cartes
		
		int rang; //Le rang de la carte créer.
		int couleur; //La couleur de la carte créer.
		
		int id = 0;
		//Carte "classiques"
		for(couleur = 0; couleur <=3; couleur++){ //Pour les couleur allant de 0 à 3
			for(rang = 1; rang <= 14; rang++) {	  //On augmente le rang de 0 à 14
				id++;
				paquet.add(new Carte(rang, couleur, id)); //Ajoute la carte créer au paquet.
			}
		}
		//Les Atouts
		id = 56;
		for(rang = 15; rang <=36; rang++) {	//Pour le rang allant de 15 à 36
			couleur= 4;			//On garde la couleur "Atout"
			id++;
			paquet.add(new Carte(rang, couleur, id)); //Ajoute la carte créer au paquet.	
		}
	}
	
	/**
	 * Mélange les cartes.
	 */
	public void melanger() {
		Collections.shuffle(paquet);
	}
	
	/**
	 * Affiche le nombre de carte contenue dans le paquet.
	 */
	public static void getNbCartes() {
		System.out.print("Le paquet contiens :" + paquet.size() + " cartes ! \n");
	}
	
	/**
	 * Affiche les cartes du paquet.
	 */
	public void display() {
			System.out.println(paquet);	
	}

} //FIN CLASSE DECK d(^^*)
