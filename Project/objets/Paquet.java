package objets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <b> Deck of cards!</b>
 * <p>
 * <i> It's here that the 78 cards are create and adds to the deck </i>
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
	 * Empty list fore the cards (= deck).
	 */
	public static List<Carte> paquet = new ArrayList<Carte>();

	//METHODES
	
	/**
	 * Create the cards, with a loop.
	 * <p>
	 * <i> Classical cards and "Atouts" are separate at creation.
	 * But there are added to the same list, "paquet".</i>
	 * 
	 * @return A deck of card
	 */
	public void initialisation() {
		
		int rang; 	//Card's rank.
		int couleur; 	//Card's color.
		
		int id = 0;	//id
		// Classical card
		for(couleur = 0; couleur <=3; couleur++){ 	//for the colors from 0 to 3
			for(rang = 1; rang <= 14; rang++) {	//We increase the rank from 0 to 14
				id++;				// id + 1
				paquet.add(new Carte(rang, couleur, id)); //Add the card at the deck.
			}
		}
		//Les Atouts
		id = 56;
		for(rang = 15; rang <=36; rang++) {	//for the rank from 15 to 36
			couleur= 4;			//we keep the color "Atout"
			id++;				// id +1
			paquet.add(new Carte(rang, couleur, id)); //add to the deck.	
		}
	}
	
	/**
	 * Shuffle the cards.
	 */
	public void melanger() {
		Collections.shuffle(paquet);
	}
	
	/**
	 * Display the number of cards.
	 */
	public static void getNbCartes() {
		System.out.print("Le paquet contiens :" + paquet.size() + " cartes ! \n");
	}
	
	/**
	 * Display the deck.
	 */
	public void display() {
			System.out.println(paquet);	
	}

} //FIN CLASSE DECK d(^^*)
