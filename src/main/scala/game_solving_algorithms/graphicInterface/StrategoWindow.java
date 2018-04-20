package game_solving_algorithms.graphicInterface;

import game_solving_algorithms.StrategoBoard;
import game_solving_algorithms.StrategoPlayer;
import game_solving_algorithms.players.RandomPlayer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scala.Tuple2;

public class StrategoWindow extends Application {
	private final static int BOARD_SIZE = 10;

	private final static int WINDOW_WIDTH = GraphicTile.SIZE * BOARD_SIZE;
	private final static int WINDOW_HEIGHT = GraphicTile.SIZE * BOARD_SIZE + 60;

	private StrategoBoard board = StrategoBoard.apply(BOARD_SIZE);
	private StrategoPlayer player1 = new RandomPlayer("Random1", board);
	private StrategoPlayer player2 = new RandomPlayer("Random2", board);

	private InfoBar infoBar;
	private GraphicTile[] tiles = new GraphicTile[BOARD_SIZE * BOARD_SIZE];

	public StrategoWindow() {
		infoBar = new InfoBar(player1.name(), player2.name());
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				tiles[i * BOARD_SIZE + j] = new GraphicTile(i, j);
			}
		}
	}

	private Parent createContent() {
		BorderPane root = new BorderPane();
		root.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		Button startButton = new Button("Start");
		startButton.setOnAction(e -> startGame());
		root.setTop(startButton);
		root.setCenter(createTileGrid());
		root.setBottom(infoBar);
		return root;
	}

	private void startGame() {
		while (!board.isEnded()) {
			performStep(player1);

			performStep(player2);
		}
	}

	private void performStep(StrategoPlayer player) {
		Tuple2<Object, Object> move1 = player.move();
		int reward1 = board.fill(move1);
		Object o = move1._1;
		int i = (int) (move1._1);
		int j = (Integer) (move1._2);
		getTile(i, j).color();
		player.addPoints(reward1);
		infoBar.updatePlayer1Points(player.score());
	}

	private Pane createTileGrid() {
		Pane tileGrid = new Pane();
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++)
				tileGrid.getChildren().add(getTile(i, j));
		return tileGrid;
	}

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(createContent());

		stage.setScene(scene);
		stage.show();
	}

	private GraphicTile getTile(int i, int j) {
		return tiles[i * BOARD_SIZE + j];
	}
}
