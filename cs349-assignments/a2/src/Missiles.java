import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//base class
abstract class Missiles {
    float missX, missY;
    Image missileImage;
    ImageView missileImageView;

    float getPosX() { return missX; }
    float getPosY() { return missY; }
    float getImageSize() { return (float)missileImage.getWidth(); }
    ImageView getImageView() { return missileImageView; }
    void setPosX(float f) { missX = f; }
    void setPosY(float f) { missY = f; }
}

class EnemyMissiles extends Missiles {
    EnemyMissiles(float posX, float posY) {
        missileImage =  new Image("images/bullet3.png", 10, 30, false, true);
        missileImageView = new ImageView(missileImage);
        missX= posX;
        missY = posY;
    }
}

class PlayerMissiles extends Missiles {
    PlayerMissiles(float posX, float posY) {
        missileImage =  new Image("images/player_bullet.png", 10, 30, false, true);
        missileImageView = new ImageView(missileImage);
        missX= posX;
        missY = posY;
    }
}

