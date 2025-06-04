package com.cashluto.rewad;

import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;

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
import com.google.firebase.database.ValueEventListener;
import com.shobhitpuri.custombuttons.*;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TrsFragmentActivity extends Fragment {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double n1 = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private double limit = 0;
	private String key = "";
	private boolean papau = false;
	
	private ArrayList<HashMap<String, Object>> notiMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap1 = new ArrayList<>();
	private ArrayList<String> list = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private LinearLayout linear2;
	private LinearLayout linear6;
	private ProgressBar progressbar1;
	private LinearLayout linear5;
	private TextView textview1;
	private TextView textview2;
	
	private DatabaseReference his = _firebase.getReference("histry_of_task");
	private ChildEventListener _his_child_listener;
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
	
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.trs_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		listview1 = _view.findViewById(R.id.listview1);
		linear2 = _view.findViewById(R.id.linear2);
		linear6 = _view.findViewById(R.id.linear6);
		progressbar1 = _view.findViewById(R.id.progressbar1);
		linear5 = _view.findViewById(R.id.linear5);
		textview1 = _view.findViewById(R.id.textview1);
		textview2 = _view.findViewById(R.id.textview2);
		auth = FirebaseAuth.getInstance();
		
		_his_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				linear6.setVisibility(View.GONE);
				listview1.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
listmap.clear();
				listmap1.clear();
				his.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
								listmap = new ArrayList<>();
								try {
										GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
										for (DataSnapshot _data : _dataSnapshot.getChildren()) {
												HashMap<String, Object> _map = _data.getValue(_ind);
												listmap.add(_map);
										}
								}
								catch (Exception _e) {
										_e.printStackTrace();
								}
								Collections.reverse(listmap);
								n1 = 0;
								for(int _repeat28 = 0; _repeat28 < (int)(listmap.size()); _repeat28++) {
										if (listmap.get((int)n1).get("mobile").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
												map = new HashMap<>();
												if (listmap.get((int)n1).containsKey("comment")) {
														map.put("comment", listmap.get((int)n1).get("comment").toString());
												}
												if (listmap.get((int)n1).containsKey("date")) {
														map.put("date", listmap.get((int)n1).get("date").toString());
												}
												if (listmap.get((int)n1).containsKey("amount")) {
														map.put("amount", listmap.get((int)n1).get("amount").toString());
												}
												if (listmap.get((int)n1).containsKey("order_id")) {
														map.put("order_id", listmap.get((int)n1).get("order_id").toString());
												}
												if (listmap.get((int)n1).containsKey("status")) {
														map.put("status", listmap.get((int)n1).get("status").toString());
												}
												listmap1.add(map);
												n1++;
										}
										else {
												n1++;
										}
								}
								for(int _repeat63 = 0; _repeat63 < (int)(listmap1.size()); _repeat63++) {
										list.add("r");
								}
								listview1.setAdapter(new Listview1Adapter(listmap1));
								((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
				});
				linear6.setVisibility(View.GONE);
				listview1.setVisibility(View.VISIBLE);
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
		his.addChildEventListener(_his_child_listener);
		
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
		papau = false;
		listmap.clear();
		listmap1.clear();
		his.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				listmap = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						listmap.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				Collections.reverse(listmap);
				n1 = 0;
				for(int _repeat28 = 0; _repeat28 < (int)(listmap.size()); _repeat28++) {
					if (listmap.get((int)n1).get("mobile").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						map = new HashMap<>();
						if (listmap.get((int)n1).containsKey("comment")) {
							map.put("comment", listmap.get((int)n1).get("comment").toString());
						}
						if (listmap.get((int)n1).containsKey("date")) {
							map.put("date", listmap.get((int)n1).get("date").toString());
						}
						if (listmap.get((int)n1).containsKey("amount")) {
							map.put("amount", listmap.get((int)n1).get("amount").toString());
						}
						if (listmap.get((int)n1).containsKey("order_id")) {
							map.put("order_id", listmap.get((int)n1).get("order_id").toString());
						}
						if (listmap.get((int)n1).containsKey("status")) {
							map.put("status", listmap.get((int)n1).get("status").toString());
						}
						listmap1.add(map);
						n1++;
					}
					else {
						n1++;
					}
				}
				for(int _repeat63 = 0; _repeat63 < (int)(listmap1.size()); _repeat63++) {
					list.add("r");
				}
				listview1.setAdapter(new Listview1Adapter(listmap1));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (papau) {
			listmap.clear();
			listmap1.clear();
			his.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
							listmap = new ArrayList<>();
							try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
											HashMap<String, Object> _map = _data.getValue(_ind);
											listmap.add(_map);
									}
							}
							catch (Exception _e) {
									_e.printStackTrace();
							}
							Collections.reverse(listmap);
							n1 = 0;
							for(int _repeat28 = 0; _repeat28 < (int)(listmap.size()); _repeat28++) {
									if (listmap.get((int)n1).get("mobile").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
											map = new HashMap<>();
											if (listmap.get((int)n1).containsKey("comment")) {
													map.put("comment", listmap.get((int)n1).get("comment").toString());
											}
											if (listmap.get((int)n1).containsKey("date")) {
													map.put("date", listmap.get((int)n1).get("date").toString());
											}
											if (listmap.get((int)n1).containsKey("amount")) {
													map.put("amount", listmap.get((int)n1).get("amount").toString());
											}
											if (listmap.get((int)n1).containsKey("order_id")) {
													map.put("order_id", listmap.get((int)n1).get("order_id").toString());
											}
											if (listmap.get((int)n1).containsKey("status")) {
													map.put("status", listmap.get((int)n1).get("status").toString());
											}
											listmap1.add(map);
											n1++;
									}
									else {
											n1++;
									}
							}
							for(int _repeat63 = 0; _repeat63 < (int)(listmap1.size()); _repeat63++) {
									list.add("r");
							}
							listview1.setAdapter(new Listview1Adapter(listmap1));
							((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
			});
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		papau = true;
		listmap1.clear();
		listview1.setAdapter(new Listview1Adapter(listmap1));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
	}
	public void _round_corner_and_ripple(final View _view, final double _radius, final double _shadow, final String _color, final boolean _ripple) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setElevation((int)_shadow);
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setBackground(gd);
			_view.setElevation((int)_shadow);
		}
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.hsdhh, null);
			}
			
			final androidx.cardview.widget.CardView cardview2 = _view.findViewById(R.id.cardview2);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final TextView title = _view.findViewById(R.id.title);
			final TextView coin = _view.findViewById(R.id.coin);
			final TextView time = _view.findViewById(R.id.time);
			final TextView status = _view.findViewById(R.id.status);
			
			linear2.requestFocus();
			linear2.setElevation((float)10);
			linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)1, 0xFF880E4F, 0xFFFFFFFF));
			linear5.setVisibility(View.GONE);
			progressbar1.setVisibility(View.GONE);
			if (_data.get((int)_position).containsKey("comment")) {
				title.setText(_data.get((int)_position).get("comment").toString());
			}
			if (_data.get((int)_position).containsKey("amount")) {
				coin.setText(_data.get((int)_position).get("amount").toString().concat(" ₹"));
			}
			if (_data.get((int)_position).containsKey("date")) {
				time.setText(_data.get((int)_position).get("date").toString());
			}
			if (_data.get((int)_position).containsKey("status")) {
				status.setText(_data.get((int)_position).get("status").toString());
				if (_data.get((int)_position).get("status").toString().equals("Complete")) {
					status.setTextColor(0xFF4CAF50);
				}
				if (_data.get((int)_position).get("status").toString().equals("Pending")) {
					status.setTextColor(0xFFFFC107);
				}
				if (_data.get((int)_position).get("status").toString().equals("Failed")) {
					status.setTextColor(0xFFF44336);
				}
			}
			
			return _view;
		}
	}
}
