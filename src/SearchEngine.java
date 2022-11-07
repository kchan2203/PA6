/*
 * Name: Kevin Chan
 * PID:  A16871142
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 * 
 * @author Kevin Chan
 * @since  11/07/2022
 */
public class SearchEngine {
    private static final int QUERYINDEX = 2;
    private static final int EXTRAQUERYINDEXES = 3;

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @return false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                // populate three trees with the information you just read
                // hint: create a helper function and reuse it to build all three trees
                buildTree(movieTree,cast, movie);
                buildTree(studioTree, studios,movie);
                buildTree(ratingTree,cast, rating);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public static void buildTree(BSTree tree, String[] keys, String value){
        // for each key given, make sure everything you are putting into the tree are lowercase
        // insert the key
        for(String key:keys){
            key.toLowerCase();
            value.toLowerCase();
            tree.insert(key);
            //before inserting the data check if the value is already in the key or not.
            if(!tree.findDataList(key).contains(value)) tree.insertData(key,value);
        }


    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        // process query
        String[] keys = query.toLowerCase().split(" ");

        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful

        //collect the linked lists of all the keys in a linked list
        LinkedList<LinkedList> allLists = new LinkedList<>();
        for (String key : keys) {
            if(searchTree.findKey(key)) allLists.add(searchTree.findDataList(key));
            else allLists.add(new LinkedList<String>());
        }
        LinkedList<String> common = new LinkedList<>();

        //only print the common ones if there is more than 1 thing to query
        if(keys.length > 1) {
            //add the values of the first one, and remove value that is not also a value in
            //succeeding lists
            common.addAll(allLists.get(0));
            for (int i = 0; i < allLists.size(); i++) {
                common.retainAll(allLists.get(i));
            }
            print(query, common);
        }
        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful
        for(int i = 0; i< keys.length;i++){
            //if the array comes as naturally empty, then print the output
            if(allLists.get(i).isEmpty()) print(keys[i],allLists.get(i));
            //removes all outputs that have been printed before
            allLists.get(i).removeAll(common);
            for(int j =0; j<i;j++){
                allLists.get(i).removeAll(allLists.get(j));
            }
            //if the array is empty after removal, then do not print the output
            if(!allLists.get(i).isEmpty()) print(keys[i],allLists.get(i));
        }

    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // initialize search
        BSTree<String> movieTree = new BSTree<>();
        BSTree<String> studioTree = new BSTree<>();
        BSTree<String> ratingTree = new BSTree<>();

        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        String query = args[QUERYINDEX];
        for(int i = EXTRAQUERYINDEXES; i<args.length;i++){
            query += " " + args[i];
        }

        // populate search trees
        populateSearchTrees(movieTree,studioTree,ratingTree,fileName);
        // choose the right tree to query
        if(searchKind==0)   searchMyQuery(movieTree,query);
        else if(searchKind==1) searchMyQuery(studioTree,query);
        else searchMyQuery(ratingTree,query);


    }
}
