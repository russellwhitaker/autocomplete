# Simple autocomplete

A naive implementation in Clojure of autocomplete functionality, allowing the creation of a [Trie structure](https://en.wikipedia.org/wiki/Trie) from a list of words and matching prefixes against that structure to return candidate words.

## Usage

If you don't aleady have the Clojure runtime install, you can install [Leiningen](https://leiningen.org) easily on MacOS with [Homebrew](https://brew.sh):

```clojure
$ brew install leiningen
```

In development mode, this project imports a snapshot of the `wordsEn.txt` file from the [SIL International](http://www-01.sil.org/linguistics/wordlists/english/) archive described as "a list of 109582 English words compiled and corrected in 1991 from lists obtained from the Interociter bulletin board." You, of course, can use the language word list of your choice; see the example in the `Interactive REPL` section below, which loads from the same URL but can be any arbitrary resource.

### Running the test suite

```bash
$ lein test
Compiling autocomplete.core

English word list
- has words with 'barn' as a prefix
- has words with 'xylo' as a prefix

Finished in 0.00087 seconds
2 examples, 0 failures
```

### Interactive commandline

Run the following command (which will initially load Clojure and project dependencies if you're running this for the first time):

```clojure
$ lein run
Enter a candidate prefix:
footi
Matches:  (footier footing footings)
Enter next prefix:
ax
Matches:  (ax axe axed axel axels axeman axemen axes axial axiality axially axil axillae axillar axillaries axillary axillas axils axing axiom axiomatic axiomatically axioms axis axises axle axled axles axletree axletrees axlike axman axmen axolotl axolotls axon axonal axone axones axonic axons axseed)
Enter next prefix:
kid
Matches:  (kid kidded kidder kidders kiddie kiddies kidding kiddingly kiddish kiddo kiddoes kiddos kiddy kidnap kidnaped kidnapee kidnaper kidnapers kidnaping kidnapped kidnapper kidnappers kidnapping kidnaps kidney kidneys kids kidskin kidskins kidvid)
Enter next prefix:
$
```

An empty line above breaks out of the prompt/readline loop.

### Interactive REPL

```clojure
$ lein repl
nREPL server started on port 58329 on host 127.0.0.1 - nrepl://127.0.0.1:58329
REPL-y 0.3.7, nREPL 0.2.12
Clojure 1.9.0-alpha14
Java HotSpot(TM) 64-Bit Server VM 1.8.0_102-b14
    Docs: (doc function-name-here)
          (find-doc "part-of-name-here")
  Source: (source function-name-here)
 Javadoc: (javadoc java-object-or-class-here)
    Exit: Control+D or (exit) or (quit)
 Results: Stored in vars *1, *2, *3, an exception in *e

autocomplete.core=> (def my-words (clojure.string/split (slurp "http://www-01.sil.org/linguistics/wordlists/english/wordlist/wordsEn.txt")
               #_=>                                     #"\r\n"))
#'autocomplete.core/my-words
autocomplete.core=> (count my-words)
109581
autocomplete.core=> (def my-tree (apply add {} my-words))
#'autocomplete.core/my-tree
autocomplete.core=> (match my-tree "xy")
("xylan" "xylem" "xylems" "xylene" "xylidine" "xylitol" "xylograph" "xylography" "xyloid" "xylophagous" "xylophone" "xylophones" "xylophonist" "xylophonists" "xylose" "xylotomy" "xyster" "xysters" "xysts" "xystus")
autocomplete.core=> (match my-tree "xyl")
("xylan" "xylem" "xylems" "xylene" "xylidine" "xylitol" "xylograph" "xylography" "xyloid" "xylophagous" "xylophone" "xylophones" "xylophonist" "xylophonists" "xylose" "xylotomy")
autocomplete.core=> (match my-tree "xylo")
("xylograph" "xylography" "xyloid" "xylophagous" "xylophone" "xylophones" "xylophonist" "xylophonists" "xylose" "xylotomy")
autocomplete.core=>
```

## Future / Enhancements

* add filtering of accent and diacritic marks from the extended Latin character set used by many non-English languages
* modify `add` and `match` to normalize storage tree and lookup to lowercase only
* add a flexible language dictionary lookup for plugging in dictionaries from different sources (local filesystem, S3 bucket, URL, etc.)

__Maintained by Russell Whitaker__

## License

The MIT License (MIT)

Copyright (c) 2017 Russell Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
