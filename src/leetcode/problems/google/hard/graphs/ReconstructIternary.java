package leetcode.problems.google.hard.graphs;

import java.util.*;

public class ReconstructIternary {
    /**
    *
    * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight.<br>
    * Reconstruct the itinerary in order and return it.<br><br>
    * All of the tickets belong to a man who departs from <b>"JFK"</b>, thus, the itinerary must begin with "JFK".<br>
    * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.<br>
    * <br>For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].<br>
    * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.<br><br>
    *
    *
    * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]<br>
    * Output: ["JFK","MUC","LHR","SFO","SJC"]<br>
    *
    *
    * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]<br>
    * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]<br>
    * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.<br>
    *
    * */

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> ticket : tickets) {
            graph.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            graph.putIfAbsent(ticket.get(1), new PriorityQueue<>());
            graph.get(ticket.get(0)).add(ticket.get(1));
        }

        LinkedList<String> itinerary = new LinkedList<>();

        dfs("JFK", graph, itinerary);

        return itinerary;
    }

    private void dfs(String airport, Map<String, PriorityQueue<String>> graph, LinkedList<String> itinerary) {
        PriorityQueue<String> nextAirports = graph.get(airport);
        while (!nextAirports.isEmpty()) {
            // Why are we polling from the graph? This is simply removing the edge from the graph, but why does this work?
            // We can't reuse any of our ticket.And this gives us an euler path...
            dfs(nextAirports.poll(), graph, itinerary);
        }
        itinerary.addFirst(airport);
    }
}
