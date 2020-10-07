package p1.it8343;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import p1.it8343.merge.Sorter;

class TestCase {

	@Test
	void test0() {
		Integer[] nums = new Integer[] {};
		Integer[] exp = nums.clone();

		Sorter.sort(nums);

		assertArrayEquals(exp, nums);
	}

	@Test
	void test1() {
		Integer[] nums = new Integer[] { 42 };
		Integer[] exp = nums.clone();

		Sorter.sort(nums);

		assertArrayEquals(exp, nums);
	}

	@Test
	void test2InOrder() {
		Integer[] nums = new Integer[] { 0, 1, 2, 3 };
		Integer[] exp = nums.clone();

		Sorter.sort(nums);

		assertArrayEquals(exp, nums);
	}

	@Test
	void test2NotInOrder() {
		Integer[] nums = new Integer[] { 42, 11 };
		Integer[] exp = nums.clone();

		Sorter.sort(nums);
		Arrays.sort(exp);
		assertArrayEquals(exp, nums);
	}
	@Test
	void testRandomOutOfOrder5() {
		Random rand = new Random();
		rand.setSeed(567);

		Integer[] arr = new Integer[5];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(100);
		}
		Integer[] exp = arr.clone();
		Arrays.sort(exp);

		Sorter.sort(arr);

		assertArrayEquals(exp, arr);
	}
	@Test
	void testRandomOutOfOrder100() {
		Random rand = new Random();
		rand.setSeed(567);

		Integer[] arr = new Integer[100];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(100);
		}
		Integer[] exp = arr.clone();
		Arrays.sort(exp);

		Sorter.sort(arr);

		assertArrayEquals(exp, arr);
	}
}
