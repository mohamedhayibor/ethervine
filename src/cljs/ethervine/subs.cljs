(ns ethervine.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 ::name
 (fn [db]
   (:name db)))

(rf/reg-sub
  ::web3-on-browser
  (fn [db]
    (:provides-web3? db)))
