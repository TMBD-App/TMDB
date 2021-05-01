package com.itb.tmbdmobileapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.itb.tmbdmobileapp.Database.DatabaseHelper;
import com.itb.tmbdmobileapp.Modelos.User;
import com.itb.tmbdmobileapp.R;

public class LoginFragment extends Fragment implements View.OnClickListener  {
    private TextInputLayout layoutPassword, layoutUsername;
    private TextInputEditText editTextPassword, editTextEmail;
    private FirebaseAuth firebaseAuth;
    private boolean loginSuccess = false;
    User u;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton login = view.findViewById(R.id.register_button_login);
        MaterialButton register = view.findViewById(R.id.register_button_register);
        layoutPassword = view.findViewById(R.id.login_textInputLayout_password);
        layoutUsername = view.findViewById(R.id.login_textInputLayout_email);
        editTextPassword = view.findViewById(R.id.login_textInputEditText_password);
        editTextEmail = view.findViewById(R.id.login_textInputEditText_email);

        firebaseAuth= FirebaseAuth.getInstance();


        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        ResetearInputLayout();
        boolean flag = true;
        NavDirections navDirections = null;
        switch (v.getId()) {
            case R.id.register_button_login:
                flag = canGoToWellcome();
                navDirections = LoginFragmentDirections.loginToWellcome();
                break;
            case R.id.register_button_register:
                navDirections = LoginFragmentDirections.loginToRegister();
                break;
        }
        if (flag) Navigation.findNavController(v).navigate(navDirections);
    }


    public boolean canGoToWellcome() {
        signInUser();
        boolean passwordCheck = !editTextPassword.getText().toString().equals("");
        boolean usernameCheck = !editTextEmail.getText().toString().equals("");

        if (!passwordCheck) InputLayoutError(layoutPassword, "Introduce la Contraseña");
        if (!usernameCheck) InputLayoutError(layoutUsername, "Introduce el Usuario");

        return passwordCheck && usernameCheck && loginSuccess;
    }

    private void InputLayoutError(TextInputLayout layout, String mensajeError) {
        layout.setEnabled(true);
        layout.setError(mensajeError);
    }

    private void ResetearInputLayout() {
        layoutUsername.setError("");
        layoutPassword.setError("");
    }


    private void signInUser(){
        try{
            if(!editTextEmail.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty()){
                if (firebaseAuth != null){
                    firebaseAuth.signInWithEmailAndPassword(editTextEmail.getText().toString(), editTextPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            loginSuccess = true;

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            loginSuccess = false;
                        }
                    });
                }
            }else{
                Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}