//package com.cashluto.rewad;
//
//import android.app.Activity;
//import android.content.ClipData;
//import android.content.ClipboardManager;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.*;
//import android.graphics.drawable.*;
//import android.net.Uri;
//import android.os.*;
//import android.view.*;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import androidx.annotation.*;
//import androidx.fragment.app.Fragment;
//
//import com.airbnb.lottie.*;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.GenericTypeIndicator;
//import com.google.firebase.dynamiclinks.DynamicLink;
//import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
//import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
//import com.google.firebase.dynamiclinks.ShortDynamicLink;
//import com.google.firebase.installations.InstallationTokenResult;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.util.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.Executors;
//
//
//public class ShareFragmentOLDActivity extends Fragment {
//
//	private Timer _timer = new Timer();
//	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
//
//	private HashMap<String, Object> Refer_Code_uesr = new HashMap<>();
//	private HashMap<String, Object> v = new HashMap<>();
//	private String stpl = "off";
//	private String REFER_CODE_REAL = "";
//	private double no = 0;
//	private String Refer_Code_ = "";
//	private String Refer_Code = "";
//	private String share = "";
//	private String app_uri = "";
//	private HashMap<String, Object> lGNetw = new HashMap<>();
//	private HashMap<String, Object> resultShorter = new HashMap<>();
//	private String uuuuuuu = "";
//	private String txRef = "";
//	private String shortUrl = "";
//
//	private ArrayList<String> list = new ArrayList<>();
//
//	private LinearLayout linear2;
//	private LinearLayout linear4;
//	private LinearLayout linear5;
//	private ImageView imageview2;
//	private TextView textview2;
//	private TextView textview3;
//	private LinearLayout linear3;
//	private TextView textview5;
//	private LinearLayout linear6;
//	private LinearLayout linear10;
//	private LinearLayout linear22;
//	private LinearLayout linear17;
//	private LinearLayout linear7;
//	private ImageView imageview1;
//	private TextView textview1;
//	private TextView tx_ref;
//	private LinearLayout linear8;
//	private TextView link;
//	private ImageView imageview8;
//	private LinearLayout linear18;
//	private LinearLayout linear19;
//	private TextView textview4;
//	private ImageView imageview5;
//	private LinearLayout linear9;
//	private LinearLayout linear20;
//	private LinearLayout linear21;
//	private ImageView imageview6;
//	private LinearLayout linear11;
//	private ImageView imageview7;
//	private LinearLayout linear12;
//	private LottieAnimationView lottie2;
//	private TextView textview7;
//	private LottieAnimationView lottie3;
//	private TextView textview8;
//	private LottieAnimationView lottie4;
//	private TextView textview9;
//
//	private FirebaseAuth auth;
//	private OnCompleteListener<AuthResult> _auth_create_user_listener;
//	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
//	private OnCompleteListener<Void> _auth_reset_password_listener;
//	private OnCompleteListener<Void> auth_updateEmailListener;
//	private OnCompleteListener<Void> auth_updatePasswordListener;
//	private OnCompleteListener<Void> auth_emailVerificationSentListener;
//	private OnCompleteListener<Void> auth_deleteUserListener;
//	private OnCompleteListener<Void> auth_updateProfileListener;
//	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
//	private OnCompleteListener<AuthResult> auth_googleSignInListener;
//
//	private DatabaseReference rfrrr = _firebase.getReference("refer_codes");
//	private ChildEventListener _rfrrr_child_listener;
//	private DatabaseReference All_Users = _firebase.getReference("All_Users");
//	private ChildEventListener _All_Users_child_listener;
//	private TimerTask tryi;
//	private DatabaseReference code_oi = _firebase.getReference("code_oi");
//	private ChildEventListener _code_oi_child_listener;
//	private DynamicLink dynamic;
//	private OnSuccessListener dynamic_onSuccessLink;
//	private OnFailureListener dynamic_onFailureLink;
//
//	private OnCompleteListener cm_onCompleteListener;
//	private SharedPreferences ref_link;
//	private RequestNetwork urlShorter;
//	private RequestNetwork.RequestListener _urlShorter_request_listener;
//	private SharedPreferences mainU;
//	private TimerTask cG;
//	private TimerTask g;
//	private Intent tst = new Intent();
//	private SharedPreferences DynamicLinkPrefs;
//	private TimerTask r_c;
//	private TimerTask tixfggyyyyyyty;
//
//	@NonNull
//	@Override
//	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
//		View _view = _inflater.inflate(R.layout.share_fragment, _container, false);
//		initialize(_savedInstanceState, _view);
//		FirebaseApp.initializeApp(getContext());
//		initializeLogic();
//		return _view;
//	}
//
//	private void initialize(Bundle _savedInstanceState, View _view) {
//		linear2 = _view.findViewById(R.id.linear2);
//		linear4 = _view.findViewById(R.id.linear4);
//		linear5 = _view.findViewById(R.id.linear5);
//		imageview2 = _view.findViewById(R.id.imageview2);
//		textview2 = _view.findViewById(R.id.textview2);
//		textview3 = _view.findViewById(R.id.textview3);
//		linear3 = _view.findViewById(R.id.linear3);
//		textview5 = _view.findViewById(R.id.textview5);
//		linear6 = _view.findViewById(R.id.linear6);
//		linear10 = _view.findViewById(R.id.linear10);
//		linear22 = _view.findViewById(R.id.linear22);
//		linear17 = _view.findViewById(R.id.linear17);
//		linear7 = _view.findViewById(R.id.linear7);
//		imageview1 = _view.findViewById(R.id.imageview1);
//		textview1 = _view.findViewById(R.id.textview1);
//		tx_ref = _view.findViewById(R.id.tx_ref);
//		linear8 = _view.findViewById(R.id.linear8);
//		link = _view.findViewById(R.id.link);
//		imageview8 = _view.findViewById(R.id.imageview8);
//		linear18 = _view.findViewById(R.id.linear18);
//		linear19 = _view.findViewById(R.id.linear19);
//		textview4 = _view.findViewById(R.id.textview4);
//		imageview5 = _view.findViewById(R.id.imageview5);
//		linear9 = _view.findViewById(R.id.linear9);
//		linear20 = _view.findViewById(R.id.linear20);
//		linear21 = _view.findViewById(R.id.linear21);
//		imageview6 = _view.findViewById(R.id.imageview6);
//		linear11 = _view.findViewById(R.id.linear11);
//		imageview7 = _view.findViewById(R.id.imageview7);
//		linear12 = _view.findViewById(R.id.linear12);
//		lottie2 = _view.findViewById(R.id.lottie2);
//		textview7 = _view.findViewById(R.id.textview7);
//		lottie3 = _view.findViewById(R.id.lottie3);
//		textview8 = _view.findViewById(R.id.textview8);
//		lottie4 = _view.findViewById(R.id.lottie4);
//		textview9 = _view.findViewById(R.id.textview9);
//		auth = FirebaseAuth.getInstance();
//		ref_link = getContext().getSharedPreferences("ref_link", Activity.MODE_PRIVATE);
//		urlShorter = new RequestNetwork((Activity) getContext());
//		mainU = getContext().getSharedPreferences("mainU", Activity.MODE_PRIVATE);
//		DynamicLinkPrefs = getContext().getSharedPreferences("DynamicLinkPrefs", Activity.MODE_PRIVATE);
//
//
//		link.setText("https://digitalareagrow.xyz/cashluto/source.php?ref=".concat(tx_ref.getText().toString()).trim());
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//		imageview2.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View _view) {
//				_ReferLink();
//			}
//		});
//
//		textview5.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View _view) {
//				SketchwareUtil.showMessage(getContext().getApplicationContext(), "referral code has been copyied");
//				((ClipboardManager) getContext().getSystemService(getContext().getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", tx_ref.getText().toString()));
//			}
//		});
//
//		imageview8.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View _view) {
//				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Your refer Link has been copied to clipboard !");
//				((ClipboardManager) getContext().getSystemService(getContext().getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", link.getText().toString()));
//			}
//		});
//
//		linear18.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View _view) {
//				share = "hi dost !!".concat(" Use my referral link & code Earn Money lot by completing a simple offer wnd withdrawal in direct upi !!\n\nhere the my refferal Link :".concat(tx_ref.getText().toString().concat("\n\nAnd Get Free ₹. Here Is Download Link \n".concat(app_uri.concat(tx_ref.getText().toString())))));
//				Intent i = new Intent(Intent.ACTION_SEND);i.setType("text/plain"); i.putExtra(Intent.EXTRA_TEXT, share); startActivity(Intent.createChooser(i,"Share using"));
//			}
//		});
//
//		linear19.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View _view) {
//				_watsapp_share("User !!".concat(" has Invited You To Download cash luto Earning App From Google Play Store.\n\n\nUse My Refer Code - ".concat(tx_ref.getText().toString().concat("\n\nAnd Get Free money ₹₹. Here Is Download Link \n".concat(app_uri.concat(tx_ref.getText().toString()))))), "");
//			}
//		});
//
//		_rfrrr_child_listener = new ChildEventListener() {
//			@Override
//			public void onChildAdded(DataSnapshot _param1, String _param2) {
//				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
//				final String _childKey = _param1.getKey();
//				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
//				if (_childKey.equals("Refer_Codes")) {
//					SketchwareUtil.getAllKeysFromMap(_childValue, list);
//				}
//				else {
//
//				}
//			}
//
//			@Override
//			public void onChildChanged(DataSnapshot _param1, String _param2) {
//				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
//				final String _childKey = _param1.getKey();
//				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
//				if (_childKey.equals("Refer_Codes")) {
//					SketchwareUtil.getAllKeysFromMap(_childValue, list);
//				}
//				else {
//
//				}
//			}
//
//			@Override
//			public void onChildMoved(DataSnapshot _param1, String _param2) {
//
//			}
//
//			@Override
//			public void onChildRemoved(DataSnapshot _param1) {
//				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
//				final String _childKey = _param1.getKey();
//				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
//
//			}
//
//			@Override
//			public void onCancelled(DatabaseError _param1) {
//				final int _errorCode = _param1.getCode();
//				final String _errorMessage = _param1.getMessage();
//
//			}
//		};
//		rfrrr.addChildEventListener(_rfrrr_child_listener);
//
//		_All_Users_child_listener = new ChildEventListener() {
//			@Override
//			public void onChildAdded(DataSnapshot _param1, String _param2) {
//				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
//				final String _childKey = _param1.getKey();
//				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
//				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
//						if (_childValue.containsKey("Refer_Code")) {
//								REFER_CODE_REAL = _childValue.get("Refer_Code").toString();
//								tx_ref.setText(_childValue.get("Refer_Code").toString());
//						}
//				}
//				else {
//
//				}
//			}
//
//			@Override
//			public void onChildChanged(DataSnapshot _param1, String _param2) {
//				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
//				final String _childKey = _param1.getKey();
//				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
//				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
//					if (_childValue.containsKey("Refer_Code")) {
//						REFER_CODE_REAL = _childValue.get("Refer_Code").toString();
//						tx_ref.setText(_childValue.get("Refer_Code").toString());
//						_ReferLink();
//					}
//				}
//				else {
//
//				}
//			}
//
//			@Override
//			public void onChildMoved(DataSnapshot _param1, String _param2) {
//
//			}
//
//			@Override
//			public void onChildRemoved(DataSnapshot _param1) {
//				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
//				final String _childKey = _param1.getKey();
//				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
//
//			}
//
//			@Override
//			public void onCancelled(DatabaseError _param1) {
//				final int _errorCode = _param1.getCode();
//				final String _errorMessage = _param1.getMessage();
//
//			}
//		};
//		All_Users.addChildEventListener(_All_Users_child_listener);
//
//		_code_oi_child_listener = new ChildEventListener() {
//			@Override
//			public void onChildAdded(DataSnapshot _param1, String _param2) {
//				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
//				final String _childKey = _param1.getKey();
//				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
//				if (_childKey.equals("109")) {
//					if (_childValue.containsKey("apk_link")) {
//						app_uri = _childValue.get("apk_link").toString();
//					}
//					if (_childValue.containsKey("play")) {
//						stpl = _childValue.get("play").toString();
//						//lister java
//						if (stpl.equals("on")) {
//							_ReferLink();
//						}
//						else {
//							link.setText("https://digitalareagrow.xyz/cashluto/source.php?ref=".concat(tx_ref.getText().toString()).trim());
//						}
//					}
//				}
//				else {
//
//				}
//			}
//
//			@Override
//			public void onChildChanged(DataSnapshot _param1, String _param2) {
//				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
//				final String _childKey = _param1.getKey();
//				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
//				if (_childKey.equals("109")) {
//						if (_childValue.containsKey("apk_link")) {
//								app_uri = _childValue.get("apk_link").toString();
//						}
//				}
//				else {
//
//				}
//			}
//
//			@Override
//			public void onChildMoved(DataSnapshot _param1, String _param2) {
//
//			}
//
//			@Override
//			public void onChildRemoved(DataSnapshot _param1) {
//				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
//				final String _childKey = _param1.getKey();
//				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
//
//			}
//
//			@Override
//			public void onCancelled(DatabaseError _param1) {
//				final int _errorCode = _param1.getCode();
//				final String _errorMessage = _param1.getMessage();
//
//			}
//		};
//		code_oi.addChildEventListener(_code_oi_child_listener);
//
//		dynamic_onSuccessLink = new OnSuccessListener<PendingDynamicLinkData>() {
//			@Override
//			public void onSuccess(PendingDynamicLinkData _pendingDynamicLinkData) {
//				final String _link = _pendingDynamicLinkData != null ? _pendingDynamicLinkData.getLink().toString() : "";
//
//			}
//		};
//
//		dynamic_onFailureLink = new OnFailureListener() {
//			@Override
//			public void onFailure(Exception _e) {
//				final String _errorMessage = _e.getMessage();
//
//			}
//		};
//
//		cm_onCompleteListener = new OnCompleteListener<InstallationTokenResult>() {
//			@Override
//			public void onComplete(Task<InstallationTokenResult> task) {
//				final boolean _success = task.isSuccessful();
//				final String _token = _success ? task.getResult().getToken() : "";
//				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
//
//			}
//		};
//
//		_urlShorter_request_listener = new RequestNetwork.RequestListener() {
//			@Override
//			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
//				final String _tag = _param1;
//				final String _response = _param2;
//				final HashMap<String, Object> _responseHeaders = _param3;
//				if (ref_link.getString("link", "").equals("")) {
//					SketchwareUtil.showMessage(getContext().getApplicationContext(), "rigt");
//				}
//				else {
//					if (_response.contains("Short URL created successfully")) {
//						resultShorter = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
//						ref_link.edit().putString("link", "https://cashluto.kesug.com/redirect.php?url=".concat(resultShorter.get("short_url").toString()).trim()).commit();
//					}
//					else {
//
//					}
//				}
//			}
//
//			@Override
//			public void onErrorResponse(String _param1, String _param2) {
//				final String _tag = _param1;
//				final String _message = _param2;
//				if (ref_link.getString("link", "").equals("")) {
//					SketchwareUtil.showMessage(getContext().getApplicationContext(), _message);
//				}
//				else {
//
//				}
//			}
//		};
//
//		auth_updateEmailListener = new OnCompleteListener<Void>() {
//			@Override
//			public void onComplete(Task<Void> _param1) {
//				final boolean _success = _param1.isSuccessful();
//				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
//
//			}
//		};
//
//		auth_updatePasswordListener = new OnCompleteListener<Void>() {
//			@Override
//			public void onComplete(Task<Void> _param1) {
//				final boolean _success = _param1.isSuccessful();
//				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
//
//			}
//		};
//
//		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
//			@Override
//			public void onComplete(Task<Void> _param1) {
//				final boolean _success = _param1.isSuccessful();
//				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
//
//			}
//		};
//
//		auth_deleteUserListener = new OnCompleteListener<Void>() {
//			@Override
//			public void onComplete(Task<Void> _param1) {
//				final boolean _success = _param1.isSuccessful();
//				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
//
//			}
//		};
//
//		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
//			@Override
//			public void onComplete(Task<AuthResult> task) {
//				final boolean _success = task.isSuccessful();
//				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
//
//			}
//		};
//
//		auth_updateProfileListener = new OnCompleteListener<Void>() {
//			@Override
//			public void onComplete(Task<Void> _param1) {
//				final boolean _success = _param1.isSuccessful();
//				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
//
//			}
//		};
//
//		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
//			@Override
//			public void onComplete(Task<AuthResult> task) {
//				final boolean _success = task.isSuccessful();
//				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
//
//			}
//		};
//
//		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
//			@Override
//			public void onComplete(Task<AuthResult> _param1) {
//				final boolean _success = _param1.isSuccessful();
//				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
//
//			}
//		};
//
//		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
//			@Override
//			public void onComplete(Task<AuthResult> _param1) {
//				final boolean _success = _param1.isSuccessful();
//				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
//
//			}
//		};
//
//		_auth_reset_password_listener = new OnCompleteListener<Void>() {
//			@Override
//			public void onComplete(Task<Void> _param1) {
//				final boolean _success = _param1.isSuccessful();
//
//			}
//		};
//	}
//
//	private void initializeLogic() {
//		linear3.setElevation((float)15);
//		linear3.requestFocus();
//		linear3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)3, 0xFFF5F5F5));
//		imageview8.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
//		/*
//
//g = new TimerTask() {
//@Override
//public void run() {
//getActivity().runOnUiThread(new Runnable() {
//@Override
//public void run() {
//if (tx_ref.getText().toString().equals("")) {
//
//}
//else {
//_ReferLink();
//g.cancel();
//}
//}
//});
//}
//};
//_timer.scheduleAtFixedRate(g, (int)(0), (int)(4500));
//tixfggyyyyyyty = new TimerTask() {
//@Override
//public void run() {
//getActivity().runOnUiThread(new Runnable() {
//@Override
//public void run() {
//if (link.getText().toString().equals("loading........")) {
//_loadingdialog(true, "");
//}
//else {
//_loadingdialog(false, "");
//tixfggyyyyyyty.cancel();
//}
//}
//});
//}
//};
//_timer.scheduleAtFixedRate(tixfggyyyyyyty, (int)(0), (int)(1000));
//
//
//*/
//	}
//
//
//	@Override
//	public void onStart() {
//		super.onStart();
//
//		if (stpl.equals("on")) {
//			_ReferLink();
//		}
//		else {
//			link.setText("https://digitalareagrow.xyz/cashluto/source.php?ref=".concat(tx_ref.getText().toString()).trim());
//		}
//
//
//
//
//
//
//	}
//
//	@Override
//	public void onStop() {
//		super.onStop();
//		/* cG.cancel();
//		r_c.cancel(); */
//	}
//
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		/*cG.cancel();*/
//	}
//	public String _generateRandomString() {
//		Random random = new Random();
//
//		// 4 random alphabets (small or capital)
//		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		StringBuilder randomAlphabet = new StringBuilder();
//		for (int i = 0; i < 4; i++) {
//			    randomAlphabet.append(alphabet.charAt(random.nextInt(alphabet.length())));
//		}
//
//		// 5 random digits
//		StringBuilder randomDigits = new StringBuilder();
//		for (int i = 0; i < 5; i++) {
//			    randomDigits.append(random.nextInt(10));
//		}
//
//		// 2 random letters between F and K
//		String lettersFK = "FGHIJK";
//		StringBuilder randomLettersFK = new StringBuilder();
//		for (int i = 0; i < 2; i++) {
//			    randomLettersFK.append(lettersFK.charAt(random.nextInt(lettersFK.length())));
//		}
//
//		// Combine the result without spaces
//		return randomAlphabet.toString() + randomDigits.toString() + randomLettersFK.toString();
//
//	}
//
//
//	public void _refer_code() {
//		Refer_Code_ = _generateRandomString();
//		if (list.contains(Refer_Code_)) {
//			_refer_code();
//		}
//		else {
//			Refer_Code_uesr = new HashMap<>();
//			Refer_Code_uesr.put("Refer_Code", Refer_Code_);
//			All_Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(Refer_Code_uesr);
//			v = new HashMap<>();
//			v.put(Refer_Code_, FirebaseAuth.getInstance().getCurrentUser().getUid());
//			rfrrr.child("Refer_Codes").updateChildren(v);
//		}
//		REFER_CODE_REAL = "";
//	}
//
//
//	public void _loadingdialog(final boolean _ifShow, final String _Title) {
//
//	}
//
//
//	public void _watsapp_share(final String _text, final String _file) {
//		if (FileUtil.isFile(_file)) {
//			if (_text.length() > 0) {
//				Intent whatsappIntent = new Intent(Intent.ACTION_SEND); whatsappIntent.setType("text/plain"); whatsappIntent.setPackage("com.whatsapp"); whatsappIntent.putExtra(Intent.EXTRA_TEXT, _text);
//				whatsappIntent.putExtra (Intent.EXTRA_STREAM, Uri.parse(_file));
//				 try {
//					startActivity(Intent.createChooser( whatsappIntent, "Share"));
//					 } catch (android.content.ActivityNotFoundException ex) { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp"))); }
//			}
//			else {
//				Intent whatsappIntent = new Intent(Intent.ACTION_SEND); whatsappIntent.setType("text/plain"); whatsappIntent.setPackage("com.whatsapp");
//				whatsappIntent.putExtra (Intent.EXTRA_STREAM, Uri.parse(_file));
//				 try {
//					startActivity(Intent.createChooser( whatsappIntent, "Share"));
//					 } catch (android.content.ActivityNotFoundException ex) { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp"))); }
//			}
//		}
//		else {
//			if (_text.length() > 0) {
//				Intent whatsappIntent = new Intent(Intent.ACTION_SEND); whatsappIntent.setType("text/plain"); whatsappIntent.setPackage("com.whatsapp"); whatsappIntent.putExtra(Intent.EXTRA_TEXT, _text);
//				 try {
//					startActivity(Intent.createChooser( whatsappIntent, "Share"));
//					 } catch (android.content.ActivityNotFoundException ex) { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp"))); }
//			}
//			else {
//
//			}
//			if (_file.length() > 0) {
//				SketchwareUtil.showMessage(getContext().getApplicationContext(), "file path dosent exist");
//			}
//			else {
//
//			}
//		}
//	}
//
////	 https://rewads.page.link     https://cashrewads.page.link
//	public void _ReferLink() {
//		DynamicLink dynamic = FirebaseDynamicLinks.getInstance().createDynamicLink()
//		    .setLink(Uri.parse("https://digitalareagrow.xyz/?invite=".concat(tx_ref.getText().toString())))
//		    .setDomainUriPrefix("https://cashrewads.page.link/")
//		    .setAndroidParameters(new DynamicLink.AndroidParameters.Builder("com.cashluto.rewad").build())
//		    .setSocialMetaTagParameters(new DynamicLink.SocialMetaTagParameters.Builder()
//		        .setTitle("Indias best offer & Earning App")
//		        .setDescription("Download this earning app, earn a lot of money. Download the app now!!")
//		        .setImageUrl(Uri.parse("https://i.ibb.co/6ZD1KZN/154ed464-4197-43e4-841f-329ed562cc98.webp"))
//		        .build())
//		    .buildDynamicLink();
//
//		uuuuuuu = dynamic.getUri().toString();
//		mainU.edit().putString("main_url", uuuuuuu).commit();
//
//		// In bottomshort URL
//		cG = new TimerTask() {
//			    @Override
//			    public void run() {
//				        getActivity().runOnUiThread(new Runnable() {
//					            @Override
//					            public void run() {
//						                if (ref_link.getString("link", "").equals("")) {
//							                    // No link available yet
//							                } else {
//							                    link.setText(ref_link.getString("link", ""));
//							                    app_uri = ref_link.getString("link", "");
//							                    cG.cancel();
//							                }
//						            }
//					        });
//				    }
//		};
//		_timer.scheduleAtFixedRate(cG, 0, 2500);
//
//		r_c = new TimerTask() {
//			    @Override
//			    public void run() {
//				        getActivity().runOnUiThread(new Runnable() {
//					            @Override
//					            public void run() {
//						                if (ref_link.getString("link", "").equals("")) {
//							                    Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
//							                        .setLongLink(Uri.parse(mainU.getString("main_url", "")))
//							                        .buildShortDynamicLink()
//							                        .addOnCompleteListener(Executors.newSingleThreadExecutor(), new OnCompleteListener<ShortDynamicLink>() {
//								                            @Override
//								                            public void onComplete(@NonNull Task<ShortDynamicLink> task) {
//									                                if (task.isSuccessful()) {
//										                                    ref_link.edit().putString("link", task.getResult().getShortLink().toString()).commit();
//										                                } else {
//										                                    r_c.cancel();
//										                                }
//									                            }
//								                        });
//							                } else {
//							                    r_c.cancel();
//							                }
//						            }
//					        });
//				    }
//		};
//		_timer.scheduleAtFixedRate(r_c, 1000, 2000);
//
//		/*
//
//DynamicLink dynamic = FirebaseDynamicLinks.getInstance().createDynamicLink()
//.setLink(Uri.parse("https://cashluto.kesug.com/?invite=".concat(tx_ref.getText().toString())))
//.setDomainUriPrefix("https://cashluto.page.link/")
//.setAndroidParameters( new DynamicLink.AndroidParameters.Builder("com.cashluto.rewads").build())
//.setSocialMetaTagParameters( new DynamicLink.SocialMetaTagParameters.Builder()
//.setTitle("Indias best offer & Earning App")
//.setDescription("Dowenloade this earning app earn a lot of money dowenloade the app Now !!")
//.setImageUrl(Uri.parse("https://i.ibb.co/6ZD1KZN/154ed464-4197-43e4-841f-329ed562cc98.webp"))
//.build())
//.buildDynamicLink();
//uuuuuuu = dynamic.getUri().toString();
//mainU.edit().putString("main_url", uuuuuuu).commit();
//SketchwareUtil.showMessage(getContext().getApplicationContext(), uuuuuuu);
////in bottomshor url
//cG = new TimerTask() {
//@Override
//public void run() {
//getActivity().runOnUiThread(new Runnable() {
//@Override
//public void run() {
//if (ref_link.getString("link", "").equals("")) {
//
//}
//else {
//link.setText(ref_link.getString("link", ""));
//app_uri = ref_link.getString("link", "");
//cG.cancel();
//}
//}
//});
//}
//};
//_timer.scheduleAtFixedRate(cG, (int)(0), (int)(2500));
//r_c = new TimerTask() {
//@Override
//public void run() {
//getActivity().runOnUiThread(new Runnable() {
//@Override
//public void run() {
//if (ref_link.getString("link", "").equals("")) {
//Task < ShortDynamicLink > shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
//.setLongLink(Uri.parse(mainU.getString("main_url", "")))
//.buildShortDynamicLink()
//.addOnCompleteListener(ShareFragmentOLDActivity.this, new OnCompleteListener < ShortDynamicLink > () {
//	@Override
//	public void onComplete(@NonNull Task < ShortDynamicLink > task) {
//		// don't remove cloud massage component cm
//		if (task.isSuccessful()) {
//			ref_link.edit().putString("link", task.getResult().getShortLink().toString()).commit();
//		} else {
//			r_c.cancel();
//		}
//	}
//});
//
//}
//else {
//r_c.cancel();
//}
//}
//});
//}
//};
//_timer.scheduleAtFixedRate(r_c, (int)(1000), (int)(2000));
//
//
//*/
//	}
//
//
//	public void _LinkPuter() {
//		/*
//
//// Assuming tx_ref is an EditText field where the user enters the transaction reference
//EditText tx_ref = findViewById(R.id.tx_ref_input);  // Example EditText reference, replace with actual ID
//
//// Example tx_ref (this could come from an EditText or other input)
//String txRef = tx_ref.getText().toString();
//
//// Create and store the dynamic link asynchronously
//DynamicLinkHelper.createAndStoreDynamicLink(this, txRef, new DynamicLinkHelper.DynamicLinkCallback() {
//    @Override
//    public void onDynamicLinkGenerated(String shortUrl) {
//        // Check if the short URL was generated successfully
//        if (shortUrl != null) {
//            // Short URL is available
//            Toast.makeText(ShareFragmentOLDActivity.this, "Short URL: " + shortUrl, Toast.LENGTH_LONG).show();
//
//            // Set the generated short URL to a TextView or any other component
//            link.setText(shortUrl);  // Replace 'link' with your actual TextView reference
//        } else {
//            // Short URL not available (may not be generated yet)
//            Toast.makeText(ShareFragmentOLDActivity.this, "Short URL not generated yet", Toast.LENGTH_LONG).show();
//        }
//    }
//});
//
//// Example tx_ref (this could come from an EditText or other input)
//txRef = tx_ref.getText().toString();
//
//// Create and store the dynamic link
//DynamicLinkHelper.createAndStoreDynamicLink(this, txRef);
//
//// Get the short URL (it will be retrieved from SharedPreferences)
//String shortUrl = DynamicLinkHelper.getShortUrl(this);
//
//// Handle the case where the short URL might not be available yet
//if (shortUrl != null) {
//    // Short URL is available
//    Toast.makeText(this, "Short URL: " + shortUrl, Toast.LENGTH_LONG).show();
//
//    link.setText(shortUrl);
//
//} else {
//    // Short URL not available (may not be generated yet)
//    Toast.makeText(this, "Short URL not generated yet", Toast.LENGTH_LONG).show();
//}
//
//
//
//*/
//	}
//
//}
