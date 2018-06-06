package tuyenmanucian.e_flashcard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tuyenmanucian.e_flashcard.R;
import tuyenmanucian.e_flashcard.models.Word;

public class HistoryAdapter extends ArrayAdapter<Word> {
    private Context mContext;
    private int mLayout;
    private ArrayList<Word> mList;

    public HistoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Word> objects) {
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

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = ((HistoryAdapter.ViewHolder) convertView.getTag());
        }

        Word history = mList.get(position);
        viewHolder.tvWord.setText(history.getVocabulary());
        viewHolder.tvMean.setText(history.getMean());

        return convertView;
    }

    public class ViewHolder{
        public TextView tvWord, tvMean;
    }
}
