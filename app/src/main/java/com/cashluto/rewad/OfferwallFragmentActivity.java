package com.cashluto.rewad;

import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import com.google.firebase.database.ValueEventListener;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class OfferwallFragmentActivity extends Fragment {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double n1 = 0;
	private HashMap<String, Object> map1 = new HashMap<>();
	private HashMap<String, Object> map2 = new HashMap<>();
	private double n1s = 0;
	private double ccff = 0;
	private boolean a = false;
	private boolean b = false;
	private double rept_chking = 0;
	private double one = 0;
	private boolean stockertd = false;
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> refer_list_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap1 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> refer_list_map1 = new ArrayList<>();
	private ArrayList<String> uidSet = new ArrayList<>();
	private ArrayList<String> presonal_filters = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private TextView textview2;
	private LinearLayout linear2;
	private ProgressBar progressbar1;
	private TextView textview3;
	
	private Intent active = new Intent();
	private FirebaseAuth ath;
	private OnCompleteListener<AuthResult> _ath_create_user_listener;
	private OnCompleteListener<AuthResult> _ath_sign_in_listener;
	private OnCompleteListener<Void> _ath_reset_password_listener;
	private OnCompleteListener<Void> ath_updateEmailListener;
	private OnCompleteListener<Void> ath_updatePasswordListener;
	private OnCompleteListener<Void> ath_emailVerificationSentListener;
	private OnCompleteListener<Void> ath_deleteUserListener;
	private OnCompleteListener<Void> ath_updateProfileListener;
	private OnCompleteListener<AuthResult> ath_phoneAuthListener;
	private OnCompleteListener<AuthResult> ath_googleSignInListener;
	
	private DatabaseReference All_Users = _firebase.getReference("All_Users");
	private ChildEventListener _All_Users_child_listener;
	private TimerTask lo;
	private DatabaseReference actVio = _firebase.getReference("Active_offerwall/data");
	private ChildEventListener _actVio_child_listener;
	private DatabaseReference offer_data_st = _firebase.getReference("offer_data_st");
	private ChildEventListener _offer_data_st_child_listener;
	private TimerTask after;
	private TimerTask uu;
	private TimerTask dTu;
	private TimerTask rp;
	private TimerTask omasd;
	private Intent abcdd = new Intent();
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.offerwall_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		listview1 = _view.findViewById(R.id.listview1);
		textview2 = _view.findViewById(R.id.textview2);
		linear2 = _view.findViewById(R.id.linear2);
		progressbar1 = _view.findViewById(R.id.progressbar1);
		textview3 = _view.findViewById(R.id.textview3);
		ath = FirebaseAuth.getInstance();
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
			}
		});
		
		_All_Users_child_listener = new ChildEventListener() {
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
		All_Users.addChildEventListener(_All_Users_child_listener);
		
		_actVio_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				listview1.setVisibility(View.VISIBLE);
				linear2.setVisibility(View.GONE);
				//new
//				refer_list_map.clear();
//				listview1.setAdapter(new Listview1Adapter(refer_list_map1));
//				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
//				_Refer_List();
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
				_Refer_List();
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		actVio.addChildEventListener(_actVio_child_listener);
		
		_offer_data_st_child_listener = new ChildEventListener() {
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
		offer_data_st.addChildEventListener(_offer_data_st_child_listener);
		
		ath_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		ath_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		ath_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		ath_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		ath_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		ath_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		ath_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_ath_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_ath_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_ath_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		_Refer_List();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
	}
	public void _Refer_List() {
		_NewApiUpdater();
		listmap.clear();
		refer_list_map.clear();
		actVio.addListenerForSingleValueEvent(new ValueEventListener() {
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
				n1 = 0;
				for(int _repeat14 = 0; _repeat14 < (int)(listmap.size()); _repeat14++) {
					if (listmap.get((int)n1).get("st").toString().equals("active")) {
						map1 = new HashMap<>();
						if (listmap.get((int)n1).containsKey("offer_name")) {
							map1.put("offer_name", listmap.get((int)n1).get("offer_name").toString());
						}
						if (listmap.get((int)n1).containsKey("offer_link")) {
							map1.put("offer_link", listmap.get((int)n1).get("offer_link").toString());
						}
						if (listmap.get((int)n1).containsKey("logo")) {
							map1.put("logo", listmap.get((int)n1).get("logo").toString());
						}
						if (listmap.get((int)n1).containsKey("offer_rs")) {
							map1.put("offer_rs", listmap.get((int)n1).get("offer_rs").toString());
						}
						if (listmap.get((int)n1).containsKey("c1")) {
							map1.put("c1", listmap.get((int)n1).get("c1").toString());
						}
						if (listmap.get((int)n1).containsKey("c2")) {
							map1.put("c2", listmap.get((int)n1).get("c2").toString());
						}
						if (listmap.get((int)n1).containsKey("c3")) {
							map1.put("c3", listmap.get((int)n1).get("c3").toString());
						}
						if (listmap.get((int)n1).containsKey("c4")) {
							map1.put("c4", listmap.get((int)n1).get("c4").toString());
						}
						if (listmap.get((int)n1).containsKey("csm")) {
							map1.put("csm", listmap.get((int)n1).get("csm").toString());
						}
						if (listmap.get((int)n1).containsKey("offer_id")) {
							map1.put("offer_id", listmap.get((int)n1).get("offer_id").toString());
						}
						refer_list_map.add(map1);
					}
					if (presonal_filters.contains(listmap.get((int)n1).get("offer_id").toString().concat("Completed"))) {
						refer_list_map.remove(map1);
					}
					else {
						
					}
					n1++;
				}
				Collections.reverse(refer_list_map);
				listview1.setAdapter(new Listview1Adapter(refer_list_map));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
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
	
	
	public void _NewApiUpdater() {
		listmap1.clear();
		refer_list_map1.clear();
		offer_data_st.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				listmap1 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						listmap1.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				n1s = 0;
				for(int _repeat14 = 0; _repeat14 < (int)(listmap1.size()); _repeat14++) {
					if (listmap1.get((int)n1s).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						map2 = new HashMap<>();
						if (listmap1.get((int)n1s).containsKey("comment")) {
							map2.put("comment", listmap1.get((int)n1s).get("comment").toString());
						}
						if (listmap1.get((int)n1s).containsKey("offer_logo")) {
							map2.put("offer_logo", listmap1.get((int)n1s).get("offer_logo").toString());
						}
						if (listmap1.get((int)n1s).containsKey("st")) {
							map2.put("st", listmap1.get((int)n1s).get("st").toString());
						}
						if (listmap1.get((int)n1s).containsKey("uid")) {
							map2.put("uid", listmap1.get((int)n1s).get("uid").toString());
						}
						if (listmap1.get((int)n1s).containsKey("offer_id")) {
							map2.put("offer_id", listmap1.get((int)n1s).get("offer_id").toString());
							presonal_filters.add(listmap1.get((int)n1s).get("offer_id").toString().concat(listmap1.get((int)n1s).get("st").toString()));
						}
						refer_list_map1.add(map2);
					}
					n1s++;
				}
				Collections.reverse(refer_list_map1);
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	
	public void _glideFromURL(final String _url, final ImageView _imageview) {
		
		Glide.with(getContext().getApplicationContext())
		.load(_url)
		.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
		.error(R.drawable.app_icon2)
		.into(_imageview);
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
				_view = _inflater.inflate(R.layout.offer_cs, null);
			}
			
			final LinearLayout linear136 = _view.findViewById(R.id.linear136);
			final ImageView imageview_logo = _view.findViewById(R.id.imageview_logo);
			final ScrollView vscroll22 = _view.findViewById(R.id.vscroll22);
			final LinearLayout linear151 = _view.findViewById(R.id.linear151);
			final TextView textview45 = _view.findViewById(R.id.textview45);
			final LinearLayout linear153 = _view.findViewById(R.id.linear153);
			final LinearLayout linear139 = _view.findViewById(R.id.linear139);
			final TextView textview21 = _view.findViewById(R.id.textview21);
			final TextView textview_rs_1 = _view.findViewById(R.id.textview_rs_1);
			
			linear136.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)18, (int)4, 0xFF246447, 0xFFDBFCF5));
			/*

imageview_logo.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)13, (int)1, 0xFFEEEEEE, 0xFFFFFFFF));
 

*/
			if (_data.get((int)_position).containsKey("offer_name")) {
				textview45.setText(_data.get((int)_position).get("offer_name").toString());
			}
			if (_data.get((int)_position).containsKey("offer_link")) {
				/*
coin.setText(_data.get((int)_position).get("offer_link").toString());
*/
			}
			if (_data.get((int)_position).containsKey("offer_rs")) {
				textview_rs_1.setText(_data.get((int)_position).get("offer_rs").toString());
			}
			if (_data.get((int)_position).containsKey("logo")) {
				_glideFromURL(_data.get((int)_position).get("logo").toString(), imageview_logo);
				/*
Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("logo").toString())).into(imageview_logo);
*/
			}
			if (_data.get((int)_position).containsKey("c1")) {
				/*
name_short.setText(_data.get((int)_position).get("c1").toString());
*/
			}
			if (_data.get((int)_position).containsKey("c2")) {
				/*
name_short.setText(_data.get((int)_position).get("c2").toString());
*/
			}
			if (_data.get((int)_position).containsKey("c3")) {
				/*
name_short.setText(_data.get((int)_position).get("c3").toString());
*/
			}
			if (_data.get((int)_position).containsKey("c4")) {
				/*
name_short.setText(_data.get((int)_position).get("c4").toString());
*/
			}
			//ccc
			if (_data.get((int)_position).containsKey("csm")) {

				textview2.setText(_data.get((int)_position).get("csm").toString());

			}

			//end c
			linear136.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					_rippleRoundStroke(linear136, "#FFDBFCF5", "#F5F5F5", 0, 0, "#F44336");
					linear136.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)18, (int)4, 0xFF246447, 0xFFDBFCF5));
					//srt
					//apna code
					if (textview2.getText().toString().equals("on")) {
						abcdd.setClass(getContext().getApplicationContext(), LaunchActivity.class);
						abcdd.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(abcdd);
					}
					else {
						//
						if (_data.get((int)_position).containsKey("offer_id")) {
							active.setClass(getContext().getApplicationContext(), OfferOverviewActivity.class);
							active.putExtra("tpt", _data.get((int)_position).get("offer_id").toString());
							startActivity(active);
						}
						else {
							SketchwareUtil.showMessage(getContext().getApplicationContext(), "data network error ");
						}

						//endi

					}

//					//
//					if (_data.get((int)_position).containsKey("offer_id")) {
//						active.setClass(getContext().getApplicationContext(), OfferOverviewActivity.class);
//						active.putExtra("tpt", _data.get((int)_position).get("offer_id").toString());
//						startActivity(active);
//					}
//					else {
//						SketchwareUtil.showMessage(getContext().getApplicationContext(), "data network error ");
//					}
//
//					//endi
				}
			});
			
			return _view;
		}
	}
}
