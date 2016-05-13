(ns examples.iris.csv
  (:require
    [clojure.java.io :as io]
    [clojure.pprint :refer [pprint print-table]]
    [clojure.data.csv :refer [read-csv]]))

(defn process-csv
  "Reads csv file from resources/iris.csv and split them into input and output matrices"
  []
  (let [iris-file (io/reader "resources/iris.csv")
        shuffled-input (shuffle (read-csv iris-file))

        ; {"iris-versicolor" 0 "iris-setosa" 1 "iris-virginica" 2}
        output-map (->> (map #(first (rseq %)) shuffled-input)
                        set
                        vec
                        (map-indexed #(vec [%2 %1]))
                        (into {}))
        output-features-count (count output-map)
        zeroes (vec (repeat output-features-count 0.0))

        input-partitioned-by-output (map #(partition-by (fn [value] (contains? output-map value)) %)
                                         shuffled-input)
        ; input and output matrix
        input (->> input-partitioned-by-output
                   (map first)
                   (mapv #(map (fn [value] (Double/parseDouble value)) %)))
        output (->> input-partitioned-by-output
                    (map second)
                    (map #(map (fn [value] (assoc zeroes (get output-map value) 1.0)) %))
                    (mapv flatten))]
    {:input  input
     :output output}))

(defn split-input-output
  "Splits input/output into training/cross validation/test (60%/20%/20%)"
  [input output]
  {:pre (= (count input) (count output))}
  (let [training-count (int (Math/floor (* 0.6 (count input))))
        cross-validation-count (int (Math/floor (* 0.2 (count input))))
        test-count (- (count input) training-count cross-validation-count)

        training-input (vec (take training-count input))
        training-output (vec (take training-count output))

        cv-input (vec (take cross-validation-count (nthrest input training-count)))
        cv-output (vec (take cross-validation-count (nthrest output training-count)))

        test-input (vec (take test-count (nthrest input
                                                  (+ cross-validation-count training-count))))
        test-output (vec (take test-count (nthrest output
                                                   (+ cross-validation-count training-count))))]
    {:training {:input training-input
                :output training-output}
     :cv {:input cv-input
          :output cv-output}
     :test {:input test-input
            :output test-output}}))
