(ns aoc-2022.day04-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [clojure.test :refer :all]
            [aoc-2022.day04 :refer :all]))

(def test-input (slurp "resources/day04_test_data.txt"))
(def puzzle-input (slurp "resources/day04_data.txt"))

(deftest part1-test
  (are [expected input] (= expected (part1 input))
    2 test-input
    573 puzzle-input))

(deftest part2-test
  (are [expected input] (= expected (part2 input))
    4 test-input
    867 puzzle-input))
