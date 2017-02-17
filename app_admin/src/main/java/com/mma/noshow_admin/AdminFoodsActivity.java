package com.mma.noshow_admin;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mma.noshow_admin.databinding.ActivityAdminFoodsBinding;
import com.mma.noshow_admin.databinding.DialogAddFoodBinding;
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
			public View getView(final int position, View convertView, ViewGroup parent)
			{
				ItemAdminFoodsBinding itemBinding;
				if (convertView == null)
					itemBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.item_admin_foods, parent, false);
				else
					itemBinding = DataBindingUtil.getBinding(convertView);

				Food food = getItem(position);
				itemBinding.txtFoodName.setText(food.name);
				itemBinding.txtPrice.setText(food.price + "￦");
				itemBinding.foodCard.setOnLongClickListener(new View.OnLongClickListener()
				{
					@Override
					public boolean onLongClick(View v)
					{
						new AlertDialog.Builder(AdminFoodsActivity.this)
								.setTitle("경고")
								.setMessage("음식을 삭제하시겠습니까?")
								.setPositiveButton("예", new DialogInterface.OnClickListener()
								{
									@Override
									public void onClick(DialogInterface dialog, int which)
									{
										adapter.remove(adapter.getItem(position));
									}
								})
								.setNegativeButton("아니요", null).show();
						return true;
					}
				});

				return itemBinding.getRoot();
			}
		};
		binding.listFood.setAdapter(adapter);

		adapter.add(new Food( "불고기 버거", 2500));
		adapter.add(new Food("1955 버거", 3800));
	}

	public void onClick(View view)
	{
		if (view == binding.btnBack)
		{
			finish();
		}

		else if (view == binding.btnAdd)
		{
			final DialogAddFoodBinding dialogBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_add_food, null, false);

			new AlertDialog.Builder(this)
					.setTitle("음식 등록")
					.setView(dialogBinding.getRoot())
					.setPositiveButton("추가", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							String foodName = dialogBinding.editFoodName.getText().toString();
							long price = Integer.parseInt( dialogBinding.editPrice.getText().toString());
							adapter.add(new Food("ASDFADSF", foodName, price));
							dialog.dismiss();
						}
					})
					.setNegativeButton("취소", null).show();
		}
	}
}
