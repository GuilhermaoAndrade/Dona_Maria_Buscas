package br.com.etecia.dona_maria_buscas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class MenuActivity extends AppCompatActivity {

    private int[] mImagens = new int[]{
            R.drawable.bombom, R.drawable.fraldas, R.drawable.arroz, R.drawable.feijao

    };
    private String[] mImagensTitle = new String[]{


    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        CarouselView carouselView = findViewById(R.id.carousel);
        carouselView.setPageCount(mImagens.length);

        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImagens[position]);
            }
        });

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MenuActivity.this, mImagensTitle[position], Toast.LENGTH_SHORT).show();
            }
        });


    }
}
