package diplomado.ccm.itesm.starproyect;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;


public class Star extends Activity {

    private Button      btnStar;
    private ImageView   container;
    private Paint       pen;
    private Canvas      canvas;
    private SeekBar     sbStar;
    private TextView    tvNumLines;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);
        this.initializeStarVariables();

    }

    /**
     * Inicializamos las variables que utilizaremos en el pintado de la estrella
     */
    private void initializeStarVariables(){

        this.btnStar    = (Button)      findViewById(R.id.buttonStar);
        this.container  = (ImageView)   findViewById(R.id.imageViewStar);
        this.sbStar     = (SeekBar)     findViewById(R.id.seekBarStar);
        this.pen        = new Paint();

        this.btnStar.setOnClickListener(listenerBtnStar);
        this.sbStar.setOnSeekBarChangeListener(listenerSbStar);
        this.sbStar.setMax(50);
        this.tvNumLines = (TextView) findViewById(R.id.textViewStar);
        this.tvNumLines.setText("3");


    }




    /**
     * Listener para el boton de mostrar la estrella
     */
    private View.OnClickListener  listenerBtnStar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawStar(10);  //llamamos al metodo que pinta la estrella
            sbStar.setProgress(10);
        }
    };




    /**
     * Listener para la barra deslizable
     */
    private SeekBar.OnSeekBarChangeListener listenerSbStar = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            progress = (progress <3)?3:progress;
            drawStar(progress);  //llamamos al metodo que pinta la etrella
            tvNumLines.setText(String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void drawStar(int numLines){

        Bitmap bm       =  Bitmap.createBitmap(this.container.getWidth(), this.container.getHeight(), Bitmap.Config.ARGB_8888);
        this.canvas     = new Canvas(bm);
        this.container.setImageBitmap(bm);




        int x           = this.container.getWidth()  /2;
        int y           = this.container.getHeight() /2;
        Random  ran     = new Random();
        int red         = ran.nextInt(256);
        int green       = ran.nextInt(256);
        int blue        = ran.nextInt(256);

        this.pen.setColor(Color.rgb(red, green, blue));
        this.canvas.drawLine(x, 0, x, this.container.getHeight(), this.pen);
        this.canvas.drawLine(0, y, this.container.getWidth(), y, this.pen);


        int lowSide     = this.container.getWidth() > this.container.getHeight()?this.container.getHeight():this.container.getWidth();
        lowSide        /= 2;
        int lnInterval  = lowSide / numLines;

        for(int i = 1, j = numLines; i <= numLines; i++, j--){

            canvas.drawLine(x, y -i*lnInterval, x + j*lnInterval, y, this.pen);
            canvas.drawLine(x, y +i*lnInterval, x - j*lnInterval, y, this.pen);
            canvas.drawLine(x, y -i*lnInterval, x - j*lnInterval, y, this.pen);
            canvas.drawLine(x, y +i*lnInterval, x + j*lnInterval, y, this.pen);
        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_star, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
