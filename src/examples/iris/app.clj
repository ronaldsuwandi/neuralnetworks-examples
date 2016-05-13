(ns examples.iris.app
  (:require [clojure.test :refer :all]
            [neuralnetworks.core :as nn]
            [clojure.core.matrix :as m]
            [clojure.pprint :refer [print-table]]
            [neuralnetworks.optimizer.stopping-conditions :refer :all]
            [examples.iris.csv :refer :all]))

(defn build-matrices
  [{:keys [training cv test]}]
  {:training {:input  (m/array (:input training))
              :output (m/array (:output training))}
   :cv       {:input  (m/array (:input cv))
              :output (m/array (:output cv))}
   :test     {:input  (m/array (:input test))
              :output (m/array (:output test))}})

(defn update-results!
  [instance results {:keys [training cv test]}]
  (conj! results {:iteration      @(get-in instance [:states :iteration])
                  :training-error (format "%.6f"
                                          (nn/error instance (:input training) (:output training)))
                  :cv-error       (format "%.6f" (nn/error instance (:input cv) (:output cv)))
                  :test-error     (format "%.6f" (nn/error instance (:input test) (:output test)))
                  :training-time  @(get-in instance [:states :training-duration])}))

(defn initial-thetas
  "Initializes thetas with 1 hidden layer"
  []
  (nn/randomize-thetas 4 [4] 3))

(defn run
  "Train neural networks and check the error rates for training, cross validation and test data set
   using different iterations"
  [processed-csv thetas matrix-implementation]
  ; matrix-implementation can be :vectorz :ndarray (see core.matrix docs)
  (m/set-current-implementation matrix-implementation)
  (let [splitted-csv (split-input-output (:input processed-csv) (:output processed-csv))
        {:keys [training cv test] :as splitted-matrices} (build-matrices splitted-csv)
        instance (nn/new-instance (:input training) thetas (:output training) :classification {})
        training-iterations [100 200 400 800 1600 3200]
        results (transient [])]

    (conj! results {:iteration      0
                    :training-error (format "%.6f" (nn/error instance
                                                             (:input training) (:output training)))
                    :cv-error       (format "%.6f" (nn/error instance (:input cv) (:output cv)))
                    :test-error     (format "%.6f" (nn/error instance (:input test) (:output test)))
                    :training-time  0})

    (doseq [iteration training-iterations]
      (nn/train! instance [(max-iterations iteration)])
      (update-results! instance results splitted-matrices))
    (print-table (persistent! results))))
