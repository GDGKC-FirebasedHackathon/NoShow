package com.mma.noshow_admin;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.mma.noshow_admin.databinding.ActivityLoginBinding;

import controller.AdminController;
import controller.LoginController;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener
{

	private  final int RC_SIGN_IN = 1;
	private ActivityLoginBinding binding;

	private  GoogleApiClient mGoogleApiClient;
	@Override
		protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			binding = DataBindingUtil.setContentView(this, R.layout.activity_login);


			GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
					.requestIdToken(getString(R.string.default_web_client_id))
					.requestEmail()
					.build();

		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.enableAutoManage(this, this)
				.addApi(Auth.GOOGLE_SIGN_IN_API, gso)
				.build();
		//init
		LoginController.firebaseInit(new FirebaseAuth.AuthStateListener() {
			@Override
			public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
				FirebaseUser user = firebaseAuth.getCurrentUser();
				if (user != null) {
					// User is signed in
					Log.d("ASDF", "onAuthStateChanged:signed_in:" + user.getUid());
					LoginController.uid = user.getUid();
					finish();
					startActivity(new Intent(LoginActivity.this,AdminHomeActivity.class));
					Toast.makeText(LoginActivity.this, "123123", Toast.LENGTH_SHORT).show();
				} else {
					// User is signed outg
					Log.d("ASDF", "onAuthStateChanged:sined_out");
				}
			}
		});

		Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
		startActivityForResult(signInIntent, RC_SIGN_IN);
	}



	@Override
	public void onStart() {
		super.onStart();
//		LoginController.addAuthListener();
	}

	@Override
	public void onStop() {
		super.onStop();
//		LoginController.removeAuthListener();

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
		if (requestCode == RC_SIGN_IN) {
			GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
			Log.d("ASDF", "handleSignInResult:" + result.isSuccess());
			if (result.isSuccess()) {
				GoogleSignInAccount acct = result.getSignInAccount();
		//		LoginController.firebaseAuthWithGoogle(this,acct);
			}
		}
	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
	{

	}
}
