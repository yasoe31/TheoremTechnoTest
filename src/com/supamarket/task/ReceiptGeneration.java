package com.supamarket.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReceiptGeneration {

	static String input = null;
	String[] parts;
	String[] secondParts;

	public static void main(String[] args) {

		ReceiptGeneration receiptGeneration = new ReceiptGeneration();
		List<Item> itemObject = new ArrayList<Item>();		

		System.out.println("Please Enter the Item/Quantity");

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			input = bufferedReader.readLine();
		} catch (IOException e) {

			e.printStackTrace();
		}

		itemObject = receiptGeneration.getItemQauntity();
	}
	
	public List<Item> getItemQauntity() {

		List<Item> itemObject = new ArrayList<Item>();
		input = input.replace("\"", "");
		parts = input.split("\\\\n");

		for (int i = 0; i < parts.length; i++) {

			Item it = new Item();

			secondParts = parts[i].split("\\|");

			for (int j = 0; j < secondParts.length; j++) {
				// System.out.println("Second Part is " + secondParts[j]);

				if (j % 2 == 0) {
					it.setName(secondParts[j]);
					secondParts[j] = secondParts[j].replaceAll("\\s", "");
					it.setValue(Price.valueOf(
							secondParts[j].replaceAll("\\s+", " ")
									.toUpperCase()).getPrice());
				} else {
					it.setQuantity(Integer.parseInt(secondParts[j]));
				}
			}
			itemObject.add(it);
		}
		return itemObject;
	}

	public enum Price {

		ORANGE {
			@Override
			public float getPrice() {
				return .40f;
			}
		},
		BANANA {
			@Override
			public float getPrice() {
				return .12f;
			}
		},
		BOXOFCEREAL {
			@Override
			public float getPrice() {
				return 1.80f;
			}
		},
		LOAFOFBREAD {
			@Override
			public float getPrice() {
				return .80f;
			}
		},
		FROZENPIZZA {
			@Override
			public float getPrice() {
				return 2.50f;
			}
		},
		TOMATO {
			@Override
			public float getPrice() {
				return .20f;
			}
		};

		public abstract float getPrice();
	};

}
