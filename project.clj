(defproject neuralnetworks-examples "0.1.0-SNAPSHOT"
  :description "Some examples for neuralnetworks library"
  :url "https://github.com/ronaldsuwandi/neuralnetworks-examples"
  :license {:name "MIT License"
            :url  "https://opensource.org/licenses/MIT"}
  :java-source-paths ["src/java"]
  :dependencies [[org.clojure/clojure "1.8.0"]

                 ; neural networks
                 [ronaldsuwandi/neuralnetworks "0.3.0"]

                 ; csv handling
                 [org.clojure/data.csv "0.1.3"]

                 ;matrix
                 [net.mikera/core.matrix "0.52.0"]
                 [net.mikera/vectorz-clj "0.44.0"]
                 [clatrix "0.5.0"]
                 [com.taoensso/timbre "4.3.1"]])
