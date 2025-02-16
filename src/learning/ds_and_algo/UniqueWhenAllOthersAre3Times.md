### Find element that occurs once when all others occur 3 times
The crux of the approach is

If a bit appears the first time, add it to seenOnce. It will not be added to seenTwice because of it's presence in seenOnce.

If a bit appears a second time, remove it from seenOnce and add it to seenTwice.

If a bit appears a third time, it won't be added to seenOnce because it is already present in seenTwice. After that it will be removed from seenTwice.

```java
class Solution {
    public int singleNumber(int[] nums) {
        // Initialize seenOnce and seenTwice to 0
        int seenOnce = 0, seenTwice = 0;

        // Iterate through nums
        for (int num : nums) {
            // Update using derived equations
            seenOnce = (seenOnce ^ num) & (~seenTwice);
            seenTwice = (seenTwice ^ num) & (~seenOnce);
        }

        // Return integer which appears exactly once
        return seenOnce;
    }
}
```
