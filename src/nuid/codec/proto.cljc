(ns nuid.codec.proto)

(defmulti encode
  (fn
    ([content-type _]   content-type)
    ([content-type _ _] content-type)))

(defmulti decode
  (fn
    ([content-type _]   content-type)
    ([content-type _ _] content-type)))
