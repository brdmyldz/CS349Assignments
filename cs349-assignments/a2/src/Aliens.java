import javafx.scene.image.Image ;
import javafx.scene.image.ImageView;



//base class
abstract class Aliens {
        float enemy_x, enemy_y;
        Image enemyImage;
        ImageView enemyImageView;

        float getPosX() { return enemy_x; }
        float getPosY() { return enemy_y; }
        float getImageSizeX() { return (float)enemyImage.getWidth(); }
        float getImageSizeY() { return (float)enemyImage.getHeight(); }
        ImageView getImageView() { return enemyImageView; }
        void setPosX(float f) { enemy_x = f; }
        void setPosY(float f) { enemy_y = f; }
        int givePoint() { return 0; };
}

class Enemy1 extends Aliens {
    Enemy1(float posX, float posY) {
        enemyImage =  new Image("images/enemy1.png", 60, 40, false, true);
        enemyImageView = new ImageView(enemyImage);
        enemy_x = posX;
        enemy_y = posY;
    }

    int givePoint() { return 10; }
}

class Enemy2 extends Aliens{
    Enemy2(float posX, float posY) {
        enemyImage =  new Image("images/enemy2.png", 60, 40, false, true);
        enemyImageView = new ImageView(enemyImage);
        enemy_x = posX;
        enemy_y = posY;
    }
    int givePoint() { return 20; }
}

class Enemy3 extends Aliens{
    Enemy3(float posX, float posY) {
        enemyImage = new Image("images/enemy3.png", 60, 40, false, true);
        enemyImageView = new ImageView(enemyImage);
        enemy_x = posX;
        enemy_y = posY;
    }
    int givePoint() { return 30; }
}
