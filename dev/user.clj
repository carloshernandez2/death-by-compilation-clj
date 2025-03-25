(System/setProperty "clj-reload.auto-init" "false")
(set! *ns* (find-ns 'clojure.core))

(ns user
  (:require [clj-reload.core :as repl]))

(defn set-refresh-dirs []
  (repl/init
    {:dirs (remove #{(str (System/getProperty "user.dir") "/" (or *compile-path* "classes"))}
                   (repl/classpath-dirs))}))

(defn refresh [ns]
  (let [loaded (loaded-libs)]
    (binding [*compile-files* true]
      (if (loaded ns)
        (repl/reload)
        (require ns)))))

(user/set-refresh-dirs)

(in-ns 'clojure.core)
(alter-var-root #'*ns* (constantly (find-ns 'user)))
(in-ns 'user)

(comment
  (prn (java.time.LocalDateTime/now))
  (user/set-refresh-dirs)
  (user/refresh)
  (prn (java.time.LocalDateTime/now)))
