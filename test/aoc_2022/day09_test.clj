(ns aoc-2022.day09-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [clojure.test :refer :all]
            [aoc-2022.day09 :refer :all]))

(def test-input (slurp "resources/day09_test_data.txt"))
(def test-input2 (slurp "resources/day09_test_data2.txt"))
(def puzzle-input (slurp "resources/day09_data.txt"))

(deftest part1-test
  (are [expected input] (= expected (part1 input))
    13 test-input
    6384 puzzle-input))

(deftest part2-test
  (are [expected input] (= expected (part2 input))
    1 test-input
    36 test-input2
    2734 puzzle-input))
