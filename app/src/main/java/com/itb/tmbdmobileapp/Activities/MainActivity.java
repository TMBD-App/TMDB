package com.itb.tmbdmobileapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.itb.tmbdmobileapp.R;
import com.itb.tmbdmobileapp.Retrofit.ApiClient;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static ApiClient apiClient = new ApiClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiClient.crearCliente();
    }
}