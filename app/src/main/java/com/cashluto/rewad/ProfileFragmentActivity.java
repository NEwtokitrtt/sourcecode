package com.cashluto.rewad;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.*;
import android.net.Uri;
import android.os.*;
import android.view.*;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;

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

import de.hdodenhof.circleimageview.*;

import java.util.HashMap;

public class ProfileFragmentActivity extends Fragment {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String work = "";
	private String privacy_po = "";
	private String term_cd = "";
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout how_to_work;
	private LinearLayout linear4;
	private LinearLayout wallet;
	private LinearLayout linear5;
	private LinearLayout group;
	private LinearLayout linear6;
	private LinearLayout contact;
	private LinearLayout linear7;
	private LinearLayout share;
	private LinearLayout linear8;
	private LinearLayout terms;
	private LinearLayout linear9;
	private LinearLayout privacy;
	private LinearLayout linear10;
	private CircleImageView imageview1;
	private TextView names;
	private TextView mailid;
	private ImageView imageview2;
	private TextView textview3;
	private ImageView imageview3;
	private TextView textview4;
	private ImageView imageview4;
	private TextView textview5;
	private ImageView imageview5;
	private TextView textview6;
	private ImageView imageview6;
	private TextView textview7;
	private ImageView imageview7;
	private TextView textview8;
	private ImageView imageview8;
	private TextView textview9;
	
	private FirebaseAuth a;
	private OnCompleteListener<AuthResult> _a_create_user_listener;
	private OnCompleteListener<AuthResult> _a_sign_in_listener;
	private OnCompleteListener<Void> _a_reset_password_listener;
	private OnCompleteListener<Void> a_updateEmailListener;
	private OnCompleteListener<Void> a_updatePasswordListener;
	private OnCompleteListener<Void> a_emailVerificationSentListener;
	private OnCompleteListener<Void> a_deleteUserListener;
	private OnCompleteListener<Void> a_updateProfileListener;
	private OnCompleteListener<AuthResult> a_phoneAuthListener;
	private OnCompleteListener<AuthResult> a_googleSignInListener;
	
	private DatabaseReference All_Users = _firebase.getReference("All_Users");
	private ChildEventListener _All_Users_child_listener;
	private Intent yt = new Intent();
	private DatabaseReference code_oi = _firebase.getReference("code_oi");
	private ChildEventListener _code_oi_child_listener;
	private Intent tld = new Intent();
	private Intent f = new Intent();
	private Intent ppr = new Intent();
	private Intent ttr = new Intent();
	private Intent help = new Intent();
	private SharedPreferences imgo;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.profile_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		vscroll1 = _view.findViewById(R.id.vscroll1);
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		linear3 = _view.findViewById(R.id.linear3);
		how_to_work = _view.findViewById(R.id.how_to_work);
		linear4 = _view.findViewById(R.id.linear4);
		wallet = _view.findViewById(R.id.wallet);
		linear5 = _view.findViewById(R.id.linear5);
		group = _view.findViewById(R.id.group);
		linear6 = _view.findViewById(R.id.linear6);
		contact = _view.findViewById(R.id.contact);
		linear7 = _view.findViewById(R.id.linear7);
		share = _view.findViewById(R.id.share);
		linear8 = _view.findViewById(R.id.linear8);
		terms = _view.findViewById(R.id.terms);
		linear9 = _view.findViewById(R.id.linear9);
		privacy = _view.findViewById(R.id.privacy);
		linear10 = _view.findViewById(R.id.linear10);
		imageview1 = _view.findViewById(R.id.imageview1);
		names = _view.findViewById(R.id.names);
		mailid = _view.findViewById(R.id.mailid);
		imageview2 = _view.findViewById(R.id.imageview2);
		textview3 = _view.findViewById(R.id.textview3);
		imageview3 = _view.findViewById(R.id.imageview3);
		textview4 = _view.findViewById(R.id.textview4);
		imageview4 = _view.findViewById(R.id.imageview4);
		textview5 = _view.findViewById(R.id.textview5);
		imageview5 = _view.findViewById(R.id.imageview5);
		textview6 = _view.findViewById(R.id.textview6);
		imageview6 = _view.findViewById(R.id.imageview6);
		textview7 = _view.findViewById(R.id.textview7);
		imageview7 = _view.findViewById(R.id.imageview7);
		textview8 = _view.findViewById(R.id.textview8);
		imageview8 = _view.findViewById(R.id.imageview8);
		textview9 = _view.findViewById(R.id.textview9);
		a = FirebaseAuth.getInstance();
		imgo = getContext().getSharedPreferences("imgo", Activity.MODE_PRIVATE);
		
		how_to_work.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (work.equals("")) {
					
				}
				else {
					yt.setAction(Intent.ACTION_VIEW);
					yt.setData(Uri.parse(work));
					startActivity(yt);
				}
			}
		});
		
		wallet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Cluck on wallet to view the balance ");
			}
		});
		
		group.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				tld.setAction(Intent.ACTION_VIEW);
				tld.setData(Uri.parse("https://t.me/cash_lootoo"));
				startActivity(tld);
			}
		});
		
		contact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				help.setAction(Intent.ACTION_VIEW);
				help.setData(Uri.parse("https://t.me/cash_luto_1"));
				startActivity(help);
			}
		});
		
		share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		terms.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (term_cd.equals("")) {
					
				}
				else {
					ttr.setAction(Intent.ACTION_VIEW);
					ttr.setData(Uri.parse(term_cd));
					startActivity(ttr);
				}
			}
		});
		
		privacy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (privacy_po.equals("")) {
					
				}
				else {
					ppr.setAction(Intent.ACTION_VIEW);
					ppr.setData(Uri.parse(privacy_po));
					startActivity(ppr);
				}
			}
		});
		
		_All_Users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					/*
if (_childValue.containsKey("pro_img")) {
Glide.with(getContext().getApplicationContext()).load(Uri.parse(_childValue.get("pro_img").toString())).into(imageview1);
}
*/
					if (_childValue.containsKey("name")) {
						names.setText(_childValue.get("name").toString());
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
						/*
if (_childValue.containsKey("pro_img")) {
Glide.with(getContext().getApplicationContext()).load(Uri.parse(_childValue.get("pro_img").toString())).into(imageview1);
}
*/
						if (_childValue.containsKey("name")) {
								names.setText(_childValue.get("name").toString());
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
		
		_code_oi_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("109")) {
					if (_childValue.containsKey("privacy")) {
						privacy_po = _childValue.get("privacy").toString();
					}
					if (_childValue.containsKey("term")) {
						term_cd = _childValue.get("term").toString();
					}
					if (_childValue.containsKey("how_work")) {
						work = _childValue.get("how_work").toString();
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
		code_oi.addChildEventListener(_code_oi_child_listener);
		
		a_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		a_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		a_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		a_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		a_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		a_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		a_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_a_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_a_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_a_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		mailid.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
		_Icon_Colour(imageview2, "#616161");
		_Icon_Colour(imageview3, "#616161");
		_Icon_Colour(imageview4, "#616161");
		_Icon_Colour(imageview5, "#616161");
		_Icon_Colour(imageview6, "#616161");
		_Icon_Colour(imageview7, "#616161");
		_Icon_Colour(imageview8, "#616161");
		_rippleRoundStroke(how_to_work, "#FFFFFF", "#F5F5F5", 0, 0, "#F44336");
		_rippleRoundStroke(wallet, "#FFFFFF", "#F5F5F5", 0, 0, "#F44336");
		_rippleRoundStroke(group, "#FFFFFF", "#F5F5F5", 0, 0, "#F44336");
		_rippleRoundStroke(contact, "#FFFFFF", "#F5F5F5", 0, 0, "#F44336");
		_rippleRoundStroke(share, "#FFFFFF", "#F5F5F5", 0, 0, "#F44336");
		_rippleRoundStroke(terms, "#FFFFFF", "#F5F5F5", 0, 0, "#F44336");
		_rippleRoundStroke(privacy, "#FFFFFF", "#F5F5F5", 0, 0, "#F44336");
		if (imgo.getString("path", "").equals("")) {
			
		}
		else {
			Glide.with(getContext().getApplicationContext()).load(Uri.parse(imgo.getString("path", ""))).into(imageview1);
		}
	}
	
	public void _Icon_Colour(final ImageView _iconview, final String _colour) {
		_iconview.getDrawable().setColorFilter(Color.parseColor(_colour), PorterDuff.Mode.SRC_IN);
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
	
}
