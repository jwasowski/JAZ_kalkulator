package pl.wasowski.breakthrough.game.player;


import java.util.Scanner;

import pl.wasowski.breakthrough.game.GameBoard;

public class HumanPlayer extends Player { // Rozszerza Gracza, tote¿ dziedziczy

	public HumanPlayer(PlayerColour colour, GameBoard board) { // Konstruktor z dziedziczeniem
		super(colour, board);
	}

	@Override
	public boolean makeMove() {	// Metoda wykonywania ruchu przez Gracza

		Scanner in = new Scanner(System.in);

		System.out.println("Wybierz pionek");
		String originalCoordinate = in.next();	// Wybranie pionka przez Gracza
		
		System.out.println("Wybierz pole docelowe");
		String destinationCoordinate = in.next();
		//in.close();
		System.out.println("Twoj ruch to:");
		System.out.println("Z pola " + originalCoordinate + " na pole " + destinationCoordinate);
		
		if (originalCoordinate.length() == 2 && destinationCoordinate.length() == 2) { // Sprawdzenie czy wspl skladaja sie z 2 znakow
			return board.makeMove(originalCoordinate, destinationCoordinate, colour);
		} else {
			return false;
		}

	}

}
