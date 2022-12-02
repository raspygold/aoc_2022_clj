(ns aoc-2022.day02-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [clojure.test :refer :all]
            [aoc-2022.day02 :refer :all]))

(def test-input (slurp "resources/day02_test_data.txt"))
(def puzzle-input (slurp "resources/day02_data.txt"))

(deftest part1-test
  (are [expected input] (= expected (part1 input))
                        15 test-input
                        13009 puzzle-input))

(deftest part2-test
  (are [expected input] (= expected (part2 input))
                        12 test-input
                        10398 puzzle-input))
