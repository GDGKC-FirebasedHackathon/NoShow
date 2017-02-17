package controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by yunho on 2017. 2. 18..
 */

public class LoginController {

    private static FirebaseAuth.AuthStateListener mAuthListener;
    private static FirebaseAuth mAuth;
    public static String uid;
    public static void firebaseInit(FirebaseAuth.AuthStateListener dd){
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = dd;
    }
    public static void firebaseAuthWithGoogle(Activity act, final GoogleSignInAccount acct) {
        Log.d("ASDF", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(act, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("ASDF", "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("ASDF", "signInWithCredential", task.getException());

                        }
                        else
                        {
                            Log.d("ASDF", "token: " + acct.getIdToken());
                        }
                        // ...
                    }
                });
    }
    public static void addAuthListener(){
        mAuth.addAuthStateListener(mAuthListener);
    }
    public static void removeAuthListener(){
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public static String getUid() { return uid;}
}
