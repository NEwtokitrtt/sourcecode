package com.cashluto.rewad;

import android.content.Intent;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.shobhitpuri.custombuttons.*;

import java.util.*;
import java.util.Timer;
import java.util.TimerTask;


public class PrrActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private double v_limt = 0;
	
	private LinearLayout linear1;
	private TextView textview1;
	private ProgressBar progressbar1;
	
	private Intent n_gr = new Intent();
	private TimerTask wrk_f;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.prr);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		progressbar1 = findViewById(R.id.progressbar1);
	}
	
	private void initializeLogic() {
		/*

v_limt = 0;
wrk_f = new TimerTask() {
@Override
public void run() {
runOnUiThread(new Runnable() {
@Override
public void run() {
if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == 
                    PackageManager.PERMISSION_GRANTED) {
    // Permission granted
    
startActivity(n_gr);
wrk_f.cancel();
finish();

} else {
    // Permission not granted
    if (v_limt == 0) {
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
		CharSequence name = "Channel name 1";
		String description = "Notification channel";
		int importance = NotificationManager.IMPORTANCE_MAX;
		NotificationChannel channel = new NotificationChannel("id 1", name, importance);
		channel.setDescription(description);
		
		NotificationManager notificationManager = getSystemService(NotificationManager.class);
		notificationManager.createNotificationChannel(channel);
}
if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == 
                    PackageManager.PERMISSION_GRANTED) {
    // Permission granted
     

} else {
    // Permission not granted
    v_limt = v_limt + 1;

}

}
else {
if (v_limt == 2) {

startActivity(n_gr);
wrk_f.cancel();
finish();
}
}

}

}
});
}
};
_timer.scheduleAtFixedRate(wrk_f, (int)(0), (int)(3000));
 

*/
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
