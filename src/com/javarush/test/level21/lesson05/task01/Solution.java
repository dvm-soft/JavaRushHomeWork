package com.javarush.test.level21.lesson05.task01;

import java.util.HashSet;
import java.util.Set;

/* Equals and HashCode
В классе Solution исправить пару методов equals/hashCode в соответствии с правилами реализации этих методов.
Метод main не участвует в тестировании.
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object n) {
        if (n == null)
            return false;
        if (n.getClass() !=  getClass())
            return false;
        if (n ==  this)
            return true;
        Solution f = (Solution) n;
        boolean result = true;
        if (first != null)
        {
            if (!first.equals(f.first))
                result = false;
        }
        else if (f.first != null)
            result = false;

        if (last != null)
        {
            if (!last.equals(f.last))
                result = false;
        }
        else if (f.last != null)
            result = false;

        return result;
    }

    public int hashCode() {
        return 31 *
                (first == null ? 0 : first.hashCode()) +
                (last == null ? 0 : last.hashCode());
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        s.add(new Solution(null, null));
        s.add(new Solution(null, "Duck"));
        s.add(new Solution("Donald", null));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
        System.out.println(s.contains(null));
        System.out.println(s.contains(new Solution(null, null)));
        System.out.println(s.contains(new Solution("Donald", null)));
        System.out.println(s.contains(new Solution(null, "Duck")));
        System.out.println(s.contains(new Solution("Duck", null)));

    }
}
