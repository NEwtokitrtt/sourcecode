package com.cashluto.rewad;

import android.os.*;
import android.view.*;

import androidx.annotation.*;
import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseApp;
import com.shobhitpuri.custombuttons.*;

public class Earn_FragmentActivity extends Fragment {
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.earn__fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
	}
	
	private void initializeLogic() {
	}
	
}
