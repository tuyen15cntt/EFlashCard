package tuyenmanucian.e_flashcard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tuyenmanucian.e_flashcard.models.Card;
import tuyenmanucian.e_flashcard.models.Irregular;

public class MainActivity extends AppCompatActivity {


    private static final Integer STATUS_OK = 1 ;
    private static final Integer STATUS_ERROR = 0 ;
    private Button btnClear, btnFlashcard, btnYourWord, btnTranslate, btnIrregular, btnHistory, btnSetting;
    private DatabaseReference mDatabase;

    private AutoCompleteTextView actv;
    private ArrayList<Card> mList;
    private ArrayList<String> mListWord;
    private ArrayAdapter<String> mAdapter;

    private String[] aPeriods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash);
        getSupportActionBar().hide();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mList = new ArrayList<Card>();
        mListWord = new ArrayList<String>();
        DownloadData downloadData;
        downloadData = new DownloadData();
        downloadData.execute();
       // getWord();
       // start();


        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        //readJson(); //init data
        //readFromFB();
        //read();

    }

    private void start() {
        setContentView(R.layout.activity_main);
        getSupportActionBar().show();
        addControls();
        addEvents();
    }

    private void getWord() {
        aPeriods = getResources().getStringArray(R.array.Periods);
        for (int i=0; i < aPeriods.length; i++ ){
          //  getData(aPeriods[i]);
            mDatabase.child("Period").child(aPeriods[i]).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Card card = dataSnapshot.getValue(Card.class);
                    mList.add(card);
                    mListWord.add(card.getWord());
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


    }

    private void getData(String temp) {
//        mDatabase.child("Period").child(temp).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Card card = dataSnapshot.getValue(Card.class);
//                mList.add(card);
//                mListWord.add(card.getWord());
//                mAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    private void read() {
        String json = "{" +
                "  \"WordList\": {" +
                "    \"-xmlns:xsi\": \"http://www.w3.org/2001/XMLSchema-instance\"," +
                "    \"Card\": [" +
                "      {" +
                "        \"Word\": \"adjustment\"," +
                "        \"Essential\": \"Noun\"," +
                "        \"Pronunciation\": \"/ə'dʤʌstmənt/\"," +
                "        \"Image\": \"http://600tuvungtoeic.com/template/english/images/study/adjustment.jpg\"," +
                "        \"Mean\": \"điều chỉnh, chỉnh lý, sự sửa lại cho đúng\"" +
                "      }," +
                "      {" +
                "        \"Word\": \"automatically\"," +
                "        \"Essential\": \"Adverb\"," +
                "        \"Pronunciation\": \"/ˌɔːtəˈmætɪkli/\"," +
                "        \"Image\": \"http://600tuvungtoeic.com/template/english/images/study/automatically.jpg\"," +
                "        \"Mean\": \"‹một cách› tự động\"" +
                "      }," +
                "      {" +
                "        \"Word\": \"crucial\"," +
                "        \"Essential\": \"Adjective\"," +
                "        \"Pronunciation\": \"/'kru:ʃjəl/\"," +
                "        \"Image\": \"http://600tuvungtoeic.com/template/english/images/study/crucial.jpg\"," +
                "        \"Mean\": \"cốt yếu, chủ yếu, có tính quyết định\"" +
                "      }," +
                "      {" +
                "        \"Word\": \"discrepancy\"," +
                "        \"Essential\": \"Noun\"," +
                "        \"Pronunciation\": \"/dis'krepənsi/\"," +
                "        \"Image\": \"http://600tuvungtoeic.com/template/english/images/study/discrepancy.jpg\"," +
                "        \"Mean\": \"‹sự› khác nhau, trái ngược nhau; không nhất quán/thống nhất\"" +
                "      }," +
                "      {" +
                "        \"Word\": \"disturb\"," +
                "        \"Essential\": \"verb\"," +
                "        \"Pronunciation\": \"/dis'tə:b/\"," +
                "        \"Image\": \"http://600tuvungtoeic.com/template/english/images/study/disturb.jpg\"," +
                "        \"Mean\": \"quấy rầy, quấy rối, làm náo động, gây náo loạn, làm ồn, làm phiền\"" +
                "      }," +
                "      {" +
                "        \"Word\": \"liability\"," +
                "        \"Essential\": \"Noun\"," +
                "        \"Pronunciation\": \"/,laiə'biliti/\"," +
                "        \"Image\": \"http://600tuvungtoeic.com/template/english/images/study/liability.png\"," +
                "        \"Mean\": \"trách nhiệm, nghĩa vụ pháp lý; nguy cơ, điều gây khó khăn trở ngại\"" +
                "      }," +
                "      {" +
                "        \"Word\": \"reflection\"," +
                "        \"Essential\": \"Noun\"," +
                "        \"Pronunciation\": \"/rɪˈflekʃn/\"," +
                "        \"Image\": \"http://600tuvungtoeic.com/template/english/images/study/reflection.jpg\"," +
                "        \"Mean\": \"‹sự› phản chiếu, phản xạ, phản ánh, hình ảnh; phê phán, nhận xét\"" +
                "      }," +
                "      {" +
                "        \"Word\": \"run\"," +
                "        \"Essential\": \"verb\"," +
                "        \"Pronunciation\": \"/rʌn/\"," +
                "        \"Image\": \"http://600tuvungtoeic.com/template/english/images/study/run.jpg\"," +
                "        \"Mean\": \"chạy, vận hành, hoạt động, thực hiện\"" +
                "      }," +
                "      {" +
                "        \"Word\": \"scan\"," +
                "        \"Essential\": \"verb\"," +
                "        \"Pronunciation\": \"/skæn/\"," +
                "        \"Image\": \"http://600tuvungtoeic.com/template/english/images/study/scan.jpg\"," +
                "        \"Mean\": \"xem lướt, xem qua\"" +
                "      }," +
                "      {" +
                "        \"Word\": \"subtract\"," +
                "        \"Essential\": \"verb\"," +
                "        \"Pronunciation\": \"/səb'trækt/\"," +
                "        \"Image\": \"http://600tuvungtoeic.com/template/english/images/study/subtract.gif\"," +
                "        \"Mean\": \"trừ đi, khấu trừ; loại ra, lấy ra khỏi\"" +
                "      }," +
                "      {" +
                "        \"Word\": \"tedious\"," +
                "        \"Essential\": \"Adjective\"," +
                "        \"Pronunciation\": \"/'ti:djəs/\"," +
                "        \"Image\": \"https://www.studytienganh.vn/upload/2017/11/9418.png\"," +
                "        \"Mean\": \"chán ngắt, tẻ nhạt, nhạt nhẽo, buồn tẻ\"" +
                "      }," +
                "      {" +
                "        \"Word\": \"verify\"," +
                "        \"Essential\": \"verb\"," +
                "        \"Pronunciation\": \"/'verifai/\"," +
                "        \"Image\": \"http://600tuvungtoeic.com/template/english/images/study/tedious.jpg\"," +
                "        \"Mean\": \"kiểm tra lại, thẩm tra, xác minh\"" +
                "      }" +
                "    ]" +
                "  }" +
                "}";

        try {


            JSONObject myjson = new JSONObject(json);

            JSONObject irregularListJsonObject = (JSONObject) myjson.get("WordList");
            JSONArray irregularJsonArray =  irregularListJsonObject.getJSONArray("Card");
            //Toast.makeText(this, irregularList.toString(), Toast.LENGTH_LONG).show();
            int lengthOfIrregularJsonArray = irregularJsonArray.length();
            Log.d("json", ""+lengthOfIrregularJsonArray);
            for (int i = 0; i < lengthOfIrregularJsonArray; ++i) {
                JSONObject rec = irregularJsonArray.getJSONObject(i);

                String word = rec.getString("Word");
                String essential = rec.getString("Essential");
                String pronunciation = rec.getString("Pronunciation");
                String image = rec.getString("Image");
                String mean = rec.getString("Mean");

                Card item = new Card(word, essential, pronunciation, image, mean);
                Log.d("json", ""+item.toString());

                mDatabase.child("Period").child("Inventory").push().setValue(item);

                // ...
            }
        } catch (JSONException e) {
            Log.d("json", "Loi");
            Toast.makeText(this, "a", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void readFromFB() {
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Irregular irregular = dataSnapshot.getValue(Irregular.class);

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

    private void readJson() {
        String jsontext = "{" +
                "\"IrregularList\": {" +
                "\"Irregular\": [" +
                "{" +
                "\"Infinitive\": \"abide\"," +
                "\"SimplePast\": \"abode/ abided\"," +
                "\"PastParticiple\": \"abiden/ aboded\"," +
                "\"Mean\": \"lưu trú, lưu lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"arise\"," +
                "\"SimplePast\": \"arose\"," +
                "\"PastParticiple\": \"arisen\"," +
                "\"Mean\": \"phát sinh\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"awake\"," +
                "\"SimplePast\": \"awoke\"," +
                "\"PastParticiple\": \"awoken\"," +
                "\"Mean\": \"đánh thức, thức\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"backslide\"," +
                "\"SimplePast\": \"backslid\"," +
                "\"PastParticiple\": \"backslid\"," +
                "\"Mean\": \"lại phạm tội\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"be\"," +
                "\"SimplePast\": \"was/were\"," +
                "\"PastParticiple\": \"been\"," +
                "\"Mean\": \"thì, là, bị, ở\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"bear\"," +
                "\"SimplePast\": \"bore\"," +
                "\"PastParticiple\": \"born\"," +
                "\"Mean\": \"mang, chịu đựng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"beat\"," +
                "\"SimplePast\": \"beat\"," +
                "\"PastParticiple\": \"beaten\"," +
                "\"Mean\": \"đánh đập\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"become\"," +
                "\"SimplePast\": \"became\"," +
                "\"PastParticiple\": \"become\"," +
                "\"Mean\": \"trở nên\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"befall\"," +
                "\"SimplePast\": \"befell\"," +
                "\"PastParticiple\": \"befallen\"," +
                "\"Mean\": \"xảy ra, xảy đến\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"begin\"," +
                "\"SimplePast\": \"began\"," +
                "\"PastParticiple\": \"begun\"," +
                "\"Mean\": \"bắt đầu\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"behold\"," +
                "\"SimplePast\": \"beheld\"," +
                "\"PastParticiple\": \"beheld\"," +
                "\"Mean\": \"ngắm nhìn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"bend\"," +
                "\"SimplePast\": \"bent\"," +
                "\"PastParticiple\": \"bent\"," +
                "\"Mean\": \"bẻ cong\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"beset\"," +
                "\"SimplePast\": \"beset\"," +
                "\"PastParticiple\": \"beset\"," +
                "\"Mean\": \"bao quanh\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"bespeak\"," +
                "\"SimplePast\": \"bespoke\"," +
                "\"PastParticiple\": \"bespoken\"," +
                "\"Mean\": \"chứng tỏ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"bid\"," +
                "\"SimplePast\": \"bid\"," +
                "\"PastParticiple\": \"bid\"," +
                "\"Mean\": \"trả giá\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"bide\"," +
                "\"SimplePast\": \"bided/bode\"," +
                "\"PastParticiple\": \"bided/bidden\"," +
                "\"Mean\": \"chờ đợi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"bind\"," +
                "\"SimplePast\": \"bound\"," +
                "\"PastParticiple\": \"bound\"," +
                "\"Mean\": \"buộc, trói\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"bite\"," +
                "\"SimplePast\": \"bit\"," +
                "\"PastParticiple\": \"bitten\"," +
                "\"Mean\": \"cắn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"bleed\"," +
                "\"SimplePast\": \"bled\"," +
                "\"PastParticiple\": \"bled\"," +
                "\"Mean\": \"chảy máu\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"bless\"," +
                "\"SimplePast\": \"blessed/blest\"," +
                "\"PastParticiple\": \"blessed/blest\"," +
                "\"Mean\": \"ban phúc\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"blow\"," +
                "\"SimplePast\": \"blew\"," +
                "\"PastParticiple\": \"blown\"," +
                "\"Mean\": \"thổi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"break\"," +
                "\"SimplePast\": \"broke\"," +
                "\"PastParticiple\": \"broken\"," +
                "\"Mean\": \"đập vỡ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"breed\"," +
                "\"SimplePast\": \"bred\"," +
                "\"PastParticiple\": \"bred\"," +
                "\"Mean\": \"nuôi, dạy dỗ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"bring\"," +
                "\"SimplePast\": \"brought\"," +
                "\"PastParticiple\": \"brought\"," +
                "\"Mean\": \"mang đến\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"broadcast\"," +
                "\"SimplePast\": \"broadcast\"," +
                "\"PastParticiple\": \"broadcast\"," +
                "\"Mean\": \"phát thanh, phát sóng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"build\"," +
                "\"SimplePast\": \"built\"," +
                "\"PastParticiple\": \"built\"," +
                "\"Mean\": \"xây dựng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"burn\"," +
                "\"SimplePast\": \"burnt/ burned\"," +
                "\"PastParticiple\": \"burnt/ burned\"," +
                "\"Mean\": \"đốt, cháy\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"burst\"," +
                "\"SimplePast\": \"burst/brast\"," +
                "\"PastParticiple\": \"burst/brast\"," +
                "\"Mean\": \"bùng nổ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"bust\"," +
                "\"SimplePast\": \"bust/busted\"," +
                "\"PastParticiple\": \"bust/busted\"," +
                "\"Mean\": \"làm vỡ, bắt giữ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"buy\"," +
                "\"SimplePast\": \"bought\"," +
                "\"PastParticiple\": \"bought\"," +
                "\"Mean\": \"mua\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"cast\"," +
                "\"SimplePast\": \"cast\"," +
                "\"PastParticiple\": \"cast\"," +
                "\"Mean\": \"ném, tung\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"can\"," +
                "\"SimplePast\": \"could\"," +
                "\"Mean\": \"có thể\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"catch\"," +
                "\"SimplePast\": \"caught\"," +
                "\"PastParticiple\": \"caught\"," +
                "\"Mean\": \"bắt, chụp\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"chide\"," +
                "\"SimplePast\": \"chid/ chided\"," +
                "\"PastParticiple\": \"chid/ chidden/ chided\"," +
                "\"Mean\": \"mắng chửi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"choose\"," +
                "\"SimplePast\": \"chose\"," +
                "\"PastParticiple\": \"chosen\"," +
                "\"Mean\": \"chọn, lựa\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"clad\"," +
                "\"SimplePast\": \"clad\"," +
                "\"PastParticiple\": \"clad\"," +
                "\"Mean\": \"che phủ, bao bọc, tráng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"clap\"," +
                "\"SimplePast\": \"clapped/clapt\"," +
                "\"PastParticiple\": \"clapped/clapt\"," +
                "\"Mean\": \"vỗ, vỗ tay, đặt mạnh\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"cleave\"," +
                "\"SimplePast\": \"clove/ cleft/ cleaved\"," +
                "\"PastParticiple\": \"cloven/ cleft/ cleaved\"," +
                "\"Mean\": \"chẻ, tách hai\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"cling\"," +
                "\"SimplePast\": \"clung\"," +
                "\"PastParticiple\": \"clung\"," +
                "\"Mean\": \"dính chặt\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"clothe\"," +
                "\"SimplePast\": \"clad/clothed\"," +
                "\"PastParticiple\": \"clad/clothed\"," +
                "\"Mean\": \"mặc quần áo\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"come\"," +
                "\"SimplePast\": \"came\"," +
                "\"PastParticiple\": \"come\"," +
                "\"Mean\": \"đến, đi đến\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"cost\"," +
                "\"SimplePast\": \"cost\"," +
                "\"PastParticiple\": \"cost\"," +
                "\"Mean\": \"có giá là\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"creep\"," +
                "\"SimplePast\": \"crept\"," +
                "\"PastParticiple\": \"crept\"," +
                "\"Mean\": \"leo, bò, trườn, lết\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"crow\"," +
                "\"SimplePast\": \"crew/crewed\"," +
                "\"PastParticiple\": \"crowed\"," +
                "\"Mean\": \"gáy (gà)\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"cut\"," +
                "\"SimplePast\": \"cut\"," +
                "\"PastParticiple\": \"cut\"," +
                "\"Mean\": \"cắt, chặt\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"deal\"," +
                "\"SimplePast\": \"dealt\"," +
                "\"PastParticiple\": \"dealt\"," +
                "\"Mean\": \"ngã giá, giao thiệp\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"dig\"," +
                "\"SimplePast\": \"dug/digged\"," +
                "\"PastParticiple\": \"dug/digged\"," +
                "\"Mean\": \"đào\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"dive\"," +
                "\"SimplePast\": \"dove/ dived\"," +
                "\"PastParticiple\": \"dove/dived\"," +
                "\"Mean\": \"lặn, lao xuống\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"do\"," +
                "\"SimplePast\": \"did\"," +
                "\"PastParticiple\": \"done\"," +
                "\"Mean\": \"làm\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"dow\"," +
                "\"SimplePast\": \"dowed/dought\"," +
                "\"PastParticiple\": \"dowed/dought\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"draw\"," +
                "\"SimplePast\": \"drew\"," +
                "\"PastParticiple\": \"drawn\"," +
                "\"Mean\": \"vẽ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"dream\"," +
                "\"SimplePast\": \"dreamt/ dreamed\"," +
                "\"PastParticiple\": \"dreamt/ dreamed\"," +
                "\"Mean\": \"mơ thấy\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"drink\"," +
                "\"SimplePast\": \"drank\"," +
                "\"PastParticiple\": \"drunk\"," +
                "\"Mean\": \"uống\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"drive\"," +
                "\"SimplePast\": \"drove\"," +
                "\"PastParticiple\": \"driven\"," +
                "\"Mean\": \"lái xe\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"dwell\"," +
                "\"SimplePast\": \"dwelt\"," +
                "\"PastParticiple\": \"dwelt\"," +
                "\"Mean\": \"trú ngụ, ở\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"earn\"," +
                "\"SimplePast\": \"earned/earnt\"," +
                "\"PastParticiple\": \"earned/earnt\"," +
                "\"Mean\": \"kiếm sống\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"eat\"," +
                "\"SimplePast\": \"ate\"," +
                "\"PastParticiple\": \"eaten\"," +
                "\"Mean\": \"ăn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"fall\"," +
                "\"SimplePast\": \"fell\"," +
                "\"PastParticiple\": \"fallen\"," +
                "\"Mean\": \"ngã, rơi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"feed\"," +
                "\"SimplePast\": \"fed\"," +
                "\"PastParticiple\": \"fed\"," +
                "\"Mean\": \"cho ăn, ăn, nuôi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"feel\"," +
                "\"SimplePast\": \"felt\"," +
                "\"PastParticiple\": \"felt\"," +
                "\"Mean\": \"cảm thấy\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"fight\"," +
                "\"SimplePast\": \"fought\"," +
                "\"PastParticiple\": \"fought\"," +
                "\"Mean\": \"chiến đấu\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"find\"," +
                "\"SimplePast\": \"found\"," +
                "\"PastParticiple\": \"found\"," +
                "\"Mean\": \"tìm thấy, thấy\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"fit\"," +
                "\"SimplePast\": \"fitted/fit\"," +
                "\"PastParticiple\": \"fitted/fit\"," +
                "\"Mean\": \"làm vừa vặn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"flee\"," +
                "\"SimplePast\": \"fled\"," +
                "\"PastParticiple\": \"fled\"," +
                "\"Mean\": \"chạy trốn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"fling\"," +
                "\"SimplePast\": \"flung\"," +
                "\"PastParticiple\": \"flung\"," +
                "\"Mean\": \"tung, quăng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"fly\"," +
                "\"SimplePast\": \"flew\"," +
                "\"PastParticiple\": \"flown\"," +
                "\"Mean\": \"bay\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"forbear\"," +
                "\"SimplePast\": \"forbore\"," +
                "\"PastParticiple\": \"forborne\"," +
                "\"Mean\": \"nhịn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"forbid\"," +
                "\"SimplePast\": \"forbade/ forbad\"," +
                "\"PastParticiple\": \"forbidden\"," +
                "\"Mean\": \"cấm đoán, cấm\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"forecast\"," +
                "\"SimplePast\": \"forecast/ forecasted\"," +
                "\"PastParticiple\": \"forecast/ forecasted\"," +
                "\"Mean\": \"tiên đoán, dự đoán, dự báo\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"foresee\"," +
                "\"SimplePast\": \"foresaw\"," +
                "\"PastParticiple\": \"forseen\"," +
                "\"Mean\": \"thấy trước\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"foretell\"," +
                "\"SimplePast\": \"foretold\"," +
                "\"PastParticiple\": \"foretold\"," +
                "\"Mean\": \"đoán trước\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"forget\"," +
                "\"SimplePast\": \"forgot\"," +
                "\"PastParticiple\": \"forgotten\"," +
                "\"Mean\": \"quên\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"forgive\"," +
                "\"SimplePast\": \"forgave\"," +
                "\"PastParticiple\": \"forgiven\"," +
                "\"Mean\": \"tha thứ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"forsake\"," +
                "\"SimplePast\": \"forsook\"," +
                "\"PastParticiple\": \"forsaken\"," +
                "\"Mean\": \"ruồng bỏ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"freeze\"," +
                "\"SimplePast\": \"froze\"," +
                "\"PastParticiple\": \"frozen\"," +
                "\"Mean\": \"(làm) đông lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"get\"," +
                "\"SimplePast\": \"got\"," +
                "\"PastParticiple\": \"got/ gotten\"," +
                "\"Mean\": \"có được\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"gild\"," +
                "\"SimplePast\": \"gilt/ gilded\"," +
                "\"PastParticiple\": \"gilt/ gilded\"," +
                "\"Mean\": \"mạ vàng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"gird\"," +
                "\"SimplePast\": \"girt/ girded\"," +
                "\"PastParticiple\": \"girt/ girded\"," +
                "\"Mean\": \"đeo vào\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"give\"," +
                "\"SimplePast\": \"gave\"," +
                "\"PastParticiple\": \"given\"," +
                "\"Mean\": \"cho\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"go\"," +
                "\"SimplePast\": \"went\"," +
                "\"PastParticiple\": \"gone\"," +
                "\"Mean\": \"đi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"grave\"," +
                "\"SimplePast\": \"grove/graved\"," +
                "\"PastParticiple\": \"graven/graved\"," +
                "\"Mean\": \"đào huyệt\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"grind\"," +
                "\"SimplePast\": \"ground\"," +
                "\"PastParticiple\": \"ground\"," +
                "\"Mean\": \"nghiền, xay\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"grow\"," +
                "\"SimplePast\": \"grew\"," +
                "\"PastParticiple\": \"grown\"," +
                "\"Mean\": \"mọc, trồng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"hang\"," +
                "\"SimplePast\": \"hung\"," +
                "\"PastParticiple\": \"hung\"," +
                "\"Mean\": \"móc lên, treo lên\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"have\"," +
                "\"SimplePast\": \"had\"," +
                "\"PastParticiple\": \"had\"," +
                "\"Mean\": \"có\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"hear\"," +
                "\"SimplePast\": \"heard\"," +
                "\"PastParticiple\": \"heard\"," +
                "\"Mean\": \"nghe\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"heave\"," +
                "\"SimplePast\": \"hove/ heaved\"," +
                "\"PastParticiple\": \"hove/ heaved\"," +
                "\"Mean\": \"trục lên\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"hide\"," +
                "\"SimplePast\": \"hid\"," +
                "\"PastParticiple\": \"hidden\"," +
                "\"Mean\": \"giấu, trốn, ẩn, nấp\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"hit\"," +
                "\"SimplePast\": \"hit\"," +
                "\"PastParticiple\": \"hit\"," +
                "\"Mean\": \"đụng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"hold\"," +
                "\"SimplePast\": \"held\"," +
                "\"PastParticiple\": \"held/holden\"," +
                "\"Mean\": \"giữ, nắm\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"hurt\"," +
                "\"SimplePast\": \"hurt\"," +
                "\"PastParticiple\": \"hurt\"," +
                "\"Mean\": \"làm đau\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"inlay\"," +
                "\"SimplePast\": \"inlaid\"," +
                "\"PastParticiple\": \"inlaid\"," +
                "\"Mean\": \"dát, khảm\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"input\"," +
                "\"SimplePast\": \"input\"," +
                "\"PastParticiple\": \"input\"," +
                "\"Mean\": \"đưa vào (máy điện toán)\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"inset\"," +
                "\"SimplePast\": \"inset\"," +
                "\"PastParticiple\": \"inset\"," +
                "\"Mean\": \"cài, ghép\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"keep\"," +
                "\"SimplePast\": \"kept\"," +
                "\"PastParticiple\": \"kept\"," +
                "\"Mean\": \"giữ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"kneel\"," +
                "\"SimplePast\": \"knelt/ kneeled\"," +
                "\"PastParticiple\": \"knelt/ kneeled\"," +
                "\"Mean\": \"quỳ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"knit\"," +
                "\"SimplePast\": \"knit/ knitted\"," +
                "\"PastParticiple\": \"knit/ knitted\"," +
                "\"Mean\": \"đan\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"know\"," +
                "\"SimplePast\": \"knew\"," +
                "\"PastParticiple\": \"known\"," +
                "\"Mean\": \"biết, quen biết\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"lade\"," +
                "\"SimplePast\": \"laded\"," +
                "\"PastParticiple\": \"laden/laded\"," +
                "\"Mean\": \"rời khỏi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"lay\"," +
                "\"SimplePast\": \"laid\"," +
                "\"PastParticiple\": \"laid\"," +
                "\"Mean\": \"đặt, để\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"lead\"," +
                "\"SimplePast\": \"led\"," +
                "\"PastParticiple\": \"led\"," +
                "\"Mean\": \"dẫn dắt, lãnh đạo\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"leap\"," +
                "\"SimplePast\": \"leapt\"," +
                "\"PastParticiple\": \"leapt\"," +
                "\"Mean\": \"nhảy, nhảy qua\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"learn\"," +
                "\"SimplePast\": \"learnt/ learned\"," +
                "\"PastParticiple\": \"learnt/ learned\"," +
                "\"Mean\": \"học, được biết\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"leave\"," +
                "\"SimplePast\": \"left\"," +
                "\"PastParticiple\": \"left\"," +
                "\"Mean\": \"ra đi, để lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"lend\"," +
                "\"SimplePast\": \"lent\"," +
                "\"PastParticiple\": \"lent\"," +
                "\"Mean\": \"cho mượn (vay)\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"let\"," +
                "\"SimplePast\": \"let\"," +
                "\"PastParticiple\": \"let\"," +
                "\"Mean\": \"cho phép, để cho\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"lie\"," +
                "\"SimplePast\": \"lay\"," +
                "\"PastParticiple\": \"lain\"," +
                "\"Mean\": \"nằm\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"light\"," +
                "\"SimplePast\": \"lit/ lighted\"," +
                "\"PastParticiple\": \"lit/ lighted\"," +
                "\"Mean\": \"thắp sáng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"lose\"," +
                "\"SimplePast\": \"lost\"," +
                "\"PastParticiple\": \"lost\"," +
                "\"Mean\": \"làm mất, mất\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"make\"," +
                "\"SimplePast\": \"made\"," +
                "\"PastParticiple\": \"made\"," +
                "\"Mean\": \"chế tạo, sản xuất\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"may\"," +
                "\"SimplePast\": \"might\"," +
                "\"Mean\": \"có thể\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"mean\"," +
                "\"SimplePast\": \"meant\"," +
                "\"PastParticiple\": \"meant\"," +
                "\"Mean\": \"có nghĩa là\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"meet\"," +
                "\"SimplePast\": \"met\"," +
                "\"PastParticiple\": \"met\"," +
                "\"Mean\": \"gặp mặt\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"melt\"," +
                "\"SimplePast\": \"melted/molt\"," +
                "\"PastParticiple\": \"melted/molten\"," +
                "\"Mean\": \"nóng chảy\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"mislay\"," +
                "\"SimplePast\": \"mislaid\"," +
                "\"PastParticiple\": \"mislaid\"," +
                "\"Mean\": \"để lạc mất\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"misread\"," +
                "\"SimplePast\": \"misread\"," +
                "\"PastParticiple\": \"misread\"," +
                "\"Mean\": \"đọc sai\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"misspell\"," +
                "\"SimplePast\": \"misspelt\"," +
                "\"PastParticiple\": \"misspelt\"," +
                "\"Mean\": \"viết sai chính tả\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"mistake\"," +
                "\"SimplePast\": \"mistook\"," +
                "\"PastParticiple\": \"mistaken\"," +
                "\"Mean\": \"phạm lỗi, nhầm lẫn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"misunderstand\"," +
                "\"SimplePast\": \"misunderstood\"," +
                "\"PastParticiple\": \"misunderstood\"," +
                "\"Mean\": \"hiểu lầm\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"mow\"," +
                "\"SimplePast\": \"mowed\"," +
                "\"PastParticiple\": \"mown/ mowed\"," +
                "\"Mean\": \"cắt cỏ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"outbid\"," +
                "\"SimplePast\": \"outbid\"," +
                "\"PastParticiple\": \"outbid\"," +
                "\"Mean\": \"trả hơn giá\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"outdo\"," +
                "\"SimplePast\": \"outdid\"," +
                "\"PastParticiple\": \"outdone\"," +
                "\"Mean\": \"làm giỏi hơn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"outgrow\"," +
                "\"SimplePast\": \"outgrew\"," +
                "\"PastParticiple\": \"outgrown\"," +
                "\"Mean\": \"lớn nhanh hơn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"output\"," +
                "\"SimplePast\": \"output\"," +
                "\"PastParticiple\": \"output\"," +
                "\"Mean\": \"cho ra (dữ kiện)\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"outrun\"," +
                "\"SimplePast\": \"outran\"," +
                "\"PastParticiple\": \"outrun\"," +
                "\"Mean\": \"chạy nhanh hơn, vượt quá\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"outsell\"," +
                "\"SimplePast\": \"outsold\"," +
                "\"PastParticiple\": \"outsold\"," +
                "\"Mean\": \"bán nhanh hơn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"overcome\"," +
                "\"SimplePast\": \"overcame\"," +
                "\"PastParticiple\": \"overcome\"," +
                "\"Mean\": \"khắc phục\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"overeat\"," +
                "\"SimplePast\": \"overate\"," +
                "\"PastParticiple\": \"overeaten\"," +
                "\"Mean\": \"ăn quá nhiều\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"overfly\"," +
                "\"SimplePast\": \"overflew\"," +
                "\"PastParticiple\": \"overflown\"," +
                "\"Mean\": \"bay qua\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"overhang\"," +
                "\"SimplePast\": \"overhung\"," +
                "\"PastParticiple\": \"overhung\"," +
                "\"Mean\": \"nhô lên trên, treo lơ lửng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"overhear\"," +
                "\"SimplePast\": \"overheard\"," +
                "\"PastParticiple\": \"overheard\"," +
                "\"Mean\": \"nghe trộm\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"overlay\"," +
                "\"SimplePast\": \"overlaid\"," +
                "\"PastParticiple\": \"overlaid\"," +
                "\"Mean\": \"phủ lên\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"overpay\"," +
                "\"SimplePast\": \"overpaid\"," +
                "\"PastParticiple\": \"overpaid\"," +
                "\"Mean\": \"trả quá tiền\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"overrun\"," +
                "\"SimplePast\": \"overran\"," +
                "\"PastParticiple\": \"overrun\"," +
                "\"Mean\": \"tràn ngập\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"oversee\"," +
                "\"SimplePast\": \"oversaw\"," +
                "\"PastParticiple\": \"overseen\"," +
                "\"Mean\": \"trông nom\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"oversell\"," +
                "\"SimplePast\": \"oversold\"," +
                "\"PastParticiple\": \"oversold\"," +
                "\"Mean\": \"bán quá mức\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"overshoot\"," +
                "\"SimplePast\": \"overshot\"," +
                "\"PastParticiple\": \"overshot\"," +
                "\"Mean\": \"đi quá đích\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"oversleep\"," +
                "\"SimplePast\": \"overslept\"," +
                "\"PastParticiple\": \"overslept\"," +
                "\"Mean\": \"ngủ quên\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"overtake\"," +
                "\"SimplePast\": \"overtook\"," +
                "\"PastParticiple\": \"overtaken\"," +
                "\"Mean\": \"đuổi bắt kịp\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"overthrow\"," +
                "\"SimplePast\": \"overthrew\"," +
                "\"PastParticiple\": \"overthrown\"," +
                "\"Mean\": \"lật đổ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"pay\"," +
                "\"SimplePast\": \"paid\"," +
                "\"PastParticiple\": \"paid\"," +
                "\"Mean\": \"trả (tiền)\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"proofread\"," +
                "\"SimplePast\": \"proofread\"," +
                "\"PastParticiple\": \"proofread\"," +
                "\"Mean\": \"đọc lại, soát lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"prove\"," +
                "\"SimplePast\": \"proved\"," +
                "\"PastParticiple\": \"proven/ proved\"," +
                "\"Mean\": \"chứng minh, chứng tỏ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"put\"," +
                "\"SimplePast\": \"put\"," +
                "\"PastParticiple\": \"put\"," +
                "\"Mean\": \"đặt, để\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"read /riːd/\"," +
                "\"SimplePast\": \"read /rɛd/\"," +
                "\"PastParticiple\": \"read /rɛd/\"," +
                "\"Mean\": \"đọc\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"rebuild\"," +
                "\"SimplePast\": \"rebuilt\"," +
                "\"PastParticiple\": \"rebuilt\"," +
                "\"Mean\": \"xây dựng lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"redo\"," +
                "\"SimplePast\": \"redid\"," +
                "\"PastParticiple\": \"redone\"," +
                "\"Mean\": \"làm lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"relearn\"," +
                "\"SimplePast\": \"relearned/relearnt\"," +
                "\"PastParticiple\": \"relearned/relearnt\"," +
                "\"Mean\": \"học lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"remake\"," +
                "\"SimplePast\": \"remade\"," +
                "\"PastParticiple\": \"remade\"," +
                "\"Mean\": \"làm lại, chế tạo lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"rend\"," +
                "\"SimplePast\": \"rent\"," +
                "\"PastParticiple\": \"rent\"," +
                "\"Mean\": \"toạc ra, xé\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"repay\"," +
                "\"SimplePast\": \"repaid\"," +
                "\"PastParticiple\": \"repaid\"," +
                "\"Mean\": \"hoàn tiền lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"reread\"," +
                "\"SimplePast\": \"reread\"," +
                "\"PastParticiple\": \"reread\"," +
                "\"Mean\": \"đọc lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"resell\"," +
                "\"SimplePast\": \"resold\"," +
                "\"PastParticiple\": \"resold\"," +
                "\"Mean\": \"bán lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"resend\"," +
                "\"SimplePast\": \"resent\"," +
                "\"PastParticiple\": \"resent\"," +
                "\"Mean\": \"gửi lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"reshoot\"," +
                "\"SimplePast\": \"reshot\"," +
                "\"PastParticiple\": \"reshot\"," +
                "\"Mean\": \"bắn lại, chụp lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"retake\"," +
                "\"SimplePast\": \"retook\"," +
                "\"PastParticiple\": \"retaken\"," +
                "\"Mean\": \"chiếm lại, tái chiếm\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"rewrite\"," +
                "\"SimplePast\": \"rewrote\"," +
                "\"PastParticiple\": \"rewritten\"," +
                "\"Mean\": \"viết lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"rid\"," +
                "\"SimplePast\": \"rid\"," +
                "\"PastParticiple\": \"rid\"," +
                "\"Mean\": \"giải thoát\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"ride\"," +
                "\"SimplePast\": \"rode\"," +
                "\"PastParticiple\": \"ridden\"," +
                "\"Mean\": \"cưỡi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"ring\"," +
                "\"SimplePast\": \"rang\"," +
                "\"PastParticiple\": \"rung\"," +
                "\"Mean\": \"rung chuông\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"rise\"," +
                "\"SimplePast\": \"rose\"," +
                "\"PastParticiple\": \"risen\"," +
                "\"Mean\": \"đứng dậy, mọc\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"run\"," +
                "\"SimplePast\": \"ran\"," +
                "\"PastParticiple\": \"run\"," +
                "\"Mean\": \"chạy\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"saw\"," +
                "\"SimplePast\": \"sawed\"," +
                "\"PastParticiple\": \"sawn\"," +
                "\"Mean\": \"cưa\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"say\"," +
                "\"SimplePast\": \"said\"," +
                "\"PastParticiple\": \"said\"," +
                "\"Mean\": \"nói\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"see\"," +
                "\"SimplePast\": \"saw\"," +
                "\"PastParticiple\": \"seen\"," +
                "\"Mean\": \"nhìn thấy\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"seek\"," +
                "\"SimplePast\": \"sought\"," +
                "\"PastParticiple\": \"sought\"," +
                "\"Mean\": \"tìm kiếm\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"sell\"," +
                "\"SimplePast\": \"sold\"," +
                "\"PastParticiple\": \"sold\"," +
                "\"Mean\": \"bán\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"send\"," +
                "\"SimplePast\": \"sent\"," +
                "\"PastParticiple\": \"sent\"," +
                "\"Mean\": \"gửi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"sew\"," +
                "\"SimplePast\": \"sewed\"," +
                "\"PastParticiple\": \"sewn/ sewed\"," +
                "\"Mean\": \"may\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"shake\"," +
                "\"SimplePast\": \"shook\"," +
                "\"PastParticiple\": \"shaken\"," +
                "\"Mean\": \"rung, lay, lắc\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"shear\"," +
                "\"SimplePast\": \"sheared\"," +
                "\"PastParticiple\": \"shorn\"," +
                "\"Mean\": \"xén lông (cừu)\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"shed\"," +
                "\"SimplePast\": \"shed\"," +
                "\"PastParticiple\": \"shed\"," +
                "\"Mean\": \"rơi, rụng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"shine\"," +
                "\"SimplePast\": \"shone\"," +
                "\"PastParticiple\": \"shone\"," +
                "\"Mean\": \"chiếu sáng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"shoot\"," +
                "\"SimplePast\": \"shot\"," +
                "\"PastParticiple\": \"shot\"," +
                "\"Mean\": \"bắn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"show\"," +
                "\"SimplePast\": \"showed\"," +
                "\"PastParticiple\": \"shown/ showed\"," +
                "\"Mean\": \"cho xem\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"shrink\"," +
                "\"SimplePast\": \"shrank\"," +
                "\"PastParticiple\": \"shrunk\"," +
                "\"Mean\": \"co rút\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"shut\"," +
                "\"SimplePast\": \"shut\"," +
                "\"PastParticiple\": \"shut\"," +
                "\"Mean\": \"đóng lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"sing\"," +
                "\"SimplePast\": \"sang\"," +
                "\"PastParticiple\": \"sung\"," +
                "\"Mean\": \"ca hát\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"sit\"," +
                "\"SimplePast\": \"sat\"," +
                "\"PastParticiple\": \"sut\"," +
                "\"Mean\": \"ngồi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"sink\"," +
                "\"SimplePast\": \"sank\"," +
                "\"PastParticiple\": \"sunk\"," +
                "\"Mean\": \"chìm, lặn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"slay\"," +
                "\"SimplePast\": \"slew\"," +
                "\"PastParticiple\": \"slain\"," +
                "\"Mean\": \"sát hại, giết hại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"sleep\"," +
                "\"SimplePast\": \"slept\"," +
                "\"PastParticiple\": \"slept\"," +
                "\"Mean\": \"ngủ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"slide\"," +
                "\"SimplePast\": \"slid\"," +
                "\"PastParticiple\": \"slid\"," +
                "\"Mean\": \"trượt, lướt\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"sling\"," +
                "\"SimplePast\": \"slung\"," +
                "\"PastParticiple\": \"slung\"," +
                "\"Mean\": \"ném mạnh\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"slink\"," +
                "\"SimplePast\": \"slunk\"," +
                "\"PastParticiple\": \"slunk\"," +
                "\"Mean\": \"lẻn đi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"smell\"," +
                "\"SimplePast\": \"smelt\"," +
                "\"PastParticiple\": \"smelt\"," +
                "\"Mean\": \"ngửi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"smite\"," +
                "\"SimplePast\": \"smote\"," +
                "\"PastParticiple\": \"smitten\"," +
                "\"Mean\": \"đập mạnh\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"sow\"," +
                "\"SimplePast\": \"sowed\"," +
                "\"PastParticiple\": \"sown/ sewed\"," +
                "\"Mean\": \"gieo, rải\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"speak\"," +
                "\"SimplePast\": \"spoke\"," +
                "\"PastParticiple\": \"spoken\"," +
                "\"Mean\": \"nói\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"speed\"," +
                "\"SimplePast\": \"sped/ speeded\"," +
                "\"PastParticiple\": \"sped/ speeded\"," +
                "\"Mean\": \"chạy vụt\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"spell\"," +
                "\"SimplePast\": \"spelt/ spelled\"," +
                "\"PastParticiple\": \"spelt/ spelled\"," +
                "\"Mean\": \"đánh vần\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"spend\"," +
                "\"SimplePast\": \"spent\"," +
                "\"PastParticiple\": \"spent\"," +
                "\"Mean\": \"tiêu xài, sử dụng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"spill\"," +
                "\"SimplePast\": \"spilt/ spilled\"," +
                "\"PastParticiple\": \"spilt/ spilled\"," +
                "\"Mean\": \"tràn đổ ra\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"spin\"," +
                "\"SimplePast\": \"spun/ span\"," +
                "\"PastParticiple\": \"spun\"," +
                "\"Mean\": \"quay sợi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"spit\"," +
                "\"SimplePast\": \"spat\"," +
                "\"PastParticiple\": \"spat\"," +
                "\"Mean\": \"khạc nhổ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"spoil\"," +
                "\"SimplePast\": \"spoilt/ spoiled\"," +
                "\"PastParticiple\": \"spoilt/ spoiled\"," +
                "\"Mean\": \"làm hỏng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"spread\"," +
                "\"SimplePast\": \"spread\"," +
                "\"PastParticiple\": \"spread\"," +
                "\"Mean\": \"lan truyền\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"spring\"," +
                "\"SimplePast\": \"sprang\"," +
                "\"PastParticiple\": \"sprung\"," +
                "\"Mean\": \"nhảy\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"stand\"," +
                "\"SimplePast\": \"stood\"," +
                "\"PastParticiple\": \"stood\"," +
                "\"Mean\": \"đứng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"stave\"," +
                "\"SimplePast\": \"stove/ staved\"," +
                "\"PastParticiple\": \"stove/ staved\"," +
                "\"Mean\": \"đâm thủng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"steal\"," +
                "\"SimplePast\": \"stole\"," +
                "\"PastParticiple\": \"stolen\"," +
                "\"Mean\": \"đánh cắp\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"stick\"," +
                "\"SimplePast\": \"stuck\"," +
                "\"PastParticiple\": \"stuck\"," +
                "\"Mean\": \"ghim vào, đính\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"sting\"," +
                "\"SimplePast\": \"stung\"," +
                "\"PastParticiple\": \"stung\"," +
                "\"Mean\": \"châm, chích, đốt\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"stink\"," +
                "\"SimplePast\": \"stunk/ stank\"," +
                "\"PastParticiple\": \"stunk\"," +
                "\"Mean\": \"bốc mùi hôi\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"strew\"," +
                "\"SimplePast\": \"strewed\"," +
                "\"PastParticiple\": \"strewn/ strewed\"," +
                "\"Mean\": \"rắc, rải\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"stride\"," +
                "\"SimplePast\": \"strode\"," +
                "\"PastParticiple\": \"stridden\"," +
                "\"Mean\": \"bước sải\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"strike\"," +
                "\"SimplePast\": \"struck\"," +
                "\"PastParticiple\": \"struck\"," +
                "\"Mean\": \"đánh đập\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"string\"," +
                "\"SimplePast\": \"strung\"," +
                "\"PastParticiple\": \"strung\"," +
                "\"Mean\": \"gắn dây vào\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"strive\"," +
                "\"SimplePast\": \"strove\"," +
                "\"PastParticiple\": \"striven\"," +
                "\"Mean\": \"cố sức\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"swear\"," +
                "\"SimplePast\": \"swore\"," +
                "\"PastParticiple\": \"sworn\"," +
                "\"Mean\": \"tuyên thệ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"sweep\"," +
                "\"SimplePast\": \"swept\"," +
                "\"PastParticiple\": \"swept\"," +
                "\"Mean\": \"quét\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"swell\"," +
                "\"SimplePast\": \"swelled\"," +
                "\"PastParticiple\": \"swollen/ swelled\"," +
                "\"Mean\": \"phồng, sưng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"swim\"," +
                "\"SimplePast\": \"swam\"," +
                "\"PastParticiple\": \"swum\"," +
                "\"Mean\": \"bơi, lội\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"swing\"," +
                "\"SimplePast\": \"swung\"," +
                "\"PastParticiple\": \"swung\"," +
                "\"Mean\": \"đong đưa, lắc\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"take\"," +
                "\"SimplePast\": \"took\"," +
                "\"PastParticiple\": \"taken\"," +
                "\"Mean\": \"cầm, lấy\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"teach\"," +
                "\"SimplePast\": \"taught\"," +
                "\"PastParticiple\": \"taught\"," +
                "\"Mean\": \"dạy, giảng dạy\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"tear\"," +
                "\"SimplePast\": \"tore\"," +
                "\"PastParticiple\": \"torn\"," +
                "\"Mean\": \"xé, rách\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"tell\"," +
                "\"SimplePast\": \"told\"," +
                "\"PastParticiple\": \"told\"," +
                "\"Mean\": \"kể, bảo\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"think\"," +
                "\"SimplePast\": \"thought\"," +
                "\"PastParticiple\": \"thought\"," +
                "\"Mean\": \"suy nghĩ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"throw\"," +
                "\"SimplePast\": \"threw\"," +
                "\"PastParticiple\": \"thrown\"," +
                "\"Mean\": \"ném, liệng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"thrust\"," +
                "\"SimplePast\": \"thrust\"," +
                "\"PastParticiple\": \"thrust\"," +
                "\"Mean\": \"thọc, nhấn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"tread\"," +
                "\"SimplePast\": \"trod\"," +
                "\"PastParticiple\": \"trodden/ trod\"," +
                "\"Mean\": \"giẫm, đạp\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"unbend\"," +
                "\"SimplePast\": \"unbent\"," +
                "\"PastParticiple\": \"unbent\"," +
                "\"Mean\": \"làm thẳng lại\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"undercut\"," +
                "\"SimplePast\": \"undercut\"," +
                "\"PastParticiple\": \"undercut\"," +
                "\"Mean\": \"ra giá rẻ hơn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"undergo\"," +
                "\"SimplePast\": \"underwent\"," +
                "\"PastParticiple\": \"undergone\"," +
                "\"Mean\": \"kinh qua\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"underlie\"," +
                "\"SimplePast\": \"underlay\"," +
                "\"PastParticiple\": \"underlain\"," +
                "\"Mean\": \"nằm dưới\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"underpay\"," +
                "\"SimplePast\": \"underpaid\"," +
                "\"PastParticiple\": \"underpaid\"," +
                "\"Mean\": \"trả lương thấp\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"undersell\"," +
                "\"SimplePast\": \"undersold\"," +
                "\"PastParticiple\": \"undersold\"," +
                "\"Mean\": \"bán rẻ hơn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"understand\"," +
                "\"SimplePast\": \"understood\"," +
                "\"PastParticiple\": \"understood\"," +
                "\"Mean\": \"hiểu\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"undertake\"," +
                "\"SimplePast\": \"undertook\"," +
                "\"PastParticiple\": \"undertaken\"," +
                "\"Mean\": \"đảm nhận\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"underwrite\"," +
                "\"SimplePast\": \"underwrote\"," +
                "\"PastParticiple\": \"underwritten\"," +
                "\"Mean\": \"bảo hiểm\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"undo\"," +
                "\"SimplePast\": \"undid\"," +
                "\"PastParticiple\": \"undone\"," +
                "\"Mean\": \"tháo ra\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"unfreeze\"," +
                "\"SimplePast\": \"unfroze\"," +
                "\"PastParticiple\": \"unfrozen\"," +
                "\"Mean\": \"làm tan đông\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"unwind\"," +
                "\"SimplePast\": \"unwound\"," +
                "\"PastParticiple\": \"unwound\"," +
                "\"Mean\": \"tháo ra\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"uphold\"," +
                "\"SimplePast\": \"upheld\"," +
                "\"PastParticiple\": \"upheld\"," +
                "\"Mean\": \"ủng hộ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"upset\"," +
                "\"SimplePast\": \"upset\"," +
                "\"PastParticiple\": \"upset\"," +
                "\"Mean\": \"đánh đổ, lật đổ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"wake\"," +
                "\"SimplePast\": \"woke/ waked\"," +
                "\"PastParticiple\": \"woken/ waked\"," +
                "\"Mean\": \"thức giấc\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"waylay\"," +
                "\"SimplePast\": \"waylaid\"," +
                "\"PastParticiple\": \"waylaid\"," +
                "\"Mean\": \"mai phục\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"wear\"," +
                "\"SimplePast\": \"wore\"," +
                "\"PastParticiple\": \"worn\"," +
                "\"Mean\": \"mặc\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"weave\"," +
                "\"SimplePast\": \"wove/ weaved\"," +
                "\"PastParticiple\": \"woven/ weaved\"," +
                "\"Mean\": \"dệt\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"wed\"," +
                "\"SimplePast\": \"wed/ wedded\"," +
                "\"PastParticiple\": \"wed/ wedded\"," +
                "\"Mean\": \"kết hôn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"weep\"," +
                "\"SimplePast\": \"wept\"," +
                "\"PastParticiple\": \"wept\"," +
                "\"Mean\": \"khóc\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"wet\"," +
                "\"SimplePast\": \"wet/ wetted\"," +
                "\"PastParticiple\": \"wet/ wetted\"," +
                "\"Mean\": \"làm ướt\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"win\"," +
                "\"SimplePast\": \"won\"," +
                "\"PastParticiple\": \"won\"," +
                "\"Mean\": \"chiến thắng\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"will\"," +
                "\"SimplePast\": \"would\"," +
                "\"Mean\": \"sẽ\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"wind\"," +
                "\"SimplePast\": \"wound\"," +
                "\"PastParticiple\": \"wound\"," +
                "\"Mean\": \"quấn\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"withdraw\"," +
                "\"SimplePast\": \"withdrew\"," +
                "\"PastParticiple\": \"withdrawn\"," +
                "\"Mean\": \"rút lui\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"withhold\"," +
                "\"SimplePast\": \"withheld\"," +
                "\"PastParticiple\": \"withheld\"," +
                "\"Mean\": \"từ khước\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"withstand\"," +
                "\"SimplePast\": \"withstood\"," +
                "\"PastParticiple\": \"withstood\"," +
                "\"Mean\": \"cầm cự\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"work\"," +
                "\"SimplePast\": \"wrought/ worked\"," +
                "\"PastParticiple\": \"wrought/ worked\"," +
                "\"Mean\": \"rèn (sắt)\"" +
                "}," +
                "{" +
                "\"Infinitive\": \"wring\"," +
                "\"SimplePast\": \"wrung\"," +
                "\"PastParticiple\": \"wrung\"," +
                "\"Mean\": \"vặn, siết chặt\"" +
                "}" +
                "]," +
                "\"_xmlns:xsi\": \"http://www.w3.org/2001/XMLSchema-instance\"" +
                "}" +
                "}";

        try {


            JSONObject myjson = new JSONObject(jsontext);

            JSONObject irregularListJsonObject = (JSONObject) myjson.get("IrregularList");
            JSONArray irregularJsonArray =  irregularListJsonObject.getJSONArray("Irregular");
            //Toast.makeText(this, irregularList.toString(), Toast.LENGTH_LONG).show();
            int lengthOfIrregularJsonArray = irregularJsonArray.length();
            Log.d("json", ""+lengthOfIrregularJsonArray);
            for (int i = 0; i < lengthOfIrregularJsonArray; ++i) {
                JSONObject rec = irregularJsonArray.getJSONObject(i);
                String infinitive = rec.getString("Infinitive");
                String simplePast = rec.getString("SimplePast");
                String pastParticiple = null;
                if (rec.has("PastParticiple")){
                    pastParticiple = rec.getString("PastParticiple");
                }
                String mean = null;
                if (rec.has("Mean")){
                    mean = rec.getString("Mean");

                }
                Irregular item = new Irregular(infinitive, simplePast, pastParticiple, mean);
                mDatabase.child("Irregular").push().setValue(item);

                                // ...
            }
        } catch (JSONException e) {
            Toast.makeText(this, "a", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    @SuppressLint("WrongViewCast")
    private void addControls() {
        actv = findViewById(R.id.actv);
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mListWord); // lam custom cho autocomplete
        actv.setAdapter(mAdapter);
        actv.setThreshold(1);
        btnClear=findViewById(R.id.btnClear);
        btnFlashcard=findViewById(R.id.btnFlashcard);
        btnYourWord=findViewById(R.id.btnYourWord);
        btnTranslate=findViewById(R.id.btnTranslate);
        btnIrregular=findViewById(R.id.btnIrregular);
        btnHistory=findViewById(R.id.btnHistory);
        btnSetting=findViewById(R.id.btnSetting);
        mAdapter.notifyDataSetChanged();

    }

    private void addEvents() {
        actv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!actv.getText().toString().equals("")) { //if edittext include text
                    btnClear.setVisibility(View.VISIBLE);
                } else { //not include text
                    btnClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                View layout = getLayoutInflater().inflate(R.layout.dialog_word, null);
                final TextView tvWord = layout.findViewById(R.id.tvWord);
                final TextView tvEssential = layout.findViewById(R.id.tvEssential);
                final ImageView imgView = layout.findViewById(R.id.imgView);
                final TextView tvPronunciation = layout.findViewById(R.id.tvPronunciation);
                final TextView tvMean = layout.findViewById(R.id.tvMean);
                int idx=0;
                String temp = actv.getText().toString();
                for(int k = 0; k < mList.size(); k++)
                {

                    if(mList.get(k).getWord().equals(temp))
                    {
                        idx = k;
                        break;
                    }
                }
                Card card = mList.get(idx);
                tvWord.setText(card.getWord());
                tvEssential.setText(card.getEssential());
                Picasso.with(MainActivity.this).load(card.getImage()).into(imgView);
                tvPronunciation.setText(card.getPronunciation());
                tvMean.setText(card.getMean());
                new AlertDialog.Builder(MainActivity.this)
                        .setView(layout)
                        .create()
                        .show();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actv.setText("");
            }
        });

        btnFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CardActivity.class);
                startActivity(intent);
            }
        });

        btnYourWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WordsActivity.class);
                startActivity(intent);
            }
        });

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TranslateActivity.class);
                startActivity(intent);
            }
        });

        btnIrregular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IrregularActivity.class);
                startActivity(intent);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }

    private class DownloadData extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {

            try {
                aPeriods = getResources().getStringArray(R.array.Periods);
                for (int i = 0; i < aPeriods.length; i++) {
                    //  getData(aPeriods[i]);
                    mDatabase.child("Period").child(aPeriods[i]).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Card card = dataSnapshot.getValue(Card.class);
                            mList.add(card);
                            mListWord.add(card.getWord());
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
                SystemClock.sleep(5000);
            }
            catch (Exception e)
            {
                return STATUS_ERROR;
            }


            return STATUS_OK;
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

            if (s == STATUS_OK) {
                start();


            } else {


            }
        }
    }


}
