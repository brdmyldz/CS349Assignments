import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpaceShip {
    float posX;
    float posY;
    Image enemyImage;
    ImageView enemyImageView;

    float getPosX() { return posX; }
    float getPosY() { return posY; }
    void resetPositions() { posX = 570; posY = 840;}
    float getImageSizeX() { return (float)enemyImage.getWidth(); }
    float getImageSizeY() { return (float)enemyImage.getHeight(); }
    ImageView getImageView() { return enemyImageView; }
    void setPosX(float f) { posX = f; }

    SpaceShip() {
        enemyImage =  new Image("images/player.png", 60, 40, false, true);
        enemyImageView = new ImageView(enemyImage);
        posX = 570;
        posY = 840;
    }
}


