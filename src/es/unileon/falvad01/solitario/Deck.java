/**
 * 
 */
package es.unileon.falvad01.solitario;

/**
 * @author falvad01
 *
 */

/**
QD AD 8H 5S 3H 5H TC 4D JH KS 6H 8S JS AC AS 8D 2H QS TS 3S AH 4H TH TD 3C 6S
8C 7D 4C 4S 7S 9H 7C 5D 2S KD 2D QH JD 6D 9D JC 2C KH 3D QC 6C 9S KC 7H 9C 5C
 */
public class Deck {

	private Card[] deck;
	private int next;

	/**
	 * 
	 */
	public Deck() {

		this.deck = new Card[52];
		this.next = 0;

	}

	public Card[] getDeck() {

		return this.deck;
	}

	/**
	 * 
	 * @param newCard
	 * @throws SolitarioExceptions
	 * 
	 *                             Metodo que se encarga de mirar a ver si las
	 *                             cartas intriducidad son correctas
	 */
	public void addCard(String newCard) throws SolitarioExceptions {

		String[] card = newCard.split("");
		Card cardObject;
		/*
		 * for (int i = 0; i < card.length; i++) {
		 * 
		 * if (i % 2 == 0) { System.out.println("Number: " + card[i]); } else {
		 * System.out.println("Palo: " + card[i]); } }
		 */

		if (card[0].equals("A") || card[0].equals("1") || card[0].equals("2") || card[0].equals("3")
				|| card[0].equals("4") || card[0].equals("5") || card[0].equals("6") || card[0].equals("7")
				|| card[0].equals("8") || card[0].equals("9") || card[0].equals("T") || card[0].equals("J")
				|| card[0].equals("Q") || card[0].equals("K")) { // Comprovamos que el numero sea
																	// el correcto

			if (card[1].equals("C") || card[1].equals("D") || card[1].equals("H") || card[1].equals("S")) { // Comprovamos
																											// que el
																											// palo sea
																											// el
																											// correcto

				cardObject = new Card(card[0], card[1]); // Creamos el objeto carta

				if (this.next == 0) {
					deck[next++] = cardObject;
				} else {

					if (lookRepeatCards(cardObject)) {

						deck[next++] = cardObject;
					} else {
						throw new SolitarioExceptions("Entrada incorrecta");
					}
				}

			} else {
				throw new SolitarioExceptions("Entrada incorrecta");
			}

		} else {
			throw new SolitarioExceptions("Entrada incorrecta");
		}

	}

	/**
	 * 
	 * @param card
	 * @throws SolitarioExceptions
	 * 
	 *                             Metodo que se encarga de comprobar si hay alguna
	 *                             carta repetida en la baraja
	 * 
	 *                             AUN NO FUNCIONA
	 */

	private boolean lookRepeatCards(Card card) throws SolitarioExceptions {

		boolean out = true;

		for (int i = 0; i < this.next; i++) {
			// System.out.println(i);

			if ((this.deck[i].getNumber().equals(card.getNumber()))
					&& (this.deck[i].getPalo().equals(card.getPalo()))) {
				out = false;
			}
		}

		return out;

	}

	public String toString() {

		StringBuilder exit = new StringBuilder();

		for (int i = 0; i < this.next; i++) {
			exit.append(deck[i]);
		}

		return exit.toString();
	}

}