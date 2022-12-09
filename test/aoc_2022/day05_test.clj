(ns aoc-2022.day05-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [clojure.test :refer :all]
            [aoc-2022.day05 :refer :all]))

(def test-input (slurp "resources/day05_test_data.txt"))
(def puzzle-input (slurp "resources/day05_data.txt"))

(deftest part1-test
  (are [expected input initial-stacks] (= expected (part1 input initial-stacks))
    "CMZ" test-input {"1" [\Z, \N],
                      "2" [\M, \C, \D],
                      "3" [\P]}
    "FCVRLMVQP" puzzle-input {"1" [\G \F \V \H \P \S],
                              "2" [\G \J \F \B \V \D \Z \M],
                              "3" [\G \M \L \J \N],
                              "4" [\N \G \Z \V \D \W \P],
                              "5" [\V \R \C \B],
                              "6" [\V \R \S \M \P \W \L \Z],
                              "7" [\T \H \P],
                              "8" [\Q \R \S \N \C \H \Z \V],
                              "9" [\F \L \G \P \V \Q \J]}))

(deftest part2-test
  (are [expected input initial-stacks] (= expected (part2 input initial-stacks))
    "MCD" test-input {"1" [\Z, \N],
                      "2" [\M, \C, \D],
                      "3" [\P]}
    "RWLWGJGFD" puzzle-input {"1" [\G \F \V \H \P \S],
                              "2" [\G \J \F \B \V \D \Z \M],
                              "3" [\G \M \L \J \N],
                              "4" [\N \G \Z \V \D \W \P],
                              "5" [\V \R \C \B],
                              "6" [\V \R \S \M \P \W \L \Z],
                              "7" [\T \H \P],
                              "8" [\Q \R \S \N \C \H \Z \V],
                              "9" [\F \L \G \P \V \Q \J]}))
