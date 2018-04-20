package game_solving_algorithms.graphicInterface;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GraphicTile extends StackPane {
	public  static final int SIZE = 40;

	private static final Color EMPTY_COLOR = Color.LIGHTGREY;
	private static final Color FILL_COLOR = Color.ALICEBLUE;
	private static final Color STROKE_COLOR = Color.DARKGREY;

	private int x, y;
	private boolean isColored;

	private Rectangle rect = new Rectangle(SIZE - 2, SIZE - 2);

	public GraphicTile(int x, int y) {
		this.x = x;
		this.y = y;
		initializeRectangle();
	}

	private void initializeRectangle() {
		rect.setStroke(STROKE_COLOR);
		rect.setFill(EMPTY_COLOR);

		getChildren().addAll(rect);

		setTranslateX(x * SIZE);
		setTranslateY(y * SIZE);

		setOnMouseClicked(e -> color());
	}

	public void color() {
		if (!isColored) {
			isColored = true;
			rect.setFill(FILL_COLOR);
		}
	}
}
