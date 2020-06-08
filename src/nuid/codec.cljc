(ns nuid.codec
  (:require
   [nuid.codec.json]
   [nuid.codec.proto :as proto]
   [nuid.codec.transit]
   #?@(:clj
       [[nuid.codec.cbor]])))

(def encode proto/encode)
(def decode proto/decode)
