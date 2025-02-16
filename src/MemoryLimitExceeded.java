public class MemoryLimitExceeded {

    class Solution {
        public boolean validWordAbbreviation(String word, String abbr) {
            StringBuilder number = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            for(char c: abbr.toCharArray()){
                if(Character.isDigit(c)) {
                    number.append(c);
                } else {
                    if(!number.isEmpty()) {
                        if(number.charAt(0) == '0') return false;
                        int num = Integer.parseInt(number.toString());
                        // this step is where memory limit is saved.... think of abbr as - "999999999a";
                        if(sb.length() + num > word.length()) return false;
                        while(num-->0) sb.append('.');
                        number = new StringBuilder();
                    }
                    {
                        sb.append(c);
                    }
                }
            }
            if(!number.isEmpty()) {
                if(number.charAt(0) == '0') return false;
                // this step is where memory limit is saved.... think of abbr as - "999999999a";
                int num = Integer.parseInt(number.toString());
                if(sb.length() + num > word.length()) return false;
                while(num-->0) sb.append('.');
                number = new StringBuilder();
            }
            String abr = sb.toString();
            System.out.println(abr);
            if(abr.length() != word.length()) return false;
            for(int i =0; i<abr.length(); i++) {
                if(abr.charAt(i) == '.') continue;
                if(abr.charAt(i) != word.charAt(i)) return false;
            }
            return true;
        }
    }

}
