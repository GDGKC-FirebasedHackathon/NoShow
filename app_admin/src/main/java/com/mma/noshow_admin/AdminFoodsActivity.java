package com.mma.noshow_admin;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mma.noshow_admin.databinding.ActivityAdminFoodsBinding;
import com.mma.noshow_admin.databinding.ItemAdminFoodsBinding;

import model.Food;

public class AdminFoodsActivity extends AppCompatActivity
{
	private ActivityAdminFoodsBinding binding;
	private ArrayAdapter<Food> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_foods);

		adapter = new ArrayAdapter<Food>(this, R.layout.item_admin_foods)
		{
			@NonNull
			@Override
			public View getView(int position, View convertView, ViewGroup parent)
			{
				ItemAdminFoodsBinding itemBinding;
				if (convertView == null)
					itemBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.item_admin_foods, parent, false);
				else
					itemBinding = DataBindingUtil.getBinding(convertView);

				Food food = getItem(position);
				itemBinding.txtFoodName.setText(food.name);
				itemBinding.txtPrice.setText(food.price + "￦");

				return itemBinding.getRoot();
			}
		};
		binding.listFood.setAdapter(adapter);

		adapter.add(new Food("ASDFSDAF", "불고기 버거", "2500"));
		adapter.add(new Food("ASDFSDAF", "1955 버거", "3800"));
	}
}
