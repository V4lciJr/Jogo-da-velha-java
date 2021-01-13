package jododavelha.core;

import jododavelha.Constants;
import jododavelha.ui.UI;

public class Game {
	
	private Board board = new Board();
	private Player[] players = new Player[Constants.SYMBOL_PLAYERS.length];
	private int currentPlayerIndex = 0;
	
	public void play() 
	{
		UI.printGameTitle();
		
		for (int i  = 0; i < players.length; i++)
		{
			players[i] = createPlayer(i);
		}
		
		boolean gameEndend = false;
		Player currentPlayer = nextPlayer();
		
		while(!gameEndend)
		{
			board.print();
		}
	}
	
	private Player createPlayer(int index)
	{
		String name = UI.readInput("Jogador " + (index + 1) + " =>");
		char symbol = Constants.SYMBOL_PLAYERS[index];
		Player player = new Player(name, board, symbol);
		
		UI.printText("O joagador '" + name + "' vai usar o símbolo '" + symbol + "'");
		
		return player;
		
	}
	
	private Player nextPlayer()
	{
		currentPlayerIndex++;
		
		if(currentPlayerIndex >= players.length)
		{
			currentPlayerIndex = 0;
		}
		
		return players[currentPlayerIndex];
	}
}
