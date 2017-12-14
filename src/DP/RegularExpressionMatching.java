// https://www.youtube.com/watch?v=l3hda49XcDE
// https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/RegexMatching.java

public boolean matchRegex(char[] text, char[] pattern) {
    boolean T[][] = new boolean[text.length + 1][pattern.length + 1];
    T[0][0] = true;

    // Deals with patterns like a* or a*b* or a*b*c*
    // These patterns match empty text
    for (int i = 1; i < T[0].length; i++) {
        if (pattern[i-1] == '*') {
            T[0][i] = T[0][i - 2];
        }
    }

    for (int i = 1; i < T.length; i++) {
        for (int j = 1; j < T[0].length; j++) {
            
            //T="x" and pat="x" or T="x" and pat="." 
            if (pattern[j - 1] == '.' || pattern[j - 1] == text[i - 1]) {
                T[i][j] = T[i-1][j-1];
                
            } else if (pattern[j - 1] == '*')  {
                //T="x" and pat="xa*", taking True from "x" so going j-2
                T[i][j] = T[i][j - 2];
                
                if (pattern[j-2] == '.' || pattern[j - 2] == text[i - 1]) {
                    T[i][j] = T[i][j] | T[i - 1][j];
                }
                
            } else {
                T[i][j] = false;
            }
        }
    }
    return T[text.length][pattern.length];
}
