(ns autocomplete.core
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

;; The following atoms are only populated if -main is called, in order
;; to avoid memory bloat when 'add' and 'match' are used as library
;; functions.
(def dictionary "resources/english.txt")
(def delimiter #"\r\n")
(def words (atom []))
(def search-tree (atom {}))

(defn add
  "Populate a deeply nested map with words indexed by character"
  [tree & words]
  (reduce #(assoc-in %1 (concat %2 [::term]) %2) tree words))

(defn match
  "Return words matching prefix characters. Assumes a tree has
   already been constructed.
  "
  [tree prefix]
  (letfn
    [(search
       [node]
       (mapcat (fn [[k v]]
                 (if (= ::term k) [v] (search v)))
               node))]
    (search (get-in tree prefix))))

(defn- get-prefix
  "Private helper method used with -main to loop through suffixes
   entered on the commandline when invoked as 'lein trampoline run'.
  "
  [prompt]
  (loop [p prompt]
    (println p)
    (let [input (read-line)]
      (if (not (empty? input))
        (do (println "Matches: " (match @search-tree input))
            (recur (get-prefix "Enter next prefix: ")))
        (System/exit 0)))))

(defn -main
  "Only invoked from the commandline, decoupling 'add' and 'match' to be used
   separately with arbitrary word lists without invoking the overhead hit
   of the heavy lifting here.
  "
  []
  (let [english
         (s/split
           (slurp (io/resource dictionary)) #"\r\n")]
    (swap! words into english)
    (swap! search-tree into (apply add {} @words))
    (get-prefix "Enter a candidate prefix: ")))
