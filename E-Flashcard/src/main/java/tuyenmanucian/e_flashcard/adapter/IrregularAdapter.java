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

import tuyenmanucian.e_flashcard.R;
import tuyenmanucian.e_flashcard.models.Irregular;

public class IrregularAdapter extends ArrayAdapter<Irregular> {

    private Context mContext;
    private int mLayout;
    private ArrayList<Irregular> mList;


    public IrregularAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Irregular> objects) {
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

            viewHolder =new ViewHolder();
            viewHolder.tvInf = (convertView.findViewById(R.id.tvInf));
            viewHolder.tvSp = (convertView.findViewById(R.id.tvSp));
            viewHolder.tvPp = (convertView.findViewById(R.id.tvPp));
            viewHolder.tvMean = (convertView.findViewById(R.id.tvMean));

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = ((ViewHolder) convertView.getTag());
        }

        Irregular irregular = mList.get(position);
        viewHolder.tvInf.setText(irregular.getInfinitive());
        viewHolder.tvSp.setText(irregular.getSimplePast());
        viewHolder.tvPp.setText(irregular.getPastParticiple());
        viewHolder.tvMean.setText(irregular.getMean());
        return convertView;
    }

    public class ViewHolder{
        public TextView tvInf, tvSp, tvPp, tvMean;
    }
}
