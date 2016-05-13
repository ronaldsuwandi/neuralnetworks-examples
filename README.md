# neuralnetworks-examples

Some example projects that uses [neuralnetworks](https://github.com/ronaldsuwandi/neuralnetworks) 
library

## Data set source

* `iris.csv` [UCI Machine Learning Repository](https://archive.ics.uci.edu/ml/datasets/Iris)

## Usage

Run using `lein repl`

```clojure
(use 'examples.iris.app)

; setup initial thetas
(def init-thetas (initial-thetas))

; shuffle and split iris.csv into training, cross validation and test sets (60%, 20%, 20%)
(def processed-csv (process-csv))

; to train and run neural networks using `vectorz` implementation 
(run processed-csv init-thetas :vectorz)
```

## Result

Using both `:ndarray` (core.matrix default) and `:vectorz` implementation (all using the same 
initial thetas and same shuffled csv file)

```
ndarray
| :iteration | :training-error | :cv-error | :test-error | :training-time |
|------------+-----------------+-----------+-------------+----------------|
|          0 |        2.144546 |  2.122925 |    2.183408 |              0 |
|        100 |        0.556129 |  0.518145 |    0.529271 |           7130 |
|        200 |        0.335730 |  0.277782 |    0.257101 |          16034 |
|        400 |        0.243881 |  0.168322 |    0.142792 |          33550 |
|        800 |        0.196183 |  0.101787 |    0.087433 |          66931 |
|       1600 |        0.156697 |  0.047007 |    0.055821 |         145606 |
|       3200 |        0.127961 |  0.042809 |    0.051376 |         337396 |

vectorz
| :iteration | :training-error | :cv-error | :test-error | :training-time |
|------------+-----------------+-----------+-------------+----------------|
|          0 |        2.144546 |  2.122925 |    2.183408 |              0 |
|        100 |        0.556129 |  0.518145 |    0.529271 |           6591 |
|        200 |        0.335730 |  0.277782 |    0.257101 |          15078 |
|        400 |        0.243881 |  0.168322 |    0.142792 |          31990 |
|        800 |        0.196183 |  0.101787 |    0.087433 |          61576 |
|       1600 |        0.156697 |  0.047007 |    0.055821 |         109726 |
|       3200 |        0.127961 |  0.042809 |    0.051376 |         236149 |
```
