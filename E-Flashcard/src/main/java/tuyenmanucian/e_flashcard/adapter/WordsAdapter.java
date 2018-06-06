package tuyenmanucian.e_flashcard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tuyenmanucian.e_flashcard.R;
import tuyenmanucian.e_flashcard.models.Word;

public class WordsAdapter extends ArrayAdapter<Word> {

    private Context mContext;
    private int mLayout;
    private ArrayList<Word> mList;


    public WordsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Word> objects) {
        super(context, resource, objects);
        mContext = context;
        mLayout = resource;
        mList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mLayout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvWord = (convertView.findViewById(R.id.tvWord));
            viewHolder.tvMean = (convertView.findViewById(R.id.tvMean));
            viewHolder.btnDelete = (convertView.findViewById(R.id.btnDelete));
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = ((ViewHolder) convertView.getTag());
        }

        Word word = mList.get(position);
        viewHolder.tvWord.setText(word.getVocabulary());
        viewHolder.tvMean.setText(word.getMean());
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tttttttttt", "Gfgfdg");
            }
        });
        return convertView;
    }

    static class ViewHolder{
        private TextView tvWord, tvMean;
        private ImageButton btnDelete;
    }

}
