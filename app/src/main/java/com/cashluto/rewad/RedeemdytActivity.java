package com.cashluto.rewad;

import android.app.*;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.shobhitpuri.custombuttons.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class RedeemdytActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String balance = "";
	private HashMap<String, Object> mok = new HashMap<>();
	private String key_token = "";
	private HashMap<String, Object> mok2 = new HashMap<>();
	private double balance_N = 0;
	private boolean data1 = false;
	private boolean data2 = false;
	private boolean data3 = false;
	private boolean data4 = false;
	private double current_balance = 0;
	private HashMap<String, Object> map2 = new HashMap<>();
	private HashMap<String, Object> n_rq = new HashMap<>();
	private String withdraw_amount = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String redeemKey = "";
	private String name = "";
	private String mobilenum = "";
	private String city_1 = "";
	private HashMap<String, Object> update_r = new HashMap<>();
	private double total_wh = 0;
	private String lefts = "";
	
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	
	private DatabaseReference All_Users = _firebase.getReference("All_Users");
	private ChildEventListener _All_Users_child_listener;
	private DatabaseReference rdd = _firebase.getReference("redeem");
	private ChildEventListener _rdd_child_listener;
	private Calendar col = Calendar.getInstance();
	private RequestNetwork network;
	private RequestNetwork.RequestListener _network_request_listener;
	private Intent nty = new Intent();
	private DatabaseReference limited_rq = _firebase.getReference("limited_rq");
	private ChildEventListener _limited_rq_child_listener;
	private Calendar cal = Calendar.getInstance();
	private DatabaseReference other = _firebase.getReference("other/limit_wh");
	private ChildEventListener _other_child_listener;
	private TimerTask r;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.redeemdyt);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		auth = FirebaseAuth.getInstance();
		network = new RequestNetwork(this);
		
		_All_Users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				final EditText nameField = findViewById(R.id.nameField);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("balance")) {
						balance = _childValue.get("balance").toString();
						balance_N = Double.parseDouble(_childValue.get("balance").toString());
					}
				}
				else {
					
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				final EditText nameField = findViewById(R.id.nameField);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (_childValue.containsKey("balance")) {
								balance = _childValue.get("balance").toString();
								balance_N = Double.parseDouble(_childValue.get("balance").toString());
						}
				}
				else {
						
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		All_Users.addChildEventListener(_All_Users_child_listener);
		
		_rdd_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		rdd.addChildEventListener(_rdd_child_listener);
		
		_network_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				nty.setClass(getApplicationContext(), NetworkActivity.class);
				nty.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nty);
				_networks();
			}
		};
		
		_limited_rq_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				final EditText nameField = findViewById(R.id.nameField);
				final EditText upiField = findViewById(R.id.upiField);
				final EditText amountField = findViewById(R.id.amountField);
				final Button transferButton = findViewById(R.id.transferButton);
				final TextView conversionText = findViewById(R.id.conversionText);
				
				
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey(new SimpleDateFormat("ddMMyyyy").format(cal.getTime()))) {
						lefts = _childValue.get(new SimpleDateFormat("ddMMyyyy").format(cal.getTime())).toString();
						if (lefts.equals("0")) {
							amountField.setTextColor(0xFFB71C1C);
							transferButton.setEnabled(false);
							conversionText.setText("Today Withdrwal limited");
							conversionText.setTextSize((int)38);
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				final EditText nameField = findViewById(R.id.nameField);
				final EditText upiField = findViewById(R.id.upiField);
				final EditText amountField = findViewById(R.id.amountField);
				final Button transferButton = findViewById(R.id.transferButton);
				final TextView conversionText = findViewById(R.id.conversionText);
				
				
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (_childValue.containsKey(new SimpleDateFormat("ddMMyyyy").format(cal.getTime()))) {
								lefts = _childValue.get(new SimpleDateFormat("ddMMyyyy").format(cal.getTime())).toString();
								if (lefts.equals("0")) {
										amountField.setTextColor(0xFFB71C1C);
										transferButton.setEnabled(false);
										conversionText.setText("Today Withdrwal limited");
										conversionText.setTextSize((int)38);
								}
						}
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		limited_rq.addChildEventListener(_limited_rq_child_listener);
		
		_other_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("wh_limit")) {
					lefts = _childValue.get("wh_limit").toString();
					SketchwareUtil.showMessage(getApplicationContext(), _childValue.get("wh_limit").toString());
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("wh_limit")) {
						lefts = _childValue.get("wh_limit").toString();
						SketchwareUtil.showMessage(getApplicationContext(), _childValue.get("wh_limit").toString());
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		other.addChildEventListener(_other_child_listener);
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		setTitle("Withdrawal Money");
		_networks();
		_CodingAllXml();
		r = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (balance.equals("")) {
							_loadingdialog(true, "Loading Account...");
						}
						else {
							_loadingdialog(false, "");
							if (String.valueOf((long)(balance_N)).equals("")) {
								_loadingdialog(true, "Loading Account...");
							}
							else {
								_loadingdialog(false, "");
								r.cancel();
							}
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(r, (int)(0), (int)(1000));
	}
	
	public void _CodingAllXml() {
		setContentView(R.layout.activity_redeem);
		// Finding views for the main content inside the ScrollView
		final EditText nameField = findViewById(R.id.nameField);
		final EditText upiField = findViewById(R.id.upiField);
		final EditText amountField = findViewById(R.id.amountField);
		final Button transferButton = findViewById(R.id.transferButton);
		
		// Finding views for the Toolbar and AppBarLayout
		final CoordinatorLayout coordinatorLayout = findViewById(R.id._coordinator);
		final AppBarLayout appBarLayout = findViewById(R.id._app_bar);
		final Toolbar toolbar = findViewById(R.id._toolbar);
		
		// (Optional) Finding other elements like the TextView for conversion rate
		final TextView conversionText = findViewById(R.id.conversionText);
		
		// (Optional) If you want to use `mainLayout1` which is set with zero height and width in XML
		final View mainLayout1 = findViewById(R.id.mainLayout1);
		
		setTitle("Hello");
		transferButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (nameField.getText().toString().equals("")) {
					com.google.android.material.snackbar.Snackbar.make(mainLayout1, "Please Enter Your Name", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
						@Override
						public void onClick(View _view) {
							SketchwareUtil.showMessage(getApplicationContext(), "Please Enter Your Name");
						}
					}).show();
				}
				else {
					if (upiField.getText().toString().equals("")) {
						com.google.android.material.snackbar.Snackbar.make(mainLayout1, "Please Enter Your Upi id", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
							@Override
							public void onClick(View _view) {
								SketchwareUtil.showMessage(getApplicationContext(), "Please Enter Your Upi id");
							}
						}).show();
					}
					else {
						if (amountField.getText().toString().equals("")) {
							com.google.android.material.snackbar.Snackbar.make(mainLayout1, "Please Enter Your Withdrawal Amount ", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
								@Override
								public void onClick(View _view) {
									SketchwareUtil.showMessage(getApplicationContext(), "Please Enter Your Withdrawal Amount ");
								}
							}).show();
						}
						else {
							/*
if (balance_N == Double.parseDouble(amountField.getText().toString())) {
_loadingdialog(false, "");
_WithdrawalApiAcces();
}
else {

if (balance_N < Double.parseDouble(amountField.getText().toString())) {
_loadingdialog(false, "");
_WithdrawalApiAcces();
}
else {
_loadingdialog(false, "");
SketchwareUtil.showMessage(getApplicationContext(), "in your wallet Account Withdrawal Amount not available ");
}
}
*/
							if (Double.parseDouble(amountField.getText().toString()) > 9) {
								if (100 < Double.parseDouble(amountField.getText().toString())) {
									((EditText)amountField).setError("Maximum 100₹ Withdrawal ");
									SketchwareUtil.showMessage(getApplicationContext(), "Maximum 100₹ Withdrawal at a time");
								}
								else {
									final AlertDialog dialog = new AlertDialog.Builder(RedeemdytActivity.this).create();
									View inflate = getLayoutInflater().inflate(R.layout.redeem_cus,null); 
									dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
									dialog.setView(inflate);
									LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
									TextView b1 = (TextView) inflate.findViewById(R.id.b1);
									b1.setText("CONFIRM");
									TextView amount = (TextView) inflate.findViewById(R.id.amount);
									amount.setText(amountField.getText().toString());
									TextView method = (TextView) inflate.findViewById(R.id.method);
									method.setText("UPI Id");
									TextView account = (TextView) inflate.findViewById(R.id.account);
									account.setText(upiField.getText().toString());
									TextView time = (TextView) inflate.findViewById(R.id.time);
									time.setText(new SimpleDateFormat("dd MMM yyyy").format(cal.getTime()));
									bg.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFFFFFFFF));
									_RippleRoundStroke(b1, "#FFC107", "#E9E9E9", 25, 10, 0, "#000000");
									b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
											_WithdrawalApiAcces();
											SketchwareUtil.showMessage(getApplicationContext(), "Please Wait Loading...");
											dialog.dismiss();
										}
									});
									dialog.setCancelable(true);
									dialog.show();
								}
							}
							else {
								((EditText)amountField).setError("Minimum 10₹");
								SketchwareUtil.showMessage(getApplicationContext(), "Minimum Withdrawal 10₹");
							}
						}
					}
				}
			}
		});
	}
	
	
	public void _loadingdialog(final boolean _ifShow, final String _Title) {
		if (_ifShow) {
			if (prog == null){
				prog = new ProgressDialog(this);
				prog.setMax(100);
				prog.setIndeterminate(true);
				prog.setCancelable(false);
				prog.setCanceledOnTouchOutside(false);
			}
			prog.setMessage(_Title);
			prog.show();
			
		}
		else {
			if (prog != null){
				prog.dismiss();
			}
			
			
		}
	} private ProgressDialog prog; {
		
		
	}
	
	
	public void _WithdrawalApiAcces() {
		if (SketchwareUtil.isConnected(getApplicationContext())) {
			// Finding views for the main content inside the ScrollView
			final EditText nameField = findViewById(R.id.nameField);
			final EditText upiField = findViewById(R.id.upiField);
			final EditText amountField = findViewById(R.id.amountField);
			final Button transferButton = findViewById(R.id.transferButton);
			if ("15/04/1987".equals("99999")) {
				SketchwareUtil.showMessage(getApplicationContext(), "alredy withdrwaled");
				_loadingdialog(false, "");
			}
			else {
				if (balance_N > Double.parseDouble(amountField.getText().toString())) {
					map2.clear();
					map2 = new HashMap<>();
					map2.put(new SimpleDateFormat("ddMMyyyy").format(cal.getTime()), String.valueOf((long)(Double.parseDouble(lefts) - 1)));
					limited_rq.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map2);
					map2.clear();
					
					
					mok = new HashMap<>();
					key_token = rdd.push().getKey();
					mok.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					mok.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
					mok.put("date", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(col.getTime()));
					mok.put("name", nameField.getText().toString());
					mok.put("upi", upiField.getText().toString());
					mok.put("amount", amountField.getText().toString());
					mok.put("st", "Pending");
					rdd.child(key_token).updateChildren(mok);
					mok.clear();
					mok2 = new HashMap<>();
					mok2.put("balance", String.valueOf((long)(balance_N - Double.parseDouble(amountField.getText().toString()))));
					All_Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(mok2);
					mok2.clear();
					SketchwareUtil.showMessage(getApplicationContext(), "Your Withdrawal Has Benn Susfully placed cheak in redeem history of the status ");
					_loadingdialog(false, "");
					finish();
				}
				else {
					if (balance_N == Double.parseDouble(amountField.getText().toString())) {
						map2.clear();
						map2 = new HashMap<>();
						map2.put(new SimpleDateFormat("ddMMyyyy").format(cal.getTime()), String.valueOf((long)(Double.parseDouble(lefts) - 1)));
						limited_rq.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map2);
						map2.clear();
						
						
						mok = new HashMap<>();
						key_token = rdd.push().getKey();
						mok.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
						mok.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
						mok.put("date", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(col.getTime()));
						mok.put("name", nameField.getText().toString());
						mok.put("upi", upiField.getText().toString());
						mok.put("amount", amountField.getText().toString());
						mok.put("st", "Pending");
						rdd.child(key_token).updateChildren(mok);
						mok.clear();
						mok2 = new HashMap<>();
						mok2.put("balance", String.valueOf((long)(balance_N - Double.parseDouble(amountField.getText().toString()))));
						All_Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(mok2);
						mok2.clear();
						SketchwareUtil.showMessage(getApplicationContext(), "Your Withdrawal Has Benn Susfully placed cheak in redeem history of the status ");
						_loadingdialog(false, "");
						finish();
					}
					else {
						_loadingdialog(false, "");
						SketchwareUtil.showMessage(getApplicationContext(), "Insufficent Balance");
					}
				}
			}
		}
		else {
			_loadingdialog(false, "");
			SketchwareUtil.showMessage(getApplicationContext(), "Network has been disconnected ");
		}
	}
	
	
	public void _networks() {
		network.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "Sketch Store Yt", _network_request_listener);
	}
	
	
	public void _RippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _shadow, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
		_view.setElevation((int)_shadow);
	}
	
	
	public void _Withdraw_Dialog() {
		
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
