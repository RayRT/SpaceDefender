package start2develop.org.lasthero;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
   // private static final int BMP_FILAS = 4; //Filas del Bitmap
   // private static final int BMP_COLUMNAS = 3;//Columnas del Bitmap
    private int x = 600; //Posición eje x
    private int y = 1600; //Posición eje y
    private int xSpeed = 0; //Velocidad eje X
    private int speed = 0;
    private GameView gameView;
    private Bitmap bmp;
    private int width; //Ancho
    private int height; //Alto
    public Sprite(GameView gameView, Bitmap bmp) {
        this.setGameView(gameView);
        this.setBmp(bmp); //Le pasamos una imagen Bitmap
        this.setWidth(bmp.getWidth()); //Dividimos el ancho del bitmap por el número de columnas
        this.setHeight(bmp.getHeight()) ; //Dividimos el alto delbitmap por el número de filas
    }
    private void update() {
            if (x > gameView.getWidth() - width - xSpeed) {
                setxSpeed(-10);
            }
            if (x + xSpeed < 0) {
                setxSpeed(10);
            }
        setX(getX() + getxSpeed());
      // actualFrame = ++actualFrame % BMP_COLUMNAS; //Par
    }
public void onDraw(Canvas canvas) {
        update();
        canvas.drawBitmap(getBmp(), getX(),getY() , null);
        }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}