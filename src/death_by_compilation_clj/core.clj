(ns death-by-compilation-clj.core
  (:import (java.io Writer)
           (java.time LocalDateTime ZoneOffset ZonedDateTime)
           (java.time.format DateTimeFormatter)))

(defn- iso-string->zoned-at-utc ^ZonedDateTime [iso-8601-string]
  (-> (ZonedDateTime/parse iso-8601-string DateTimeFormatter/ISO_DATE_TIME)
      (.withZoneSameInstant ZoneOffset/UTC)))

(defn local-date-time->utc
  ^ZonedDateTime [local-date-time]
  (ZonedDateTime/of local-date-time ZoneOffset/UTC))

(defn local-date-time->string
  ^String
  [local-date-time & [pattern-string locale]]
  (let [utc-date-time (local-date-time->utc local-date-time)
        formatter (if pattern-string
                    (DateTimeFormatter/ofPattern pattern-string)
                    DateTimeFormatter/ISO_DATE_TIME)]
    (.format utc-date-time (if locale
                             (.withLocale formatter locale)
                             formatter))))

(defn b
  (^LocalDateTime [iso-8601-string]
   (-> iso-8601-string iso-string->zoned-at-utc .toLocalDateTime))
  (^LocalDateTime [date-time-string pattern]
   (let [formatter (DateTimeFormatter/ofPattern pattern)]
     (LocalDateTime/parse date-time-string formatter))))

(defn c
  [arg]
  (sorted-set arg))

(defmethod print-method LocalDateTime [local-date-time ^Writer out] (.write out (str "#a/b \"" (local-date-time->string local-date-time) \")))
(defmethod print-dup LocalDateTime [local-date-time ^Writer out] (.write out (str "#a/b \"" (local-date-time->string local-date-time) \")))
;Fix here:
;(defmethod print-dup LocalDateTime [v ^Writer w]
;  (doseq [^byte b [35 61 40]]
;    (.write w b))
;  (.write w (.getName ^Class LocalDateTime))
;  (.write w "/parse \"")
;  (.write w (str v))
;  (.write w "\")"))
