package start2develop.org.lasthero;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class GameLoopThread extends Thread {
    private Sprite sprite;
    private boolean muerto = false;
    private Bitmap bl;
    private int contador = 0;
    private GameView view; //Añadimos nuestra superficie al bucle
    private boolean running = false; //Variable para controlar si esta elbucle activo o no
    public GameLoopThread(GameView view) {
        this.setView(view);
    }
    public void setRunning(boolean run) {
        running = run;
    }
    @Override
    public void run() {
        while (isRunning()) { //running un "flag" para parar el "game loop"
            Canvas c = null;

            try {

                c = getView().getHolder().lockCanvas();
                //Sincroniza para impedir que se pueda usar varias veces a la vez
                synchronized (getView().getHolder()) {
                    getView().onDraw(c);
                    detectarColision();



                }
            } finally {
                if (c != null) {
                    getView().getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
    //Método que detecta si el sprite del personaje colisiona con un árbol
    public void detectarColision(){
        int i= view.colision();
 if(i>=0){
        Log.e("Muerto por el arbol ", String.valueOf(i));
    }
}

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public boolean isMuerto() {
        return muerto;
    }

    public void setMuerto(boolean muerto) {
        this.muerto = muerto;
    }

    public Bitmap getBl() {
        return bl;
    }

    public void setBl(Bitmap bl) {
        this.bl = bl;
    }

    public GameView getView() {
        return view;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public boolean isRunning() {
        return running;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}
