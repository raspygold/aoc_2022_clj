(ns aoc-2022.day07-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [clojure.test :refer :all]
            [aoc-2022.day07 :refer :all]))

(def test-input (slurp "resources/day07_test_data.txt"))
(def puzzle-input (slurp "resources/day07_data.txt"))

(deftest part1-test
  (are [expected input] (= expected (part1 input))
    95437 test-input
    1315285 puzzle-input))

(deftest part2-test
  (are [expected input] (= expected (part2 input))
    "d" test-input
    "9847279" puzzle-input))
