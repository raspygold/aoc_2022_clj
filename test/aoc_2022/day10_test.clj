(ns aoc-2022.day10-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [clojure.test :refer :all]
            [aoc-2022.day10 :refer :all]))

(def test-input (slurp "resources/day10_test_data.txt"))
(def puzzle-input (slurp "resources/day10_data.txt"))

(deftest part1-test
  (are [expected input] (= expected (part1 input))
    13140 test-input
    12640 puzzle-input))

(deftest part2-test
  ;; "Check standard out for result"
  (part2 puzzle-input))
