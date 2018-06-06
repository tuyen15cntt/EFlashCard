package tuyenmanucian.e_flashcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import tuyenmanucian.e_flashcard.adapter.IrregularAdapter;
import tuyenmanucian.e_flashcard.models.Irregular;

public class IrregularActivity extends AppCompatActivity {

    private ListView lvIrregular;
    private Button btnRemove;
    private EditText etIrre;
    private DatabaseReference mDatabase;

    private ArrayList<Irregular> mList;
    private IrregularAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irregular);

        addControls();
        addEvents();
        getData();
    }

    private void getData() {
         mDatabase.child("Irregular").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Irregular irregular = dataSnapshot.getValue(Irregular.class);
                mList.add(irregular);
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

    private void addControls() {
        btnRemove=findViewById(R.id.btnRemove);
        etIrre=findViewById(R.id.etIrre);
        lvIrregular=findViewById(R.id.lvIrregular);

        mList = new ArrayList<Irregular>();
        mAdapter = new IrregularAdapter(this, R.layout.item_irregular, mList);
        lvIrregular.setAdapter(mAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void addEvents() {
        etIrre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!etIrre.getText().toString().equals("")) { //if edittext include text
                    btnRemove.setVisibility(View.VISIBLE);
                } else { //not include text
                    btnRemove.setVisibility(View.GONE);
                }
                String key = charSequence.toString();
                IrregularActivity.this.mAdapter.getFilter().filter(key);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etIrre.setText("");
            }
        });
    }

}
