(defproject autocomplete "0.1.0"
  :description "A very simple implementation in Clojure of autocomplete"
  :url "https://github.com/russellwhitaker/autocomplete"
  :license {:name "The MIT License (MIT)"
            :url "http://www.opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]]
  :profiles {:dev {:dependencies [[speclj "3.3.2"]]
                   :resources "src/resources"}}
  :plugins [[speclj "3.3.2"]]
  :test-paths ["spec"]
  :main autocomplete.core
  :aot :all
  :aliases {"test"  ["do" ["clean"] ["spec" "--reporter=d"]]
            "build" ["do" ["clean"] ["uberjar"]]})
