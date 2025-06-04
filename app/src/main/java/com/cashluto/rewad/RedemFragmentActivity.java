package com.cashluto.rewad;

import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import android.widget.*;
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

public class RedemFragmentActivity extends Fragment {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double n1 = 0;
	private HashMap<String, Object> map1 = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> refer_list_map = new ArrayList<>();
	
	private LinearLayout linear1;
	private boolean pot = true;
	private ListView listview1;
	private LinearLayout linear2;
	private LinearLayout linear6;
	private ProgressBar progressbar1;
	private LinearLayout linear5;
	private TextView textview1;
	private TextView textview2;
	
	private DatabaseReference redeem = _firebase.getReference("redeem");
	private ChildEventListener _redeem_child_listener;
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
		View _view = _inflater.inflate(R.layout.redem_fragment, _container, false);
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
		pot = false;
		
		_redeem_child_listener = new ChildEventListener() {
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
		redeem.addChildEventListener(_redeem_child_listener);
		
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
		_Refer_List();
	}
	
	public void _Refer_List() {
		listmap.clear();
		refer_list_map.clear();
		redeem.addListenerForSingleValueEvent(new ValueEventListener() {
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
					if (listmap.get((int)n1).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						map1 = new HashMap<>();
						if (listmap.get((int)n1).containsKey("amount")) {
							map1.put("amount", listmap.get((int)n1).get("amount").toString());
						}
						if (listmap.get((int)n1).containsKey("date")) {
							map1.put("date", listmap.get((int)n1).get("date").toString());
						}
						if (listmap.get((int)n1).containsKey("upi")) {
							map1.put("upi", listmap.get((int)n1).get("upi").toString());
						}
						if (listmap.get((int)n1).containsKey("st")) {
							map1.put("st", listmap.get((int)n1).get("st").toString());
						}
						refer_list_map.add(map1);
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


	@Override
	public void onResume() {
		super.onResume();
		if(pot) {
			_Refer_List();
		}


	}

	@Override
	public void onPause()  {
		super.onPause();
		pot = true;
		refer_list_map.clear();
		listview1.setAdapter(new Listview1Adapter(refer_list_map));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
				_view = _inflater.inflate(R.layout.redem, null);
			}
			
			final LinearLayout linear158 = _view.findViewById(R.id.linear158);
			final LinearLayout base_ui = _view.findViewById(R.id.base_ui);
			final LinearLayout linear_profile = _view.findViewById(R.id.linear_profile);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView amount = _view.findViewById(R.id.amount);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final TextView name = _view.findViewById(R.id.name);
			final TextView id_up = _view.findViewById(R.id.id_up);
			final TextView date = _view.findViewById(R.id.date);
			final TextView status = _view.findViewById(R.id.status);
			final ImageView imageview13 = _view.findViewById(R.id.imageview13);
			
			base_ui.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)4, (int)2, 0xFF246447, 0xFFDBFCF5));
			id_up.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/montserrat_semibold.ttf"), 1);
			amount.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/montserrat_bold.ttf"), 0);
			linear_profile.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFF000000));
			if (_data.get((int)_position).containsKey("upi")) {
				id_up.setText(_data.get((int)_position).get("upi").toString());
			}
			if (_data.get((int)_position).containsKey("date")) {
				date.setText(_data.get((int)_position).get("date").toString());
			}
			if (_data.get((int)_position).containsKey("amount")) {
				amount.setText("₹ ".concat(_data.get((int)_position).get("amount").toString()));
			}
			if (_data.get((int)_position).containsKey("st")) {
				status.setText(_data.get((int)_position).get("st").toString());
				if (_data.get((int)_position).get("st").toString().equals("Pending")) {
					linear2.setBackgroundDrawable(getResources().getDrawable(R.drawable.style2));
				}
				if (_data.get((int)_position).get("st").toString().equals("Sussces")) {
					linear2.setBackgroundDrawable(getResources().getDrawable(R.drawable.style1));
				}
			}
			
			return _view;
		}
	}
}
