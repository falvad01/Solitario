package es.unileon.falvad01.solitario;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * 
 * @author falvad01
 *
 */

public class MainSolitario {

	public static void main(String[] args) {

		ArrayList<String> decks = new ArrayList<String>();
		String[] buffer = new String[2];
		StringBuffer subBuffer = new StringBuffer();
		String preDeck;
		String[] deck = new String[52];

		int[] result = new int[52];
		int numPiles = 0;

		GameTable table = new GameTable();

		float deckNum = 0;
		Scanner sc = new Scanner(System.in);

		try {

			while (sc.hasNext("#") == false) { // Scanner hasta que introducimos el simbolo #

				decks.add(sc.nextLine());

			}

			sc.close();
			long tiempoInit = System.currentTimeMillis();
			deckNum = decks.size() / 2.0f;
			String check[][] = new String[(int) deckNum][52]; // TODO NUEVO
			int h = 0;

			for (int i = 0; i <= deckNum * 2 - 1; i += 2) {// BUCLE PRINCIPAL, DA TANTAS VUELTAS COMO MAZOS DE CARTAS

				buffer = Stream.of(decks.get(i), decks.get(i + 1)).flatMap(Stream::of).toArray(String[]::new);// Juntamos
																												// la
																												// baraja
																												// en
																												// un
																												// mismo
																												// array

				for (int j = 0; j < buffer.length; j++) {

					subBuffer.append(buffer[j]); // Trasformamos el array en stringBuffer
					subBuffer.append(" ");
				}

				preDeck = subBuffer.toString();// Tranformamos el stringBuffer en string

				deck = preDeck.split(" ");// Metemos cada carta en un nueva posicion del array

				Deck deckObject = new Deck();

				for (int j = 0; j < 52; j++) {
					deckObject.addCard(deck[j]);
					check[h][j] = deck[j];

				}
				h++;

				subBuffer.delete(0, subBuffer.length()); // Borramos el buffer del StringBUffer

			}

			//////////////////////////////
			int g = 0;
			String[] deckk = new String[52];
			for (int i = 0; i <= deckNum * 2 - 1; i += 2) {

				for (int j = 0; j < 52; j++) {
					deckk[j] = check[g][j];
				}
				g++;

				table.addDeck(deckk); // LLamamos al tablero de juego

				result = table.getResultado();

				for (int k = 0; k < 52; k++) {

					if (result[k] > 0) {

						numPiles++;
					}
				}

				if (numPiles == 1) {
					System.out.print("Ha quedado " + numPiles + " pila:");
				} else {

					System.out.print("Han quedado " + numPiles + " pilas:");
				}
				for (int k = 0; k < 52; k++) {

					if (result[k] > 0) {

						System.out.print(" " + result[k]);
					}

				}

				System.out.println();

				for (int k = 0; k < 52; k++) {
					result[k] = 0; // Limpiamos el array de resultado
				}

				numPiles = 0; // Reiniciamos el numero de pilas
			}

			////////////////////////
			long f = System.currentTimeMillis();
			long finall = f - tiempoInit;

			System.out.println(finall);

		} catch (SolitarioExceptions e) {
			System.out.println(e.getMessage());

		}

	}

}