import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

// Demonstrates switching between scenes at runtime

public class SpaceInvaders extends Application {

    final float screenWidth = 1200;
    final float screenHeight = 900;
    final float endingHeight = 780;
    boolean isRunning = false;

    // space ship movements
    final float playerMS = 5f;
    boolean leftPressed = false;
    boolean rightPressed = false;

    // initial alien move speeds
    final float descendingGap = 12.0f;
    final float enemySpeedInc = 0.2f;
    float curEnemyMS;

    // initial pos of bottom left alien
    final float initPosX = 200;
    final float initPosY = 330;

    // spaceShip Missiles
    boolean playerFired = false;
    int playerCD = 0;
    final float playerMissileSpeed = 10;
    final float upperLimit = 50;

    // enemy missiles
    int maxMissiles;
    final int missileChance = 0;
    final float enemyMissileSpeed = 8;
    final float bottomLimit = 870;

    List<Aliens> aliensList = new ArrayList<>();
    SpaceShip spaceShip = new SpaceShip();
    List<Missiles> playerMissiles = new ArrayList<>();
    List<Missiles> enemyMissiles = new ArrayList<>();
    int livesLeft = 3;
    int score = 0;
    Text livesText;
    Text scoreText;
    Rectangle endGameBackground = new Rectangle();
    Text endGameText;
    Text defeated;
    Text victory;
    Text endGameScore;

    //Group for the Game Scene
    Group gameElements = new Group();

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            allAnimations();
        }
    };



    @Override
    public void start(Stage stage) {

        // Main menu scene
        Group menuElements = new Group();
        Image backgroundImage = new Image("images/background.png", 1200, 900, true ,true);
        ImageView backgroundImageView = new ImageView(backgroundImage);

        Image logoImage = new Image("images/logo.png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setLayoutX(355);
        logoImageView.setLayoutY(50);

        Text titleText = new Text("INSTRUCTIONS");
        titleText.setFont(Font.font("Copperplate", FontWeight.BOLD, 40));
        titleText.setFill(Color.WHITE);
        titleText.setLayoutX(450);
        titleText.setLayoutY(400);

        Text instructions = new Text("ENTER - Start Game" + "\n" +
                                       "A or ←,D or → - Move ship left and right" + "\n"+
                                        "SPACE - Fire!" + "\n" +
                                        "1 or 2 or 3 - Start game at specific level");
        instructions.setFont(Font.font("Copperplate", 26));
        instructions.setTextAlignment(TextAlignment.CENTER);
        instructions.setFill(Color.WHITE);
        instructions.setLayoutX(330);
        instructions.setLayoutY(450);

        menuElements.getChildren().add(backgroundImageView);
        menuElements.getChildren().add(instructions);
        menuElements.getChildren().add(titleText);
        menuElements.getChildren().add(logoImageView);

        Scene mainMenu = new Scene(menuElements);


        // Game Scene
        Text Info1 = new Text("P - PAUSE/RESUME");
        Info1.setFont(Font.font("Copperplate", FontWeight.BOLD, 26));
        Info1.setFill(Color.WHITE);
        Info1.setLayoutX(220);
        Info1.setLayoutY(20);

        Text Info2 = new Text("M - MAIN MENU");
        Info2.setFont(Font.font("Copperplate", FontWeight.BOLD, 26));
        Info2.setFill(Color.WHITE);
        Info2.setLayoutX(780);
        Info2.setLayoutY(20);



        Text levelText = new Text("LEVEL 1");
        levelText.setFont(Font.font("Copperplate", FontWeight.BOLD, 26));
        levelText.setFill(Color.WHITE);
        levelText.setLayoutX(1075);
        levelText.setLayoutY(20);

        scoreText = new Text("SCORE: " + score );
        scoreText.setFont(Font.font("Copperplate", FontWeight.BOLD, 26));
        scoreText.setFill(Color.WHITE);
        scoreText.setLayoutX(20);
        scoreText.setLayoutY(20);

        livesText = new Text("LIVES LEFT: " + livesLeft);
        livesText.setFont(Font.font("Copperplate", FontWeight.BOLD, 26));
        livesText.setFill(Color.WHITE);
        livesText.setLayoutX(520);
        livesText.setLayoutY(20);

        endGameBackground.setHeight(500);
        endGameBackground.setWidth(600);
        endGameBackground.setFill(Color.DARKGRAY);
        endGameBackground.setArcHeight(10);
        endGameBackground.setArcWidth(10);
        endGameBackground.setLayoutX(300);
        endGameBackground.setLayoutY(200);

        endGameText = new Text("ENTER - Start Game" + "\n" +
                "M - GO BACK TO MAIN MENU" + "\n"+
                "Q - QUIT" + "\n" +
                "1 or 2 or 3 - Start game at specific level");
        endGameText.setTextAlignment(TextAlignment.CENTER);
        endGameText.setFill(Color.BLACK);
        endGameText.setFont(Font.font("Copperplate", FontWeight.BOLD, 26));
        endGameText.setLayoutX(330);
        endGameText.setLayoutY(550);

        defeated = new Text("DEFEATED");
        defeated.setFill(Color.BLACK);
        defeated.setFont(Font.font("Copperplate", 80));
        defeated.setLayoutX(380);
        defeated.setLayoutY(310);

        victory = new Text("VICTORY");
        victory.setFill(Color.BLACK);
        victory.setFont(Font.font("Copperplate", 80));
        victory.setLayoutX(420);
        victory.setLayoutY(310);

        endGameScore = new Text();
        endGameScore.setFill(Color.BLACK);
        endGameScore.setFont(Font.font("Copperplate", 32));
        endGameScore.setLayoutX(450);
        endGameScore.setLayoutY(445);


        spaceShip.getImageView().setLayoutX(spaceShip.getPosX());
        spaceShip.getImageView().setLayoutY(spaceShip.getPosY());

        alienCreater();

        gameElements.getChildren().add(levelText);
        gameElements.getChildren().add(Info1);
        gameElements.getChildren().add(Info2);
        gameElements.getChildren().add(scoreText);
        gameElements.getChildren().add(livesText);
        gameElements.getChildren().add(spaceShip.getImageView());

        Scene gameScene = new Scene(gameElements, Color.BLACK);







        // switch scenes with keyboard inputs
        mainMenu.setOnKeyPressed(key -> {
            if(key.getCode() == KeyCode.ENTER || key.getCode() == KeyCode.DIGIT1){
                levelText.setText("LEVEL 1");
                curEnemyMS = 1;
                maxMissiles = 2;
                stage.setScene(gameScene);
                isRunning = true;
                timer.start();
            }
            if(key.getCode() == KeyCode.DIGIT2){
                levelText.setText("LEVEL 2");
                curEnemyMS = 3;
                maxMissiles = 3;
                stage.setScene(gameScene);
                isRunning = true;
                timer.start();
            }
            if(key.getCode() == KeyCode.DIGIT3){
                levelText.setText("LEVEL 3");
                curEnemyMS = 5;
                maxMissiles = 4;
                stage.setScene(gameScene);
                isRunning = true;
                timer.start();
            }
            if(key.getCode() == KeyCode.Q){
                stage.close();
            }
        });

        gameScene.setOnKeyPressed(key -> {
            if(key.getCode() == KeyCode.M){
                restart();
                stage.setScene(mainMenu);
                isRunning = false;
                timer.stop();
            }
            if(key.getCode() == KeyCode.P){
                // future improvement: bring a pause menu
                if(isRunning == true){
                    timer.stop();
                    isRunning = false;
                } else {
                    timer.start();
                    isRunning = true;
                }
            }
            if(key.getCode() == KeyCode.A ||
                    key.getCode() == KeyCode.LEFT){
                leftPressed = true;
            }
            if(key.getCode() == KeyCode.D ||
                    key.getCode() == KeyCode.RIGHT){
                rightPressed = true;
            }
            if(key.getCode() == KeyCode.SPACE){
                playerFired = true;
            }
            if(isRunning == false) {
                if(key.getCode() == KeyCode.ENTER || key.getCode() == KeyCode.DIGIT1){
                    restart();
                    levelText.setText("LEVEL 1");
                    curEnemyMS = 1;
                    maxMissiles = 2;
                    stage.setScene(gameScene);
                    isRunning = true;
                    timer.start();
                }
                if(key.getCode() == KeyCode.DIGIT2){
                    restart();
                    levelText.setText("LEVEL 2");
                    curEnemyMS = 3;
                    maxMissiles = 3;
                    stage.setScene(gameScene);
                    isRunning = true;
                    timer.start();
                }
                if(key.getCode() == KeyCode.DIGIT3){
                    restart();
                    levelText.setText("LEVEL 3");
                    curEnemyMS = 5;
                    maxMissiles = 4;
                    stage.setScene(gameScene);
                    isRunning = true;
                    timer.start();
                }
                if(key.getCode() == KeyCode.M){
                    restart();
                    stage.setScene(mainMenu);
                    isRunning = false;
                    timer.stop();
                }
                if(key.getCode() == KeyCode.Q){
                    stage.close();
                }
            }


        });

        gameScene.setOnKeyReleased(key -> {
            if(key.getCode() == KeyCode.A ||
                    key.getCode() == KeyCode.LEFT){
                leftPressed = false;
            }
            if(key.getCode() == KeyCode.D ||
                    key.getCode() == KeyCode.RIGHT){
                rightPressed = false;
            }
            if(key.getCode() == KeyCode.SPACE){
                playerFired = false;
            }
        });




        // show starting scene
        stage.setTitle("Space Invaders");
        stage.setScene(mainMenu);
        stage.setHeight(screenHeight);
        stage.setWidth(screenWidth);
        stage.setResizable(false);
        stage.show();
    }

    void allAnimations() {
        //missile generator
        if(enemyMissiles.size() < maxMissiles){
            Random r = new Random();
            int randomNum = r.nextInt(100);

            if (randomNum == missileChance){

                int randomAlien = r.nextInt(aliensList.size());
                Aliens currentEnemy = aliensList.get(randomAlien);
                float posX = currentEnemy.getPosX();
                float posY = currentEnemy.getPosY();

                EnemyMissiles missile = new EnemyMissiles(posX + 30, posY + 45);
                enemyMissiles.add(missile);
                missile.getImageView().setLayoutX(missile.getPosX());
                missile.getImageView().setLayoutY(missile.getPosY());
                gameElements.getChildren().add(missile.getImageView());
            }
        }

        // enem missiles mover
        for(int i = 0; i < enemyMissiles.size(); ++i){
            Missiles currentMissile = enemyMissiles.get(i);
            float currentX = currentMissile.getPosX();
            float currentY = currentMissile.getPosY();

            float playerX = spaceShip.getPosX();
            float playerY = spaceShip.getPosY();
            float pImageSizeX = spaceShip.getImageSizeX();
            float pImageSizeY = spaceShip.getImageSizeY();

            boolean isHit = false;
            if((currentX + 10 > playerX && currentX < playerX + pImageSizeX) &&
                    (currentY + 30 > playerY && currentY + 30 < playerY + pImageSizeY)) {
                livesLeft -= 1;
                if (livesLeft == 0) {
                    isRunning = false;
                    timer.stop();
                    endGameMenu();
                    gameElements.getChildren().add(defeated);
                }
                spaceShip.resetPositions();
                spaceShip.getImageView().setLayoutX(spaceShip.getPosX());
                spaceShip.getImageView().setLayoutY(spaceShip.getPosY());

                gameElements.getChildren().remove(currentMissile.getImageView());
                enemyMissiles.remove(i);

                String sound = getClass().getClassLoader().getResource("sounds/explosion.mp3").toString();
                AudioClip clip = new AudioClip(sound);
                clip.play();

                isHit = true;
            }

            if (isHit == false){
                if(currentY < bottomLimit) {
                    currentY += enemyMissileSpeed;
                    currentMissile.setPosY(currentY);
                    currentMissile.getImageView().setLayoutY(currentY);
                } else { //Missiles didn't hit anything just destroy it
                    gameElements.getChildren().remove(currentMissile.getImageView());
                    enemyMissiles.remove(i);
                }
            }
        }


        // spaceShip fire
        if(playerCD > 0 && playerCD < 30){
            ++playerCD;
        }
        if(playerFired == true) {
            if (playerCD == 0){
                Missiles playerMissile = new PlayerMissiles(spaceShip.getPosX() + 25,
                                                                    spaceShip.getPosY() - 25);
                playerMissiles.add(playerMissile);
                playerMissile.getImageView().setLayoutX(playerMissile.getPosX());
                playerMissile.getImageView().setLayoutY(playerMissile.getPosY());
                gameElements.getChildren().add(playerMissile.getImageView());

                String sound = getClass().getClassLoader().getResource("sounds/shoot.mp3").toString();
                AudioClip clip = new AudioClip(sound);
                clip.play();

                ++playerCD;
            } else if (playerCD == 30) {
                playerCD = 0; // reset
            }
        }

        if(!playerMissiles.isEmpty()){
            for(int i = 0; i < playerMissiles.size(); ++i){
                Missiles currentMissile = playerMissiles.get(i);
                float currentX = currentMissile.getPosX();
                float currentY = currentMissile.getPosY();

                boolean isHit = false;
                for(int alienInc = 0; alienInc < aliensList.size(); ++ alienInc){
                    Aliens currentAlien = aliensList.get(alienInc);
                    float alienX = currentAlien.getPosX();
                    float alienY = currentAlien.getPosY();
                    float alienSizeX = currentAlien.getImageSizeX();
                    float alienSizeY = currentAlien.getImageSizeY();

                    if((currentY > alienY && currentY < alienY + alienSizeY) &&
                            (currentX + 10 > alienX && currentX < alienX + alienSizeX)) {
                        if(curEnemyMS > 0) {
                            curEnemyMS += enemySpeedInc;
                        } else {
                            curEnemyMS -= enemySpeedInc;
                        }

                        score += currentAlien.givePoint();
                        scoreText.setText("SCORE: " + score);
                        gameElements.getChildren().remove(currentAlien.getImageView());
                        aliensList.remove(alienInc);
                        gameElements.getChildren().remove(currentMissile.getImageView());
                        playerMissiles.remove(i);
                        isHit = true;
                        if(aliensList.isEmpty()){
                            isRunning = false;
                            timer.stop();
                            endGameMenu();
                            gameElements.getChildren().add(victory);
                        }
                        break;
                    }
                }

                if(isHit == false) {
                    if(currentY > upperLimit) {
                        currentY -= playerMissileSpeed;
                        currentMissile.setPosY(currentY);
                        currentMissile.getImageView().setLayoutY(currentY);
                    } else { //Missiles didn't hit anything just destroy it
                        gameElements.getChildren().remove(currentMissile.getImageView());
                        playerMissiles.remove(i);
                    }
                }
            }
        }

        // spaceShip move
        float playerX = spaceShip.getPosX();
        if (leftPressed == true){
            if(playerX > 0.0f){
                playerX -= playerMS;
                spaceShip.setPosX(playerX);
                spaceShip.getImageView().setLayoutX(playerX);
            }
        } else if (rightPressed == true){
            float playerSize = spaceShip.getImageSizeX();
            if((playerX + playerSize) < screenWidth){
                playerX += playerMS;
                spaceShip.setPosX(playerX);
                spaceShip.getImageView().setLayoutX(playerX);
            }
        }
        boolean edge = false;
        for(int i = 0; i < aliensList.size(); ++i){
            Aliens enemy = aliensList.get(i);
            float enemyX = enemy.getPosX();
            float enemyY = enemy.getPosY();
            float enemySize = enemy.getImageSizeX();

            // if enemy hit the edge go 1 row below and change direction and fire
            if (enemyX < 0.0f || (enemyX + enemySize) > screenWidth){
                curEnemyMS *= -1.0f;
                edge = true;
                break;
            }

            if (enemyY > endingHeight) { // Game Ends
                isRunning = false;
                timer.stop();
                endGameMenu();
                gameElements.getChildren().add(defeated);
            }
        }

        if(edge == true){ // fire random missile
            Random r = new Random();
            int randomNum = r.nextInt(aliensList.size());
            Aliens currentEnemy = aliensList.get(randomNum);
            float posX = currentEnemy.getPosX();
            float posY = currentEnemy.getPosY();

            EnemyMissiles missile = new EnemyMissiles(posX + 30, posY + 45);
            enemyMissiles.add(missile);
            missile.getImageView().setLayoutX(missile.getPosX());
            missile.getImageView().setLayoutY(missile.getPosY());
            gameElements.getChildren().add(missile.getImageView());
        }

        for (int i = 0; i < aliensList.size(); ++i){
            Aliens enemy = aliensList.get(i);
            float enemyX = enemy.getPosX();
            float enemyY = enemy.getPosY();

            // move enemies
            enemyX += curEnemyMS;
            enemy.setPosX(enemyX);
            enemy.getImageView().setLayoutX(enemyX);
            if(edge == true) {
                enemyY += descendingGap;
                enemy.setPosY(enemyY);
                enemy.getImageView().setLayoutY(enemyY);
            }
        }

    }

    void alienCreater() {
        int totalAlien = 50;
        int totalEnemy1s = 20;
        int totalEnemy1N2s = 40;
        int eachRow = 10;

        final float incConstX = 75;
        final float incConstY = 55;

        float incPosX = 0;
        float incPosY = 0;


        Enemy1 initEnemy = new Enemy1(initPosX, initPosY);
        aliensList.add(initEnemy);
        initEnemy.getImageView().setLayoutX(initEnemy.getPosX());
        initEnemy.getImageView().setLayoutY(initEnemy.getPosY());
        gameElements.getChildren().add(initEnemy.getImageView());

        for(int i = 1; i < totalAlien; ++i){
            if(i < totalEnemy1s) {
                if(i == eachRow){ // Increment PosY
                    incPosX = 0;
                    incPosY -= incConstY;
                } else {
                    incPosX += incConstX;
                }
                Enemy1 enemy = new Enemy1(initPosX + incPosX, initPosY + incPosY);
                aliensList.add(enemy);
                enemy.getImageView().setLayoutX(enemy.getPosX());
                enemy.getImageView().setLayoutY(enemy.getPosY());
                gameElements.getChildren().add(enemy.getImageView());
            } else if (i < totalEnemy1N2s){
                if(i == eachRow + totalEnemy1s || i == totalEnemy1s) { // Increment PosY
                    incPosX = 0;
                    incPosY -= incConstY;
                } else {
                    incPosX += incConstX;
                }
                Enemy2 enemy = new Enemy2(initPosX + incPosX, initPosY + incPosY);
                aliensList.add(enemy);
                enemy.getImageView().setLayoutX(enemy.getPosX());
                enemy.getImageView().setLayoutY(enemy.getPosY());
                gameElements.getChildren().add(enemy.getImageView());
            } else {
                if(i == totalEnemy1N2s){
                    incPosX = 0;
                    incPosY -= incConstY;
                } else {
                    incPosX += incConstX;
                }
                Enemy3 enemy = new Enemy3(initPosX + incPosX, initPosY + incPosY);
                aliensList.add(enemy);
                enemy.getImageView().setLayoutX(enemy.getPosX());
                enemy.getImageView().setLayoutY(enemy.getPosY());
                gameElements.getChildren().add(enemy.getImageView());
            }
        }
    }

    void restart(){
        for(int i = 0; i < aliensList.size(); ++i) {
            gameElements.getChildren().remove(aliensList.get(i).getImageView());
        }
        aliensList.clear();
        alienCreater();

        spaceShip.resetPositions();
        spaceShip.getImageView().setLayoutX(spaceShip.getPosX());
        spaceShip.getImageView().setLayoutY(spaceShip.getPosY());
        gameElements.getChildren().remove(endGameBackground);
        gameElements.getChildren().remove(endGameText);
        gameElements.getChildren().remove(endGameScore);
        gameElements.getChildren().remove(victory);
        gameElements.getChildren().remove(defeated);

        livesLeft = 3;
        score = 0;
        scoreText.setText("SCORE: " + score);
        livesText.setText("LIVES LEFT: " + livesLeft);
    }

    void endGameMenu(){
        endGameScore.setText("YOUR SCORE: " + score);
        gameElements.getChildren().add(endGameBackground);
        gameElements.getChildren().add(endGameText);
        gameElements.getChildren().add(endGameScore);
    }


}

