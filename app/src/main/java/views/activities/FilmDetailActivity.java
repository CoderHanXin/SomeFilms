package views.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.addict.somefilms.R;
import com.addict.somefilms.mvp.presenters.FilmDetailPresenter;
import com.addict.somefilms.mvp.views.FilmDetailView;
import com.squareup.picasso.Picasso;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public class FilmDetailActivity extends Activity implements FilmDetailView {

    private FilmDetailPresenter mFilmDetailPresenter;
    private TextView mContentTextView;
    private ImageView mImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        mContentTextView = (TextView) findViewById(R.id.textView_content);
        mImage = (ImageView) findViewById(R.id.image);
        mFilmDetailPresenter = new FilmDetailPresenter(this, "23761370");
    }


    @Override
    protected void onStop() {

        super.onStop();
        mFilmDetailPresenter.stop();
    }

    @Override
    protected void onStart() {

        super.onStart();
        mFilmDetailPresenter.start();
    }

    @Override
    public void setContent(String content) {
        mContentTextView.setText(content);
    }

    @Override
    public void setFilmImage(String url) {
        Picasso.with(this).load(url).fit().into(mImage);
    }
}
