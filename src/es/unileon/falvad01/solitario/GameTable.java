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
 * FALLO EN ITER 54-55, En 3: TS Con TH [TS SOLO SE MUEVE 1, BORRANDO JH Y DUPLICANDO TH]
 */
public class GameTable {

	private Card[] deck;
	private Card[][] pileCards;
	int[] resultado;
	private int posToDelete = 51;
	private boolean movedownYet = false;
	private int numVueltas = 0; // DEBUG

	public GameTable() {
		this.deck = new Card[52];
		this.pileCards = new Card[52][52];
		this.resultado = new int[52];
	}
	
	public int[] getResultado() {
		return this.resultado;
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
		System.out.println("PRIMERA MATRIZ");
		printMatrix();
		System.out.println("PRIMERA MATRIZ");

		if (movements(1, 0, false)) {
			calculateResult();
			//System.out.println("Todo Comprovado");
			
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

		if ( posX == 51) {
			return true;
		} else {

			if (pileCards[0][posX] != null
					&& ((pileCards[0][posX].getNumber().equals(pileCards[0][posX - 1].getNumber()))
							|| (pileCards[0][posX].getPalo().equals(pileCards[0][posX - 1].getPalo())))) { // Miramos si
																											// la
				// inmediatamente a
				// la
				// izquierda emparejan

				System.out.println(pileCards[0][posX].toString() + " Con " + pileCards[0][posX - 1].toString());
				checkMoves(posX - 1, posY, 1);// Enviamos la posicion destino
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

					System.out.println("En 3: " + pileCards[0][posX].toString() + " Con " + pileCards[0][posX - 3].toString());
					checkMoves(posX - 3, posY, 3); // Enviamos la posicion destino
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

	/**
	 * 
	 * @param posX
	 * @param posY
	 * @param postToMove
	 * 
	 * 
	 *                   Comprovamos si tenemos que mover izquierda arriba o solo
	 *                   izquierda
	 */
	private void checkMoves(int posX, int posY, int postToMove) {

		//System.out.println(numVueltas++);

		if (postToMove == 1) { // Si hay que moverse solo una posicion habria que moverse: 1-Abajo la carta que
								// esta em la posicion destino 2-todo a la izquierda

			if (pileCards[posY + 1][posX + 1] != null) { // Miramos si debajo de la carta que vamos a mover hay alguna
															// carta, en caso de ser asi se sustituye le mover a la
															// izquierda por mover arriba todas las cartas que esten
															// debajo de el

				moveDown(posX, posY);
				moveLeft(posX + 1, posY);
				moveUp(posX + 1, posY);

			} else {

				moveDown(posX, posY);
				moveLeft(posX + 1, posY);
				moveAllLeft(posX + 1, posY);
				pileCards[posY][posToDelete--] = null; // Eliminamos la carta que acabamos de mover de su antigua
														// posicion

			}

		} else if (postToMove == 3) {

			if (pileCards[posY + 1][posX + 3] != null) { // Miramos si debajo de la carta que vamos a mover hay alguna
				// carta, en caso de ser asi se sustituye le mover a la
				// izquierda por mover arriba todas las cartas que esten
				// debajo de el

				moveDown(posX, posY);
				moveThreeLeft(posX + 3, posY);
				moveUp(posX + 3, posY);

			} else {

				moveDown(posX, posY);
				moveThreeLeft(posX + 3, posY);
				moveAllLeft(posX + 3, posY);
				pileCards[posY][posToDelete--] = null; // Eliminamos la carta que acabamos de mover de su antigua
														// posicion

			}

		}
		System.out.println("Iter Nº" +  numVueltas++);
		printMatrix();
		System.out.println("ESTO ES UN ESPACIO\n");
		movements(1, 0, false);// volvemos a mirar todos los movimientos desde 0

	}

	/**
	 * 
	 * @param posX
	 * @param posY
	 * 
	 *             Movemos todo a la izquieda desde la posicion dada
	 */

	private void moveAllLeft(int posX, int posY) {

		for (int j = 0; j < 51; j++) {
			for (int i = posX; i < 51; i++) {
				pileCards[j][i] = pileCards[j][i + 1]; // Movemos a la izquierda toda la baraja
			}
		}

	}

	/**
	 * 
	 * @param posX
	 * @param posY
	 * 
	 *             Movemos solo UNA carta a la izquierda
	 */

	private void moveLeft(int posX, int posY) {

		pileCards[posY][posX - 1] = pileCards[posY][posX];

	}

	/**
	 * 
	 * @param posX
	 * @param posY
	 * 
	 *             Movemos solo UNA carta 3 posiciones a la izquierda, eso en todas
	 *             las columnas
	 */
	private void moveThreeLeft(int posX, int posY) {

		pileCards[posY][posX - 3] = pileCards[posY][posX];

	}

	/**
	 * 
	 * @param posX
	 * @param posY
	 * 
	 *             Movemos las cartas abajo para dejar hueco a la que se va a mover
	 */
	private void moveDown(int posX, int posY) {

		for (int i = 51; i >= 0; i--) {
			if (i < 51) {
				pileCards[i + 1][posX] = pileCards[i][posX];
			}
		}

	}

	/**
	 * 
	 * @param posX
	 * @param posY
	 * 
	 *             En caso de que tengamos una carta con cartas debajo, en vez de
	 *             mover a la izquierda, tenemos que subir las cartas que hay debajo
	 */
	private void moveUp(int posX, int posY) {

		for (int i = 0; i < 51; i++) {
			pileCards[i][posX] = pileCards[i + 1][posX]; // Subimos las que tenia debajo la que movimos
															// a la izquierda
		}
	}

	private void calculateResult() {

		
		
		
		for (int i = 0; i < 52; i++) {
			for(int j = 0; j< 52;j++) {
				
				if(pileCards[i][j] != null) {
					resultado[j]++;
				}
				
			}
			/*
			for(int k = 0; k < 51; k++) {
				System.out.println(resultado[k]);
			}
*/
		}

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