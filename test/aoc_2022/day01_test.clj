(ns aoc-2022.day01-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [clojure.test :refer :all]
            [aoc-2022.day01 :refer :all]))

(def test-input (slurp "resources/day01_test_data.txt"))
(def puzzle-input (slurp "resources/day01_data.txt"))

(deftest part1-test
  (are [expected input] (= expected (part1 input))
                        24000 test-input
                        74394 puzzle-input))

(deftest part2-test
  (are [expected input] (= expected (part2 input))
                        45000 test-input
                        212836 puzzle-input))