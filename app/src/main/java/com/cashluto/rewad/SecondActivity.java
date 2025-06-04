package com.cashluto.rewad;

import android.content.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.View;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.shobhitpuri.custombuttons.*;

import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.widget.Toast;



public class SecondActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private String pk = "";
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private Button button1;
	private ImageView imageview1;
	private TextView textview1;
	
	private TimerTask f;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.second);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		button1 = findViewById(R.id.button1);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_uninstallApp(pk);
			}
		});
	}
	
	private void initializeLogic() {
		textview1.setText(getIntent().getStringExtra("message").concat(pk));
		pk = getIntent().getStringExtra("PKG");
		ImageView imageView = findViewById(R.id.imageview1);  // Replace with the correct ID
		
		try {
			    // Replace with the actual package name
			    PackageManager packageManager = getPackageManager();
			    Drawable appIcon = packageManager.getApplicationIcon(pk);
			    imageView.setImageDrawable(appIcon);
		} catch (PackageManager.NameNotFoundException e) {
			    Toast.makeText(this, "Package not found", Toast.LENGTH_SHORT).show();
		}
		
		SketchwareUtil.showMessage(getApplicationContext(), pk);
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	

	public void _uninstallApp(final String _packageName) {
		if (!_packageName.equals("")) {
			Uri packageURI = Uri.parse("package:".concat(pk)); Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI); startActivity(uninstallIntent);
			SketchwareUtil.showMessage(getApplicationContext(), "after uninstall re-start the app to Work properly!.");
		}
		else {
			SketchwareUtil.showMessage(getApplicationContext(), "ERROR{}");
		}
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
