package objets;  //Appartennance au package "objets".
 
/**
  *<b> Les cartes !</b>
  *<P>
  *Elle contient :
  *<ul>
  *<li> Les attributs de couleurs
  *<li> Les attributs de rangs
  *<li> Un identifiant (id) unique a chaque carte.
  *<li> Deux image ,une identique pour le dos, et une unique pour la face avant.
  *</ul>
  *</p>
  * 
  * @since 0.5
  * 			<i> Comparateur ( et valeur integer) </i>
  * @author Aurélien
  * @version 0.5
  */

public class Carte   						//Création d'une class "Carte"
implements Comparable<Carte>				//Implémentation d'un comparateur
{
	//ATTRIBUTS
	
	/* Les couleurs */
	public final static int PIQUE = 0;
	public final static int TREFLE = 1;
	public final static int COEUR = 2;
	public final static int CARREAU = 3;
	public final static int ATOUT = 4;
	
	/* Les rangs (=Carte "spéciale") */
	public final static int VALET = 11;
	public final static int CAVALIER = 12;
	public final static int DAME = 13;
	public final static int ROI = 14;
	public final static int PETIT = 15;
	public final static int EXCUSE = 36;
	
	/* variables (=non modifiable une fois la carte créer) */
	// Integer a cause du comparareur
	public Integer couleur;
	public Integer rang;
	public Integer id;
	
	/**
	 * Le constructeur, permet de créer une carte.
	 * <p>
	 * @param Rang
	 * 				Le rang de la carte
	 * <p>
	 * @param Couleur
	 * 				La couleur de la carte
	 * <p>
	 * @param Id
	 * 				L'identifiant de la carte
	 */
	public Carte(int Rang, int Couleur, int Id) {
		
	if (Couleur != PIQUE && Couleur != COEUR && Couleur != CARREAU &&  //Vérifie la couleur de la carte.
			Couleur != TREFLE && Couleur != ATOUT) {
		
	 throw new IllegalArgumentException("Erreur: couleur impossible !"); //indique que cette couleur n'est pas possible.
	}
	
	if (Rang < 1 || Rang > 36) {										//Vérifie le rang de la carte.
		
	 throw new IllegalArgumentException("Erreur: rang impossible !"); //indique que ce rang n'est pas possible.
	}
	
	rang = Rang;
	couleur = Couleur;
	id = Id;
	
	}
	
	//METHODES\\
	
	/**
	 * @return La couleur de la carte. (int)
	 */
	public int getCouleur(){ //Indique la couleur de la carte
		return couleur;	//renvois la couleur
	}
	
	/**
	 * @return Le rang de la carte. (int)
	 */
	public int getRang(){	//Indique le rang de la carte
		return rang;	//renvois le rang
	}
	
	/**
	 * 
	 * @return L'id (unique) de la carte. (int)
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Nomme les couleurs.
	 * 
	 * @return La couleur de la carte. (String)
	 */
	public String getCouleurNom() {
			
	switch ( couleur ) {					//Evite les répétitions de If(...) Else(...).
	// Exemple : Si couleur = 1, alors getCouleurNom() afficheras "de Pique" .
    // De même pour couleur = 2, getcouleurNom() = "de Trefle".
	
	case PIQUE:   return " de Pique";
	case TREFLE:    return " de Trefle";
	case COEUR:   return " de Coeur";
	case CARREAU: return " de Carreau";
	case ATOUT: return " d'Atout";
	
	default: return "ERROR"; // Valeur par défaut, ici cela correspondrait a une erreur lors de la création des cartes.
      }
   }
    
	/**
	 * Nomme les rangs.
	 * <p>
	 * <i> Les rangs des cartes classique sont compris de 1 à 14,
	 * alors que les atouts sont de 15 à 36. </i>
	 * </p>
	 * @return Le rang de la carte. (String)
	 */
	public String getRangNom() {
		
         switch ( rang ) {			//Evite les répétitions de If(...) Else(...).
         // Exemple : Si rang = 1, alors getRangNom() afficheras "As" .
         // De même pour rang = 2, getRangNom() = "2".
         
         case 1:   return "As";
         case 2:   return "2";
         case 3:   return "3";
         case 4:   return "4";
         case 5:   return "5";
         case 6:   return "6";
         case 7:   return "7";
         case 8:   return "8";
         case 9:   return "9";
         case 10:  return "10";
         case 11:  return "Valet";
         case 12:  return "Cavalier";
         case 13:  return "Dame";
         case 14:  return "Roi";
         //Atouts
         case 15:  return "Petit";
         case 16:   return "Deux";
         case 17:   return "Trois";
         case 18:   return "Quatre";
         case 19:   return "Cinq";
         case 20:   return "Six";
         case 21:   return "Sept";
         case 22:   return "Huit";
         case 23:   return "Neuf";
         case 24:   return "Dix";
         case 25:  return "Onze";
         case 26:  return "Douze";
         case 27:  return "Treize";
         case 28:  return "Quatorze";
         case 29:  return "Quinze";
         case 30:  return "Seize";
         case 31:   return "Dix_sept";
         case 32:  return "Dix_huit";
         case 33:  return "Dix_neuf";
         case 34:  return "Vingt";
         case 35:  return "Vingt_et_un";
         case 36:  return "Excuse";
         
         default: return "ERROR"; // Valeur par défaut, ici cela correspondrait a une erreur lors de la création des cartes.
      }
    }
   
	/**
	 * Permet de nommer les cartes
	 * 
	 * @return Le nom de la carte (String)
	 */
	@Override
	public String toString() { //Nomme les cartes (rang+nom), utilisation du toString (=Reconnu par JAVA)
		return getRangNom() + getCouleurNom();
	}
	
	/**
	 * Méthode pour comparer les cartes
	 * par couleur puis par rang puis par id
	 * 
	 * WIP !!!
	 */
	@Override
	public int compareTo(Carte carte2) {

		//Comparaison couleur
		int compCouleur = this.couleur.compareTo(carte2.couleur);
		if(compCouleur != 0) {return compCouleur;}
				
		//Comparaison rang
		int compRang = this.rang.compareTo(carte2.rang);
		if(compRang != 0) {return compRang;}
		
		//Comparaison id
		int compId = this.id.compareTo(carte2.id);
		if(compId != 0) {return compId;}
	
		return 0;
	}
} //FIN CLASSE CARTE d(^^*)