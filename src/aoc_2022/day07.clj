(ns aoc-2022.day07
  (:require [clojure.string :as str]))

(defn process-input [input]
  (->> (str/split-lines input)))

(defn new-dir [path]
  (sorted-map :path path, :type :dir, :size 0))

(defn new-file [path size]
  (sorted-map :path path, :type :file, :size size))

(defn navigate-up [current-path]
  (let [path-segments (str/split current-path #"/")
        last-segment (last path-segments)
        to-remove (+ 1 (count last-segment))]

    (subs current-path 0 (- (count current-path) to-remove))))

(defn calculate-cd-path [current-path arg]
  (if (= arg "/") "/"
      (if (= arg "..")
        (navigate-up current-path)
        (str current-path arg "/"))))

(defn process-instruction [filesystem current-path instr]
  (let [[_ cmd arg] (str/split instr #" ")
        cmd-is-cd (str/starts-with? cmd "cd")
        existing-paths (map #(get % :path) filesystem)
        cd-path (if cmd-is-cd
                  (calculate-cd-path current-path arg)
                  current-path)
        cd-path-new? (if cmd-is-cd
                       (not (some #{cd-path} existing-paths))
                       false)]

    (if cd-path-new?
      [(conj filesystem (new-dir cd-path)) cd-path]
      [filesystem cd-path])))

(defn process-instruction-result [filesystem current-path result]
  ;; ignoring dir listing from ls, if we don't navigate to it and ls we can ignore it
  (if (str/starts-with? result "dir")
    [filesystem current-path]
    (let [[size name] (str/split result #" ")
          size (Long/valueOf size)]
      [(conj filesystem (new-file (str/join [current-path name]) size)) current-path])))

(defn process-terminal-output [[filesystem current-path] output]
  (if (str/starts-with? output "$")
    (process-instruction filesystem current-path output)
    (process-instruction-result filesystem current-path output)))

(defn build-filesystem [input]
  (let [current-path "/"
        filesystem [(new-dir current-path)]
        terminal-output (process-input input)
        [filesystem, _last-path] (reduce
                                  process-terminal-output
                                  [filesystem current-path]
                                  terminal-output)]

    filesystem))

(defn calc-directory-sizes [filesystem]
  (let [directories (filter #(= :dir (get % :type)) filesystem)
        files (filter #(= :file (get % :type)) filesystem)
        directories-with-size (map
                               #(assoc % :size
                                       (first
                                        (reduce
                                         (fn [[total-size dir-path] file]
                                           (if (str/starts-with? (get file :path) dir-path)
                                             [(+ total-size (get file :size)) dir-path]
                                             [total-size dir-path]))
                                         [0 (get % :path)]
                                         files)))
                               directories)]

    directories-with-size))

(defn part1 [input]
  (let [filesystem (build-filesystem input)
        directories-with-size (calc-directory-sizes filesystem)]

    (reduce
     (fn [total-size dir]
       (if (<= (get dir :size) 100000)
         (+ total-size (get dir :size))
         total-size))
     0
     directories-with-size)))

(defn part2 [input]
  (let [filesystem (build-filesystem input)
        files (filter #(= :file (get % :type)) filesystem)
        directories-with-size (calc-directory-sizes filesystem)
        disk-size 70000000
        disk-used (reduce + 0 (map #(get % :size) files))
        disk-free (- disk-size disk-used)
        target-unused 30000000
        to-free (- target-unused disk-free)
        dir-del-targets (filter
                         (fn [dir]
                           (println (- target-unused disk-free) to-free (get dir :size) (<= to-free (get dir :size)))
                           (<= to-free (get dir :size)))
                         directories-with-size)]

    (get (apply min-key #(get % :size) dir-del-targets) :size)))
