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

public class SecondActivity extends AppCompatActivity {

  @BindView(R.id.text_number) EditText mNumberInput;
  @BindView(R.id.button_navigate) AppCompatButton mButtonNavigate;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    initializeUI();
  }

  private void initializeUI() {
    int number = getIntent().getIntExtra(Constants.EXTRA_NUMBER, 0) * 2;
    mNumberInput.setText(String.valueOf(number));
    mButtonNavigate.setText(R.string.button_navigate_screen1);
  }

  @OnClick(R.id.button_navigate) public void onClick(View view) {
    String temp = mNumberInput.getText().toString();

    if (temp == null || temp.trim().length() == 0) {
      mNumberInput.setError(getString(R.string.error_mandatory_field));
      return;
    }

    Intent intent = new Intent();
    intent.putExtra(Constants.EXTRA_NUMBER, Integer.parseInt(temp.trim()));
    setResult(Activity.RESULT_OK, intent);
    this.finish();
  }

  /**
   * This method prepares Intent with given params and lunch screen2.
   *
   * @param activity Source activity
   * @param param Input params passed to screen two.
   * @param requestCode request code of Intent.
   */
  public static void launchScreen(AppCompatActivity activity, int param, int requestCode) {
    Intent intent = new Intent(activity, SecondActivity.class);
    intent.putExtra(Constants.EXTRA_NUMBER, param);
    activity.startActivityForResult(intent, requestCode);
  }
}
