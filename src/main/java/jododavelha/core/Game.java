package jododavelha.core;

import jododavelha.Constants;
import jododavelha.score.ScoreManager;
import jododavelha.ui.UI;

public class Game {
	
	private Board board = new Board();
	private Player[] players = new Player[Constants.SYMBOL_PLAYERS.length];
	private int currentPlayerIndex = -1;
	private ScoreManager scoreManager; 
	
	public void play() 
	{
		scoreManager = createScoreManager();
		UI.printGameTitle();
		
		for (int i  = 0; i < players.length; i++)
		{
			players[i] = createPlayer(i);
		}
		
		boolean gameEndend = false;
		Player currentPlayer = nextPlayer();
		Player winner = null;
		
		while(!gameEndend)
		{
			board.print();
			boolean sequenceFound;
			
			try {
				sequenceFound = currentPlayer.play();
			} catch (InvalidMoveException e) {
				UI.printText("ERRO. " + e.getMessage());
				continue;
			}
				
			if(sequenceFound)
			{
				gameEndend = true;
				winner = currentPlayer;
			}
			else if(board.isFull())
			{
				gameEndend = true;
			}
			
			currentPlayer = nextPlayer();
		}
		
		if (winner == null)
		{
			UI.printText("O jogo terminou empatado.");
		}
		else
		{
			UI.printText("O Jogador '" + winner.getName() + "' venceu o jogo!!");
			
			scoreManager.saveScore(winner);
		}
		
		board.print();
		UI.printText("Fim do Jogo");
	}
	
	private Player createPlayer(int index)
	{
		String name = UI.readInput("Jogador " + (index + 1) + " =>");
		char symbol = Constants.SYMBOL_PLAYERS[index];
		Player player = new Player(name, board, symbol);
		Integer score = scoreManager.getScore(player);
		
		if(score != null)
		{
			UI.printText("O jogador '" + player.getName() + "' possui " + score + " vit�ria(s).");
		}
		
		UI.printText("O joagador '" + name + "' vai usar o s�mbolo '" + symbol + "'");
		
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
	
	private ScoreManager createScoreManager()
	{
		// TODO retornar objeto correto
		return null;
	}
}
