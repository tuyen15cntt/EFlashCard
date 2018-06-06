  package tuyenmanucian.e_flashcard;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import tuyenmanucian.e_flashcard.adapter.CardAdapter;
import tuyenmanucian.e_flashcard.models.Card;
import tuyenmanucian.e_flashcard.models.Period;

public class CardActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private CardAdapter  mAdapter;
    private ArrayList<Period> listPeriod;
    private ArrayList<Card> mList;
    private ViewPager vpCard;
    private String []aPeriods;

    private Button btnShow;
    private Spinner spnPeriods;
    private CheckBox cbWord, cbPron, cbMean;

    private TextView tvCurrentOverTotal;
    private int mCurrentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        addControls();
        addEvents();
        getPeriod();
        setViewPager();

    }

    private int idPeriod = 0;
    private void getPeriod() {
        mDatabase.child("Period").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Period period = new Period(idPeriod, key);
                Log.d("a", period.toString());
                listPeriod.add(period);
                idPeriod++;

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getData(String periodname) {
        //Toast.makeText(CardActivity.this,"Da vao ham getData", Toast.LENGTH_SHORT).show();

        mDatabase.child("Period").child(periodname).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Card card = dataSnapshot.getValue(Card.class);

                mList.add(card);
                mAdapter.notifyDataSetChanged();
                setCurrentOverTotal(mCurrentIndex);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    private void addEvents() {
        vpCard.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentIndex = position;
                setCurrentOverTotal(mCurrentIndex);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setCurrentOverTotal(int index){
        tvCurrentOverTotal.setText((index + 1) + "/" + mList.size());
    }

    private void addControls() {

        vpCard = findViewById(R.id.vpCard);
        tvCurrentOverTotal = findViewById(R.id.tvCurrentOverTotal);

        spnPeriods = findViewById(R.id.spnPeriods);

        listPeriod = new ArrayList<Period>();
        mList = new ArrayList<Card>();
        aPeriods = getResources().getStringArray(R.array.Periods);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,aPeriods);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnPeriods.setAdapter(adapter);
        spnPeriods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {

                mList.removeAll(mList);
                getData(spnPeriods.getSelectedItem().toString());
                setViewPager();
                mAdapter.notifyDataSetChanged();
                Toast.makeText(CardActivity.this, spnPeriods.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void setViewPager(){
        mAdapter = new CardAdapter(this, R.layout.item_card, mList,
              true, true, true);

        mCurrentIndex = 0;
        vpCard.setAdapter(mAdapter);
        vpCard.setCurrentItem(mCurrentIndex);

    }

}
