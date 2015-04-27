package hk.edu.cityuappslab.flashcard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kinnimew on 21/4/15.
 */
public class FlashcardFragment extends Fragment {

    public final static int TYPE_QUESTION = 0;
    public final static int TYPE_ANSWER = 1;

    public static FlashcardFragment newInstance(String text, int type){
        Bundle args = new Bundle();

        args.putString("text", text);
        args.putInt("type", type);

        FlashcardFragment flashcardFragment = new FlashcardFragment();
        flashcardFragment.setArguments(args);

        return flashcardFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flashcard, container, false);

        String text = getArguments().getString("text");
        int type = getArguments().getInt("type");

        ((TextView)view.findViewById(R.id.question)).setText(text);

        if (type == TYPE_QUESTION){
            view.findViewById(R.id.question).setBackgroundColor(0xFF615F84);

        }

        return view;
    }
}
