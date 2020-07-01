(ns nuid.codec.cbor
  (:require
   [clj-cbor.core :as cbor]
   [nuid.codec.proto :as proto]))

(defmethod proto/encode "application/cbor"
  ([_ data]      (cbor/encode data))
  ([_ opts data] (cbor/encode opts data)))

(defmethod proto/decode "application/cbor"
  ([_ data]      (cbor/decode data))
  ([_ opts data] (cbor/decode opts data)))
