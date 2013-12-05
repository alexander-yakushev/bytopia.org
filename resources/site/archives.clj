{:title "Archives"}

[:ul {:class "posts"}
 (for [[date posts] (posts-by-month)]
   [:h4 (parse-date "yyyy-MM" "MMMM yyyy" date)
    [:ul (for [f posts]
           [:li [:a {:href (post-url f)}
                 (:title (first (io/read-doc f)))]])]])]
