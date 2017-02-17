package com.mma.noshow_admin;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mma.noshow_admin.databinding.ActivityAdminProfileBinding;

public class AdminProfileActivity extends AppCompatActivity {

    private ActivityAdminProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_profile);
    }
    public void onClick(View view)
    {
        if (view == binding.btnBack)
        {
            finish();
        }
        else if (view == binding.btnEdit)
        {
            String name = binding.txtName.getText().toString();
            String location = binding.txtLocation.getText().toString();
            String phone = binding.txtPhone.getText().toString();
            updateProfile(name, location, phone);
        }
    }

    private void updateProfile(String name, String location, String phone)
    {
        // TODO 실시간 데이터베이스에 업데이트
    }
}
