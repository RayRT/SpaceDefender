package start2develop.org.lasthero;

import android.content.Context;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.KeyEvent;
import android.view.SurfaceHolder;

import android.view.SurfaceView;
import android.view.View;


public class GameView extends SurfaceView implements View.OnKeyListener{

    private Bitmap bmp;
    private GameView gameview;
    private Enemy enemy2;
    private Bitmap nin;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private Sprite sprite;
    private Enemy enemy;
    private int puntuacion = 0;
    MediaPlayer mp;
    SoundPool sp;
    int win;
    int hit ;
    int lose;


    public GameView(Context context) {

        super(context);

        gameLoopThread = new GameLoopThread(this);

        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {



            @Override

            public void surfaceDestroyed(SurfaceHolder holder) {

                boolean retry = true;

                gameLoopThread.setRunning(false);

                while (retry) {

                    try {

                        gameLoopThread.join();

                        retry = false;

                    } catch (InterruptedException e) {

                    }

                }

            }

            @Override

            public void surfaceCreated(SurfaceHolder holder) {

                gameLoopThread.setRunning(true);

                gameLoopThread.start();

            }



            @Override

            public void surfaceChanged(SurfaceHolder holder, int format,

                                       int width, int height) {

            }

        });


        //Inicializamos los sonidos
        mp=new MediaPlayer().create(context,R.raw.soundtrack);
        mp.setVolume(0.50F,0.50F);
        mp.setLooping(true);
        mp.start();

        sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        hit=sp.load(context,R.raw.hit,1);
        win = sp.load(context,R.raw.win,1);
        lose = sp.load(context,R.raw.lose,1);

//Añadimos el listener de onKey al juego
        setOnKeyListener(this);
//Pedimos que el GameView tenga el foco para que actúen los eventos.
        setFocusableInTouchMode(true);
        requestFocus();

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.barrita);
        nin = BitmapFactory.decodeResource(getResources(), R.drawable.bolas);
        sprite = new Sprite(this,bmp);
        enemy = new Enemy(this,nin);
        enemy2 = new Enemy(this,nin);
    }

    public int colision(){
        int nivelColision=100; //Mínimo 10 muy permisivo máximo 100 (tamaño delsprite) poco permisivo
        int i = 1;

            if((Math.abs(sprite.getX()-enemy.getX())<150 ||
                    Math.abs(enemy.getX()-sprite.getX())<150) //Eje X
                    && (Math.abs(sprite.getY()-enemy.getY())<150 ||
                    Math.abs(enemy.getY()-sprite.getY())<0))

            {
                enemy.setxSpeed(enemy.getxSpeed()-10);
                enemy.setySpeed(enemy.getySpeed()-10);
                puntuacion = puntuacion + 5;
                sp.play(hit,1,1,1,0,1);

                return i;
            }if(enemy.getY() > this.getHeight() - enemy.getHeight() - enemy.getySpeed()) {
            puntuacion = puntuacion - 10;
        }

        return -1;
    }
    @Override

    protected void onDraw(Canvas canvas) {
        Bitmap bmpFondo = BitmapFactory.decodeResource(getResources(),
               R.drawable.fondojuego);
        Rect src = new Rect(0, 0, bmpFondo.getWidth(), bmpFondo.getHeight());
        Rect dst = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(bmpFondo, src, dst, null);

        enemy.onDraw(canvas);
        sprite.onDraw(canvas);

        Paint p = new Paint();
        p.setStrokeWidth(8);
        p.setColor(Color.GREEN);
        p.setTextSize(60F);
        p.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("Score : "+puntuacion,80,80,p);

//SI EL JUGADOR GANA
        if(puntuacion >=50) {
            Paint o = new Paint();
            o.setStrokeWidth(8);
            o.setColor(Color.BLUE);
            o.setTextSize(100F);
            o.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText(" ¡ YOU WIN  !  ",250,500,p);
            sp.play(win,1,1,1,0,1);
            mp.stop();

            gameLoopThread.setRunning(false);
        }
        //SI EL JUGADOR PIERDE
        if(puntuacion <=-10) {
            mp.stop();
            Paint o = new Paint();
            o.setStrokeWidth(8);
            o.setColor(Color.BLUE);
            o.setTextSize(100F);
            o.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText(" ¡ YOU LOSE  !  ",250,500,p);
            sp.play(lose,1,1,1,0,1);

            gameLoopThread.setRunning(false);
        }

    }


    public boolean onKey(View v, int keyCode, KeyEvent keyEvent) {
        boolean hecho = false;
        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
    //IZQUIERDA
 case KeyEvent.KEYCODE_A:
     sprite.setX(sprite.getX()-70);
            case KeyEvent.KEYCODE_DPAD_LEFT:
            sprite.setxSpeed(-sprite.getSpeed()); //Ponemos la velocidaddel eje X hacia la izquierda
 //Paramos la velocidad del eje Y
            hecho = true;
                sprite.setX(sprite.getX()-70);
 break;
    //DERECHA
 case KeyEvent.KEYCODE_D:
    // sprite.setX(sprite.getX()+70);
     sprite.setxSpeed(sprite.getSpeed());
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            sprite.setxSpeed(sprite.getSpeed());
            hecho = true;
                sprite.setX(sprite.getX()+70);
 break;
}
 }
         return hecho;
         }
         }