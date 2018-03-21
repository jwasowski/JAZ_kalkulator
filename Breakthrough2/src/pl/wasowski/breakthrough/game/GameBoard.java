package pl.wasowski.breakthrough.game;

import java.util.HashMap;
import java.util.Map;

import pl.wasowski.breakthrough.game.player.PlayerColour;

public class GameBoard {

	public static final String A2H = "ABCDEFGH";		// Jednorazowe (w pamieci) zadeklarowanie stalych dla klasy
	public static final String ONE2EIGHT = "87654321";
	private static final String WHITE_PIECE = "W";
	private static final String BLACK_PIECE = "B";
	private static final String EMPTY_TILE = ".";

	private HashMap<String, Tile> board = new HashMap<>();		// Stworzenie obiektu planszy mapa (klucz , wartosc)

	GameBoard() {		// Konstruktor klasy wypelniajacy wspol. odpowiednimi wartosciami
		for (int i = 0; i < ONE2EIGHT.length(); i++) {
			for (int j = 0; j < A2H.length(); j++) {
				String coordinates = getCoordinates(i, j); // Pobieramy elementy stringu np. 8 i A, funkcja przetwarza w A8
				if (coordinates.contains("7") || coordinates.contains("8")) // Jezeli jest to rzad 8 lub 7 to wypelniamy czarnymi symbolami
					board.put(coordinates, new Tile(BLACK_PIECE));
				else if (coordinates.contains("1") || coordinates.contains("2")) // // Jezeli jest to rzad 1 lub 2 to wypelniamy bialymi
					board.put(coordinates, new Tile(WHITE_PIECE));
				else
					board.put(coordinates, new Tile(EMPTY_TILE));	// Reszta wypelniona symbolami pol pustych
			}
		}
	}

	void printGameBoard() {		// Metoda interpretacji konsolowej planszy
		System.out.println();
		System.out.println("   A B C D E F G H");
		System.out.println("  ----------------");
		for (int i = 0; i < ONE2EIGHT.length(); i++) {
			System.out.print(8 - i + " |");
			for (int j = 0; j < A2H.length(); j++) {
				String coordinates = getCoordinates(i, j);	// Przetworzenie wspolrzednych
				System.out.print(board.get(coordinates).getContent());// Drukowanie zawartosci danej wspolrzednej planszy (mapy) po przetworzeniu
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	public Map<String, Tile> getGameBoard() { // Metoda implementacji zwracajaca obiekt plansza
		return board;
	}

	public boolean makeMove(String originalCoordinates, String newCoordinates, PlayerColour colour) { // Funkcja ruchu na planszy
		if (isValidMoveForPlayer(originalCoordinates, newCoordinates, colour)) { // Sprawdzenie poprawnosci ruchu
			Tile original = board.get(originalCoordinates);
			Tile newPosition = board.get(newCoordinates);
			newPosition.setContent(original.getContent()); // Ustawiamy zawartosc oryginalu na nowej wspl.
			original.setContent(EMPTY_TILE);
			return true;
		}
		return false;

	}

	public boolean isValidMoveForPlayer(String originalCoordinates, String newCoordinates, PlayerColour colour) { // Spr czy ruch jest poprawny
		Tile newPosition = board.get(newCoordinates);

		System.out.println("Sprawdzam dla pol: " + originalCoordinates + " -> " + newCoordinates);

		if (colour.equals(PlayerColour.BLACK)) {	// Sprawdzamy dla Czarnego
			if (!ONE2EIGHT.contains(String.valueOf(newCoordinates.charAt(1))) // Sprawdzenie czy wspl. nie sa spoza zakresu mapy
					|| !A2H.contains(String.valueOf(newCoordinates.charAt(0)))) {
				System.out.println("black outside of gaming area.");
				return false;
			}
			if (newCoordinates.charAt(1) - originalCoordinates.charAt(1) != -1) { // Sprawdzenie czy porusza sie o 1 rzad
				System.out.println("black trying to move too far.");
				return false;
			}
			if (newPosition.getContent().equals(BLACK_PIECE)) {		// Sprawdzenie czy wspl nie zawiera swojego pionka
				System.out.println("black, dont kill yourself.");
				return false;
			}
			if (newPosition.getContent().equals(WHITE_PIECE) // Sprawdzenie czy nie chce bic do przodu w tej samej lini
					&& newCoordinates.charAt(0) - originalCoordinates.charAt(0) == 0) {
				System.out.println("black cant move straight to kill.");
				return false;
			}
			if (Math.abs(newCoordinates.charAt(0) - originalCoordinates.charAt(0)) > 1) { // Sprawdzenie zmiany linii wg. specyfiki gry
				System.out.println("black illegal move.");
				return false;
			}
		} else {		// Sprawdzenie dla Bialego
			if (!ONE2EIGHT.contains(String.valueOf(newCoordinates.charAt(1))) // Sprawdzenie czy wspl. nie sa spoza zakresu mapy
					|| !A2H.contains(String.valueOf(newCoordinates.charAt(0)))) {
				System.out.println("white outside of gaming area.");
				return false;
			}
			if (newCoordinates.charAt(1) - originalCoordinates.charAt(1) != 1) { // Sprawdzenie czy porusza sie o 1 rzad
				System.out.println("white trying to move too far.");
				return false;
			}
			if (newPosition.getContent().equals(WHITE_PIECE)) {		// Sprawdzenie czy wspl nie zawiera swojego pionka
				System.out.println("white, dont kill yourself.");
				return false;
			}
			if (newPosition.getContent().equals(BLACK_PIECE)	
					&& newCoordinates.charAt(0) - originalCoordinates.charAt(0) == 0) {	// Sprawdzenie czy nie chce bic do przodu w tej samej lini
				System.out.println("white cant move straight to kill.");
				return false;
			}
			if (Math.abs(newCoordinates.charAt(0) - originalCoordinates.charAt(0)) > 1) {	// Sprawdzenie zmiany linii wg. specyfiki gry
				System.out.println("illegal move.");
				return false;
			}
		}
		return true;
	}

	public String getCoordinates(int i, int j) {		// Metoda tworzaca kordynaty
		String number = String.valueOf(ONE2EIGHT.charAt(i)); // Pobiera wartosc stringa w danym miejscu
		String letter = String.valueOf(A2H.charAt(j));	// Pobiera wartosc stringa w danym miejscu
		String coordinates = String.valueOf(letter + number);	// Tworzy zlepek dwoch wartosci np. A1 - nowy string
		return coordinates;
	}

	public boolean thereIsAVictor() {		// Metoda sprawdza czy ktos wygral
		for (int i = 0; i < A2H.length(); i++) {
			if (board.get(getCoordinates(0, i)).getContent().equals("W")) { // Sprawdzamy czy na wspl 8(A,B..) jest bialy
				return true;
			} else if (board.get(getCoordinates(7, i)).getContent().equals("B")) { // Sprawdzamy czy na wspl 1(A,B..) jest czarny
				return true;
			}
		}

		boolean isThereWhiteTileFound = false;
		boolean isThereBlackTileFound = false;
		for (int i = 0; i < A2H.length(); i++) {		// Sprawdzamy czy na planszy sa pionki biale i czarne
			for (int j = 0; j < ONE2EIGHT.length(); j++) {
				if (isThereWhiteTileFound && isThereBlackTileFound) {
					return false;
				} else {
					if (board.get(getCoordinates(i, j)).getContent().equals("W")) {
						isThereWhiteTileFound = true;
					} else if (board.get(getCoordinates(i, j)).getContent().equals("B")) {
						isThereBlackTileFound = true;
					}
				}
			}
		}
		return true;
	}
}
