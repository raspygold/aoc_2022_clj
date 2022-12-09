(ns aoc-2022.day08-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [clojure.test :refer :all]
            [aoc-2022.day08 :refer :all]))

(def test-input (slurp "resources/day08_test_data.txt"))
(def puzzle-input (slurp "resources/day08_data.txt"))

(deftest part1-test
  (are [expected input] (= expected (part1 input))
    21 test-input
    1843 puzzle-input))

(deftest part2-test
  (are [expected input] (= expected (part2 input))
    8 test-input
    180000 puzzle-input))
