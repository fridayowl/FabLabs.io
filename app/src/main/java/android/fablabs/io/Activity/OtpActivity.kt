package android.fablabs.io.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.fablabs.io.Activity.ui.theme.FabLabsioTheme
import android.fablabs.io.ui.theme.Buttoncolor
import android.fablabs.io.ui.theme.Purple200
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OtpActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var auth: FirebaseAuth
        auth=FirebaseAuth.getInstance()
        val storedVerificationId= intent.getStringExtra("storedVerificationId")
        super.onCreate(savedInstanceState)
        setContent {
            FabLabsioTheme {
                 OtpScreen( )
            }
        }
    }
}


@Preview(showBackground =true)
@Composable
fun DefaultPreview2() {

    FabLabsioTheme {
       OtpScreen()
       
    }
}




@Composable
fun OtpTextField(State: MutableState<String>, onValueChange: (String, String) -> String = { _, new -> new }, focusRequester: FocusRequester, nextFocusRequester: FocusRequester, modifier: Modifier){
//   val  State = rememberSaveable {mutableStateOf("")}
    val color by remember { mutableStateOf(Black) }
    OutlinedTextField(
        modifier = modifier.focusRequester( focusRequester),
        value = State.value,
        onValueChange = {val value = onValueChange(State.value, it)
            State.value = value},

        textStyle = MaterialTheme.typography.h4.copy(textAlign = TextAlign.Center),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next),

        keyboardActions = KeyboardActions(
            onNext = { nextFocusRequester.requestFocus()  }
        )
    )

}

@Composable

fun OtpScreen(){
    Scaffold {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Purple200),verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            var length :Int =5
            val code: List<Char> by remember {
                mutableStateOf(listOf())
            }
            val focusRequesters: List<FocusRequester> = remember {
                val temp = mutableListOf<FocusRequester>()
                repeat(length) {
                    temp.add(FocusRequester())
                }
                temp
            }
            val context = LocalContext.current
            val state = rememberSaveable { mutableStateOf("") }
            val state1 = rememberSaveable { mutableStateOf("") }
            val state2 = rememberSaveable { mutableStateOf("") }
            val state3 = rememberSaveable { mutableStateOf("") }
            val state4 = rememberSaveable { mutableStateOf("") }
            Row {

                val focusReferences = List(6) { FocusRequester() }
                OtpTextField(state,onValueChange = { old, new -> if (new.length > 1 || new.any { !it.isDigit() }) old else new },focusRequesters[0], focusRequesters[1],
                    Modifier
                        .weight(1f)
                        .padding(vertical = 2.dp))
                Spacer(modifier = Modifier.width(16.dp))
                OtpTextField(state1,onValueChange = { old, new -> if (new.length > 1 || new.any { !it.isDigit() }) old else new },focusRequesters[1], focusRequesters[2],
                    Modifier
                        .weight(1f)
                        .padding(vertical = 2.dp))
                Spacer(modifier = Modifier.width(16.dp))
                OtpTextField(state2,onValueChange = { old, new -> if (new.length > 1 || new.any { !it.isDigit() }) old else new },focusRequesters[2], focusRequesters[3],
                    Modifier
                        .weight(1f)
                        .padding(vertical = 2.dp))
                Spacer(modifier = Modifier.width(16.dp))
                OtpTextField(state3,onValueChange = { old, new -> if (new.length > 1 || new.any { !it.isDigit() }) old else new },focusRequesters[3], focusRequesters[4],
                    Modifier
                        .weight(1f)
                        .padding(vertical = 2.dp))
                Spacer(modifier = Modifier.width(16.dp))
                OtpTextField(state4,onValueChange = { old, new -> if (new.length > 1 || new.any { !it.isDigit() }) old else new },focusRequesters[4], focusRequesters[0],
                    Modifier
                        .weight(1f)
                        .padding(vertical = 2.dp))
                Spacer(modifier = Modifier.width(16.dp))

            }
            Button(onClick =  {
                OtpValidation(state1.value,context)

            }, modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(55.dp)
                .padding(vertical = 4.dp)
                .padding(top = 5.dp) ,
                colors= ButtonDefaults.buttonColors(backgroundColor = Buttoncolor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Sign Up", fontSize = 15.sp)
            }
        }
    }
}

fun OtpValidation(value:String, context:Context){
    Toast.makeText(context, "$value", Toast.LENGTH_LONG).show()
    if(value.isNotEmpty()){
        val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(
            storedVerificationId.toString(), otp)
        signInWithPhoneAuthCredential(credential)
    }else{
        Toast.makeText(context,"Enter OTP", Toast.LENGTH_SHORT).show()
    }

}






