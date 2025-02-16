package leetcode.problems.google.medium.union_find;

import java.util.*;

public class SynonymousSentences {

/*
    You are given a list of equivalent string pairs synonyms where synonyms[i] = [si, ti] indicates that si and ti are equivalent strings. You are also given a sentence text.

    Return all possible synonymous sentences sorted lexicographically.
*/

/*
    Example 1:

    Input: synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]], text = "I am happy today but was sad yesterday"
    Output: ["I am cheerful today but was sad yesterday","I am cheerful today but was sorrow yesterday","I am happy today but was sad yesterday","I am happy today but was sorrow yesterday","I am joy today but was sad yesterday","I am joy today but was sorrow yesterday"]
    Example 2:

    Input: synonyms = [["happy","joy"],["cheerful","glad"]], text = "I am happy today but was sad yesterday"
    Output: ["I am happy today but was sad yesterday","I am joy today but was sad yesterday"]
*/


    class SolutionBFS {
        public List<String> generateSentences(List<List<String>> synonyms, String text) {
            var graph = new HashMap<String,List<String>>();
            for(var s: synonyms) {
                String w1 = s.get(0), w2 = s.get(1);
                graph.putIfAbsent(w1, new ArrayList<>());
                graph.putIfAbsent(w2, new ArrayList<>());
                graph.get(w1).add(w2);
                graph.get(w2).add(w1);
            }

            var map = new HashMap<String,List<String>>();
            for(var w: graph.keySet()) {
                if(map.containsKey(w)) continue;
                var list = new ArrayList<String>();
                map.put(w, list);
                var q = new LinkedList<String>();
                q.add(w);
                while(!q.isEmpty()) {
                    var w2 = q.remove();
                    list.add(w2);
                    for(var nei: graph.get(w2)) {
                        if(map.containsKey(nei)) continue;
                        map.put(nei, list);
                        q.add(nei);
                    }
                }
                Collections.sort(list);
            }

            var sb = new StringBuilder();
            var words = text.split(" ");
            var res = new ArrayList<String>();
            helper(words, sb, res, map, 0);

            return res;
        }

        void helper(String[] words, StringBuilder sb, List<String> res, Map<String, List<String>> map, int start) {
            int n = words.length;
            if(start == n) {
                res.add(sb.toString());
                return;
            }
            var w = words[start];
            var synos = map.get(w);
            var variances = synos == null ? List.of(w) : synos;

            int oldLen = sb.length();
            for(var v: variances) {
                if(!sb.isEmpty()) sb.append(' ');
                sb.append(v);
                helper(words, sb, res, map, start + 1);
                sb.setLength(oldLen);
            }
        }
    }
    class SolutionUF {
        class UnionFind{
            int[] root;
            int[] rank;
            public UnionFind(int size){
                root = new int[size];
                rank = new int[size];
                for(int i = 0; i < size; i++){
                    root[i] = i;
                    rank[i] = 1;
                }
            }
            public int find(int a){
                if(root[a] == a){
                    return a;
                }
                return root[a] = find(root[a]);
            }
            public void union(int a, int b){
                int roota = find(a);
                int rootb = find(b);
                if(roota != rootb){
                    if(rank[roota] > rank[rootb]){
                        root[rootb] = roota;
                    }
                    else if(rank[rootb] > rank[roota]){
                        root[roota] = rootb;
                    }
                    else{
                        root[rootb] = roota;
                        rank[roota] += rank[rootb];
                    }
                }
            }
        }
        public List<String> generateSentences(List<List<String>> synonyms, String text) {
            Set<String> set = new HashSet<>();
            List<String> syns = new ArrayList<>();
            for(List<String> syn : synonyms){
                for(String str : syn){
                    if(set.add(str)){
                        syns.add(str);
                    }
                }
            }
            UnionFind uf = new UnionFind(set.size());
            Map<String, Integer> string_to_index = new HashMap<>();
            for(int i = 0; i < syns.size(); i++){
                string_to_index.put(syns.get(i), i);
            }
            for(List<String> list : synonyms){
                String str1 = list.get(0);
                int index1 = string_to_index.get(str1);
                String str2 = list.get(1);
                int index2 = string_to_index.get(str2);
                uf.union(index1, index2);
            }
            Map<String, List<String>> parent_to_child = new HashMap<>();
            for(String str : syns){
                int index = string_to_index.get(str);
                int parent_index = uf.find(index);
                String parent = syns.get(parent_index);
                parent_to_child.putIfAbsent(parent, new ArrayList<>());
                parent_to_child.get(parent).add(str);
            }
            String[] array = text.split(" ");
            StringBuilder sb = new StringBuilder();
            List<String> result = new ArrayList<>();
            helper(string_to_index, syns, uf, parent_to_child, array, sb, result, 0, set);
            Collections.sort(result);
            return result;
        }
        private void helper(Map<String, Integer> string_to_index, List<String> list, UnionFind uf, Map<String, List<String>> map, String[] array, StringBuilder sb, List<String> result, int index, Set<String> set){
            if(index == array.length){
                sb.deleteCharAt(sb.length() - 1);
                String str = new String(sb);
                result.add(str);
                sb.append(" ");
                return;
            }
            String current = array[index];
            if(set.contains(current)){
                int current_index = string_to_index.get(current);
                int parent_index = uf.find(current_index);
                String parent = list.get(parent_index);
                List<String> neighbors = map.get(parent);
                for(String nei : neighbors){
                    sb.append(nei);
                    sb.append(" ");
                    helper(string_to_index, list, uf, map, array, sb, result, index + 1, set);
                    sb.setLength(sb.length() - nei.length() - 1);
                }
            }
            else{
                sb.append(current);
                sb.append(" ");
                helper(string_to_index, list, uf, map, array, sb, result, index + 1, set);
                sb.setLength(sb.length() - current.length() - 1);
            }
        }
    }
}
