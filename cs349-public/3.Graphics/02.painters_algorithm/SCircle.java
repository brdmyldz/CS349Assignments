import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class SCircle implements IShape {
	private int x, y, r;
	private Color color;

	public void draw(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(color);
		gc.fillOval(x, y, r*2, r*2);
	}

	SCircle(int _x, int _y, int _r, Color _color) {
		x = _x;
		y = _y;
		r = _r;
		color = _color;
	}
}