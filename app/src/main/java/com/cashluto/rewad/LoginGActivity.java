package com.cashluto.rewad;

import android.app.*;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.os.*;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.util.*;
import android.view.View;
import android.view.View.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.shobhitpuri.custombuttons.*;
import android.content.ClipboardManager;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import android.content.ClipboardManager;
import android.content.Context;


public class LoginGActivity extends AppCompatActivity {
	
	public final int REQ_CD_GLOGIN = 101;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String spk = "";
	private double n = 0;
	private double exist = 0;
	private String emailAddress = "";
	private String password = "";
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> usersdata = new HashMap<>();
	private String id = "";
	GoogleSignInOptions options;
	AuthCredential credential;
	GoogleSignInAccount account;
	FirebaseUser FirebaseUser;
	private HashMap<String, Object> mapt = new HashMap<>();
	private String Koiskey = "";
	private String rf_by = "";
	private String id2;
	private String fcm_tiken = "";
	private String userID = "";
	private boolean isEnabled = false;
	private boolean isSubscribed = false;
	
	private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private ImageView imageview1;
	private TextView textview1;
	private ImageView imageview2;
	private TextView textview2;
	private LinearLayout linear5;
	private TextView textview4;
//	private ImageView imageview3;
//	private TextView textview3;
//	private ImageView imageview4;
	
	private TextToSpeech h;
	private DatabaseReference All_Users = _firebase.getReference("All_Users");
	private ChildEventListener _All_Users_child_listener;
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
	
	private Intent ii = new Intent();
	private TimerTask t;
	private Calendar cal = Calendar.getInstance();
	private GoogleSignInClient glogin;
	private DatabaseReference histry_of_task = _firebase.getReference("histry_of_task");
	private ChildEventListener _histry_of_task_child_listener;
	private Calendar koi = Calendar.getInstance();
	private RequestNetwork r;
	private RequestNetwork.RequestListener _r_request_listener;
	private DynamicLink dynamic;
	private OnSuccessListener dynamic_onSuccessLink;
	private OnFailureListener dynamic_onFailureLink;
	private SharedPreferences tok;
	private TimerTask t_wo;
	private TimerTask io;
	private TimerTask ospp;
	private SharedPreferences rrr;
	private ProgressBar p1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.login_g);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
//		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		imageview2 = findViewById(R.id.imageview2);
		textview2 = findViewById(R.id.textview2);
//		linear5 = findViewById(R.id.linear5);
		textview4 = findViewById(R.id.textview4);
//		imageview3 = findViewById(R.id.imageview3);
//		textview3 = findViewById(R.id.textview3);
//		imageview4 = findViewById(R.id.imageview4);
		p1 = findViewById(R.id.p1);
//		TextView welcome_text = findViewById(R.id.welcome_text);
//		TextView subtitle_text = findViewById(R.id.subtitle_text);

		h = new TextToSpeech(getApplicationContext(), null);
		auth = FirebaseAuth.getInstance();
		r = new RequestNetwork(this);
		tok = getSharedPreferences("tok", Activity.MODE_PRIVATE);
		rrr = getSharedPreferences("rrr", Activity.MODE_PRIVATE);
		String rf_by = getClipboardText();
		// Inside your activity or context-aware component
		String id2 = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
		linear4.setVisibility(View.VISIBLE);
		p1.setVisibility(View.GONE);
		imageview2.performClick();

		imageview2.setOnClickListener(new OnClickListener() {
										  @Override
										  public void onClick(View v) {

											  String rf_by = getClipboardText();

										  }
									  }
		);
		
		linear4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View _view) {

				n = 0;
					exist = 0;
					for(int _repeat12 = 0; _repeat12 < (int)(map1.size()); _repeat12++) {
						if (map1.get((int)n).get("device_id").toString().equals(id)) {
							if (map1.get((int)n).get("device_id").toString().equals(id)) {
								SketchwareUtil.showMessage(getApplicationContext(), "Your Device reagister login ".concat(map1.get((int)n).get("email").toString()));
								Intent signInIntent = glogin.getSignInIntent();
//
								startActivityForResult(signInIntent, REQ_CD_GLOGIN);
								refgot();
								linear4.setVisibility(View.GONE);
								p1.setVisibility(View.VISIBLE);
							exist = 1;
							}
						}
					n++;
				}
					if (exist == 0) {

						Intent signInIntent = glogin.getSignInIntent();
//
				startActivityForResult(signInIntent, REQ_CD_GLOGIN);
				refgot();
				linear4.setVisibility(View.GONE);
				p1.setVisibility(View.VISIBLE);



//				_firebaseAuthWithGoogle(account);
	/*

((EditText)edittext_name).setError("Please Enter Name");


*/
					}






//				Intent signInIntent = glogin.getSignInIntent();
//
//				startActivityForResult(signInIntent, REQ_CD_GLOGIN);
			}
		});
		
		_All_Users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				All_Users.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						map1 = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								map1.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
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
		All_Users.addChildEventListener(_All_Users_child_listener);
		
		_histry_of_task_child_listener = new ChildEventListener() {
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
		histry_of_task.addChildEventListener(_histry_of_task_child_listener);
		
		_r_request_listener = new RequestNetwork.RequestListener() {
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
				
			}
		};
		
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
		//new
		id2 = Settings.Secure.getString(
				getApplicationContext().getContentResolver(),
				Settings.Secure.ANDROID_ID
		);

//		linear4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)98, 0xFF246447));
//		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/montserrat_semibold.ttf"), Typeface.NORMAL);
//		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/montserrat_semibold.ttf"), Typeface.NORMAL);
//		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/montserrat_semibold.ttf"), Typeface.NORMAL);
		spk = "Welcome to Cash Luto app 💰. Click on login with google and chose account. then complete offer earn a money thanks";
		h.speak(spk, TextToSpeech.QUEUE_ADD, null);
		id= Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
		GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken("571718566664-t9babc17k8qq9fmosdsq85caa43ipl0m.apps.googleusercontent.com").requestEmail().build();
		glogin = GoogleSignIn.getClient(this, options);
		auth = FirebaseAuth.getInstance();
//		_rippleRoundStroke(linear4, "#FF246447", "#F5F5F5", 98, 0, "#FFFFFF");
//		linear5.setVisibility(View.GONE);
		rf_by = rrr.getString("rrr_by", "");
		textview4.setText("You inviting By his referral Code : ".concat(rf_by));
		if (!rf_by.equals("")) {
			linear5.setVisibility(View.VISIBLE);
		}
		else {
			
		}
		/*

if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
SketchwareUtil.showMessage(getApplicationContext(), "Welcome to offer wala ");
}
else {
FirebaseDynamicLinks.getInstance()
.getDynamicLink(getIntent())
.addOnSuccessListener(LoginGActivity.this, new OnSuccessListener<PendingDynamicLinkData>() {
	@Override
	public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
		// Get deep link from result (may be null if no link is found)
		Uri deepLink = null;
		if (pendingDynamicLinkData != null) {
			deepLink = pendingDynamicLinkData.getLink();
		}
		if(deepLink !=null){
			linear5.setVisibility(View.VISIBLE);
rf_by = deepLink.getQueryParameter("invite");
textview4.setText("You inviting By his referral Code : ".concat(rf_by));
SketchwareUtil.showMessage(getApplicationContext(), rf_by);
		}
	}
})
.addOnFailureListener(this, new OnFailureListener() {
	@Override
	public void onFailure(@NonNull Exception e) {
		SketchwareUtil.showMessage(getApplicationContext(), e.toString());
	}
});
}
 

*/
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_GLOGIN:
			if (_resultCode == Activity.RESULT_OK) {
				Task<GoogleSignInAccount> _task = GoogleSignIn.getSignedInAccountFromIntent(_data);
				
				try{
						GoogleSignInAccount account = _task.getResult(ApiException.class);


//					n = 0;
//					exist = 0;
//					for(int _repeat12 = 0; _repeat12 < (int)(map1.size()); _repeat12++) {
//						if (map1.get((int)n).get("device_id").toString().equals(id)) {
//							if (map1.get((int)n).get("device_id").toString().equals(id)) {
//								SketchwareUtil.showMessage(getApplicationContext(), "Your Device reagister ".concat(map1.get((int)n).get("email").toString()));
//								exist = 1;
//							}
//						}
//						n++;
//					}
//					if (exist == 0) {

						_firebaseAuthWithGoogle(account);
	/*

((EditText)edittext_name).setError("Please Enter Name");


*/
//					}





//						_firebaseAuthWithGoogle(account);









				}catch(Exception e){
						SketchwareUtil.showMessage(getApplicationContext(), e.getMessage());
				}
			}
			else {
				_loadingdialog(false, "");
				linear4.setVisibility(View.VISIBLE);
				p1.setVisibility(View.GONE);
				SketchwareUtil.showMessage(getApplicationContext(), "try again login");
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
		h.shutdown();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		t_wo = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_TGen();
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(t_wo, (int)(0), (int)(1000));
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
	
	
	public void _firebaseAuthWithGoogle(final GoogleSignInAccount _account) {
		AuthCredential credential = GoogleAuthProvider.getCredential(_account.getIdToken(), null);
		auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
			    @Override
			    public void onComplete(@NonNull Task<AuthResult> task) {
				        if (task.isSuccessful()) {
					            final FirebaseUser firebaseUser = auth.getCurrentUser(); // Declare final
					            final boolean isNewUser = task.getResult().getAdditionalUserInfo().isNewUser(); // Store task result in a final variable
					            
					            _loadingdialog(false, "Please Wait...");
					            
					            final DatabaseReference userRef = All_Users.child(firebaseUser.getUid()); // Declare final
					            
					            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
						                @Override
						                public void onDataChange(@NonNull DataSnapshot snapshot) {
							                    if (snapshot.exists()) {
								                        // User already exists
								                        SketchwareUtil.showMessage(getApplicationContext(), "Login Success");
								                        
								                        ii.setClass(getApplicationContext(), HomeActivity.class);
								                        startActivity(ii);
								                        finish();
								                    } else {
								                        // New user, add to database
								                        usersdata = new HashMap<>();
								                        usersdata.put("name", firebaseUser.getDisplayName());
								                        usersdata.put("pro_img", firebaseUser.getPhotoUrl() != null ? firebaseUser.getPhotoUrl().toString() : "");
								                        usersdata.put("email", firebaseUser.getEmail());
								                        usersdata.put("user_id", firebaseUser.getUid());
								                        usersdata.put("block", "false");
								                        usersdata.put("invalid_click", "0");
								                        usersdata.put("register_date", new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()));
								                        usersdata.put("balance", "1");
								                        usersdata.put("token", id2); //token put
								                        usersdata.put("device_id", id);
								                        
								                        // Add 'ref_by' only if it is not empty
								                        if (rf_by != null && !rf_by.trim().isEmpty()) {
									                            usersdata.put("ref_by", rf_by);
									                        }
								                        
								                        userRef.updateChildren(usersdata);
								                        
								                        SketchwareUtil.showMessage(getApplicationContext(), "Account Created Successfully");
								                        
								                        if (isNewUser) {
									                            ii.putExtra("new_user", "true");
									                        }
								                        
								                        ii.setClass(getApplicationContext(), HomeActivity.class);
								                        startActivity(ii);
								                        finish();
								                        
								                        // Now, add to the history_of_task after account creation
								                        Map<String, Object> mapt = new HashMap<>();
								                        String Koiskey = histry_of_task.push().getKey();
								                        
								                        mapt.put("comment", "Signup Bones");
								                        mapt.put("amount", "1");
								                        mapt.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(cal.getTime()));
								                        mapt.put("mobile", FirebaseAuth.getInstance().getCurrentUser().getUid());
								                        mapt.put("order_id", "xS1X1");
								                        mapt.put("status", "Complete");
								                        
								                        // Push the data to the history_of_task node
								                        histry_of_task.child(Koiskey).updateChildren(mapt);
								                        
								                        // Clear the map after updating
								                        mapt.clear();
								                        
								                        SketchwareUtil.showMessage(getApplicationContext(), "Received");
								                    }
							                }
						                
						                @Override
						                public void onCancelled(@NonNull DatabaseError error) {
							                    SketchwareUtil.showMessage(getApplicationContext(), "Error: " + error.getMessage());
							                }
						            });
					        } else {
					            SketchwareUtil.showMessage(getApplicationContext(), "Error: " + task.getException().getMessage());
					        }
				    }
		});
		
		/*
//temp code
AuthCredential credential = GoogleAuthProvider.getCredential(_account.getIdToken(), null); 
auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            final FirebaseUser firebaseUser = auth.getCurrentUser(); // Declare final
            final boolean isNewUser = task.getResult().getAdditionalUserInfo().isNewUser(); // Store task result in a final variable
            
            _loadingdialog(false, "Please Wait...");
            
            final DatabaseReference userRef = All_Users.child(firebaseUser.getUid()); // Declare final
            
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // User already exists
                        SketchwareUtil.showMessage(getApplicationContext(), "Login Success");
                        
                        ii.setClass(getApplicationContext(), HomeActivity.class);
                        startActivity(ii);
                        finish();
                    } else {
                        // New user, add to database
                        usersdata = new HashMap<>();
                        usersdata.put("name", firebaseUser.getDisplayName());
                        usersdata.put("pro_img", firebaseUser.getPhotoUrl() != null ? firebaseUser.getPhotoUrl().toString() : "");
                        usersdata.put("email", firebaseUser.getEmail());
                        usersdata.put("user_id", firebaseUser.getUid());
                        usersdata.put("block", "false");
                        usersdata.put("invalid_click", "0");
                        usersdata.put("register_date", new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()));
                        usersdata.put("balance", "1");
                        usersdata.put("device_id", id);
                        
                        userRef.updateChildren(usersdata);
                        
                        SketchwareUtil.showMessage(getApplicationContext(), "Account Created Successfully");
                        
                        if (isNewUser) {
                            ii.putExtra("new_user", "true");
                        }
                        
                        ii.setClass(getApplicationContext(), HomeActivity.class);
                        startActivity(ii);
                        finish();
                        
                        // Now, add to the history_of_task after account creation
                        Map<String, Object> mapt = new HashMap<>();
                        String Koiskey = histry_of_task.push().getKey();
                        
                        mapt.put("comment", "Signup Bones");
                        mapt.put("amount", "1");
                        mapt.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(cal.getTime()));
                        mapt.put("mobile", FirebaseAuth.getInstance().getCurrentUser().getUid());
                        mapt.put("order_id", "xS1X1");
                        mapt.put("status", "Complete");
                        
                        // Push the data to the history_of_task node
                        histry_of_task.child(Koiskey).updateChildren(mapt);
                        
                        // Clear the map after updating
                        mapt.clear();
                        
                        SketchwareUtil.showMessage(getApplicationContext(), "Received");
                    }
                }
                
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    SketchwareUtil.showMessage(getApplicationContext(), "Error: " + error.getMessage());
                }
            });
        } else {
            SketchwareUtil.showMessage(getApplicationContext(), "Error: " + task.getException().getMessage());
        }
    }
});

*/
		/*
//old code 
AuthCredential credential = GoogleAuthProvider.getCredential(_account.getIdToken(), null); 
auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            final FirebaseUser firebaseUser = auth.getCurrentUser(); // Declare final
            final boolean isNewUser = task.getResult().getAdditionalUserInfo().isNewUser(); // Store task result in a final variable
            
            _loadingdialog(false, "Please Wait...");

            final DatabaseReference userRef = All_Users.child(firebaseUser.getUid()); // Declare final

            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // User already exists
                        SketchwareUtil.showMessage(getApplicationContext(), "Login Success");

                        ii.setClass(getApplicationContext(), HomeActivity.class);
                        startActivity(ii);
                        finish();
                    } else {
                        // New user, add to database
                        usersdata = new HashMap<>();
                        usersdata.put("name", firebaseUser.getDisplayName());
                        usersdata.put("pro_img", firebaseUser.getPhotoUrl() != null ? firebaseUser.getPhotoUrl().toString() : "");
                        usersdata.put("email", firebaseUser.getEmail());
                        usersdata.put("user_id", firebaseUser.getUid());
                        usersdata.put("block", "false");
                        usersdata.put("invalid_click", "0");
                        usersdata.put("register_date", new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()));
                        usersdata.put("balance", "1");
                        usersdata.put("device_id", id);

                        userRef.updateChildren(usersdata);

                        SketchwareUtil.showMessage(getApplicationContext(), "Account Created Successfully");
                        

                        if (isNewUser) {
                            ii.putExtra("new_user", "true");
                        }

                        ii.setClass(getApplicationContext(), HomeActivity.class);
                        startActivity(ii);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    SketchwareUtil.showMessage(getApplicationContext(), "Error: " + error.getMessage());
                }
            });

        } else {
            SketchwareUtil.showMessage(getApplicationContext(), "Error: " + task.getException().getMessage());
        }
    }
});

*/
	}
	
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		GradientDrawable GG = new GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		RippleDrawable RE = new RippleDrawable(new ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	// Clipboard se text read karne ka function
	private String getClipboardText() {
		ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		if (clipboard.hasPrimaryClip() && clipboard.getPrimaryClip().getItemCount() > 0) {
			ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
			return item.getText().toString();
		}
		return "";
	}




public void _NewSignupHis() {
		mapt = new HashMap<>();
		Koiskey = histry_of_task.push().getKey();
		mapt.put("comment", "Signup Bones");
		mapt.put("amount", "1");
		mapt.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(koi.getTime()));
		mapt.put("mobile", FirebaseAuth.getInstance().getCurrentUser().getUid());
		mapt.put("order_id", "xS1X1");
		mapt.put("status", "Complete ");
		histry_of_task.child(Koiskey).updateChildren(mapt);
		mapt.clear();
		SketchwareUtil.showMessage(getApplicationContext(), "Recived ");
	}

	//new ref
	public void refgot() {
		ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
			// Get all items in clipboard
			ClipData clipData = clipboardManager.getPrimaryClip();

			// Loop through all items in the clipboard
			for (int i = 0; i < clipData.getItemCount(); i++) {
				CharSequence clipText = clipData.getItemAt(i).getText();

				// Check if the clipboard item contains the specific string
				if (clipText != null && clipText.toString().contains("AndroidHOST.aiofg")) {
					// Extract the part after the colon
					String fullText = clipText.toString();
					int colonIndex = fullText.indexOf(":");
					if (colonIndex != -1 && colonIndex + 1 < fullText.length()) {
						// Get the part after the colon
						String extractedText = fullText.substring(colonIndex + 1).trim();
						// Set the extracted text to EditText
						textview1.setText(extractedText);
						//manulpet string now
						rf_by = extractedText;

					}
					break;  // Exit loop once the matching clip is found
				}
			}
		}
//n
		if (!rf_by.equals("")) {
			linear5.setVisibility(View.VISIBLE);
		}
	}


	public void _TGen() {
		FirebaseMessaging.getInstance().getToken()
				.addOnCompleteListener(new OnCompleteListener<String>() {
					@Override
					public void onComplete(@NonNull Task<String> task) {
						if (task.isSuccessful()) {
							String fcmToken = task.getResult();

							// Update SharedPreferences
							tok.edit().putString("tok1", fcmToken).apply();

							t_wo.cancel();
						} else {
							SketchwareUtil.showMessage(getApplicationContext(), "Failed to generate token");
							t_wo.cancel();
						}
					}
				});
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
