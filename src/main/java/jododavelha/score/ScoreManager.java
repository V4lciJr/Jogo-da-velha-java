package jododavelha.score;

import java.io.IOException;

import jododavelha.core.Player;

public interface ScoreManager {
	
	public Integer getScore(Player player);
	
	public void saveScore(Player player) throws IOException;
}
