package com.cashluto.rewad;

import android.animation.*;
import android.app.*;
import android.content.Intent;
import android.graphics.*;
import android.graphics.Typeface;
import android.os.*;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.animation.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;


public class WithdrawalActivity extends AppCompatActivity {

	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private double balance_N = 0;
	private HashMap<String, Object> map2 = new HashMap<>();
	private String lefts = "";
	private HashMap<String, Object> mok = new HashMap<>();
	private String key_token = "";
	private HashMap<String, Object> mok2 = new HashMap<>();
	private boolean real_contects = false;
	private String balance = "";
	private boolean a_ss = false;
	private boolean l_ss = false;
	private boolean o_ss = false;
	private String input = "";

	// Loading state management
	private boolean isDataLoaded = false;
	private boolean isProcessingWithdrawal = false;

	private RelativeLayout linear4;
	private LinearLayout mainLayout1;
	private TextView textview1;
	private EditText nameField;
	private TextView textview3;
	private EditText upiField;
	private TextView textview5;
	private EditText amountField;
	private TextView conversionText;
	private LinearLayout linear5;
	private Button button1;
	private Button transferButton;
	private LinearLayout linear6;
	private TextView textview7;

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
		setContentView(R.layout.withdrawal);
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
		linear4 = findViewById(R.id.linear4);
		mainLayout1 = findViewById(R.id.mainLayout1);
		textview1 = findViewById(R.id.textview1);
		nameField = findViewById(R.id.nameField);
		textview3 = findViewById(R.id.textview3);
		upiField = findViewById(R.id.upiField);
		textview5 = findViewById(R.id.textview5);
		amountField = findViewById(R.id.amountField);
		conversionText = findViewById(R.id.conversionText);
		linear5 = findViewById(R.id.linear5);
		button1 = findViewById(R.id.button1);
		transferButton = findViewById(R.id.transferButton);
		linear6 = findViewById(R.id.linear6);
		textview7 = findViewById(R.id.textview7);
		auth = FirebaseAuth.getInstance();
		network = new RequestNetwork(this);

		amountField.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				input = _charSeq;

				// Check if the first character is '0' or if the length exceeds 3 digits
				if (input.startsWith("0") || input.length() > 3) {
					input = input.substring(1); // Remove the leading '0'
				}

				// Regex to allow only digits
				if (!input.matches("[0-9]*")) {
					// Remove non-digit characters
					String filteredInput = input.replaceAll("[^0-9]", "");

					// Limit input to 3 digits
					if (filteredInput.length() > 3) {
						filteredInput = filteredInput.substring(0, 3); // Limit to 3 digits
					}

					amountField.setText(filteredInput); // Set the filtered input
					amountField.setSelection(filteredInput.length()); // Move cursor to the end
				}
			}

			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

			}

			@Override
			public void afterTextChanged(Editable _param1) {

			}
		});

		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(button1);
				LayoutInflater Inflater = getLayoutInflater();

				View InfView = getLayoutInflater().inflate(R.layout.newdg2, null);

				LinearLayout border = (LinearLayout) InfView.findViewById(R.id.border);

				LinearLayout linear3 = (LinearLayout) InfView.findViewById(R.id.linear3);

				LinearLayout line = (LinearLayout) InfView.findViewById (R.id.line);

				TextView title = (TextView) InfView.findViewById(R.id.title);

				TextView content = (TextView) InfView.findViewById(R.id.content);

				ImageView image_logo = (ImageView) InfView.findViewById(R.id.image_logo);

				Toast ToastName = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT);

				ToastName.setView(InfView);

				ToastName.show();
				image_logo.setImageResource(R.drawable.error1);
				title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/montserrat_semibold.ttf"), Typeface.NORMAL);
				content.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/montserrat_semibold.ttf"), Typeface.NORMAL);
				_RoundAndBorder(border, "#F44236", 0, "#ffffff", 8);
				_SX_CornerRadius_4(line, "#80ffffff", "#80ffffff", 0, 0, 8, 0, 8);
				title.setText("Error!");
				content.setText("Today Withdrwal Limit has been Over come back tomorrow for more Withdrawal.");
			}
		});

		transferButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// Prevent multiple clicks during processing
				if (isProcessingWithdrawal) {
					SketchwareUtil.showMessage(getApplicationContext(), "Please wait, processing your request...");
					return;
				}

				// Check if data is fully loaded
				if (!isDataLoaded) {
					SketchwareUtil.showMessage(getApplicationContext(), "Please wait for data to load completely");
					return;
				}

				// Validation checks
				if (nameField.getText().toString().trim().isEmpty()) {
					nameField.setError("Enter your name (Max 15 characters)");
					SketchwareUtil.showMessage(getApplicationContext(), "Please Enter Your Name");
				} else if (nameField.getText().toString().length() > 16) {
					nameField.setError("Name cannot exceed 16 characters");
					SketchwareUtil.showMessage(getApplicationContext(), "Name cannot exceed 15 characters");
				} else if (upiField.getText().toString().trim().isEmpty()) {
					upiField.setError("Enter your UPI ID");
					SketchwareUtil.showMessage(getApplicationContext(), "Please Enter Your UPI ID");
				} else if (!upiField.getText().toString().contains("@")) {
					upiField.setError("Invalid UPI ID. Must contain '@'");
					SketchwareUtil.showMessage(getApplicationContext(), "Invalid UPI ID. Must contain '@'");
				} else if (upiField.getText().toString().length() > 80) {
					upiField.setError("UPI ID cannot exceed 80 characters");
					SketchwareUtil.showMessage(getApplicationContext(), "UPI ID cannot exceed 80 characters");
				} else if (amountField.getText().toString().trim().isEmpty()) {
					amountField.setError("Enter withdrawal amount");
					SketchwareUtil.showMessage(getApplicationContext(), "Please Enter Withdrawal Amount");
				} else {
					double amount = Double.parseDouble(amountField.getText().toString());
					if (amount < 10) {
						amountField.setError("Minimum withdrawal is ₹10");
						SketchwareUtil.showMessage(getApplicationContext(), "Minimum withdrawal is ₹10");
					} else if (amount > 1000) {
						amountField.setError("Maximum withdrawal is ₹1000");
						SketchwareUtil.showMessage(getApplicationContext(), "Maximum withdrawal is ₹1000");
					} else if (balance_N < amount) {
						SketchwareUtil.showMessage(getApplicationContext(), "Insufficient balance");
					} else if (!real_contects || lefts.equals("0")) {
						if (!real_contects) {
							SketchwareUtil.showMessage(getApplicationContext(), "Check network connection and try again");
						} else {
							SketchwareUtil.showMessage(getApplicationContext(), "Today's withdrawal limit reached. Try again tomorrow");
						}
					} else {
						isProcessingWithdrawal = true;
						_loadingdialog(true, "Processing withdrawal...");
						_OnlyWithdrawalSuscessTips();
					}
				}
				
				/*
if (nameField.getText().toString().equals("") || (upiField.getText().toString().equals("") || (amountField.getText().toString().equals("") || balance.equals("")))) {
if (nameField.getText().toString().equals("")) {
((EditText)nameField).setError("Enter your name ");
com.google.android.material.snackbar.Snackbar.make(mainLayout1, "Please Enter Your Name", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
@Override
public void onClick(View _view) {
SketchwareUtil.showMessage(getApplicationContext(), "Please Enter Your Name");
}
}).show();
}
if (upiField.getText().toString().equals("")) {
((EditText)nameField).setError("Please enter upi");
com.google.android.material.snackbar.Snackbar.make(mainLayout1, "Please Enter Your Upi id", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
@Override
public void onClick(View _view) {
SketchwareUtil.showMessage(getApplicationContext(), "Please Enter Your Upi id");
}
}).show();
}
if (amountField.getText().toString().equals("")) {
((EditText)amountField).setError("Amount ");
com.google.android.material.snackbar.Snackbar.make(mainLayout1, "Please Enter Your Withdrawal Amount ", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
@Override
public void onClick(View _view) {
SketchwareUtil.showMessage(getApplicationContext(), "Please Enter Your Withdrawal Amount ");
}
}).show();
}
if (balance.equals("")) {
SketchwareUtil.showMessage(getApplicationContext(), "insufficient balance   ♎");
}
}
else {
if ((Double.parseDouble(amountField.getText().toString()) > 9) && (100 < Double.parseDouble(amountField.getText().toString()))) {
if (Double.parseDouble(amountField.getText().toString()) > 9) {
((EditText)amountField).setError("Minimum Withdrawal 10₹.");
}
if (100 < Double.parseDouble(amountField.getText().toString())) {
((EditText)amountField).setError("Maximum Withdrawal 100₹ .");
}
}
else {
if (real_contects && lefts.equals("0")) {
if (real_contects) {
SketchwareUtil.showMessage(getApplicationContext(), "cheak network connection try again");
}
if (lefts.equals("0")) {
SketchwareUtil.showMessage(getApplicationContext(), "Today Withdrwal Limited Try Again tomorrow ");
}
}
else {
if (balance_N > Double.parseDouble(amountField.getText().toString())) {
_loadingdialog(true, "Wait  Withdrawal Amount ");
_OnlyWithdrawalSuscessTips();
}
else {
if (balance_N == Double.parseDouble(amountField.getText().toString())) {
_loadingdialog(true, "Wait Withdrwal Excitation ");
_OnlyWithdrawalSuscessTips();
}
else {
SketchwareUtil.showMessage(getApplicationContext(), "insufficient balance ");
}
}
}
}
}
*/
			}
		});

		_All_Users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				a_ss = true;
//				if (_childValue.containsKey("balance")) {
//					Object balanceObj = _childValue.get("balance");
//
//					if (balanceObj != null) {
//						try {
//							balance = balanceObj.toString();
//							balance_N = Double.parseDouble(balanceObj.toString());
//						} catch (NumberFormatException e) {
//							Log.e("Balance Parsing", "Error parsing balance: " + balanceObj.toString());
//							// Handle the error (e.g., set balance_N to 0 or log an error)
//						}
//					} else {
//						// Handle the case where balance is null
//						balance_N = 0; // default value
//					}
//				}

				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("balance")) {
						balance = _childValue.get("balance").toString();
						balance_N = Double.parseDouble(_childValue.get("balance").toString());
						checkDataLoadingComplete();
					}
				}

				conversionText.setText(String.valueOf((long)(balance_N)));
				/*
if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
if (_childValue.containsKey("balance")) {
balance = _childValue.get("balance").toString();
balance_N = Double.parseDouble(_childValue.get("balance").toString());
}
}
else {

}
*/
			}

			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				a_ss = true;
//				if (_childValue.containsKey("balance")) {
//					Object balanceObj = _childValue.get("balance");
//
//					if (balanceObj != null) {
//						try {
//							balance = balanceObj.toString();
//							balance_N = Double.parseDouble(balanceObj.toString());
//						} catch (NumberFormatException e) {
//							Log.e("Balance Parsing", "Error parsing balance: " + balanceObj.toString());
//							// Handle the error (e.g., set balance_N to 0 or log an error)
//						}
//					} else {
//						// Handle the case where balance is null
//						balance_N = 0; // default value
//					}
//				}

				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("balance")) {
						balance = _childValue.get("balance").toString();
						balance_N = Double.parseDouble(_childValue.get("balance").toString());
						checkDataLoadingComplete();
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
				real_contects = true;
				checkDataLoadingComplete();
			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				real_contects = false;
				if (r != null) {
					r.cancel();
				}
				nty.setClass(getApplicationContext(), NetworkActivity.class);
				startActivity(nty);
			}
		};

		_limited_rq_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				l_ss = true;
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey(new SimpleDateFormat("ddMMyyyy").format(cal.getTime()))) {
						lefts = _childValue.get(new SimpleDateFormat("ddMMyyyy").format(cal.getTime())).toString();
						if (lefts.equals("0")) {
							amountField.setTextColor(0xFFB71C1C);
							transferButton.setEnabled(false);
							conversionText.setText("Today Withdrwal limited");
							conversionText.setTextSize((int)25);
							textview7.setText("Withdrawal limit ".concat(lefts.concat(" / ".concat("1"))));
							button1.setVisibility(View.VISIBLE);
							transferButton.setVisibility(View.GONE);
						}
					}
				}
				checkDataLoadingComplete();
			}

			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				l_ss = true;
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey(new SimpleDateFormat("ddMMyyyy").format(cal.getTime()))) {
						lefts = _childValue.get(new SimpleDateFormat("ddMMyyyy").format(cal.getTime())).toString();
						if (lefts.equals("0")) {
							amountField.setTextColor(0xFFB71C1C);
							transferButton.setEnabled(false);
							conversionText.setText("Today Withdrwal limited");
							conversionText.setTextSize((int)25);
							textview7.setText("Withdrawal limit ".concat(lefts.concat(" / ".concat("1"))));
							button1.setVisibility(View.VISIBLE);
							transferButton.setVisibility(View.GONE);
						}
					}
				}
				checkDataLoadingComplete();
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
				o_ss = true;
				if (_childValue.containsKey("wh_limit")) {
					lefts = _childValue.get("wh_limit").toString();
				}
				checkDataLoadingComplete();
			}

			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				o_ss = true;
				if (_childValue.containsKey("wh_limit")) {
					lefts = _childValue.get("wh_limit").toString();
				}
				checkDataLoadingComplete();
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
		real_contects = false;
		button1.setVisibility(View.GONE);
		_CodingAllXml();
		_loadingdialog(true, "Loading data...");

		r = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_networks();
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(r, (int)(0), (int)(1500));
		setTitle("Withdrawal Money ");
		nameField.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
		upiField.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
		amountField.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
		nameField.setSingleLine(true);
		upiField.setSingleLine(true);
		amountField.setSingleLine(true);
	}

	// Method to check if all data is loaded
	private void checkDataLoadingComplete() {
		if (real_contects && a_ss && o_ss) {
			isDataLoaded = true;
			_loadingdialog(false, "");
			if (r != null) {
				r.cancel();
			}
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (r != null) {
			r.cancel();
		}
	}


	public void _CodingAllXml() {

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
		/*
if (real_contects) {
if (SketchwareUtil.isConnected(getApplicationContext())) {

if ("15/04/1987".equals("99999")) {
SketchwareUtil.showMessage(getApplicationContext(), "alredy withdrwaled");
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
}
else {
SketchwareUtil.showMessage(getApplicationContext(), "Insufficent Balance");
}
}
}
}
else {
SketchwareUtil.showMessage(getApplicationContext(), "Network has been disconnected ");
}
}
else {
SketchwareUtil.showMessage(getApplicationContext(), "Network has been not connecting ");
}
*/
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


	public void _OnlyWithdrawalSuscessTips() {
		if (balance_N == 0) {
			isProcessingWithdrawal = false;
			_loadingdialog(false, "");
			SketchwareUtil.showMessage(getApplicationContext(), "withdrawal cancelled due to technical issue");
		}
		else {
			// Success flags for tracking the completion of each task
			final boolean[] isBalanceUpdated = {false};
			final boolean[] isLimitedUpdated = {false};
			final boolean[] isHistoryUpdated = {false};

			// Method to check if all tasks are successful
			Runnable checkAllSuccess = new Runnable() {
				@Override
				public void run() {
					// Ensure balance is updated first, then proceed with others
					if (isBalanceUpdated[0] && isLimitedUpdated[0] && isHistoryUpdated[0]) {
						// Stop loading dialog and reset processing flag
						isProcessingWithdrawal = false;
						_loadingdialog(false, "");

						// Final Custom Toast and Finish Activity
						LayoutInflater inflater = getLayoutInflater();
						View infView = inflater.inflate(R.layout.newdg2, null);

						LinearLayout border = infView.findViewById(R.id.border);
						LinearLayout line = infView.findViewById(R.id.line);
						TextView title = infView.findViewById(R.id.title);
						TextView content = infView.findViewById(R.id.content);
						ImageView imageLogo = infView.findViewById(R.id.image_logo);

						Toast toastName = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);
						toastName.setView(infView);

						imageLogo.setImageResource(R.drawable.success1);
						title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf"), Typeface.BOLD);
						content.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/montserrat_semibold.ttf"));

						_RoundAndBorder(border, "#47D765", 0, "#ffffff", 8);
						_SX_CornerRadius_4(line, "#80ffffff", "#80ffffff", 0, 0, 8, 0, 8);

						title.setText("Success!");
						content.setText("Your Withdrawal Has Been Successfully placed. Check redeem history for status.");

						toastName.show();

						// Delay finish to allow toast to show
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								finish();
							}
						}, 2000);
					}
				}
			};

			// **Balance Update** (this should be done first)
			mok2 = new HashMap<>();
			mok2.put("balance", String.valueOf((long)(balance_N - Double.parseDouble(amountField.getText().toString()))));
			All_Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
					.updateChildren(mok2)
					.addOnSuccessListener(new OnSuccessListener<Void>() {
						@Override
						public void onSuccess(Void unused) {
							isBalanceUpdated[0] = true;
							// Call checkAllSuccess only after balance is updated
							checkAllSuccess.run();

							// Now proceed with other updates
							// **Limited Request Update**
							HashMap<String, Object> map2 = new HashMap<>();
							map2.put(new SimpleDateFormat("ddMMyyyy").format(cal.getTime()),
									String.valueOf((long)(Double.parseDouble(lefts) - 1)));
							limited_rq.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
									.updateChildren(map2)
									.addOnSuccessListener(new OnSuccessListener<Void>() {
										@Override
										public void onSuccess(Void unused) {
											isLimitedUpdated[0] = true;
											checkAllSuccess.run(); // re-check once limited request is done
										}
									})
									.addOnFailureListener(new OnFailureListener() {
										@Override
										public void onFailure(Exception e) {
											isProcessingWithdrawal = false;
											_loadingdialog(false, "");
											Toast.makeText(getApplicationContext(), "Failed to update limited request: " + e.getMessage(), Toast.LENGTH_LONG).show();
										}
									});

							// **Pending History Update**
							mok = new HashMap<>();
							key_token = rdd.push().getKey();
							mok.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
							mok.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
							mok.put("date", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(col.getTime()));
							mok.put("name", nameField.getText().toString());
							mok.put("upi", upiField.getText().toString());
							mok.put("amount", amountField.getText().toString());
							mok.put("st", "Pending");
							rdd.child(key_token).updateChildren(mok)
									.addOnSuccessListener(new OnSuccessListener<Void>() {
										@Override
										public void onSuccess(Void unused) {
											isHistoryUpdated[0] = true;
											checkAllSuccess.run(); // re-check once history update is done
										}
									})
									.addOnFailureListener(new OnFailureListener() {
										@Override
										public void onFailure(Exception e) {
											isProcessingWithdrawal = false;
											_loadingdialog(false, "");
											Toast.makeText(getApplicationContext(), "Failed to add history: " + e.getMessage(), Toast.LENGTH_LONG).show();
										}
									});
						}
					})
					.addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(Exception e) {
							isProcessingWithdrawal = false;
							_loadingdialog(false, "");
							Toast.makeText(getApplicationContext(), "Failed to update balance: " + e.getMessage(), Toast.LENGTH_LONG).show();
						}
					});
			
			/*

// Success flags
final boolean[] isLimitedUpdated = {false};
final boolean[] isBalanceUpdated = {false};
final boolean[] isHistoryUpdated = {false};

// Method to check if all tasks are successful
Runnable checkAllSuccess = new Runnable() {
    @Override
    public void run() {
        if (isLimitedUpdated[0] && isBalanceUpdated[0] && isHistoryUpdated[0]) {
            // Stop loading dialog
            _loadingdialog(false, "");

            // Final Custom Toast and Finish Activity
            LayoutInflater inflater = getLayoutInflater();
            View infView = inflater.inflate(R.layout.newdg2, null);

            LinearLayout border = infView.findViewById(R.id.border);
            LinearLayout line = infView.findViewById(R.id.line);
            TextView title = infView.findViewById(R.id.title);
            TextView content = infView.findViewById(R.id.content);
            ImageView imageLogo = infView.findViewById(R.id.image_logo);

            Toast toastName = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
            toastName.setView(infView);

            imageLogo.setImageResource(R.drawable.success1);
            title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf"), Typeface.BOLD);
            content.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/montserrat_semibold.ttf"));

            _RoundAndBorder(border, "#47D765", 0, "#ffffff", 8);
            _SX_CornerRadius_4(line, "#80ffffff", "#80ffffff", 0, 0, 8, 0, 8);

            title.setText("Success!");
            content.setText("Your Withdrawal Has Been Successfully placed. Check redeem history for status.");

            toastName.show();
            finish();
        }
    }
};

// **Limited Request Update**
HashMap<String, Object> map2 = new HashMap<>();
map2.put(new SimpleDateFormat("ddMMyyyy").format(cal.getTime()),
    String.valueOf((long)(Double.parseDouble(lefts) - 1)));
limited_rq.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
    .updateChildren(map2)
    .addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void unused) {
            isLimitedUpdated[0] = true;
            checkAllSuccess.run();
        }
    })
    .addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(Exception e) {
            _loadingdialog(false, "");
            Toast.makeText(getApplicationContext(), "Failed to update limited request: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    });

// **Balance Update**
mok2 = new HashMap<>();
mok2.put("balance", String.valueOf((long)(balance_N - Double.parseDouble(amountField.getText().toString()))));
All_Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
    .updateChildren(mok2)
    .addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void unused) {
            isBalanceUpdated[0] = true;
            checkAllSuccess.run();
        }
    })
    .addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(Exception e) {
            _loadingdialog(false, "");
            Toast.makeText(getApplicationContext(), "Failed to update balance: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    });

// **Pending History Update**
mok = new HashMap<>();
key_token = rdd.push().getKey();
mok.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
mok.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
mok.put("date", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(col.getTime()));
mok.put("name", nameField.getText().toString());
mok.put("upi", upiField.getText().toString());
mok.put("amount", amountField.getText().toString());
mok.put("st", "Pending");
rdd.child(key_token).updateChildren(mok)
    .addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void unused) {
            isHistoryUpdated[0] = true;
            checkAllSuccess.run();
        }
    })
    .addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(Exception e) {
            _loadingdialog(false, "");
            Toast.makeText(getApplicationContext(), "Failed to add history: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    });

 

*/
			/*
// Initialize success flags
final boolean[] isBalanceUpdated = {false};
final boolean[] isHistoryUpdated = {false};

// Method to check if both tasks are successful
Runnable checkBothSuccess = () -> {
    if (isBalanceUpdated[0] && isHistoryUpdated[0]) {
        // Stop loading dialog
        _loadingdialog(false, "");

        // Final Custom Toast and Finish Activity
        LayoutInflater Inflater = getLayoutInflater();
        View InfView = Inflater.inflate(R.layout.newdg2, null);

        LinearLayout border = InfView.findViewById(R.id.border);
        LinearLayout linear3 = InfView.findViewById(R.id.linear3);
        LinearLayout line = InfView.findViewById(R.id.line);
        TextView title = InfView.findViewById(R.id.title);
        TextView content = InfView.findViewById(R.id.content);
        ImageView image_logo = InfView.findViewById(R.id.image_logo);

        Toast ToastName = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
        ToastName.setView(InfView);

        image_logo.setImageResource(R.drawable.success1);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf"), Typeface.BOLD);
        content.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/montserrat_semibold.ttf"));

        _RoundAndBorder(border, "#47D765", 0, "#ffffff", 8);
        _SX_CornerRadius_4(line, "#80ffffff", "#80ffffff", 0, 0, 8, 0, 8);

        title.setText("Success!");
        content.setText("Your Withdrawal Has Been Successfully placed. Check redeem history for status.");

        ToastName.show();
        finish();
    }
};

// Balance update code
mok2 = new HashMap<>();
mok2.put("balance", String.valueOf((long)(balance_N - Double.parseDouble(amountField.getText().toString()))));
All_Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(mok2)
    .addOnSuccessListener(unused -> {
        isBalanceUpdated[0] = true;
        Toast.makeText(getApplicationContext(), "Balance updated successfully!", Toast.LENGTH_SHORT).show();
        checkBothSuccess.run();
    })
    .addOnFailureListener(e -> {
        _loadingdialog(false, "");
        Toast.makeText(getApplicationContext(), "Failed to update balance: " + e.getMessage(), Toast.LENGTH_LONG).show();
    });

// Add pending history
mok = new HashMap<>();
key_token = rdd.push().getKey();
mok.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
mok.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
mok.put("date", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(col.getTime()));
mok.put("name", nameField.getText().toString());
mok.put("upi", upiField.getText().toString());
mok.put("amount", amountField.getText().toString());
mok.put("st", "Pending");
rdd.child(key_token).updateChildren(mok)
    .addOnSuccessListener(unused1 -> {
        isHistoryUpdated[0] = true;
        checkBothSuccess.run();
    })
    .addOnFailureListener(e -> {
        _loadingdialog(false, "");
        Toast.makeText(getApplicationContext(), "Failed to add history: " + e.getMessage(), Toast.LENGTH_LONG).show();
    });

*/
			/*
//old cide of without loading dialog
// Balance update code
mok2 = new HashMap<>();
mok2.put("balance", String.valueOf((long)(balance_N - Double.parseDouble(amountField.getText().toString()))));
All_Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(mok2)
    .addOnSuccessListener(unused -> {
        // Success Toast after balance update
        Toast.makeText(getApplicationContext(), "Balance updated successfully!", Toast.LENGTH_SHORT).show();
        
        // Add pending history
        mok = new HashMap<>();
        key_token = rdd.push().getKey();
        mok.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
        mok.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        mok.put("date", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(col.getTime()));
        mok.put("name", nameField.getText().toString());
        mok.put("upi", upiField.getText().toString());
        mok.put("amount", amountField.getText().toString());
        mok.put("st", "Pending");
        rdd.child(key_token).updateChildren(mok)
            .addOnSuccessListener(unused1 -> {
                // Final Custom Toast and Finish Activity
                LayoutInflater Inflater = getLayoutInflater();
                View InfView = Inflater.inflate(R.layout.newdg2, null);

                LinearLayout border = InfView.findViewById(R.id.border);
                LinearLayout linear3 = InfView.findViewById(R.id.linear3);
                LinearLayout line = InfView.findViewById(R.id.line);
                TextView title = InfView.findViewById(R.id.title);
                TextView content = InfView.findViewById(R.id.content);
                ImageView image_logo = InfView.findViewById(R.id.image_logo);

                Toast ToastName = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
                ToastName.setView(InfView);

                image_logo.setImageResource(R.drawable.success1);
                title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf"), Typeface.BOLD);
                content.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/montserrat_semibold.ttf"));

                _RoundAndBorder(border, "#47D765", 0, "#ffffff", 8);
                _SX_CornerRadius_4(line, "#80ffffff", "#80ffffff", 0, 0, 8, 0, 8);

                title.setText("Success!");
                content.setText("Your Withdrawal Has Been Successfully placed. Check redeem history for status.");

                ToastName.show();
                finish();
            })
            .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to add history: " + e.getMessage(), Toast.LENGTH_LONG).show());
    })
    .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to update balance: " + e.getMessage(), Toast.LENGTH_LONG).show());

*/
			/*
map2 = new HashMap<>();
map2.put(new SimpleDateFormat("ddMMyyyy").format(cal.getTime()), String.valueOf((long)(Double.parseDouble(lefts) - 1)));
limited_rq.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map2);
map2.clear();
mok2 = new HashMap<>();
mok2.put("balance", String.valueOf((long)(balance_N - Double.parseDouble(amountField.getText().toString()))));
All_Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(mok2);
mok2.clear();
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
SketchwareUtil.showMessage(getApplicationContext(), "Your Withdrawal Has Benn Susfully placed cheak in redeem history of the status ");
LayoutInflater Inflater = getLayoutInflater();

View InfView = getLayoutInflater().inflate(R.layout.newdg2, null);

LinearLayout border = (LinearLayout) InfView.findViewById(R.id.border);

LinearLayout linear3 = (LinearLayout) InfView.findViewById(R.id.linear3);

LinearLayout line = (LinearLayout) InfView.findViewById (R.id.line);

TextView title = (TextView) InfView.findViewById(R.id.title);

TextView content = (TextView) InfView.findViewById(R.id.content);

ImageView image_logo = (ImageView) InfView.findViewById(R.id.image_logo);

Toast ToastName = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT);

ToastName.setView(InfView);

ToastName.show();
image_logo.setImageResource(R.drawable.success1);
title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/montserrat_bold.ttf"), 1);
content.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/montserrat_semibold.ttf"), 0);
_RoundAndBorder(border, "#47D765", 0, "#ffffff", 8);
_SX_CornerRadius_4(line, "#80ffffff", "#80ffffff", 0, 0, 8, 0, 8);
title.setText("Success!");
content.setText("Your Withdrawal Has Benn Susfully placed cheak in redeem history of the status ");
*/
		}
	}


	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}


	public void _SX_CornerRadius_4(final View _view, final String _color1, final String _color2, final double _str, final double _n1, final double _n2, final double _n3, final double _n4) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();

		gd.setColor(Color.parseColor(_color1));

		gd.setStroke((int)_str, Color.parseColor(_color2));

		gd.setCornerRadii(new float[]{(int)_n1,(int)_n1,(int)_n2,(int)_n2,(int)_n3,(int)_n3,(int)_n4,(int)_n4});

		_view.setBackground(gd);

		_view.setElevation(8);
	}


	public void _AnimationLogo(final View _view, final String _propertyName, final double _value, final double _duration) {
		ObjectAnimator anim = new ObjectAnimator();

		anim.setTarget(_view);

		anim.setPropertyName(_propertyName);

		anim.setFloatValues((float)_value);

		anim.setDuration((long)_duration);

		anim.setInterpolator(new AccelerateDecelerateInterpolator());

		anim.start();
	}


	public void _clickAnimation(final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(300);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
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