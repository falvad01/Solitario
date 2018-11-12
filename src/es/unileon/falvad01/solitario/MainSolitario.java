package es.unileon.falvad01.solitario;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * 
 * @author Francisco Javier Alvarez de Celis
 *
 */

public class MainSolitario {

	public static void main(String[] args){

		ArrayList<String> decks = new ArrayList<String>();
		String[] buffer = new String[2];
		StringBuffer subBuffer = new StringBuffer();
		String preDeck;
		String[] deck = new String[52];
		StringBuffer result = new StringBuffer();

		GameTable table = new GameTable();

		float cardNum = 0;
		float deckNum = 0;
		System.out.println("COMENCEMOS");
		Scanner sc = new Scanner(System.in);

		try {
			
		while (sc.hasNext("#") == false) { // Scanner hasta que introducimos el simbolo #

			decks.add(sc.nextLine());

		}
		
		sc.close();
		deckNum = decks.size() / 2.0f;
		System.out.println("DECKS: " + decks);

		for (int i = 0; i <= deckNum; i += 2) {// BUCLE PRINCIPAL, DA TANTAS VUELTAS COMO MAZOS DE CARTAS
			System.out.println("Vueltas" + i);
			buffer = Stream.of(decks.get(i), decks.get(i + 1)).flatMap(Stream::of).toArray(String[]::new);// Juntamos la
																											// baraja en
																											// un mismo
																											// array

			for (int j = 0; j < buffer.length; j++) {

				subBuffer.append(buffer[j]); // Trasformamos el array en stringBuffer
				subBuffer.append(" ");
			}

			preDeck = subBuffer.toString();// Tranformamos el stringBuffer en string

			deck = preDeck.split(" ");// Metemos cada carta en un nueva posicion del array

			cardNum = deck.length;
			System.out.println("Decks: " + deckNum + ";" + "Cards per deck: " + cardNum);

			table.addDeck(deck); //LLamamos al tablero de juego
			
			table.printMatrix();

			subBuffer.delete(0, subBuffer.length()); // Borramos el buffer del StringBUffer
		}
		
		}catch (SolitarioExceptions e) {
			System.out.println(e.getMessage());
		
		}

	}

}

/*
 * 
 * pileCards[posY][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+1][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+2][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+3][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+4][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+5][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+6][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+7][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+8][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+9][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+10][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+11][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+12][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+13][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+14][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+15][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+16][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+17][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+18][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+19][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+20][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+21][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+22][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+23][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+24][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+25][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+26][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+27][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+28][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+29][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+30][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+31][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+32][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+33][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+34][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+35][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+36][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+37][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+38][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+39][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+40][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+41][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+42][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+43][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+44][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+45][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+46][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+47][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+48][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+49][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+50][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
			pileCards[posY+51][i] = pileCards[posY][i + 1]; // Movemos a la izquierda toda la baraja
 * 
 * 
 */
