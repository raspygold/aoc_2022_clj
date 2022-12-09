(ns aoc-2022.day03-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [clojure.test :refer :all]
            [aoc-2022.day03 :refer :all]))

(def test-input (slurp "resources/day03_test_data.txt"))
(def puzzle-input (slurp "resources/day03_data.txt"))

(deftest part1-test
  (are [expected input] (= expected (part1 input))
    157 test-input
    7742 puzzle-input))

(deftest part2-test
  (are [expected input] (= expected (part2 input))
    70 test-input
    2276 puzzle-input))
