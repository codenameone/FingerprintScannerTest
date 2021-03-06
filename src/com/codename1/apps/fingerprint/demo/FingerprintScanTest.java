package com.codename1.apps.fingerprint.demo;


import com.codename1.fingerprint.Fingerprint;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.FailureCallback;
import com.codename1.util.SuccessCallback;
import java.io.IOException;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class FingerprintScanTest {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        Log.bindCrashProtection(true);
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        Form hi = new Form("Fingerprint", BoxLayout.y());
        if(!Fingerprint.isAvailable()) {
            hi.add("Fingerprint not available...");
        } else {
            Button scanFingerprint = new Button("Scan Fingerprint");
            hi.add(scanFingerprint);
            scanFingerprint.addActionListener(e -> {
                Fingerprint.scanFingerprint(value -> {
                    hi.add("Scan successfull!");
                    hi.revalidate();
                }, (sender, err, errorCode, errorMessage) -> {
                    hi.add("Scan Failed!");
                    hi.revalidate();
                });
            });
        }
        hi.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }

}
