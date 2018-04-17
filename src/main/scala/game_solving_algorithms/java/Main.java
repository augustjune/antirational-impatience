/*
package game_solving_algorithms.java;

import game_solving_algorithms.StrategoPlayer;
import game_solving_algorithms.java.Table;
import game_solving_algorithms.players.GreedyRandomPlayer;

public class Main {
	public static void main(String[] args) throws Exception {
//		Table table = new Table(4);
//		table.print();
//		table.color(0, 0);
//		table.color(1, 2);
//		table.color(0, 2);
//		table.color(0, 3);
//		table.color(2, 3);
//		table.print();
//		System.out.println(table.calculateReward(0, 1));
		play(10, 1000);
	}

	private static void play(int tableSize, int hazard) throws InterruptedException {
		Table table = new Table(tableSize);
		StrategoPlayer player1 = new GreedyRandomPlayer("", table);
		StrategoPlayer player2 = new GreedyRandomPlayer(table);
		while (!table.isEnded()) {
			player1.performStep();
			table.print();
			System.out.println("Player 1 score: " + player1.score());
			Thread.sleep(hazard);
			player2.performStep();
			table.print();
			System.out.println("Player 2 score: " + player2.score());
			Thread.sleep(hazard);
		}

	}
}
*/
