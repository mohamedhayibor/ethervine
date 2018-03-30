(ns ethervine.views
  (:require [re-frame.core :as rf]
            [ethervine.subs :as subs]))


(defn main-panel []
  (let [name (rf/subscribe [::subs/name])
        web3-on-browser (rf/subscribe [::subs/web3-on-browser])]
    [:div
     [:h2 "Hello from " @name]
     [:h2 "Web-3 present? >> " (type @web3-on-browser) @web3-on-browser]]))
