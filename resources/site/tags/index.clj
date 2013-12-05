{:title "Tags"}

[:ul {:class "posts"}
 (for [[tag posts] (tag-map)]
   [:h4 {:id tag} tag
    [:ul (for [[url title] posts]
           [:li [:a {:href url} title]])]])]
