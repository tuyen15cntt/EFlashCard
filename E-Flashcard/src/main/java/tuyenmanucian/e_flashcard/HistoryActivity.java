package tuyenmanucian.e_flashcard;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import tuyenmanucian.e_flashcard.adapter.HistoryAdapter;
import tuyenmanucian.e_flashcard.adapter.WordsAdapter;
import tuyenmanucian.e_flashcard.models.Irregular;
import tuyenmanucian.e_flashcard.models.Word;

public class HistoryActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private ListView lvHistory;
    private HistoryAdapter mAdapter = null;
    private ArrayList<Word> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        addControls();
        addEvents();
        getData();

    }

    private void getData() {

        mDatabase.child("YourWord").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Word word = dataSnapshot.getValue(Word.class);
//                Log.d("YourWord", word.getVocabulary().toString());
                mList.add(word);
                mAdapter.notifyDataSetChanged();

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

        lvHistory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(HistoryActivity.this, "OK", Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }


    private void addControls() {

        lvHistory = findViewById(R.id.lvHistory);

        mList = new ArrayList<Word>();
        mAdapter = new HistoryAdapter(this, R.layout.item_words, mList);
        lvHistory.setAdapter(mAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }
}
