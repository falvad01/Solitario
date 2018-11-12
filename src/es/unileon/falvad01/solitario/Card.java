/**
 * 
 */
package es.unileon.falvad01.solitario;

/**
 * @author javi1
 *
 */
public class Card {
	
	/**
	 * Atributo del numero de la carta
	 */
	public String number;
	
	/**
	 * Atrinuto del palo de la carta
	 */
	public String palo;

	/**
	 * 
	 * @param number
	 * @param palo
	 * 
	 *               Constructor de la clase
	 */
	public Card(String number, String palo) {

		setNumber(number);
		setPalo(palo);

	}

	/**
	 * 
	 * @param carta
	 * 
	 *              Constructor de copia
	 */
	public Card(Card carta) {

		this.number = carta.getNumber();
		this.palo = carta.getPalo();

	}

	/**
	 * 
	 * @return
	 */
	public String getNumber() {
		return this.number;
	}

	/**
	 * 
	 * @param number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * 
	 * @return
	 */
	public String getPalo() {
		return this.palo;
	}

	/**
	 * 
	 * @param palo
	 */
	public void setPalo(String palo) {
		this.palo = palo;
	}

	/**
	 * 
	 */
	public String toString() {

		return getNumber() + getPalo();
	}

}
