package com.example.praktikum4_intentimplisitdinto;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ShareCompat;
import android.view.Menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Intent.ACTION_DIAL;

public class IntentImplisit_Activity extends Activity implements View.OnClickListener {
	private EditText teleponNumber;
	private EditText browserUri;
	private EditText lokasiUri;
	private EditText teksShare;
	
	Button buttonTelepon;
	Button buttonBrowser;
	Button buttonLokasi;
	Button buttonTeks;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_implisit);
        
        teleponNumber = (EditText) findViewById(R.id.editTelepon);
        browserUri = (EditText) findViewById(R.id.editBrowser);
        lokasiUri = (EditText) findViewById(R.id.editLokasi);
        teksShare = (EditText) findViewById(R.id.editTeks);
        
        buttonTelepon = (Button) findViewById(R.id.buttonTelepon);
        buttonTelepon.setOnClickListener(this);
        
        buttonBrowser = (Button) findViewById(R.id.buttonBrowser);
        buttonBrowser.setOnClickListener(this);
        
        buttonLokasi = (Button) findViewById (R.id.buttonLokasi);
        buttonLokasi.setOnClickListener(this);
        
        buttonTeks = (Button) findViewById (R.id.buttonTeks);
        buttonTeks.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intent_implisit_, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.buttonTelepon:
			if (teleponNumber.getText().toString()==null || teleponNumber.getText().toString().trim().equals("")){
				Toast.makeText(getBaseContext(), "Nomor Tak Boleh Kosong", Toast.LENGTH_LONG).show();
			}
			else{
				Intent dialTelepon = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + teleponNumber.getText().toString()));
				startActivity(dialTelepon);
			}
			break;
			
		case R.id.buttonBrowser:
			if(browserUri.getText().toString()==null || browserUri.getText().toString().trim().equals("")){
				Toast.makeText(getBaseContext(), "Link Tak Boleh Kosong", Toast.LENGTH_LONG).show();	
			}
			else{
				String url = browserUri.getText().toString();
				if (!url.startsWith("http://") && !url.startsWith("http://")){
					url ="http://" + url;
					Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					startActivity(openBrowser);
					break;
				}
			}
			
		
		case R.id.buttonLokasi:
			if (lokasiUri.getText().toString()==null || lokasiUri.getText().toString().trim().equals("")){
				Toast.makeText(getBaseContext(), "Alamat Tak Boleh Kosong", Toast.LENGTH_LONG).show();
			}
			else {
				Intent openLokasi = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + lokasiUri.getText().toString()));
				startActivity(openLokasi);
			}
			break;
		
		case R.id.buttonTeks:
			if (teksShare.getText().toString()==null || teksShare.getText().toString().trim().equals("")){
				Toast.makeText(getBaseContext(), "Pesan Tak Boleh Kosong", Toast.LENGTH_LONG).show();				
			}
			else{
				ShareCompat.IntentBuilder.from(this).setType("text/plan").setChooserTitle("Membuka dengan Aplikasi : ").setText(teksShare.getText().toString()).startChooser();
			}
			break;
		// TODO Auto-generated method stub
		
	}
    
}
}
