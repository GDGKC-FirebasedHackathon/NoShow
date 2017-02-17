package com.mma.noshow_admin;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mma.common.databinding.ItemNoImgReservationInfoBinding;
import com.mma.noshow_admin.databinding.ActivityAdminHomeBinding;

import model.Admin;
import model.Food;
import model.FoodNoShow;

public class AdminHomeActivity extends Activity
{
	private ActivityAdminHomeBinding binding;
	private ArrayAdapter<FoodNoShow> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_home);

		adapter = new ArrayAdapter<FoodNoShow>(this, R.layout.item_no_img_reservation_info)
		{
			@NonNull
			@Override
			public View getView(int position, View convertView, ViewGroup parent)
			{
				ItemNoImgReservationInfoBinding itemBinding;

				if (convertView == null)
					itemBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.item_no_img_reservation_info, parent, false);
				else itemBinding = DataBindingUtil.getBinding(convertView);

				FoodNoShow item = getItem(position);
				itemBinding.txtFoodName.setText(item.foods.get(0).name);
				itemBinding.txtDiscountedPrice.setText(item.totalPrice + "");
				itemBinding.txtPrice.setText(item.foods.get(0).price * item.foods.get(0).count + "");
				itemBinding.txtRemained.setText(item.foods.get(0).count + "");
				itemBinding.txtRestaurantName.setText(item.admin.name);
				itemBinding.txtRestaurantLocation.setText(item.admin.location);
				itemBinding.txtDiscountRate.setText("0%");

				return itemBinding.getRoot();
			}
		};
		binding.listNoshow.setAdapter(adapter);
/*
		FoodNoShow foodNoShow = new FoodNoShow(new Admin( "맥도날드", "서울 강북구 미아 2동"), "", 12000, "종합 세트");
		foodNoShow.foods.add(new Food("불고기 버거", "2000", 5));
		adapter.add(foodNoShow);

		foodNoShow = new FoodNoShow(new Admin("빕스", "서울 강북구 미아 1동"), "", 7000, "어떤 세트");
		foodNoShow.foods.add(new Food("스테이크", 5000, 2));
		adapter.add(foodNoShow);*/
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
