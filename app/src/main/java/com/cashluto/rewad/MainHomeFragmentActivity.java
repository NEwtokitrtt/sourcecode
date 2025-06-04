package com.cashluto.rewad;

import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.*;
import android.graphics.drawable.*;
import android.net.Uri;
import android.os.*;
import android.view.*;
import android.view.View;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MainHomeFragmentActivity extends Fragment {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double slidpos = 0;
	private String Refer_Code_ = "";
	private HashMap<String, Object> Refer_Code_uesr = new HashMap<>();
	private HashMap<String, Object> v = new HashMap<>();
	private String REFER_CODE_REAL = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<String> list = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear15;
	private LinearLayout linear_sliders;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private CircleImageView circleimageview1;
	private TextView textview1;
	private LinearLayout linear6;
	private TextView textview2;
	private LinearLayout linear16;
	private ViewPager viewpager1;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private TextView textview3;
	private TextView textview4;
	private LinearLayout linear13;
	private LinearLayout linear14;
	
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
	
	private DatabaseReference All_User = _firebase.getReference("All_Users");
	private ChildEventListener _All_User_child_listener;
	private Intent demo = new Intent();
	private DatabaseReference refer_codes = _firebase.getReference("refer_codes");
	private ChildEventListener _refer_codes_child_listener;
	private Intent tl = new Intent();
	private Intent lrn = new Intent();
	private SharedPreferences imgo;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.main_home_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		linear15 = _view.findViewById(R.id.linear15);
		linear_sliders = _view.findViewById(R.id.linear_sliders);
		linear9 = _view.findViewById(R.id.linear9);
		linear10 = _view.findViewById(R.id.linear10);
		linear3 = _view.findViewById(R.id.linear3);
		linear4 = _view.findViewById(R.id.linear4);
		circleimageview1 = _view.findViewById(R.id.circleimageview1);
		textview1 = _view.findViewById(R.id.textview1);
		linear6 = _view.findViewById(R.id.linear6);
		textview2 = _view.findViewById(R.id.textview2);
		linear16 = _view.findViewById(R.id.linear16);
		viewpager1 = _view.findViewById(R.id.viewpager1);
		linear11 = _view.findViewById(R.id.linear11);
		linear12 = _view.findViewById(R.id.linear12);
		textview3 = _view.findViewById(R.id.textview3);
		textview4 = _view.findViewById(R.id.textview4);
		linear13 = _view.findViewById(R.id.linear13);
		linear14 = _view.findViewById(R.id.linear14);
		auth = FirebaseAuth.getInstance();
		imgo = getContext().getSharedPreferences("imgo", Activity.MODE_PRIVATE);
		
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		linear11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linear13.setVisibility(View.VISIBLE);
				linear14.setVisibility(View.GONE);
				linear11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)13, 0xFF15B8A7));
				linear12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)4, 0xFF15B8A7, 0xFFFFFFFF));
				textview4.setTextColor(0xFF246447);
				textview3.setTextColor(0xFFFFFFFF);
			}
		});
		
		linear12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linear13.setVisibility(View.GONE);
				linear14.setVisibility(View.VISIBLE);
				linear12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)13, 0xFF15B8A7));
				linear11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)4, 0xFF15B8A7, 0xFFFFFFFF));
				textview3.setTextColor(0xFF246447);
				textview4.setTextColor(0xFFFFFFFF);
			}
		});
		
		linear13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_All_User_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					/*
if (_childValue.containsKey("pro_img")) {
Glide.with(getContext().getApplicationContext()).load(Uri.parse(_childValue.get("pro_img").toString())).into(circleimageview1);
}
*/
					if (_childValue.containsKey("balance")) {
						textview2.setText("₹ ".concat(_childValue.get("balance").toString()));
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
Glide.with(getContext().getApplicationContext()).load(Uri.parse(_childValue.get("pro_img").toString())).into(circleimageview1);
}
*/
						if (_childValue.containsKey("balance")) {
								textview2.setText("₹ ".concat(_childValue.get("balance").toString()));
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
		All_User.addChildEventListener(_All_User_child_listener);
		
		_refer_codes_child_listener = new ChildEventListener() {
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
		refer_codes.addChildEventListener(_refer_codes_child_listener);
		
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
		_NewBlockSlider();
		viewpager1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)4, (int)2, 0xFF246447, Color.TRANSPARENT));
		linear6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF246447));
		linear11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)13, 0xFF15B8A7));
		linear12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)4, 0xFF15B8A7, 0xFFFFFFFF));
		if (imgo.getString("path", "").equals("")) {
			
		}
		else {
			Glide.with(getContext().getApplicationContext()).load(Uri.parse(imgo.getString("path", ""))).into(circleimageview1);
		}
		Fragment fragmenth1 = new OfferwallFragmentActivity();
		getActivity().getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.linear13, fragmenth1)
		.commit();
		Fragment fragmenth2 = new OfferwallStatusFragmentActivity();
		getActivity().getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.linear14, fragmenth2)
		.commit();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		t.cancel();
	}
	public void _NewBlockSlider() {
		// Initialize listmap and populate it with data
		listmap = new ArrayList<>();
		
		{
			    HashMap<String, Object> _item = new HashMap<>();
			    _item.put("a", "b");
			    listmap.add(_item);
		}
		
		{
			    HashMap<String, Object> _item = new HashMap<>();
			    _item.put("c", "d");
			    listmap.add(_item);
		}
		
		{
			    HashMap<String, Object> _item = new HashMap<>();
			    _item.put("e", "f");
			    listmap.add(_item);
		}
		
		{
			    HashMap<String, Object> _item = new HashMap<>();
			    _item.put("g", "h");
			    listmap.add(_item);
		}
		
		{
			    HashMap<String, Object> _item = new HashMap<>();
			    _item.put("i", "j");
			    listmap.add(_item);
		}
		
		// Set adapter for the ViewPager
		viewpager1.setAdapter(new Viewpager1Adapter(listmap));
		
		// Set up TimerTask for auto-sliding
		t = new TimerTask() {
			    @Override
			    public void run() {
				        if (MainHomeFragmentActivity.this != null) { // Check if the FragmentActivity is not null
					            getActivity().runOnUiThread(new Runnable() {
						                @Override
						                public void run() {
							                    if (viewpager1 != null) { // Check if viewpager1 is initialized
								                        slidpos = viewpager1.getCurrentItem() + 1;
								                        if (slidpos > 4) {
									                            slidpos = 0;
									                        }
								                        viewpager1.setCurrentItem((int) slidpos);
								                    }
							                }
						            });
					        }
				    }
		};
		_timer.scheduleAtFixedRate(t, 4000, 4000);
		
		/*
{
HashMap<String, Object> _item = new HashMap<>();
_item.put("a", "b");
listmap.add(_item);
}

{
HashMap<String, Object> _item = new HashMap<>();
_item.put("c", "d");
listmap.add(_item);
}

{
HashMap<String, Object> _item = new HashMap<>();
_item.put("e", "f");
listmap.add(_item);
}

{
HashMap<String, Object> _item = new HashMap<>();
_item.put("g", "h");
listmap.add(_item);
}

{
HashMap<String, Object> _item = new HashMap<>();
_item.put("i", "j");
listmap.add(_item);
}

viewpager1.setAdapter(new Viewpager1Adapter(listmap));
t = new TimerTask() {
@Override
public void run() {
getActivity().runOnUiThread(new Runnable() {
@Override
public void run() {
slidpos = viewpager1.getCurrentItem() + 1;
if (slidpos > 4) {
slidpos = 0;
viewpager1.setCurrentItem((int)slidpos);
}
else {
viewpager1.setCurrentItem((int)slidpos);
}
}
});
}
};
_timer.scheduleAtFixedRate(t, (int)(4000), (int)(4000));
*/
	}
	
	public class Viewpager1Adapter extends PagerAdapter {
		
		Context _context;
		ArrayList<HashMap<String, Object>> _data;
		
		public Viewpager1Adapter(Context _ctx, ArrayList<HashMap<String, Object>> _arr) {
			_context = _ctx;
			_data = _arr;
		}
		
		public Viewpager1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_context = getContext().getApplicationContext();
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public boolean isViewFromObject(View _view, Object _object) {
			return _view == _object;
		}
		
		@Override
		public void destroyItem(ViewGroup _container, int _position, Object _object) {
			_container.removeView((View) _object);
		}
		
		@Override
		public int getItemPosition(Object _object) {
			return super.getItemPosition(_object);
		}
		
		@Override
		public CharSequence getPageTitle(int pos) {
			// Use the Activity Event (onTabLayoutNewTabAdded) in order to use this method
			return "page " + String.valueOf(pos);
		}
		
		@Override
		public Object instantiateItem(ViewGroup _container,  final int _position) {
			View _view = LayoutInflater.from(_context).inflate(R.layout.slid, _container, false);
			
			final LinearLayout cardview1 = _view.findViewById(R.id.cardview1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			if (_position == 0) {
				imageview1.setImageResource(R.drawable.img);
				imageview1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						tl.setAction(Intent.ACTION_VIEW);
						tl.setData(Uri.parse("https://t.me/cash_lootoo"));
						startActivity(tl);
					}
				});
			}
			if (_position == 1) {
				imageview1.setImageResource(R.drawable.indis);
				imageview1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						
					}
				});
			}
			if (_position == 2) {
				imageview1.setImageResource(R.drawable.off);
				imageview1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						tl.setAction(Intent.ACTION_VIEW);
						tl.setData(Uri.parse("https://t.me/cash_lootoo"));
						startActivity(tl);
					}
				});
			}
			if (_position == 3) {
				imageview1.setImageResource(R.drawable.paiso);
				imageview1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						
					}
				});
			}
			if (_position == 4) {
				imageview1.setImageResource(R.drawable.indis);
				imageview1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						
					}
				});
			}
			
			_container.addView(_view);
			return _view;
		}
	}
}
