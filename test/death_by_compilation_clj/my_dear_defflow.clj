(ns death-by-compilation-clj.my-dear-defflow
  (:require [clojure.test :refer :all]
            [death-by-compilation-clj.my-defflow :as my-defflow]))

(def defflow #'my-defflow/defflow)
(.setMacro #'defflow)
