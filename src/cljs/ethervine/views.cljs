(ns ethervine.views
  (:require [re-frame.core :as rf]
            [ethervine.subs :as subs]))


(defn main-panel []
  (let [name (rf/subscribe [::subs/name])]
    [:div "Hello from " @name]))
