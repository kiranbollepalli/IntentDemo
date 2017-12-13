package com.medtronics.intentdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.medtronics.intentdemo.R;
import com.medtronics.intentdemo.util.Constants;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.text_number) EditText mNumberInput;
  @BindView(R.id.button_navigate) AppCompatButton mButtonNavigate;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.button_navigate) public void onClick(View view) {
    String temp = mNumberInput.getText().toString();
    if (temp == null || temp.trim().length() == 0) {
      mNumberInput.setError(getString(R.string.error_mandatory_field));
      return;
    }
    int number = Integer.parseInt(temp.trim());
    SecondActivity.launchScreen(this, number, Constants.RC_SCREEN_TWO);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == Constants.RC_SCREEN_TWO && resultCode == Activity.RESULT_OK) {
      int number = data.getIntExtra(Constants.EXTRA_NUMBER, 0) / 2;
      mNumberInput.setText(String.valueOf(number));
    }
  }
}
