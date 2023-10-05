package hw3;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class HW3Test {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);


	@Test
	public void test05HeightEmpty() {
		IntTree set = new IntTree();
		assertEquals(-1, set.height());
	}

	@Test
	public void test05HeightSingleNode() {
		IntTree set = IntTree.fromString("40:");
		assertEquals(0, set.height());
	}

	@Test
	public void test05HeightSmallBalanced() {
		IntTree set = IntTree.fromString("40: 20:l");
		assertEquals(1, set.height());
		set = IntTree.fromString("40: 60:r");
		assertEquals(1, set.height());
		set = IntTree.fromString("40: 20:l 60:r");
		assertEquals(1, set.height());
		set = IntTree.fromString("40: 20:l 60:r 10:ll");
		assertEquals(2, set.height());
		set = IntTree.fromString("40: 20:l 60:r 30:lr");
		assertEquals(2, set.height());
		set = IntTree.fromString("40: 20:l 60:r 50:rl");
		assertEquals(2, set.height());
		set = IntTree.fromString("40: 20:l 60:r 70:rr");
		assertEquals(2, set.height());
		set = IntTree.fromString("40: 20:l 60:r 10:ll 30:lr 50:rl 70:rr");
		assertEquals(2, set.height());
		set = IntTree.fromString("40: 20:l 10:ll");
		assertEquals(2, set.height());
		set = IntTree.fromString("40: 10:l 20:lr");
		assertEquals(2, set.height());
		set = IntTree.fromString("10: 40:r 20:rl");
		assertEquals(2, set.height());
		set = IntTree.fromString("10: 20:r 40:rr");
		assertEquals(2, set.height());
		set = IntTree.fromString("40: 20:l 60:r 10:ll 30:lr 50:rl 70:rr 5:lll");
		assertEquals(3, set.height());
		set = IntTree.fromString("40: 20:l 60:r 10:ll 30:lr 50:rl 70:rr 15:llr");
		assertEquals(3, set.height());
		set = IntTree.fromString("40: 20:l 60:r 10:ll 30:lr 50:rl 70:rr 25:lrl");
		assertEquals(3, set.height());
		set = IntTree.fromString("40: 20:l 60:r 10:ll 30:lr 50:rl 70:rr 35:lrr");
		assertEquals(3, set.height());
		set = IntTree.fromString("40: 20:l 60:r 10:ll 30:lr 50:rl 70:rr 45:rll");
		assertEquals(3, set.height());
		set = IntTree.fromString("40: 20:l 60:r 10:ll 30:lr 50:rl 70:rr 55:rlr");
		assertEquals(3, set.height());
		set = IntTree.fromString("40: 20:l 60:r 10:ll 30:lr 50:rl 70:rr 65:rrl");
		assertEquals(3, set.height());
		set = IntTree.fromString("40: 20:l 60:r 10:ll 30:lr 50:rl 70:rr 75:rrr");
		assertEquals(3, set.height());
	}

	@Test
	public void test05HeightLarge() {
		IntTree set = new IntTree();
		assertEquals(-1, set.height());

		int key = 500;
		String path = "";
		int h = 0;
		set.put(key, path);
		assertEquals(h, set.height());

		// Make a zig-zag insertion path starting at 1000 and moving left, right, left,
		// right, etc.
		while (h < 100) {
			if (h % 2 == 0)
				path += 'l';
			else
				path += 'r';
			h++;
			key--;
			set.put(key, path);
			assertEquals(h, set.height());
		}

		// Insert siblings at the bottom of the path. Height should change by 1.
		h++;
		set.put(1000, path + 'r');
		assertEquals(h, set.height());
		set.put(10001, path + 'l');
		assertEquals(h, set.height());

		// Make 100 insertions down the right side. Height shouldn't change.
		path = "";
		for (int j = 0; j < 100; j++) {
			path += 'r';
			key--;
			set.put(key, path);
			assertEquals(h, set.height());
		}
	}

	@Test
	public void test05SizeOddEmpty() {
		IntTree set = new IntTree();
		assertEquals(0, set.sizeOdd());
	}

	@Test
	public void test05SizeOddSingleNode() {
		IntTree set = IntTree.fromString("40:");
		assertEquals(0, set.sizeOdd());
		set = IntTree.fromString("71:");
		assertEquals(1, set.sizeOdd());
	}

	@Test
	public void test05SizeOddSmall() {
		IntTree s1 = new IntTree();
		assertEquals(0, s1.sizeOdd());
		IntTree s2 = new IntTree();
		assertEquals(0, s1.sizeOdd());
		s1.put(21, "");
		s2.put(42, "");
		assertEquals(1, s1.sizeOdd());
		assertEquals(0, s2.sizeOdd());
		s1.put(42, "r");
		s2.put(21, "l");
		assertEquals(1, s1.sizeOdd());
		assertEquals(1, s2.sizeOdd());
		s1.put(11, "l");
		s2.put(64, "r");
		assertEquals(2, s1.sizeOdd());
		assertEquals(1, s2.sizeOdd());
	}

	@Test
	public void test05SizeOddLarge() {
		// Insert the following ints into an empty set checking oddSize after each
		// insertion.
		int[] a =    {100, 55,  160, 22,   66,   103,  195,  67,    190,   191,    2,    1, 
				98,     201,    200 };
		String[] p = {"",  "l", "r", "ll", "lr", "rl", "rr", "lrr", "rrl", "rrlr", "lll", "llll",
				"lrrr", "rrr", "rrrl"};
		int oddCount = 0;
		IntTree s = new IntTree();
		for (int i = 0; i < a.length; i++) {
			s.put(a[i], p[i]);
			if (a[i] % 2 == 1)
				oddCount++;
			assertEquals(oddCount, s.sizeOdd());
		}
	}

	@Test
	public void test05TreeEqualsEmpty() {
		IntTree s1 = new IntTree();
		IntTree s2 = new IntTree();
		IntTree s3 = IntTree.fromString("43:");
		assertTrue(s1.treeEquals(s2));
		assertTrue(s2.treeEquals(s1));
		assertFalse(s1.treeEquals(s3));
		assertFalse(s3.treeEquals(s1));
	}

	@Test
	public void test05TreeEqualsSingleNode() {
		IntTree s1 = IntTree.fromString("43:");
		IntTree s2 = IntTree.fromString("43:");
		IntTree s3 = IntTree.fromString("44:");
		assertTrue(s1.treeEquals(s2));
		assertTrue(s2.treeEquals(s1));
		assertFalse(s1.treeEquals(s3));
		assertFalse(s3.treeEquals(s1));
	}

	@Test
	public void test05TreeEqualsSmall() {
		IntTree s1;
		IntTree s2;

		s1 = IntTree.fromString("43: 41:l");
		s2 = IntTree.fromString("43: 41:l");
		assertTrue(s1.treeEquals(s2));
		assertTrue(s2.treeEquals(s1));

		s1 = IntTree.fromString("43: 41:l");
		s2 = IntTree.fromString("41: 43:r");
		assertFalse(s1.treeEquals(s2));
		assertFalse(s2.treeEquals(s1));

		s1 = IntTree.fromString("2: 1:l 3:r");
		s2 = IntTree.fromString("2: 3:r 1:l");
		assertTrue(s1.treeEquals(s2));
		assertTrue(s2.treeEquals(s1));
	}

	@Test
	public void test05TreeEqualsLarge() {
		IntTree s1 = new IntTree();
		IntTree s2 = new IntTree();
		int[] a = { 100, 55, 160, 22, 66, 103, 195, 67, 190, 191, 12, 11, 98, 201, 200 };
		String[] p = { "", "l", "r", "ll", "lr", "rl", "rr", "lrr", "rrl", "rrlr", "lll", "llll", "lrrr", "rrr",
				"rrrl" };

		// for each int in a, insert first into s1, check not equal
		// then insert into s2 check equal
		for (int i = 0; i < a.length; i++) {
			s1.put(a[i], p[i]);
			assertFalse(s1.treeEquals(s2));
			assertFalse(s2.treeEquals(s1));
			s2.put(a[i], p[i]);
			assertTrue(s1.treeEquals(s2));
			assertTrue(s2.treeEquals(s1));
		}

		s1.put(202, "rrrr");
		s2.put(4, "lllll");
		assertFalse(s1.treeEquals(s2));
		assertFalse(s2.treeEquals(s1));
		s1.put(4, "lllll");
		s2.put(202, "rrrr");
		assertTrue(s1.treeEquals(s2));
		assertTrue(s2.treeEquals(s1));
		s1.put(1, "llllll");
		s2.put(2, "llllll");
		assertFalse(s1.treeEquals(s2));
		assertFalse(s2.treeEquals(s1));

		s1 = IntTree.fromString("50: 150:r 100:rl");
		s2 = IntTree.fromString("50: 100:l 150:lr");
		assertFalse(s1.treeEquals(s2));
		assertFalse(s2.treeEquals(s1));

		s1 = IntTree.fromString("100: 50:l 150:r 25:ll 75:lr 125:rl 175:rr");
		s2 = IntTree.fromString("100: 50:l 150:r 25:ll 75:lr 125:rl 175:rr");
		assertTrue(s1.treeEquals(s2));
		assertTrue(s2.treeEquals(s1));
		s1.put(126, "rlr");
		s1.put(127, "rlrr");
		s2.put(127, "rlr");
		s2.put(126, "rlrl");
		assertFalse(s1.treeEquals(s2));
		assertFalse(s2.treeEquals(s1));
	}

	@Test
	public void test05SizeAtDepthSmall() {
		IntTree set = new IntTree();
		assertEquals(0, set.sizeAtDepth(0));
		assertEquals(0, set.sizeAtDepth(1));
		assertEquals(0, set.sizeAtDepth(2));

		set = IntTree.fromString("10:");
		assertEquals(1, set.sizeAtDepth(0));
		assertEquals(0, set.sizeAtDepth(1));
		assertEquals(0, set.sizeAtDepth(2));

		set = IntTree.fromString("10: 20:r");
		assertEquals(1, set.sizeAtDepth(0));
		assertEquals(1, set.sizeAtDepth(1));
		assertEquals(0, set.sizeAtDepth(2));

		set = IntTree.fromString("20: 10:l");
		assertEquals(1, set.sizeAtDepth(0));
		assertEquals(1, set.sizeAtDepth(1));
		assertEquals(0, set.sizeAtDepth(2));

		set = IntTree.fromString("10: 5:l 15:r");
		assertEquals(1, set.sizeAtDepth(0));
		assertEquals(2, set.sizeAtDepth(1));
		assertEquals(0, set.sizeAtDepth(2));

	}

	@Test
	public void test05SizeAtDepthLarge() {
		int[] keys = { 1000, 500, 250, 750, 1500, 125, 1750, 2000, 625, 1250 };
		String[] p = { "", "l", "ll", "lr", "r", "lll", "rr", "rrr", "lrl", "rl" };
		int[] d1 = { 0, 1, 1, 1, 2, 2, 2, 2, 2, 2 };
		int[] d2 = { 0, 0, 1, 2, 2, 2, 3, 3, 3, 4 };
		int[] d3 = { 0, 0, 0, 0, 0, 1, 1, 2, 3, 3 };
		IntTree set = new IntTree();
		assertEquals(0, set.sizeAtDepth(0));
		assertEquals(0, set.sizeAtDepth(1));
		assertEquals(0, set.sizeAtDepth(2));

		for (int i = 0; i < keys.length; i++) {
			set.put(keys[i], p[i]);
			assertEquals(1, set.sizeAtDepth(0));
			assertEquals(d1[i], set.sizeAtDepth(1));
			assertEquals(d2[i], set.sizeAtDepth(2));
			assertEquals(d3[i], set.sizeAtDepth(3));
		}
	}

	@Test
	public void test05sizeAboveDepthSmall() {
		IntTree set = new IntTree();
		assertEquals(0, set.sizeAboveDepth(0));
		assertEquals(0, set.sizeAboveDepth(1));
		assertEquals(0, set.sizeAboveDepth(2));

		set = IntTree.fromString("10:");
		assertEquals(0, set.sizeAboveDepth(0));
		assertEquals(1, set.sizeAboveDepth(1));
		assertEquals(1, set.sizeAboveDepth(2));

		set = IntTree.fromString("10: 20:r");
		assertEquals(0, set.sizeAboveDepth(0));
		assertEquals(1, set.sizeAboveDepth(1));
		assertEquals(2, set.sizeAboveDepth(2));

		set = IntTree.fromString("20: 10:l");
		assertEquals(0, set.sizeAboveDepth(0));
		assertEquals(1, set.sizeAboveDepth(1));
		assertEquals(2, set.sizeAboveDepth(2));

		set = IntTree.fromString("10: 5:l 15:r");
		assertEquals(0, set.sizeAboveDepth(0));
		assertEquals(1, set.sizeAboveDepth(1));
		assertEquals(3, set.sizeAboveDepth(2));

	}

	@Test
	public void test05sizeAboveDepthLarge() {
		int[] keys = { 1000, 500, 250, 750, 1500, 125, 1750, 2000, 625, 1250 };
		String[] p = { "", "l", "ll", "lr", "r", "lll", "rr", "rrr", "lrl", "rl" };
		int[] d2 = { 1, 2, 2, 2, 3, 3, 3, 3, 3, 3 };
		int[] d3 = { 1, 2, 3, 4, 5, 5, 6, 6, 6, 7 };
		int[] d4 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		IntTree set = new IntTree();
		assertEquals(0, set.sizeAboveDepth(0));
		assertEquals(0, set.sizeAboveDepth(1));
		assertEquals(0, set.sizeAboveDepth(2));

		for (int i = 0; i < keys.length; i++) {
			set.put(keys[i], p[i]);
			assertEquals(0, set.sizeAboveDepth(0));
			assertEquals(1, set.sizeAboveDepth(1));
			assertEquals(d2[i], set.sizeAboveDepth(2));
			assertEquals(d3[i], set.sizeAboveDepth(3));
			assertEquals(d4[i], set.sizeAboveDepth(4));
		}
	}

	@Test
	public void test05IsPerfectlyBalancedSmall() {
		IntTree set = new IntTree();
		assertTrue(set.isPerfectlyBalanced());
		set = IntTree.fromString("50:");
		assertTrue(set.isPerfectlyBalanced());
		set = IntTree.fromString("50: 100:r");
		assertFalse(set.isPerfectlyBalanced());
		set = IntTree.fromString("100: 50:l");
		assertFalse(set.isPerfectlyBalanced());
		set = IntTree.fromString("100: 50:l 150:r");
		assertTrue(set.isPerfectlyBalanced());
		set = IntTree.fromString("50: 100:r 150:rr");
		assertFalse(set.isPerfectlyBalanced());
		set = IntTree.fromString("150: 100:l 50:ll");
		assertFalse(set.isPerfectlyBalanced());
		set = IntTree.fromString("50: 100:r 75:rl");
		assertFalse(set.isPerfectlyBalanced());
		set = IntTree.fromString("100: 50:l 75:lr");
		assertFalse(set.isPerfectlyBalanced());
	}

	@Test
	public void test05IsPerfectlyBalancedLarge() {
		IntTree set;
		set = IntTree.fromString("100: 50:l 150:r 25:ll");
		assertFalse(set.isPerfectlyBalanced());
		set = IntTree.fromString("100: 50:l 150:r 125:rl");
		assertFalse(set.isPerfectlyBalanced());
		set = IntTree.fromString("100: 50:l 150:r 25:ll 175:rr");
		assertFalse(set.isPerfectlyBalanced());
		set = IntTree.fromString("100: 50:l 150:r 25:ll 75:lr");
		assertFalse(set.isPerfectlyBalanced());
		set = IntTree.fromString("100: 50:l 150:r 125:rl 175:rr");
		assertFalse(set.isPerfectlyBalanced());
		set = IntTree.fromString("50: 25:l 75:r 12:ll 37:lr 62:rl 87:rr");
		assertTrue(set.isPerfectlyBalanced());
		set = IntTree.fromString("50: 25:l 12:ll 37:lr 75:r 62:rl 87:rr");
		assertTrue(set.isPerfectlyBalanced());
		set = IntTree.fromString("50: 75:r 62:rl 87:rr 25:l 12:ll 37:lr");
		assertTrue(set.isPerfectlyBalanced());
		set = IntTree.fromString("50: 25:l 75:r 12:ll 37:lr 62:rl 87:rr 51:rll");
		assertFalse(set.isPerfectlyBalanced());
		set = IntTree.fromString("50: 25:l 75:r 12:ll 22:llr 62:rl 87:rr");
		assertFalse(set.isPerfectlyBalanced());
		set = IntTree.fromString(
				"50: 25:l 75:r 12:ll 37:lr 62:rl 87:rr 5:lll 18:llr 31:lrl" + " 42:lrr 56:rll 68:rlr 81:rrl 93:rrr");
		assertTrue(set.isPerfectlyBalanced());
		set = IntTree.fromString(
				"50: 25:l 75:r 12:ll 37:lr 62:rl 87:rr 5:lll 18:llr 31:lrl" + " 36:lrlr 56:rll 68:rlr 81:rrl 93:rrr");
		assertFalse(set.isPerfectlyBalanced());
	}

	@Test
	public void test05RemoveOddSubtreesSmall() {
		IntTree set = new IntTree();
		set.removeOddSubtrees();
		assertEquals("", set.toString());
		set = IntTree.fromString("100:");
		set.removeOddSubtrees();
		assertEquals("100 ", set.toString());
		set = IntTree.fromString("101:");
		set.removeOddSubtrees();
		assertEquals("", set.toString());
		set = IntTree.fromString("100: 98:l 101:r");
		set.removeOddSubtrees();
		assertEquals("100 98 ", set.toString());
		set = IntTree.fromString("100: 91:l 102:r");
		set.removeOddSubtrees();
		assertEquals("100 102 ", set.toString());
		set = IntTree.fromString("101: 98:l 100:lr");
		set.removeOddSubtrees();
		assertEquals("", set.toString());
	}

	@Test
	public void test05RemoveOddSubtreesLarge() {
		IntTree set;
		set = IntTree.fromString("100: 50:l 150:r 20:ll 60:lr 130:rl 180:rr");
		set.removeOddSubtrees();
		assertEquals("100 50 150 20 60 130 180 ", set.toString());
		set = IntTree.fromString("100: 50:l 150:r 20:ll 61:lr 130:rl 180:rr");
		set.removeOddSubtrees();
		assertEquals("100 50 150 20 130 180 ", set.toString());
		set = IntTree.fromString("100: 50:l 150:r 20:ll 60:lr 130:rl 1801:rr");
		set.removeOddSubtrees();
		assertEquals("100 50 150 20 60 130 ", set.toString());
		set = IntTree.fromString("100: 50:l 150:r 21:ll 60:lr 131:rl 180:rr");
		set.removeOddSubtrees();
		assertEquals("100 50 150 60 180 ", set.toString());
		set = IntTree.fromString("100: 51:l 150:r 20:ll 60:lr 130:rl 180:rr");
		set.removeOddSubtrees();
		assertEquals("100 150 130 180 ", set.toString());
		set = IntTree.fromString("100: 50:l 151:r 20:ll 60:lr 130:rl 180:rr");
		set.removeOddSubtrees();
		assertEquals("100 50 20 60 ", set.toString());
		set = IntTree.fromString("100: 57:l 153:r 20:ll 60:lr 130:rl 180:rr");
		set.removeOddSubtrees();
		assertEquals("100 ", set.toString());
		set = IntTree.fromString("97: 50:l 150:r 20:ll 60:lr 130:rl 180:rr");
		set.removeOddSubtrees();
		assertEquals("", set.toString());
		set = IntTree.fromString("99: 51:l 153:r 23:ll 59:lr 131:rl 189:rr");
		set.removeOddSubtrees();
		assertEquals("", set.toString());
	}
}
