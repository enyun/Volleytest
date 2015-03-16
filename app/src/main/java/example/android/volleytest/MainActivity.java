package example.android.volleytest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        textView.setText("hello testing");

        button.setOnClickListener(this);
//        imageView.setImageURI(Uri.parse("https://www.baidu.com/img/bdlogo.png"));

/*
        new Thread(){
            public void run(){
                URL picUrl = null;
                Bitmap pngBM = null;

                try {
                    picUrl = new URL("http://img1.gtimg.com/12/1206/120666/12066617_980x1200_0.jpg");
                    pngBM = BitmapFactory.decodeStream(picUrl.openStream());
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                URL picUrl = null;
                Bitmap pngBM = null;

                try {
                    picUrl = new URL("http://img1.gtimg.com/12/1206/120666/12066617_980x1200_0.jpg");
                    pngBM = BitmapFactory.decodeStream(picUrl.openStream());
                    imageView.setImageBitmap(pngBM);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 200);
*/
//        imageView.setImageResource();
        setPicBitmap(imageView, "http://s1.jikexueyuan.com/current/static/images/logo.png");
    }

    public static void setPicBitmap(final ImageView ivPic, final String pic_url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("huhuhuhuhuhuhhu");
                    HttpURLConnection conn = (HttpURLConnection) new URL(pic_url).openConnection();
                    System.out.println("conn is "+conn);

                    conn.connect();
                    InputStream is = conn.getInputStream();
                    System.out.println("is is "+is);

                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    ivPic.setImageBitmap(bitmap);
                    is.close();
                    System.out.println("uuuuuuuuuuuuuu");

                } catch (Exception e) {
                    System.err.println("error error error");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private ImageView imageView;
    private TextView textView;
    private Button button;
    private static boolean flag=true;

    @Override
    public void onClick(View v) {
        flag = !flag;
        if (flag) {
            textView.setText("what");
            setPicBitmap(imageView, "http://s1.jikexueyuan.com/current/static/images/logo.png");

        }else {
            textView.setText("how");
            setPicBitmap(imageView, "https://www.baidu.com/img/bdlogo.png");
        }
    }
}
