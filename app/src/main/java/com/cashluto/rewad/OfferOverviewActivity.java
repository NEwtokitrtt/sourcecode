package com.cashluto.rewad;

import android.app.*;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.net.Uri;
import android.os.*;
import android.util.*;
import android.view.View;
import android.view.View.*;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
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
import com.shobhitpuri.custombuttons.*;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class OfferOverviewActivity extends AppCompatActivity {

	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

	private String oLink = "";
	private String rffa = "";
	private String off_tmp = "";
	private double n1 = 0;
	private HashMap<String, Object> map1 = new HashMap<>();
	private HashMap<String, Object> newdta = new HashMap<>();
	private String logoss = "";
	private String finalUrl = "";
	private String org_ur = "";
	private String click_id_getAUid = "";
	private String ref_code = "";
	private String aff_id_getRef_cod = "default";
	private String modifiedUrl = "";
	private String modifiedUrlL = "";
	private boolean b_Cwncel = false;

	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> refer_list_map = new ArrayList<>();

	private LinearLayout linear141;
	private LinearLayout linear_last;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear_cr2;
	private LinearLayout linear_cr1;
	private ImageView imageview1;
	private TextView textview16;
	private LinearLayout linear81;
	private LinearLayout linear_cr3;
	private LinearLayout linear85;
	private LinearLayout linear87;
	private ScrollView vscroll3;
	private LinearLayout linear103;
	private ScrollView vscroll4;
	private LinearLayout linear107;
	private LinearLayout linear108;
	private LinearLayout linear99;
	private ScrollView vscroll14;
	private LinearLayout linear140;
	private LinearLayout linear86;
	private LinearLayout linear88;
	private ImageView imageview_appLogo;
	private LinearLayout linear90;
	private LinearLayout linear146;
	private TextView textview_apk_info_1;
	private TextView textview_title;
	private TextView textview_rewadsAmount;
	private ImageView imageview33;
	private LinearLayout linear109;
	private TextView textview22;
	private TextView textview23;
	private LinearLayout linear148;
	private TextView textview21;
	private LinearLayout linear147;
	private LinearLayout linear3;
	private LinearLayout linearcccccccc;
	private TextView textview_apk_details_1;
	private LinearLayout linear144;
	private LinearLayout linear143;
	private LinearLayout linear145;
	private LinearLayout linear142;
	private ImageView imageview48;
	private TextView textview_c1;
	private ImageView imageview47;
	private TextView textview_c2;
	private ImageView imageview49;
	private TextView textview_c3;
	private ImageView imageview32;
	private TextView textview_c4;
	private WebView webview1;
	private LinearLayout linear115;
	private LinearLayout linear112;
	private ImageView imageview45;
	private TextView textview24;
	private LinearLayout linear84;
	private TextView textview17;
	private ProgressBar progressbar1;

	private TimerTask load;
	private DatabaseReference of = _firebase.getReference("Active_offerwall/data");
	private ChildEventListener _of_child_listener;
	private Intent o = new Intent();
	private TimerTask v;
	private DatabaseReference o178 = _firebase.getReference("offer_data_st");
	private ChildEventListener _o178_child_listener;
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

	private TimerTask wor;
	private Intent i_opnr = new Intent();
	private RequestNetwork rq;
	private RequestNetwork.RequestListener _rq_request_listener;
	private Intent n = new Intent();
	private DatabaseReference All_Users = _firebase.getReference("All_Users");
	private ChildEventListener _All_Users_child_listener;
	private SharedPreferences store_rr;
	private DatabaseReference other = _firebase.getReference("other/rf_tmp");
	private ChildEventListener _other_child_listener;
	private SharedPreferences rk;
	private TimerTask b_cnclrr;
	private Intent intent = new Intent();

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.offer_overview);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}

	private void initialize(Bundle _savedInstanceState) {
		linear141 = findViewById(R.id.linear141);
		linear_last = findViewById(R.id.linear_last);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear_cr2 = findViewById(R.id.linear_cr2);
		linear_cr1 = findViewById(R.id.linear_cr1);
		imageview1 = findViewById(R.id.imageview1);
		textview16 = findViewById(R.id.textview16);
		linear81 = findViewById(R.id.linear81);
		linear_cr3 = findViewById(R.id.linear_cr3);
		linear85 = findViewById(R.id.linear85);
		linear87 = findViewById(R.id.linear87);
		vscroll3 = findViewById(R.id.vscroll3);
		linear103 = findViewById(R.id.linear103);
		vscroll4 = findViewById(R.id.vscroll4);
		linear107 = findViewById(R.id.linear107);
		linear108 = findViewById(R.id.linear108);
		linear99 = findViewById(R.id.linear99);
		vscroll14 = findViewById(R.id.vscroll14);
		linear140 = findViewById(R.id.linear140);
		linear86 = findViewById(R.id.linear86);
		linear88 = findViewById(R.id.linear88);
		imageview_appLogo = findViewById(R.id.imageview_appLogo);
		linear90 = findViewById(R.id.linear90);
		linear146 = findViewById(R.id.linear146);
		textview_apk_info_1 = findViewById(R.id.textview_apk_info_1);
		textview_title = findViewById(R.id.textview_title);
		textview_rewadsAmount = findViewById(R.id.textview_rewadsAmount);
		imageview33 = findViewById(R.id.imageview33);
		linear109 = findViewById(R.id.linear109);
		textview22 = findViewById(R.id.textview22);
		textview23 = findViewById(R.id.textview23);
		linear148 = findViewById(R.id.linear148);
		textview21 = findViewById(R.id.textview21);
		linear147 = findViewById(R.id.linear147);
		linear3 = findViewById(R.id.linear3);
		linearcccccccc = findViewById(R.id.linearcccccccc);
		textview_apk_details_1 = findViewById(R.id.textview_apk_details_1);
		linear144 = findViewById(R.id.linear144);
		linear143 = findViewById(R.id.linear143);
		linear145 = findViewById(R.id.linear145);
		linear142 = findViewById(R.id.linear142);
		imageview48 = findViewById(R.id.imageview48);
		textview_c1 = findViewById(R.id.textview_c1);
		imageview47 = findViewById(R.id.imageview47);
		textview_c2 = findViewById(R.id.textview_c2);
		imageview49 = findViewById(R.id.imageview49);
		textview_c3 = findViewById(R.id.textview_c3);
		imageview32 = findViewById(R.id.imageview32);
		textview_c4 = findViewById(R.id.textview_c4);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		linear115 = findViewById(R.id.linear115);
		linear112 = findViewById(R.id.linear112);
		imageview45 = findViewById(R.id.imageview45);
		textview24 = findViewById(R.id.textview24);
		linear84 = findViewById(R.id.linear84);
		textview17 = findViewById(R.id.textview17);
		progressbar1 = findViewById(R.id.progressbar1);
		auth = FirebaseAuth.getInstance();
		rq = new RequestNetwork(this);
		store_rr = getSharedPreferences("store_rr", Activity.MODE_PRIVATE);
		rk = getSharedPreferences("rk", Activity.MODE_PRIVATE);

		imageview1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});

		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				_custom_1();
				super.onPageStarted(_param1, _param2, _param3);
			}

			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				finalUrl = _url;
				if (_url.contains("details?id=")) {
					//id code exicutive for play store launcher
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl));
					intent.putExtra(Intent.EXTRA_REFERRER, Uri.parse("android-app://com.android.vending"));
					startActivity(intent);

				}
				else {
					i_opnr.setAction(Intent.ACTION_VIEW);
					i_opnr.setData(Uri.parse(finalUrl));
					startActivity(i_opnr);
				}
				/*

bottomSheetDialog.cancel();


*/
				b_Cwncel = true;
				webview1.stopLoading();
				webview1.clearHistory();
				webview1.clearCache(true);
				_networks();
				/*

final String finalUrl = url;


*/
				if (_url.contains("details?id=")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Please don't install app from Any ither sources only Google play store ");
				}
				else {

				}
				/*

Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("U"));
intent.putExtra(Intent.EXTRA_REFERRER, Uri.parse("android-app://com.android.vending"));
startActivity(intent);



*/
				super.onPageFinished(_param1, _param2);
			}
		});

		linear84.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View _view) {
				textview17.setVisibility(View.GONE);
				progressbar1.setVisibility(View.VISIBLE);
				v = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								textview17.setVisibility(View.VISIBLE);
								progressbar1.setVisibility(View.GONE);
							}
						});
					}
				};
				_timer.schedule(v, (int)(2000));
				if (oLink.equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "offer not load....");
				}
				else {
					if (off_tmp.equals("")) {
						SketchwareUtil.showMessage(getApplicationContext(), "net Connection lost😭");
						getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
						getWindow().setStatusBarColor(0xFFFFFFFF);
					}
					else {
						_loadingdialog(true, "Loading...offer");
						newdta = new HashMap<>();
						newdta.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
						newdta.put("offer_name", textview_title.getText().toString());
						newdta.put("st", "Pending");
						newdta.put("offer_logo", logoss);
						o178.child(off_tmp.concat(FirebaseAuth.getInstance().getCurrentUser().getUid())).updateChildren(newdta);
						newdta.clear();
						wor = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										_loadingdialog(false, "");
										org_ur = oLink;
										modifiedUrl = oLink.replace("{click_id}", ref_code);
										modifiedUrlL = modifiedUrl.replace("{aff_id}", aff_id_getRef_cod);
										//work wirh open
										Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(modifiedUrlL));
										intent.setPackage("com.android.chrome");
										startActivity(intent);

										/*


										webview1.loadUrl(modifiedUrlL); //action  */
									}
								});
							}
						};
						_timer.schedule(wor, (int)(2000));
					}
				}
				/*

o.setAction(Intent.ACTION_VIEW);
o.setData(Uri.parse(oLink));
startActivity(o);
originalUrl = "https://indiancampaign.com/affiliate/c/?o=96&a=250&aff_click_id={click_id}&sub_aff_id={aff_id}";
newClickId = "click_3388383838282";
aff_id_getRef_cod = "aff_7228910100101010018237";
modifiedUrl = "";
modifiedUrlL = modifiedUrl.replace("{aff_id}", newAffId);
edittext1.setText(modifiedUrlL);
SketchwareUtil.showMessage(getApplicationContext(), modifiedUrlL);


*/
			}
		});

		_of_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				_loadingdialog(false, "");
				if (_childValue.containsKey("offer_id")) {
					if (_childValue.get("offer_id").toString().equals(off_tmp)) {
						if (_childValue.containsKey("offer_name")) {
							textview_title.setText(_childValue.get("offer_name").toString());
						}
						if (_childValue.containsKey("offer_link")) {
							oLink = _childValue.get("offer_link").toString();
						}
						if (_childValue.containsKey("offer_rs")) {
							textview_rewadsAmount.setText("Rewads ₹ ".concat(_childValue.get("offer_rs").toString()));
						}
						if (_childValue.containsKey("logo")) {
							logoss = _childValue.get("logo").toString();
							load = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											Glide.with(getApplicationContext()).load(Uri.parse(logoss)).into(imageview_appLogo);
										}
									});
								}
							};
							_timer.schedule(load, (int)(1000));
						}
						if (_childValue.containsKey("c1")) {
							textview_c1.setText(_childValue.get("c1").toString());
						}
						if (_childValue.containsKey("c2")) {
							textview_c2.setText(_childValue.get("c2").toString());
						}
						if (_childValue.containsKey("c3")) {
							textview_c3.setText(_childValue.get("c3").toString());
						}
						if (_childValue.containsKey("c4")) {
							textview_c4.setText(_childValue.get("c4").toString());
						}
					}
				}
			}

			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("offer_id")) {
					if (_childValue.get("offer_id").toString().equals(off_tmp)) {
						if (_childValue.containsKey("offer_name")) {
							textview_title.setText(_childValue.get("offer_name").toString());
						}
						if (_childValue.containsKey("offer_link")) {
							oLink = _childValue.get("offer_link").toString();
						}
						if (_childValue.containsKey("offer_rs")) {
							textview_rewadsAmount.setText("Rewads ₹ ".concat(_childValue.get("offer_rs").toString()));
						}
						if (_childValue.containsKey("logo")) {
							load = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo").toString())).into(imageview_appLogo);
										}
									});
								}
							};
							_timer.schedule(load, (int)(1000));
						}
						if (_childValue.containsKey("c1")) {
							textview_c1.setText(_childValue.get("c1").toString());
						}
						if (_childValue.containsKey("c2")) {
							textview_c2.setText(_childValue.get("c2").toString());
						}
						if (_childValue.containsKey("c3")) {
							textview_c3.setText(_childValue.get("c3").toString());
						}
						if (_childValue.containsKey("c4")) {
							textview_c4.setText(_childValue.get("c4").toString());
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
		of.addChildEventListener(_of_child_listener);

		_o178_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);

				//new
				if (_childKey.equals(off_tmp.concat(FirebaseAuth.getInstance().getCurrentUser().getUid()).trim())) {
					if (_childValue.containsKey("st")) {
						if (_childValue.get("st").toString().equals("Completed")) {
							SketchwareUtil.showMessage(getApplicationContext(), "You are alredy completed this offer chosed another now !!");
							finish();
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
		o178.addChildEventListener(_o178_child_listener);

		_rq_request_listener = new RequestNetwork.RequestListener() {
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
				n.setClass(getApplicationContext(), NetworkActivity.class);
				n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(n);
			}
		};



		_All_Users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("Refer_Code")) {
						ref_code = _childValue.get("Refer_Code").toString();
					}
					else {
						ref_code = store_rr.getString("rf", "");
					}
					if (_childValue.containsKey("ref_by")) {
						aff_id_getRef_cod = _childValue.get("ref_by").toString();
						rffa = _childValue.get("ref_by").toString();
					}
					else {
//						aff_id_getRef_cod = rk.getString("r_r", "");
						aff_id_getRef_cod = "default";
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
						ref_code = _childValue.get("Refer_Code").toString();
					}
					else {
						ref_code = store_rr.getString("rf", "");
					}
					if (_childValue.containsKey("ref_by")) {
						aff_id_getRef_cod = _childValue.get("ref_by").toString();
						aff_id_getRef_cod = _childValue.get("ref_by").toString();
					}
					else {
//						aff_id_getRef_cod = rk.getString("r_r", "");
						aff_id_getRef_cod = "default";
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

		_other_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("rf")) {
					rk.edit().putString("r_r", _childValue.get("ref_by").toString()).commit();
				}
				else {

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
		int[] colorsCRNHM = { Color.parseColor("#009688"), Color.parseColor("#009688") }; GradientDrawable CRNHM = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNHM);
		CRNHM.setCornerRadii(new float[]{(int)0,(int)0,(int)0,(int)0,(int)0,(int)0,(int)0,(int)0});
		CRNHM.setStroke((int) 0, Color.parseColor("#000000"));
		linear_cr1.setElevation((float) 5);
		linear_cr1.setBackground(CRNHM);

		//Paste this code in (add source directly block) asd block
		//Milz
		int[] colorsCRNWE = { Color.parseColor("#009688"), Color.parseColor("#009688") }; GradientDrawable CRNWE = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNWE);
		CRNWE.setCornerRadii(new float[]{(int)0,(int)0,(int)0,(int)0,(int)0,(int)0,(int)0,(int)0});
		CRNWE.setStroke((int) 0, Color.parseColor("#000000"));
		linear_cr2.setElevation((float) 5);
		linear_cr2.setBackground(CRNWE);

		//Paste this code in (add source directly block) asd block
		//Milz
		int[] colorsCRNTH = { Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF") }; GradientDrawable CRNTH = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNTH);
		CRNTH.setCornerRadii(new float[]{(int)35,(int)35,(int)35,(int)35,(int)0,(int)0,(int)0,(int)0});
		CRNTH.setStroke((int) 0, Color.parseColor("#000000"));
		linear_cr3.setElevation((float) 5);
		linear_cr3.setBackground(CRNTH);

		//Paste this code in (add source directly block) asd block
		//Milz
		_networks();
		webview1.setVisibility(View.GONE);
		progressbar1.setVisibility(View.GONE);
		off_tmp = getIntent().getStringExtra("tpt");
		_loadingdialog(true, "Loading ... offer");
		_rippleRoundStroke(linear84, "#FF246447", "#F5F5F5", 1, 0, "#FFFFFF");
		_ClickEffect(imageview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		b_Cwncel = false;
	}

	public void _ClickEffect(final View _view) {
		TypedValue typedValue = new TypedValue();

		getApplicationContext().getTheme().resolveAttribute(16843868, typedValue, true);

		_view.setBackgroundResource(typedValue.resourceId);

		_view.setClickable(true);
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


	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		GradientDrawable GG = new GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
				Color.parseColor("#" + _strokeclr.replace("#", "")));
		RippleDrawable RE = new RippleDrawable(new ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}


	public void _custom_1() {
		// Initialize BottomSheetDialog
		final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(OfferOverviewActivity.this);

		// Inflate the custom layout
		View bottomSheetView = getLayoutInflater().inflate(R.layout.custom, null);
		bottomSheetDialog.setContentView(bottomSheetView);

		// Retrieve the parent view to apply behavior
		View bottomSheetInternal = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);

		if (bottomSheetInternal != null) {
			BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheetInternal);
			behavior.setState(BottomSheetBehavior.STATE_EXPANDED); // Default state
			behavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO); // Optional
		}

		// Set background as transparent to customize appearance
		if (bottomSheetInternal != null) {
			bottomSheetInternal.setBackgroundResource(android.R.color.transparent);
		}

		// Find LinearLayout and TextView inside the custom layout
		LinearLayout linear1 = bottomSheetView.findViewById(R.id.linear1);
		TextView textview1 = bottomSheetView.findViewById(R.id.textview1);

		// Customize the LinearLayout's background
		linear1.setBackground(new GradientDrawable() {
			public GradientDrawable getIns(int radius, int color) {
				this.setCornerRadius(radius);
				this.setColor(color);
				return this;
			}
		}.getIns(54, 0xFFECEFF1));

		// Set a custom font for the TextView
		textview1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ttf1.ttf"), Typeface.NORMAL);

		// Prevent the BottomSheetDialog from being cancelable
		bottomSheetDialog.setCancelable(false);

		// Show the BottomSheetDialog
		bottomSheetDialog.show();

		// Timer task to handle auto-dismissal logic if needed
		b_cnclrr = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (b_Cwncel) {
							bottomSheetDialog.cancel();
							b_cnclrr.cancel();
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(b_cnclrr, 0, 1000);
	}


	public void _networks() {
		rq.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "CASH", _rq_request_listener);
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
