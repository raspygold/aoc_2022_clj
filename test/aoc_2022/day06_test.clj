(ns aoc-2022.day06-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [clojure.test :refer :all]
            [aoc-2022.day06 :refer :all]))

(def puzzle-input (slurp "resources/day06_data.txt"))

(deftest part1-test
  (are [expected input] (= expected (part1 input))
                        7 "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
                        5 "bvwbjplbgvbhsrlpgdmjqwftvncz" 
                        6 "nppdvjthqldpwncqszvftbrmjlhg" 
                        10 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" 
                        11 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
                        1282 puzzle-input))

(deftest part2-test
  (are [expected input] (= expected (part2 input))
                        19 "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
                        23 "bvwbjplbgvbhsrlpgdmjqwftvncz" 
                        23 "nppdvjthqldpwncqszvftbrmjlhg" 
                        29 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" 
                        26 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" 
                        3513 puzzle-input))
