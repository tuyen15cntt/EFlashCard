package tuyenmanucian.e_flashcard.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tuyenmanucian.e_flashcard.R;
import tuyenmanucian.e_flashcard.models.Card;

public class CardAdapter extends PagerAdapter{
    private Context mContext;
    private int mLayout;
    private ArrayList<Card> mList;

    private boolean mIsShowWord;
    private boolean mIsShowPron;
    private boolean mIsShowMean;

    public CardAdapter(Context c, int layout, ArrayList<Card> list,
                       boolean showW, boolean showP, boolean showM){
        mContext = c;
        mLayout = layout;
        mList = list;

        mIsShowWord = showW;
        mIsShowPron = showP;
        mIsShowMean = showM;


    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object  ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View layout = inflater.inflate(mLayout, container, false);

        final Card card = mList.get(position);
        final TextView tvWord = layout.findViewById(R.id.tvWord);
        TextView tvEssen = layout.findViewById(R.id.tvEssential);
        final TextView tvPron = layout.findViewById(R.id.tvPronunciation);
        final TextView tvMean = layout.findViewById(R.id.tvMean);
        ImageView imageView  = layout.findViewById(R.id.imgView);
        if(card.getImage()!=null)
            Picasso.with(mContext).load(card.getImage()).into(imageView);
        else
            Picasso.with(mContext).load(R.drawable.flashcard).into(imageView);


        tvWord.setText(mIsShowWord ? card.getWord() : "???");
        tvEssen.setText(card.getEssential());
        //imageView.setImageBitmap(card.getImage());
        tvPron.setText(mIsShowPron ? card.getPronunciation() : "???");
        tvMean.setText(mIsShowMean ? card.getMean() : "???");

        tvWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIsShowWord)
                {
                    mIsShowWord = !mIsShowWord;
                    tvWord.setText(mIsShowWord ? card.getWord() : "???");
                }
                else
                {
                    mIsShowWord = !mIsShowWord;
                    tvWord.setText(mIsShowWord ? card.getWord() : "???");
                }

            }
        });

        tvPron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIsShowPron)
                {
                    mIsShowPron = !mIsShowPron;
                    tvPron.setText(mIsShowPron ? card.getPronunciation() : "???");
                }
                else
                {
                    mIsShowPron = !mIsShowPron;
                    tvPron.setText(mIsShowPron ? card.getPronunciation() : "???");
                }

            }
        });

        tvMean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIsShowMean)
                {
                    mIsShowMean = !mIsShowMean;
                    tvMean.setText(mIsShowMean ? card.getMean() : "???");
                }
                else
                {
                    mIsShowMean = !mIsShowMean;
                    tvMean.setText(mIsShowMean ? card.getMean() : "???");
                }

            }
        });
        container.addView(layout);
        return layout;
    }

    public static void Show(String link)
    {
        Log.d("showlinkfromcard", link);

    }







}
