package android.fablabs.io.LoginActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.fablabs.io.Activity.List_of_Data;
import android.fablabs.io.Activity.Variables;
import android.fablabs.io.R;
import android.fablabs.io.databinding.ActivityGoogleSigninBinding;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GoogleSignin extends AppCompatActivity {
    ActivityGoogleSigninBinding binding;
    private Button signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private int RC_SIGN_IN = 1;
    SharedPreferences sharedpreference;
    Animation animimgpage, bttone, bttwo, btthree, ltr;
    View bgprogress, bgprogresstop;
    FirebaseDatabase DB;
    DatabaseReference ROOT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityGoogleSigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(getApplicationContext());
        DB = FirebaseDatabase.getInstance();
        ROOT = DB.getReference();
        sharedpreference = getSharedPreferences(Variables.DEFAUlTDATA, Context.MODE_PRIVATE);
        if(sharedpreference.contains(Variables.FLOW))
        {
            String key = sharedpreference.getString(Variables.FLOW,"null");
            if(key.contains("ALREADYSIGNEDIN"))
            {

                binding.login.setText("Create a List");
            }

        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(GoogleSignin.this, gso);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = binding.login.getText().toString();
                if(text.equalsIgnoreCase("login")) {
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                }else {
                    Intent in = new Intent(GoogleSignin.this, List_of_Data.class);
                    startActivity(in);
                }
            }
        });
        binding.bgprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(GoogleSignin.this, List_of_Data.class);
                startActivity(in);
            }
        });
        animimgpage = AnimationUtils.loadAnimation(GoogleSignin.this, R.anim.animimgpage);
        bttone = AnimationUtils.loadAnimation(GoogleSignin.this, R.anim.bttone);
        bttwo = AnimationUtils.loadAnimation(GoogleSignin.this, R.anim.bttwo);
        btthree = AnimationUtils.loadAnimation(GoogleSignin.this, R.anim.btthree);
        ltr = AnimationUtils.loadAnimation(GoogleSignin.this, R.anim.ltr);
        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");
        Typeface Vidaloka = Typeface.createFromAsset(getAssets(), "fonts/Vidaloka.ttf");
        // customize font
       // binding.titlepage.setTypeface(Vidaloka);
        binding.subtitlepage.setTypeface(MLight);
        binding.login.setTypeface(MMedium);
        // export animate
        binding.imgpage.startAnimation(animimgpage);
        binding.titlepage.startAnimation(bttone);
        binding.subtitlepage.startAnimation(bttone);
        binding.login.startAnimation(btthree);
        binding.bgprogress.startAnimation(bttwo);
        binding.bgprogresstop.startAnimation(ltr);
        binding.bgprogress.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent in = new Intent(GoogleSignin.this, List_of_Data.class);
               startActivity(in);
           }
       });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
           // Toast.makeText(GoogleSignin.this,"Signed In Successfully",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        }
        catch (ApiException e){
            // Toast.makeText(GoogleSignin.this,e.toString(),Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {

        if (acct != null) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            mAuth.signInWithCredential(authCredential).addOnCompleteListener(GoogleSignin.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //Toast.makeText(BlurSignuporLogin.this, "Successful", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        String Uid =mAuth.getUid();
                      //  Toast.makeText(GoogleSignin.this,Uid,Toast.LENGTH_LONG).show();
                        updateUI(user ,Uid);
                    } else {
                       //.makeText(GoogleSignin.this, "Failed", Toast.LENGTH_SHORT).show();
                       // updateUI(null,null);
                    }
                }
            });
        }
        else{
           Toast.makeText(GoogleSignin.this, "acc failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser fUser,String UID){

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account !=  null){
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getServerAuthCode();
            Uri personPhoto = account.getPhotoUrl();
            //signInButton.setVisibility(View.INVISIBLE);
           SharedPreferences.Editor editor =  sharedpreference.edit();
            editor.putString(Variables.USERID,UID);
            editor.putString(Variables.USEREMAILID,personEmail);
            editor.putString(Variables.ACCOUNTCREATED,"YES");
            editor.putString(Variables.FLOW,"ALREADYSIGNEDIN");
            editor.commit();

           // ROOT.child(Variables.DBNAMEUSERS).child(UID).child(Variables.DISPLAYNAME).child(account.getDisplayName());
          //  ROOT.child(Variables.DBNAMEUSERS).child(UID).child(Variables.GIVENNAME).child(account.getGivenName());
          //  ROOT.child(Variables.DBNAMEUSERS).child(UID).child(Variables.PROFILEURl).child(account.getPhotoUrl().toString());
            ROOT.child(Variables.USERSINFODB).child(UID).child(Variables.USEREMAILID).setValue(personEmail);
            ROOT.child(Variables.USERSINFODB).child(UID).child(Variables.USERMODE).setValue("NORMAL");
            Intent in = new Intent(GoogleSignin.this, List_of_Data.class);
            startActivity(in);
        }

    }



}