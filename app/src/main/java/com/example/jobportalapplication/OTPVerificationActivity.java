package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivityOtpverificationBinding;

public class OTPVerificationActivity extends AppCompatActivity {

    ActivityOtpverificationBinding binding;
    CountDownTimer countDownTimer;
    int timeLeft = 30; // seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpverificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setNumericInputOnly();
        setupOtpAutoFocus();
        showKeyboard(binding.otp1);
        startCountDown();

        binding.verifyButton.setOnClickListener(v -> {
            if (binding.verifyButton.getText().toString().equalsIgnoreCase("Resend")) {
                Toast.makeText(this, "OTP Resent", Toast.LENGTH_SHORT).show();
                timeLeft = 30;
                startCountDown();
            } else {
                String otp = binding.otp1.getText().toString() +
                        binding.otp2.getText().toString() +
                        binding.otp3.getText().toString() +
                        binding.otp4.getText().toString();

                if (otp.length() == 4) {
                    Toast.makeText(this, "OTP Verified", Toast.LENGTH_SHORT).show();

                    // ✅ Navigate to ResetPasswordActivity
                    Intent intent = new Intent(OTPVerificationActivity.this, ResetPasswordActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(this, "Enter complete OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.backButton.setOnClickListener(v -> onBackPressed());
    }

    private void setNumericInputOnly() {
        binding.otp1.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        binding.otp2.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        binding.otp3.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        binding.otp4.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
    }

    private void setupOtpAutoFocus() {
        binding.otp1.addTextChangedListener(new GenericTextWatcher(binding.otp1, binding.otp2));
        binding.otp2.addTextChangedListener(new GenericTextWatcher(binding.otp2, binding.otp3));
        binding.otp3.addTextChangedListener(new GenericTextWatcher(binding.otp3, binding.otp4));
        binding.otp4.addTextChangedListener(new GenericTextWatcher(binding.otp4, null));
    }

    private void showKeyboard(EditText editText) {
        editText.requestFocus();
        new Handler().postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 200);
    }

    private void startCountDown() {
        binding.verifyButton.setText("Verify");
        countDownTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeft--;
                binding.timerText.setText("Resend Code in " + timeLeft + " Sec");
            }

            public void onFinish() {
                binding.verifyButton.setText("Resend");
                binding.timerText.setText("");
            }
        }.start();
    }

    public static class GenericTextWatcher implements android.text.TextWatcher {
        private final EditText currentView;
        private final EditText nextView;

        public GenericTextWatcher(EditText currentView, EditText nextView) {
            this.currentView = currentView;
            this.nextView = nextView;
        }

        @Override
        public void afterTextChanged(android.text.Editable s) {
            if (s.length() == 1 && nextView != null) {
                nextView.requestFocus();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }
    }
}
