(ns death-by-compilation-clj.my-defflow
  (:require [clojure.test :refer :all]))

(defmacro defflow
  [name]
  `(def ~name 1))
