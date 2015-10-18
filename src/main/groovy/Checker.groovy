/*
 * Created by Chiaki Chikame on 10/18/15.
 *
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/txt/copying/ for more details.
 */

class Checker {
    static boolean check(File f) {
        boolean wellFormed = true
        f.withReader { r ->
            def lines = r.readLines()
            lines.eachWithIndex { String entry, int i ->
                int idxMod = i % 3
                wellFormed &= (idxMod == 0 && entry[0] == 'L') || (idxMod == 1 && entry[0] == 'C') || (idxMod == 2 && entry[0] == 'V')
            }
        }
        return wellFormed
    }
}
