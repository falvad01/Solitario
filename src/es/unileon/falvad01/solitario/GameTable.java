package es.unileon.falvad01.solitario;

/**
 * CASOS:
 * 
 * 1-MOVER A LA IZQUIERDA Y A LA CARTA(S) QUE ESTABA EN SU LUGAR HACIA ABAJO
 * 2-MOVER A LA IZQUIERDA Y A LA CARTA(S) QUE ESTABA EN SU LUGAR HACIA ABAJO Y
 * QUE LAS QUE TENIA DEBAJO HAYA QUE SUBIRLAS
 * 
 * 3-DESPUES DE MOVER LA CARTA A LA IZQUIERDA SE REPITE EL PROCESODE
 * COMPROVACION(SE PUEDE HACER RECURSIVO) SE PUEDE HACER CON FLAGS, CUANDO TODOS
 * SEAN FALSO(NO HAY ACCIONES) SE PASA A MIRAR LA CARTA SIGUIENTE
 * 
 * 
 */
public class GameTable {

	private Card[] deck;
	private Card[][] pileCards;
	private String result;
	private int posToDelete = 51;
	private boolean movedownYet = false;
	private int numVueltas = 0; // DEBUG

	public GameTable() {
		this.deck = new Card[52];
		this.pileCards = new Card[52][52];
	}

	public void addDeck(String[] newDeck) throws SolitarioExceptions {

		Deck deckObject = new Deck();

		for (int h = 0; h < deck.length; h++) {
			// System.out.println("CARD " + (h + 1) + ": " + deck[h]);
			deckObject.addCard(newDeck[h]);// Añadimos carta a carta a la baraja

		}

		deck = deckObject.getDeck();
		arrayToMatrix();
		/*
		 * for (int i = 0; i < 52; i++) {
		 * 
		 * System.out.println(deck[i]); }
		 */

	}

	public void arrayToMatrix() {

		for (int i = 0; i < deck.length; i++) {

			this.pileCards[0][i] = this.deck[i];

		}

		printMatrix();

		if (movements(1, 0, false)) {
			// TODO LLAMAR AL METODO PARA CONTAR LA MATRIZ
			System.out.println("Todo Comprovado");
		}

	}

	/**
	 * 
	 * @param posX
	 * @param posY
	 * @param endMovements
	 * 
	 *                     Metodo que compueba que movimiento debemos realizar, es
	 *                     recursivo cuando todas las comprovaciones se han
	 *                     realizado
	 * 
	 *                     (LLAMAR A ESTE METODO DESDE LOS DE MOVER PARA QUE SE
	 *                     COMPRUEBE OTRA VEZ DESPUES DE CADA MOVIMIENTO) (SOLO
	 *                     ACCEDERA AL ULTIMO RETURN CUANDO TODOS LOS MOVIMIENTOS
	 *                     DESPUES DE LOS CAMBIS SE HAYAN REALIZADO) (NO SOLO SE
	 *                     DEBEN COMPROBAR LOS DE LA CARTA EN LA QUE SE ESTA, SINO
	 *                     DESDE LA PRIMERA CARTA)
	 * 
	 */
	private boolean movements(int posX, int posY, boolean endMovements) {

		boolean checkOtherOption = true;

		if (endMovements || posX == 51) {
			return endMovements;
		} else {

			if (pileCards[0][posX] != null
					&& ((pileCards[0][posX].getNumber().equals(pileCards[0][posX - 1].getNumber()))
							|| (pileCards[0][posX].getPalo().equals(pileCards[0][posX - 1].getPalo())))) { // Miramos si
																											// la
				// inmediatamente a
				// la
				// izquierda emparejan

				System.out.println(pileCards[0][posX].toString() + " Con " + pileCards[0][posX - 1].toString());
				//moveUp(posX - 1, posY, 1);// SE ENVIA LA POSICION DE LA QUE TIENE QUE IR ABAJO
				checkOtherOption = false;// Cambiamos el falg para que no mire otras opciones

			} else if ((posX >= 3) && checkOtherOption) { // Para mirar la primera a la izquierda la primera posicion
															// debe de ser la 3 y que la de la izquierda no se realizara

				if (pileCards[0][posX] != null
						&& ((pileCards[0][posX].getNumber().equals(pileCards[0][posX - 3].getNumber()))
								|| (pileCards[0][posX].getPalo().equals(pileCards[0][posX - 3].getPalo())))) {// Miaramos
																												// si 3
					// cartas a la
					// izquierda hay
					// una que
					// empareja 

					System.out.println(
							"En 3: " + pileCards[0][posX].toString() + " Con " + pileCards[0][posX - 3].toString());
				//	moveUp(posX - 3, posY, 3);// TODO MIRAR COMENTARIOS DE moveUp
					checkOtherOption = false;// Cambiamos el falg para que no mire otras opciones

				}

			}

		}

		return movements(posX + 1, posY, endMovements);

	}

	/**
	 * TIENE PREFERENCIA MOVER ARRIBA ANTES QUE MOVER A LA IZQUIERDA TODO EL FALLO
	 * DE QUE NO EMPAREJEN BIEN LAS CARTAS PUEDE ESTAR AQUI
	 */
	private void moveUp(int posX, int posY, int postToMove) {

		if (postToMove == 1) { // Casos para cuando saltamos hacia la izquierda

			if (pileCards[posY + 1][posX + 1] == null) { // Comprobamos que en la posicion de abajo no haya nada, y
															// llamamos a moveLeft
				moveLeft(posX, posY);
			} else {
				// TODO mover a la izquierda y arriba

				moveDown(posX, posY); // Movemos abajo para dejar hueco

				System.out.println("MOVEMOS A LA IZQUIERDA Y LUEGO ARRIBA");
				pileCards[posY][posX] = pileCards[posY][posX + 1]; // En este caso solo hay que mover una carta a la izq

				for (int i = 0; i < 51; i++) {

					pileCards[i][posX + 1] = pileCards[i + 1][posX + 1]; // Subimos las que tenia debajo la que movimos
																			// a la izquierda
				}

			}

		} else if (postToMove == 3) {// Casos en los que saltamos 3 a la izquieda

			if (pileCards[posY + 1][posX + 3] == null) { // Comprobamos que en la posicion de abajo no haya nada, y
															// llamamos a moveLeft
				moveThreeLeft(posX, posY);

			} else {

				moveDown(posX, posY); // Movemos abajo para dejar hueco

				System.out.println("MOVEMOS A LA IZQUIERDA Y LUEGO ARRIBA");
				pileCards[posY][posX] = pileCards[posY][posX + 3]; // En este caso solo hay que mover una carta a la izq

				for (int i = 0; i < 51; i++) {

					pileCards[i][posX + 1] = pileCards[i + 1][posX + 1]; // Subimos las que tenia debajo la que movimos
																			// a la izquierda
				}
			}
		}

		printMatrix();
		System.out.println("Vuelta Nº" + numVueltas++);

		movements(1, 0, false); // Volvemos a comprobar si hay algun movimiento extra despues de cada movimiento

	}

	private void moveLeft(int posX, int posY) {

		if (!movedownYet) {
			
			moveDown(posX, posY);// Movemos abajo para dejar hueco
		}
		System.out.println("MOVEMOS A LA IZQUIERDA");

		// TODO MOVER LAS FILAS DE DEBAJO TAMBIEN, PARA QUE ESTE TODO EN LINEA
		// TODO INVESTIGAR COMO MOVER TODAS LAS COLUMNAS DE IZQUIERDA A DERECHA

		// for (int j = posY; j < 51; j++) {
		for (int i = posX; i < 51; i++) {

			pileCards[posY][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja

		}
		// }

		pileCards[posY][posToDelete--] = null; // Eliminamos la carta que acabamos de mover de su antigua posicion
	}

	private void moveThreeLeft(int posX, int posY) {

		System.out.println("MOVER 3 A LA IZQUIERDA");
		moveDown(posX, posY);

		pileCards[posY][posX] = pileCards[posY][posX + 3]; // En este caso solo hay que mover una carta a la izq

		moveLeft(posX + 3, posY);

	}

	private void moveDown(int posX, int posY) {

		System.out.println("MOVEMO ABAJO");
		// System.out.println(pileCards[posY][posX].toString());

		for (int i = 51; i >= 0; i--) {

			if (i < 51) {
				pileCards[i + 1][posX] = pileCards[i][posX];
			}
		}
		movedownYet = true;
		printMatrix();

	}

	private void calculateResult() {

	}

	public void printMatrix() {

		/**
		 * DEBUG
		 */
		for (int x = 0; x < pileCards.length; x++) {
			System.out.print("|");
			for (int y = 0; y < pileCards[x].length; y++) {

				System.out.print(pileCards[x][y]);

				if (y != pileCards[x].length - 1) {
					System.out.print("\t");

				}
			}
			System.out.println("|");
		}

		/**
		 * FIN DEBUG
		 */

	}

	public String toString() {

		return "";
	}

}
