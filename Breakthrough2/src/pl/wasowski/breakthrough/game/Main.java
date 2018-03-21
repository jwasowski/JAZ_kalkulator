package pl.wasowski.breakthrough.game;

import pl.wasowski.breakthrough.game.player.AgressiveComputerPlayer;
import pl.wasowski.breakthrough.game.player.HumanPlayer;
import pl.wasowski.breakthrough.game.player.Player;
import pl.wasowski.breakthrough.game.player.PlayerColour;
import pl.wasowski.breakthrough.game.player.RandomComputerPlayer;

public class Main {

	public static void main(String[] args) {
		String currentPlayer = "";		// Zmienna do okreslania czyja jest tura
		GameBoard board = new GameBoard();		// Tworzymy obiekt plansza
		Player player1 = new AgressiveComputerPlayer(PlayerColour.WHITE, board);		// Tworzymy gracza 1 (wedlug specyfiki gry jest on bialy)
		Player player2 = new AgressiveComputerPlayer(PlayerColour.BLACK, board);	// Tworzymy gracza 2 (czarny wg. specyfiki)
		board.printGameBoard();		// Drukujemy plansze w konsoli
		while (!board.thereIsAVictor()) { // Petla odpowiedzialna za rozgrywke (modelujaca przebieg rozgrywki)
			currentPlayer = "WHITE";
			while (!player1.makeMove()) {	// Petla odpowiedzialna za ruch gracza 1
				board.printGameBoard();
			}
			if (board.thereIsAVictor())		// Warunek sprawdzajacy czy gracz 1 po wykonaniu ruchu wygral
				break;
			board.printGameBoard();
			currentPlayer = "BLACK";
			while (!player2.makeMove()) {	// Petla odpowiedzialna za ruch gracza 2
				board.printGameBoard();
			}
			board.printGameBoard();
		}
		board.printGameBoard();
		System.out.println("GRATULACJE!, " + currentPlayer + " WYGRAL!");
	}

}
