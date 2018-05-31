package start2develop.org.lasthero;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Enemy {
    private static final int BMP_FILAS = 4; //Filas del Bitmap
    private static final int BMP_COLUMNAS = 3;//Columnas del Bitmap
    private int x = 0; //Posición eje x
    private int y = 0; //Posición eje y
    private int xSpeed = 5; //Velocidad eje X
    private int ySpeed = 10;
    private int speed = 0;
    private GameLoopThread gl;
    private int contador = 0;
    private GameView gameView;
    private Bitmap bmp;
    private int actualFrame = 0; //Frame Actual
    private int actualFila = 2; //Fila Actual de Dirección
    private int width; //Ancho
    private int height; //Alto
    public Enemy (GameView gameView, Bitmap bmp) {
        this.setGameView(gameView);
        this.setBmp(bmp); //Le pasamos una imagen Bitmap
        this.setWidth(bmp.getWidth() / getBmpColumnas()); //Dividimos el ancho del bitmap por el número de columnas
        this.setHeight(bmp.getHeight() / getBmpFilas()); //Dividimos el alto delbitmap por el número de filas
        this.setContador(getContador());
    }

    public static int getBmpFilas() {
        return BMP_FILAS;
    }

    public static int getBmpColumnas() {
        return BMP_COLUMNAS;
    }

    private void update() {
        if (x > gameView.getWidth() - width - xSpeed) {
            setxSpeed(-getxSpeed());
            setActualFila(1);
        }
        if (x + xSpeed < 0) {
            setxSpeed(getxSpeed()*-1);
            setActualFila(1);
        }

        if (y > gameView.getHeight() - height - ySpeed) {
            setySpeed(-getySpeed());
            setActualFila(1);

        }
        if (y + ySpeed < 0) {
            setySpeed(-getySpeed()+5);
            setActualFila(1);
        }
        setX(getX() + getxSpeed());
        setY(getY() + getySpeed());
        actualFrame = ++actualFrame % BMP_COLUMNAS; //Par
    }
    public void onDraw(Canvas canvas) {
        update();
        int srcX = getActualFrame() * getWidth();
        int srcY = getActualFila() * getHeight();
        Rect src = new Rect(srcX, srcY, srcX + getWidth(), srcY + getHeight());
        Rect dst = new Rect(getX(), getY(), getX() + getWidth() +50, getY() + getHeight() +50); //Añadimos 50  para hacerlo más grande
        canvas.drawBitmap(getBmp(), src , dst, null);
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

    public int getActualFrame() {
        return actualFrame;
    }

    public void setActualFrame(int actualFrame) {
        this.actualFrame = actualFrame;
    }

    public int getActualFila() {
        return actualFila;
    }

    public void setActualFila(int actualFila) {
        this.actualFila = actualFila;
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

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}