# neuralnetworks-examples

Some example projects that uses [neuralnetworks](https://github.com/ronaldsuwandi/neuralnetworks) 
library

## Data set source

* `iris.csv` [UCI Machine Learning Repository](https://archive.ics.uci.edu/ml/datasets/Iris)

## Usage

Run using `lein repl`

```clojure
(use 'examples.iris.app)
(use 'examples.iris.csv)

; setup initial thetas
(def init-thetas (initial-thetas))

; shuffle and split iris.csv into training, cross validation and test sets (60%, 20%, 20%)
(def processed-csv (process-csv))

; to train and run neural networks using `vectorz` implementation 
(run processed-csv init-thetas :vectorz)
```

## Result

Using both `:persistent-vector` ([core.matrix](https://github.com/mikera/core.matrix) default), 
`:ndarray`, `:vectorz` and `:clatrix` implementation (all using the same initial thetas and same 
shuffled csv file)

```
persistent-vector
| :iteration | :training-error | :cv-error | :test-error | :training-time |
|------------+-----------------+-----------+-------------+----------------|
|          0 |        1.957541 |  2.008086 |    2.022220 |              0 |
|        100 |        0.642222 |  0.597090 |    0.508589 |           8117 |
|        200 |        0.368939 |  0.401693 |    0.249231 |          21919 |
|        400 |        0.198466 |  0.276737 |    0.087201 |          41264 |
|        800 |        0.128815 |  0.216054 |    0.031734 |          78749 |
|       1600 |        0.098140 |  0.218428 |    0.015926 |         167306 |
|       3200 |        0.075886 |  0.224617 |    0.008630 |         354707 |

ndarray
| :iteration | :training-error | :cv-error | :test-error | :training-time |
|------------+-----------------+-----------+-------------+----------------|
|          0 |        1.957541 |  2.008086 |    2.022220 |              0 |
|        100 |        0.642222 |  0.597090 |    0.508589 |           8643 |
|        200 |        0.368939 |  0.401693 |    0.249231 |          18553 |
|        400 |        0.198466 |  0.276737 |    0.087201 |          39913 |
|        800 |        0.128815 |  0.216054 |    0.031734 |          77245 |
|       1600 |        0.098140 |  0.218428 |    0.015926 |         154624 |
|       3200 |        0.075886 |  0.224617 |    0.008630 |         305047 |

vectorz
| :iteration | :training-error | :cv-error | :test-error | :training-time |
|------------+-----------------+-----------+-------------+----------------|
|          0 |        1.957541 |  2.008086 |    2.022220 |              0 |
|        100 |        0.642222 |  0.597090 |    0.508589 |           5843 |
|        200 |        0.368939 |  0.401693 |    0.249231 |          14659 |
|        400 |        0.198466 |  0.276737 |    0.087201 |          28882 |
|        800 |        0.128815 |  0.216054 |    0.031734 |          58551 |
|       1600 |        0.098140 |  0.218428 |    0.015926 |         138371 |
|       3200 |        0.075886 |  0.224617 |    0.008630 |         256058 |

clatrix
| :iteration | :training-error | :cv-error | :test-error | :training-time |
|------------+-----------------+-----------+-------------+----------------|
|          0 |        1.957541 |  2.008086 |    2.022220 |              0 |
|        100 |        0.642222 |  0.597090 |    0.508589 |           6503 |
|        200 |        0.368939 |  0.401693 |    0.249231 |          17007 |
|        400 |        0.198466 |  0.276737 |    0.087201 |          33475 |
|        800 |        0.128815 |  0.216054 |    0.031734 |          64632 |
|       1600 |        0.098140 |  0.218428 |    0.015926 |         142054 |
|       3200 |        0.075886 |  0.224617 |    0.008630 |         285299 |
```
