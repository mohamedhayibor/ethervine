(ns ethervine.views
  (:require [re-frame.core :as re-frame]
            [ethervine.subs :as subs]
            ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div "Hello from " @name]))
