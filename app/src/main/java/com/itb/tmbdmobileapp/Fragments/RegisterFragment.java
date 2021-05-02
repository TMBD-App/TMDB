package com.itb.tmbdmobileapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.itb.tmbdmobileapp.Modelos.Movie;
import com.itb.tmbdmobileapp.Modelos.People;
import com.itb.tmbdmobileapp.Modelos.User;
import com.itb.tmbdmobileapp.R;

import java.util.ArrayList;
import java.util.List;


public class RegisterFragment extends Fragment implements View.OnClickListener {
    MaterialButton login, register;
    private TextInputLayout layoutPassword, layoutUsername, layoutEmail, layoutRepeatPassword;
    private TextInputEditText editTextPassword, editTextUsername, editTextEmail, editTextRepeatPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        login = view.findViewById(R.id.register_button_login);
        register = view.findViewById(R.id.register_button_register);
        layoutPassword = view.findViewById(R.id.register_textInputLayout_password);
        layoutUsername = view.findViewById(R.id.register_textInputLayout_username);
        layoutEmail = view.findViewById(R.id.register_textInputLayout_email);
        layoutRepeatPassword = view.findViewById(R.id.register_textInputLayout_repeatpassword);
        editTextPassword = view.findViewById(R.id.register_textInputEditText_password);
        editTextUsername = view.findViewById(R.id.register_textInputEditText_username);
        editTextEmail = view.findViewById(R.id.register_textInputEditText_email);
        editTextRepeatPassword = view.findViewById(R.id.register_textInputEditText_repeatpassword);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        boolean flag = true;
        ResetearInputLayout();
        if (v.getId() == R.id.register_button_register) flag = canGoToLogin();

        if (flag) {
            createUser();
            NavDirections navDirections = RegisterFragmentDirections.registerToLogin();
            Navigation.findNavController(v).navigate(navDirections);
        }
    }

    public boolean canGoToLogin() {
        boolean passwordCheck = !editTextPassword.getText().toString().equals("");
        boolean usernameCheck = !editTextUsername.getText().toString().equals("");
        boolean emailCheck = !editTextEmail.getText().toString().equals("");

        if (!passwordCheck) InputLayoutError(layoutPassword, "Introduce la Contraseña");
        if (!usernameCheck) InputLayoutError(layoutUsername, "Introduce el Usuario");
        if (!emailCheck) InputLayoutError(layoutEmail, "Introduce el email");

        return passwordCheck && usernameCheck && emailCheck;
    }

    private void InputLayoutError(TextInputLayout layout, String mensajeError) {
        layout.setEnabled(true);
        layout.setError(mensajeError);
    }

    private void ResetearInputLayout() {
        layoutUsername.setError("");
        layoutPassword.setError("");
        layoutEmail.setError("");
    }

    public void createUser(){
        try{
            if(!editTextEmail.getText().toString().isEmpty()&&
                    !editTextPassword.getText().toString().isEmpty() &&
                    !editTextRepeatPassword.getText().toString().isEmpty()){
                if (editTextPassword.getText().toString().equals(editTextRepeatPassword.getText().toString())){
                    firebaseAuth.createUserWithEmailAndPassword(editTextEmail.getText().toString(), editTextPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            User u = new User(firebaseAuth.getCurrentUser().getUid(), editTextUsername.getText().toString(),firebaseAuth.getCurrentUser().getEmail());
                            DatabaseHelper.insert(u);
                            Toast.makeText(getContext(), "User created successfully", Toast.LENGTH_SHORT).show();

                            if(firebaseAuth.getCurrentUser()!= null){
                                firebaseAuth.signOut();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getContext(), "Passwords are not the same", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}