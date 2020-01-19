import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class SRectangle implements IShape {
	private int x, y;
	private int w, h;
	private Color color;
	private Rectangle r = new Rectangle();

	SRectangle(int _x, int _y, int _w, int _h, Color _color) {
		x = _x;
		y = _y;
		w = _w;
		h = _h;
		color = _color;
	}

	public void draw(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(color);
		gc.fillRect(x, y, w, h);
	}
}