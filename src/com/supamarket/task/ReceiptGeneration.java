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
		List<Item> offerItemList = new ArrayList<Item>();

		System.out.println("Please Enter the Item/Quantity");

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			input = bufferedReader.readLine();
		} catch (IOException e) {

			e.printStackTrace();
		}

		itemObject = receiptGeneration.getItemQauntity();
		Item item = new Item();
		item.setName("Frozen Pizza");

		offerItemList = receiptGeneration.twoForOne(item);
		receiptGeneration.generateTwoForOneReceipt(itemObject, offerItemList);
	}

	public List<Item> twoForOne(Item item) {
		List<Item> offerItemList = new ArrayList<Item>();
		offerItemList.add(item);
		return offerItemList;
	}

	public void generateTwoForOneReceipt(List<Item> itemList,
			List<Item> offerItemsList) {

		float total = 0;
		float discount = 0.00f;

		System.out.println("Customer Receipt");
		System.out.format("%15s%10s%10s", "Item", "Quantity", "Cost");

		System.out.println("\n--------------------------------------");

		for (Item item : itemList) {

			for (Item item2 : offerItemsList) {

				if (item.getName().trim()
						.equalsIgnoreCase(item2.getName().trim())) {

					if (item.getQuantity() >= 2 && item.getQuantity() % 2 == 0) {

						System.out.format("%15s%10d%10.2f", item.getName(),
								item.getQuantity(),
								item.getValue() * item.getQuantity());
						System.out.println("\n");
						total += item.getValue() * item.getQuantity() / 2;
						discount += item.getValue() * item.getQuantity() / 2;

					} else {

						System.out.format("%15s%10d%10.2f", item.getName(),
								item.getQuantity(),
								item.getValue() * item.getQuantity());
						System.out.println("\n");
						total += item.getValue() * (item.getQuantity() % 2 + 1);
						discount += item.getValue() * (item.getQuantity() % 2);
					}

				} else {

					System.out.format("%15s%10d%10.2f", item.getName(),
							item.getQuantity(),
							item.getValue() * item.getQuantity());
					System.out.println("\n");
					total += item.getValue() * item.getQuantity();
				}
			}
		}

		if (discount != 0.00) {
			System.out.println("2 for 1 discount -" + discount);
		}
		System.out.println("\n-------------------------------------");
		System.out.format("%8s%3.2f", "Total £", total);

	}

	public void generateReceipt(List<Item> itemList) {

		float total = 0;

		System.out.println("Customer Receipt");
		System.out.format("%15s%10s%10s", "Item", "Quantity", "Cost");

		System.out.println("\n--------------------------------------");

		for (Item item : itemList) {
			System.out.format("%15s%10d%10.2f", item.getName(),
					item.getQuantity(), item.getValue() * item.getQuantity());
			System.out.println("\n");
			total += item.getValue() * item.getQuantity();
		}

		System.out.println("\n-------------------------------------");
		System.out.format("%8s%3.2f", "Total £", total);

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
