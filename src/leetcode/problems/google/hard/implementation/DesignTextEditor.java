package leetcode.problems.google.hard.implementation;


public class DesignTextEditor {
    /**
     * Design a text editor with a cursor that can do the following:
     * <p>
     * Add text to where the cursor is.
     * Delete text from where the cursor is (simulating the backspace key).
     * Move the cursor either left or right.
     * When deleting text, only characters to the left of the cursor will be deleted. The cursor will also remain within the actual text and cannot be moved beyond it. More formally, we have that 0 <= cursor.position <= currentText.length always holds.
     * <p>
     * Implement the TextEditor class:
     * <p>
     * TextEditor() Initializes the object with empty text.
     * void addText(string text) Appends text to where the cursor is. The cursor ends to the right of text.
     * int deleteText(int k) Deletes k characters to the left of the cursor. Returns the number of characters actually deleted.
     * string cursorLeft(int k) Moves the cursor to the left k times. Returns the last min(10, len) characters to the left of the cursor, where len is the number of characters to the left of the cursor.
     * string cursorRight(int k) Moves the cursor to the right k times. Returns the last min(10, len) characters to the left of the cursor, where len is the number of characters to the left of the cursor.
     */

    // We can alternatively use stacks... leftStack, rightStack...
    class TextEditor {
        char buffer[];
        int left;
        int right;

        public TextEditor() {
            buffer = new char[800000];
            left = 0;
            right = buffer.length - 1;
        }

        public void addText(String text) {
            for (char c : text.toCharArray()) {
                buffer[left] = c;
                left++;
            }
        }

        public int deleteText(int k) {
            if (k > left) k = left;
            left -= k;
            return k;
        }

        public String cursorLeft(int k) {
            k = Math.min(k, left);
            while (k > 0) {
                left--;
                k--;
                buffer[right] = buffer[left];
                right--;
            }
            int len = Math.min(10, left);
            StringBuilder sb = new StringBuilder();
            int i = left;
            while (len != 0) {
                len--;
                i--;
                sb.append(buffer[i]);
            }
            return sb.reverse().toString();
        }

        public String cursorRight(int k) {
            int charOnRight = buffer.length - right - 1;
            k = Math.min(k, charOnRight);
            while (k > 0) {
                k--;
                right++;
                buffer[left] = buffer[right];
                left++;
            }
            int len = Math.min(10, left);
            int i = left;
            StringBuilder sb = new StringBuilder();
            while (len != 0) {
                len--;
                i--;
                sb.append(buffer[i]);
            }
            return sb.reverse().toString();

        }
    }
}
