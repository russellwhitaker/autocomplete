(ns autocomplete.core-spec
  (:require [speclj.core :refer :all]
            [autocomplete.core :refer :all]
            [clojure.java.io :as io]
            [clojure.string :as s]))

(def ws (s/split
  (slurp (io/resource "resources/english.txt")) #"\r\n"))
(def s-tree (apply add {} ws))

(describe "English word list"
  (it "has words with 'barn' as a prefix"
    (should= '("barn" "barnacle" "barnacled"
               "barnacles" "barnier" "barns"
               "barnstorm" "barnstormed" "barnstormer"
               "barnstormers" "barnstorming" "barnstorms"
               "barny" "barnyard" "barnyards")
             (match s-tree "barn")))
  (it "has words with 'xylo' as a prefix"
    (should= '("xylograph" "xylography" "xyloid"
               "xylophagous" "xylophone" "xylophones"
               "xylophonist" "xylophonists" "xylose"
               "xylotomy")
             (match s-tree "xylo"))))
