package fr.actia.utilitaire;

import java.util.ArrayList;
public class AddListToList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> firstList = new ArrayList<Integer>();
		firstList.add(2);
		firstList.add(4);
		firstList.add(6);
		System.out.println("première liste \n"+firstList.toString());
		
		ArrayList<Integer> secondList = new ArrayList<Integer>();
		secondList.add(1);
		secondList.add(3);
		secondList.add(5);
		System.out.println("seconde liste \n"+secondList);

		firstList.addAll(secondList);
		System.out.println("première liste après addall\n"+firstList.toString());
		
		int qqch = firstList.indexOf(9);
		System.out.println(qqch);
	}

}
