package leetcode.problems.google.medium.topologicalSort.very_much_asked;

import leetcode.Pair;

import java.util.*;

public class FindAllPossibleRecipiesFromGivenSupplier {
/*
    You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients.
    The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i].
    Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.

    You are also given a string array supplies containing all the ingredients that you initially have,
    and you have an infinite supply of all of them.

    Return a list of all the recipes that you can create. You may return the answer in any order.

    Note that two recipes may contain each other in their ingredients.



            Example 1:

    Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
    Output: ["bread"]
    Explanation:
    We can create "bread" since we have the ingredients "yeast" and "flour".
    Example 2:

    Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
    Output: ["bread","sandwich"]
    Explanation:
    We can create "bread" since we have the ingredients "yeast" and "flour".
    We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
    Example 3:

    Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
    Output: ["bread","sandwich","burger"]
    Explanation:
    We can create "bread" since we have the ingredients "yeast" and "flour".
    We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
    We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".


    Constraints:

    n == recipes.length == ingredients.length
1 <= n <= 100
            1 <= ingredients[i].length, supplies.length <= 100
            1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
    recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
    All the values of recipes and supplies combined are unique.
    Each ingredients[i] does not contain any duplicate values.
    */

    // We don't really need TopoSort for this problem, simple Bruteforce DFS with DP will equally work...
    class Solution {
        public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
            HashMap<String, Integer> index = new HashMap<>();
            HashMap<String, List<String>> graph = new HashMap<>();

            // create hashset of supplies
            HashSet<String> sup = new HashSet<>(Arrays.asList(supplies));

            // store index of all recipes
            for(int i = 0; i < recipes.length; i++) {
                index.put(recipes[i], i);
            }

            int[] indegree = new int[recipes.length];
            // create a mapping of all the recipes that are Ingredients as well
            // to the recipes they are ingredients for
            for(int i = 0; i < recipes.length; i++) {
                for(String need: ingredients.get(i)) {
                    if(sup.contains(need))
                        continue;

                    graph.putIfAbsent(need, new ArrayList<String>());
                    graph.get(need).add(recipes[i]);
                    indegree[i]++;
                }
            }

            LinkedList<Integer> q = new LinkedList<>();
            // add all the recipes with indegree 0 to the queue
            for(int i = 0; i < recipes.length; i++) {
                if(indegree[i] == 0) {
                    q.add(i);
                }
            }

            List<String> cooked = new ArrayList<>();
            while(!q.isEmpty()) {
                int i = q.poll();
                cooked.add(recipes[i]);

                if(!graph.containsKey(recipes[i])) {
                    // if the graph does not contain this recipe, this means
                    // this recipe is not an ingredient for any other recipe
                    // and no further processing is required
                    continue;
                }

                for(String recipe: graph.get(recipes[i])) {
                    if(--indegree[index.get(recipe)] == 0) {
                        q.add(index.get(recipe));
                    }
                }
            }

            return cooked;
        }
    }

}
