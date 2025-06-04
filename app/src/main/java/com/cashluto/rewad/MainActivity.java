package com.cashluto.rewad;

import android.animation.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.*;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.animation.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.installations.InstallationTokenResult;
import com.shobhitpuri.custombuttons.*;
import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchProviderException;
import android.content.ClipboardManager;



public class MainActivity extends AppCompatActivity {

	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

	private String app_version = "";
	private String y_version = "";
	private String p_name = "";
	private String serverKey = "";
	private String sega = "";
	private boolean s5 = false;
	private boolean s1 = false;
	private boolean s2 = false;
	private boolean s3 = false;
	private boolean isRooted = false;
	private boolean s4 = false;
	private boolean isEnabled = false;
	private boolean isSubscribed = false;
	private String timeSettings = "";
	private double nk = 0;
	private String tryu = "";

	private LinearLayout linear1;
	private LinearLayout linear_last;
	private LinearLayout linear83;
	private LinearLayout linear84;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear6;
	private ProgressBar progressbar1;
	private ScrollView vscroll1;
	private LinearLayout linear5;
	private ImageView imageview1;
	private TextView textview4;
	private TextView textview5;
	private LinearLayout linear85;
	private TextView textview3;

	private RequestNetwork network;
	private RequestNetwork.RequestListener _network_request_listener;
	private Intent i_net = new Intent();
	private DatabaseReference update = _firebase.getReference("update");
	private ChildEventListener _update_child_listener;
	private Intent ii = new Intent();
	private TimerTask t;
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


	private OnCompleteListener cloudMessaging_onCompleteListener;
	private SharedPreferences tt;
	private RequestNetwork requestNetwork;
	private RequestNetwork.RequestListener _requestNetwork_request_listener;
	private Intent Intent_Developer = new Intent();
	private TimerTask Loading1;
	private TimerTask Loading2;
	private TimerTask Loading3;
	private TimerTask Loading;
	private TimerTask Time;
	private SharedPreferences fft;
	private Intent py = new Intent();
	private SharedPreferences rrr;
	private DynamicLink dynamic;
	private OnSuccessListener dynamic_onSuccessLink;
	private OnFailureListener dynamic_onFailureLink;
	private TimerTask r;
	private EditText edittext1;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}

	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		edittext1 = findViewById(R.id.edittext1);
		linear_last = findViewById(R.id.linear_last);
		linear83 = findViewById(R.id.linear83);
		linear84 = findViewById(R.id.linear84);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear6 = findViewById(R.id.linear6);
		progressbar1 = findViewById(R.id.progressbar1);
		vscroll1 = findViewById(R.id.vscroll1);
		linear5 = findViewById(R.id.linear5);
		imageview1 = findViewById(R.id.imageview1);
		textview4 = findViewById(R.id.textview4);
		textview5 = findViewById(R.id.textview5);
		linear85 = findViewById(R.id.linear85);
		textview3 = findViewById(R.id.textview3);
		network = new RequestNetwork(this);
		auth = FirebaseAuth.getInstance();
		tt = getSharedPreferences("tt", Activity.MODE_PRIVATE);
		requestNetwork = new RequestNetwork(this);
		fft = getSharedPreferences("fft", Activity.MODE_PRIVATE);
		rrr = getSharedPreferences("rrr", Activity.MODE_PRIVATE);
		edittext1.setVisibility(View.GONE);
		imageview1.performClick();

		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
//				Intent_Developer.setAction(Intent.ACTION_VIEW);
//
//				startActivity(Intent_Developer);
				ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
					CharSequence clipText = clipboardManager.getPrimaryClip().getItemAt(0).getText();
					if (clipText != null) {

						edittext1.setText(clipText);
						// SharedPreferences का नाम "MyPrefs" रखें
						SharedPreferences rrr = getSharedPreferences("MyPrefs", MODE_PRIVATE);

// Value Save करें
						rrr.edit().putString("rrr_by", edittext1.getText().toString()).commit();


					}
				}

			}
		});

		_network_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (s1 && (s2 && (s3 && (s4 && s5)))) {
					Loading1 = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									textview3.setText("Loading.");
									Loading2 = new TimerTask() {
										@Override
										public void run() {
											runOnUiThread(new Runnable() {
												@Override
												public void run() {
													textview3.setText("Loading..");
													Loading3 = new TimerTask() {
														@Override
														public void run() {
															runOnUiThread(new Runnable() {
																@Override
																public void run() {
																	textview3.setText("Loading...");
																	Loading = new TimerTask() {
																		@Override
																		public void run() {
																			runOnUiThread(new Runnable() {
																				@Override
																				public void run() {
																					textview3.setText("Loading....");
																					Time = new TimerTask() {
																						@Override
																						public void run() {
																							runOnUiThread(new Runnable() {
																								@Override
																								public void run() {
																									textview3.setText("Loading.....");
																									Time = new TimerTask() {
																										@Override
																										public void run() {
																											runOnUiThread(new Runnable() {
																												@Override
																												public void run() {
																													textview3.setText(_response);
																												}
																											});
																										}
																									};
																									_timer.schedule(Time, (int)(500));
																									if (SketchwareUtil.isConnected(getApplicationContext())) {
																										if (!(FirebaseAuth.getInstance().getCurrentUser() != null)) {
																											serverKey = "BHPBhyoyhAiiMOaMq6asNZnKcn6aGr6oy67vEme-D2xRy9-viwgeVqZNlXyvHESI7inZ8th8GUOQNCZSla7xIaA";
																											_subscribeFCMTopic("all");
																											Intent_Developer.setClass(getApplicationContext(), LoginGActivity.class);
																											Intent_Developer.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
																											startActivity(Intent_Developer);
																											finish();
																										}
																										else {
																											serverKey = "BHPBhyoyhAiiMOaMq6asNZnKcn6aGr6oy67vEme-D2xRy9-viwgeVqZNlXyvHESI7inZ8th8GUOQNCZSla7xIaA";
																											_subscribeFCMTopic("all");
																											Intent_Developer.setClass(getApplicationContext(), HomeActivity.class);
																											Intent_Developer.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
																											startActivity(Intent_Developer);
																											finish();
																										}
																									}
																									else {
																										Intent_Developer.setClass(getApplicationContext(), NetworkActivity.class);
																										Intent_Developer.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
																										startActivity(Intent_Developer);
																									}
																								}
																							});
																						}
																					};
																					_timer.schedule(Time, (int)(500));
																				}
																			});
																		}
																	};
																	_timer.schedule(Loading, (int)(500));
																}
															});
														}
													};
													_timer.schedule(Loading3, (int)(500));
												}
											});
										}
									};
									_timer.schedule(Loading2, (int)(500));
								}
							});
						}
					};
					_timer.schedule(Loading1, (int)(500));
				}
				else {

				}
			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				textview3.setText("er");
				Intent_Developer.setClass(getApplicationContext(), NetworkActivity.class);
				Intent_Developer.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				Intent_Developer.putExtra("t", "m");
				startActivity(Intent_Developer);
			}
		};

		_update_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				app_version = _childValue.get("version").toString();
				if ((Double.parseDouble(y_version) == Double.parseDouble(app_version)) || (Double.parseDouble(y_version) > Double.parseDouble(app_version))) {

				}
				else {
					if (Double.parseDouble(y_version) < Double.parseDouble(app_version)) {
						ii.setClass(getApplicationContext(), UpdatActivity.class);
						ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(ii);
					}
				}
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
		update.addChildEventListener(_update_child_listener);

		cloudMessaging_onCompleteListener = new OnCompleteListener<InstallationTokenResult>() {
			@Override
			public void onComplete(Task<InstallationTokenResult> task) {
				final boolean _success = task.isSuccessful();
				final String _token = _success ? task.getResult().getToken() : "";
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";

				// Handle success or error accordingly
			}
		};

		_requestNetwork_request_listener = new RequestNetwork.RequestListener() {
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
		String timeSettings = android.provider.Settings.System.getString(
		                this.getContentResolver(),
		                android.provider.Settings.System.AUTO_TIME);
		        if (timeSettings.contentEquals("0")) {
					            android.provider.Settings.System.putString(
					                    this.getContentResolver(),
					                    android.provider.Settings.System.AUTO_TIME, "1");
					        }
		if (timeSettings.equals("1")) {
					// put in md

				if (fft.getString("pr", "").equals("")) {

					fft.edit().putString("pr", "true").commit();
					py.setClass(getApplicationContext(), TmpproActivity.class);
					startActivity(py);
					finish();
			}
			else {
					network.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "net", _network_request_listener);
					p_name = "com.cashluto.rewad";
					android.content.pm.PackageManager pm = getPackageManager();
					try {
							android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo( p_name, android.content.pm.PackageManager.GET_ACTIVITIES);
							y_version = pinfo.versionName; }
					catch (Exception e){ showMessage(e.toString()); }
					serverKey = "BHPBhyoyhAiiMOaMq6asNZnKcn6aGr6oy67vEme-D2xRy9-viwgeVqZNlXyvHESI7inZ8th8GUOQNCZSla7xIaA";
					_subscribeFCMTopic("all");
					textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/montserrat_semibold.ttf"), Typeface.NORMAL);
					textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/montserrat_semibold.ttf"), Typeface.NORMAL);
					_SecurityApp();
					if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
							SketchwareUtil.showMessage(getApplicationContext(), "Please Wait Loading depend on your network connectivity ");
					}
					else {
							FirebaseDynamicLinks.getInstance()
							.getDynamicLink(getIntent())
							.addOnSuccessListener(MainActivity.this, new OnSuccessListener<PendingDynamicLinkData>() {
										@Override
										public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
													// Get deep link from result (may be null if no link is found)
													Uri deepLink = null;
													if (pendingDynamicLinkData != null) {
																deepLink = pendingDynamicLinkData.getLink();
													}
													if(deepLink !=null){
																rrr.edit().putString("rrr_by", deepLink.getQueryParameter("invite")).commit();
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
			}


				//ok put
		}
		else {
					SketchwareUtil.showMessage(getApplicationContext(), "Something Went Wrong your clock⏰ rong");
		}
		/*

if (fft.getString("pr", "").equals("")) {

fft.edit().putString("pr", "true").commit();
py.setClass(getApplicationContext(), TmpproActivity.class);
startActivity(py);
finish();
}
else {
network.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "net", _network_request_listener);
p_name = "com.cashluto.rewad";
android.content.pm.PackageManager pm = getPackageManager();
try {
android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo( p_name, android.content.pm.PackageManager.GET_ACTIVITIES);
y_version = pinfo.versionName; }
catch (Exception e){ showMessage(e.toString()); }
		serverKey = "BHPBhyoyhAiiMOaMq6asNZnKcn6aGr6oy67vEme-D2xRy9-viwgeVqZNlXyvHESI7inZ8th8GUOQNCZSla7xIaA";
		_subscribeFCMTopic("all");
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/montserrat_semibold.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/montserrat_semibold.ttf"), 0);
		_SecurityApp();
		if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
			SketchwareUtil.showMessage(getApplicationContext(), "Please Wait Loading depend on your network connectivity ");
		}
		else {
			FirebaseDynamicLinks.getInstance()
			.getDynamicLink(getIntent())
			.addOnSuccessListener(MainActivity.this, new OnSuccessListener<PendingDynamicLinkData>() {
					@Override
					public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
							// Get deep link from result (may be null if no link is found)
							Uri deepLink = null;
							if (pendingDynamicLinkData != null) {
									deepLink = pendingDynamicLinkData.getLink();
							}
							if(deepLink !=null){
									rrr.edit().putString("rrr_by", deepLink.getQueryParameter("invite")).commit();
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
	}


	*/
}


@Override
public void onResume() {
	super.onResume();
	/* if (textview3.getText().toString().equals("er")) { */
		textview3.setText("load");

		/* network.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "net", _network_request_listener);
	}
	else {

	} */
}
public void _subscribeFCMTopic(final String _name) {
	if (_name.matches("[a-zA-Z0-9-_.~%]{1,900}")) {
		String topicName = java.text.Normalizer.normalize(_name, java.text.Normalizer.Form.NFD);
		topicName = topicName.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	}
	else {
		SketchwareUtil.showMessage(getApplicationContext(), "Badly Formated Topic");
	}
}


public void _sendFCMNotification(final String _key, final String _title, final String _content, final String _imageUrl, final String _topic, final String _token) {
	if (SketchwareUtil.isConnected(getApplicationContext())) {
		HashMap<String, Object> requestHeader = new HashMap<>();

		HashMap<String, Object> notificationBody = new HashMap<>();

		HashMap<String, Object> requestBody = new HashMap<>();
		requestHeader = new HashMap<>();
		requestHeader.put("Authorization", "key=".concat(_key));
		requestHeader.put("Content-Type", "application/json");

		notificationBody = new HashMap<>();
		notificationBody.put("title", _title);
		notificationBody.put("body", _content);
		notificationBody.put("image", _imageUrl);

		requestBody = new HashMap<>();
		if (!_topic.equals("null")) {
			requestBody.put("to", "/topics/".concat(_topic));
		} else {
			requestBody.put("to", _token);
		}
		requestBody.put("notification", notificationBody);
		requestNetwork.setHeaders(requestHeader);
		requestNetwork.setParams(requestBody, RequestNetworkController.REQUEST_BODY);
		requestNetwork.startRequestNetwork(RequestNetworkController.POST, "https://fcm.googleapis.com/fcm/send", "", _requestNetwork_request_listener);
	}
	else {
		SketchwareUtil.showMessage(getApplicationContext(), "No Internet Connection");
	}
}


public void _glideFromURL(final String _url, final ImageView _imageview) {

	Glide.with(getApplicationContext())
	.load(_url)
	.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
	.error(R.drawable.app_icon)
	.into(_imageview);
}


public void _ICC(final ImageView _img, final String _c1, final String _c2) {
	_img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
}


public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
	android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
	gd.setColor(Color.parseColor(_color1));
	gd.setCornerRadius((int) _round);
	gd.setStroke((int) _border, Color.parseColor(_color2));
	_view.setBackground(gd);
}


public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
	android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
	GG.setColor(Color.parseColor(_focus));
	GG.setCornerRadius((float)_round);
	GG.setStroke((int) _stroke,
	Color.parseColor("#" + _strokeclr.replace("#", "")));
	android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
	_view.setBackground(RE);
}


public void _DARK_ICONS() {
	getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
}


public void _iDEA_Anmition() {
	final TheGlowingLoader glow = new TheGlowingLoader(this);
	glow.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, android.widget.LinearLayout.LayoutParams.MATCH_PARENT));

	final Configuration configuration = new Configuration();

	configuration.setLine1Color(0xFFFFFFFF);

	configuration.setLine2Color(0xFFFF5C5C);

	configuration.setRippleColor(0xFFFFFFFF);

	configuration.setParticle1Color(0xffFFCA28);

	configuration.setParticle2Color(0xFFFFFFFF);

	configuration.setParticle3Color(0xff448AFF);

	configuration.setLineStrokeWidth(Constants.DEF_LINE_STROKE_WIDTH);

	configuration.setDisableShadows(false);

	configuration.setDisableRipple(false);

	configuration.setShadowOpacity(Constants.DEF_SHADOW_OPACITY);

	glow.setConfiguration(configuration);
	linear1.addView(glow);

}
public class TheGlowingLoader extends FrameLayout {
	    private Paint paint;
	    private LineAnimator lineAnimator;
	    private RippleAnimator rippleAnimator1, rippleAnimator2;
	    private Configuration configuration;
	    public TheGlowingLoader(Context context) {
		        super(context);
		        init();
		    }
	    public TheGlowingLoader(Context context,  AttributeSet attrs) {
		        super(context, attrs);
		        getStuffFromXML(attrs);
		        init();
		    }
	    public TheGlowingLoader(Context context,  AttributeSet attrs, int defStyleAttr) {
		        super(context, attrs, defStyleAttr);
		        getStuffFromXML(attrs);
		        init();
		    }
	    public TheGlowingLoader(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		        super(context, attrs, defStyleAttr, defStyleRes);
		        getStuffFromXML(attrs);
		        init();
		    }
	    private void getStuffFromXML(AttributeSet attrs) {
		        configuration = new Configuration();
		        configuration.setLine1Color(0xFFFFFFFF);
		        configuration.setLine2Color(0xFFFF5C5C); //red
		        configuration.setRippleColor(0xFFFFFFFF);
		        configuration.setParticle1Color(0xffFFCA28); //yellow
		        configuration.setParticle2Color(0xFFFFFFFF);
		        configuration.setParticle3Color(0xff448AFF); //blue
		        configuration.setLineStrokeWidth(Constants.DEF_LINE_STROKE_WIDTH);
		        configuration.setDisableShadows(false);
		        configuration.setDisableRipple(false);
		        configuration.setShadowOpacity(Constants.DEF_SHADOW_OPACITY);
		    }
	    public void setConfiguration(Configuration configuration) {
		        this.configuration = configuration;
		    }
	    private void init() {
		        if (configuration == null) {
			            configuration = new Configuration(getContext());
			        }
		        setWillNotDraw(false);
		        rippleAnimator1 = new RippleAnimator(TheGlowingLoader.this, configuration);
		        rippleAnimator2 = new RippleAnimator(TheGlowingLoader.this, configuration);
		        lineAnimator = new LineAnimator(TheGlowingLoader.this, configuration);
		        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		        paint.setStrokeCap(Paint.Cap.ROUND);
		    }
	    private void startAnimation() {
		        lineAnimator.start(new LineAnimator.Callback() {
			            @Override
			            public void onValueUpdated() {
				                invalidate();
				            }

			            @Override
			            public void startFirstCircleAnimation(float x, float y) {
				                if (!configuration.isDisableRipple()) {
					                    rippleAnimator1.setCircleCenter(x, y);
					                    rippleAnimator1.start(new RippleAnimator.Callback() {
						                        @Override
						                        public void onValueUpdated() {
							                            invalidate();
							                        }
						                    }, 60, 150, 270);
					                }
				            }

			            @Override
			            public void startSecondCircleAnimation(float x, float y) {
				                if (!configuration.isDisableRipple()) {
					                    rippleAnimator2.setCircleCenter(x, y);
					                    rippleAnimator2.start(new RippleAnimator.Callback() {
						                        @Override
						                        public void onValueUpdated() {
							                            invalidate();
							                        }
						                    }, -60, 0, Constants.INVALID_DEG);
					                }

				            }
			        });
		    }
	    @Override
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		        super.onSizeChanged(w, h, oldw, oldh);
		        float offset = 0;
		        float x1, x2, x3, x4, y1, y2, y3, y4;

		        if (w > h) {
			            offset = (w - h) * 80 / 100;
			        }

		        w = w - (int) offset;
		        x1 = .05f * w + offset / 2;
		        y1 = h / 2 + .15f * w;
		        x2 = .30f * w + offset / 2;
		        y2 = h / 2 + -.12f * w;
		        x3 = .70f * w + offset / 2;
		        y3 = h / 2 + .27f * w;
		        x4 = .95f * w + offset / 2;
		        y4 = h / 2 - .02f * w;

		        float circleMaxRadius = (x4 - x1) * .18f;
		        rippleAnimator2.setCircleMaxRadius(circleMaxRadius);
		        rippleAnimator1.setCircleMaxRadius(circleMaxRadius);
		        lineAnimator.updateEdgeCoordinates(x1, x2, x3, x4, y1, y2, y3, y4);
		        startAnimation();
		    }
	    @Override
	    protected void onDraw(Canvas canvas) {
		        super.onDraw(canvas);
		        rippleAnimator1.draw(canvas, paint);
		        rippleAnimator2.draw(canvas, paint);
		        lineAnimator.draw(canvas, paint);
		    }
}



public static class Constants {
	    public static final int DEF_LINE_STROKE_WIDTH = 30;
	    public static final int INVALID_DEG = 124988984;
	    public static final float DEF_SHADOW_OPACITY = .23f;
}

public static class Configuration {
	    public static int line1Color = 0xFFFFFFFF;
	    public static int line2Color = 0xFFFF5C5C;
	    public static int rippleColor = 0xFFFFFFFF;
	    public static int particle1Color = 0xffFFCA28;
	    public static int particle2Color = 0xFFFFFFFF;
	    public static int particle3Color = 0xff448AFF;
	    public static int lineStrokeWidth = Constants.DEF_LINE_STROKE_WIDTH;
	    public static boolean disableShadows = false;
	    public static boolean disableRipple = false;
	    public static float shadowOpacity = Constants.DEF_SHADOW_OPACITY;
	    Configuration() {
		    }

	    public Configuration(Context context) {
		        disableShadows = disableShadows;
		        disableRipple = disableRipple;
		        shadowOpacity = shadowOpacity;
		        line1Color = line1Color;
		        line2Color = line2Color;
		        lineStrokeWidth = lineStrokeWidth;
		        rippleColor = rippleColor;
		        particle1Color = particle1Color;
		        particle2Color = particle2Color;
		        particle3Color = particle3Color;
		    }
	    public int getLine1Color() {
		        return line1Color;
		    }
	    public void setLine1Color(int line1Color) {
		        this.line1Color = line1Color;
		    }
	    public int getLine2Color() {
		        return line2Color;
		    }
	    public void setLine2Color(int line2Color) {
		        this.line2Color = line2Color;
		    }
	    public int getRippleColor() {
		        return rippleColor;
		    }
	    public void setRippleColor(int rippleColor) {
		        this.rippleColor = rippleColor;
		    }
	    public int getParticle1Color() {
		        return particle1Color;
		    }
	    public void setParticle1Color(int particle1Color) {
		        this.particle1Color = particle1Color;
		    }
	    public int getParticle2Color() {
		        return particle2Color;
		    }
	    public void setParticle2Color(int particle2Color) {
		        this.particle2Color = particle2Color;
		    }
	    public int getParticle3Color() {
		        return particle3Color;
		    }
	    public void setParticle3Color(int particle3Color) {
		        this.particle3Color = particle3Color;
		    }
	    public int getLineStrokeWidth() {
		        return lineStrokeWidth;
		    }
	    public void setLineStrokeWidth(int lineStrokeWidth) {
		        this.lineStrokeWidth = lineStrokeWidth;
		    }
	    public boolean isDisableShadows() {
		        return disableShadows;
		    }
	    public void setDisableShadows(boolean disableShadows) {
		        this.disableShadows = disableShadows;
		    }
	    public boolean isDisableRipple() {
		        return disableRipple;
		    }
	    public void setDisableRipple(boolean disableRipple) {
		        this.disableRipple = disableRipple;
		    }
	    public float getShadowOpacity() {
		        return shadowOpacity;
		    }
	    public void setShadowOpacity(float shadowOpacity) {
		        if (shadowOpacity < 0.0f) {
			            shadowOpacity = 0f;
			        } else if (shadowOpacity > 1f) {
			            shadowOpacity = 1f;
			        }
		        this.shadowOpacity = shadowOpacity;
		    }
}

public static abstract class ParticleView extends View {
	    public ParticleView(Context context) {
		        super(context);
		    }
	    public abstract void setPaintColor(int color);
}

public static class Triangle extends ParticleView {
	    private int w;
	    private int h;
	    private Paint paint;

	    public Triangle(Context context) {
		        super(context);
		        init();
		    }
	    @Override
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		        super.onSizeChanged(w, h, oldw, oldh);
		        this.w = w;
		        this.h = h;
		    }
	    private void init() {
		        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		        paint.setStrokeCap(Paint.Cap.ROUND);
		        paint.setStyle(Paint.Style.FILL);
		    }
	    @Override
	    protected void onDraw(Canvas canvas) {
		        super.onDraw(canvas);
		        Path path = new Path();
		        path.moveTo(w / 2, 0);
		        path.lineTo(w, h);
		        path.lineTo(0, h);
		        path.lineTo(w / 2, 0);
		        canvas.drawPath(path, paint);
		    }
	    public void setPaintColor(int paintColor) {
		        paint.setColor(paintColor);
		        invalidate();
		    }
}


public static class Circle extends ParticleView {
	    private int w;
	    private int h;
	    private Paint paint;
	    public Circle(Context context) {
		        super(context);
		        init();
		    }
	    @Override
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		        super.onSizeChanged(w, h, oldw, oldh);
		        this.w = w;
		        this.h = h;
		    }
	    private void init() {
		        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		        paint.setStrokeCap(Paint.Cap.ROUND);
		        paint.setStyle(Paint.Style.FILL);
		    }
	    @Override
	    protected void onDraw(Canvas canvas) {
		        super.onDraw(canvas);
		        canvas.drawCircle(w / 2, h / 2, w / 2, paint);
		    }
	    public void setPaintColor(int paintColor) {
		        paint.setColor(paintColor);
		        invalidate();
		    }
}



public static class RippleAnimator {
	    private final static int PARTICLE_TYPE_TRIANGLE = 0;
	    private final static int PARTICLE_TYPE_CIRCLE = 1;
	    private float circleMaxRadius;
	    private float circleRadius, circleRadius2;
	    private int circleStroke, circleStroke2;
	    private float circleAlpha, circleAlpha2;
	    private float cX, cY;
	    private FrameLayout view;
	    private Configuration configuration;
	    public RippleAnimator(FrameLayout v, Configuration configuration) {
		        view = v;
		        this.configuration = configuration;
		    }
	    public void setCircleMaxRadius(float r) {
		        this.circleMaxRadius = r;
		    }
	    public void setCircleCenter(float x, float y) {
		        cX = x;
		        cY = y;
		    }
	    public void startCircleMajor(final Callback callback) {
		        PropertyValuesHolder rp = PropertyValuesHolder.ofFloat("radius", 0, circleMaxRadius);
		        PropertyValuesHolder ap = PropertyValuesHolder.ofFloat("alpha", 1, 0);
		        PropertyValuesHolder sp = PropertyValuesHolder.ofInt("stroke", (int) (circleMaxRadius * .15f), 0);
		        ValueAnimator va = ValueAnimator.ofPropertyValuesHolder(rp, ap, sp);
		        va.setInterpolator(new AccelerateInterpolator(.4f));
		        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator animation) {
				                circleRadius = (float) animation.getAnimatedValue("radius");
				                circleAlpha = (float) animation.getAnimatedValue("alpha");
				                circleStroke = (int) animation.getAnimatedValue("stroke");
				                callback.onValueUpdated();
				            }
			        });
		        va.setDuration(450);
		        va.start();
		    }
	    public void startCircleMinor(final Callback callback) {
		        PropertyValuesHolder rp2 = PropertyValuesHolder.ofFloat("radius", 0, circleMaxRadius * .60f);
		        PropertyValuesHolder ap2 = PropertyValuesHolder.ofFloat("alpha", 1, 0);
		        PropertyValuesHolder sp2 = PropertyValuesHolder.ofInt("stroke", (int) (circleMaxRadius * .06f), 0);
		        ValueAnimator va2 = ValueAnimator.ofPropertyValuesHolder(rp2, ap2, sp2);
		        va2.setInterpolator(new AccelerateInterpolator(.4f));
		        va2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator animation) {
				                circleRadius2 = (float) animation.getAnimatedValue("radius");
				                circleAlpha2 = (float) animation.getAnimatedValue("alpha");
				                circleStroke2 = (int) animation.getAnimatedValue("stroke");
				                callback.onValueUpdated();
				            }
			        });
		        va2.setDuration(380);
		        va2.start();
		    }
	    public void startParticleAnimation(float degree, int particleType, int particleColor) {
		        float length = 2 * circleMaxRadius;
		        float x = (float) (cX + length * Math.cos(Math.toRadians(degree)));
		        float y = (float) (cY - length * Math.sin(Math.toRadians(degree)));
		        final ParticleView particleView;
		        switch (particleType) {
			            case PARTICLE_TYPE_CIRCLE:
			                particleView = new Circle(view.getContext());
			                break;
			            case PARTICLE_TYPE_TRIANGLE:
			                particleView = new Triangle(view.getContext());
			                break;
			            default:
			                particleView = new Circle(view.getContext());
			        }
		        int size = (int) (circleMaxRadius * .3f);
		        particleView.setLayoutParams(new ViewGroup.LayoutParams(size, size));
		        particleView.setPaintColor(particleColor);
		        view.addView(particleView);
		        PropertyValuesHolder sP = PropertyValuesHolder.ofFloat("scale", 0, 1);
		        PropertyValuesHolder rP = PropertyValuesHolder.ofFloat("rotation", 0, 360);
		        PropertyValuesHolder aP = PropertyValuesHolder.ofFloat("alpha", 1, 0);
		        PropertyValuesHolder xP = PropertyValuesHolder.ofFloat("x", cX, x);
		        PropertyValuesHolder yP = PropertyValuesHolder.ofFloat("y", cY, y);
		        ValueAnimator va = ValueAnimator.ofPropertyValuesHolder(xP, yP, sP, aP, rP);
		        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator animation) {
				                float x = (float) animation.getAnimatedValue("x");
				                float y = (float) animation.getAnimatedValue("y");
				                float scale = (float) animation.getAnimatedValue("scale");
				                float alpha = (float) animation.getAnimatedValue("alpha");
				                float rotation = (float) animation.getAnimatedValue("rotation");
				                particleView.setX(x);
				                particleView.setY(y);
				                particleView.setRotation(rotation);
				                particleView.setScaleX(scale);
				                particleView.setScaleY(scale);
				                particleView.setAlpha(alpha);
				            }
			        });
		        va.setInterpolator(new AccelerateInterpolator(.4f));
		        va.setDuration(550);
		        va.start();

		        va.addListener(new AnimatorListenerAdapter() {
			            @Override
			            public void onAnimationEnd(Animator animation) {
				                super.onAnimationEnd(animation);
				                view.removeView(particleView);
				            }
			        });
		    }
	    public void start(final Callback callback, float degree1, float degree2, float degree3) {
		        startCircleMajor(callback);
		        startCircleMinor(callback);
		        if (degree1 != Constants.INVALID_DEG)
		            startParticleAnimation(degree1, PARTICLE_TYPE_TRIANGLE, configuration.getParticle1Color());
		        if (degree2 != Constants.INVALID_DEG)
		            startParticleAnimation(degree2, PARTICLE_TYPE_CIRCLE, configuration.getParticle2Color());
		        if (degree3 != Constants.INVALID_DEG)
		            startParticleAnimation(degree3, PARTICLE_TYPE_TRIANGLE, configuration.getParticle3Color());
		    }
	    public void draw(Canvas canvas, Paint paint) {
		        paint.setMaskFilter(null);
		        paint.setStyle(Paint.Style.STROKE);
		        paint.setColor(configuration.getRippleColor());
		        paint.setStrokeWidth(circleStroke);
		        paint.setAlpha((int) (255 * circleAlpha));
		        canvas.drawCircle(cX, cY, circleRadius, paint);
		        if (!configuration.isDisableShadows()) {
			            paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
			            paint.setStrokeWidth(.28f * circleRadius);
			            paint.setAlpha((int) (255 * circleAlpha * configuration.getShadowOpacity()));
			            canvas.drawCircle(cX, cY + 100, circleRadius, paint);
			        }
		        paint.setMaskFilter(null);
		        paint.setColor(configuration.getRippleColor());
		        paint.setStrokeWidth(circleStroke2);
		        paint.setAlpha((int) (255 * circleAlpha2));
		        canvas.drawCircle(cX, cY, circleRadius2, paint);
		    }
	    public interface Callback {
		        void onValueUpdated();
		    }
}



public static class LineAnimator {
	    private View view;
	    private Configuration configuration;
	    private float x1, x2, x3, x4, y1, y2, y3, y4;
	    private float wxa11, wxa12, wya11, wya12, wxa21, wya21, wxa22, wya22, wxa31, wya31, wxa32, wya32;
	    private float rxa11, rxa12, rya11, rya12, rxa21, rya21, rxa22, rya22, rxa31, rya31, rxa32, rya32;
	    public LineAnimator(View view, Configuration configuration) {
		        this.view = view;
		        this.configuration = configuration;
		    }
	    public void updateEdgeCoordinates(float x1, float x2, float x3, float x4, float y1, float y2, float y3, float y4) {
		        this.x1 = x1;
		        this.x2 = x2;
		        this.x3 = x3;
		        this.x4 = x4;
		        this.y1 = y1;
		        this.y2 = y2;
		        this.y3 = y3;
		        this.y4 = y4;
		    }
	    private void initLineCoordinates(boolean forward) {
		        if (forward) {
			            wxa11 = wxa12 = rxa11 = rxa12 = x1;
			            wxa21 = wxa22 = rxa21 = rxa22 = x2;
			            wya11 = wya12 = rya11 = rya12 = y1;
			            wya21 = wya22 = rya21 = rya22 = y2;
			            wxa31 = wxa32 = rxa31 = rxa32 = x3;
			            wya31 = wya32 = rya31 = rya32 = y3;
			        } else {
			            wxa11 = wxa12 = rxa11 = rxa12 = x2;
			            wxa21 = wxa22 = rxa21 = rxa22 = x3;
			            wya11 = wya12 = rya11 = rya12 = y2;
			            wya21 = wya22 = rya21 = rya22 = y3;
			            wxa31 = wxa32 = rxa31 = rxa32 = x4;
			            wya31 = wya32 = rya31 = rya32 = y4;
			        }
		    }
	    public void draw(Canvas canvas, Paint paint) {
		        paint.setStyle(Paint.Style.STROKE);
		        paint.setMaskFilter(null);
		        paint.setStrokeWidth(configuration.getLineStrokeWidth());
		        paint.setColor(configuration.getLine1Color());
		        if (wxa11 != wxa12 && wya11 != wya12)
		            canvas.drawLine(wxa11, wya11, wxa12, wya12, paint);
		        if (wxa21 != wxa22 && wya21 != wya22)
		            canvas.drawLine(wxa21, wya21, wxa22, wya22, paint);
		        if (wxa31 != wxa32 && wya31 != wya32)
		            canvas.drawLine(wxa31, wya31, wxa32, wya32, paint);
		        paint.setColor(configuration.getLine2Color());
		        if (rxa11 != rxa12 && rya11 != rya12)
		            canvas.drawLine(rxa11, rya11, rxa12, rya12, paint);
		        if (rxa21 != rxa22 && rya21 != rya22)
		            canvas.drawLine(rxa21, rya21, rxa22, rya22, paint);
		        if (rxa31 != rxa32 && rya31 != rya32)
		            canvas.drawLine(rxa31, rya31, rxa32, rya32, paint);
		        if (!configuration.isDisableShadows()) {
			            paint.setMaskFilter(new BlurMaskFilter(70, BlurMaskFilter.Blur.NORMAL));
			            paint.setStrokeWidth(2.666f * configuration.getLineStrokeWidth());
			            paint.setColor(configuration.getLine1Color());
			            paint.setAlpha((int) (255 * configuration.getShadowOpacity()));
			            if (wxa11 != wxa12 && wya11 != wya12)
			                canvas.drawLine(wxa11, wya11 + 100, wxa12, wya12 + 100, paint);
			            if (wxa21 != wxa22 && wya21 != wya22)
			                canvas.drawLine(wxa21, wya21 + 100, wxa22, wya22 + 100, paint);
			            if (wxa31 != wxa32 && wya31 != wya32)
			                canvas.drawLine(wxa31, wya31 + 100, wxa32, wya32 + 100, paint);
			            paint.setColor(configuration.getLine2Color());
			            paint.setAlpha((int) (255 * configuration.getShadowOpacity()));
			            if (rxa11 != rxa12 && rya11 != rya12)
			                canvas.drawLine(rxa11, rya11 + 100, rxa12, rya12 + 100, paint);
			            if (rxa21 != rxa22 && rya21 != rya22)
			                canvas.drawLine(rxa21, rya21 + 100, rxa22, rya22 + 100, paint);
			            if (rxa31 != rxa32 && rya31 != rya32)
			                canvas.drawLine(rxa31, rya31 + 100, rxa32, rya32 + 100, paint);
			        }
		    }
	    public void start(final Callback callback) {
		        playForwardAnimation(new LocalCallback() {
			            @Override
			            public void onValueUpdated() {
				                callback.onValueUpdated();
				            }

			            @Override
			            public void startFirstCircleAnimation(float x, float y) {
				                callback.startFirstCircleAnimation(x, y);
				            }

			            @Override
			            public void startSecondCircleAnimation(float x, float y) {
				                callback.startSecondCircleAnimation(x, y);
				            }

			            @Override
			            public void onComplete() {

				            }
			        });
		    }

	    private void playForwardAnimation(final LocalCallback callback) {
		        playWhiteLineForward(new LocalCallback() {
			            @Override
			            public void onValueUpdated() {
				                callback.onValueUpdated();
				            }

			            @Override
			            public void startFirstCircleAnimation(float x, float y) {
				                callback.startFirstCircleAnimation(x, y);
				            }

			            @Override
			            public void startSecondCircleAnimation(float x, float y) {
				                callback.startSecondCircleAnimation(x, y);
				            }


			            @Override
			            public void onComplete() {

				            }
			        });

		        view.postDelayed(new Runnable() {
			            @Override
			            public void run() {
				                playRedLineForward(new LocalCallback() {
					                    @Override
					                    public void onValueUpdated() {
						                        callback.onValueUpdated();
						                    }

					                    @Override
					                    public void startFirstCircleAnimation(float x, float y) {
						                        callback.startFirstCircleAnimation(x, y);
						                    }

					                    @Override
					                    public void startSecondCircleAnimation(float x, float y) {
						                        callback.startSecondCircleAnimation(x, y);
						                    }

					                    @Override
					                    public void onComplete() {
						                        playBackwardAnimation(callback);
						                    }
					                });
				            }
			        }, 183);
		    }

	    private void playBackwardAnimation(final LocalCallback callback) {
		        playWhiteLineBackward(new LocalCallback() {
			            @Override
			            public void onValueUpdated() {
				                callback.onValueUpdated();
				            }

			            @Override
			            public void startFirstCircleAnimation(float x, float y) {
				                callback.startFirstCircleAnimation(x, y);
				            }

			            @Override
			            public void startSecondCircleAnimation(float x, float y) {
				                callback.startSecondCircleAnimation(x, y);
				            }

			            @Override
			            public void onComplete() {

				            }
			        });

		        view.postDelayed(new Runnable() {
			            @Override
			            public void run() {
				                playRedLineBackward(new LocalCallback() {
					                    @Override
					                    public void onValueUpdated() {
						                        callback.onValueUpdated();
						                    }

					                    @Override
					                    public void startFirstCircleAnimation(float x, float y) {
						                        callback.startFirstCircleAnimation(x, y);
						                    }

					                    @Override
					                    public void startSecondCircleAnimation(float x, float y) {
						                        callback.startSecondCircleAnimation(x, y);
						                    }

					                    @Override
					                    public void onComplete() {
						                        playForwardAnimation(callback);
						                    }
					                });
				            }
			        }, 183);
		    }

	    private void playWhiteLineForward(final LocalCallback callback) {
		        initLineCoordinates(true);
		        ValueAnimator va11, va12, va21, va22, va31, va32;
		        PropertyValuesHolder px1 = PropertyValuesHolder.ofFloat("X", x1, x2);
		        PropertyValuesHolder py1 = PropertyValuesHolder.ofFloat("Y", y1, y2);
		        PropertyValuesHolder px2 = PropertyValuesHolder.ofFloat("X", x2, x3);
		        PropertyValuesHolder py2 = PropertyValuesHolder.ofFloat("Y", y2, y3);
		        PropertyValuesHolder px3 = PropertyValuesHolder.ofFloat("X", x3, x4);
		        PropertyValuesHolder py3 = PropertyValuesHolder.ofFloat("Y", y3, y4);

		        va11 = ValueAnimator.ofPropertyValuesHolder(px1, py1);
		        va11.setInterpolator(new AccelerateInterpolator(1.8f));
		        va11.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                wxa12 = (float) valueAnimator.getAnimatedValue("X");
				                wya12 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va12 = ValueAnimator.ofPropertyValuesHolder(px1, py1);
		        va12.setInterpolator(new AccelerateInterpolator(1.8f));
		        va12.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                wxa11 = (float) valueAnimator.getAnimatedValue("X");
				                wya11 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });


		        va21 = ValueAnimator.ofPropertyValuesHolder(px2, py2);
		        va21.setInterpolator(new AccelerateInterpolator(1.8f));
		        va21.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                wxa22 = (float) valueAnimator.getAnimatedValue("X");
				                wya22 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va22 = ValueAnimator.ofPropertyValuesHolder(px2, py2);
		        va22.setInterpolator(new AccelerateInterpolator(1.8f));
		        va22.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                wxa21 = (float) valueAnimator.getAnimatedValue("X");
				                wya21 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va31 = ValueAnimator.ofPropertyValuesHolder(px3, py3);
		        va31.setInterpolator(new DecelerateInterpolator(1.8f));
		        va31.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                wxa32 = (float) valueAnimator.getAnimatedValue("X");
				                wya32 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va32 = ValueAnimator.ofPropertyValuesHolder(px3, py3);
		        va32.setInterpolator(new DecelerateInterpolator(1.8f));
		        va32.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                wxa31 = (float) valueAnimator.getAnimatedValue("X");
				                wya31 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        int tw11 = 450;
		        int tw21 = 143;
		        int tw31 = 510;
		        va11.setDuration(tw11);
		        va21.setDuration(tw21);
		        va31.setDuration(tw31);

		        va21.setStartDelay(tw11);
		        va31.setStartDelay(tw11 + tw21);

		        int tw12 = 510;
		        int tw22 = 165;
		        int tw32 = 433;
		        va12.setDuration(tw12);
		        va22.setDuration(tw22);
		        va32.setDuration(tw32);


		        va22.setStartDelay(tw12);
		        va32.setStartDelay(tw12 + tw22);

		        va11.start();
		        va12.start();
		        va21.start();
		        va22.start();
		        va31.start();
		        va32.start();

		        va12.addListener(new AnimatorListenerAdapter() {
			            @Override
			            public void onAnimationEnd(Animator animation) {
				                super.onAnimationEnd(animation);
				                callback.startFirstCircleAnimation(x2, y2);
				            }
			        });

		        va22.addListener(new AnimatorListenerAdapter() {
			            @Override
			            public void onAnimationEnd(Animator animation) {
				                super.onAnimationEnd(animation);
				                callback.startSecondCircleAnimation(x3, y3);
				            }
			        });

		        va32.addListener(new AnimatorListenerAdapter() {
			            @Override
			            public void onAnimationEnd(Animator animation) {
				                super.onAnimationEnd(animation);
				                callback.onComplete();
				            }
			        });
		    }

	    private void playWhiteLineBackward(final LocalCallback callback) {
		        initLineCoordinates(false);
		        ValueAnimator va11, va12, va21, va22, va31, va32;
		        PropertyValuesHolder px1 = PropertyValuesHolder.ofFloat("X", x4, x3);
		        PropertyValuesHolder py1 = PropertyValuesHolder.ofFloat("Y", y4, y3);
		        PropertyValuesHolder px2 = PropertyValuesHolder.ofFloat("X", x3, x2);
		        PropertyValuesHolder py2 = PropertyValuesHolder.ofFloat("Y", y3, y2);
		        PropertyValuesHolder px3 = PropertyValuesHolder.ofFloat("X", x2, x1);
		        PropertyValuesHolder py3 = PropertyValuesHolder.ofFloat("Y", y2, y1);

		        va11 = ValueAnimator.ofPropertyValuesHolder(px1, py1);
		        va11.setInterpolator(new AccelerateInterpolator(1.8f));
		        va11.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                wxa32 = (float) valueAnimator.getAnimatedValue("X");
				                wya32 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va12 = ValueAnimator.ofPropertyValuesHolder(px1, py1);
		        va12.setInterpolator(new AccelerateInterpolator(1.8f));
		        va12.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                wxa31 = (float) valueAnimator.getAnimatedValue("X");
				                wya31 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va21 = ValueAnimator.ofPropertyValuesHolder(px2, py2);
		        va21.setInterpolator(new AccelerateInterpolator(1.8f));
		        va21.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                wxa22 = (float) valueAnimator.getAnimatedValue("X");
				                wya22 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va22 = ValueAnimator.ofPropertyValuesHolder(px2, py2);
		        va22.setInterpolator(new AccelerateInterpolator(1.8f));
		        va22.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                wxa21 = (float) valueAnimator.getAnimatedValue("X");
				                wya21 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va31 = ValueAnimator.ofPropertyValuesHolder(px3, py3);
		        va31.setInterpolator(new DecelerateInterpolator(1.8f));
		        va31.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                wxa12 = (float) valueAnimator.getAnimatedValue("X");
				                wya12 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va32 = ValueAnimator.ofPropertyValuesHolder(px3, py3);
		        va32.setInterpolator(new DecelerateInterpolator(1.8f));
		        va32.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                wxa11 = (float) valueAnimator.getAnimatedValue("X");
				                wya11 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        int tw11 = 450;
		        int tw21 = 143;
		        int tw31 = 510;
		        va11.setDuration(tw11);
		        va21.setDuration(tw21);
		        va31.setDuration(tw31);

		        va21.setStartDelay(tw11);
		        va31.setStartDelay(tw11 + tw21);

		        int tw12 = 510;
		        int tw22 = 165;
		        int tw32 = 433;
		        va12.setDuration(tw12);
		        va22.setDuration(tw22);
		        va32.setDuration(tw32);


		        va22.setStartDelay(tw12);
		        va32.setStartDelay(tw12 + tw22);

		        va11.start();
		        va21.start();
		        va31.start();

		        va12.start();
		        va22.start();
		        va32.start();


		        va12.addListener(new AnimatorListenerAdapter() {
			            @Override
			            public void onAnimationEnd(Animator animation) {
				                super.onAnimationEnd(animation);
				                callback.startSecondCircleAnimation(x3, y3);
				            }
			        });

		        va22.addListener(new AnimatorListenerAdapter() {
			            @Override
			            public void onAnimationEnd(Animator animation) {
				                super.onAnimationEnd(animation);
				                callback.startFirstCircleAnimation(x2, y2);
				            }
			        });

		        va32.addListener(new AnimatorListenerAdapter() {
			            @Override
			            public void onAnimationEnd(Animator animation) {
				                super.onAnimationEnd(animation);
				                callback.onComplete();
				            }
			        });
		    }

	    private void playRedLineForward(final LocalCallback callback) {
		        initLineCoordinates(true);
		        ValueAnimator va11, va12, va21, va22, va31, va32;
		        PropertyValuesHolder px1 = PropertyValuesHolder.ofFloat("X", x1, x2);
		        PropertyValuesHolder py1 = PropertyValuesHolder.ofFloat("Y", y1, y2);
		        PropertyValuesHolder px2 = PropertyValuesHolder.ofFloat("X", x2, x3);
		        PropertyValuesHolder py2 = PropertyValuesHolder.ofFloat("Y", y2, y3);
		        PropertyValuesHolder px3 = PropertyValuesHolder.ofFloat("X", x3, x4);
		        PropertyValuesHolder py3 = PropertyValuesHolder.ofFloat("Y", y3, y4);

		        va11 = ValueAnimator.ofPropertyValuesHolder(px1, py1);
		        va11.setInterpolator(new AccelerateInterpolator(1.8f));
		        va11.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                rxa12 = (float) valueAnimator.getAnimatedValue("X");
				                rya12 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va12 = ValueAnimator.ofPropertyValuesHolder(px1, py1);
		        va12.setInterpolator(new AccelerateInterpolator(1.8f));
		        va12.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                rxa11 = (float) valueAnimator.getAnimatedValue("X");
				                rya11 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va21 = ValueAnimator.ofPropertyValuesHolder(px2, py2);
		        va21.setInterpolator(new AccelerateInterpolator(1.8f));
		        va21.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                rxa22 = (float) valueAnimator.getAnimatedValue("X");
				                rya22 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va22 = ValueAnimator.ofPropertyValuesHolder(px2, py2);
		        va22.setInterpolator(new AccelerateInterpolator(1.8f));
		        va22.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                rxa21 = (float) valueAnimator.getAnimatedValue("X");
				                rya21 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va31 = ValueAnimator.ofPropertyValuesHolder(px3, py3);
		        va31.setInterpolator(new DecelerateInterpolator(1.8f));
		        va31.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                rxa32 = (float) valueAnimator.getAnimatedValue("X");
				                rya32 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va32 = ValueAnimator.ofPropertyValuesHolder(px3, py3);
		        va32.setInterpolator(new DecelerateInterpolator(1.8f));
		        va32.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                rxa31 = (float) valueAnimator.getAnimatedValue("X");
				                rya31 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });


		        int tw11 = 450;
		        int tw21 = 143;
		        int tw31 = 510;
		        va11.setDuration(tw11);
		        va21.setDuration(tw21);
		        va31.setDuration(tw31);

		        va21.setStartDelay(tw11);
		        va31.setStartDelay(tw11 + tw21);

		        int tw12 = 510;
		        int tw22 = 165;
		        int tw32 = 433;
		        va12.setDuration(tw12);
		        va22.setDuration(tw22);
		        va32.setDuration(tw32);


		        va22.setStartDelay(tw12);
		        va32.setStartDelay(tw12 + tw22);

		        va11.start();
		        va12.start();
		        va21.start();
		        va22.start();
		        va31.start();
		        va32.start();

		        va32.addListener(new AnimatorListenerAdapter() {
			            @Override
			            public void onAnimationEnd(Animator animation) {
				                super.onAnimationEnd(animation);
				                callback.onComplete();
				            }
			        });
		    }

	    private void playRedLineBackward(final LocalCallback callback) {
		        initLineCoordinates(false);
		        ValueAnimator va11, va12, va21, va22, va31, va32;
		        PropertyValuesHolder px1 = PropertyValuesHolder.ofFloat("X", x4, x3);
		        PropertyValuesHolder py1 = PropertyValuesHolder.ofFloat("Y", y4, y3);
		        PropertyValuesHolder px2 = PropertyValuesHolder.ofFloat("X", x3, x2);
		        PropertyValuesHolder py2 = PropertyValuesHolder.ofFloat("Y", y3, y2);
		        PropertyValuesHolder px3 = PropertyValuesHolder.ofFloat("X", x2, x1);
		        PropertyValuesHolder py3 = PropertyValuesHolder.ofFloat("Y", y2, y1);

		        va11 = ValueAnimator.ofPropertyValuesHolder(px1, py1);
		        va11.setInterpolator(new AccelerateInterpolator(1.8f));
		        va11.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                rxa32 = (float) valueAnimator.getAnimatedValue("X");
				                rya32 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va12 = ValueAnimator.ofPropertyValuesHolder(px1, py1);
		        va12.setInterpolator(new AccelerateInterpolator(1.8f));
		        va12.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                rxa31 = (float) valueAnimator.getAnimatedValue("X");
				                rya31 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va21 = ValueAnimator.ofPropertyValuesHolder(px2, py2);
		        va21.setInterpolator(new AccelerateInterpolator(1.8f));
		        va21.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                rxa22 = (float) valueAnimator.getAnimatedValue("X");
				                rya22 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va22 = ValueAnimator.ofPropertyValuesHolder(px2, py2);
		        va22.setInterpolator(new AccelerateInterpolator(1.8f));
		        va22.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                rxa21 = (float) valueAnimator.getAnimatedValue("X");
				                rya21 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va31 = ValueAnimator.ofPropertyValuesHolder(px3, py3);
		        va31.setInterpolator(new DecelerateInterpolator(1.8f));
		        va31.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                rxa12 = (float) valueAnimator.getAnimatedValue("X");
				                rya12 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        va32 = ValueAnimator.ofPropertyValuesHolder(px3, py3);
		        va32.setInterpolator(new DecelerateInterpolator(1.8f));
		        va32.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(ValueAnimator valueAnimator) {
				                rxa11 = (float) valueAnimator.getAnimatedValue("X");
				                rya11 = (float) valueAnimator.getAnimatedValue("Y");
				                callback.onValueUpdated();
				            }
			        });

		        int tw11 = 450;
		        int tw21 = 143;
		        int tw31 = 510;
		        va11.setDuration(tw11);
		        va21.setDuration(tw21);
		        va31.setDuration(tw31);

		        va21.setStartDelay(tw11);
		        va31.setStartDelay(tw11 + tw21);

		        int tw12 = 510;
		        int tw22 = 165;
		        int tw32 = 433;
		        va12.setDuration(tw12);
		        va22.setDuration(tw22);
		        va32.setDuration(tw32);


		        va22.setStartDelay(tw12);
		        va32.setStartDelay(tw12 + tw22);

		        va11.start();
		        va12.start();
		        va21.start();
		        va22.start();
		        va31.start();
		        va32.start();

		        va32.addListener(new AnimatorListenerAdapter() {
			            @Override
			            public void onAnimationEnd(Animator animation) {
				                super.onAnimationEnd(animation);
				                callback.onComplete();
				            }
			        });
		    }

	    private interface LocalCallback {
		        void onValueUpdated();

		        void startFirstCircleAnimation(float x, float y);

		        void startSecondCircleAnimation(float x, float y);

		        void onComplete();
		    }

	    public interface Callback {
		        void onValueUpdated();

		        void startFirstCircleAnimation(float x, float y);

		        void startSecondCircleAnimation(float x, float y);
		    }
}
{
}


public void _RootDevice() {
	boolean isRooted = false; // ডিফল্ট ভ্যালু false

	String[] paths = {
		    "/system/app/Superuser.apk",
		    "/sbin/su",
		    "/system/bin/su",
		    "/system/xbin/su",
		    "/data/local/xbin/su",
		    "/data/local/bin/su",
		    "/system/sd/xbin/su",
		    "/system/bin/failsafe/su",
		    "/data/local/su"
	};

	for (String path : paths) {
		    if (new File(path).exists()) {
			        isRooted = true; // যদি রুট করা হয় তবে true সেট করুন
			        break; // লুপ বন্ধ করুন
			    }
	}

	// isRooted ভ্যালুকে ব্যবহার করুন
}


public void _SecurityApp() {
	/*
String sega = signature.getSHA1(getApplicationContext());
if (sega.equals("equalToShKey_Put")) {
s5 = true;


}
else {
SketchwareUtil.showMessage(getApplicationContext(), "_sting:*Error : 404 not found");
finishAffinity();
}
*/
	String sega = signature.getSHA1(getApplicationContext());
//	if (sega.equals("83AB6C571A6E5B5D9A24941DFC2F92015490B851")) {
	if (sega.equals("BA84BFD73F9DAB8F676FC668FF67FF1F74662E00")) {
			s5 = true;
			       try {

					            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
					            keyStore.load(null);

					            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
//					            keyGenerator.init(new KeyGenParameterSpec.Builder("cashLuto100",
					            keyGenerator.init(new KeyGenParameterSpec.Builder("key0",
					                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
					                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
					                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
					                    .build());
					            SecretKey secretKey = keyGenerator.generateKey();

					        } catch (KeyStoreException e) {
					            e.printStackTrace();
					        } catch (NoSuchAlgorithmException e) {
					            e.printStackTrace();
					        } catch (CertificateException e) {
					            e.printStackTrace();
					        } catch (IOException e) {
					            e.printStackTrace();
					        } catch (InvalidAlgorithmParameterException e) {
					            e.printStackTrace();
					        } catch (NoSuchProviderException e) {
					            e.printStackTrace();
					        }

			if (android.os.Debug.isDebuggerConnected()) {
					    SketchwareUtil.showMessage(getApplicationContext(), "Debugger Detect ");
					finishAffinity();
			} else {
					if (new File("/data/local/tmp/").exists()) {
							    if (getApplicationContext().getPackageName().equals("com.cashluto.rewad")) {

s1 = true;
									String originalFirebaseURL = "https://cash-looto-8fe1e-default-rtdb.firebaseio.com";
									String currentFirebaseURL = FirebaseDatabase.getInstance().getReference().toString();

									if (currentFirebaseURL.equals(originalFirebaseURL)) {
											    s2 = true;
											SketchwareUtil.showMessage(getApplicationContext(), "Welcome ");
											String originalAppName = "Cash Luto";
											String currentAppName = getApplicationInfo().loadLabel(getPackageManager()).toString();

											if (currentAppName.equals(originalAppName)) {
													    s3 = true;
													if (!isRooted) {
															s4 = true;
													}
													else {
															SketchwareUtil.showMessage(getApplicationContext(), "Root device");
															finishAffinity();
													}
											} else {
													    SketchwareUtil.showMessage(getApplicationContext(), "App +n!M");
													finishAffinity();
											}
									} else {
											    SketchwareUtil.showMessage(getApplicationContext(), "App doesn't Exist in the world");
											finishAffinity();
									}
							}
							else {

SketchwareUtil.showMessage(getApplicationContext(), "Application Version Error you need to update on play store ");
									finishAffinity();
							}
					} else {
							    SketchwareUtil.showMessage(getApplicationContext(), "x-€√π`÷`=`RrrTtYwiwijwChangerget;: Experience Faith Worshop_₹¢^$Scam()Api_Security(Working)");
							finishAffinity();
					}
			}

	}
	else {
			SketchwareUtil.showMessage(getApplicationContext(), "_sting:*Error : 404 not found");
			finishAffinity();
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
