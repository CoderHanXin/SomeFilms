package views.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.addict.somefilms.R;
import com.addict.somefilms.mvp.presenters.FilmDetailPresenter;
import com.addict.somefilms.mvp.views.FilmDetailView;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public class FilmDetailActivity extends Activity implements FilmDetailView {

    private FilmDetailPresenter mFilmDetailPresenter;
    private TextView mContentTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        mContentTextView = (TextView) findViewById(R.id.textView_content);
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
}
