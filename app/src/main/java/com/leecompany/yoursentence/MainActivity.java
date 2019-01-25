package com.leecompany.yoursentence;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 3333;
    EditText mSetText;

    // 폰트 사이즈 관련
    EditText mSetSize;
    Button mSizeMinus;
    Button mSizePlus;
    int fontSize;

    // 폰트 색상 관련
    TextView mColor01;
    TextView mColor02;
    TextView mColor03;
    TextView mColor04;
    TextView mColor05;
    TextView mColor06;
    TextView mColor07;
    TextView mColor08;
    TextView mColor09;
    int colorIdx;

    // 텍스트 출력 위치 관련
    RadioGroup mSetTextGroup;
    RadioButton mRadioButtonTop;
    RadioButton mRadioButtonBottom;
    int textPosition;

    Button mSetBtn;
    int fontNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰 연결
        mSetText = findViewById(R.id.setText);

        mSetSize = findViewById(R.id.setSize);
        mSizeMinus = findViewById(R.id.sizeMinus);
        mSizePlus = findViewById(R.id.sizePlus);

        mColor01 = findViewById(R.id.color01);
        mColor02 = findViewById(R.id.color02);
        mColor03 = findViewById(R.id.color03);
        mColor04 = findViewById(R.id.color04);
        mColor05 = findViewById(R.id.color05);
        mColor06 = findViewById(R.id.color06);
        mColor07 = findViewById(R.id.color07);
        mColor08 = findViewById(R.id.color08);
        mColor09 = findViewById(R.id.color09);

        // 텍스트 배치 관련
        mRadioButtonTop = findViewById(R.id.setTextTop);
        mRadioButtonBottom = findViewById(R.id.setTextBottom);

        mSetTextGroup = findViewById(R.id.setTextGroup);
        mSetTextGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.setTextTop) {
                    textPosition = 1;
                } else if(i == R.id.setTextBottom) {
                    textPosition = 2;
                }
            }
        });

        // 텍스트 사이즈 설정 부분 구현
        mSizeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fontSize = Integer.parseInt(mSetSize.getText().toString());
                fontSize -= 1;

                if (fontSize < 0) {
                    fontSize += 1;
                }

                mSetSize.setText("" + fontSize);
            }
        });

        mSizePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fontSize = Integer.parseInt(mSetSize.getText().toString());
                fontSize += 1;

                mSetSize.setText("" + fontSize);
            }
        });

        // 폰트 색상 관련
        View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.color01:
                            colorIdx = 1;
                            mColor09.setBackgroundColor(Color.parseColor("#FF0000"));
                            break;
                        case R.id.color02:
                            colorIdx = 2;
                            mColor09.setBackgroundColor(Color.parseColor("#FFBB00"));
                            break;
                        case R.id.color03:
                            colorIdx = 3;
                            mColor09.setBackgroundColor(Color.parseColor("#ABF200"));
                            break;
                        case R.id.color04:
                            colorIdx = 4;
                            mColor09.setBackgroundColor(Color.parseColor("#0054FF"));
                            break;
                        case R.id.color05:
                            colorIdx = 5;
                            mColor09.setBackgroundColor(Color.parseColor("#FFB2D9"));
                            break;
                        case R.id.color06:
                            colorIdx = 6;
                            mColor09.setBackgroundColor(Color.parseColor("#FFE400"));
                            break;
                        case R.id.color07:
                            colorIdx = 7;
                            mColor09.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            break;
                        case R.id.color08:
                            colorIdx = 8;
                            mColor09.setBackgroundColor(Color.parseColor("#000000"));
                            break;
                        default:
                            colorIdx = 8;
                            mColor09.setBackgroundColor(Color.parseColor("#000000"));
                            break;
                    }
                }
            };

        mColor01.setOnClickListener(mOnClickListener);
        mColor02.setOnClickListener(mOnClickListener);
        mColor03.setOnClickListener(mOnClickListener);
        mColor04.setOnClickListener(mOnClickListener);
        mColor05.setOnClickListener(mOnClickListener);
        mColor06.setOnClickListener(mOnClickListener);
        mColor07.setOnClickListener(mOnClickListener);
        mColor08.setOnClickListener(mOnClickListener);

        // 설정완료 버튼
        mSetBtn = findViewById(R.id.setBtn);

        // 기본적
        RadioButton font01 = findViewById(R.id.font01);

        // 진지한
        RadioButton font02 = findViewById(R.id.font02);
        Typeface mTypeFace02 = Typeface.createFromAsset(getAssets(), "fonts/HYBSRB.TTF");
        font02.setTypeface(mTypeFace02);

        // 동글이
        RadioButton font03 = findViewById(R.id.font03);
        Typeface mTypeFace03 = Typeface.createFromAsset(getAssets(), "fonts/HYDNKB.TTF");
        font03.setTypeface(mTypeFace03);

        // 손글씨
        RadioButton font04 = findViewById(R.id.font04);
        Typeface mTypeFace04 = Typeface.createFromAsset(getAssets(), "fonts/HMFMPYUN.TTF");
        font04.setTypeface(mTypeFace04);

        RadioGroup mFontGroup = findViewById(R.id.fontGroup);
        mFontGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.font01)
                    fontNum = 0;
                else if (i == R.id.font02)
                    fontNum = 1;
                else if (i == R.id.font03)
                    fontNum = 2;
                else if (i == R.id.font04)
                    fontNum = 3;
            }
        });

        startOverlayWindowService(getApplicationContext());

        // 설정완료 버튼 눌렀을 때
        mSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "설정되었습니다!", Toast.LENGTH_LONG).show();

                fontSize = Integer.parseInt(mSetSize.getText().toString());

                Intent mSIntent = new Intent(MainActivity.this, LockScreenTextService.class);
                mSIntent.putExtra("userSetText", mSetText.getText().toString());
                mSIntent.putExtra("userSetFont", fontNum);
                mSIntent.putExtra("userSetSize", fontSize);
                mSIntent.putExtra("userSetColor", colorIdx);
                mSIntent.putExtra("userSetPosition", textPosition);

                startService(mSIntent);
            }
        });
    }

    public void startOverlayWindowService(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                Toast.makeText(getApplicationContext(), "오버레이 권환 확인 완료", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "오버레이 권한이 없습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
