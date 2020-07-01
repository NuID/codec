(ns nuid.codec.transit
  (:require
   [nuid.codec.proto :as proto]
   [nuid.transit :as transit]))

(defmethod proto/encode "application/transit+json"
  ([_ data]      (transit/write data))
  ([_ opts data] (transit/write opts data)))

(defmethod proto/decode "application/transit+json"
  ([_ transit]      (transit/read transit))
  ([_ opts transit] (transit/read opts transit)))
