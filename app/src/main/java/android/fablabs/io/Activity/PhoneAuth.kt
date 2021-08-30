package android.fablabs.io.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.tooling.preview.Preview
import android.fablabs.io.Activity.ui.theme.FabLabsioTheme
import android.fablabs.io.R
import android.fablabs.io.ui.theme.*
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import  androidx.compose.ui.unit.TextUnit

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.FirebaseException
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class PhoneAuth : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            FabLabsioTheme {
                PhoneAuthScreen()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FabLabsioTheme {
        PhoneAuthScreen()
    }
}




fun  checkphoneauth(phonevalue :String,context:Context,activty :Activity){
    Toast.makeText(context, "You just clicked a $phonevalue", Toast.LENGTH_LONG)
        .show()
    lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    auth=FirebaseAuth.getInstance()
    val firebaseAppCheck = FirebaseAppCheck.getInstance()
    firebaseAppCheck.installAppCheckProviderFactory(
        SafetyNetAppCheckProviderFactory.getInstance()
    )
     var number = "+91$phonevalue"
    lateinit var storedVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // This method is called when the verification is completed
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//            startActivity(Intent(applicationContext, MainActivity::class.java))
//            finish()
            Log.d("GFG" , "onVerificationCompleted Success")
        }

        // Called when verification is failed add log statement to see the exception
        override fun onVerificationFailed(e: FirebaseException) {
            Log.d("GFG" , "onVerificationFailed  $e")
        }

        // On code is sent by the firebase this method is called
        // in here we start a new activity where user can enter the OTP
        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.d("GFG","onCodeSent: $verificationId")
            storedVerificationId = verificationId
            resendToken = token

//             Start a new activity using intent
//             also send the storedVerificationId using intent
//             we will use this id to send the otp back to firebase
            val intent = Intent(context,OtpActivity::class.java)
            intent.putExtra("storedVerificationId",storedVerificationId)
            context.startActivity(intent)
            activty.finish()

        }
    }
    val options = PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(number) // Phone number to verify
        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
        .setActivity(activty) // Activity (for callback binding)
        .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
    Log.d("GFG" , "Auth started")

}




@Composable
fun PhoneTextField(phoneValue: MutableState<String>, onValueChange: (String, String) -> String = { _, new -> new }){
    val focusManager = LocalFocusManager.current
    val headingFont = Font(R.font.josefinsans_bold)
    OutlinedTextField(
        value = phoneValue.value,
        onValueChange =  {
            val value = onValueChange(phoneValue.value, it)
            phoneValue.value = value },
        label = { Text(text = "Enter Phone Number",color = Color.White) },
        placeholder = { Text(text = "Phone Number",color = Color.White ) },
        singleLine = true,
        textStyle = TextStyle(color = Color.White, fontFamily = FontFamily(headingFont),fontSize = 30.sp),
        modifier = Modifier.fillMaxWidth(0.8f),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone,imeAction = ImeAction.Done,),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Buttoncolor,
        unfocusedBorderColor = SecondaryColor
    )
    )
}

@Composable

fun PhoneAuthScreen(){
    val headingFont = Font(R.font.josefinsans_bold)
    Scaffold {
        Column(modifier = Modifier.fillMaxSize()
            .background(Purple200)) {
            Column(
                modifier = Modifier
                    .width(408.dp)
                    .height(180.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_homeimage),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth().padding(top=50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Fabregator 0.1",
                    fontSize = 36.sp,
                    color = Color(0xFFFFFFFF),

                    fontFamily = FontFamily(headingFont)
                )
                Text(
                    text = "''You dream it, we help you get it done''",
                    fontSize = 20.sp,
                    color = Color(0xFFFDDAE2)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=35.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val context = LocalContext.current
                val activity = LocalContext.current as Activity
                val phoneValue = remember { mutableStateOf("") }
                PhoneTextField(phoneValue,onValueChange = { old, new -> if (new.length > 10 || new.any { !it.isDigit() }) old else new })
                Button(
                    onClick = {
                           if (phoneValue.value.length == 10) {

                               checkphoneauth(phoneValue.value,context,activity)
                           }
                    }, modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(55.dp)
                        .padding(vertical = 4.dp)
                        .padding(top = 5.dp),
                    colors = buttonColors(backgroundColor = Buttoncolor),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Sign Up", fontSize = 15.sp)
                }
            }
        }



    }
}