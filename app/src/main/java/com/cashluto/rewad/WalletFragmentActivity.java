package com.cashluto.rewad;

import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.HashMap;

public class WalletFragmentActivity extends Fragment {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear5;
	private LinearLayout linear3;
	private ImageView imageview1;
	private TextView textview1;
	private LinearLayout linear33;
	private TextView textview4;
	private TextView textview18;
	private LinearLayout linear50;
	private TextView textview_coin;
	private LinearLayout linear4;
	private ViewPager viewpager1;
	private TabLayout tablayout1;
	private LinearLayout linear51;
	
	private FragFragmentAdapter frag;
	private DatabaseReference d = _firebase.getReference("All_Users");
	private ChildEventListener _d_child_listener;
	private FirebaseAuth Authorizatioopp;
	private OnCompleteListener<AuthResult> _Authorizatioopp_create_user_listener;
	private OnCompleteListener<AuthResult> _Authorizatioopp_sign_in_listener;
	private OnCompleteListener<Void> _Authorizatioopp_reset_password_listener;
	private OnCompleteListener<Void> Authorizatioopp_updateEmailListener;
	private OnCompleteListener<Void> Authorizatioopp_updatePasswordListener;
	private OnCompleteListener<Void> Authorizatioopp_emailVerificationSentListener;
	private OnCompleteListener<Void> Authorizatioopp_deleteUserListener;
	private OnCompleteListener<Void> Authorizatioopp_updateProfileListener;
	private OnCompleteListener<AuthResult> Authorizatioopp_phoneAuthListener;
	private OnCompleteListener<AuthResult> Authorizatioopp_googleSignInListener;
	
	private SharedPreferences gb;
	private Intent a_with = new Intent();
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.wallet_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		linear5 = _view.findViewById(R.id.linear5);
		linear3 = _view.findViewById(R.id.linear3);
		imageview1 = _view.findViewById(R.id.imageview1);
		textview1 = _view.findViewById(R.id.textview1);
		linear33 = _view.findViewById(R.id.linear33);
		textview4 = _view.findViewById(R.id.textview4);
		textview18 = _view.findViewById(R.id.textview18);
		linear50 = _view.findViewById(R.id.linear50);
		textview_coin = _view.findViewById(R.id.textview_coin);
		linear4 = _view.findViewById(R.id.linear4);
		viewpager1 = _view.findViewById(R.id.viewpager1);
		tablayout1 = _view.findViewById(R.id.tablayout1);
		linear51 = _view.findViewById(R.id.linear51);
		frag = new FragFragmentAdapter(getContext().getApplicationContext(), getActivity().getSupportFragmentManager());
		Authorizatioopp = FirebaseAuth.getInstance();
		gb = getContext().getSharedPreferences("gb", Activity.MODE_PRIVATE);
		
		textview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				a_with.setClass(getContext().getApplicationContext(), WithdrawalActivity.class);
				a_with.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(a_with);
			}
		});
		
		_d_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("balance")) {
						textview_coin.setText("₹ ".concat(_childValue.get("balance").toString()));
						gb.edit().putString("gb_bal", "₹ ".concat(_childValue.get("balance").toString())).commit();
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
						if (_childValue.containsKey("balance")) {
								textview_coin.setText("₹ ".concat(_childValue.get("balance").toString()));
								gb.edit().putString("gb_bal", "₹ ".concat(_childValue.get("balance").toString())).commit();
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
		d.addChildEventListener(_d_child_listener);
		
		Authorizatioopp_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Authorizatioopp_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Authorizatioopp_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Authorizatioopp_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Authorizatioopp_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		Authorizatioopp_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Authorizatioopp_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_Authorizatioopp_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_Authorizatioopp_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_Authorizatioopp_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		linear2.setElevation((float)8);
		linear5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)40, 0xFF246447));
		linear5.setElevation((float)8);
		linear5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)40, 0xFF246447));
		tablayout1.setupWithViewPager(viewpager1);
		frag.setTabCount(2);
		viewpager1.setAdapter(frag);
		tablayout1.setTabTextColors(0xFF616161, 0xFF000000);
		tablayout1.setSelectedTabIndicatorColor(0xFF000000);
		imageview1.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
		linear3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)16, 0xFFFFFFFF));
		linear3.setElevation((float)12);
		linear3.requestFocus();
		textview4.requestFocus();
		textview4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)4, 0xFF4A148C));
	}
	
	public class FragFragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;
		
		public FragFragmentAdapter(Context context, FragmentManager manager) {
			super(manager);
			this.context = context;
		}
		
		public void setTabCount(int tabCount) {
			this.tabCount = tabCount;
		}
		
		@Override
		public int getCount() {
			return tabCount;
		}
		
		@Override
		public CharSequence getPageTitle(int _position) {
			if (_position == 0) {
					return "Tranjection history";
			}
			if (_position == 1) {
					return "Redeem History";
			}
			return null;
		}
		
		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
					return new TrsFragmentActivity();
			}
			if (_position == 1) {
					return new RedemFragmentActivity();
			}
			return null;
		}
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
