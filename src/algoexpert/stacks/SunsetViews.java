package algoexpert.stacks;

import java.util.ArrayList;
import java.util.Collections;

public class SunsetViews {
    public ArrayList<Integer> sunsetViews(int[] buildings, String direction) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(buildings.length == 0) return ans;
        if(direction.equals("EAST")) {
            int curMax = buildings.length-1;
            ans.add(buildings.length-1);
            for(int i = buildings.length -2; i>=0; i--){
                if(buildings[i] > buildings[curMax]) {
                    ans.add(i);
                    curMax = i;
                }
            }
            Collections.reverse(ans);
        }
        if(direction.equals("WEST")) {
            int curMax = 0;
            ans.add(curMax);
            for(int i = 1; i < buildings.length; i++){
                if(buildings[i] > buildings[curMax]) {
                    ans.add(i);
                    curMax = i;
                }
            }
        }
        return ans;
    }
}
