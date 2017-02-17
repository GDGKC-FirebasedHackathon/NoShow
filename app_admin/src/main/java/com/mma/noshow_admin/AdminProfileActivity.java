package com.mma.noshow_admin;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
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
        if(view == binding.btnBack) {
            finish();
        }else if(view==binding.btnEdit){
            if(binding.txtLocation.getInputType() == InputType.TYPE_NULL){
                Log.d("input","(null)input type : "+InputType.TYPE_NULL+" and modified :" + InputType.TYPE_CLASS_TEXT);
                binding.txtLocation.setInputType(InputType.TYPE_CLASS_TEXT);
                binding.txtName.setInputType(InputType.TYPE_CLASS_TEXT);
            }else {
                Log.d("input","input type : "+InputType.TYPE_CLASS_TEXT+" and modified :" + InputType.TYPE_NULL);
                binding.txtLocation.setInputType(InputType.TYPE_NULL);
                binding.txtName.setInputType(InputType.TYPE_NULL);
            }
        }
    }
}
