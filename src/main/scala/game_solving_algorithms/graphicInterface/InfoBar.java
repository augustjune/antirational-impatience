package game_solving_algorithms.graphicInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class InfoBar extends HBox {
	private static final String TITLE_FORMAT = "%s score: %d";

	private Text player1Text;
	private Text player2Text;

	private String player1Info;
	private String player2Info;

	public InfoBar(String player1Name, String player2Name) {
		setupTexts(player1Name, player2Name);
		setupComponents();
	}

	private void setupTexts(String player1Name, String player2Name) {
		player1Info = player1Name + " score: ";
		player2Info = player2Name + " score: ";
		player1Text = new Text(player1Info + 0);
		player2Text = new Text(player2Info + 0);
	}

	private void setupComponents() {
		setPadding(new Insets(5, 10, 5, 10));
		setSpacing(100);
		setAlignment(Pos.CENTER);
		getChildren().add(player1Text);
		getChildren().add(player2Text);
	}

	public void updatePlayer1Points(int points) {
		player1Text.setText(player1Info + points);
	}

	public void updatePlayer2Points(int points) {
		player2Text.setText(player2Info + points);
	}
}
