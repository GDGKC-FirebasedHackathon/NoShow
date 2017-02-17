package com.mma.noshow_admin;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.mma.noshow_admin.databinding.ActivityAdminHomeBinding;

public class AdminHomeActivity extends Activity {

    private ActivityAdminHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_home);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    public void onClick(View view)
    {
         if(view == binding.btnProfile){
             startActivity(new Intent(this, AdminProfileActivity.class));
         }

    }
}
