(ns nuid.codec.json
  (:require
   [clojure.data.json :as json]
   [nuid.codec.proto :as proto]))

(defmethod proto/encode "application/json"
  ([_ data]      (json/write-str data))
  ([_ opts data] (apply json/write-str
                        data
                        (interleave
                         (keys opts)
                         (vals opts)))))

(defmethod proto/decode "application/json"
  ([_ json]      (json/read-str json :key-fn keyword))
  ([_ opts json] (apply json/read-str
                        json
                        (interleave
                         (keys opts)
                         (vals opts)))))
