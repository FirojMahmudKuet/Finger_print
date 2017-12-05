package com.example.arun.fingerprintauthentication;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class SigninActivity extends AppCompatActivity {

/*    public KeyguardManager keyguardmanager;
    public FingerprintManager fingerprintmanager;*/
    public TextView tv_fp_mes;
//    public GenerateKeyCipher genratekey;
 /*   public String  KEY_NAME="arun";
    public KeyStore keyStore;
    public Cipher cipher;*/
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Initializing both Android Keyguard Manager and Fingerprint Manager
            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

//           tv_fp_mes = (TextView) findViewById(R.id.tv_fp_message);

            // Check whether the device has a Fingerprint sensor.
            if(!fingerprintManager.isHardwareDetected()){

//                tv_fp_mes.setText("Your Device does not have a Fingerprint Sensor");
            }else {
                // Checks whether fingerprint permission is set on manifest
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
//                   tv_fp_mes.setText("Fingerprint authentication permission not enabled");
                }else{
                    // Check whether at least one fingerprint is registered
                    if (!fingerprintManager.hasEnrolledFingerprints()) {
//                       tv_fp_mes.setText("Register at least one fingerprint in Settings");
                    }else{
                        // Checks whether lock screen security is enabled or not
                        if (!keyguardManager.isKeyguardSecure()) {
//                            tv_fp_mes.setText("Lock screen security not enabled in Settings");
                            Toast.makeText(this, ""+"Lock screen security not enabled in Settings", Toast.LENGTH_SHORT).show();
                        }else{
                            GenerateKeyCipher generateKeyCipher =new GenerateKeyCipher();
                            generateKeyCipher.generateKey();
                            if (generateKeyCipher.cipherInit()) {
                                FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(generateKeyCipher.getcipher());
                                FingerprintHandler helper = new FingerprintHandler(this);
                                helper.startAuth(fingerprintManager, cryptoObject);

                            }
                        }
                    }
                }
            }
        }

}
