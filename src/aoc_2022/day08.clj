(ns aoc-2022.day08
  (:require [clojure.string :as str]
            [aoc-2022.utils :refer [parse-int]]
            [clojure.math.combinatorics :as combo]))


(defn shorter-than? [forest tree [y x]]
  (< (aget (aget forest y) x) tree))

(defn tree-visible? [forest y x tree]
  (let [forest-height (count forest)
        forest-width (count (first forest))
        ys (filter #(not= y %) (range 0 forest-height))
        xs (filter #(not= x %) (range 0 forest-width))
        [south-ys north-ys] (partition-by #(< y %) ys)
        [west-xs east-xs] (partition-by #(< x %) xs)
        north (combo/cartesian-product north-ys [x])
        east (combo/cartesian-product [y] east-xs)
        south (combo/cartesian-product south-ys [x])
        west (combo/cartesian-product [y] west-xs)]
    (if (or (every? (partial shorter-than? forest tree) north)
            (every? (partial shorter-than? forest tree) east)
            (every? (partial shorter-than? forest tree) south)
            (every? (partial shorter-than? forest tree) west))
      1
      0)))

(defn count-visible [forest tree direction]
  (let [shorter-trees (count
                       (take-while
                        (partial shorter-than? forest tree) direction))]
    (if (< shorter-trees (count direction))
      (+ shorter-trees 1) 
      shorter-trees)))

(defn calc_scenic_score [forest, y, x tree]
  (let [forest-height (count forest)
        forest-width (count (first forest))
        ys (filter #(not= y %) (range 0 forest-height))
        xs (filter #(not= x %) (range 0 forest-width))
        [north-ys south-ys] (if (= 0 y) [[] ys] (partition-by #(< y %) ys))
        [west-xs east-xs] (if (= 0 x) [[] xs ](partition-by #(< x %) xs))
        north (combo/cartesian-product (reverse north-ys) [x])
        east (combo/cartesian-product [y] east-xs)
        south (combo/cartesian-product south-ys [x])
        west (combo/cartesian-product [y] (reverse west-xs))]
    (reduce * 1 [(count-visible forest tree north)
                 (count-visible forest tree east)
                 (count-visible forest tree south)
                 (count-visible forest tree west)])
    ))

(defn process-input [input]
  (let [tree-rows (str/split-lines input)
        trees-2d-list (map 
               (fn [row] 
                 (map parse-int (vec row)))
               tree-rows)
        forest (to-array-2d trees-2d-list)]
    forest))

(defn part1 [input]
  (let [forest (process-input input)
        visible-trees (map-indexed
                       (fn [yidx row]
                         (map-indexed (partial tree-visible? forest yidx) row))
                       forest)]
    (reduce + 0 (map #(reduce + 0 %) visible-trees))))

(defn part2 [input]
  (let [forest (process-input input)
        tree-scenic-scores (map-indexed
                       (fn [yidx row]
                         (map-indexed (partial calc_scenic_score forest yidx) row))
                       forest)]
    (apply max (map #(apply max %) tree-scenic-scores))
    ))
