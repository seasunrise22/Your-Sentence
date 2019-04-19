# Your-Sentence
- 개발인원 : 1명
- 역할
  - 전체
## Introduction
원하는 문구를 스마트폰 잠금화면에 항상 띄워놓을 수 있도록 도와주는 안드로이드 애플리케이션입니다. 

각종 옵션들을 스스로 선택할 수 있도록 구성하여 다채롭고 편리하게 자기만의 잠금화면을 꾸밀 수 있도록 구현하였습니다.

## Development Environment
- IDE : Android Studio
- Language : Java

## Code Preview
***Intent에 정보를 담아 서비스 실행***
```java
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
```
    
***BroadcastReceiver와 IntentFilter를 이용한 잠금화면 감지***     
```java
public class LockScreenStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
        if(!isShowing) {
            mWindowManager.addView(setTextView, mParams);
            isShowing = true;
            }
        }

        else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            if(isShowing) {
                mWindowManager.removeViewImmediate(setTextView);
                isShowing = false;
            }
        }
    }
}
```
    
## Screenshots
![resize_01](https://user-images.githubusercontent.com/45503931/56092562-99fe7000-5ef8-11e9-96af-e486960320f5.png)
![resize_02](https://user-images.githubusercontent.com/45503931/56092564-99fe7000-5ef8-11e9-8aec-90b2678485fd.png)
