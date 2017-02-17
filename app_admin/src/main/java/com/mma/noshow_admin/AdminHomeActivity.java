package com.mma.noshow_admin;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mma.common.databinding.ItemNoImgReservationInfoBinding;
import com.mma.noshow_admin.databinding.ActivityAdminHomeBinding;
import com.mma.noshow_admin.databinding.DialogAddNoshowBinding;

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

				final FoodNoShow item = getItem(position);
				itemBinding.txtFoodName.setText(item.foods.get(0).name);
				itemBinding.txtDiscountedPrice.setText(item.totalPrice + "￦");
				itemBinding.txtRemained.setText(item.foods.get(0).count + "");
				itemBinding.txtRestaurantName.setText(item.admin.name);
				itemBinding.txtRestaurantLocation.setText(item.admin.location);
				itemBinding.txtDiscountRate.setText("0%");
				itemBinding.noshowCard.setOnLongClickListener(new View.OnLongClickListener()
				{
					@Override
					public boolean onLongClick(View v)
					{
						new AlertDialog.Builder(AdminHomeActivity.this)
								.setTitle("경고")
								.setMessage("NoShow를 삭제하시겠습니까?")
								.setPositiveButton("예", new DialogInterface.OnClickListener()
								{
									@Override
									public void onClick(DialogInterface dialog, int which)
									{
										adapter.remove(item);
										dialog.dismiss();
									}
								})
								.setNegativeButton("아니요", null).show();

						return true;
					}
				});

				return itemBinding.getRoot();
			}
		};
		binding.listNoshow.setAdapter(adapter);

		FoodNoShow foodNoShow = new FoodNoShow(new Admin("맥도날드", "서울 강북구 미아 2동"), "", 12000, "종합 세트");
		foodNoShow.foods.add(new Food("불고기 버거", 2000));
		adapter.add(foodNoShow);

		foodNoShow = new FoodNoShow(new Admin("빕스", "서울 강북구 미아 1동"), "", 7000, "어떤 세트");
		foodNoShow.foods.add(new Food("스테이크", 5000));
		adapter.add(foodNoShow);

		// NoShow 추가
		binding.fab.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final DialogAddNoshowBinding dialogBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_add_noshow, null, false);

				new AlertDialog.Builder(AdminHomeActivity.this)
						.setTitle("NoShow 추가")
						.setView(dialogBinding.getRoot())
						.setPositiveButton("추가", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								String foodName = dialogBinding.editFoodName.getText().toString();
								String price = dialogBinding.editPrice.getText().toString();
								String count = dialogBinding.editCount.getText().toString();

								FoodNoShow foodNoShow = new FoodNoShow(new Admin("어떤 음식점", "안르로메다"), "", Integer.parseInt(price), "어떤 세트");
								foodNoShow.foods.add(new Food(foodName, price, Integer.parseInt(count)));
								adapter.add(foodNoShow);

								dialog.dismiss();
							}
						})
						.setNegativeButton("취소", null).show();
			}
		});
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
