(ns death-by-compilation-clj.reproduce-null)

(defn bootstrap []
  (println (bound? #'*ns*))
  (println *ns*)
  (ns-name *ns*))
