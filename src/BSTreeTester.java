import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class BSTreeTester {
    static BSTree<Integer> bst1;
    static BSTree<String> bst2;
    static BSTree<Integer> bst3;

    @Before
    public void createBST1(){
        bst1 = new BSTree<>();
        bst1.insert(new Integer(1));
        bst1.insert(new Integer(2));
        bst1.insert(new Integer(3));
        bst1.insert(new Integer(4));
        bst1.insert(new Integer(5));
        bst1.insert(new Integer(6));

        bst2= new BSTree<>();
        bst2.insert("C");
        bst2.insert("A");
        bst2.insert("B");
        bst2.insert("D");
        bst2.insert("E");
        bst2.insert("F");

        bst3= new BSTree<>();
        bst3.insert(new Integer(10));
        bst3.insert(new Integer(12));
        bst3.insert(new Integer(8));
        bst3.insert(new Integer(9));
        bst3.insert(new Integer(7));
        bst3.insert(new Integer(4));
        bst3.insert(new Integer(5));
        bst3.insert(new Integer(6));
    }

    @Test
    public void bst1Constructor(){
        assertEquals(bst1.getRoot().getKey(),new Integer(1));
    }
    @Test
    public void bst2Constructor(){
        assertEquals(bst2.getRoot().getKey(),"C");
    }
    @Test
    public void bst3Constructor(){
        assertEquals(bst3.getRoot().getKey(),new Integer(10));
    }

    @Test
    public void getRootTest() {
        assertEquals(bst1.getRoot().getKey(), new Integer(1));
    }

    @Test
    public void getSizeTest() {
        assertEquals(bst2.getSize(),6);
    }

    @Test
    public void insertTest1() {
        assertTrue(bst1.insert(new Integer(7)));
        assertTrue(bst1.findKey(new Integer(7)));
    }
    @Test
    public void insertTest2() {
        assertFalse(bst1.insert(new Integer(6)));
    }
    @Test
    public void insertTest3() {
        assertTrue(bst1.insert(new Integer(-1)));
        assertEquals(bst1.getRoot().getLeft().getKey(),new Integer(-1));
    }

    @Test
    public void findKeyTest1() {
        assertTrue(bst1.findKey(new Integer(6)));
    }
    @Test
    public void findKeyTest2() {
        assertTrue(bst2.findKey("C"));
    }
    @Test
    public void findKeyTest3() {
        assertTrue(bst3.findKey(new Integer(6)));
    }

    @Test
    public void insertDataTest1() {
        bst1.insertData(new Integer(6), new Integer(2));
        assertEquals(bst1.findDataList(new Integer(6)).getLast(), new Integer(2));
    }
    @Test
    public void insertDataTest2() {
        bst1.insertData(new Integer(6), new Integer(2));
        bst1.insertData(new Integer(6), new Integer(4));
        assertEquals(bst1.findDataList(new Integer(6)).getLast(), new Integer(4));
    }
    @Test
    public void insertDataTest3() {
        bst2.insertData("A", "ABC");
        bst2.insertData("A", "EDC");
        assertEquals(bst2.findDataList("A").get(1), "EDC");
    }
    @Test (expected = NullPointerException.class)
    public void insertDataTest4() {
        bst2.insertData(null,"ABC");
        assertEquals(bst2.findDataList("A").get(1), "EDC");
    }
    @Test (expected = NullPointerException.class)
    public void insertDataTest5() {
        bst2.insertData("A",null);
        assertEquals(bst2.findDataList("A").get(1), "EDC");
    }
    @Test (expected = IllegalArgumentException.class)
    public void insertDataTest6() {
        bst2.insertData("K","ABC");
        assertEquals(bst2.findDataList("A").get(1), "EDC");
    }

    @Test
    public void findDataListTest1() {
        bst1.insertData(new Integer(6), new Integer(2));
        assertEquals(bst1.findDataList(new Integer(6)).get(0), new Integer(2));
    }
    @Test
    public void findDataListTest2() {
        bst1.insertData(new Integer(6), new Integer(2));
        bst1.insertData(new Integer(6), new Integer(4));
        assertEquals(bst1.findDataList(new Integer(6)).get(1), new Integer(4));
    }
    @Test
    public void findDataListTest3() {
        bst2.insertData("A", "ABC");
        bst2.insertData("A", "EDC");
        assertEquals(bst2.findDataList("A").get(1), "EDC");
    }
    @Test (expected = NullPointerException.class)
    public void findDataListTest4() {
        bst2.insertData("A", "ABC");
        bst2.insertData("A", "EDC");
        assertEquals(bst2.findDataList(null).get(1), "EDC");
    }
    @Test (expected = IllegalArgumentException.class)
    public void findDataListTest5() {
        bst2.insertData("A", "ABC");
        bst2.insertData("A", "EDC");
        assertEquals(bst2.findDataList("K").get(1), "EDC");
    }
    @Test
    public void findHeightTest1() {
        assertEquals(bst1.findHeight(), 5);
    }
    @Test
    public void findHeightTest2() {
        assertEquals(bst2.findHeight(), 3);
    }
    @Test
    public void findHeightTest3() {
        assertEquals(bst3.findHeight(), 5);
    }

    @Test
    public void iteratorTester1(){
        Iterator<Integer> bsti1 =bst1.iterator();
        assertEquals(bsti1.next(),new Integer(1));
    }
    @Test
    public void iteratorTester2(){
        Iterator<String> bsti2 =bst2.iterator();
        assertEquals(bsti2.next(),"A");
    }
    @Test
    public void iteratorTester3(){
        Iterator<Integer> bsti3 =bst3.iterator();
        assertEquals(bsti3.next(),new Integer(4));
    }
    @Test
    public void iteratorHasNextTest1(){
        Iterator<Integer> bsti1 =bst1.iterator();
        bsti1.next();
        bsti1.next();
        bsti1.next();
        bsti1.next();
        bsti1.next();
        bsti1.next();
        assertFalse(bsti1.hasNext());
    }
    @Test
    public void iteratorHasNextTest2(){
        Iterator<Integer> bsti1 =bst1.iterator();
        bsti1.next();
        assertTrue(bsti1.hasNext());
    }
    @Test
    public void iteratorHasNextTest3(){
        Iterator<Integer> bsti3 =bst3.iterator();
        bsti3.next();
        assertTrue(bsti3.hasNext());
    }
    @Test
    public void iteratorNextTest1() {
        Iterator<Integer> bsti1 =bst1.iterator();
        assertEquals(bsti1.next(),new Integer(1));
        assertEquals(bsti1.next(),new Integer(2));
        assertEquals(bsti1.next(),new Integer(3));
        assertEquals(bsti1.next(),new Integer(4));
        assertEquals(bsti1.next(),new Integer(5));
        assertEquals(bsti1.next(),new Integer(6));
    }
    @Test
    public void iteratorNextTest2() {
        Iterator<String> bsti2 =bst2.iterator();
        assertEquals(bsti2.next(),"A");
        assertEquals(bsti2.next(),"B");
        assertEquals(bsti2.next(),"C");
        assertEquals(bsti2.next(),"D");
        assertEquals(bsti2.next(),"E");
        assertEquals(bsti2.next(),"F");
    }
    @Test
    public void iteratorNextTest3() {
        Iterator<Integer> bsti3 =bst3.iterator();
        assertEquals(bsti3.next(),new Integer(4));
        assertEquals(bsti3.next(),new Integer(5));
        assertEquals(bsti3.next(),new Integer(6));
        assertEquals(bsti3.next(),new Integer(7));
        assertEquals(bsti3.next(),new Integer(8));
        assertEquals(bsti3.next(),new Integer(9));
        assertEquals(bsti3.next(),new Integer(10));
        assertEquals(bsti3.next(),new Integer(12));
    }
    @Test
    public void iteratorNextTest4(){
        BSTree<Integer> intTree = new BSTree<>();
        Iterator<Integer> iter = intTree.iterator();
        assertFalse(iter.hasNext());

        BSTree<Integer> intTree2 = new BSTree<>();
        intTree2.insert(10);
        intTree2.insert(5);
        intTree2.insert(30);
        intTree2.insert(40);
        Iterator<Integer> iter2 = intTree2.iterator();
        assertTrue(iter2.hasNext());
    }
    @Test (expected = NoSuchElementException.class)
    public void iteratorNextTest5(){
        Iterator<Integer> bsti1 =bst1.iterator();
        assertEquals(bsti1.next(),new Integer(1));
        assertEquals(bsti1.next(),new Integer(2));
        assertEquals(bsti1.next(),new Integer(3));
        assertEquals(bsti1.next(),new Integer(4));
        assertEquals(bsti1.next(),new Integer(5));
        assertEquals(bsti1.next(),new Integer(6));
        bsti1.next();
    }

    @Test
    public void intersectionTest1() {
        BSTree<Integer> intTree1 = new BSTree<>();
        intTree1.insert(new Integer(5));
        intTree1.insert(new Integer(1));
        intTree1.insert(new Integer(10));
        intTree1.insert(new Integer(0));
        intTree1.insert(new Integer(4));
        intTree1.insert(new Integer(7));
        intTree1.insert(new Integer(9));

        BSTree<Integer> intTree2 = new BSTree<>();
        intTree2.insert(new Integer(10));
        intTree2.insert(new Integer(7));
        intTree2.insert(new Integer(20));
        intTree2.insert(new Integer(4));
        intTree2.insert(new Integer(9));

        ArrayList<Integer> check = new ArrayList<>();
        check.add(new Integer(4));
        check.add(new Integer(7));
        check.add(new Integer(9));
        check.add(new Integer(10));
        assertEquals(check, intTree1.intersection(intTree1.iterator(), intTree2.iterator()));
    }

    @org.junit.Test
    public void levelMax() {
    }
}