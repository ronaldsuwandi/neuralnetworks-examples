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
|        100 |        0.642222 |  0.597090 |    0.508589 |           3994 |
|        200 |        0.368939 |  0.401693 |    0.249231 |           8928 |
|        400 |        0.198466 |  0.276737 |    0.087201 |          17755 |
|        800 |        0.128815 |  0.216054 |    0.031734 |          34553 |
|       1600 |        0.098140 |  0.218428 |    0.015926 |          70452 |
|       3200 |        0.075886 |  0.224617 |    0.008630 |         148290 |

ndarray
| :iteration | :training-error | :cv-error | :test-error | :training-time |
|------------+-----------------+-----------+-------------+----------------|
|          0 |        1.957541 |  2.008086 |    2.022220 |              0 |
|        100 |        0.642222 |  0.597090 |    0.508589 |           3460 |
|        200 |        0.368939 |  0.401693 |    0.249231 |           8068 |
|        400 |        0.198466 |  0.276737 |    0.087201 |          16085 |
|        800 |        0.128815 |  0.216054 |    0.031734 |          31441 |
|       1600 |        0.098140 |  0.218428 |    0.015926 |          62782 |
|       3200 |        0.075886 |  0.224617 |    0.008630 |         133452 |

vectorz
| :iteration | :training-error | :cv-error | :test-error | :training-time |
|------------+-----------------+-----------+-------------+----------------|
|          0 |        1.957541 |  2.008086 |    2.022220 |              0 |
|        100 |        0.642222 |  0.597090 |    0.508589 |           2909 |
|        200 |        0.368939 |  0.401693 |    0.249231 |           6599 |
|        400 |        0.198466 |  0.276737 |    0.087201 |          13050 |
|        800 |        0.128815 |  0.216054 |    0.031734 |          25378 |
|       1600 |        0.098140 |  0.218428 |    0.015926 |          51255 |
|       3200 |        0.075886 |  0.224617 |    0.008630 |         108396 |

clatrix
| :iteration | :training-error | :cv-error | :test-error | :training-time |
|------------+-----------------+-----------+-------------+----------------|
|          0 |        1.957541 |  2.008086 |    2.022220 |              0 |
|        100 |        0.642222 |  0.597090 |    0.508589 |           3033 |
|        200 |        0.368939 |  0.401693 |    0.249231 |           6881 |
|        400 |        0.198466 |  0.276737 |    0.087201 |          13512 |
|        800 |        0.128815 |  0.216054 |    0.031734 |          26429 |
|       1600 |        0.098140 |  0.218428 |    0.015926 |          53129 |
|       3200 |        0.075886 |  0.224617 |    0.008630 |         112705 |
```
