package tuyenmanucian.e_flashcard;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import tuyenmanucian.e_flashcard.adapter.WordsAdapter;
import tuyenmanucian.e_flashcard.models.Word;

public class WordsActivity extends AppCompatActivity {


    private DatabaseReference mDatabase;
    private Button btnAdd, btnRemove;
    private EditText etSearch;

    private ListView lvWord;
    private WordsAdapter mAdapter = null;
    private ArrayList<Word> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);


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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View layout = getLayoutInflater().inflate(R.layout.dialog_add_word, null);
                final EditText etWord = layout.findViewById(R.id.etWord);
                final EditText etMean = layout.findViewById(R.id.etMean);

                new AlertDialog.Builder(WordsActivity.this)
                        .setTitle("Add word")
                        .setView(layout)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String vocabulary = etWord.getText().toString();
                                String mean = etMean.getText().toString();
                                Word word = new Word(vocabulary, mean);
//                                mList.add(0,vocabulary + " : " + mean);
                                mDatabase.child("YourWord").push().setValue(word);
                                mAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create()
                        .show();
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (!etSearch.getText().toString().equals("")) { //if edittext include text
                    btnRemove.setVisibility(View.VISIBLE);
                } else { //not include text
                    btnRemove.setVisibility(View.GONE);
                }
                WordsActivity.this.mAdapter.getFilter().filter(charSequence.toString());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText("");
            }
        });
        lvWord.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(WordsActivity.this, "OK", Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }


    private void addControls() {

        btnRemove = findViewById(R.id.btnRemove);
        btnAdd = findViewById(R.id.btnAdd);
        lvWord=findViewById(R.id.lvWord);
        etSearch = findViewById(R.id.etSearch);

        mList = new ArrayList<Word>();
        mAdapter = new WordsAdapter(this, R.layout.item_words, mList);
        lvWord.setAdapter(mAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }
}
