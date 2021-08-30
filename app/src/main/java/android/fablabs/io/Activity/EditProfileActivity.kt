package android.fablabs.io.Activity

import android.content.Context
import android.fablabs.io.Activity.ui.theme.FabLabsioTheme
import android.fablabs.io.R
import android.fablabs.io.ui.theme.Buttoncolor
import android.fablabs.io.ui.theme.Purple200
import android.fablabs.io.ui.theme.SecondaryColor
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.CheckboxDefaults.colors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditProfileActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            FabLabsioTheme {
                val context = LocalContext.current
                LoadUI(context)


            }
        }


    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    FabLabsioTheme {
//        LoadUI(ROOT, DB)

    }
}


@Composable
fun LoadUI( context: Context) {


    Scaffold {
        var snackbarstate = remember {
            mutableStateOf(false)
        }
        var skilllist = mutableListOf<String>("", "", "", "", "")
        var otherskill = remember {
            mutableStateOf(false)
        }
        var otherskillname = remember {
            mutableStateOf("Other Skills")
        }
        val (showDialog, setShowDialog) = remember { mutableStateOf(false) }


        LazyColumn(
            modifier = Modifier
                .background(Purple200)
                .fillMaxSize()
        ) {

            item {

                if (otherskill.value) {
                    setShowDialog(true)
                    DialogBox(showDialog, setShowDialog, otherskill, otherskillname)
                }

                heading("Enter Profile Details")
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val UserName = remember { mutableStateOf("") }
                    Username(
                        UserName,
                        onValueChange = { old, new -> if (new.length > 15 || new.any { it.isDigit() }) old else new },
                        "Enter Your Name"
                    )
                    CheckedThumbColorSwitch()
                    heading("Select Your Skills")
                    if (snackbarstate.value) {
                        Snackbar(

                            action = {
                                Button(onClick = { snackbarstate.value = false },colors = ButtonDefaults.buttonColors(backgroundColor =Color.Red),
                                    shape = RoundedCornerShape(12.dp)) {
                                    Text("Close",color=Color.White)

                                }
                            },

                            modifier = Modifier.padding( all=20.dp),
                            shape = RoundedCornerShape(12.dp)

                        )
                        {
                            Text(text = " You can select maximum  of 15 skills only!")

                        }
                    }
                }
                Column {
                    var number_of_active = remember { mutableStateOf(0) }

                    SimpleCheckboxComponent(
                        "Project management and Web design",
                        number_of_active,
                        snackbarstate,
                        1,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Computer-aided design",
                        number_of_active,
                        snackbarstate,
                        2,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Computer-controlled cutting",
                        number_of_active,
                        snackbarstate,
                        3,
                        skilllist,
                        otherskill,
                        otherskillname,false                )
                    SimpleCheckboxComponent(
                        "Electronics production",
                        number_of_active,
                        snackbarstate,
                        4,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "3D scanning and printing",
                        number_of_active,
                        snackbarstate,
                        5,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Electronics design",
                        number_of_active,
                        snackbarstate,
                        6,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Computer-controlled machining",
                        number_of_active,
                        snackbarstate,
                        7,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Embedded programming",
                        number_of_active,
                        snackbarstate,
                        8,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Mechanical and machine design",
                        number_of_active,
                        snackbarstate,
                        9,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Input and output devices",
                        number_of_active,
                        snackbarstate,
                        10,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Molding and casting",
                        number_of_active,
                        snackbarstate,
                        11,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Networking and communications",
                        number_of_active,
                        snackbarstate,
                        12,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Interface and application programming",
                        number_of_active,
                        snackbarstate,
                        13,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "UI and UX Design",
                        number_of_active,
                        snackbarstate,
                        14,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Invention, intellectual property, and income",
                        number_of_active,
                        snackbarstate,
                        15,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Scale-up and production",
                        number_of_active,
                        snackbarstate,
                        16,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )
                    SimpleCheckboxComponent(
                        "Project Presentation and documentation",
                        number_of_active,
                        snackbarstate,
                        17,
                        skilllist,
                        otherskill,
                        otherskillname,false
                    )

                    if(otherskillname.value!="Other Skills"){
                        SimpleCheckboxComponent(
                            "${otherskillname.value}",
                            number_of_active,
                            snackbarstate,
                            18,
                            skilllist,
                            otherskill,
                            otherskillname,true
                        )
                    }else{
                        SimpleCheckboxComponent(
                            "${otherskillname.value}",
                            number_of_active,
                            snackbarstate,
                            18,
                            skilllist,
                            otherskill,
                            otherskillname,false
                        )
                    }


                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Button(
                        onClick = {
                            save_Data( skilllist,context)

                            Log.d("COUNTER", "$skilllist")

                        }, modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(55.dp)
                            .padding(vertical = 4.dp)
                            .padding(top = 5.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Buttoncolor),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Save", fontSize = 15.sp)
                    }
                }


            }
        }
    }

}


@Composable
fun Username(
    username: MutableState<String>,
    onValueChange: (String, String) -> String = { _, new -> new },
    Label: String
) {
    val focusManager = LocalFocusManager.current
    val headingFont = Font(R.font.josefinsans_bold)
    OutlinedTextField(
        value = username.value,
        onValueChange = {
            val value = onValueChange(username.value, it)
            username.value = value
        },
        label = { Text(text = "$Label", color = Color.White) },
        placeholder = { Text(text = "$Label", color = Color.White) },
        singleLine = true,
        textStyle = TextStyle(
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.quicksand_medium)),
            fontSize = 15.sp
        ),
        modifier = Modifier.fillMaxWidth(0.8f),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Buttoncolor,
            unfocusedBorderColor = SecondaryColor
        )

    )
}

@Composable
fun CheckedThumbColorSwitch() {
    Spacer(modifier = Modifier.requiredHeight(20.dp))
    val mRemember = remember { mutableStateOf(false) }
    Row {
        Text(text = "Are you a Workbench employee ?", color = Color.White)
        Switch(
            checked = mRemember.value,
            onCheckedChange = { mRemember.value = it },
            enabled = true,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Cyan
            ),

        )
    }

}


@Composable
fun SimpleCheckboxComponent(
    checkboxname: String,
    number_of_active: MutableState<Int>,
    snackbarstate: MutableState<Boolean>,
    i: Int,
    skilllist: MutableList<String>,
    otherskill: MutableState<Boolean>,
    otherskillname: MutableState<String>,
    b: Boolean,
) {
    val checkedState = remember { mutableStateOf(b) }
    Modifier.padding(start = 16.dp)
    val context = LocalContext.current

    Row (Modifier.clickable(onClick = {

        if (number_of_active.value < 15) {
            if (!checkedState.value) {
//                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                if (i == 18) {
                    Log.d("COUNTER", "c")
                    otherskill.value = true
                }else {
                    checkedState.value = true
                }
                number_of_active.value = number_of_active.value + 1
                skilllist[number_of_active.value - 1] = checkboxname

            } else {
                checkedState.value = false
                number_of_active.value = number_of_active.value - 1
                if (i == 18) {
                    Log.d("COUNTER", "d")
                    otherskillname.value ="Other Skills"
                }

            }
        }else{
            if(checkedState.value){
                checkedState.value = false
                number_of_active.value = number_of_active.value - 1
            }
        }

    })){

        Checkbox(
            checked = checkedState.value,
            modifier = Modifier.padding(start = 10.dp),
            onCheckedChange = {

                if (number_of_active.value < 15) {
                    if (checkedState.value == false) {
                        number_of_active.value = number_of_active.value + 1
                       // Toast.makeText(context, "${checkedState.value}", Toast.LENGTH_SHORT).show();
                        skilllist[number_of_active.value - 1] = checkboxname
                        if (i == 18) {
                            otherskill.value = true
                        }
                    } else {
                       // Toast.makeText(context, "FALSE", Toast.LENGTH_SHORT).show();
                        number_of_active.value = number_of_active.value - 1
                        if (i == 18) {
                            Log.d("COUNTER", "d")
                            otherskillname.value ="Other Skills"
                        }
                    }
                    Log.d("COUNTER", "${number_of_active.value}")
                    checkedState.value = it
                } else {
                    if (checkedState.value == false) {
                        snackbarstate.value = true
                        Handler().postDelayed({
                            snackbarstate.value = false
                        }, 3000)
                    } else {
                      //  Toast.makeText(context, "FALSE", Toast.LENGTH_SHORT).show();
                        number_of_active.value = number_of_active.value - 1
                        checkedState.value = it
                    }

                    Log.d("COUNTER", "${number_of_active.value}")



                }

            },
            colors = colors(
                uncheckedColor = Buttoncolor
            ),
        )
        Text(text = "$checkboxname", color = Color.White,modifier = Modifier.padding(start=10.dp))
    }

}


@Composable
fun heading(heading: String) {
    val headingFont = Font(R.font.josefinsans_bold)
    Spacer(modifier = Modifier.requiredHeight(20.dp))
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "$heading",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 15.dp),
            fontFamily = FontFamily(headingFont),
            fontSize = 30.sp
        )

    }


}

@Composable
fun DialogBox(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    otherskill: MutableState<Boolean>,
    otherskillname: MutableState<String>,
) {
    if (showDialog) {
        var text by rememberSaveable { mutableStateOf("") }
        AlertDialog(
            modifier = Modifier.padding(top = 20.dp),
            onDismissRequest = {
            },
            title = {

                TextField(
                    value = text,

                    onValueChange = {
                        text = it
                    },
                    label = { Text("Enter Other skill") },


                    )
            },


            confirmButton = {
                Button(
                    onClick = {
                        // Change the state to close the dialog
                        setShowDialog(false)
                        otherskill.value = false
                        otherskillname.value = "Other Skill : $text"
                    },
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        // Change the state to close the dialog
                        setShowDialog(false)
                        otherskill.value = false
                        otherskillname.value = "Other Skills"
                    },
                ) {
                    Text("Dismiss")
                }
            },
            backgroundColor = Buttoncolor,


            )
    }
}

fun save_Data(
    skilllist: MutableList<String>,
    context: Context
) {

    Log.d("onclick", "$skilllist")

    Log.d("onclick", "abcd")
    FirebaseApp.initializeApp(context)
     var DB = FirebaseDatabase.getInstance()
    var ROOT = DB.reference
    Log.d("onclick", "abcd")
    Log.d("onclick", "$ROOT")

    Log.d("onclick", "abcd")
    ROOT.child("users").child("userId").setValue("user").addOnSuccessListener()
    Log.d("onclick", "abcd")
//    ROOT.child("abc").child("userId").setValue("nj")
//        .addOnSuccessListener {
//            // Write was successful!
//            // ...
//            Log.d("onclick", "done")
//        }
//        .addOnFailureListener {
//            // Write failed
//            // ...
//            Log.d("onclick", "failed")
//
//        }
}

private fun <TResult> Task<TResult>.addOnSuccessListener() {
    TODO("Not yet implemented")
    Log.d("onclick", "done")
}
