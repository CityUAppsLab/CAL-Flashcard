package hk.edu.cityuappslab.flashcard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.List;

import hk.edu.cityuappslab.flashcard.backend.myBackend.MyBackend;
import hk.edu.cityuappslab.flashcard.backend.myBackend.model.Flashcard;
import hk.edu.cityuappslab.flashcard.backend.myBackend.model.FlashcardCollection;


public class MainActivity extends ActionBarActivity{

    private ViewPager mPager;

    private PagerAdapter mPagerAdapter;

    private MyBackend myApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Init UI
        mPager = (ViewPager) findViewById(R.id.pager);

        // Fetch data from server
        new LoadFlashcardAsyncTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private class FlashcardPagerAdapter extends FragmentStatePagerAdapter {

        List<Flashcard> flashcards;

        public FlashcardPagerAdapter(FragmentManager fm, List<Flashcard> flashcards) {
            super(fm);

            this.flashcards = flashcards;
        }

        @Override
        public Fragment getItem(int position) {
            Flashcard flashcard = flashcards.get(position/2);
            String text = (position % 2 == 0) ? flashcard.getQuestion() : flashcard.getAnswer();

            return FlashcardFragment.newInstance(text, position % 2);
        }

        @Override
        public int getCount() {
            // Each card has "question" + "answer", so count = numOfCards * 2
            return flashcards.size()*2;
        }

    }


    class LoadFlashcardAsyncTask extends AsyncTask<Void, Void, List<Flashcard>> {

        @Override
        protected List<Flashcard> doInBackground(Void... params) {
            if(myApiService == null) {  // Only do this once
                //MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                MyBackend.Builder builder = new MyBackend.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)

                        // This IP is used for development
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            try {

                FlashcardCollection flashcardCollection = myApiService.getAllFlashcard().execute();
                List<Flashcard> flashcards = flashcardCollection.getItems();

                return flashcards;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Flashcard> flashcards) {
            Toast.makeText(MainActivity.this, "There are " + flashcards.size() + " items in the server", Toast.LENGTH_SHORT).show();

            mPagerAdapter = new FlashcardPagerAdapter(getSupportFragmentManager(), flashcards);
            mPager.setAdapter(mPagerAdapter);

        }
    }


}
