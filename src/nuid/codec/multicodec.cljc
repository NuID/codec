(ns nuid.codec.multicodec
  (:require
   [nuid.bytes :as bytes]
   #?@(:clj
       [[multiformats.varint :as varint]
        [multiformats.codec :as codec]])))

(def key->code (comp varint/encode codec/key->code))

(defn prefixed
  [k bytes]
  (bytes/concat (key->code k) bytes))

(defn unprefixed
  [bytes]
  (let [[_ offset] (varint/read-bytes bytes 0)
        length     (- (count bytes) offset)]
    (with-open [out (java.io.ByteArrayOutputStream.)]
      (.write out bytes offset length)
      (.toByteArray out))))
