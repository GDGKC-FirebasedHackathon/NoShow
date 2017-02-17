package com.mma.noshow_admin;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.mma.noshow_admin.databinding.ActivityAdminHomeBinding;

public class AdminHomeActivity extends Activity
{
	private ActivityAdminHomeBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_home);
	}

	public void onClick(View view)
	{
		if (view == binding.btnProfile)
		{
			startActivity(new Intent(this, AdminProfileActivity.class));
		}

		else if (view == binding.btnFood)
		{
			startActivity(new Intent(this, AdminFoodsActivity.class));
		}
	}
}
