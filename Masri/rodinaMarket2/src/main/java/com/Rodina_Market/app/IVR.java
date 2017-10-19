package com.Rodina_Market.app;

import java.util.Stack;

import com.dmbteam.catalogapp.settings.AppSettings;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

public class IVR extends Activity{

	
	
	protected void onDestroy() {
		super.onDestroy();
		if(mp!=null){
			mp.release();
		}
	};
	
	MediaPlayer mp;
	
	Stack<Integer>stack=new Stack<Integer>();
	int voice;
	int language;
	
	
	void StartWelcomeTape(){
		
		voice=R.raw.welcome;
		stack.clear();
		stack.push(R.raw.star_english);
		stack.push(R.raw.star_arabic);
		stack.push(R.raw.welcome);
		Play(stack.pop());
	}
	
	void StartArabicTape(){
		
		voice=R.raw.arabic;
		stack.clear();
		stack.push(R.raw.star_arabic);
		stack.push(R.raw.arabic);
		Play(stack.pop());
	}
	
	void StartEnglishTape(){
		
		voice=R.raw.english;
		stack.clear();
		stack.push(R.raw.star_english);
		stack.push(R.raw.english);
		Play(stack.pop());
	}
	
	
	void ERROR(){
		
		stack.clear();
		
		if(voice==R.raw.welcome){
			stack.push(R.raw.star_english);
			stack.push(R.raw.star_arabic);
			
			stack.push(R.raw.error_english);
			stack.push(R.raw.error_arabic);
		}
		else if(voice==R.raw.arabic){
			
			stack.push(R.raw.star_arabic);
			stack.push(R.raw.error_arabic);
		}
		else if(voice==R.raw.english){
			stack.push(R.raw.star_english);
			stack.push(R.raw.error_english);
			
		}
		
		Play(stack.pop());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ivr);
	
		StartWelcomeTape();
		
		
		
		final EditText edt=(EditText)findViewById(R.id.editText1);
		
		edt.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				String x=edt.getText().toString();
				if(x.length()>0)
				{
					x=x.substring(x.length()-1);
					if(x.equals("*")){
						if(voice==R.raw.welcome){
							StartWelcomeTape();
						}else if(voice==R.raw.arabic){
							StartArabicTape();
						}
						else if(voice==R.raw.english){
							StartEnglishTape();
						}
					}
				}
			}
		});
		
		edt.setOnKeyListener(new OnKeyListener() {
			
		
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
			
				if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

	               finish();

	                return true;

	            }
				if (event.getAction()!=KeyEvent.ACTION_DOWN)
                    return true;
				char unicodeChar = (char)event.getUnicodeChar();
				String j=edt.getText().toString()+unicodeChar;
				edt.setText("");
				edt.append(j);
				
				if(unicodeChar>='1'&& unicodeChar<='9'||unicodeChar=='*'){
				if(voice==R.raw.welcome){
					if(unicodeChar=='1'){
						
						unicodeChar='-';
						StartArabicTape(); 
						return true;
			  		}
					else if(unicodeChar=='2'){  
						unicodeChar='-';
						StartEnglishTape();
						return true;
					}
					else{
						
						unicodeChar='-';
						ERROR();
						//ERR(R.raw.error_english);
						return true;  
					}
				}
				else if(voice==R.raw.arabic||voice==R.raw.english)
				{
					
					if(unicodeChar=='1'){
						unicodeChar='-';
						//sa;e
						Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + AppSettings.IVRNUM[0]));
						startActivity(intent);
						finish();
						return true;
					}else if(unicodeChar=='2'){
						unicodeChar='-';
						//cus
						Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + AppSettings.IVRNUM[1]));
						startActivity(intent);
						finish();
						return true;
					}
					else if(unicodeChar=='3'){
						//tec s 
						unicodeChar='-';
						Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + AppSettings.IVRNUM[2]));
						startActivity(intent);
						finish();
						return true;
					}
					else if(unicodeChar=='4'){
						//bill
						unicodeChar='-';
						Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + AppSettings.IVRNUM[3]));
						startActivity(intent);
						finish();
						return true;
					}
					else if(unicodeChar=='0'){
						//op
						unicodeChar='-';
						Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + AppSettings.IVRNUM[4]));
						startActivity(intent);
						finish();
						return true;
					}else{
						unicodeChar='-';
						ERROR();
						return true;
					}
					
				}
				
				}  
				//Toast.makeText(getApplicationContext(), unicodeChar+"", 0).show();
				return true;
			}
		});
		
		
	
	}   
	
	void Play(int res){
		
		try{
	
		if(mp!=null &&mp.isPlaying())mp.stop();
		mp = MediaPlayer.create(this,res); 
		mp.start();
		mp.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
			 
				if(!stack.empty()){
				  
					Play(stack.pop());
			    }
				
			}
		});
		}catch(Exception ex){}
	}
	

}
