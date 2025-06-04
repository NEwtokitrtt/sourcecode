package com.cashluto.rewad;

import android.app.*;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.*;
import android.provider.Settings;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.onesignal.OneSignal;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {

	private String externalId;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> Refer_Code_uesr = new HashMap<>();
	private HashMap<String, Object> v = new HashMap<>();
	private String Refer_Code_ = "";
	private String REFER_CODE_REAL = "";
	private String img_c = "";
	private String id2 = "";
	
	private ArrayList<String> list = new ArrayList<>();
	
	private LinearLayout base;
	private ProgressBar progressbar1;
	private TextView textview1;
	private LinearLayout page_loader;
	private BottomNavigationView bottomnavigation1;
	
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
	
	private DatabaseReference refer_codes = _firebase.getReference("refer_codes");
	private ChildEventListener _refer_codes_child_listener;
	private DatabaseReference All_Users = _firebase.getReference("All_Users");
	private ChildEventListener _All_Users_child_listener;
	private TimerTask f;
	private RequestNetwork network;
	private RequestNetwork.RequestListener _network_request_listener;
	private Intent nt = new Intent();
	private SharedPreferences store_rr;
	private SharedPreferences imgo;
	private TimerTask piopu;
	private SharedPreferences mainU;
	private SharedPreferences ref_link;
	
	private OnCompleteListener cm_onCompleteListener;
	private TimerTask r_c;
	private DynamicLink dynamic;
	private OnSuccessListener dynamic_onSuccessLink;
	private OnFailureListener dynamic_onFailureLink;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		externalId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
		OneSignal.login(externalId);
		OneSignal.getUser();
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
		//str
		if (store_rr.getString("rf","").equals("")){
			_refer_code();

		}

	}
	
	private void initialize(Bundle _savedInstanceState) {
		base = findViewById(R.id.base);
		progressbar1 = findViewById(R.id.progressbar1);
		textview1 = findViewById(R.id.textview1);
		page_loader = findViewById(R.id.page_loader);
		bottomnavigation1 = findViewById(R.id.bottomnavigation1);
		auth = FirebaseAuth.getInstance();
		network = new RequestNetwork(this);
		store_rr = getSharedPreferences("store_rr", Activity.MODE_PRIVATE);
		imgo = getSharedPreferences("imgo", Activity.MODE_PRIVATE);
		mainU = getSharedPreferences("mainU", Activity.MODE_PRIVATE);
		ref_link = getSharedPreferences("ref_link", Activity.MODE_PRIVATE);
		//device id get


		//st gn
//		_loadingdialog(true, "Loading..🔐");
//		f = new TimerTask() {
//			@Override
//			public void run() {
//				runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						if (REFER_CODE_REAL.equals("")) {
//							if (store_rr.getString("rf", "").equals("")) {
//								_refer_code();
//							}
//							else {
//								f.cancel();
//								_loadingdialog(false, "");
//							}
//						}
//						else {
//							f.cancel();
//							_loadingdialog(false, "");
//						}
//					}
//				});
//			}
//		};
//		_timer.scheduleAtFixedRate(f, (int)(0), (int)(1000));


		//end gn

//		OneSignal.login(id2);
		
		bottomnavigation1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(MenuItem item) {
				final int _itemId = item.getItemId();
				if (_itemId == 0) {
					Fragment fragmenth = new MainHomeFragmentActivity();
					getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.page_loader, fragmenth)
					.commit();
				}

if (_itemId == 9) {
Fragment fragmentha = new Earn_FragmentActivity();
getSupportFragmentManager()
.beginTransaction()
.replace(R.id.page_loader, fragmentha)
.commit();
}

				if (_itemId == 1) {
					Fragment fragmenthb = new WalletFragmentActivity();
					getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.page_loader, fragmenthb)
					.commit();
				}
				if (_itemId == 2) {
					_loadingdialog(true, "Loading..🔐");
					f = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									if (REFER_CODE_REAL.equals("")) {
										if (store_rr.getString("rf", "").equals("")) {
											_refer_code();
										}
										else {
											f.cancel();
											_loadingdialog(false, "");
										}
									}
									else {
										f.cancel();
										_loadingdialog(false, "");
										Fragment fragmenthc = new ShareFragmentActivity();
										getSupportFragmentManager()
										.beginTransaction()
										.replace(R.id.page_loader, fragmenthc)
										.commit();
									}
								}
							});
						}
					};
					_timer.scheduleAtFixedRate(f, (int)(0), (int)(1000));
				}
				if (_itemId == 3) {
					Fragment fragmenthd = new ProfileFragmentActivity();
					getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.page_loader, fragmenthd)
					.commit();
				}
				return true;
			}
		});
		
		_refer_codes_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("Refer_Codes")) {
					SketchwareUtil.getAllKeysFromMap(_childValue, list);
				}
				else {
					
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("Refer_Codes")) {
					SketchwareUtil.getAllKeysFromMap(_childValue, list);
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
		refer_codes.addChildEventListener(_refer_codes_child_listener);
		
		_All_Users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("Refer_Code")) {
						REFER_CODE_REAL = _childValue.get("Refer_Code").toString();
					}
					if (_childValue.containsKey("pro_img")) {
						img_c = _childValue.get("pro_img").toString();
						if (imgo.getString("path", "").equals("")) {
							imgo.edit().putString("path", _childValue.get("pro_img").toString()).commit();
						}
						else {
							
						}
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
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (_childValue.containsKey("Refer_Code")) {
								REFER_CODE_REAL = _childValue.get("Refer_Code").toString();
						}
						if (_childValue.containsKey("pro_img")) {
								img_c = _childValue.get("pro_img").toString();
								if (imgo.getString("path", "").equals("")) {
										imgo.edit().putString("path", _childValue.get("pro_img").toString()).commit();
								}
								else {
										
								}
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
				nt.setClass(getApplicationContext(), NetworkActivity.class);
				nt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nt);
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
		_networks();
		bottomnavigation1.getMenu().add(0, 0, 0, "Home").setIcon(R.drawable.home_1);
//		bottomnavigation1.getMenu().add(0, 9, 0, "Earn ").setIcon(R.drawable.home_2);
		bottomnavigation1.getMenu().add(0, 1, 0, "Wallet").setIcon(R.drawable.home_3);
		bottomnavigation1.getMenu().add(0, 2, 0, "Refer").setIcon(R.drawable.home_4);
		bottomnavigation1.getMenu().add(0, 3, 0, "Profile ").setIcon(R.drawable.home_5);

		Fragment fragmenthX = new MainHomeFragmentActivity();
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.page_loader, fragmenthX)
		.commit();
		base.setVisibility(View.GONE);
		f = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Fragment fragmenthX1 = new WalletFragmentActivity();
						getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.page_loader, fragmenthX1)
						.commit();
						piopu = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										base.setVisibility(View.VISIBLE);
										Fragment fragmenthX = new MainHomeFragmentActivity();
										getSupportFragmentManager()
										.beginTransaction()
										.replace(R.id.page_loader, fragmenthX)
										.commit();
//										_Next();
									}
								});
							}
						};
						_timer.schedule(piopu, (int)(500));
					}
				});
			}
		};
		_timer.schedule(f, (int)(1000));
	}
	
	public String _generateRandomString() {
		Random random = new Random();
		
		// 4 random alphabets (small or capital)
		String alphabet = "1234567890";
		StringBuilder randomAlphabet = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			    randomAlphabet.append(alphabet.charAt(random.nextInt(alphabet.length())));
		}
		
		// 5 random digits
		StringBuilder randomDigits = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			    randomDigits.append(random.nextInt(10));
		}
		
		// 2 random letters between F and K
		String lettersFK = "456789";
		StringBuilder randomLettersFK = new StringBuilder();
		for (int i = 0; i < 2; i++) {
			    randomLettersFK.append(lettersFK.charAt(random.nextInt(lettersFK.length())));
		}
		
		// Combine the result without spaces
		return randomAlphabet.toString() + randomDigits.toString() + randomLettersFK.toString();
		
	}


	//start
	@Override
	protected void onPostCreate(Bundle _savedInstanceState) {
		super.onPostCreate(_savedInstanceState);
		//s
		OneSignal.login(externalId);

	}

	@Override
	public void onStart() {
		super.onStart();
		OneSignal.login(externalId);

	}

	@Override
	public void onPause() {
		super.onPause();
		OneSignal.login(externalId);

	}

	@Override
	public void onResume() {
		super.onResume();

	}
//lock


	public void _refer_code() {
		Refer_Code_ = _generateRandomString();
		if (list.contains(Refer_Code_)) {
			_refer_code();
		}
		else {
			Refer_Code_uesr = new HashMap<>();
			Refer_Code_uesr.put("Refer_Code", Refer_Code_);
			store_rr.edit().putString("rf", Refer_Code_).commit();
			All_Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(Refer_Code_uesr);
			v = new HashMap<>();
			v.put(Refer_Code_, FirebaseAuth.getInstance().getCurrentUser().getUid());
			refer_codes.child("Refer_Codes").updateChildren(v);
		}
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
	
	
	public void _networks() {
		network.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "Sketch Store Yt", _network_request_listener);
		//sr

	}

	public void _Next() {

		r_c = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						//a
						Fragment fragmenthP = new WalletFragmentActivity();
						getSupportFragmentManager()
								.beginTransaction()
								.replace(R.id.page_loader, fragmenthP)
								.commit();
						f = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										//b
										Fragment fragmenthJ = new MainHomeFragmentActivity();
										getSupportFragmentManager()
												.beginTransaction()
												.replace(R.id.page_loader, fragmenthJ)
												.commit();
									}
								});
							}
						};
						_timer.schedule(f, (int)(500));
					}
				});
			}
		};
		_timer.schedule(r_c, (int)(1000));




		Fragment fragmentQ = new Earn_FragmentActivity();
		getSupportFragmentManager()
				.beginTransaction()
			.replace(R.id.page_loader,fragmentQ)
				.commit();

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
